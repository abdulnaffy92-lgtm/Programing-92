public class Plot {
    protected String id;
    protected PlotType type;
    protected ShapeType shape;
    protected double[] dimensions;
    protected double area;
    protected long price;
    protected boolean booked;

    public Plot(String id, PlotType type, ShapeType shape, double[] dimensions) {
        this.id = id;
        this.type = type;
        this.shape = shape;
        this.dimensions = dimensions.clone();
        this.booked = false;
        computeArea();
        computePrice();
    }

    protected void computeArea() {
        switch (shape) {
            case RECTANGLE:
                area = dimensions[0] * dimensions[1];
                break;
            case TRAPEZOID:
                area = ((dimensions[0] + dimensions[1]) / 2.0) * dimensions[2];
                break;
            case L_SHAPE:
                area = (dimensions[0] * dimensions[1]) + (dimensions[2] * dimensions[3]);
                break;
            default:
                area = 0;
        }
    }

    protected void computePrice() {
        price = type.getBasePrice();
    }

    public boolean book() {
        if (booked) return false;
        booked = true;
        return true;
    }

    public boolean cancel() {
        if (!booked) return false;
        booked = false;
        return true;
    }

    public String availabilityMark() {
        return booked ? "X" : "A";
    }

    public String summary() {
        return String.format("%s | %s | area=%.1f su | price=%,d PKR | %s",
                id, type.name(), area, price, booked ? "BOOKED" : "AVAILABLE");
    }

    // Getters for other classes
    public String getId() { return id; }
    public PlotType getType() { return type; }
    public double getArea() { return area; }
    public boolean isBooked() { return booked; }
}


