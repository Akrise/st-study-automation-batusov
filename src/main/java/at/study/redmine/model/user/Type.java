package at.study.redmine.model.user;

public enum Type {
    ANONYMOUSUSER("AnonymousUser"),
    USER("User"),
    GROUPNONMEMBER("GroupNonMember"),
    GROUPANONYMOUS("GroupAnonymous");

    public final String dbType;

    Type(String dbType) {
        this.dbType = dbType;
    }

    public static Type getValue(String userType){
        for(Type type : Type.values()){
            if(type.dbType.equals(userType)){
                return type;
            }
        }
        throw new IllegalArgumentException("Не найдено значение Type по строке " + userType);
    }
}
