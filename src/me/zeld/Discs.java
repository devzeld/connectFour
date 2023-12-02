package me.zeld;

public class Discs {
    private final char cell;

    private final char defaultEmptySpace = '□';

    private final char blueToken = '●';

    private final char redToken = '○';
    public Discs(){
        cell = defaultEmptySpace;
    }
    public Discs(boolean humanTurn){
        if (humanTurn) {
            cell = blueToken;
        } else {
            cell = redToken;
        }
    }

    public char getCell() {
        return cell;
    }
    public char getDefaultEmptySpace() {
        return defaultEmptySpace;
    }

    public char getBlueToken() {
        return blueToken;
    }

    public char getRedToken() {
        return redToken;
    }
}
