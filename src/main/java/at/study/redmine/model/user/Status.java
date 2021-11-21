package at.study.redmine.model.user;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum Status {
    UNREGISTERED(0, "Не зарегистрированный"),
    ACTIVE(1, "Активный"),
    UNACCEPTED(2, "Неподтвержденный"),
    LOCKED(3, "Заблокированный");

    public final int statusCode;
    private final String description;

    public static Status getValue(int code){
        for(Status status: Status.values()){
            if(status.statusCode == code){
                return status;
            }
        }
        throw new IllegalArgumentException("Не найдено значение Status с кодом " + code);
    }

    public static Status getValue(String description){
        return Stream.of(Status.values())
                .filter(status -> status.description.equals(description))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("По описанию " + description + " статус не найден."));
    }
}
