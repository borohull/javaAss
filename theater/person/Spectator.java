package theater.person;

import theater.TheaterSeating;
import theater.seating.Seat;
import theater.seating.SeatType;

public class Spectator {
    private Seat seat;
    private final String name;

    public Spectator(String name) {
        this.name = name;
        this.seat = null;
    }

    public Seat getSeat() {
        return this.seat;
    }

    public String getName() {
        return this.name;
    }

    public boolean takeGift() {
        if (seat != null && seat.getHasGift()) {
            seat.setHasGift(false);
            return true;
        }
        return false;
    }

    public void bookAnySeat(TheaterSeating t) {
        this.seat = t.bookSeat();
    }

    public void bookSpecificSeat(TheaterSeating t, int row, int col) {
        this.seat = t.bookSeat(row, col);
    } 

    public void bookTailoredSeat(TheaterSeating t) {
        Seat s = null;
        if (name.length() < 3) {
            s = t.bookTailoredEmptySeat(SeatType.OT, true);
        } else if(name.length() % 2 == 0) {
            s = t.bookTailoredEmptySeat(SeatType.IT, true);
        } else {
            s = t.bookTailoredEmptySeat(SeatType.MT, true);
        }
        this.seat = s;
    }

}