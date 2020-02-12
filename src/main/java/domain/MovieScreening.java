package domain;

import java.time.LocalDateTime;

public class MovieScreening
{
    private Movie movie;

    // LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 20);
    private LocalDateTime dateAndTime;
    private double pricePerSeat;

    public MovieScreening(Movie movie, LocalDateTime dateAndTime, double pricePerSeat)
    {
        this.movie = movie;
        movie.addScreening(this);

        this.dateAndTime = dateAndTime;
        this.pricePerSeat = pricePerSeat;
    }

    public double getPricePerSeat()
    {
        return pricePerSeat;
    }

    public LocalDateTime getDateAndTime() {
        return this.dateAndTime;
    }

    @Override
    public String toString() {
        return movie.getTitle() + " - " + dateAndTime;
    }
}
