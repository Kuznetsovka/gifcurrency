# <img src="https://avatars.githubusercontent.com/u/59878441?s=400&u=0e11f16a42c2df3837066138017e67af0ce1472b&v=4" width="300">

## Назначение приложения

Повышение настроения от изменения курса валют

## Используемые технологии

* Java 8
* Gradle
* Spring (Spring Boot, Spring MVC)
* Feign
* Lombok
* Swagger
* Mockito

## Используемые внешние API сервисы

* [REST API гифок](https://developers.giphy.com/docs/api#quick-start-guide)
* [REST API курсов валют](https://docs.openexchangerates.org/)
## Список доступных end-point API

* Вчерашний курс {заданной валюты} по отношению к USD. - http://localhost:8080/api/v1/check/{валюта}/yesterday
* Текущий курс {заданной валюты} по отношению к USD. - http://localhost:8080/api/v1/check/{валюта}/today
* Получение gif - http://localhost:8080/api/v1/check/{валюта}

## Ссылка на документацию
Документация отобразится, когда Сервис запущен
* [Documents](http://localhost:8080/v2/api-docs)

## Ссылка на Docker контейнер

* docker pull kuznetsovka/dockerhub:gifcurrencypush
* docker run -d -p 8080:8080 -t dockerhub:gifcurrencypush
