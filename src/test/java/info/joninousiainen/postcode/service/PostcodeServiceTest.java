package info.joninousiainen.postcode.service;

import info.joninousiainen.postcode.PostcodeApp;
import info.joninousiainen.postcode.services.PostcodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PostcodeApp.class)
public class PostcodeServiceTest {
    @Autowired
    PostcodeService service;

    @Test
    public void testGetTotalUniqueStreetNames() {
        assertEquals(99302, service.getTotalUniqueStreetNames());
    }

    @Test
    public void testGetMatchingStreetNames() {
        Map<String, Set<String>> results = service.getMatchingStreetNamesByCity("leirikaar");

        Set<String> streetNamesInEspoo = results.get("Espoo");
        assertEquals(1, streetNamesInEspoo.size());
        assertEquals("Leirikaari", streetNamesInEspoo.iterator().next());
    }

    @Test
    public void testGetStreetNamesByCity() {
        Map<String, Set<String>> result = service.getStreetNamesByCity();

        assertNotNull(result);
        assertTrue(result.containsKey("Espoo"));
        assertEquals(3683, result.get("Espoo").size());
    }

    @Test
    public void testGetStreetNameCounts() {
        Map<String, Integer> result = service.getStreetNameCounts();
        assertEquals(Integer.valueOf(2), result.get("Leirikaari"));
        assertEquals(Integer.valueOf(94), result.get("Kauppakatu"));
    }
}
