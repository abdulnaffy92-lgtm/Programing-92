package sp25_bcs_079;
public class Screen {
    private final String name;
    private Seat[][] seats; // jagged 2D array

    // pricing policy
    public static final double REGULAR_PRICE = 500.0;
    public static final double PREMIUM_PRICE = 750.0;
    public static final double VIP_PRICE = 1000.0;
    public static final double RECLINER_PRICE = 1200.0;

    public Screen(String name) {
        this(name, new int[]{10, 11, 12, 13, 14});
    }

    public Screen(String name, int[] rowLengths) {
        if (name == null) throw new IllegalArgumentException("Screen name cannot be null");
        this.name = name;
        if (rowLengths == null || rowLengths.length == 0) {
            rowLengths = new int[]{10, 11, 12, 13, 14};
        }
        // sanitize row lengths (must be at least 1)
        for (int i = 0; i < rowLengths.length; i++) {
            if (rowLengths[i] < 1) rowLengths[i] = 1;
        }
        materializeSeats(rowLengths);
    }

    private void materializeSeats(int[] rowLengths) {
        seats = new Seat[rowLengths.length][];
        int totalRows = rowLengths.length;
        for (int r = 0; r < totalRows; r++) {
            int cols = rowLengths[r];
            seats[r] = new Seat[cols];
            // derive type per row once (faster and consistent)
            Seat.SeatType type = typeForRow(r, totalRows);
            double price = priceForType(type);
            for (int c = 0; c < cols; c++) {
                String id = (r + 1) + "-" + String.format("%03d", c + 1);
                seats[r][c] = new Seat(id, type, price);
            }
        }
    }

    /**
     * Policy: last row = RECLINER, penultimate = VIP, the remaining rows are split
     * into front (REGULAR) and middle (PREMIUM). The method is defensive for
     * very small numbers of rows.
     */
    private Seat.SeatType typeForRow(int rowIndex, int totalRows) {
        if (totalRows <= 2) {
            // if only 1 or 2 rows, make the last RECLINER (if present) and others REGULAR
            if (rowIndex == totalRows - 1) return Seat.SeatType.RECLINER;
            return Seat.SeatType.REGULAR;
        }
        if (rowIndex == totalRows - 1) return Seat.SeatType.RECLINER;
        if (rowIndex == totalRows - 2) return Seat.SeatType.VIP;
        // remaining rows count (exclude last two)
        int remaining = totalRows - 2;
        // decide how many front rows are REGULAR (at least 1)
        int regularRows = Math.max(1, remaining / 2);
        if (rowIndex < regularRows) return Seat.SeatType.REGULAR;
        return Seat.SeatType.PREMIUM;
    }

    private double priceForType(Seat.SeatType type) {
        switch (type) {
            case PREMIUM:
                return PREMIUM_PRICE;
            case VIP:
                return VIP_PRICE;
            case RECLINER:
                return RECLINER_PRICE;
            case REGULAR:
            default:
                return REGULAR_PRICE;
        }
    }

    public String getName() {
        return name;
    }

    // find by id (safeguarded)
    public Seat findSeatById(String id) {
        if (id == null) return null;
        for (Seat[] row : seats) {
            for (Seat s : row) {
                if (id.equals(s.getId())) return s;
            }
        }
        return null;
    }

    // find by coordinates (1-based row, 1-based col)
    public Seat findSeatByCoords(int row1, int col1) {
        int r = row1 - 1;
        int c = col1 - 1;
        if (r < 0 || r >= seats.length) return null;
        if (c < 0 || c >= seats[r].length) return null;
        return seats[r][c];
    }

    public boolean bookSeatById(String id) {
        Seat s = findSeatById(id);
        if (s == null) return false;
        return s.book();
    }

    public boolean cancelSeatById(String id) {
        Seat s = findSeatById(id);
        if (s == null) return false;
        return s.cancelBooking();
    }

    public int totalSeats() {
        int total = 0;
        for (Seat[] row : seats) total += row.length;
        return total;
    }

    public int availableSeats() {
        int count = 0;
        for (Seat[] row : seats) for (Seat s : row) if (s.isAvailable()) count++;
        return count;
    }

    public int countByType(Seat.SeatType type) {
        int c = 0;
        for (Seat[] row : seats) for (Seat s : row) if (s.getType() == type) c++;
        return c;
    }

    public int availableByType(Seat.SeatType type) {
        int c = 0;
        for (Seat[] row : seats) for (Seat s : row) if (s.getType() == type && s.isAvailable()) c++;
        return c;
    }

    // two display modes
    public void displayCompact() {
        System.out.println("Screen: " + name + " (Total: " + totalSeats() + ", Available: " + availableSeats() + ")");
        for (int r = 0; r < seats.length; r++) {
            StringBuilder line = new StringBuilder();
            line.append("Row ").append(r + 1).append(": ");
            for (Seat s : seats[r]) {
                line.append(s.isAvailable() ? "[ ]" : "[X]");
            }
            System.out.println(line.toString());
        }
    }

    public void displayDetailed() {
        System.out.println("--- Detailed listing for Screen: " + name + " ---");
        for (Seat[] row : seats) for (Seat s : row) System.out.println(s.toString());
    }

    // return Seat[] matching a predicate - implemented for type (two-pass)
    public Seat[] seatsOfType(Seat.SeatType type) {
        int count = 0;
        for (Seat[] row : seats) for (Seat s : row) if (s.getType() == type) count++;
        Seat[] result = new Seat[count];
        int idx = 0;
        for (Seat[] row : seats) for (Seat s : row) if (s.getType() == type) result[idx++] = s;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Screen %s (Rows: %d, Seats: %d, Available: %d)",
                name, seats.length, totalSeats(), availableSeats());
    }
}





