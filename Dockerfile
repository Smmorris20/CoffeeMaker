# ===== Build Stage =====
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy source and test files
COPY src ./src
COPY unittests ./unittests
COPY libs ./libs

# Create directories for compiled classes
RUN mkdir -p out test-classes

# Compile main source code
RUN javac -d out $(find src -name "*.java")

# Compile unit tests with classpath including main classes and JUnit
RUN javac -cp libs/junit-platform-console-standalone.jar:out -d test-classes $(find unittests -name "*.java")

# ===== Runtime Stage =====
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy compiled classes and JUnit jar
COPY --from=build /app/out ./out
COPY --from=build /app/test-classes ./test-classes
COPY --from=build /app/libs ./libs

# Build argument to choose mode (default = web)
ARG MODE=web
ENV MODE=${MODE}

# Default command (runs shell to evaluate mode)
CMD ["sh", "-c", "if [ \"$MODE\" = \"test\" ]; then \
        java -jar libs/junit-platform-console-standalone.jar -cp test-classes:out --scan-classpath; \
    else \
        java -cp out:libs/* edu.ncsu.csc326.coffeemaker.CoffeeMakerWeb; \
    fi"]
