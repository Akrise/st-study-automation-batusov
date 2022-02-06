package at.study.redmine.db.requests;

import at.study.redmine.db.connection.PostgresConnection;
import at.study.redmine.model.Project;
import at.study.redmine.model.Role;
import at.study.redmine.model.User;
import at.study.redmine.model.user.MailNotification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        createMemberRoles(project);
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

        if (project.getMembers().size() != 0)
            createMemberRoles(project);
    }

    private void createMemberRoles(Project project){
        if (project.getMembers().size() != 0) {
            Map<User, Set<Role>> members = project.getMembers();
            String membersQuery = "INSERT INTO public.members\n" +
                    "(id, user_id, project_id,  created_on, mail_notification)\n" +
                    "VALUES(DEFAULT, ?, ?, ?, ?) RETURNING *;\n";
            String memberRolesQuery = "INSERT INTO public.member_roles\n" +
                    "(id, member_id, role_id, inherited_from)\n" +
                    "VALUES(DEFAULT, ?, ?, ?) RETURNING *;";

            for (Map.Entry<User, Set<Role>> entry : members.entrySet()) {
                boolean mailNotifications = entry.getKey().getMailNotification() != MailNotification.NONE;
                List<Map<String, Object>> memberResult = PostgresConnection.INSTANCE.executeQuery(membersQuery,
                        entry.getKey().getId(),
                        project.getId(),
                        project.getCreatedOn(),
                        mailNotifications);
                Integer memberId = (Integer) memberResult.get(0).get("id");
                for (Role role : entry.getValue()) {
                    PostgresConnection.INSTANCE.executeQuery(memberRolesQuery,
                            memberId,
                            role.getId(),
                            null);
                }
            }
        }
    }
}
