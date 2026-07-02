package Hemisphere;

/**
 * A hemisphere classification derived from a latitude value.
 * Non-negative latitudes are classified as "Northern"; negative
 * latitudes are classified as "Southern".
 */
public class HemisphereByLatitude extends Hemisphere {

    /**
     * Creates a HemisphereByLatitude, classifying it based on the
     * given latitude.
     *
     * @param latitude the latitude to classify, in degrees
     *                 (values >= 0 are classified as Northern)
     */
    public HemisphereByLatitude(double latitude) {
        if (latitude >= 0) {
            this.name = "Northern";
        } else {
            this.name = "Southern";
        }
    }
}