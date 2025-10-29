public class Block {
    private String name;
    private Plot[][] plots; // jagged: streets x plots
    private Park[] parks;   // 1..2 parks
    private int parkCount;
    private CommercialMarket market;

    // default street lengths: 10,11,12,13,14
    private static final int[] DEFAULT_LENGTHS = {10, 11, 12, 13, 14};

    public Block(String name) {
        this.name = name;
        createPlots(DEFAULT_LENGTHS);
        createAmenities();
    }

    // Alternate constructor if caller wants custom street lengths
    public Block(String name, int[] streetLengths) {
        this.name = name;
        createPlots(streetLengths);
        createAmenities();
    }

    private void createPlots(int[] streetLengths) {
        plots = new Plot[5][]; // exactly 5 streets
        for (int s = 0; s < 5; s++) {
            int len = streetLengths[s];
            plots[s] = new Plot[len];
            for (int p = 0; p < len; p++) {
                int streetIndex = s + 1; // 1-based
                String id = String.format("[%d-%03d]", streetIndex, p + 1);

                PlotType type;
                ShapeType shape;
                double[] dims;

                switch (streetIndex) {
                    case 1:
                        type = PlotType.RES_5_MARLA;
                        shape = ShapeType.RECTANGLE;
                        dims = new double[]{20.0, 10.0};
                        break;
                    case 2:
                        type = PlotType.RES_10_MARLA;
                        shape = ShapeType.RECTANGLE;
                        dims = new double[]{30.0, 15.0};
                        break;
                    case 3:
                        type = PlotType.RES_1_KANAL;
                        shape = ShapeType.TRAPEZOID;
                        dims = new double[]{40.0, 44.0, 20.0}; // front, back, depth
                        break;
                    case 4:
                        type = PlotType.COMM_SHOP;
                        shape = ShapeType.RECTANGLE;
                        dims = new double[]{8.0, 5.0};
                        break;
                    case 5:
                        type = PlotType.COMM_OFFICE;
                        shape = ShapeType.RECTANGLE;
                        dims = new double[]{10.0, 8.0};
                        break;
                    default:
                        type = PlotType.PARKING;
                        shape = ShapeType.RECTANGLE;
                        dims = new double[]{4.0, 5.0};
                }

                // Every 5th plot becomes PARKING
                if ((p + 1) % 5 == 0) {
                    type = PlotType.PARKING;
                    shape = ShapeType.RECTANGLE;
                    dims = new double[]{3.0, 2.0};
                }

                // Every 4th plot on residential streets -> CornerPlot (unless it's parking)
                if (streetIndex <= 3 && ((p + 1) % 4 == 0) && type != PlotType.PARKING) {
                    plots[s][p] = new CornerPlot(id, type, shape, dims, 5.0);
                } else {
                    plots[s][p] = new Plot(id, type, shape, dims);
                }
            }
        }
    }

    private void createAmenities() {
        parks = new Park[2];
        parks[0] = new Park(name + " Park 1", ShapeType.RECTANGLE, new double[]{30.0, 20.0});
        parkCount = 1;
        // Randomly create a second park about half the time
        if (Math.random() > 0.5) {
            parks[parkCount++] = new Park(name + " Park 2", ShapeType.RECTANGLE, new double[]{24.0, 18.0});
        }
        market = new CommercialMarket(name + " Market", 14);
    }

    // --- Accessors used by other classes ---
    public String getName() { return name; }
    public Plot[][] getPlots() { return plots; }
    public CommercialMarket getMarket() { return market; }

    // Find plot in the jagged array by id
    public Plot findPlotById(String plotId) {
        for (int s = 0; s < plots.length; s++) {
            for (int p = 0; p < plots[s].length; p++) {
                if (plots[s][p].getId().equals(plotId)) return plots[s][p];
            }
        }
        return null;
    }

    public boolean bookPlot(String plotId) {
        Plot pl = findPlotById(plotId);
        if (pl == null) return false;
        return pl.book();
    }

    public boolean cancelPlot(String plotId) {
        Plot pl = findPlotById(plotId);
        if (pl == null) return false;
        return pl.cancel();
    }

    // Return a compact layout for a street (1-based streetIndex)
    public String streetLayoutSummary(int streetIndex) {
        if (streetIndex < 1 || streetIndex > plots.length) return "";
        Plot[] street = plots[streetIndex - 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < street.length; i++) {
            sb.append(street[i].getId()).append(street[i].availabilityMark()).append(" ");
        }
        return sb.toString();
    }

    // Print a readable block report
    public void printBlockReport() {
        System.out.println("\n--- " + name + " Report ---");
        int total = 0, available = 0;
        for (int s = 0; s < plots.length; s++) {
            total += plots[s].length;
            for (int p = 0; p < plots[s].length; p++) if (!plots[s][p].isBooked()) available++;
        }
        System.out.printf("Total plots: %d | Available: %d%n", total, available);
        System.out.println("Street layouts (ID + A/X):");
        for (int s = 0; s < plots.length; s++) {
            System.out.print("Street " + (s + 1) + ": ");
            for (int p = 0; p < plots[s].length; p++) System.out.print(plots[s][p].getId() + plots[s][p].availabilityMark() + " ");
            System.out.println();
        }
        System.out.println("Detailed plots:");
        for (int s = 0; s < plots.length; s++) {
            for (int p = 0; p < plots[s].length; p++) System.out.println(plots[s][p].summary());
        }
        System.out.println("Amenities:");
        for (int i = 0; i < parkCount; i++) System.out.println(parks[i].summary());
        System.out.println(market.summary());
    }

    // Find first available RES_1_KANAL CornerPlot in this block (or null)
    public Plot findFirstAvailableRES1KanalCorner() {
        for (int s = 0; s < plots.length; s++) {
            for (int p = 0; p < plots[s].length; p++) {
                Plot pl = plots[s][p];
                if (!pl.isBooked() && pl.getType() == PlotType.RES_1_KANAL && pl instanceof CornerPlot) return pl;
            }
        }
        return null;
    }
}

