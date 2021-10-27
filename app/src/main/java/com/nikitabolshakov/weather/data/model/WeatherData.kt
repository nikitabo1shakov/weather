package com.nikitabolshakov.weather.data.model

fun getWorldCities() = mutableListOf(
    Weather(City("London", 51.5085300, -0.1257400), 1, 2),
    Weather(City("Tokyo", 35.6895000, 139.6917100), 3, 4),
    Weather(City("Sydney", -33.8679, 151.207)),
    Weather(City("Los Angeles", 34.0522, -118.244)),
    Weather(City("Paris", 48.8534100, 2.3488000), 5, 6),
    Weather(City("Berlin", 52.52000659999999, 13.404953999999975), 7, 8),
    Weather(City("Rome", 41.9027835, 12.496365500000024), 9, 10),
    Weather(City("New York City", 40.7143, -74.006)),
    Weather(City("Minsk", 53.90453979999999, 27.561524400000053), 11, 12),
    Weather(City("Istanbul", 41.0082376, 28.97835889999999), 13, 14),
    Weather(City("Washington", 38.9071923, -77.03687070000001), 15, 16),
    Weather(City("Kiev", 50.4501, 30.523400000000038), 17, 18),
    Weather(City("San Francisco", 37.7749, -122.419)),
    Weather(City("Miami", 25.7743, -80.1937))
)

fun getRussianCities() = mutableListOf(
    Weather(City("Москва", 55.755826, 37.617299900000035), 1, 2),
    Weather(City("Санкт-Петербург", 59.9342802, 30.335098600000038), 3, 3),
    Weather(City("Новосибирск", 55.00835259999999, 82.93573270000002), 5, 6),
    Weather(City("Екатеринбург", 56.83892609999999, 60.60570250000001), 7, 8),
    Weather(City("Нижний Новгород", 56.2965039, 43.936059), 9, 10),
    Weather(City("Казань", 55.8304307, 49.06608060000008), 11, 12),
    Weather(City("Челябинск", 55.1644419, 61.4368432), 13, 14),
    Weather(City("Омск", 54.9884804, 73.32423610000001), 15, 16),
    Weather(City("Ростов-на-Дону", 47.2357137, 39.701505), 17, 18),
    Weather(City("Уфа", 54.7387621, 55.972055400000045), 19, 20),
    Weather(City("Владивосток", 43.1056, 131.874)),
    Weather(City("Набережные Челны", 55.7254, 52.4112))
)