package at.study.redmine.model;

public interface Readable<T extends CreatableEntity> {

    T read();
}
