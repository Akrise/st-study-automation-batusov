package at.study.redmine.db.requests;

import at.study.redmine.model.Entity;

public interface Read<T extends Entity> {

    /**
     * Получить значение из базы данных по ID
     * @param id идентификатор в БД
     * @return сущность, осодержащаяся в БД
     */
    T read(Integer id);
}
