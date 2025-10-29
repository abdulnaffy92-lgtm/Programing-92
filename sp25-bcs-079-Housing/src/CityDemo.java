public class CityDemo {
    public static void main(String[] args) {
        CityHousing lahore = new CityHousing("Lahore");
        // Print initial state
        lahore.printCityReport();

        System.out.println("\n--- Simulate bookings ---");
        boolean b1 = lahore.book("LDA Avenue 1", "Block A", "3-007");
        System.out.println("Booking LDA Avenue 1 > Block A > 3-007 : " + b1);
        boolean b2 = lahore.book("LDA Avenue 1", "Block A", "3-007");
        System.out.println("Attempt to book same plot again: " + b2);
        boolean c1 = lahore.cancel("LDA Avenue 1", "Block A", "3-007");
        System.out.println("Cancel booking: " + c1);

        System.out.println("\n-- Layout of Street 3 in LDA Avenue 1 > Block A --");
        HousingSociety s1 = lahore.findSocietyByName("LDA Avenue 1");
        if (s1 != null) {
            Block blockA = s1.findBlockByName("Block A");
            if (blockA != null) System.out.println(blockA.streetLayoutSummary(3));
        }

        System.out.println("\n-- Search: First available RES_1_KANAL CornerPlot in city --");
        Plot found = lahore.findFirstRES1KanalCornerInCity();
        if (found != null) System.out.println("Found: " + found.summary());
        else System.out.println("No RES_1_KANAL corner available.");

        System.out.println("\n-- First 3 vacant shops in Block C market of LDA Avenue 1 --");
        if (s1 != null) {
            Block blockC = s1.findBlockByName("Block C");
            if (blockC != null) {
                Shop[] vacantShops = blockC.getMarket().firstNVacantShops(3);
                if (vacantShops.length == 0) System.out.println("No vacant shops.");
                for (int i = 0; i < vacantShops.length; i++) System.out.println(vacantShops[i].summary());
            }
        }

        System.out.println("\n--- Demo complete ---");
    }
}

