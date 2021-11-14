package at.study.redmine.utils;

import at.study.redmine.api.ui.BrowserUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompareUtils {

    @Step("Проверка сортировки списка по убыванию")
    public static boolean isListSortedByDesc(List<String> list){
        list = list.stream().map(String::toLowerCase).collect(Collectors.toList());
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        return list.equals(sortedList);
    }

    public static boolean isWebListSortedByDesc(List<WebElement> list){
        return isListSortedByDesc(BrowserUtils.getElementsText(list));
    }

    @Step("Проверка сортировки списка по возрастанию")
    public static boolean isListSortedByAsc(List<String> list){
        list = list.stream().map(String::toLowerCase).collect(Collectors.toList());
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        return list.equals(sortedList);
    }

    public static boolean isWebListSortedByAsc(List<WebElement> list) {
        return isListSortedByAsc(BrowserUtils.getElementsText(list));
    }

    @Step("Проверка сортировки списка в любом порядке")
    public static boolean isListSorted(List<String> list){
        return isListSortedByAsc(list)||isListSortedByDesc(list);
    }


    public static boolean isWebListSorted(List<WebElement> list){
        return isListSorted(BrowserUtils.getElementsText(list));
    }
}
