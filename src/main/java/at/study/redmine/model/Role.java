package at.study.redmine.model;

import at.study.redmine.db.requests.RoleRequests;
import at.study.redmine.model.role.IssuesVisibility;
import at.study.redmine.model.role.Permissions;
import at.study.redmine.model.role.UsersVisibility;
import at.study.redmine.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
public class Role extends CreatableEntity implements Creatable<Role>, Readable<Role>, Updatable<Role>, Deletable<Role> {

    private String name = "BATRole" + StringUtils.randomEnglishString(6);
    private Integer position;
    private Boolean assignable = true;
    private Integer builtIn = 0;
    private Set<Permissions> permissions = new HashSet<Permissions>() {{
        add(Permissions.VIEW_ISSUES);
        add(Permissions.EDIT_ISSUES);
        add(Permissions.VIEW_MESSAGES);
    }};
    private IssuesVisibility issuesVisibility = IssuesVisibility.DEFAULT;
    private UsersVisibility usersVisibility = UsersVisibility.ALL;
    private String timeEntriesVisibility = "all";
    private Boolean allRolesManaged = true;
    private String settings = "--- !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "permissions_all_trackers: !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "  view_issues: '1'\n" +
            "  add_issues: '1'\n" +
            "  edit_issues: '1'\n" +
            "  add_issue_notes: '1'\n" +
            "  delete_issues: '1'\n" +
            "permissions_tracker_ids: !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "  view_issues: []\n" +
            "  add_issues: []\n" +
            "  edit_issues: []\n" +
            "  add_issue_notes: []\n" +
            "  delete_issues: []\n";

    @Override
    public Role create() {
        new RoleRequests().create(this);
        return this;
    }

    @Override
    public void delete() {
        //TODO
        throw new IllegalStateException("Метод не реализован.");
    }

    @Override
    public Role read() {
        throw new IllegalStateException("Метод не реализован.");
        //TODO
    }

    @Override
    public void update(Role entity) {
        throw new IllegalStateException("Метод не реализован.");
        //TODO
    }
}
