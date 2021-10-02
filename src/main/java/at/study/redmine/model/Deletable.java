package at.study.redmine.model;

public interface Deletable<T extends Entity> {

    void delete();
}
