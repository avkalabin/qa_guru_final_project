# Дипломный проект по автоматизации тестирования web, api и mobile платформ

## :open_book: Содержание:

- [Технологии и инструменты](#gear-технологии-и-инструменты)
- [Тест кейсы](#heavy_check_mark-Тест-кейсы)
- [Запуск тестов](#computer-запуск-тестов-из-терминала)
- [Примеры использования](#примеры-использования)
- [Запуск тестов в Jenkins](#-запуск-тестов-из-jenkins)
- [Отчет о результатах тестирования в Allure Report](#-отчет-о-результатах-тестирования-в-Allure-report)
- [Интеграция с Allure TestOps](#-интеграция-с-allure-testops)
- [Уведомления в Telegram](#-уведомления-в-telegram)
- [Видео прохождения тестов](#movie_camera-видео-с-прогоном-тестов)

## :gear: Технологии и инструменты

<p align="left">
<a href="https://www.jetbrains.com/idea/"><img src="media/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA" title="IntelliJ IDEA"/></a>
<a href="https://www.java.com/"><img src="media/logo/Java.svg" width="50" height="50" alt="Java" title="Java"/></a>
<a href="https://github.com/"><img src="media/logo/GitHub.svg" width="50" height="50" alt="Github" title="GitHub"/></a>
<a href="https://junit.org/junit5/"><img src="media/logo/JUnit5.svg" width="50" height="50" alt="JUnit 5" title="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="media/logo/Gradle.svg" width="50" height="50" alt="Gradle" title="Gradle"/></a>
<a href="https://selenide.org/"><img src="media/logo/Selenide.svg" width="50" height="50" alt="Selenide" title="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="media/logo/Selenoid.svg" width="50" height="50" alt="Selenoid" title="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="media/logo/Allure_Report.svg" width="50" height="50" alt="Allure" title="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="media/logo/Jenkins.svg" width="50" height="50" alt="Jenkins" title="Jenkins"/></a>
<a href="https://web.telegram.org/"><img src="media\logo\Telegram.svg" width="50" height="50" alt="Telegram" title="Telegram"></a>
<a href="https://qameta.io/"><img src="media\logo\Allure_TO.svg" width="50" height="50" alt="Allure_TO" title="Allure_TO"></a>
</p>

В данном проекте автотесты написаны на **Java** с использованием фреймворка для тестирования **Selenide**. Для сборки проекта в среде **IntelliJ IDEA** используется **Gradle**.
**JUnit5** задействован в качестве фреймворка модульного тестирования. Запуск тестов выполняется из **Jenkins**. **Selenoid** используется для запуска браузеров в контейнерах **Docker**.

**Allure Report**, **AllureTestOps** и **Telegram Bot** используются для визуализации результатов тестирования.

## :heavy_check_mark: Тест кейсы

- Проверка элементов главного меню
- В перечне технологий содержится ссылка на "1С-Битрикс"
- Форма "Свяжитесь с нами" имеет обязательные поля
- Адрес московоского офиса указан корректно
- В разделе "Карьера" указана возможность удаленной работы
- Допустимый раздел прикрепляемого CV не более 3Mb
- Форма расчет стоимости проекта работоспособна

## :computer: Запуск тестов из терминала

```bash
gradle clean test -Dselenoid_url="selenoid.autotests.cloud/wd/hub" -Dbrowser_size="1920х1080" -Dbrowser="chrome" -Dbrowser_version="100.0"
```
## <img width="4%" title="Jenkins" src="media/logo/Jenkins.svg"> Запуск тестов из [Jenkins](https://jenkins.autotests.cloud/job/015_aziyatdinov_final_ui/)
Для запуска сборки необходимо перейти в раздел <code><strong>*Собрать с параметрами*</strong></code> и нажать кнопку <code><strong>*Собрать*</strong></code>.

<p align="center">
  <img src="media/screen/start_jenkins.png" alt="Jenkins" width="800">
</p>

После выполнения сборки, в блоке <code><strong>*История сборок*</strong></code> напротив номера сборки появится
значок *Allure Report* и *Allure TestOps* кликнув по которому, откроется страница с сформированным html-отчетом и тестовой документацией.

## <img width="4%" title="Allure Report" src="media/logo/Allure_Report.svg"> Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job/018-avkalabin-lesson15/12/allure/)

<p align="center">
  <img src="media/screen/jenkins_overview.png" alt="allure-report" width="900">
</p>

<p align="center">
  <img src="media/screen/jenkins_behaviors.png" alt="allure-report_1" width="900">
</p>

## <img width="4%" title="Allure TestOPS" src="media/logo/Allure_TO.svg"> Интеграция с [Allure TestOps](https://allure.autotests.cloud/project/2194/dashboards)

### Основной дашборд

<p align="center">
  <img src="media/screen/allure-dashboard.PNG" alt="dashboard" width="900">
</p>

### Список тестов с результатами прогона

<p align="center">
  <img src="media/screen/allure-testops-results.png" alt="allure-results" width="900">
</p>

### Тест-кейсы

<p align="center">
  <img src="media/screen/allure-testops-testcases.png" alt="testcase" width="900">
</p>

## <img width="4%" title="Telegram" src="media/logo/Telegram.svg"> Уведомления в Telegram
После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне тестов.

<p align="center">
<img title="Telegram Notifications" src="media/screen/telegram-notification.PNG">

## :movie_camera: Видео с прогоном тестов

В отчетах Allure для каждого теста прикреплен не только скриншот, но и видео прохождения теста

<p align="center">
  <img title="Video" src="media/video/aston-tests.gif">
</p>
