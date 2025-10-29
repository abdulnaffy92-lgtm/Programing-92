public class Shop extends Plot {
    public Shop(String id, double width, double depth) {
        super(id, PlotType.COMM_SHOP, ShapeType.RECTANGLE, new double[]{width, depth});
    }
}

