package at.tests;

import at.study.redmine.model.Project;
import org.testng.annotations.Test;

public class ProjectRequestsTests {

    @Test
    public void createProjectTest() {
        Project project = new Project();
        project.create();
    }

    @Test
    public void updateAndDeleteTest() {
        Project project = new Project();
        project.create();
        System.out.println("Project ID:" + project.getId());
        System.out.println("First project name" + project.getName());
        Project newProject = new Project();
        newProject.setName("UpdatedProjectName");
        project.update(newProject);
        System.out.println("Updated project name" + newProject.getName());
        project.delete();
    }
}
