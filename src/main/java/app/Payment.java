package app;

import java.util.ArrayList;
import view.InputView;

public class Payment {

  private final ArrayList<BookingResult> bookingResults;

  public Payment(ArrayList<BookingResult> bookingResults) {
    this.bookingResults = bookingResults;
  }

  public void start() {
    System.out.println("## 결제를 진행합니다.");
    int point = getPoint();

  }

  private void validatePoint(int point) throws Exception {
    if(point < 0){
      throw new Exception("0보다 크거나 같아야 합니다.");
    }
  }

  private int getPoint() {
    try {
      int point = InputView.inputPoint();
      validatePoint(point);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return getPoint();
    }
  }
}
