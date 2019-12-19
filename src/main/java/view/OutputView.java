package view;

import app.BookingResult;
import domain.Movie;

import java.util.List;

public class OutputView {

  public static void printMovies(List<Movie> movies) {
    for (Movie movie : movies) {
      System.out.println(movie);
    }
  }

  public static void printSchedule(Movie movie) {
    System.out.println(movie);
  }

  public static void printBookingResult(BookingResult bookingResult) {
    System.out.println(bookingResult);
  }
}
