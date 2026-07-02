package WorldMap;

import java.util.ArrayList;
import java.util.List;

import Hemisphere.Hemisphere;
import Hemisphere.HemisphereByLatitude;
import Hemisphere.HemisphereByLongitude;

/**
 * Represents a country, along with its continent, oceans, languages,
 * and the hemispheres it belongs to (derived from its latitude and
 * longitude).
 */
public class Country {

    private String name;
    private long landArea;
    private long coast;
    private long population;
    private float latitude;
    private float longitude;

    private Continent continent;
    private List<Ocean> oceans = new ArrayList<>();
    private List<Language> languages = new ArrayList<>();

    /** The hemisphere (Northern/Southern) derived from latitude. */
    private Hemisphere latitudeHemisphere;

    /** The hemisphere (Eastern/Western) derived from longitude. */
    private Hemisphere longitudeHemisphere;

    /**
     * Creates a Country with the given name.
     *
     * @param name the country's name
     */
    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLandArea() {
        return landArea;
    }

    /**
     * Sets the country's land area.
     *
     * @param landArea the land area in square kilometers; must not be negative
     * @throws Exception if landArea is negative
     */
    public void setLandArea(long landArea) throws Exception {
        if (landArea < 0) {
            throw new Exception("Land area cannot be negative");
        }
        this.landArea = landArea;
    }

    public long getCoast() {
        return coast;
    }

    /**
     * Sets the length of the country's coastline.
     *
     * @param coast the coastline length in kilometers; must not be negative
     * @throws Exception if coast is negative
     */
    public void setCoast(long coast) throws Exception {
        if (coast < 0) {
            throw new Exception("Coast cannot be negative");
        }
        this.coast = coast;
    }

    public long getPopulation() {
        return population;
    }

    /**
     * Sets the country's population.
     *
     * @param population the population; must not be negative
     * @throws Exception if population is negative
     */
    public void setPopulation(long population) throws Exception {
        if (population < 0) {
            throw new Exception("Population cannot be negative");
        }
        this.population = population;
    }

    public float getLatitude() {
        return latitude;
    }

    /**
     * Sets the country's latitude and automatically derives its
     * latitude-based hemisphere (Northern/Southern).
     *
     * @param latitude the latitude in degrees, between -90 and 90
     * @throws Exception if latitude is out of range
     */
    public void setLatitude(float latitude) throws Exception {
        if (latitude < -90 || latitude > 90) {
            throw new Exception("Latitude must be between -90 and 90");
        }
        this.latitude = latitude;
        this.latitudeHemisphere = new HemisphereByLatitude(latitude);
    }

    public float getLongitude() {
        return longitude;
    }

    /**
     * Sets the country's longitude and automatically derives its
     * longitude-based hemisphere (Eastern/Western).
     *
     * @param longitude the longitude in degrees, between -180 and 180
     * @throws Exception if longitude is out of range
     */
    public void setLongitude(float longitude) throws Exception {
        if (longitude < -180 || longitude > 180) {
            throw new Exception("Longitude must be between -180 and 180");
        }
        this.longitude = longitude;
        this.longitudeHemisphere = new HemisphereByLongitude(longitude);
    }

    /**
     * Returns the hemisphere derived from this country's latitude
     * (Northern or Southern).
     *
     * @return the latitude-based hemisphere, or null if latitude has not been set
     */
    public Hemisphere getLatitudeHemisphere() {
        return latitudeHemisphere;
    }

    /**
     * Returns the hemisphere derived from this country's longitude
     * (Eastern or Western).
     *
     * @return the longitude-based hemisphere, or null if longitude has not been set
     */
    public Hemisphere getLongitudeHemisphere() {
        return longitudeHemisphere;
    }

    public Continent getContinent() {
        return continent;
    }

    /**
     * Sets the country's continent.
     *
     * @param continent the continent; must not be null
     * @throws Exception if continent is null
     */
    public void setContinent(Continent continent) throws Exception {
        if (continent == null) {
            throw new Exception("Invalid continent");
        }
        this.continent = continent;
    }

    public List<Ocean> getOceans() {
        return oceans;
    }

    /**
     * Adds an ocean bordering this country.
     *
     * @param ocean the ocean to add; must not be null
     * @throws Exception if ocean is null
     */
    public void addOcean(Ocean ocean) throws Exception {
        if (ocean == null) {
            throw new Exception("Invalid ocean");
        }
        this.oceans.add(ocean);
    }

    public List<Language> getLanguages() {
        return languages;
    }

    /**
     * Adds a language spoken in this country.
     *
     * @param language the language to add; must not be null
     * @throws Exception if language is null
     */
    public void addLanguage(Language language) throws Exception {
        if (language == null) {
            throw new Exception("Invalid language");
        }
        this.languages.add(language);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Country: ").append(name).append(":\n");
        sb.append("  - Area: ").append(String.format("%,d", landArea)).append(" km2\n");

        if (continent != null) {
            sb.append("  - Continent: ").append(continent).append("\n");
        }

        sb.append("  - Coast: ").append(String.format("%,d", coast)).append(" km\n");

        sb.append("  - Ocean(s): ");
        if (!oceans.isEmpty()) {
            for (int i = 0; i < oceans.size(); i++) {
                Ocean ocean = oceans.get(i);
                sb.append(ocean.getName())
                  .append(" (Area: ").append(String.format("%,d", ocean.getArea())).append(" km2)");
                if (i < oceans.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            sb.append("None");
        }
        sb.append("\n");

        sb.append("  - Language(s): ");
        if (!languages.isEmpty()) {
            for (int i = 0; i < languages.size(); i++) {
                Language language = languages.get(i);
                sb.append(language.getName())
                  .append(" (Speakers: ").append(String.format("%,d", language.getNumberOfSpeakers())).append(")");
                if (i < languages.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("\n");

        sb.append("  - Population: ").append(String.format("%,d", population)).append("\n");
        sb.append("  - Latitude: ").append(latitude);
        if (latitudeHemisphere != null) {
            sb.append(" (").append(latitudeHemisphere).append(")");
        }
        sb.append("\n");

        sb.append("  - Longitude: ").append(longitude);
        if (longitudeHemisphere != null) {
            sb.append(" (").append(longitudeHemisphere).append(")");
        }
        sb.append("\n");

        return sb.toString();
    }
}