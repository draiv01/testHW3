package HW__3.accuweather;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.example.HW__3.accuweather.weather.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

class ForecastsLocationTest extends AccuweatherAbstractTest {

    @Test
    void testGetResponse1DayOfDailyForecasts() { // получение ответа (1 день)
        Weather weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50) //  подготовка исходных данных / добавляем параемтры
                .when().get(getBaseUrl() + "/forecasts/v1/daily/1day/{locationKey}") // Resource URL: http://dataservice.accuweather.com/forecasts/v1/daily/1day/
                .then().statusCode(200).time(lessThan(2000L)) // выполнение тестового действия / время статус
                .extract().response().body().as(Weather.class); // вывод массива дней
        Assertions.assertEquals(1, weather.getDailyForecasts().size()); // проверка количество дней
    }

    @Test
    void testGetResponse10DayOfDailyForecasts() { // получение ответа (10 дней)
        String code = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50) //  подготовка исходных данных / добавляем параемтры
                .when().get(getBaseUrl() + "/forecasts/v1/daily/10day/{locationKey}") // Resource URL: http://dataservice.accuweather.com/forecasts/v1/daily/10day
                .then().statusCode(401)
                .extract().jsonPath().getString("Code");


        String message = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/10day/{locationKey}") // Resource URL: http://dataservice.accuweather.com/forecasts/v1/daily/10d
                .then().statusCode(401)
            //тут .getString("Message") без ":"
                .extract().jsonPath().getString("Message:");

        Assertions.assertAll(() -> Assertions.assertEquals("Unauthorized: ", code),  // "Code": "Unauthorized"
                () -> Assertions.assertEquals("Api Authorization failed", message)); // "Message": "Api Authorization failed"
    }

    @Test
    void testGetResponse15DayOfDailyForecasts() { // получение ответа (15 дней)

        String code = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/15day/{locationKey}")// Resource URL: http://dataservice.accuweather.com/forecasts/v1/daily/15day/
                .then().statusCode(401)
                .extract().jsonPath().getString("Code");

        String message = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/15day/{locationKey}")
                .then().statusCode(401)
                .extract().jsonPath().getString("Message");

        Assertions.assertAll(() -> Assertions.assertEquals("Unauthorized", code),
                () -> Assertions.assertEquals("Api Authorization failed", message));
    }

    @Test
    void testGetResponseLocations() {

//        Map<String, String> mapQuery = new HashMap<>();
//        mapQuery.put("apikey", getApiKey());
//        mapQuery.put("q", "Moscow");
//        List<Location> locationList = given().queryParams(mapQuery)
//                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
//                .then().statusCode(200)
//                .extract().body().jsonPath().getList(".", Location.class);
//        Assertions.assertAll(() -> Assertions.assertEquals(10, locationList.size()),
//                () -> Assertions.assertEquals("Moscow", locationList.get(2).getLocalizedName()));

        JsonPath response = given().queryParam("apikey", getApiKey()).queryParam("q", "Moscow")
                .when().request(Method.GET, getBaseUrl() + "/locations/v1/cities/autocomplete") // Resource URL: http://dataservice.accuweather.com/locations/v1/cities/autocomplete
                .body().jsonPath();
        Assertions.assertAll(() -> Assertions.assertEquals("Moscow", response.get("[0].LocalizedName")),
                () -> Assertions.assertEquals("294021", response.get("[0].Key")));
    }
}


