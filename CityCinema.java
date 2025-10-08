package sp25_bcs_079;
public class CityCinema {
private final String cityName;
private Cinema[] cinemas;
private int size;


public CityCinema(String cityName, int initialCapacity) {
this.cityName = cityName;
cinemas = new Cinema[Math.max(1, initialCapacity)];
size = 0;
}


public String getCityName() { return cityName; }


public void addCinema(Cinema c) {
if (size >= cinemas.length) grow();
cinemas[size++] = c;
}


private void grow() {
Cinema[] copy = new Cinema[cinemas.length * 2 + 1];
for (int i = 0; i < cinemas.length; i++) copy[i] = cinemas[i];
cinemas = copy;
}


public Cinema findCinemaByName(String name) {
for (int i = 0; i < size; i++) if (cinemas[i].getName().equals(name)) return cinemas[i];
return null;
}


public boolean book(String cinemaName, String screenName, String seatId) {
Cinema c = findCinemaByName(cinemaName);
if (c == null) return false;
return c.bookSeat(screenName, seatId);
}


public boolean cancel(String cinemaName, String screenName, String seatId) {
Cinema c = findCinemaByName(cinemaName);
if (c == null) return false;
return c.cancelSeat(screenName, seatId);
}


// find first available VIP seat - return a human friendly string or null
public String findFirstAvailableVIP() {
for (int i = 0; i < size; i++) {
Cinema c = cinemas[i];
for (int s = 0; s < Integer.MAX_VALUE; s++) { // iterate screens until find null
Screen screen = c.getScreenAt(s);
if (screen == null) break;
Seat[] vipSeats = screen.seatsOfType(Seat.SeatType.VIP);
for (Seat seat : vipSeats) {
if (seat.isAvailable()) {
return String.format("%s > %s > %s (%s, %.2f PKR)",
c.getName(), screen.getName(), seat.getId(), seat.getType(), seat.getPrice());
}
}
}
}
return null;
}


public void displayCompactCity() {
System.out.println("=== City: " + cityName + " ===");
for (int i = 0; i < size; i++) cinemas[i].displayCompactAllScreens();
}
}