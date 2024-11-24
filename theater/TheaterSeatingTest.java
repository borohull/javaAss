    package theater;
    import org.junit.jupiter.api.*;
    import static org.junit.jupiter.api.Assertions.*;
    import theater.TheaterSeating;
    import theater.seating.*;
    public class TheaterSeatingTest {
        private TheaterSeating theater;

        @BeforeEach
        public void setUp() {
            theater = new TheaterSeating(4, 5);
        }

        @Test
        public void testInitialization() {
            assertEquals(4, theater.getSeats().length);
            assertEquals(5, theater.getSeats()[0].length);

            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    Seat seat = theater.getSeats()[row][col];
                    if (row == 0 || row == rowCount - 1 || col == 0 || col == colCount - 1) {
                        assertEquals(SeatType.OT, seat.getSeatType(), "Outer seat type mismatch at " + row + "," + col);
                    } else if (col == ((colCount-1) / 2)) {
                        assertEquals(SeatType.MT, seat.getSeatType(), "Middle seat type mismatch at " + row + "," + col);
                    } else {
                        assertEquals(SeatType.IT, seat.getSeatType(), "Inner seat type mismatch at " + row + "," + col);
                    }
                }
            }
        }

        @Test
        public void testGiftsInitialization(int rows, int cols, int expectedGifts) {
            TheaterSeating testTheater = new TheaterSeating(rows, cols);
            assertEquals(expectedGifts, testTheater.getAmountOfGifts(), "Gift count mismatch for " + rows + "x" + cols);
        }
        @Test
        public void testBookSeat() {
            Seat bookedSeat = theater.bookSeat();
            assertNotNull(bookedSeat);
            assertTrue(bookedSeat.isOccupied());
            assertThrows(IllegalArgumentException.class, () -> theater.bookSeat(bookedSeat.getRow(), bookedSeat.getCol()));
        }

        @Test
        public void testBookTailoredEmptySeat() {
            Seat tailoredSeat = theater.bookTailoredEmptySeat(SeatType.MT, true);
            assertNotNull(tailoredSeat, "Tailored middle seat not booked.");
            assertTrue(tailoredSeat.isOccupied(), "Tailored middle seat is not marked as occupied.");
            int middleColumn = (theater.getSeats()[0].length - 1) / 2;
            assertEquals(SeatType.MT, tailoredSeat.getSeatType(), "Booked seat is not of type MT.");
            assertEquals(middleColumn, tailoredSeat.getCol(), "Booked seat is not in the middle column.");
        }

        @Test
        public void testText() {
            String expectedOutput = """
                [A] [A] [A] [A]
                [A] [A] [A] [A]
                [A] [A] [A] [A]
                [A] [A] [A] [A]
                """;
            assertEquals(expectedOutput, theater.toString());
        }
    }