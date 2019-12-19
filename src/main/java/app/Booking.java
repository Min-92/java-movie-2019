package app;

import domain.Movie;
import domain.MovieRepository;
import domain.PlaySchedule;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Booking {

  private List<Movie> movies;
  private HashMap<Integer, Integer> idMap = new HashMap<>();

  private final int ZERO = 0;
  private final int ONE = 1;

  public Booking() {
    movies = MovieRepository.getMovies();

    for (int i = 0; i < movies.size(); i++) {
      idMap.put(movies.get(i).getId(), i);
    }

  }

  public void start() {
    showMovieList();
    int movieId = getBookingMovieId();

    showMovieSchedule(movieId);
    int scheduleId = getBookingScheduleId(movieId);

  }


  private void showMovieSchedule(int movieId) {
    OutputView.printSchedule(movies.get(idMap.get(movieId)));
  }

  private void validateMovieId(int inputId) throws Exception {
    if (!idMap.containsKey(inputId)) {
      throw new Exception("영화 ID가 존재하지 않습니다.");
    }
  }

  private void isStarted(LocalDateTime startTime) throws Exception {
    if(startTime.isBefore(LocalDateTime.now())){
      throw new Exception("영화가 이미 시작했습니다.");
    }
  }

  private void validateScheduleId(List<PlaySchedule> schedules, int scheduleId) throws Exception {
    if(scheduleId > schedules.size() || scheduleId <= ZERO){
      throw new Exception("스케쥴 ID가 올바르지 않습니다.");
    }
  }

  private void hasNoCapacity(int capacity) throws Exception {
    if(capacity <= 0){
      throw new Exception("예약 가능 인원이 없습니다.");
    }
  }

  private void validateScheduleId(int movieId, int scheduleId) throws Exception {
    List<PlaySchedule> schedules = movies.get(idMap.get(movieId)).getPlaySchedules();
    validateScheduleId(schedules, scheduleId);

    PlaySchedule schedule = schedules.get(scheduleId-ONE);

    hasNoCapacity(schedule.getCapacity());
    isStarted(schedule.getStartDateTime());
  }

  private int getBookingScheduleId(int movieId) {
    try {
      int scheduleId = InputView.inputScheduleId();
      validateScheduleId(movieId, scheduleId);
      return scheduleId;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return getBookingScheduleId(movieId);
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
