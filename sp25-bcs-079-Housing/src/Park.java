public class Park {
    private String name;
    private ShapeType shape;
    private double[] dimensions;
    private double area;

    public Park(String name, ShapeType shape, double[] dimensions) {
        this.name = name;
        this.shape = shape;
        this.dimensions = dimensions.clone();
        computeArea();
    }

    private void computeArea() {
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

    public String summary() {
        return String.format("%s | %s | area=%.1f su", name, shape.name(), area);
    }
}

