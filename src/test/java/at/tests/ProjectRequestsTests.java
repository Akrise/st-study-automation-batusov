package at.tests;

import at.study.redmine.model.Project;
import org.testng.annotations.Test;

public class ProjectRequestsTests {

    @Test
    public void  createProjectTest(){
        Project project = new Project();
        project.create();
    }
}
