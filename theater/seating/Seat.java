package theater.seating;

import theater.seating.SeatType;

public class Seat{
    private final String id;
    private boolean hasGift;
    private final SeatType seatType;
    private boolean isOccupied;

    public Seat(String id, boolean hasGift, SeatType seatType) {
        this.id = id;
        this.hasGift = hasGift;
        this.seatType = seatType;
        this.isOccupied = false;
    }

    public String getId() {
        return this.id;
    }

    public boolean getHasGift() {
        return this.hasGift;
    }
    public void setHasGift(boolean gift) {
        this.hasGift = gift;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }

    public boolean getIsOccupied() {
        return this.isOccupied;
    }
    public void setIsOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}