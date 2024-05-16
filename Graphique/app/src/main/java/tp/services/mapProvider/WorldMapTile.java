package tp.services.mapProvider;

public enum WorldMapTile {
    LAND('G'),
    WATER('A'),
    HILL('M'),
    DESERT('J'),
    BEACH('D'),
    MOUNTAIN('P');

    private final char symbol;

    WorldMapTile(char symbol) {
        this.symbol = symbol;
    }

    public static WorldMapTile fromChar(char c) {
        for (WorldMapTile tile : values()) {
            if (tile.symbol == c) {
                return tile;
            }
        }
        throw new IllegalArgumentException("Unknown tile symbol: " + c);
    }

    public char getSymbol() {
        return symbol;
    }
}