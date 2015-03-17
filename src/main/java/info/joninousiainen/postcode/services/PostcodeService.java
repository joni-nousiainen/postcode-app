package info.joninousiainen.postcode.services;

import info.joninousiainen.postcode.model.PostcodeEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PostcodeService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public List<PostcodeEntry> getAllPostCodeEntries() {
        List<PostcodeEntry> lines = new LinkedList<>();
        getLinesStream()
                .filter(fileLine -> fileLine.length() == 256)
                .forEach(line -> lines.add(PostCodeEntryParser.parse(line)));
        return lines;
    }

    private Stream<String> getLinesStream() {
        try {
            File file = new File(getClass().getResource("/BAF/BAF_20150307.dat").getFile());
            return Files.lines(file.toPath(), Charset.forName("ISO-8859-1"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
