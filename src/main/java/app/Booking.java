package app;

import domain.Movie;
import domain.MovieRepository;
import java.util.HashMap;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Booking {

  private List<Movie> movies;

  public Booking() {
    movies = MovieRepository.getMovies();
  }

  public void start() {
    showMovieList();

    int movieId = getBookingMovieId();

  }


  private void validateMovieId(int inputId) throws Exception {
    HashMap<Integer, Boolean> idMap = new HashMap<>();

    for (Movie movie : movies) {
      idMap.put(movie.getId(), true);
    }

    if (!idMap.containsKey(inputId)) {
      throw new Exception("영화 ID가 존재하지 않습니다.");
    }

  }

  private int getBookingMovieId() {
    try {
      int id = InputView.inputMovieId();
      validateMovieId(id);
      return id;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return getBookingMovieId();
    }
  }

  private void showMovieList() {
    OutputView.printMovies(movies);
  }


}
