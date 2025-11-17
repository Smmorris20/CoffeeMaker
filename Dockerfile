# ===== Build Stage =====
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy source code
COPY src ./src

# Compile Java source files
RUN mkdir -p out
RUN javac -d out $(find src -name "*.java")

# ===== Runtime Stage =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy compiled classes
COPY --from=build /app/out .

# Set the main class to run
CMD ["java", "edu.ncsu.csc326.coffeemaker.Main"]

