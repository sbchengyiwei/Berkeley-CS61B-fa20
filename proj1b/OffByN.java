public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int n) {
        this.n = n;
    }

    /** Determine whether the order of two characters is within N */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs((int) x - (int) y) == this.n;
    }
}
