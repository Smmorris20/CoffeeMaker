# ===== Build Stage =====
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy source code
COPY src ./src

# Copy unit tests
COPY unittests ./unittests

# Download JUnit standalone jar (change version if needed)
RUN mkdir -p libs
RUN curl -L -o libs/junit-platform-console-standalone.jar \
    https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/junit-platform-console-standalone-1.10.0.jar

# Compile Java source files
RUN mkdir -p out
RUN javac -d out $(find src -name "*.java")

# Compile unit tests
RUN mkdir -p out_test
RUN javac -d out_test -cp out:libs/junit-platform-console-standalone.jar $(find unittests -name "*.java")

# Run unit tests
RUN java -jar libs/junit-platform-console-standalone.jar \
    --class-path out:out_test \
    --scan-class-path

# ===== Runtime Stage =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy compiled classes from build stage
COPY --from=build /app/out .

# Set the main class to run
CMD ["java", "edu.ncsu.csc326.coffeemaker.Main"]
