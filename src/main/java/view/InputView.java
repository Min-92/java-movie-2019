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

    public static int inputNextProcess(){
        System.out.println("## 예약을 종료하고 결제를 진행하려면 1번, 추가 예약을 진행하려면 2번");
        return inputNumber();
    }

    public static int inputPoint(){
        System.out.println("## 포인트 사용금액을 입력하세요. 포인트가 없으면 0 입력");
        return inputNumber();
    }
}
