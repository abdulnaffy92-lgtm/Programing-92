public class CityHousing {
    private String cityName;
    private HousingSociety[] societies;
    private int societyCount;

    public CityHousing(String cityName) {
        this.cityName = cityName;
        societies = new HousingSociety[2];
        societyCount = 0;
        preloadDefaultSocieties();
    }

    private void preloadDefaultSocieties() {
        addSociety(new HousingSociety("LDA Avenue 1"));
        addSociety(new HousingSociety("LDA Avenue 2"));
    }

    public void addSociety(HousingSociety s) {
        if (societyCount >= societies.length) {
            HousingSociety[] bigger = new HousingSociety[societies.length * 2];
            for (int i = 0; i < societies.length; i++) bigger[i] = societies[i];
            societies = bigger;
        }
        societies[societyCount++] = s;
    }

    public HousingSociety findSocietyByName(String name) {
        for (int i = 0; i < societyCount; i++) {
            if (societies[i].getName().equalsIgnoreCase(name)) return societies[i];
        }
        return null;
    }

    // Book/cancel forwarded
    public boolean book(String societyName, String blockName, String plotId) {
        HousingSociety s = findSocietyByName(societyName);
        if (s == null) return false;
        return s.book(blockName, plotId);
    }

    public boolean cancel(String societyName, String blockName, String plotId) {
        HousingSociety s = findSocietyByName(societyName);
        if (s == null) return false;
        return s.cancel(blockName, plotId);
    }

    // City-wide search for first RES_1_KANAL CornerPlot
    public Plot findFirstRES1KanalCornerInCity() {
        for (int i = 0; i < societyCount; i++) {
            Plot p = societies[i].findFirstAvailableRES1KanalCorner();
            if (p != null) return p;
        }
        return null;
    }

    public void printCityReport() {
        System.out.println("\n################ City: " + cityName + " ################");
        for (int i = 0; i < societyCount; i++) societies[i].printSocietyReport();
    }
}

