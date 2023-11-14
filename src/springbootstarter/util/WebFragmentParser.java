package springbootstarter.util;

import springbootstarter.SpringBootProjectModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebFragmentParser {

    private static final String CATEGORY_PREFIX = "<li class=\"group-title\"><span>";
    private static final String CATEGORY_START = "<strong>";
    private static final String CATEGORY_MIDDLE = "</strong><span>";
    private static final String CATEGORY_END = "</span>";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("web.fragment.txt").toPath());
        Map<String, String> nameToCategory = new LinkedHashMap<>();
        Map<String, String> descriptionToName = new LinkedHashMap<>();
        String currentCategory = null;
        for (String line : lines) {
            if (line.startsWith(CATEGORY_PREFIX)) {
                currentCategory = line.substring(CATEGORY_PREFIX.length(), line.indexOf("<", CATEGORY_PREFIX.length() + 1));
                continue;
            }
            int pos = line.indexOf(CATEGORY_START);
            if (pos == -1 || currentCategory == null) {
                continue;
            }
            line = line.substring(pos + CATEGORY_START.length());
            pos = line.indexOf(CATEGORY_MIDDLE);
            if (pos == -1) {
                continue;
            }
            String name = line.substring(0, pos);

            line = line.substring(pos + CATEGORY_MIDDLE.length());
            pos = line.indexOf(CATEGORY_END);
            if (pos == -1) {
                continue;
            }
            String description = line.substring(0, pos);
            nameToCategory.put(name, currentCategory);
            descriptionToName.put(description, name);
        }
        SpringBootProjectModel model = SpringBootProjectModel.initFromWeb();
        Set<String> dependencyKeys = model.getDependencyKeys();
        for (String id : dependencyKeys) {
            String description = model.getDescription(id);
            String name = descriptionToName.get(description);
            String category = nameToCategory.get(name);
            if (name.endsWith(" ")) name = name.substring(0, name.length() - 1);
            String codeName = name
                    .replace(" ", "_")
                    .replace("(", "")
                    .replace(")","")
                    .replace("'s", "")
                    .replace("+", "Plus");
            category = category
                    .replace(" ", "_")
                    .replace("/", "__");
            System.out.println(codeName.replace(" ", "_") + "(\""+id+"\", \"" + name + "\", \"" + description.replace("\"", "\\\"") + "\", " + category+"),");
        }
    }
}
