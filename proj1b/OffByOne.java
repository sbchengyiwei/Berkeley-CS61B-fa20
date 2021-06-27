public class OffByOne implements CharacterComparator {

    /** Determine whether the order of the character is within 1 */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs((int) x - (int) y) == 1;
    }
}
