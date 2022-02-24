package steps;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.api.client.RestResponse;
import at.study.redmine.api.dto.users.UserInfoDto;
import at.study.redmine.cucumber.PageObjectHelper;
import at.study.redmine.db.requests.UserRequests;
import at.study.redmine.model.Project;
import at.study.redmine.model.Token;
import at.study.redmine.model.user.Status;
import at.study.redmine.ui.BrowserManager;
import at.study.redmine.ui.pages.HeaderPage;
import at.study.redmine.ui.pages.LoginPage;
import at.study.redmine.ui.pages.NewUserPage;
import at.study.redmine.ui.pages.Page;
import at.study.redmine.context.Context;
import at.study.redmine.model.User;
import at.study.redmine.utils.CompareUtils;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Но;
import cucumber.api.java.ru.То;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class AssertionsSteps {

    @То("Открыта домашняя страница")
    public void homepageOpened() {
        AllureAssert.assertEquals(
                BrowserManager.getBrowser().getDriver().getCurrentUrl(),
                "http://edu-at.dfu.i-teco.ru/",
                "Открыта домашняя страница");
    }

    @То("В заголовке отображается текст Вошли как <логин пользователя (.*)>")
    public void loggedAsUser(String stashID) {
        AllureAssert.assertEquals(
                Page.getPage(HeaderPage.class).loggedAs.getText(),
                "Вошли как " + Context.getStash().get(stashID, User.class).getLogin(),
                "Проверка отображения текста \"Вошли как " + Context.getStash().get(stashID, User.class).getLogin() + "\"");
    }

    @То("В заголовке отображается элемент Домашняя страница")
    public void homepageIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).homepage.isDisplayed(), "В заголовке отображается элемент \"Домашняя страница\"");
    }


    @Но("На странице авторизации отображается ошибка с текстом (.*)")
    public void authorisationErrorText(String errorDescription) {
        AllureAssert.assertEquals(
                Page.getPage(LoginPage.class).error.getText(),
                errorDescription,
                "Отображается ошибка с текстом \"" + errorDescription + "\"");
    }

    @То("Открыта страница (.*)")
    public void currentUrlIs(String url) {
        AllureAssert.assertEquals(
                BrowserManager.getBrowser().getDriver().getCurrentUrl(),
                url,
                "Отображается страница " + url);

    }

    @То("Отображается страница (.*)")
    public void currentPageName(String pageName) {
        AllureAssert.assertEquals(
                PageObjectHelper.findElement(pageName, "Имя страницы").getText(),
                pageName,
                "Отображается страница " + pageName
        );
    }

    @И("На странице (.*) в множестве (.*) отображается проект (.*)")
    public void listContainsEntity(String pageName, String listName, String stashID) {
        String expectedProjectName = ((Project) Context.getStash().get(stashID)).getName();
        AllureAssert.assertTrue(
                PageObjectHelper.findElementsList(pageName, listName)
                        .stream()
                        .map(WebElement::getText)
                        .anyMatch(projectName -> projectName.equals(expectedProjectName)),
                "На странице отображается проект " + stashID
        );
    }

    @И("На странице (.*) в множестве (.*) отсутствует проект (.*)")
    public void listNotContainsEntity(String pageName, String listName, String stashID) {
        String expectedProjectName = ((Project) Context.getStash().get(stashID)).getName();
        AllureAssert.assertFalse(
                PageObjectHelper.findElementsList(pageName, listName)
                        .stream()
                        .map(WebElement::getText)
                        .anyMatch(projectName -> projectName.equals(expectedProjectName)),
                "На странице отображается проект " + stashID
        );
    }

    @И("Таблица на странице (.*) отсортирована в порядке (.*), ключ сортировки (.*)")
    public void tableIsSortedBy(String pageName, String sortKind, String column) {
        List<String> columnFields = PageObjectHelper.findElementsList(pageName, column).stream().map(WebElement::getText).collect(Collectors.toList());
        if (sortKind.equals("убывания")) {
            AllureAssert.assertTrue(CompareUtils.isListSortedByDesc(columnFields), "Таблица отсортирована по убыванию, ключ:" + column);
        } else if (sortKind.equals("возрастания")) {
            AllureAssert.assertTrue(CompareUtils.isListSortedByAsc(columnFields), "Таблица отсортирована по возрастанию, ключ:" + column);
        } else
            throw new IllegalArgumentException("Не найден тип сортировки, используйте слова убывания/возрастания. Полученное слово:" + sortKind);
    }

    @То("Таблица на странице (.*)  не отсортирована по ключу: (.*)")
    public void tableIsNotSortedBy(String pageName, String column) {
        List<String> columnFields = PageObjectHelper.findElementsList(pageName, column).stream().map(WebElement::getText).collect(Collectors.toList());
        AllureAssert.assertFalse(CompareUtils.isListSorted(columnFields), "Таблица не отсортирована по ключу: " + column);
    }

    @То("Статус код ответа на запрос (.*) равен (.*)")
    public void responseCodeAssert(String requestName, String responseCode) {
        RestResponse response = (RestResponse) Context.getStash().get(requestName + " response");
        AllureAssert.assertEquals(response.getStatusCode(), Integer.parseInt(responseCode), "Код ответа равен " + responseCode);
    }

    /**
     * Шаг проверки ответа на запрос
     *
     * @param requestName Имя запроса к API
     * @param parameters  Для проверки параметров возможна передача - прямых значений, значения null, связанного значения из UserInfoDto по StashID.
     */
    @То("В ответе на запрос (.*) получены:")
    public void responsePayloadAssert(String requestName, Map<String, String> parameters) {
        RestResponse response = (RestResponse) Context.getStash().get(requestName + " response");
        UserInfoDto userInfoDtoResponse = response.getPayload(UserInfoDto.class);
        if (parameters.containsKey("id")) {
            if (parameters.get("id").equals("NotNull")) {
                AllureAssert.assertNotNull(userInfoDtoResponse.getUser().getId());
            }
        }
        if (parameters.containsKey("status")) {
            AllureAssert.assertEquals(Integer.parseInt(parameters.get("status")), userInfoDtoResponse.getUser().getStatus());
        }
        if (parameters.containsKey("error1")) {
            for (int i = 1; parameters.containsKey("error" + i); i++) {
                AllureAssert.assertTrue(userInfoDtoResponse.getErrors().contains(parameters.get("error" + i)), "В ответе получена ошибка " + parameters.get("error" + i));
            }
        }
        if (parameters.containsKey("API key") && Context.getStash().contains(parameters.get("API key"))) {
            User user = Context.getStash().get(parameters.get("API key"), User.class);
            AllureAssert.assertEquals(userInfoDtoResponse.getUser().getApiKey(), user.getTokens().stream().filter(s -> s.getAction() == Token.TokenType.API).map(s -> s.getValue()).findFirst().get(), "API key из запросов совпадают");
        }
        if (parameters.containsKey("API key") && parameters.get("API key") == null) {
            Boolean exCathed = false;
            try {
                userInfoDtoResponse.getUser().getApiKey();
            } catch (NoSuchElementException e) {
                exCathed = true;
            }
            AllureAssert.assertTrue(exCathed, "Пользователь не найден в БД");
        }
        if (parameters.containsKey("firstname") && Context.getStash().contains(parameters.get("firstname"))) {
            User user = Context.getStash().get(parameters.get("firstname"), User.class);
            AllureAssert.assertEquals(userInfoDtoResponse.getUser().getFirstName(), user.getFirstName(), "Имена пользователей из запросов совпадают");
        }
        if (parameters.containsKey("lastname") && Context.getStash().contains(parameters.get("lastname"))) {
            User user = Context.getStash().get(parameters.get("lastname"), User.class);
            AllureAssert.assertEquals(userInfoDtoResponse.getUser().getLastName(), user.getLastName(), "Фамилии пользователей из запросов совпадают");
        }
        if (parameters.containsKey("admin") && parameters.get("admin") == null) {
            Boolean exCathed = false;
            try {
                userInfoDtoResponse.getUser().getAdmin();
            } catch (NoSuchElementException e) {
                exCathed = true;
            }
            AllureAssert.assertTrue(exCathed, "Пользователь не найден в БД");
        }
    }

    @То("Запросом к БД проверить информацию о пользователе (.*):")
    public void checkUserFromDB(String requestName, Map<String, String> parameters) {
        User user = Context.getStash().get(requestName, User.class);
        Integer userID = user.getId();
        if (parameters.containsKey("status")) {
            AllureAssert.assertEquals(new UserRequests().read(userID).getStatus(), Status.getValue(Integer.parseInt(parameters.get("status"))));
        }
    }

    @И("В базе данных отсутсвует информация о пользователе (.*)")
    public void noUserInDB(String userName) {
        User user = Context.getStash().get(userName, User.class);
        Integer userID = user.getId();
        Boolean exCathed = false;
        try {
            new UserRequests().read(userID);
        } catch (NullPointerException e) {
            exCathed = true;
        }
        AllureAssert.assertTrue(exCathed, "Пользователь не найден в БД");
    }

    @И("Пользователь (.*) присутствует в БД")
    public void userFoundInDB(String userName) {
        User user = Context.getStash().get(userName, User.class);
        Integer userID = user.getId();
        AllureAssert.assertNotNull(new UserRequests().read(userID));
    }

    @То("Отображается сообщение Пользователь <логин> создан с данными пользователя (.*)")
    public void popupUserCreated(String userName) {
        User user = Context.getStash().get(userName, User.class);
        AllureAssert.assertEquals(Page.getPage(NewUserPage.class).notice.getText(), "Пользователь " + user.getLogin() + " создан.", "Отображается сообщение \"Пользователь " + user.getLogin() + " создан.\"");
        Integer userID = Integer.parseInt(Page.getPage(NewUserPage.class).newUserUrl.getAttribute("href").replaceAll("[^0-9]", ""));
        user.setId(userID);
        Context.getStash().put(userName, user);
    }

}

