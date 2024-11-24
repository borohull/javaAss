package theater;
import theater.seating.*;
public class TheaterSeating{
    private Seat[][] seats;
    private int giftsTotal;

    public TheaterSeating(int rowCount, int colCount) {
        if (rowCount < 1 || colCount < 1) {
            throw new IllegalArgumentException("Invalid rows and column. Must be positive");
        }
        this.seats = new Seat[rowCount][colCount];
        initSeating(rowCount, colCount);
    }

    public Seat[][] getSeats(){
        return this.seats;
    }

    private void initSeating(int rowCount, int colCount) {
        int giftCount = 0;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                boolean hasGift = (row + col) % 2 != 0;
                SeatType seatType;
                if (row == 0 || row == rowCount - 1 || col == 0 || col == colCount - 1) {
                    seatType = SeatType.OT;
                } else if (col == ((colCount-1) / 2)) {
                    seatType = SeatType.MT;
                } else {
                    seatType = SeatType.IT;
                }
                seats[row][col] = new Seat("R" + row + "C" + col, hasGift, seatType);
                if (hasGift) {
                    giftCount++;
                }
            }
        }
        this.giftsTotal = giftCount;
    }

    public int getAmountOfGifts() {
        return giftsTotal;
    }

    public int totalTakenGifts() {
        int takenGifts = 0;
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (seat.getIsOccupied() && seat.getHasGift()) {
                    takenGifts++;
                }
            }
        }
        return giftsTotal - takenGifts;
    }

    public void decreaseGifts() {
        int count = 0;
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (seat.getIsOccupied() && seat.getHasGift()) {
                    count++;
                }
            }
        }
        giftsTotal -= count;
    }


    public Seat bookSeat() {
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (seat.getIsOccupied() == false) {
                    seat.setIsOccupied(true);
                    return seat;
                }
            }
        }
        return null;
    }

    public Seat bookSeat(int row, int col) {
        if (seats[row][col].getIsOccupied()) {
            throw new IllegalArgumentException("Invalid. Seat booked already");
        }
        seats[row][col].setIsOccupied(true);
        return seats[row][col];
    }

    public Seat bookTailoredEmptySeat(SeatType type, boolean left) {
        for (int row = 0; row < seats.length; row++) {
            for (int col = left ? 0 : seats[row].length - 1; left ? col < seats[row].length : col >= 0; col += left ? 1 : -1) {
                Seat seat = seats[row][col];
                if (!seat.getIsOccupied() && seat.getSeatType() == type) {
                    seat.setIsOccupied(true);
                    return seat;
                }
            }
        }
        return null;
    }

    @Override   
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                sb.append(seat.getIsOccupied() ? "[B]" : "[A]").append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();

    }
}
