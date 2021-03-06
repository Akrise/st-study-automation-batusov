#language: ru

Функция: Создание пользователя со списком E-mail адресов

  Предыстория:
    Пусть Создан список E-mail адресов СПИСОК_ЕМЕЙЛ_АДРЕСОВ:
      | Адрес         | По умолчанию | Уведомления |
      | one@mail.ru   | true         | true        |
      | two@mail.ru   | false        | true        |
      | three@mail.ru | false        | false       |
      | four@mail.ru  | false        | true        |
    И В системе создан пользователь Пользователь со следующими параметрами:
      | E-mail | СПИСОК_ЕМЕЙЛ_АДРЕСОВ |
    И Браузер открыт на домашней странице


  Сценарий:
    Если Нажать на кнопку Войти
    И Войти в систему пользователем Пользователь
    То В заголовке отображается элемент
