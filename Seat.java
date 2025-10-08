package sp25_bcs_079;
public class Seat {
public enum SeatType { REGULAR, PREMIUM, VIP, RECLINER }


private final String id; // format row-col e.g. 3-007
private final SeatType type;
private double price;
private boolean available;


public Seat(String id, SeatType type, double price) {
this.id = id;
this.type = type;
this.price = price;
this.available = true;
}


public String getId() { return id; }
public SeatType getType() { return type; }
public double getPrice() { return price; }
public boolean isAvailable() { return available; }


public boolean book() {
if (!available) return false;
available = false;
return true;
}


public boolean cancelBooking() {
if (available) return false;
available = true;
return true;
}


public void setPrice(double price) { this.price = price; }


@Override
public String toString() {
return String.format("Seat %s (%s, %.2f PKR) - %s",
id, type, price, (available ? "Available" : "Booked"));
}
}