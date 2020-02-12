package domain;

import java.util.ArrayList;

public class Movie
{
    private String title;
    private ArrayList<MovieScreening> screenings;

    public Movie(String title)
    {
        this.title = title;

        screenings = new ArrayList<MovieScreening>();
    }

    public String getTitle()
    {
        return title;
    }

    public void addScreening(MovieScreening screening)
    {
        screenings.add(screening);
    }
}
