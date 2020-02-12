package domain;

import java.time.LocalDateTime;

public class MovieTicket
{
    private MovieScreening movieScreening;
    private boolean isPremiumTicket;

    private int seatRow;
    private int seatNr;

    public MovieTicket(
            MovieScreening movieScreening,
            boolean isPremiumTicket,
            int seatRow,
            int seatNr)
    {
        this.movieScreening = movieScreening;
        this.isPremiumTicket = isPremiumTicket;
        this.seatRow = seatRow;
        this.seatNr = seatNr;
    }

    public boolean isPremiumTicket()
    {
        return isPremiumTicket;
    }

    public double getPrice()
    {
        return movieScreening.getPricePerSeat();
    }

    public LocalDateTime getDate() {
        return movieScreening.getDateAndTime();
    }

    @Override
    public String toString() {
        return movieScreening.toString() + " - row " + seatRow + ", seat " + seatNr +
                (isPremiumTicket ? " (Premium)" : "");
    }
}
