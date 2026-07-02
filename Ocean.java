package WorldMap;

/**
 * Represents an ocean, identified by name and total area.
 */
public class Ocean {

    private String name;
    private long area;

    /**
     * Creates an Ocean with the given name.
     *
     * @param name the ocean's name
     */
    public Ocean(String name) {
        this.name = name;
    }

    /**
     * Returns the ocean's name.
     *
     * @return the ocean's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the ocean's name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the ocean's area in square kilometers.
     *
     * @return the area
     */
    public long getArea() {
        return area;
    }

    /**
     * Sets the ocean's area.
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
        return name + " (Area: " + String.format("%,d", area) + " km2)";
    }
}