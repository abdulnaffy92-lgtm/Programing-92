public class CornerPlot extends Plot {
    private static final double CORNER_PREMIUM = 0.08;
    private double secondFrontage; // extra width at second road

    public CornerPlot(String id, PlotType type, ShapeType shape, double[] dimensions, double secondFrontage) {
        super(id, type, shape, dimensions);
        this.secondFrontage = secondFrontage;
        applyCornerAdjustments();
    }

    private void applyCornerAdjustments() {
        // price premium
        price = Math.round(price * (1.0 + CORNER_PREMIUM));
        // add a small area for second frontage if rectangle
        if (shape == ShapeType.RECTANGLE && dimensions.length >= 2) {
            area += secondFrontage * dimensions[1]; // depth = dimensions[1]
        }
    }

    @Override
    public String summary() {
        return String.format("%s | %s (CORNER) | area=%.1f su | price=%,d PKR | %s",
                id, type.name(), area, price, booked ? "BOOKED" : "AVAILABLE");
    }
}
