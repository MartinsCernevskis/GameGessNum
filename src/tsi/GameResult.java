package tsi;

public class GameResult {

    private String  name;
    private int     triesCount;
    private long    gameplay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTriesCount() {
        return triesCount;
    }

    public void setTriesCount(int triesCount) {
        this.triesCount = triesCount;
    }

    public long getGameplay() {
        return gameplay;
    }

    public void setGameplay(long gameplay) {
        this.gameplay = gameplay;
    }
}
