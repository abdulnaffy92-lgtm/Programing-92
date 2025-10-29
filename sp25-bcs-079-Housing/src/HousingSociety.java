public class HousingSociety {
    private String name;
    private Block[] blocks;
    private int blockCount;

    public HousingSociety(String name) {
        this.name = name;
        blocks = new Block[3]; // start with capacity for 3
        blockCount = 0;
        preloadBlocks();
    }

    private void preloadBlocks() {
        addBlock(new Block("Block A"));
        addBlock(new Block("Block B"));
        addBlock(new Block("Block C"));
    }

    // manual growth and add
    public void addBlock(Block b) {
        if (blockCount >= blocks.length) {
            Block[] bigger = new Block[blocks.length * 2];
            for (int i = 0; i < blocks.length; i++) bigger[i] = blocks[i];
            blocks = bigger;
        }
        blocks[blockCount++] = b;
    }

    public Block findBlockByName(String blockName) {
        for (int i = 0; i < blockCount; i++) {
            if (blocks[i].getName().equalsIgnoreCase(blockName)) return blocks[i];
        }
        return null;
    }

    public boolean book(String blockName, String plotId) {
        Block b = findBlockByName(blockName);
        if (b == null) return false;
        return b.bookPlot(plotId);
    }

    public boolean cancel(String blockName, String plotId) {
        Block b = findBlockByName(blockName);
        if (b == null) return false;
        return b.cancelPlot(plotId);
    }

    public String getName() { return name; }

    // search across blocks for first available RES_1_KANAL CornerPlot
    public Plot findFirstAvailableRES1KanalCorner() {
        for (int i = 0; i < blockCount; i++) {
            Plot p = blocks[i].findFirstAvailableRES1KanalCorner();
            if (p != null) return p;
        }
        return null;
    }

    // find first COMM_SHOP available on street 4 (market-facing) across blocks
    public Plot findFirstAvailableCommShopFacingMarket() {
        for (int i = 0; i < blockCount; i++) {
            Plot[][] plots = blocks[i].getPlots();
            if (plots.length >= 4) {
                Plot[] street4 = plots[3]; // index 3 = street 4
                for (int j = 0; j < street4.length; j++) {
                    Plot pl = street4[j];
                    if (!pl.isBooked() && pl.getType() == PlotType.COMM_SHOP) {
                        return pl;
                    }
                }
            }
        }
        return null;
    }

    public Block getBlockByIndex(int index) {
        if (index < 0 || index >= blockCount) return null;
        return blocks[index];
    }

    public void printSocietyReport() {
        System.out.println("\n====== Society: " + name + " ======");
        for (int i = 0; i < blockCount; i++) blocks[i].printBlockReport();
    }
}

