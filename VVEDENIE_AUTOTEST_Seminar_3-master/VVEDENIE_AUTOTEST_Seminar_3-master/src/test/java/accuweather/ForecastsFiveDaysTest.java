package accuweather;

import io.restassured.http.Method;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seminar.accuweather.location.Location;
import seminar.accuweather.weather.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

class ForecastsFiveDaysTest extends AccuweatherAbstractTest {

    @Test

    void testGetResponse() { // получение ответа
        Weather weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)//  подготовка исходных данных / добавляем параметры
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{locationKey}")// Resource URL http://dataservice.accuweather.com/forecasts/v1/daily/5day/50
                .then().statusCode(200).time(lessThan(2000L)) // выполнение тестового действия / время статус
                .extract().response().body().as(Weather.class); // вывод массива дней
        Assertions.assertEquals(5, weather.getDailyForecasts().size()); // проверка количество дней
        System.out.println(weather);
    }

    @Test
    void testGetLocations() { // тест на Location / месте нахождения локации
        Map<String, String> mapQuery = new HashMap<>();
        mapQuery.put("apikey", getApiKey());
        mapQuery.put("q", "Samara");
        List<Location> listLocations = given().queryParams(mapQuery)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200)
                .extract().body().jsonPath().getList(".", Location.class);
        Assertions.assertAll(() -> Assertions.assertEquals(10, listLocations.size()),
                () -> Assertions.assertEquals("Samarai", listLocations.get(2).getLocalizedName()));


    }

    @Test
    void testgetLocations2() {
        Map<String, String> mapQuery = new HashMap<>();
        mapQuery.put("apikey", getApiKey());
        mapQuery.put("q", "Samara");
        List<Location> listLocations = given().queryParams(mapQuery)
                .when().request(Method.GET, getBaseUrl() + "/locations/v1/cities/autocomplete") // тип метода и куда конкретно идет
                .then().statusCode(200)
                .extract().body().jsonPath().getList(".", Location.class);
        Assertions.assertAll(() -> Assertions.assertEquals(10, listLocations.size()),
                () -> Assertions.assertEquals("Samarai", listLocations.get(2).getLocalizedName()));


    }


}
