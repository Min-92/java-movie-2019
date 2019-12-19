import app.Booking;
import app.BookingResult;
import app.Payment;
import java.util.ArrayList;

public class MovieApplication {
    public static void main(String[] args) {
        Booking booking = new Booking();

        boolean nextBooking = false;
        do {
         nextBooking = booking.start();
        }while(nextBooking);

        ArrayList<BookingResult> bookingResults = booking.finish();
        Payment payment = new Payment(bookingResults);
        payment.start();



    }
}
