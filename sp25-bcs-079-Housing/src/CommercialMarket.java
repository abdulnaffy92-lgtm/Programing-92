public class CommercialMarket {
    private String id;
    private Shop[] shops;
    private int shopCount; // logical size

    // Construct with requested number of shops (clamped 12..20)
    public CommercialMarket(String id, int requestedShops) {
        this.id = id;
        if (requestedShops < 12) requestedShops = 12;
        if (requestedShops > 20) requestedShops = 20;
        shops = new Shop[requestedShops];
        shopCount = 0;
        preloadShops(requestedShops);
    }

    private void preloadShops(int count) {
        for (int i = 0; i < count; i++) {
            String shopId = String.format("%s-S%02d", id.replace(" ", ""), i + 1);
            shops[shopCount++] = new Shop(shopId, 3.0, 4.0);
        }
    }

    // Two-pass: return first n vacant shops
    public Shop[] firstNVacantShops(int n) {
        int avail = 0;
        for (int i = 0; i < shopCount; i++) if (!shops[i].isBooked()) avail++;
        int take = Math.min(n, avail);
        Shop[] res = new Shop[take];
        int idx = 0;
        for (int i = 0; i < shopCount && idx < take; i++) {
            if (!shops[i].isBooked()) res[idx++] = shops[i];
        }
        return res;
    }

    // Book a shop by its exact shop id
    public boolean bookShopById(String shopId) {
        for (int i = 0; i < shopCount; i++) {
            if (shops[i].getId().equals(shopId)) return shops[i].book();
        }
        return false;
    }

    public String summary() {
        int vacant = 0;
        for (int i = 0; i < shopCount; i++) if (!shops[i].isBooked()) vacant++;
        return String.format("%s | total shops=%d | vacant=%d", id, shopCount, vacant);
    }
}

