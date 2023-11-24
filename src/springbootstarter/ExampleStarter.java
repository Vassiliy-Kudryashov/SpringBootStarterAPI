package springbootstarter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ExampleStarter {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        SpringBootProjectModel model = SpringBootProjectModel.initFromWeb()
            .setType(getRandomValue(ProjectType.values(), random))
            .setParameterValue(StandardProjectParameter.NAME, "TestProjectName")
            .setParameterValue(StandardProjectParameter.APPLICATION_NAME, "TestApp")
            .setParameterValue(StandardProjectParameter.GROUP_ID, "io.test.app");

        List<StandardProjectDependency> dependencies = new ArrayList<>(Arrays.asList(StandardProjectDependency.values()));
        Collections.shuffle(dependencies);
        dependencies = dependencies.subList(0, random.nextInt(dependencies.size()));
        dependencies.forEach(dependency -> model.addDependency(dependency));

        System.out.println(model.downloadProjectFile(new File("starter.zip")).getAbsolutePath() + " is ready");
    }

    private static <T> T getRandomValue(T[] array, Random random) {
        return array[random.nextInt(array.length)];
    }
}
