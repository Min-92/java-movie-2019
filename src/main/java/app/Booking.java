package app;

import domain.Movie;
import domain.MovieRepository;
import java.util.List;
import view.OutputView;

public class Booking {

  private List<Movie> movies;

  public Booking() {
    movies = MovieRepository.getMovies();
  }

  public void start() {
    showMovieList();
  }

  private void showMovieList() {
    OutputView.printMovies(movies);
  }
}
