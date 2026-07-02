package Hemisphere;

/**
 * A hemisphere classification derived from a longitude value.
 * Non-negative longitudes are classified as "Eastern"; negative
 * longitudes are classified as "Western".
 */
public class HemisphereByLongitude extends Hemisphere {

    /**
     * Creates a HemisphereByLongitude, classifying it based on the
     * given longitude.
     *
     * @param longitude the longitude to classify, in degrees
     *                  (values >= 0 are classified as Eastern)
     */
    public HemisphereByLongitude(double longitude) {
        if (longitude >= 0) {
            this.name = "Eastern";
        } else {
            this.name = "Western";
        }
    }
}