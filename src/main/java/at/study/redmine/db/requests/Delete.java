package at.study.redmine.db.requests;

import at.study.redmine.model.Entity;

public interface Delete<T extends Entity> {

    /**
     * Удалить запись в БД по id
     * @param id
     */
    void delete(Integer id);
}
