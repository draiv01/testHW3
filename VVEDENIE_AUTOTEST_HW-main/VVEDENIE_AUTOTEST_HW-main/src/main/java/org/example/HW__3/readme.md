## Введение в автоматизацию тестирования (семинары)

### 3. Тестирование REST API

В рамках выполнения задания семинара, вам необходимо покрыть тестами проект
предложений на лекции: AccuWeather — https://developer.accuweather.com/.

1) Перенести тесты в свой Maven проект с помощью библиотеки Rest Assured.
   Покрыть тестами endpoints (есть класс в базовых примерах Weather.java)

**https://developer.accuweather.com/accuweather-forecast-api/apis/get/forecasts/v1/daily/1day/%7BlocationKey%7D**

   **https://developer.accuweather.com/accuweather-forecast-api/apis/get/forecasts/v1/daily/10day/%7BlocationKey%7D**

  **https://developer.accuweather.com/accuweather-forecast-api/apis/get/forecasts/v1/daily/15day/%7BlocationKey%**

   Добавить тесты по endpoint (есть класс в базовых примерах Location.java) - 
   
**https://developer.accuweather.com/accuweather-locations-api/apis/get/locations/v1/cities/autocomplete**