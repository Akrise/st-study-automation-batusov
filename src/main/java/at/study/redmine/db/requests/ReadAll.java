package at.study.redmine.db.requests;

import at.study.redmine.model.Entity;

import java.util.List;

public interface ReadAll<T extends Entity> {

    List<T> readAll();
}
