package at.study.redmine.model.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Language {
    RUSSIAN("ru"),
    ENGLISH("en");

    public final String languageCode;

    public static Language getValue(String code) {
        //TODO переделать без if
        if (code.equals("ru")) {
            return Language.RUSSIAN;
        }
        if (code.equals("en")) {
            return Language.ENGLISH;
        }
        throw new IllegalArgumentException("Не найдено значение Language с кодом " + code);
    }

}
