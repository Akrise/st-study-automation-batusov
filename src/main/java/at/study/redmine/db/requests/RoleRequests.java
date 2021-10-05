package at.study.redmine.db.requests;

import at.study.redmine.db.connection.PostgresConnection;
import at.study.redmine.model.Role;

import java.util.List;
import java.util.Map;

public class RoleRequests implements Create<Role>, Read<Role>, Update<Role>, Delete<Role> {
    @Override
    public void create(Role role) {
        String query = "INSERT INTO public.roles\n" +
                "(id, name, position, assignable, " +
                "builtin, permissions, issues_visibility, users_visibility, " +
                "time_entries_visibility, all_roles_managed, settings)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING *;\n";
         List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query,
                role.getName(),
                role.getPosition(),
                role.getAssignable(),
                role.getBuiltIn(),
                role.getPermissions().toString(),
                role.getIssuesVisibility().name(),
                role.getUsersVisibility().name(),
                role.getTimeEntriesVisibility(),
                role.getAllRolesManaged(),
                role.getSettings()
                );
        Integer id = (Integer) result.get(0).get("id");
        role.setId(id);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Role read(Integer id) {
        return null;
    }

    @Override
    public void update(Integer id, Role entity) {

    }
}
