package app;

import domain.Movie;

public class BookingResult {
  private static final char NEW_LINE = '\n';
  private final int ONE = 1;

  private final int movieId;
  private final int scheduleId;
  private final int personnel;
  private final Movie movie;


  public BookingResult(int movieId, int scheduleId, int personnel, Movie movie) {
    this.movieId = movieId;
    this.scheduleId = scheduleId;
    this.personnel = personnel;
    this.movie = movie;
  }

  public String toString() {
    return movie.toString(scheduleId - ONE) + "예약 인원 : " + personnel + NEW_LINE;
  }
}
