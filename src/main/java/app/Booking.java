package app;

import domain.Movie;
import domain.MovieRepository;
import domain.PlaySchedule;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import utils.DateTimeUtils;
import view.InputView;
import view.OutputView;

public class Booking {

  private final int ZERO = 0;
  private final int ONE = 1;
  private final int TWO = 2;

  private final List<Movie> movies;
  private HashMap<Integer, Integer> idMap = new HashMap<>();
  private ArrayList<BookingResult> bookingResults = new ArrayList<>();
  private LocalDateTime firstTime = DateTimeUtils.createDateTime("2019-01-01 00:00");


  public Booking() {
    movies = MovieRepository.getMovies();

    for (int i = 0; i < movies.size(); i++) {
      idMap.put(movies.get(i).getId(), i);
    }
  }

  public boolean start() {
    showMovieList();
    int movieId = getBookingMovieId();

    showMovieSchedule(movieId);
    int scheduleId = getBookingScheduleId(movieId);

    int personnel = getPersonnel(movieId, scheduleId);

    addBookingResult(movieId, scheduleId, personnel);

    return endBooking();
  }

  private boolean validateNextProcess(int nextProcess) throws Exception {
    if (nextProcess != ONE && nextProcess != TWO) {
      throw new Exception("1과 2만 입력해주세요.");
    }

    return nextProcess == TWO;
  }

  public ArrayList<BookingResult> finish(){
    System.out.println("## 예약 내역");
    for(int i = 0; i < bookingResults.size(); i++){
      OutputView.printBookingResult(bookingResults.get(i));
    }
    return bookingResults;
  }

  private boolean endBooking() {
    try {
      int nextProcess = InputView.inputNextProcess();
      return validateNextProcess(nextProcess);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return endBooking();
    }
  }


  private void addBookingResult(int movieId, int scheduleId, int personnel) {
    bookingResults.add(new BookingResult(movieId, scheduleId, personnel, getMovie(movieId)));
  }

  private Movie getMovie(int movieId) {
    return movies.get(idMap.get(movieId));
  }

  private int getPersonnel(int movieId, int scheduleId) {
    try {
      int personnel = InputView.inputPersonnel();

      validatePersonnel(movieId, scheduleId, personnel);

      return personnel;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return getPersonnel(movieId, scheduleId);
    }
  }

  private void validatePersonnel(int movieId, int scheduleId, int personnel) throws Exception {
    PlaySchedule schedule = getSchedule(getSchedules(movieId), scheduleId);
    if (schedule.getCapacity() < personnel) {
      throw new Exception("예약 가능 인원이 부족합니다.");
    }
  }

  private void showMovieSchedule(int movieId) {
    OutputView.printSchedule(getMovie(movieId));
  }

  private void validateMovieId(int inputId) throws Exception {
    if (!idMap.containsKey(inputId)) {
      throw new Exception("영화 ID가 존재하지 않습니다.");
    }
  }

  private void isStarted(LocalDateTime startTime) throws Exception {
    if (startTime.isBefore(DateTimeUtils.createDateTime("2019-04-16 15:40"))) {
      throw new Exception("영화가 이미 시작했습니다.");
    }
  }

  private void validateScheduleId(List<PlaySchedule> schedules, int scheduleId) throws Exception {
    if (scheduleId > schedules.size() || scheduleId <= ZERO) {
      throw new Exception("스케쥴 ID가 올바르지 않습니다.");
    }
  }

  private void hasNoCapacity(int capacity) throws Exception {
    if (capacity <= 0) {
      throw new Exception("예약 가능 인원이 없습니다.");
    }
  }

  private List<PlaySchedule> getSchedules(int movieId) {
    return getMovie(movieId).getPlaySchedules();
  }

  private PlaySchedule getSchedule(List<PlaySchedule> schedules, int scheduleId) {
    return schedules.get(scheduleId - ONE);
  }

  private void validateScheduleId(int movieId, int scheduleId) throws Exception {
    List<PlaySchedule> schedules = getSchedules(movieId);
    validateScheduleId(schedules, scheduleId);

    PlaySchedule schedule = getSchedule(schedules, scheduleId);

    hasNoCapacity(schedule.getCapacity());
    isStarted(schedule.getStartDateTime());
    isInOneHour(schedule.getStartDateTime());

    addFirstTime(schedule.getStartDateTime());
  }

  private void isInOneHour(LocalDateTime startTime) throws Exception {
    if(firstTime.equals( DateTimeUtils.createDateTime("2019-01-01 00:00"))){
      return;
    }

    if(!DateTimeUtils.isOneHourWithinRange(firstTime,startTime)){
      throw new Exception("다른 예매 내역과 한시간 이상 차이납니다.");
    }
  }

  private void addFirstTime(LocalDateTime startTime){
    firstTime = startTime;
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
