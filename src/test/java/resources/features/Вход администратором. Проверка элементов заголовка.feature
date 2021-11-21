#language: ru

Функция: Вход администратором. Проверка элементов заголовка.

  Предыстория:
    Пусть В системе создан пользователь Администратор со следующими параметрами:
      | Администратор | true |
    И Браузер открыт на домашней странице

  Сценарий: Вход администратором, проверка элементов заголовка
    Если Нажать на кнопку Войти
    И Войти в систему пользователем Администратор
    То  Открыта домашняя страница
    И В заголовке отображается текст Вошли как <логин пользователя Администратор>
    И В заголовке отображается элемент Домашняя страница
    И В заголовке отображается элемент Моя страница
    И В заголовке отображается элемент Проекты
    И В заголовке отображается элемент Администрирование
    И В заголовке отображается элемент Помощь
    И В заголовке отображается элемент Моя учётная запись
    И В заголовке не отображается элемент Войти
    И В заголовке не отображается элемент Регистрация
    И В заголовке отображается поле для поиска
    И В заголовке отображается элемент Поиск