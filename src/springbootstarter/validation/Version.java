package springbootstarter.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Version implements Comparable<Version> {
    private static final List<String> PRIORITIES = Arrays.asList("SNAPSHOT", "M1", "M2", "RC1", "RC2", "RELEASE");
    private final List<String> words;

    public Version(String source) {
        StringTokenizer tokenizer = new StringTokenizer(source.trim(), "\\.()-");
        words = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            words.add(tokenizer.nextToken().trim());
        }
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            try {
                if (i < 3) {
                    Integer.parseInt(word);
                } else {
                    if (!PRIORITIES.contains(word)) {
                        throw new IllegalArgumentException("Incorrect suffix part [" + word + "] of version : " + source);
                    }
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Incorrect numeric part [" + word + "] of version : " + source);
            }
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(".");
        words.forEach(joiner::add);
        return joiner.toString();
    }

    @Override
    public int compareTo(Version o) {
        ArrayList<String> copy1 = new ArrayList<>(words);
        ArrayList<String> copy2 = new ArrayList<>(o.words);
        if (copy1.size() == 3) copy1.add("RELEASE");
        if (copy2.size() == 3) copy2.add("RELEASE");

        int result;
        for (int i = 0; i < copy1.size(); i++) {
            if (i < 3) {
                result = Integer.compare(Integer.parseInt(copy1.get(i)), Integer.parseInt(copy2.get(i)));
                if (result != 0) {
                    return result;
                }
            } else {
                return Integer.compare(PRIORITIES.indexOf(copy1.get(i)), PRIORITIES.indexOf(copy2.get(i)));
            }
        }
        return 0;
    }
}
