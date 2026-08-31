import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ToStringTest {

    /** 3 seats per row, 2 tier 1 rows + 3 tier 2 rows; seats with (r+c) odd are sold. */
    private Theater createTheater1() {
        Theater nt = new Theater(3, 2, 3);
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 3; c++) {
                if ((r + c) % 2 != 0)
                    nt.assignSeat(r, c);
            }
        }
        return nt;
    }

    /** 4 seats per row, 4 tier 1 rows, no tier 2 rows; seats with (r+c) even are sold. */
    private Theater createTheater2() {
        Theater nt = new Theater(4, 4, 0);
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if ((r + c) % 2 == 0)
                    nt.assignSeat(r, c);
            }
        }
        return nt;
    }

    @DisplayName("toString: 3x(2+3) checkerboard -> five lines, each ending in \\n")
    @Test
    void toString_Test01() {
        String expected = "";
        expected += "1 O X O\n";
        expected += "1 X O X\n";
        expected += "2 O X O\n";
        expected += "2 X O X\n";
        expected += "2 O X O\n";
        assertEquals(expected, createTheater1().toString(),
                "tier, then space + O/X per seat, then \\n after EVERY row including the last");
    }

    @DisplayName("toString: 4x(4+0) all tier 1, opposite checkerboard")
    @Test
    void toString_Test02() {
        String expected = "";
        expected += "1 X O X O\n";
        expected += "1 O X O X\n";
        expected += "1 X O X O\n";
        expected += "1 O X O X\n";
        assertEquals(expected, createTheater2().toString(),
                "no tier 2 rows: every line starts with 1");
    }

    @DisplayName("toString: one seat, one row, nothing sold -> \"1 O\\n\"")
    @Test
    void toString_Test03() {
        Theater tr = new Theater(1, 1, 0);
        assertEquals("1 O\n", tr.toString(), "smallest theater: tier, space, O, newline");
    }

    @DisplayName("toString: Theater(2,1,1) with seat (1,0) sold -> \"1 O O\\n2 X O\\n\" (the READ FIRST example)")
    @Test
    void toString_Test04() {
        Theater tr = new Theater(2, 1, 1);
        tr.assignSeat(1, 0);
        assertEquals("1 O O\n2 X O\n", tr.toString(), "matches the example in Theater.java");
    }

    @DisplayName("toString: result ends with \\n and does not start with one (the trailing-newline rule)")
    @Test
    void toString_Test05() {
        String s = createTheater1().toString();
        assertTrue(s.length() > 0, "toString should not be empty");
        assertTrue(s.endsWith("\n"), "the LAST row must also end with \\n");
        assertFalse(s.startsWith("\n"), "no blank line before the first row");
        assertFalse(s.endsWith("\n\n"), "exactly one \\n after the last row, not two");
    }

    @DisplayName("toString: every seat sold in a 3x(1+2) theater -> all X")
    @Test
    void toString_Test06() {
        Theater tr = new Theater(3, 1, 2);
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                tr.assignSeat(r, c);
        assertEquals("1 X X X\n2 X X X\n2 X X X\n", tr.toString(), "sold seats print as X");
    }
}
