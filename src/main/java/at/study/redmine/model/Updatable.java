package at.study.redmine.model;

public interface Updatable<T extends CreatableEntity> {

    void update(T entity);
}
