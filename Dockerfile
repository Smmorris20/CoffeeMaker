# ===== Build Stage =====
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy source and tests
COPY src ./src
COPY unittests ./unittests
COPY libs ./libs

# Compile main source
RUN mkdir -p out
RUN javac -d out $(find src -name "*.java")

# Compile unit tests
RUN mkdir -p test-classes
RUN javac -cp libs/junit-platform-console-standalone.jar -d test-classes $(find unittests -name "*.java")

# ===== Runtime Stage =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy compiled classes
COPY --from=build /app/out . 
COPY --from=build /app/test-classes ./test-classes
COPY --from=build /app/libs ./libs

# Default: run main app
CMD ["java", "edu.ncsu.csc326.coffeemaker.Main"]
