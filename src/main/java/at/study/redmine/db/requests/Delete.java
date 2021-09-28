package at.study.redmine.db.requests;

public interface Delete {

    /**
     * Удалить запись в БД по id
     * @param id
     */
    void delete(Integer id);
}
