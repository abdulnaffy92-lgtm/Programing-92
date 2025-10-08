package sp25_bcs_079;
public class Cinema {
private final String name;
private Screen[] screens;
private int size; // logical size


public Cinema(String name, int initialCapacity) {
this.name = name;
this.screens = new Screen[Math.max(1, initialCapacity)];
this.size = 0;
}


public String getName() { return name; }


public void addScreen(Screen s) {
if (size >= screens.length) grow();
screens[size++] = s;
}


private void grow() {
Screen[] copy = new Screen[screens.length * 2 + 1];
for (int i = 0; i < screens.length; i++) copy[i] = screens[i];
screens = copy;
}


public Screen findScreenByName(String sname) {
for (int i = 0; i < size; i++) if (screens[i].getName().equals(sname)) return screens[i];
return null;
}


public Screen getScreenAt(int index) {
if (index < 0 || index >= size) return null;
return screens[index];
}


public boolean bookSeat(String screenName, String seatId) {
Screen s = findScreenByName(screenName);
if (s == null) return false;
return s.bookSeatById(seatId);
}


public boolean cancelSeat(String screenName, String seatId) {
Screen s = findScreenByName(screenName);
if (s == null) return false;
return s.cancelSeatById(seatId);
}


public int totalSeats() {
int t = 0;
for (int i = 0; i < size; i++) t += screens[i].totalSeats();
return t;
}


public int availableSeats() {
int t = 0;
for (int i = 0; i < size; i++) t += screens[i].availableSeats();
return t;
}


public int availableByType(Seat.SeatType type) {
int t = 0;
for (int i = 0; i < size; i++) t += screens[i].availableByType(type);
return t;
}


public void displayCompactAllScreens() {
System.out.println("Cinema: " + name + " -> Screens: " + size + ", Total seats: " + totalSeats() + ", Available: " + availableSeats());
for (int i = 0; i < size; i++) screens[i].displayCompact();
}
}