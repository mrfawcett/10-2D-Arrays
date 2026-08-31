import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TheaterTest {

    @DisplayName("Theater(5, 3, 2): 5 rows of 5 seats; row 0 is tier 1, row 3 is tier 2")
    @Test
    void theater_Test01() {
        int col = 5, t1 = 3, t2 = 2;
        Theater tr = new Theater(col, t1, t2);
        Seat[][] seats = tr.getTheaterSeats();
        assertNotNull(seats, "the constructor must create theaterSeats");
        assertEquals(t1 + t2, seats.length, "number of rows should be tier1Rows + tier2Rows");
        assertEquals(col, seats[0].length, "number of seats per row");
        assertEquals(1, seats[0][0].getTier(), "row 0 is tier 1");
        assertEquals(2, seats[t1][0].getTier(), "row 3 (first row after the tier 1 rows) is tier 2");
    }

    @DisplayName("Theater(3, 4, 0): tier2Rows may be 0 - 4 rows, all tier 1")
    @Test
    void theater_Test02() {
        int col = 3, t1 = 4, t2 = 0;
        Theater tr = new Theater(col, t1, t2);
        Seat[][] seats = tr.getTheaterSeats();
        assertEquals(t1 + t2, seats.length, "4 rows when tier2Rows is 0");
        assertEquals(col, seats[0].length, "3 seats per row");
        assertEquals(1, seats[0][0].getTier(), "row 0 is tier 1");
        assertEquals(1, seats[t1 - 1][0].getTier(), "last row (3) is still tier 1 when there are no tier 2 rows");
    }

    @DisplayName("Theater(2, 1, 6): one tier 1 row then six tier 2 rows")
    @Test
    void theater_Test03() {
        int col = 2, t1 = 1, t2 = 6;
        Theater tr = new Theater(col, t1, t2);
        Seat[][] seats = tr.getTheaterSeats();
        assertEquals(t1 + t2, seats.length, "7 rows");
        assertEquals(col, seats[0].length, "2 seats per row");
        assertEquals(1, seats[0][0].getTier(), "row 0 is tier 1");
        assertEquals(2, seats[t1][0].getTier(), "row 1 is tier 2");
        assertEquals(2, seats[t1 + t2 - 1][col - 1].getTier(), "last seat of the last row is tier 2");
    }

    @DisplayName("Theater(10, 4, 1): wide rows - 10 seats each")
    @Test
    void theater_Test04() {
        int col = 10, t1 = 4, t2 = 1;
        Theater tr = new Theater(col, t1, t2);
        Seat[][] seats = tr.getTheaterSeats();
        assertEquals(t1 + t2, seats.length, "5 rows");
        assertEquals(col, seats[0].length, "10 seats per row");
        assertEquals(1, seats[0][0].getTier(), "row 0 is tier 1");
        assertEquals(2, seats[t1][0].getTier(), "row 4 is tier 2");
        assertEquals(1, seats[t1 - 1][col - 1].getTier(), "row 3, last seat, is the last tier 1 seat");
    }

    @DisplayName("Theater(4, 2, 2): every seat exists, is available, and has the right tier (no nulls left)")
    @Test
    void theater_Test05() {
        Theater tr = new Theater(4, 2, 2);
        Seat[][] seats = tr.getTheaterSeats();
        for (int r = 0; r < 4; r++) {
            assertEquals(4, seats[r].length, "row " + r + " should have 4 seats");
            for (int c = 0; c < 4; c++) {
                assertNotNull(seats[r][c], "seat (" + r + "," + c + ") was never created - still null");
                assertTrue(seats[r][c].isAvailable(), "seat (" + r + "," + c + ") should start available");
                int expectedTier = 2;
                if (r < 2) {
                    expectedTier = 1;
                }
                assertEquals(expectedTier, seats[r][c].getTier(), "wrong tier at (" + r + "," + c + ")");
            }
        }
    }

    @DisplayName("Theater(1, 1, 1): smallest theater - 2 rows of 1 seat, tiers 1 then 2")
    @Test
    void theater_Test06() {
        Theater tr = new Theater(1, 1, 1);
        Seat[][] seats = tr.getTheaterSeats();
        assertEquals(2, seats.length, "2 rows");
        assertEquals(1, seats[0].length, "1 seat per row");
        assertEquals(1, seats[0][0].getTier(), "row 0 tier 1");
        assertEquals(2, seats[1][0].getTier(), "row 1 tier 2");
        assertTrue(seats[1][0].isAvailable(), "seat (1,0) should start available");
    }
}
