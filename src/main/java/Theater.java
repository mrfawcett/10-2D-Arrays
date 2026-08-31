/** READ FIRST
 * A theater contains rows of seats with the same number of seats in each row.
 * Some rows contain tier 1 seats and the remaining rows contain tier 2 seats.
 * Tier 1 seats are in the front and tier 2 seats are in the back. All seats
 * in a row share the same tier.
 *
 * The Seat class (provided) represents one seat. Its boolean available is
 * false once a ticket for the seat has been sold; its int tier is 1 or 2.
 *
 * The Theater class stores its seats in a 2D array named theaterSeats.
 * Row 0 of theaterSeats is the row closest to the stage (the front).
 * theaterSeats[r][c] is the seat in row r, column c.
 *
 * Adapted from the College Board's 2020 AP Computer Science A Practice Exam
 * (free-response section).
 *
 * ---- Theater(int seatsPerRow, int tier1Rows, int tier2Rows) ----
 * Builds the 2D array with tier1Rows + tier2Rows rows and seatsPerRow columns.
 * Every seat starts available. Rows 0 .. tier1Rows-1 are tier 1; the rows after
 * that are tier 2. tier2Rows may be 0 (a theater with no back section).
 *
 * ---- reassignSeat(int fromRow, int fromCol, int toRow, int toCol) ----
 * Attempts to move a person from the source ("from") seat to the destination
 * ("to") seat. The move is allowed only if the destination seat is available
 * AND its tier number is the same as or greater than the source seat's tier.
 * A person in a tier 1 seat may move to another tier 1 seat or to a tier 2
 * seat; a person in a tier 2 seat may only move to another tier 2 seat.
 * If the move is allowed: the source seat becomes available, the destination
 * seat becomes unavailable, and the method returns true. Otherwise nothing
 * changes and the method returns false. Assume the source seat is occupied.
 *
 * ---- toString() ----
 * Returns the seating chart as one String, one row per line. Each line starts
 * with the row's tier (1 or 2), then for every seat a space followed by
 * 'O' if the seat is available or 'X' if it is not. EVERY row, including the
 * last one, ends with a newline character "\n". There is no other text.
 *
 * A theater with 5 seats per row, two tier 1 rows and three tier 2 rows
 * might print as:
 * 1 O X X O X
 * 1 X X O X X
 * 2 X O O X O
 * 2 O X X O O
 * 2 X X X X O
 * As a Java literal that String is
 * "1 O X X O X\n1 X X O X X\n2 X O O X O\n2 O X X O O\n2 X X X X O\n"
 */
public class Theater {
    private Seat[][] theaterSeats;

    /** COMPLETE THIS CONSTRUCTOR
     * Precondition: seatsPerRow > 0; tier1Rows > 0; tier2Rows >= 0
     * Creates theaterSeats with tier1Rows + tier2Rows rows and seatsPerRow
     * columns. Every element is a new Seat that is available; rows
     * 0 .. tier1Rows-1 get tier 1, the remaining rows get tier 2.
     * Example: new Theater(3, 2, 1) has 3 rows of 3 seats; rows 0 and 1 are
     *          tier 1, row 2 is tier 2, all nine seats available.
     * Hint: new Seat[rows][cols] gives you a grid of nulls - you still have to
     *       fill every cell with new Seat(true, tier) in a nested loop.
     */
    public Theater(int seatsPerRow, int tier1Rows, int tier2Rows) {
        // Insert your code below

    }

    /** PROVIDED - do not change
     * Helper for the tests: marks the seat at (row, col) as sold. Does nothing
     * if row or col is outside the theater.
     * @param row the selected row
     * @param col the selected column
     */
    public void assignSeat(int row, int col) {
        if (row < theaterSeats.length && col < theaterSeats[0].length)
            theaterSeats[row][col].setAvailability(false);
    }

    /** PROVIDED - do not change
     * Helper for the tests.
     * @return the theaterSeats array
     */
    public Seat[][] getTheaterSeats() {
        return theaterSeats;
    }

    /** COMPLETE THIS METHOD
     * Precondition: fromRow, fromCol, toRow and toCol are valid positions in
     *               the theater, and the seat at (fromRow, fromCol) is not
     *               available (someone is sitting there).
     * Moves the person from the source seat to the destination seat if the
     * destination is available and its tier is >= the source seat's tier.
     * Returns true and updates both seats if the move happened; otherwise
     * changes nothing and returns false.
     * Example: source tier 1 at (0,1), destination tier 2 at (2,4) available
     *          -> true; (0,1) is now 'O' and (2,4) is now 'X'.
     *          source tier 2, destination tier 1 -> false, nothing changes.
     * Hint: store the two Seat objects in local variables first; the
     *       condition is one if with two parts.
     */
    public boolean reassignSeat(int fromRow, int fromCol, int toRow, int toCol) {
        // Insert your code below

        return false;
    }

    /** COMPLETE THIS METHOD
     * Returns the seating chart as described in the READ FIRST comment: for
     * each row, the tier number, then " O" or " X" for each seat, then "\n".
     * Every row ends with "\n", including the last one.
     * Example: a 2-seat theater with one tier 1 row and one tier 2 row, seat
     *          (1,0) sold, returns "1 O O\n2 X O\n".
     * Hint: build the result with a String variable and +=. The tier comes
     *       from any seat in the row, e.g. theaterSeats[r][0].getTier().
     */
    public String toString() {
        // Insert your code below

        return "";
    }
}
