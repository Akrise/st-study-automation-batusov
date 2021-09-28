package at.study.redmine.model;

import at.study.redmine.model.role.IssuesVisibility;
import at.study.redmine.model.role.Permissions;
import at.study.redmine.model.role.UsersVisibility;
import at.study.redmine.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
public class Role extends CreatableEntity implements  Creatable<Role>{

    private String name = "AutoRole" + StringUtils.randomEnglishString(6);
    private int position;
    private boolean assignable = true;
    private int builtIn = 0;
    private Set<Permissions> permissions;
    private IssuesVisibility issuesVisibility = IssuesVisibility.DEFAULT;
    private UsersVisibility usersVisibility = UsersVisibility.ALL;
    private String timeEntriesVisibility = "all";
    private boolean allRolesManaged = true;
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
        return null;
    }
}
