import app.Booking;
import view.InputView;

public class MovieApplication {
    public static void main(String[] args) {
        Booking booking = new Booking();

        booking.start();

        int movieId = InputView.inputMovieId();

        // TODO 구현 진행
    }
}
