package info.joninousiainen.postcode.services;

import info.joninousiainen.postcode.model.PostcodeEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PostcodeService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private List<PostcodeEntry> entries;

    public List<PostcodeEntry> getAllPostCodeEntries() {
        if (entries == null) {
            entries = new LinkedList<>();
            getLinesStream()
                    .filter(fileLine -> fileLine.length() == 256)
                    .forEach(line -> entries.add(PostCodeEntryParser.parse(line)));
        }
        return Collections.unmodifiableList(entries);
    }

    private Stream<String> getLinesStream() {
        try {
            File file = new File(getClass().getResource("/BAF/BAF_20150307.dat").getFile());
            return Files.lines(file.toPath(), Charset.forName("ISO-8859-1"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void initPostCodeEntries() throws Exception {
        getAllPostCodeEntries(); // Calling this initializes the list.
    }
}
