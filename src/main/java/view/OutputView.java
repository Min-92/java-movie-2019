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

  public static void printPaymentResult(int price, String method){
      System.out.println("최종 결제한 금액은 "+price+"원 입니다.\n 결제수단은 "+ method + "입니다.\n예매를 완료했습니다. 즐거운 영화관람 되세요");
  }
}
