package at.study.redmine.db.requests;

import at.study.redmine.model.Entity;

public interface Update<T extends Entity> {

    void update(Integer id, T entity);
}
