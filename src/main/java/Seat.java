/** PROVIDED - do not change
 * One seat in the theater. available is false once a ticket for the seat has
 * been sold. tier is 1 (front section) or 2 (back section).
 */
public class Seat {
    private boolean available;
    private int tier;

    public Seat(boolean isAvail, int tierNum) {
        available = isAvail;
        tier = tierNum;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getTier() {
        return tier;
    }

    public void setAvailability(boolean isAvail) {
        available = isAvail;
    }
}
