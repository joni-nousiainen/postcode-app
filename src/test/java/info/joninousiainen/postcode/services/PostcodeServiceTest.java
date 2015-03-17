package info.joninousiainen.postcode.services;

import info.joninousiainen.postcode.PostcodeApp;
import info.joninousiainen.postcode.model.PostcodeEntry;
import info.joninousiainen.postcode.services.PostcodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PostcodeApp.class)
public class PostcodeServiceTest {
    @Autowired
    PostcodeService service;

    @Test
    public void testGetAllAddressLines() {
        List<PostcodeEntry> results = service.getAllPostCodeEntries();
        assertNotNull(results);

        long count = results.stream()
                .filter(line -> "Leirikaari".equals(line.getStreetNameFi()))
                .filter(line -> PostcodeEntry.StreetNumbering.EVEN.equals(line.getStreetNumbering()))
                .filter(line -> "02600".equals(line.getPostcode()))
                .filter(line -> "ESPOO".equals(line.getPostOfficeFi()))
                .count();
        assertEquals(1l, count);
    }
}
