#language: ru

Функция: Вход администратором. Проверка элементов заголовка.

  Предыстория:
    Пусть В системе создан пользователь Администратор со следующими параметрами:
      | Администратор | true |
    И Браузер открыт на домашней странице

  @ui
  Сценарий: Вход администратором. Проверка элементов заголовка.
    Если На странице Заголовок нажать на кнопку Войти
    И Войти в систему пользователем Администратор
    То  Открыта домашняя страница
    И В заголовке отображается текст Вошли как <логин пользователя Администратор>
    И На странице Заголовок присутствует элемент Домашняя страница
    И На странице Заголовок присутствует элемент Моя страница
    И На странице Заголовок присутствует элемент Проекты
    И На странице Заголовок присутствует элемент Помощь
    Но На странице Заголовок присутствует элемент Администрирование
    И На странице Заголовок присутствует элемент Моя учетная запись
    И На странице Заголовок присутствует элемент Выйти
    Но На странице Заголовок НЕ отображается элемент Войти
    Но На странице Заголовок НЕ отображается элемент Регистрация
    И На странице Заголовок присутствует элемент Поле поиска
    И На странице Заголовок присутствует элемент Поиск:
