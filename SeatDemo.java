package sp25_bcs_079;
public class SeatDemo {
    public static void main(String[] args) {
        System.out.println("--- Seat Demo ---");
        Seat s1 = new Seat("1-001", Seat.SeatType.REGULAR, Screen.REGULAR_PRICE);
        Seat s2 = new Seat("2-001", Seat.SeatType.VIP, Screen.VIP_PRICE);
        Seat s3 = new Seat("3-001", Seat.SeatType.RECLINER, Screen.RECLINER_PRICE);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println("Booking 2-001: " + s2.book());
        System.out.println("Booking 2-001 again: " + s2.book());
        System.out.println("Cancel 2-001: " + s2.cancelBooking());
        s1.setPrice(550.0);
        System.out.println(s1);
    }
}