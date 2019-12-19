import app.Booking;

public class MovieApplication {
    public static void main(String[] args) {
        Booking booking = new Booking();

        boolean nextBooking = false;
        do {
         nextBooking = booking.start();
        }while(nextBooking);

        booking.finish();

    }
}
