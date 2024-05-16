package tp.services.mapProvider;

import java.util.Objects;

public class CoordStruct {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordStruct that = (CoordStruct) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public final int x;
    public final int y;
    public CoordStruct(int x, int y) {
        this.x = x;
        this.y = y;
    }
}