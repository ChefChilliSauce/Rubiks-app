import java.util.List;

public class ScrambleInput {
    public List<List<String>> scrambles;

    public ScrambleInput() {
    }                                                                                                                                           // Default constructor

    public List<List<String>> getScrambles() {
        return scrambles;                                                                                                              // Getter and Setter (Optional, for better compatibility with frameworks)
    }

    public void setScrambles(List<List<String>> scrambles) {
        this.scrambles = scrambles;
    }
}