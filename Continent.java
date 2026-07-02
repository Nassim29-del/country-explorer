package WorldMap;

/**
 * Represents a continent, identified by name and total land area.
 */
public class Continent {

    /** The continent's name. Protected per the class diagram. */
    protected String name;

    /** The continent's total area, in square kilometers. */
    private long area;

    /**
     * Creates a Continent with the given name.
     *
     * @param name the continent's name
     */
    public Continent(String name) {
        this.name = name;
    }

    /**
     * Returns the continent's name.
     *
     * @return the continent's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the continent's name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the continent's area in square kilometers.
     *
     * @return the area
     */
    public long getArea() {
        return area;
    }

    /**
     * Sets the continent's area.
     *
     * @param area the area in square kilometers; must not be negative
     * @throws Exception if area is negative
     */
    public void setArea(long area) throws Exception {
        if (area < 0) {
            throw new Exception("Area cannot be negative");
        }
        this.area = area;
    }

    @Override
    public String toString() {
        return name + " - Area: " + String.format("%,d", area) + " km2";
    }
}