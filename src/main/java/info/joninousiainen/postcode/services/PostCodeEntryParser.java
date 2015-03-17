package info.joninousiainen.postcode.services;

import info.joninousiainen.postcode.model.PostcodeEntry;
import org.apache.commons.lang3.StringUtils;

public class PostCodeEntryParser {
    private static PostCodeEntryParser instance = new PostCodeEntryParser();

    public static PostcodeEntry parse(String line) {
        return instance.extractAddressLine(line);
    }

    private PostcodeEntry extractAddressLine(String fileLine) {
        String postcode = getPostcode(fileLine);
        String postOfficeFi = getPostOfficeFi(fileLine);
        String postOfficeSv = getPostOfficeSv(fileLine);
        String streetNameFi = getStreetNameFi(fileLine);
        String streetNameSv = getStreetNameSv(fileLine);
        PostcodeEntry.StreetNumbering streetNumbering = getStreetNumbering(fileLine);
        String streetNumberMin = getStreetNumberMin(fileLine);
        String streetNumberMax = getStreetNumberMax(fileLine);
        String municipalityCode = getMunicipalityCode(fileLine);
        String municipalityNameFi = getMunicipalityNameFi(fileLine);
        String municipalityNameSv =  getMunicipalityNameSv(fileLine);
        return new PostcodeEntry(postcode, postOfficeFi, postOfficeSv, streetNameFi, streetNameSv, streetNumbering, streetNumberMin, streetNumberMax, municipalityCode, municipalityNameFi, municipalityNameSv);
    }

    private String getPostcode(String line) {
        return getTrimmedToNullSubstring(line, 13, 18);
    }

    private String getTrimmedToNullSubstring(String line, int start, int end) {
        return StringUtils.trimToEmpty(StringUtils.substring(line, start, end));
    }

    private String getPostOfficeFi(String line) {
        return getTrimmedToNullSubstring(line, 18, 48);
    }

    private String getPostOfficeSv(String line) {
        return getTrimmedToNullSubstring(line, 48, 78);
    }

    private String getStreetNameFi(String line) {
        return getTrimmedToNullSubstring(line, 102, 132);
    }

    private String getStreetNameSv(String line) {
        return getTrimmedToNullSubstring(line, 132, 162);
    }

    private PostcodeEntry.StreetNumbering getStreetNumbering(String line) {
        String value = getTrimmedToNullSubstring(line, 186, 187);
        if ("1".equals(value)) {
            return PostcodeEntry.StreetNumbering.ODD;
        }
        else if ("2".equals(value)) {
            return PostcodeEntry.StreetNumbering.EVEN;
        }
        else {
            return PostcodeEntry.StreetNumbering.UNKNOWN;
        }
    }

    private String getStreetNumberMin(String line) {
        StringBuilder value = new StringBuilder();
        value.append(getTrimmedToNullSubstring(line, 187, 192));
        value.append(getTrimmedToNullSubstring(line, 192, 193));
        value.append(getTrimmedToNullSubstring(line, 193, 194));
        value.append(getTrimmedToNullSubstring(line, 194, 199));
        return value.toString();
    }

    private String getStreetNumberMax(String line) {
        StringBuilder value = new StringBuilder();
        value.append(getTrimmedToNullSubstring(line, 200, 205));
        value.append(getTrimmedToNullSubstring(line, 205, 206));
        value.append(getTrimmedToNullSubstring(line, 206, 207));
        value.append(getTrimmedToNullSubstring(line, 207, 212));
        return value.toString();
    }

    private String getMunicipalityCode(String line) {
        return getTrimmedToNullSubstring(line, 213, 216);
    }

    private String getMunicipalityNameFi(String line) {
        return getTrimmedToNullSubstring(line, 216, 236);
    }

    private String getMunicipalityNameSv(String line) {
        return getTrimmedToNullSubstring(line, 236, 256);
    }
}
