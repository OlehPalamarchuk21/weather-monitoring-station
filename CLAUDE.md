# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Weather Station API - A mock Spring Boot REST API that simulates a weather monitoring station for the fictional city of "Windholm". Returns randomized weather data for testing, prototyping, and learning purposes.

**Technology Stack:**
- Java 21
- Spring Boot 4.0.1
- Spring MVC (RESTful web services)
- Spring Security (included but not configured)
- PostgreSQL driver (runtime dependency)
- Lombok (compile-time annotation processing)
- JUnit 5 (testing)

## Build and Development Commands

### Build and Run
```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Clean build
./gradlew clean build
```

### Testing
```bash
# Run all tests
./gradlew test

# Run a single test class
./gradlew test --tests "com.weather.station.StationApplicationTests"

# Run with test output
./gradlew test --info
```

### Other Gradle Tasks
```bash
# List all available tasks
./gradlew tasks

# Check dependencies
./gradlew dependencies
```

## Project Structure

```
src/main/java/com/weather/station/
  └── StationApplication.java          # Main Spring Boot application entry point

src/main/resources/
  ├── application.properties            # Spring configuration
  ├── static/                           # Static web resources
  └── templates/                        # Template files

src/test/java/com/weather/station/
  └── StationApplicationTests.java     # Basic context load test

docs/
  ├── api_requirement.md                # Complete API specification and requirements
  └── plan.txt                          # Development methodology (TDD, phased approach)
```

## API Specification

The API implements two endpoints under `/api/weather`:

1. **GET /api/weather/current** - Returns current weather with randomized values
   - City: "Windholm" (fixed)
   - Temperature: -15.0 to 35.0°C (1 decimal place)
   - Humidity: 20-100%
   - Wind speed: 0.0-50.0 km/h
   - Condition: SUNNY, CLOUDY, RAINY, STORMY, SNOWY, FOGGY, WINDY (uppercase enum)

2. **GET /api/weather/forecast** - Returns 7-day forecast with randomized predictions
   - Each day: date, tempMin, tempMax, condition, precipitation (0-100%)
   - Constraint: tempMax >= tempMin

See `docs/api_requirement.md` for complete field specifications and JSON response formats.

## Development Methodology

This project follows a phased TDD approach as outlined in `docs/plan.txt`:

1. **Preparation**: Technology selection, documentation setup
2. **Planning**: Implementation plan creation with AI assistance
3. **Implementation**: Test-first development (red-green cycle), incremental progress
4. **Completion**: Refactoring, final testing, documentation updates

When implementing features:
- Write tests first (red), then implementation (green)
- Run all tests after each change
- Update documentation as features are added
- Use package-based organization for refactoring

## Coding Standards

- **No comments**: Do not add comments to the code. Keep code self-explanatory through clear naming and simple logic.
- **Test methodology**: Use the build-operate-check pattern for all tests:
  - Build: Set up test data and objects
  - Operate: Execute the operation being tested
  - Check: Assert the expected outcomes

## Key Configuration Notes

- **Application name**: "station" (defined in application.properties)
- **Package structure**: `com.weather.station.*`
- **Java version**: 21 (configured in build.gradle.kts)
- **Spring Boot version**: 4.0.1
- **Database**: PostgreSQL driver is included but not yet configured
- **Security**: Spring Security dependency is present but not yet configured

## Architecture Notes

- Currently a minimal Spring Boot scaffold with no controllers, services, or domain models implemented
- Lombok is configured for reducing boilerplate (use @Data, @Builder, etc.)
- Spring Security and PostgreSQL are dependencies but authentication and database are not required per specs (no-auth, no-database mock API)
