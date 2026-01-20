# Weather Station API

## Project Description

A simple mock API that simulates a weather monitoring station. The service provides current weather data and a weekly forecast for a fictional city. Each request returns randomized values to simulate real-time sensor readings.

This project is intended for testing, prototyping, and learning purposes.

## API Specification

### Base URL

```
/api/weather
```

---

### Endpoint 1: Current Weather

Returns current weather conditions with randomized sensor values.

**Request:**
```
GET /api/weather/current
```

**Response:**
```json
{
  "city": "Windholm",
  "timestamp": "2025-01-14T15:32:00",
  "temperature": 12.5,
  "humidity": 68,
  "windSpeed": 14.2,
  "condition": "Cloudy"
}
```

**Field descriptions:**

| Field | Type | Description | Random Range |
|-------|------|-------------|--------------|
| city | string | City name | Fixed: "Windholm" |
| timestamp | string | Current date/time (ISO 8601) | Current time |
| temperature | number | Temperature in °C | -15.0 to 35.0 |
| humidity | integer | Humidity percentage | 20 to 100 |
| windSpeed | number | Wind speed in km/h | 0.0 to 50.0 |
| condition | string | Weather condition | Random from list* |

*Conditions: "SUNNY", "CLOUDY", "RAINY", "STORMY", "SNOWY", "FOGGY", "WINDY"

> **Note:** In the actual implementation, condition values are returned in UPPERCASE as enum names.

---

### Endpoint 2: Weekly Forecast

Returns a 7-day weather forecast with randomized predictions.

**Request:**
```
GET /api/weather/forecast
```

**Response:**
```json
{
  "city": "Windholm",
  "generatedAt": "2025-01-14T15:32:00",
  "forecast": [
    {
      "date": "2025-01-15",
      "tempMin": 5.2,
      "tempMax": 14.8,
      "condition": "Sunny",
      "precipitation": 10
    },
    {
      "date": "2025-01-16",
      "tempMin": 3.1,
      "tempMax": 11.5,
      "condition": "Rainy",
      "precipitation": 75
    }
  ]
}
```

**Field descriptions:**

| Field | Type | Description | Random Range |
|-------|------|-------------|--------------|
| city | string | City name | Fixed: "Windholm" |
| generatedAt | string | Generation timestamp | Current time |
| forecast | array | Array of 7 daily forecasts | — |

**Forecast item:**

| Field | Type | Description | Random Range |
|-------|------|-------------|--------------|
| date | string | Forecast date (ISO 8601) | Next 7 days |
| tempMin | number | Minimum temperature °C | -15.0 to 20.0 |
| tempMax | number | Maximum temperature °C | tempMin to 35.0 |
| condition | string | Expected condition | Random from list |
| precipitation | integer | Precipitation chance % | 0 to 100 |

---

## Requirements

### Functional
- Service returns valid JSON responses
- Each request generates new random values
- Temperature values have one decimal place
- tempMax is always greater than or equal to tempMin

### Non-functional
- No database required
- No authentication required
- No external dependencies for data