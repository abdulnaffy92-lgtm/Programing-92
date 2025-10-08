package sp25_bcs_079;
public class CinemaDemo {
    public static void main(String[] args) {
        System.out.println("Lab 5 — Cinema Management System (sp25_bcs_079)");

        
        CityCinema karachi = preloadKarachi();
        CityCinema lahore = preloadLahore();
        CityCinema islamabad = preloadIslamabad();

        
        karachi.displayCompactCity();
        lahore.displayCompactCity();
        islamabad.displayCompactCity();

        Cinema kCinema1 = karachi.findCinemaByName("Atrium Saddar");
        if (kCinema1 != null) {
            Screen sc = kCinema1.getScreenAt(0); 
            String seatId = "3-007";
            System.out.println("Attempting to book " + seatId + " at " + kCinema1.getName() + " -> " + sc.getName());
            boolean booked = kCinema1.bookSeat(sc.getName(), seatId);
            System.out.println("Booked? " + booked);
           
            boolean bookedAgain = kCinema1.bookSeat(sc.getName(), seatId);
            System.out.println("Booked again? " + bookedAgain);
            
            boolean cancelled = kCinema1.cancelSeat(sc.getName(), seatId);
            System.out.println("Cancelled? " + cancelled);
                      sc.displayCompact();
        }

        
        String firstVip = lahore.findFirstAvailableVIP();
        System.out.println("First available VIP in Lahore: " + (firstVip == null ? "None" : firstVip));

        
    }

    private static CityCinema preloadKarachi() {
        CityCinema city = new CityCinema("Karachi", 2);
        Cinema c1 = new Cinema("Atrium Saddar", 2);
        c1.addScreen(new Screen("Nueplex Screen 1"));
        c1.addScreen(new Screen("Nueplex Screen 2"));
        Cinema c2 = new Cinema("Nueplex DHA", 2);
        c2.addScreen(new Screen("DHA Screen 1"));
        city.addCinema(c1);
        city.addCinema(c2);
        return city;
    }

    private static CityCinema preloadLahore() {
        CityCinema city = new CityCinema("Lahore", 2);
        Cinema c1 = new Cinema("CineStar Township", 2);
        c1.addScreen(new Screen("Township Screen 1"));
        Cinema c2 = new Cinema("CineStar Gulberg", 2);
        c2.addScreen(new Screen("Gulberg Screen 1"));
        city.addCinema(c1);
        city.addCinema(c2);
        return city;
    }

    private static CityCinema preloadIslamabad() {
        CityCinema city = new CityCinema("Islamabad", 1);
        Cinema c1 = new Cinema("Centaurus Megaplex", 2);
        c1.addScreen(new Screen("Centaurus Screen 1"));
        city.addCinema(c1);
        return city;
    }
}