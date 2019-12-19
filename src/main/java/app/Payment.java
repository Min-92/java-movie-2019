package app;

import java.util.ArrayList;
import view.InputView;
import view.OutputView;

public class Payment {

  private final String CASH = "현금";
  private final String CARD = "카드";
  private final int ONE = 1;
  private final int TWO = 2;

  private final ArrayList<BookingResult> bookingResults;

  public Payment(ArrayList<BookingResult> bookingResults) {
    this.bookingResults = bookingResults;
  }

  public void start() {
    System.out.println("## 결제를 진행합니다.");
    int point = getPoint();

    String method = getPaymentMethod();

    finishResult(point, method);
  }

  private int validatePrice(int price){
    if(price < 0){
      return 0;
    }
    return price;
  }

  private int calculatePrice(int point) {
    int price = 0;
    for (BookingResult bookingResult : bookingResults) {
      price += bookingResult.getPrice();
    }
    price -= point;
    return validatePrice(price);
  }

  private void finishResult(int point, String method) {
    int price = calculatePrice(point);
    OutputView.printPaymentResult(price, method);
  }

  private String validatePaymentMethod(int methodNumber) throws Exception {
    if (methodNumber != ONE && methodNumber != TWO) {
      throw new Exception("1과 2만 입력해주세요.");
    }

    if (methodNumber == ONE) {
      return CARD;
    }

    return CASH;
  }

  private String getPaymentMethod() {
    try {
      int methodNumber = InputView.inputPaymentMethod();
      return validatePaymentMethod(methodNumber);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return getPaymentMethod();
    }
  }

  private int validatePoint(int point) throws Exception {
    if (point < 0) {
      throw new Exception("0보다 크거나 같아야 합니다.");
    }
    return point;
  }

  private int getPoint() {
    try {
      int point = InputView.inputPoint();
      return validatePoint(point);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return getPoint();
    }
  }
}
