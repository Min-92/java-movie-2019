package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMovieId() {
        System.out.println("## 예약할 영화를 선택하세요.");
        return inputNumber();
    }

    private static int inputNumber(){
        try {
            return Integer.parseInt(scanner.nextLine());
        }catch (Exception e){
            System.out.println("숫자만 입력하세요");
            return inputNumber();
        }
    }

    public static int inputScheduleId(){
        System.out.println("## 예약할 시간표를 선택하세요.");
        return inputNumber();
    }

    public static int inputPersonnel(){
        System.out.println("## 예약할 인원을 입력하세요.");
        return inputNumber();
    }
}
