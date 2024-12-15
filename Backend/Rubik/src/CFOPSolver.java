import java.util.ArrayList;
import java.util.List;

public class CFOPSolver {
    private final CrossSolver crossSolver;
    private final F2L f2lSolver;
    private final OLL ollSolver;
    private final PLL pllSolver;
    private final List<String> crossMoveRecord;
    private final List<String> f2lMoveRecord;
    private final List<String> ollMoveRecord;
    private final List<String> pllMoveRecord;

    public CFOPSolver(RubiksCube rubiksCube) {
        this.crossMoveRecord = new ArrayList<>();                                                                                                                                                                       //Getter method
        this.f2lMoveRecord = new ArrayList<>();
        this.ollMoveRecord = new ArrayList<>();
        this.pllMoveRecord = new ArrayList<>();// Initialize shared list
        this.crossSolver = new CrossSolver(rubiksCube, crossMoveRecord);
        this.f2lSolver = new F2L(rubiksCube, f2lMoveRecord);
        this.ollSolver = new OLL(rubiksCube, ollMoveRecord);
        this.pllSolver = new PLL(rubiksCube, pllMoveRecord);
    }

    public void solveCross() {
        crossSolver.solveWhiteCross();                                                                                                                                                                                             //method to solve white cross
    }

    public void solveF2L() {
        f2lSolver.solveSecondLayer();                                                                                                                                                                                               //method to solve F2L
    }

    public void solveOLL() {
        ollSolver.solveOLL();                                                                                                                                                                                                                   //method to solve OLL
    }

    public void solvePLL() {
        pllSolver.solvePLL();                                                                                                                                                                                                                       //method to PLL
    }

    public List<String> getCrossMoveRecord() {
        return crossMoveRecord;                                                                                                                                                                                                            //method to return cross solution moves
    }

    public List<String> getF2LMoveRecord() {
        return f2lMoveRecord;                                                                                                                                                                                                                       //method to return F2L solution moves
    }

    public List<String> getOLLMoveRecord() {
        return ollMoveRecord;                                                                                                                                                                                                                   //method to return OLL solution moves
    }

    public List<String> getPLLMoveRecord() {
        return pllMoveRecord;                                                                                                                                                                                                                   //method to return PLL solution moves
    }
}