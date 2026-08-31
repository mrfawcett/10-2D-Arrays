# 2D Arrays: Theater Seating

**Unit 3 — 2D Arrays** · Pairs with lecture 3.2 2D Arrays · CED 4.11–4.13

A theater is a grid: rows of seats, the same number of seats in every row.
The front rows are **tier 1**, the back rows are **tier 2**, and every seat is
either available (`O`) or sold (`X`). You will build the grid, move a person
from one seat to another under a tier rule, and print the whole chart. It is
exam-shaped: construct a 2D array and fill every cell, index it with
`[row][col]`, and traverse it row by row. The `Theater`/`Seat` problem is
adapted from the College Board's 2020 AP CSA Practice Exam free-response
section.

## What you are given
| File | Status | Purpose |
|---|---|---|
| `src/main/java/Theater.java` | **you complete this** | constructor, `reassignSeat`, `toString` |
| `src/main/java/Seat.java` | provided | one seat: `isAvailable()`, `getTier()`, `setAvailability(boolean)` |
| `src/test/java/*Test.java` | provided | the autograder's tests — read them |

`Theater` also has two **provided** helpers the tests use: `assignSeat(row, col)`
marks a seat as sold, and `getTheaterSeats()` returns the 2D array. Do not
change them.

## What to write

| Method | Points | What it does |
|---|---|---|
| `Theater(int seatsPerRow, int tier1Rows, int tier2Rows)` | 30 | build the grid; every seat available; front rows tier 1, back rows tier 2 |
| `reassignSeat(int fromRow, int fromCol, int toRow, int toCol)` | 35 | move a person if the destination is free and of equal or higher tier number |
| `toString()` | 35 | the seating chart, one row per line, each line ending in `\n` |

### The constructor
`theaterSeats` must end up with `tier1Rows + tier2Rows` rows and `seatsPerRow`
columns. Row 0 is the front. Rows `0` through `tier1Rows - 1` are tier 1; the
rest are tier 2. Every seat starts available. `tier2Rows` may be `0`.

```java
theaterSeats = new Seat[tier1Rows + tier2Rows][seatsPerRow];
```
gives you a grid of `null`s. That is not a theater yet. You must visit every
cell with a nested loop and store `new Seat(true, tier)` in it. The trap is
deciding the tier: it depends on the **row** index only. A row `r` is tier 1
when `r < tier1Rows`, otherwise tier 2.

### `reassignSeat(fromRow, fromCol, toRow, toCol)`
Assume the source seat is occupied. The move is allowed when **both** are true:
- the destination seat is available, and
- the destination's tier number is `>=` the source's tier number.

So a tier 1 person may move to any free tier 1 or tier 2 seat; a tier 2
person may only move to a free tier 2 seat. If allowed: set the source
available, set the destination unavailable, return `true`. If not allowed:
change **nothing** and return `false`.

Pull the two `Seat` objects into local variables first:
```java
Seat from = theaterSeats[fromRow][fromCol];
Seat to   = theaterSeats[toRow][toCol];
```
Then it is one `if` with two conditions. The common mistake is flipping only
one of the two seats — the tests check both.

### `toString()`
Return one `String` for the whole chart. For each row, in order from row 0:
- the row's tier number (`1` or `2`) — take it from any seat in that row;
- then for each seat in the row, a **space** followed by `O` (available) or
  `X` (sold);
- then a newline `"\n"`.

**Every row ends with `"\n"`, including the last one.** There is no extra text
and no blank line. Build it with a `String` variable and `+=`.

Example — 5 seats per row, two tier 1 rows and three tier 2 rows:
```
1 O X X O X
1 X X O X X
2 X O O X O
2 O X X O O
2 X X X X O
```
is the String `"1 O X X O X\n1 X X O X X\n2 X O O X O\n2 O X X O O\n2 X X X X O\n"`.

## Examples

Start with `new Theater(5, 2, 3)` and sell every seat where `(row + col)` is
odd. The chart is:
```
1 O X O X O
1 X O X O X
2 O X O X O
2 X O X O X
2 O X O X O
```
| Call | Result | Why |
|---|---|---|
| `reassignSeat(0, 1, 0, 0)` | `true` | tier 1 to a free tier 1 seat; now `(0,1)` is `O`, `(0,0)` is `X` |
| `reassignSeat(1, 2, 2, 4)` | `true` | tier 1 to a free tier 2 seat |
| `reassignSeat(4, 3, 0, 4)` | `false` | tier 2 may not move to tier 1; nothing changes |
| `reassignSeat(0, 1, 0, 3)` | `false` | destination is sold |
| `new Theater(2, 1, 1)` then `assignSeat(1, 0)`, `toString()` | `"1 O O\n2 X O\n"` | two rows, each ends in `\n` |
| `new Theater(1, 1, 0).toString()` | `"1 O\n"` | smallest theater |

## Running the tests
`mvn test` runs everything; `mvn test -Dtest=<ClassName>` runs one rubric line.

| Test class | Rubric line | Points |
|---|---|---|
| `TheaterTest` | Theater constructor | 30 |
| `ReassignSeatTest` | `reassignSeat` | 35 |
| `ToStringTest` | `toString` | 35 |

The autograder awards a rubric line only when every test in that class passes.

## Suggested order
1. The constructor. Nothing else can be tested until the grid exists — every
   other test will throw `NullPointerException` until this works. Run
   `TheaterTest`; the "no nulls left" test tells you whether your nested loop
   reached every cell.
2. `toString`. It is the easiest way to *see* your theater. Print a
   `new Theater(3, 1, 2)` and compare with the expected format by eye, then
   run `ToStringTest`. If only the trailing-newline test fails, you are adding
   `"\n"` between rows instead of after each row.
3. `reassignSeat`. Write the two-part condition, then the two
   `setAvailability` calls. Run `ReassignSeatTest`; the "destination becomes
   unavailable" test catches the half-done version.

## Rules of the road
- AP Java subset only: 2D arrays, nested `for` loops, `if`, `String`
  concatenation. No `StringBuilder`, no `Arrays.*`, no `String.join`, no
  `String.repeat`, no streams.
- Do not change method headers or provided code (`Seat`, `assignSeat`,
  `getTheaterSeats`).
- Do not touch `src/test`, `pom.xml`, `grading.json`, or `.github`. The
  autograder checks that they are byte-identical to the template before it
  runs a single test; if they differ it stops and awards nothing, and the
  change shows up in the roster.
- `toString` must return the String — do not print it. The tests compare the
  returned value character by character.
