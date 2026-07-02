package WorldMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Driver class that reads Continent, Ocean, Language, and Country data
 * from CSV files, builds the corresponding object graph, and prints
 * each country's details.
 *
 * <p>Note: functionally identical to {@code Hemisphere.RunHemisphere},
 * since hemisphere classification is now derived automatically inside
 * {@link Country}. Consider keeping only one of the two as the
 * project's entry point.</p>
 */
public class WorldMapRun {

    private static List<Continent> continents = new ArrayList<>();
    private static List<Ocean> oceans = new ArrayList<>();
    private static List<Language> languages = new ArrayList<>();
    private static List<Country> countries = new ArrayList<>();

    /**
     * Application entry point. Loads all CSV data and prints each
     * country's details.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        try {
            readContinents("Data/continent.csv");
            readOceans("Data/ocean.csv");
            readLanguages("Data/language.csv");
            readCountries("Data/country.csv");

            System.out.println("=== Countries Information ===\n");
            for (Country country : countries) {
                System.out.println(country);
                System.out.println("----------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads continent data from the given CSV file and populates
     * the {@code continents} list.
     *
     * @param filename the path to continent.csv
     * @throws Exception if the file cannot be read or parsed
     */
    private static void readContinents(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",", 2);
            String name = data[0];
            String areaStr = data[1].replace("\"", "").replace(",", "");

            Continent continent = new Continent(name);
            continent.setArea(Long.parseLong(areaStr));
            continents.add(continent);
        }

        br.close();
    }

    /**
     * Reads ocean data from the given CSV file and populates the
     * {@code oceans} list.
     *
     * @param filename the path to ocean.csv
     * @throws Exception if the file cannot be read or parsed
     */
    private static void readOceans(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",", 2);
            String name = data[0];
            String areaStr = data[1].replace("\"", "").replace(",", "");

            Ocean ocean = new Ocean(name);
            ocean.setArea(Long.parseLong(areaStr));
            oceans.add(ocean);
        }

        br.close();
    }

    /**
     * Reads language data from the given CSV file and populates the
     * {@code languages} list.
     *
     * @param filename the path to language.csv
     * @throws Exception if the file cannot be read or parsed
     */
    private static void readLanguages(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",", 2);
            String name = data[0];
            String speakersStr = data[1].replace("\"", "").replace(",", "");

            Language language = new Language(name);
            double value = Double.parseDouble(speakersStr);
            language.setNumberOfSpeakers((long) value);
            languages.add(language);
        }

        br.close();
    }

    /**
     * Reads country data from the given CSV file, resolving each
     * country's continent, oceans, and languages against the
     * already-loaded lists, and populates the {@code countries}
     * list.
     *
     * @param filename the path to country.csv
     * @throws Exception if the file cannot be read or parsed
     */
    private static void readCountries(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            // Split on commas that are outside quoted sections.
            String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            if (data.length >= 9) {
                Country country = new Country(data[0]);

                String landStr = data[1].replace("\"", "").replace(",", "");
                country.setLandArea(Long.parseLong(landStr));

                Continent continent = findContinentByName(data[2]);
                if (continent != null) {
                    country.setContinent(continent);
                }

                String coastStr = data[3].replace("\"", "").replace(",", "");
                country.setCoast(Long.parseLong(coastStr));

                String oceanData = data[4].replace("\"", "");
                if (!oceanData.equals("-") && !oceanData.isEmpty()) {
                    String[] oceanNames = oceanData.split("\\s*;?\\s*|\\s*,\\s*");
                    for (String oceanName : oceanNames) {
                        Ocean ocean = findOceanByName(oceanName);
                        if (ocean != null) {
                            country.addOcean(ocean);
                        }
                    }
                }

                String languageData = data[5].replace("\"", "");
                if (!languageData.isEmpty()) {
                    String[] languageNames = languageData.split("\\s*,\\s*");
                    for (String languageName : languageNames) {
                        Language language = findLanguageByName(languageName);
                        if (language != null) {
                            country.addLanguage(language);
                        }
                    }
                }

                String popStr = data[6].replace("\"", "").replace(",", "");
                country.setPopulation(Long.parseLong(popStr));

                country.setLatitude(Float.parseFloat(data[7]));
                country.setLongitude(Float.parseFloat(data[8]));

                countries.add(country);
            }
        }

        br.close();
    }

    /**
     * Finds a previously-loaded continent by name (case-insensitive).
     *
     * @param name the continent name to search for
     * @return the matching Continent, or null if not found
     */
    private static Continent findContinentByName(String name) {
        for (Continent continent : continents) {
            if (continent.getName().equalsIgnoreCase(name)) {
                return continent;
            }
        }
        return null;
    }

    /**
     * Finds a previously-loaded ocean by name (case-insensitive).
     *
     * @param name the ocean name to search for
     * @return the matching Ocean, or null if not found
     */
    private static Ocean findOceanByName(String name) {
        for (Ocean ocean : oceans) {
            if (ocean.getName().equalsIgnoreCase(name)) {
                return ocean;
            }
        }
        return null;
    }

    /**
     * Finds a previously-loaded language by name (case-insensitive).
     *
     * @param name the language name to search for
     * @return the matching Language, or null if not found
     */
    private static Language findLanguageByName(String name) {
        for (Language language : languages) {
            if (language.getName().equalsIgnoreCase(name)) {
                return language;
            }
        }
        return null;
    }
}