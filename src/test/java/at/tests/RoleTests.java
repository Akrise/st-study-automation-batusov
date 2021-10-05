package at.tests;

import at.study.redmine.model.Role;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RoleTests {

    @Test
    public void createRoleTest(){
        Role role = new Role();
        role.create();
        System.out.println(role.getId());
        Assert.assertNotNull(role.getId());
    }
}
