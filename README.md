# Sprint_4

Здесь хранится код для автотестов на Java для учебного [проекта Яндекс.Самокат.](https://qa-scooter.praktikum-services.ru)


## Тестовые сценарии

1. **Выпадающий список в разделе «Вопросы о важном».** Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.
2. **Заказ самоката.** Нужно проверить весь флоу позитивного сценария с двумя наборами данных. Проверить точки входа в сценарий, их две: кнопка «Заказать» вверху страницы и внизу.
Из чего состоит позитивный сценарий:
* Нажать кнопку «Заказать». На странице две кнопки заказа.
* Заполнить форму заказа.
* Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.

## Дополнительные проверки

1. Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
2. Проверить: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.

## Использованные технологии

1. Java 11
2. JUnit 4.13.2
3. Selenium-Java 4.21.0
4. Maven 4.0.0

