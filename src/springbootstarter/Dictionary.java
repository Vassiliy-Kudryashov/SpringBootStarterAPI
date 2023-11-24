package springbootstarter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary {
    static final Map<String, String> descriptionDictionary = new HashMap<>();
    static final EnumMap<StandardProjectParameter, String> defaults = new EnumMap<>(StandardProjectParameter.class);
    static final Map<String, String> dependencyCompatibility = new LinkedHashMap<>();

    public static String getDescription(String key) {
        return descriptionDictionary.get(key);
    }

    public static Set<String> getDependencyKeys() {
        return Collections.unmodifiableSet(dependencyCompatibility.keySet());
    }

    public static String getRawCompatibility(StandardProjectDependency dependency) {
        return dependencyCompatibility.get(dependency.getId());
    }
}
