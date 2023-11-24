package springbootstarter.util;

import springbootstarter.SpringBootProjectModel;
import springbootstarter.StandardProjectDependency;
import springbootstarter.StandardProjectParameter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class IOUtil {
    public static final String IO_LOCAL_KEY = "springboot.io.local";

    public static List<String> readLinesFromSpringIO() throws IOException {
        if (Boolean.getBoolean(IO_LOCAL_KEY)) {
            try {
                URL resource = IOUtil.class.getResource("../spring.io.txt");
                if (resource == null) {
                    throw new IOException("Cannot find resource file spring.io.txt");
                }
                return Files.lines(new File(resource.toURI()).toPath()).collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new IOException("Cannot read resource file", e);
            }
        }
        HttpURLConnection connection = (HttpURLConnection) new URL("https://start.spring.io").openConnection();
        connection.setRequestProperty("ACCEPT", "text/plain");
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Response code: " + responseCode);
        }

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } finally {
            connection.disconnect();
        }
        return lines;
    }

    public static void downloadProjectFile(SpringBootProjectModel model, File target) throws IOException {
        Map<String, String> arguments = new LinkedHashMap<>();
        for (Map.Entry<StandardProjectParameter, String> e : model.getParameters().entrySet()) {
            arguments.put(e.getKey().toString(), URLEncoder.encode(e.getValue(), "UTF-8"));
        }
        Set<StandardProjectDependency> dependencies = model.getDependencies();
        if (!dependencies.isEmpty()) {
            StringJoiner dependencyJoiner = new StringJoiner(",");
            dependencies.forEach(dependency -> dependencyJoiner.add(dependency.getId()));
            arguments.put(StandardProjectParameter.DEPENDENCIES.toString(), dependencyJoiner.toString());
        }
        StringJoiner joiner = new StringJoiner("&");
        for (Map.Entry<String, String> entry : arguments.entrySet())
            joiner.add(entry.getKey() + "=" + entry.getValue());

        HttpURLConnection connection = (HttpURLConnection) new URL("https://start.spring.io/starter.zip").openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        byte[] bytes = joiner.toString().getBytes();
        connection.setFixedLengthStreamingMode(bytes.length);
        connection.getOutputStream().write(bytes);

        byte[] buffer = new byte[16384];
        try (InputStream inputStream = connection.getInputStream(); FileOutputStream fos = new FileOutputStream(target)) {
            for (int len = inputStream.read(buffer); len > 0; len = inputStream.read(buffer)) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
        } finally {
            connection.disconnect();
        }
    }

}
