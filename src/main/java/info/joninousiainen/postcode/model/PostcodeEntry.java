package info.joninousiainen.postcode.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PostcodeEntry {
    public static enum StreetNumbering {
        ODD, EVEN, UNKNOWN
    }

    private String postcode;
    private String postOfficeFi;
    private String postOfficeSv;
    private String streetNameFi;
    private String streetNameSv;
    private StreetNumbering streetNumbering;
    private String streetNumberMin;
    private String streetNumberMax;
    private String municipalityCode;
    private String municipalityFi;
    private String municipalitySv;

    public PostcodeEntry(String postcode, String postOfficeFi, String postOfficeSv, String streetNameFi, String streetNameSv, StreetNumbering streetNumbering, String streetNumberMin, String streetNumberMax, String municipalityCode, String municipalityFi, String municipalitySv) {
        this.postcode = postcode;
        this.postOfficeFi = postOfficeFi;
        this.postOfficeSv = postOfficeSv;
        this.streetNameFi = streetNameFi;
        this.streetNameSv = streetNameSv;
        this.streetNumbering = streetNumbering;
        this.streetNumberMin = streetNumberMin;
        this.streetNumberMax = streetNumberMax;
        this.municipalityCode = municipalityCode;
        this.municipalityFi = municipalityFi;
        this.municipalitySv = municipalitySv;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPostOfficeFi() {
        return postOfficeFi;
    }

    public String getPostOfficeSv() {
        return postOfficeSv;
    }

    public String getStreetNameFi() {
        return streetNameFi;
    }

    public String getStreetNameSv() {
        return streetNameSv;
    }

    public StreetNumbering getStreetNumbering() {
        return streetNumbering;
    }

    public String getStreetNumberMin() {
        return streetNumberMin;
    }

    public String getStreetNumberMax() {
        return streetNumberMax;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public String getMunicipalityFi() {
        return municipalityFi;
    }

    public String getMunicipalitySv() {
        return municipalitySv;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
