package at.study.redmine.db.requests;

import at.study.redmine.db.connection.PostgresConnection;
import at.study.redmine.model.Project;

import java.util.List;
import java.util.Map;

public class ProjectRequests implements Create<Project>, Read<Project>, Update<Project>, Delete<Project> {
    @Override
    public void create(Project project) {
        String query = "INSERT INTO public.projects\n" +
                "(id, name, description, homepage, is_public," +
                "parent_id, created_on, updated_on, identifier, " +
                "status, lft, rgt, inherit_members, " +
                "default_version_id, default_assigned_to_id)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING *;\n";
        List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query,
                project.getName(),
                project.getDescription(),
                project.getHomepage(),
                project.getIsPublic(),
                project.getParentID(),
                project.getCreatedOn(),
                project.getUpdatedOn(),
                project.getIdentifier(),
                Integer.parseInt(project.getStatus().description),
                project.getLft(),
                project.getRgt(),
                project.getInheritMembers(),
                project.getDefault_version_id(),
                project.getDefault_assigned_to_id()
        );

        Integer id = (Integer) result.get(0).get("id");
        project.setId(id);
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM public.projects\n" +
                "WHERE id= ?;\n";
        PostgresConnection.INSTANCE.executeUpdate(query, id);
    }

    @Override
    public Project read(Integer id) {
        throw new IllegalStateException("Метод не готов");
    }

    @Override
    public void update(Integer id, Project project) {
        String query = "UPDATE public.projects\n" +
                "SET name = ?, description = ?, homepage = ?, is_public = ?, " +
                "parent_id = ?, created_on = ?, updated_on = ?, identifier = ?, " +
                "status = ?, lft = ?, rgt = ?, inherit_members = ?, " +
                "default_version_id = ?, default_assigned_to_id = ?\n" +
                "WHERE id= ?;\n";

        PostgresConnection.INSTANCE.executeUpdate(query,
                project.getName(),
                project.getDescription(),
                project.getHomepage(),
                project.getIsPublic(),
                project.getParentID(),
                project.getCreatedOn(),
                project.getUpdatedOn(),
                project.getIdentifier(),
                Integer.parseInt(project.getStatus().description),
                project.getLft(),
                project.getRgt(),
                project.getInheritMembers(),
                project.getDefault_version_id(),
                project.getDefault_assigned_to_id(),
                id
        );
    }
}
