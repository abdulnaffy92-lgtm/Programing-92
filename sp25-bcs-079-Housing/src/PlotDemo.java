public class PlotDemo {
    public static void main(String[] args) {
        System.out.println("Plot Demo - unit tests\n");

        Plot p = new Plot("1-001", PlotType.RES_5_MARLA, ShapeType.RECTANGLE, new double[]{20.0, 10.0});
        System.out.println("Initial: " + p.summary());
        System.out.println("Book result: " + p.book());
        System.out.println("After booking: " + p.summary());
        System.out.println("Book again (should fail): " + p.book());
        System.out.println("Cancel result: " + p.cancel());
        System.out.println("After cancel: " + p.summary());

        System.out.println("\nCorner plot demo:");
        CornerPlot cp = new CornerPlot("1-004", PlotType.RES_5_MARLA, ShapeType.RECTANGLE, new double[]{20.0, 10.0}, 5.0);
        System.out.println(cp.summary());
    }
}
