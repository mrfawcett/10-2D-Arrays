import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReassignSeatTest {

    /** 5 seats per row, 2 tier 1 rows, 3 tier 2 rows. Seats where (r+c) is
     *  odd are sold. toString() of this theater would be:
     *  1 O X O X O
     *  1 X O X O X
     *  2 O X O X O
     *  2 X O X O X
     *  2 O X O X O
     */
    private Theater createTheater() {
        Theater nt = new Theater(5, 2, 3);
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if ((r + c) % 2 != 0)
                    nt.assignSeat(r, c);
            }
        }
        return nt;
    }

    private boolean avail(Theater t, int r, int c) {
        return t.getTheaterSeats()[r][c].isAvailable();
    }

    @DisplayName("reassignSeat: tier 1 (0,1) -> free tier 1 (0,0) is allowed; source becomes available")
    @Test
    void reassignSeat_Test01() {
        Theater tr = createTheater();
        assertEquals(true, tr.reassignSeat(0, 1, 0, 0), "same tier, destination free -> true");
        assertTrue(avail(tr, 0, 1), "source seat (0,1) should now be available");
    }

    @DisplayName("reassignSeat: tier 2 (4,3) -> free tier 1 (0,4) is NOT allowed; source stays occupied")
    @Test
    void reassignSeat_Test02() {
        Theater tr = createTheater();
        assertEquals(false, tr.reassignSeat(4, 3, 0, 4), "moving from tier 2 to tier 1 -> false");
        assertFalse(avail(tr, 4, 3), "source seat (4,3) must stay occupied when the move fails");
    }

    @DisplayName("reassignSeat: tier 1 (1,2) -> free tier 2 (2,4) is allowed (moving back is fine)")
    @Test
    void reassignSeat_Test03() {
        Theater tr = createTheater();
        assertEquals(true, tr.reassignSeat(1, 2, 2, 4), "tier 1 to tier 2 -> true");
        assertTrue(avail(tr, 1, 2), "source seat (1,2) should now be available");
    }

    @DisplayName("reassignSeat: destination (0,3) already sold -> false")
    @Test
    void reassignSeat_Test04() {
        Theater tr = createTheater();
        assertEquals(false, tr.reassignSeat(0, 1, 0, 3), "destination is occupied -> false");
        assertFalse(avail(tr, 0, 1), "source seat (0,1) must stay occupied when the move fails");
    }

    @DisplayName("reassignSeat: tier 1 (1,0) -> occupied tier 2 (3,2) -> false (tier ok, seat taken)")
    @Test
    void reassignSeat_Test05() {
        Theater tr = createTheater();
        assertEquals(false, tr.reassignSeat(1, 0, 3, 2), "destination is occupied -> false even though tier allows it");
        assertFalse(avail(tr, 1, 0), "source seat (1,0) must stay occupied when the move fails");
    }

    @DisplayName("reassignSeat: tier 2 (4,3) -> free tier 2 (2,4) is allowed")
    @Test
    void reassignSeat_Test06() {
        Theater tr = createTheater();
        assertEquals(true, tr.reassignSeat(4, 3, 2, 4), "tier 2 to tier 2, destination free -> true");
        assertTrue(avail(tr, 4, 3), "source seat (4,3) should now be available");
    }

    @DisplayName("reassignSeat: after a successful move the DESTINATION becomes unavailable")
    @Test
    void reassignSeat_Test07() {
        Theater tr = createTheater();
        assertTrue(avail(tr, 0, 0), "sanity: (0,0) starts free");
        assertEquals(true, tr.reassignSeat(0, 1, 0, 0));
        assertFalse(avail(tr, 0, 0), "destination (0,0) should now be sold - did you call setAvailability(false) on it?");
        assertTrue(avail(tr, 0, 1), "source (0,1) should now be free");
    }

    @DisplayName("reassignSeat: after a failed move the destination is unchanged")
    @Test
    void reassignSeat_Test08() {
        Theater tr = createTheater();
        assertEquals(false, tr.reassignSeat(4, 3, 0, 4), "tier 2 -> tier 1 is refused");
        assertTrue(avail(tr, 0, 4), "destination (0,4) must still be available after a refused move");
        assertFalse(avail(tr, 4, 3), "source (4,3) must still be occupied after a refused move");
    }

    @DisplayName("reassignSeat: tier 2 (3,0) -> occupied tier 2 (3,2) -> false, both seats unchanged")
    @Test
    void reassignSeat_Test09() {
        Theater tr = createTheater();
        assertEquals(false, tr.reassignSeat(3, 0, 3, 2), "destination sold -> false");
        assertFalse(avail(tr, 3, 0), "source (3,0) unchanged");
        assertFalse(avail(tr, 3, 2), "destination (3,2) unchanged");
    }

    @DisplayName("reassignSeat: two moves in a row - the freed seat can be reused")
    @Test
    void reassignSeat_Test10() {
        Theater tr = createTheater();
        assertEquals(true, tr.reassignSeat(2, 1, 2, 0), "first move (2,1) -> (2,0) succeeds");
        assertEquals(true, tr.reassignSeat(2, 3, 2, 1), "second move into the seat freed by the first succeeds");
        assertTrue(avail(tr, 2, 3), "(2,3) is now free");
        assertFalse(avail(tr, 2, 1), "(2,1) is occupied again");
        assertFalse(avail(tr, 2, 0), "(2,0) is occupied");
    }
}
