package at.study.redmine.model.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    UNREGISTERED(0),
    ACTIVE(1),
    UNACCEPTED(2),
    LOCKED(3);

    public final int statusCode;

    public static Status getValue(int code){
        for(Status status: Status.values()){
            if(status.statusCode == code){
                return status;
            }
        }
        throw new IllegalArgumentException("Не найдено значение Status с кодом " + code);
    }
}
