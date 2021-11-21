package at.study.redmine.context;

public class Context {

    private static Stash stash;

    public static Stash getStash(){
        if(stash == null){
            stash = new Stash();
        }
        return stash;
    }

    public static void clearStash() {
        stash = null;
    }
}
