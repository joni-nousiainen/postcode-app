package info.joninousiainen.postcode.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

@Service
public class PostcodeService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public int getTotalUniqueStreetNames() {
        Set<String> streetNames = new HashSet<>();
        getLinesStream().forEach(line -> streetNames.add(getFinnishStreetName(line)));
        return streetNames.size();
    }

    public Map<String, Set<String>> getStreetNamesByCity() {
        Map<String, Set<String>> map = new HashMap<>();
        getLinesStream().forEach(line -> addToMap(map, line));
        return map;
    }

    private Stream<String> getLinesStream() {
        try {
            File file = new File(getClass().getResource("/BAF/BAF_20150307.dat").getFile());
            return Files.lines(file.toPath(), Charset.forName("ISO-8859-1"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToMap(Map<String, Set<String>> map, String line) {
        String postOffice = getFinnishPostOffice(line);
        Set<String> set = map.get(postOffice);
        if(set == null) {
            set = new TreeSet<>();
            map.put(postOffice, set);
        }
        set.add(getFinnishStreetName(line));
    }

    private String getFinnishPostOffice(String line) {
        return line.substring(216, 236).trim();
    }

    private String getFinnishStreetName(String line) {
        return line.substring(102, 132).trim();
    }

    public Map<String, Integer> getStreetNameCounts() {
        Map<String, Integer> map = new HashMap<>();
        getLinesStream().forEach(line -> countLine(map, line));
        return map;
    }

    private void countLine(Map<String, Integer> map, String line) {
        String streetName = getFinnishStreetName(line);

        Integer count = map.get(streetName);
        if (count == null) {
            map.put(streetName, 1);
        }
        else {
            map.put(streetName, ++count);
        }
    }
}