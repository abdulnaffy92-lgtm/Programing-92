public enum PlotType {
    RES_5_MARLA(4_000_000L),
    RES_10_MARLA(7_500_000L),
    RES_1_KANAL(14_000_000L),
    COMM_SHOP(3_000_000L),
    COMM_OFFICE(5_000_000L),
    PARKING(200_000L);

    private final long basePrice;

    PlotType(long basePrice) { this.basePrice = basePrice; }
    public long getBasePrice() { return basePrice; }
}
