package at.study.redmine.model.user;

public enum Type {
    ANONYMOUS_USER("AnonymousUser"),
    USER("User"),
    GROUP_NON_MEMBER("GroupNonMember"),
    GROUP_ANONYMOUS("GroupAnonymous");

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
