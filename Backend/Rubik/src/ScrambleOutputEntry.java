public class ScrambleOutputEntry {
    public char[][][] scrambled_state;
    public CFOPStage white_cross;
    public CFOPStage f2l;
    public CFOPStage oll;
    public CFOPStage pll;

    public ScrambleOutputEntry(char[][][] scrambled_state, CFOPStage white_cross, CFOPStage f2l, CFOPStage oll, CFOPStage pll) {
        this.scrambled_state = scrambled_state;                                                                                                                                                     // Constructor to initialize all fields
        this.white_cross = white_cross;
        this.f2l = f2l;
        this.oll = oll;
        this.pll = pll;
    }
}