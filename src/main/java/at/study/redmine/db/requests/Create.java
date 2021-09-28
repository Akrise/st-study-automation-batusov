package at.study.redmine.db.requests;

import at.study.redmine.model.Entity;

public interface Create<T extends Entity> {

    /**
     *
     * @param entity
     */
    void create(T entity);
}
