package meta.projet;

public class BitMap<T> {
    private boolean[] bitmap;

    public BitMap(int capacity) {
        this.bitmap = new boolean[capacity];
    }

    public boolean contains(T obj) {
        return this.bitmap[obj.hashCode()];
    }

    public void add(T obj) {
        this.bitmap[obj.hashCode()] = true;
    }

    public void remove(T obj) {
        this.bitmap[obj.hashCode()] = false;
    }
}
