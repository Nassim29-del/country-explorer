package WorldMap;

/**
 * Represents a language, along with the number of people who speak it.
 */
public class Language {

    private String name;
    private long numberOfSpeakers;

    /**
     * Creates a Language with the given name.
     *
     * @param name the language's name
     */
    public Language(String name) {
        this.name = name;
    }

    /**
     * Returns the language's name.
     *
     * @return the language's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of people who speak this language.
     *
     * @return the number of speakers
     */
    public long getNumberOfSpeakers() {
        return numberOfSpeakers;
    }

    /**
     * Sets the number of speakers of this language.
     *
     * @param numberOfSpeakers the number of speakers; must not be negative
     * @throws Exception if numberOfSpeakers is negative
     */
    public void setNumberOfSpeakers(long numberOfSpeakers) throws Exception {
        if (numberOfSpeakers < 0) {
            throw new Exception("Number of speakers cannot be negative");
        }
        this.numberOfSpeakers = numberOfSpeakers;
    }

    @Override
    public String toString() {
        return name + " (Speakers: " + String.format("%,d", numberOfSpeakers) + ")";
    }
}