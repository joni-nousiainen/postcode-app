package info.joninousiainen.postcode.services;

import info.joninousiainen.postcode.model.PostcodeEntry;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostCodeEntryParserTest {
    @Test
    public void testAllFields_LeirikaariEven() {
        PostcodeEntry result = PostCodeEntryParser.parse("KATUN2015030702600ESPOO                         ESBO                                                  Leirikaari                    Lägerbågen                                            2    2           26        049Espoo               Esbo                ");
        assertEquals("02600", result.getPostcode());
        assertEquals("ESPOO", result.getPostOfficeFi());
        assertEquals("ESBO", result.getPostOfficeSv());
        assertEquals("Lägerbågen", result.getStreetNameSv());
        assertEquals(PostcodeEntry.StreetNumbering.EVEN, result.getStreetNumbering());
        assertEquals("2", result.getStreetNumberMin());
        assertEquals("26", result.getStreetNumberMax());
        assertEquals("049", result.getMunicipalityCode());
        assertEquals("Espoo", result.getMunicipalityFi());
        assertEquals("Esbo", result.getMunicipalitySv());
    }

    @Test
    public void testStreetNumberMinAndMaxWithSeparatorChar() {
        PostcodeEntry result = PostCodeEntryParser.parse("KATUN2015030791800TYRNÄVÄ                       TYRNÄVÄ                                               Simontaival                                                                         2    2 -    4     6 -    8 859Tyrnävä             Tyrnävä             ");
        assertEquals("2-4", result.getStreetNumberMin());
        assertEquals("6-8", result.getStreetNumberMax());
    }

    @Test
    public void testStreetNumberMinAndMaxWithSpecifierChar() {
        PostcodeEntry result = PostCodeEntryParser.parse("KATUN2015030791240TANNILA                       TANNILA                                               Viinamäentie                                                                        2   34a          34b       564Oulu                Uleåborg            ");
        assertEquals("34a", result.getStreetNumberMin());
        assertEquals("34b", result.getStreetNumberMax());
    }
}