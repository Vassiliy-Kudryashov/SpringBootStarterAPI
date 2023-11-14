package springbootstarter;

import java.io.File;
import java.io.IOException;

public class ExampleStarter {
    public static void main(String[] args) throws IOException {
        SpringBootProjectModel model = SpringBootProjectModel.initFromWeb();
        model.setParameter(StandardProjectParameters.NAME, "TestProjectName");
        model.setParameter(StandardProjectParameters.APPLICATION_NAME, "TestApp");
        model.setParameter(StandardProjectParameters.GROUP_ID, "io.test.app");
        model.setType(ProjectType.GRADLE_KOTLIN);
        model.addDependency(StandardProjectDependency.Spring_Data_JDBC);
        model.addDependency(StandardProjectDependency.Spring_Data_JPA);
        model.addDependency(StandardProjectDependency.Spring_Web);
        model.addDependency(StandardProjectDependency.Spring_Boot_DevTools);
        model.downloadProjectFile(new File("starter.zip"));
    }
}
