package springbootstarter.util;

import springbootstarter.Dictionary;
import springbootstarter.SpringBootProjectModel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebFragmentParser {

    private static final String CATEGORY_PREFIX = " class=\"group-title\"><span>";
    private static final String CATEGORY_START = "<strong>";
    private static final String CATEGORY_MIDDLE = "</strong><span>";
    private static final String CATEGORY_END = "</span>";

    private WebFragmentParser() {
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        parseResourceFile().forEach(System.out::println);
    }

    public static List<String> parseResourceFile() throws IOException, URISyntaxException {
        // 1. Go to start.spring.io with browser,
        // 2. Click on "ADD DEPENDENCIES..." button
        // 3. Right-click menu -> "Inspect"
        // 4. Find <ul> with full dependencies list
        // 4. Right-click menu -> Copy > Copy OuterHTML
        // 5. Save HTML from clipboard to web.fragment.txt
        // 6. Run this parser to get valuable part of StandardProjectDependency source code printed in console
        // 7. Copy printed source code to StandardProjectDependency if you need
        URL resource = WebFragmentParser.class.getResource("../web.fragment.txt");
        if (resource == null) {
            System.err.println("Cannot find resource 'springbootstarter/web.fragment.txt'");
        }
        List<String> lines = Arrays.asList(new String(Files.readAllBytes(new File(resource.toURI()).toPath()), StandardCharsets.UTF_8).split("<li"));
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
            String name = line.substring(0, pos).trim();

            line = line.substring(pos + CATEGORY_MIDDLE.length());
            pos = line.indexOf(CATEGORY_END);
            if (pos == -1) {
                continue;
            }
            String description = line.substring(0, pos);
            nameToCategory.put(name, currentCategory);
            descriptionToName.put(description, name);
        }
        SpringBootProjectModel.initFromWeb();
        Set<String> dependencyKeys = Dictionary.getDependencyKeys();
        List<String> result = new ArrayList<>();
        for (String id : dependencyKeys) {
            String description = Dictionary.getDescription(id);
            String name = descriptionToName.get(description);
            // Outdated name in local snapshots
            if (name == null) {
                continue;
            }
            String category = nameToCategory.get(name);
            String codeName = name
                    .replace(" ", "_")
                    .replace("(", "")
                    .replace(")","")
                    .replace("'s", "")
                    .replace("+", "Plus");
            category = category
                    .replace(" ", "_")
                    .replace("/", "__");
            result.add(codeName.replace(" ", "_") + "(\""+id+"\", \"" + name + "\", \"" + description.replace("\"", "\\\"") + "\", " + category+"),");
        }
        return result;
    }
}
