# Weather Station API

A simple mock REST API that simulates a weather monitoring station for the fictional city of **Windholm**. The service provides current weather data and a weekly forecast with randomized values to simulate real-time sensor readings.

This project is intended for testing, prototyping, and learning purposes.

## Technology Stack

- **Java**: 21
- **Spring Boot**: 4.0.1
- **Build Tool**: Gradle 8.x
- **Testing**: JUnit 5
- **Dependencies**:
  - Spring Web MVC
  - Spring Security
  - PostgreSQL Driver
  - Lombok

## Getting Started

### Prerequisites

- Java 21 or higher
- Gradle (or use the included Gradle wrapper)

### Building the Project

```bash
# Build the project
./gradlew build

# Clean and build
./gradlew clean build
```

### Running the Application

```bash
# Run the application
./gradlew bootRun
```

The application will start on `http://localhost:8080` (default port).

### Running Tests

```bash
# Run all tests
./gradlew test

# Run a specific test class
./gradlew test --tests "com.weather.station.StationApplicationTests"
```

## API Endpoints

### Base URL
```
/api/weather
```

### 1. Current Weather

Returns current weather conditions with randomized sensor values.

**Endpoint:** `GET /api/weather/current`

**Response Example:**
```json
{
  "city": "Windholm",
  "timestamp": "2025-01-14T15:32:00",
  "temperature": 12.5,
  "humidity": 68,
  "windSpeed": 14.2,
  "condition": "CLOUDY"
}
```

**Field Specifications:**

| Field | Type | Description | Range |
|-------|------|-------------|-------|
| city | string | City name | Fixed: "Windholm" |
| timestamp | string | Current date/time (ISO 8601) | Current time |
| temperature | number | Temperature in °C | -15.0 to 35.0 |
| humidity | integer | Humidity percentage | 20 to 100 |
| windSpeed | number | Wind speed in km/h | 0.0 to 50.0 |
| condition | string | Weather condition | SUNNY, CLOUDY, RAINY, STORMY, SNOWY, FOGGY, WINDY |

### 2. Weekly Forecast

Returns a 7-day weather forecast with randomized predictions.

**Endpoint:** `GET /api/weather/forecast`

**Response Example:**
```json
{
  "city": "Windholm",
  "generatedAt": "2025-01-14T15:32:00",
  "forecast": [
    {
      "date": "2025-01-15",
      "tempMin": 5.2,
      "tempMax": 14.8,
      "condition": "SUNNY",
      "precipitation": 10
    },
    {
      "date": "2025-01-16",
      "tempMin": 3.1,
      "tempMax": 11.5,
      "condition": "RAINY",
      "precipitation": 75
    }
    // ... 5 more days
  ]
}
```

**Forecast Item Specifications:**

| Field | Type | Description | Range |
|-------|------|-------------|-------|
| date | string | Forecast date (ISO 8601) | Next 7 days |
| tempMin | number | Minimum temperature °C | -15.0 to 20.0 |
| tempMax | number | Maximum temperature °C | tempMin to 35.0 |
| condition | string | Expected condition | SUNNY, CLOUDY, RAINY, STORMY, SNOWY, FOGGY, WINDY |
| precipitation | integer | Precipitation chance % | 0 to 100 |

**Constraint:** `tempMax` is always greater than or equal to `tempMin`.

## Features

- Returns valid JSON responses
- Each request generates new random values
- Temperature values have one decimal place precision
- No database required (in-memory data generation)
- No authentication required
- No external dependencies for weather data
- Comprehensive test coverage (unit and integration tests)
- Built following Test-Driven Development (TDD) methodology

## Architecture

The application follows a layered architecture pattern:

- **Controller Layer** (`controller/`): REST API endpoints that handle HTTP requests and responses
  - `WeatherController`: Exposes `/api/weather/current` and `/api/weather/forecast` endpoints

- **Service Layer** (`service/`): Business logic and data generation
  - `WeatherService`: Coordinates weather data retrieval
  - `WeatherDataGenerator`: Generates randomized weather data within specified constraints

- **Model Layer** (`model/`): Data transfer objects and enums
  - `dto/`: Response DTOs for API endpoints
  - `enums/`: Weather condition enumeration (SUNNY, CLOUDY, RAINY, STORMY, SNOWY, FOGGY, WINDY)

All components are developed with corresponding unit tests, and the API is validated through integration tests.

## Project Structure

```
station/
├── src/
│   ├── main/
│   │   ├── java/com/weather/station/
│   │   │   ├── StationApplication.java           # Main entry point
│   │   │   ├── controller/
│   │   │   │   └── WeatherController.java        # REST API endpoints
│   │   │   ├── service/
│   │   │   │   ├── WeatherService.java           # Business logic
│   │   │   │   └── WeatherDataGenerator.java     # Random data generation
│   │   │   └── model/
│   │   │       ├── enums/
│   │   │       │   └── WeatherCondition.java     # Weather condition enum
│   │   │       └── dto/
│   │   │           ├── CurrentWeatherResponse.java
│   │   │           ├── ForecastResponse.java
│   │   │           └── ForecastDay.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/com/weather/station/
│           ├── StationApplicationTests.java      # Context load test
│           ├── WeatherApiIntegrationTest.java    # API integration tests
│           ├── controller/
│           │   └── WeatherControllerTest.java
│           ├── service/
│           │   ├── WeatherServiceTest.java
│           │   └── WeatherDataGeneratorTest.java
│           └── model/
│               ├── enums/
│               │   └── WeatherConditionTest.java
│               └── dto/
│                   ├── CurrentWeatherResponseTest.java
│                   ├── ForecastResponseTest.java
│                   └── ForecastDayTest.java
├── docs/
│   ├── api_requirement.md
│   └── plan.txt
├── build.gradle.kts
├── settings.gradle.kts
├── CLAUDE.md
└── README.md
```

## Documentation

- **API Requirements**: See `docs/api_requirement.md` for detailed specifications
- **Development Plan**: See `docs/plan.txt` for the TDD implementation methodology
- **AI Agent Guide**: See `CLAUDE.md` for guidance when using Claude Code

## Development Methodology

This project was developed following **Test-Driven Development (TDD)** principles:

1. **Red Phase**: Write failing tests first
2. **Green Phase**: Implement minimal code to pass tests
3. **Refactor Phase**: Improve code quality while keeping tests green

The development followed a structured phased approach:
- **Phase 1**: Preparation and documentation
- **Phase 2**: Planning and design
- **Phase 3**: TDD implementation with continuous testing
- **Phase 4**: Refactoring and final documentation

All components include comprehensive unit tests, and the API is validated through integration tests to ensure correct behavior and data constraints.

## License

This is a demo project for learning and testing purposes.
