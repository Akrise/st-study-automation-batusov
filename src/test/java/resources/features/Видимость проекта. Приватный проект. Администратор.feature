#language: ru

  Функция: Видимость проекта. Приватный проект. Администратор.
    Предыстория:
      Пусть В системе создан пользователь Администратор со следующими параметрами:
      |Администратор| true|
      И В системе создан проект Проект со следующими параметрами:
      |Публичный| true|
      И Браузер открыт на домашней странице

      @ui
      Сценарий: Видимость проекта. Приватный проект. Администратор.
        Если На странице Заголовок нажать на кнопку Войти
        И Войти в систему пользователем Администратор
        То Открыта домашняя страница
        Если На странице Заголовок нажать на кнопку Проекты
        То Отображается страница Проекты
        И На странице Проекты в множестве Все названия проектов отображается проект Проект
