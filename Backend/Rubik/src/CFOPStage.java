import java.util.List;

public class CFOPStage {
    public List<String> moves;
    public char[][][] state;

    public CFOPStage(List<String> moves, char[][][] state) {
        this.moves = moves;                                                                                 // Constructor to initialize all fields
        this.state = state;
    }
}