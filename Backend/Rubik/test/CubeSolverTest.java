import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CubeSolverTest {

    private RubiksCube cube;
    private CubeMoves cubeMoves;
    private CrossSolver crossSolver;
    private F2L f2LSolver;
    private OLL ollSolver;
    private PLL pllSolver;

    @BeforeEach
    void setUp() {
        cube = new RubiksCube(); // Initialize RubiksCube
        cubeMoves = new CubeMoves(cube); // Initialize CubeMoves
        crossSolver = new CrossSolver(cube); // Initialize CrossSolver
        f2LSolver = new F2L(cube); // Initialize F2L
        ollSolver = new OLL(cube); // Initialize OLL
        pllSolver = new PLL(cube); // Initialize PLL
    }

    // Test Cross solving stage with multiple scrambles
    @Test
    void testCrossWithScrambles() {
        String[][] scrambles = {
                {"R`" , "L" , "F`" , "R" , "F" , "T`" , "F`" , "B`" , "Ba" , "R" , "F`" , "R" , "B`" , "T" , "F" , "Ba`" , "T" , "R`" , "T`" , "R"},  // Scramble 1
                {"B" , "T`" , "B`" , "R`" , "B" , "B" , "L`" , "F`" , "L`" , "R`" , "B" , "Ba" , "L`" , "B" , "B" , "T`" , "T`" , "L" , "L" , "Ba`"},// Scramble 2
                {"L`" , "R`" , "L`" , "T" , "Ba" , "L" , "F`" , "R`" , "T" , "T" , "Ba" , "T`" , "B" , "L`" , "F`" , "Ba`" , "L" , "F`" , "F`" , "R`"},
                {"B" , "Ba" , "B`" , "R`" , "T`" , "F`" , "Ba" , "B`" , "R`" , "T`" , "B" , "L`" , "B`" , "T" , "R" , "B" , "Ba`" , "F`" , "R" , "F"},
                {"F`" , "T`" , "T`" , "L" , "L" , "F`" , "B`" , "L`" , "T" , "F" , "T" , "Ba`" , "F" , "T" , "Ba" , "R" , "F" , "T`" , "Ba`" ,  "T`"},
                {"Ba" , "T" , "Ba`" , "F`" , "B" , "R" , "B" , "R`" , "R`" , "F" , "R" , "T`" , "L" , "T`" , "F`" , "R" , "B" , "R" , "L`" , "B`"},
                {"F" , "F" , "R" , "R" , "F" , "Ba" , "Ba" , "B" , "Ba`" , "L" , "F`" , "Ba" , "L`" , "F" , "R`" , "R`" , "F" , "R`" , "B`" , "B`"},
                {"F" , "L" , "F" , "T" , "L" , "B" , "R" , "F" , "T`" , "R" , "T" , "B`" , "R`" , "L`" , "L`" , "R`" , "T" , "Ba`" , "B" , "R"},
                {"T`" , "L`" , "R`" , "L`" , "Ba" , "R`" , "R`" , "Ba" , "Ba" , "T`" , "F`" , "R" , "R" , "Ba" , "L`" , "R`" , "B" , "L" , "R`" , "R`"},
                {"F" , "T" , "Ba`" , "L`" , "F" , "T" , "L`" , "Ba`" , "L" , "B`" , "Ba" , "R`" , "Ba`" , "F" , "T" , "B`" , "Ba" , "R" , "R" , "Ba"},
                {"Ba`" , "T" , "T" , "L" , "Ba`" , "F`" , "L" , "B`" , "F" , "T" , "R`" , "F`" , "T`" , "L`" , "L`" , "R`" , "L`" , "T`" , "T`" , "Ba`"},
                {"F`" , "T`" , "B" , "R" , "L" , "B" , "B" , "Ba`" , "F`" , "Ba" , "L" , "R`" , "F" , "F" , "R`" , "Ba" , "T" , "B`" , "Ba" , "B"},
                {"L`" , "Ba`" , "L`" , "R" , "L`" , "T" , "R" , "F`" , "B`" , "T" , "T" , "B`" , "B`" , "R`" , "F`" , "B" , "F`" , "Ba`" , "Ba`" , "L`"},
                {"R" , "T" , "R`" , "L`" , "L`" , "R`" , "T`" , "Ba" , "T`" , "F" , "R`" , "L" , "T" , "F`" , "L" , "Ba`" , "L`" , "B" , "T" , "Ba`"},
                {"T" , "T" , "L" , "Ba`" , "Ba`" , "T`" , "L" , "B`" , "B`" , "T`" , "Ba" , "T" , "B`" , "R" , "B" , "T`" , "L`" , "Ba" , "B`" , "L`"},
                {"B`" , "L" , "L" , "F" , "B" , "R`" , "F`" , "F`" , "Ba`" , "B`" , "Ba" , "R" , "R" , "L" , "R" , "B`" , "L" , "Ba" , "L" , "Ba"},
                {"L`" , "F`" , "Ba`" , "R" , "B`" , "B`" , "T" , "R`" , "B" , "L" , "T`" , "R" , "Ba`" , "B`" , "R`" , "T`" , "F`" , "L" , "B`" , "Ba"},
                {"F`" , "Ba`" , "Ba`" , "B" , "Ba" , "T`" , "L" , "Ba`" , "T`" , "B" , "T`" , "F`" , "R" , "Ba`" , "T`" , "F" , "T`" , "T`" , "R" , "R"},
                {"L`" , "R`" , "L`" , "L`" , "Ba" , "L" , "F`" , "F`" , "Ba" , "L" , "R`" , "T`" , "T`" , "Ba`" , "F`" , "L" , "B" , "R`" , "Ba`" , "R`"},
                {"B" , "L`" , "B`" , "L`" , "R`" , "R`" , "L" , "B`" , "L" , "R" , "F`" , "T`" , "L`" , "T" , "B`" , "B`" , "T" , "Ba`" , "F`" , "B`"},
                {"R" , "L" , "Ba" , "B`" , "T`" , "T`" , "L`" , "B`" , "F" , "F" , "Ba" , "B" , "Ba`" , "R" , "T`" , "R`" , "B" , "T`" , "F`" , "F`"},
                {"L`" , "T`" , "F`" , "T" , "R`" , "F`" , "R" , "F" , "R" , "T" , "F" , "T" , "T" , "L`" , "L`" , "F`" , "Ba`" , "B" , "L" , "R`"},
                {"F" , "T`" , "Ba" , "R`" , "T`" , "T`" , "F`" , "T" , "T" , "B" , "R`" , "F" , "R`" , "R`" , "F" , "R" , "F" , "R" , "F" , "T"},
                {"B`" , "R`" , "Ba`" , "T" , "B" , "R" , "R" , "T" , "R" , "T" , "Ba`" , "Ba`" , "B`" , "L" , "R" , "F`" , "R" , "F`" , "F`" , "T`"},
                {"T" , "B" , "B" , "L`" , "R`" , "Ba`" , "R" , "F" , "L" , "T`" , "B" , "Ba" , "T`" , "L" , "L" , "B`" , "T" , "B`" , "Ba" , "F`"},
                {"Ba" , "R" , "T`" , "B`" , "Ba`" , "B" , "R" , "T`" , "R" , "F`" , "L`" , "F" , "L" , "T`" , "R`" , "R`" , "T`" , "Ba`" , "F`" , "T`"},
                {"Ba`" , "F" , "B" , "L" , "Ba`" , "R`" , "Ba`" , "B" , "L`" , "F" , "L" , "F`" , "R" , "F`" , "L`" , "Ba`" , "F`" , "Ba" , "R" , "B"},
                {"B" , "T`" , "Ba`" , "B`" , "Ba`" , "L`" , "Ba`" , "F" , "T" , "T" , "Ba`" , "L" , "T" , "T" , "B" , "Ba`" , "R`" , "Ba`" , "R`" , "B"},
                {"B`" , "F`" , "B`" , "B`" , "Ba" , "Ba" , "R`" , "F`" , "B" , "T`" , "R`" , "L" , "T`" , "B" , "L" , "F`" , "Ba`" , "L" , "B`" , "Ba"},
                {"L" , "L" , "R`" , "B`" , "R`" , "F" , "F" , "Ba`" , "L`" , "L`" , "R" , "B`" , "F`" , "R" , "B" , "T`" , "R`" , "B`" , "T" , "L`"},
                {"B" , "L`" , "T" , "B`" , "T" , "B" , "R" , "L" , "T`" , "R`" , "R`" , "L`" , "Ba" , "F" , "Ba" , "Ba" , "L`" , "B`" , "T" , "R`"},
                {"F`" , "R`" , "T" , "R`" , "R`" , "F" , "B`" , "T`" , "R" , "B" , "L" , "L" , "T`" , "T`" , "B" , "B" , "R" , "B`" , "L" , "T`"},
                {"L" , "F" , "T`" , "Ba" , "F" , "Ba`" , "F" , "R" , "T" , "L`" , "B`" , "F" , "B`" , "T`" , "R" , "F`" , "R`" , "R`" , "F" , "F"},
                {"Ba" , "R`" , "Ba" , "R`" , "B`" , "R" , "B`" , "T" , "Ba`" , "R`" , "B" , "Ba`" , "F" , "Ba" , "B`" , "Ba" , "B`" , "L" , "T`" , "B"},
                {"R" , "T`" , "R`" , "B`" , "F`" , "T`" , "Ba" , "T`" , "R" , "T" , "Ba`" , "F" , "R`" , "R`" , "F`" , "B`" , "F" , "B`" , "B`" , "T"},
                {"Ba`" , "B" , "T`" , "F" , "L`" , "F" , "F" , "B`" , "F" , "R" , "R" , "B" , "L`" , "L`" , "F" , "B`" , "F" , "Ba`" , "B" , "T`"},
                {"Ba" , "R" , "Ba`" , "L`" , "F" , "B`" , "B`" , "L`" , "L`" , "R" , "R" , "Ba" , "T" , "F" , "Ba`" , "Ba`" , "T`" , "B`" , "L`" , "B`"},
                {"Ba" , "B`" , "L" , "R`" , "B`" , "F" , "Ba`" , "L" , "Ba" , "T" , "T" , "B`" , "Ba`" , "B`" , "L`" , "F" , "Ba`" , "T`" , "R" , "L`"},
                {"F`" , "L`" , "T`" , "L`" , "Ba" , "F`" , "L" , "Ba`" , "F`" , "F`" , "T" , "R" , "T" , "L`" , "T" , "B" , "R" , "R" , "Ba`" , "T`"},
                {"F" , "Ba" , "F" , "F" , "T`" , "F`" , "Ba" , "B" , "B" , "L`" , "F`" , "T" , "Ba" , "T`" , "T`" , "B`" , "B`" , "R`" , "Ba`" , "L`"},
                {"B" , "R" , "L" , "B`" , "T" , "B" , "L`" , "F" , "F" , "L`" , "Ba`" , "R`" , "L`" , "B`" , "L" , "B" , "T" , "T" , "B`" , "L"},
                {"B`" , "L`" , "Ba" , "R`" , "F" , "Ba" , "L" , "R" , "F`" , "B" , "T" , "B" , "Ba`" , "T`" , "R`" , "B`" , "F" , "L`" , "Ba`" , "B"},
                {"B`" , "B`" , "Ba" , "R" , "L" , "T" , "F" , "T" , "L`" , "L`" , "Ba`" , "L" , "T`" , "R`" , "B`" , "B`" , "L" , "F" , "F" , "T"},
                {"T" , "B" , "R" , "Ba`" , "F" , "T" , "L`" , "Ba`" , "T" , "R" , "F" , "Ba`" , "F" , "B`" , "L" , "T" , "T" , "R`" , "B`" , "B`"},
                {"F`" , "F`" , "R" , "B`" , "L" , "L" , "Ba" , "T`" , "R" , "B" , "B" , "F" , "B" , "B" , "R" , "B" , "F" , "B`" , "L" , "Ba"},
                {"F" , "Ba`" , "B" , "F" , "R`" , "B`" , "Ba" , "Ba" , "F`" , "T`" , "Ba`" , "F`" , "F`" , "B`" , "F" , "T" , "F" , "B`" , "F" , "R`"},
                {"F`" , "Ba" , "R" , "Ba`" , "B`" , "R" , "F" , "T`" , "T`" , "R" , "R" , "Ba" , "R`" , "Ba" , "F" , "T" , "B" , "L" , "R`" , "R`"},
                {"T`" , "F`" , "T" , "F" , "L" , "Ba" , "B" , "Ba" , "L" , "T`" , "R`" , "L`" , "L`" , "T`" , "F" , "R" , "B`" , "B`" , "R" , "F`"},
                {"R" , "T`" , "Ba`" , "F" , "F" , "T`" , "T`" , "Ba" , "L`" , "R`" , "F`" , "B`" , "T" , "F" , "R`" , "Ba`" , "L" , "Ba`" , "L`" , "T`"}// Scramble 3
        };

        for (String[] scramble : scrambles) {
            // Apply scramble to cube
            cubeMoves.performMoves(scramble);

            // Solve the white cross
            crossSolver.solveWhiteCross();

            // Test the expected output for the white cross (Bottom face and the adjacent faces)
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][0][1]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][1][0]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][1][2]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][2][1]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][2][1]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][2][1]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][2][1]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][2][1]);
        }
    }

    // Test F2L solving stage with multiple scrambles
    @Test
    void testF2LWithScrambles() {
        String[][] scrambles = {
                {"R`" , "L" , "F`" , "R" , "F" , "T`" , "F`" , "B`" , "Ba" , "R" , "F`" , "R" , "B`" , "T" , "F" , "Ba`" , "T" , "R`" , "T`" , "R"},  // Scramble 1
                {"B" , "T`" , "B`" , "R`" , "B" , "B" , "L`" , "F`" , "L`" , "R`" , "B" , "Ba" , "L`" , "B" , "B" , "T`" , "T`" , "L" , "L" , "Ba`"},// Scramble 2
                {"L`" , "R`" , "L`" , "T" , "Ba" , "L" , "F`" , "R`" , "T" , "T" , "Ba" , "T`" , "B" , "L`" , "F`" , "Ba`" , "L" , "F`" , "F`" , "R`"},
                {"B" , "Ba" , "B`" , "R`" , "T`" , "F`" , "Ba" , "B`" , "R`" , "T`" , "B" , "L`" , "B`" , "T" , "R" , "B" , "Ba`" , "F`" , "R" , "F"},
                {"F`" , "T`" , "T`" , "L" , "L" , "F`" , "B`" , "L`" , "T" , "F" , "T" , "Ba`" , "F" , "T" , "Ba" , "R" , "F" , "T`" , "Ba`" ,  "T`"},
                {"Ba" , "T" , "Ba`" , "F`" , "B" , "R" , "B" , "R`" , "R`" , "F" , "R" , "T`" , "L" , "T`" , "F`" , "R" , "B" , "R" , "L`" , "B`"},
                {"F" , "F" , "R" , "R" , "F" , "Ba" , "Ba" , "B" , "Ba`" , "L" , "F`" , "Ba" , "L`" , "F" , "R`" , "R`" , "F" , "R`" , "B`" , "B`"},
                {"F" , "L" , "F" , "T" , "L" , "B" , "R" , "F" , "T`" , "R" , "T" , "B`" , "R`" , "L`" , "L`" , "R`" , "T" , "Ba`" , "B" , "R"},
                {"T`" , "L`" , "R`" , "L`" , "Ba" , "R`" , "R`" , "Ba" , "Ba" , "T`" , "F`" , "R" , "R" , "Ba" , "L`" , "R`" , "B" , "L" , "R`" , "R`"},
                {"F" , "T" , "Ba`" , "L`" , "F" , "T" , "L`" , "Ba`" , "L" , "B`" , "Ba" , "R`" , "Ba`" , "F" , "T" , "B`" , "Ba" , "R" , "R" , "Ba"},
                {"Ba`" , "T" , "T" , "L" , "Ba`" , "F`" , "L" , "B`" , "F" , "T" , "R`" , "F`" , "T`" , "L`" , "L`" , "R`" , "L`" , "T`" , "T`" , "Ba`"},
                {"F`" , "T`" , "B" , "R" , "L" , "B" , "B" , "Ba`" , "F`" , "Ba" , "L" , "R`" , "F" , "F" , "R`" , "Ba" , "T" , "B`" , "Ba" , "B"},
                {"L`" , "Ba`" , "L`" , "R" , "L`" , "T" , "R" , "F`" , "B`" , "T" , "T" , "B`" , "B`" , "R`" , "F`" , "B" , "F`" , "Ba`" , "Ba`" , "L`"},
                {"R" , "T" , "R`" , "L`" , "L`" , "R`" , "T`" , "Ba" , "T`" , "F" , "R`" , "L" , "T" , "F`" , "L" , "Ba`" , "L`" , "B" , "T" , "Ba`"},
                {"T" , "T" , "L" , "Ba`" , "Ba`" , "T`" , "L" , "B`" , "B`" , "T`" , "Ba" , "T" , "B`" , "R" , "B" , "T`" , "L`" , "Ba" , "B`" , "L`"},
                {"B`" , "L" , "L" , "F" , "B" , "R`" , "F`" , "F`" , "Ba`" , "B`" , "Ba" , "R" , "R" , "L" , "R" , "B`" , "L" , "Ba" , "L" , "Ba"},
                {"L`" , "F`" , "Ba`" , "R" , "B`" , "B`" , "T" , "R`" , "B" , "L" , "T`" , "R" , "Ba`" , "B`" , "R`" , "T`" , "F`" , "L" , "B`" , "Ba"},
                {"F`" , "Ba`" , "Ba`" , "B" , "Ba" , "T`" , "L" , "Ba`" , "T`" , "B" , "T`" , "F`" , "R" , "Ba`" , "T`" , "F" , "T`" , "T`" , "R" , "R"},
                {"L`" , "R`" , "L`" , "L`" , "Ba" , "L" , "F`" , "F`" , "Ba" , "L" , "R`" , "T`" , "T`" , "Ba`" , "F`" , "L" , "B" , "R`" , "Ba`" , "R`"},
                {"B" , "L`" , "B`" , "L`" , "R`" , "R`" , "L" , "B`" , "L" , "R" , "F`" , "T`" , "L`" , "T" , "B`" , "B`" , "T" , "Ba`" , "F`" , "B`"},
                {"R" , "L" , "Ba" , "B`" , "T`" , "T`" , "L`" , "B`" , "F" , "F" , "Ba" , "B" , "Ba`" , "R" , "T`" , "R`" , "B" , "T`" , "F`" , "F`"},
                {"L`" , "T`" , "F`" , "T" , "R`" , "F`" , "R" , "F" , "R" , "T" , "F" , "T" , "T" , "L`" , "L`" , "F`" , "Ba`" , "B" , "L" , "R`"},
                {"F" , "T`" , "Ba" , "R`" , "T`" , "T`" , "F`" , "T" , "T" , "B" , "R`" , "F" , "R`" , "R`" , "F" , "R" , "F" , "R" , "F" , "T"},
                {"B`" , "R`" , "Ba`" , "T" , "B" , "R" , "R" , "T" , "R" , "T" , "Ba`" , "Ba`" , "B`" , "L" , "R" , "F`" , "R" , "F`" , "F`" , "T`"},
                {"T" , "B" , "B" , "L`" , "R`" , "Ba`" , "R" , "F" , "L" , "T`" , "B" , "Ba" , "T`" , "L" , "L" , "B`" , "T" , "B`" , "Ba" , "F`"},
                {"Ba" , "R" , "T`" , "B`" , "Ba`" , "B" , "R" , "T`" , "R" , "F`" , "L`" , "F" , "L" , "T`" , "R`" , "R`" , "T`" , "Ba`" , "F`" , "T`"},
                {"Ba`" , "F" , "B" , "L" , "Ba`" , "R`" , "Ba`" , "B" , "L`" , "F" , "L" , "F`" , "R" , "F`" , "L`" , "Ba`" , "F`" , "Ba" , "R" , "B"},
                {"B" , "T`" , "Ba`" , "B`" , "Ba`" , "L`" , "Ba`" , "F" , "T" , "T" , "Ba`" , "L" , "T" , "T" , "B" , "Ba`" , "R`" , "Ba`" , "R`" , "B"},
                {"B`" , "F`" , "B`" , "B`" , "Ba" , "Ba" , "R`" , "F`" , "B" , "T`" , "R`" , "L" , "T`" , "B" , "L" , "F`" , "Ba`" , "L" , "B`" , "Ba"},
                {"L" , "L" , "R`" , "B`" , "R`" , "F" , "F" , "Ba`" , "L`" , "L`" , "R" , "B`" , "F`" , "R" , "B" , "T`" , "R`" , "B`" , "T" , "L`"},
                {"B" , "L`" , "T" , "B`" , "T" , "B" , "R" , "L" , "T`" , "R`" , "R`" , "L`" , "Ba" , "F" , "Ba" , "Ba" , "L`" , "B`" , "T" , "R`"},
                {"F`" , "R`" , "T" , "R`" , "R`" , "F" , "B`" , "T`" , "R" , "B" , "L" , "L" , "T`" , "T`" , "B" , "B" , "R" , "B`" , "L" , "T`"},
                {"L" , "F" , "T`" , "Ba" , "F" , "Ba`" , "F" , "R" , "T" , "L`" , "B`" , "F" , "B`" , "T`" , "R" , "F`" , "R`" , "R`" , "F" , "F"},
                {"Ba" , "R`" , "Ba" , "R`" , "B`" , "R" , "B`" , "T" , "Ba`" , "R`" , "B" , "Ba`" , "F" , "Ba" , "B`" , "Ba" , "B`" , "L" , "T`" , "B"},
                {"R" , "T`" , "R`" , "B`" , "F`" , "T`" , "Ba" , "T`" , "R" , "T" , "Ba`" , "F" , "R`" , "R`" , "F`" , "B`" , "F" , "B`" , "B`" , "T"},
                {"Ba`" , "B" , "T`" , "F" , "L`" , "F" , "F" , "B`" , "F" , "R" , "R" , "B" , "L`" , "L`" , "F" , "B`" , "F" , "Ba`" , "B" , "T`"},
                {"Ba" , "R" , "Ba`" , "L`" , "F" , "B`" , "B`" , "L`" , "L`" , "R" , "R" , "Ba" , "T" , "F" , "Ba`" , "Ba`" , "T`" , "B`" , "L`" , "B`"},
                {"Ba" , "B`" , "L" , "R`" , "B`" , "F" , "Ba`" , "L" , "Ba" , "T" , "T" , "B`" , "Ba`" , "B`" , "L`" , "F" , "Ba`" , "T`" , "R" , "L`"},
                {"F`" , "L`" , "T`" , "L`" , "Ba" , "F`" , "L" , "Ba`" , "F`" , "F`" , "T" , "R" , "T" , "L`" , "T" , "B" , "R" , "R" , "Ba`" , "T`"},
                {"F" , "Ba" , "F" , "F" , "T`" , "F`" , "Ba" , "B" , "B" , "L`" , "F`" , "T" , "Ba" , "T`" , "T`" , "B`" , "B`" , "R`" , "Ba`" , "L`"},
                {"B" , "R" , "L" , "B`" , "T" , "B" , "L`" , "F" , "F" , "L`" , "Ba`" , "R`" , "L`" , "B`" , "L" , "B" , "T" , "T" , "B`" , "L"},
                {"B`" , "L`" , "Ba" , "R`" , "F" , "Ba" , "L" , "R" , "F`" , "B" , "T" , "B" , "Ba`" , "T`" , "R`" , "B`" , "F" , "L`" , "Ba`" , "B"},
                {"B`" , "B`" , "Ba" , "R" , "L" , "T" , "F" , "T" , "L`" , "L`" , "Ba`" , "L" , "T`" , "R`" , "B`" , "B`" , "L" , "F" , "F" , "T"},
                {"T" , "B" , "R" , "Ba`" , "F" , "T" , "L`" , "Ba`" , "T" , "R" , "F" , "Ba`" , "F" , "B`" , "L" , "T" , "T" , "R`" , "B`" , "B`"},
                {"F`" , "F`" , "R" , "B`" , "L" , "L" , "Ba" , "T`" , "R" , "B" , "B" , "F" , "B" , "B" , "R" , "B" , "F" , "B`" , "L" , "Ba"},
                {"F" , "Ba`" , "B" , "F" , "R`" , "B`" , "Ba" , "Ba" , "F`" , "T`" , "Ba`" , "F`" , "F`" , "B`" , "F" , "T" , "F" , "B`" , "F" , "R`"},
                {"F`" , "Ba" , "R" , "Ba`" , "B`" , "R" , "F" , "T`" , "T`" , "R" , "R" , "Ba" , "R`" , "Ba" , "F" , "T" , "B" , "L" , "R`" , "R`"},
                {"T`" , "F`" , "T" , "F" , "L" , "Ba" , "B" , "Ba" , "L" , "T`" , "R`" , "L`" , "L`" , "T`" , "F" , "R" , "B`" , "B`" , "R" , "F`"},
                {"R" , "T`" , "Ba`" , "F" , "F" , "T`" , "T`" , "Ba" , "L`" , "R`" , "F`" , "B`" , "T" , "F" , "R`" , "Ba`" , "L" , "Ba`" , "L`" , "T`"}// Scramble 3
        };

        for (String[] scramble : scrambles) {
            // Apply scramble to cube
            cubeMoves.performMoves(scramble);

            // Solve the F2L (First 2 Layers)
            crossSolver.solveWhiteCross();
            f2LSolver.solveSecondLayer();

            // Test the expected output for the F2L state
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][0][0]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][0][1]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][0][2]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][1][0]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][1][1]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][1][2]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][2][0]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][2][1]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][2][2]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][1][0]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][1][1]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][1][2]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][2][0]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][2][1]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][2][2]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][1][0]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][1][1]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][1][2]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][2][0]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][2][1]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][2][2]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][1][0]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][1][1]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][1][2]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][2][0]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][2][1]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][2][2]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][1][0]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][1][1]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][1][2]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][2][0]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][2][1]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][2][2]);
        }
    }

    // Test OLL solving stage with multiple scrambles
    @Test
    void testOLLWithScrambles() {
        String[][] scrambles = {
                {"R`" , "L" , "F`" , "R" , "F" , "T`" , "F`" , "B`" , "Ba" , "R" , "F`" , "R" , "B`" , "T" , "F" , "Ba`" , "T" , "R`" , "T`" , "R"},  // Scramble 1
                {"B" , "T`" , "B`" , "R`" , "B" , "B" , "L`" , "F`" , "L`" , "R`" , "B" , "Ba" , "L`" , "B" , "B" , "T`" , "T`" , "L" , "L" , "Ba`"},// Scramble 2
                {"L`" , "R`" , "L`" , "T" , "Ba" , "L" , "F`" , "R`" , "T" , "T" , "Ba" , "T`" , "B" , "L`" , "F`" , "Ba`" , "L" , "F`" , "F`" , "R`"},
                {"B" , "Ba" , "B`" , "R`" , "T`" , "F`" , "Ba" , "B`" , "R`" , "T`" , "B" , "L`" , "B`" , "T" , "R" , "B" , "Ba`" , "F`" , "R" , "F"},
                {"F`" , "T`" , "T`" , "L" , "L" , "F`" , "B`" , "L`" , "T" , "F" , "T" , "Ba`" , "F" , "T" , "Ba" , "R" , "F" , "T`" , "Ba`" ,  "T`"},
                {"Ba" , "T" , "Ba`" , "F`" , "B" , "R" , "B" , "R`" , "R`" , "F" , "R" , "T`" , "L" , "T`" , "F`" , "R" , "B" , "R" , "L`" , "B`"},
                {"F" , "F" , "R" , "R" , "F" , "Ba" , "Ba" , "B" , "Ba`" , "L" , "F`" , "Ba" , "L`" , "F" , "R`" , "R`" , "F" , "R`" , "B`" , "B`"},
                {"F" , "L" , "F" , "T" , "L" , "B" , "R" , "F" , "T`" , "R" , "T" , "B`" , "R`" , "L`" , "L`" , "R`" , "T" , "Ba`" , "B" , "R"},
                {"T`" , "L`" , "R`" , "L`" , "Ba" , "R`" , "R`" , "Ba" , "Ba" , "T`" , "F`" , "R" , "R" , "Ba" , "L`" , "R`" , "B" , "L" , "R`" , "R`"},
                {"F" , "T" , "Ba`" , "L`" , "F" , "T" , "L`" , "Ba`" , "L" , "B`" , "Ba" , "R`" , "Ba`" , "F" , "T" , "B`" , "Ba" , "R" , "R" , "Ba"},
                {"Ba`" , "T" , "T" , "L" , "Ba`" , "F`" , "L" , "B`" , "F" , "T" , "R`" , "F`" , "T`" , "L`" , "L`" , "R`" , "L`" , "T`" , "T`" , "Ba`"},
                {"F`" , "T`" , "B" , "R" , "L" , "B" , "B" , "Ba`" , "F`" , "Ba" , "L" , "R`" , "F" , "F" , "R`" , "Ba" , "T" , "B`" , "Ba" , "B"},
                {"L`" , "Ba`" , "L`" , "R" , "L`" , "T" , "R" , "F`" , "B`" , "T" , "T" , "B`" , "B`" , "R`" , "F`" , "B" , "F`" , "Ba`" , "Ba`" , "L`"},
                {"R" , "T" , "R`" , "L`" , "L`" , "R`" , "T`" , "Ba" , "T`" , "F" , "R`" , "L" , "T" , "F`" , "L" , "Ba`" , "L`" , "B" , "T" , "Ba`"},
                {"T" , "T" , "L" , "Ba`" , "Ba`" , "T`" , "L" , "B`" , "B`" , "T`" , "Ba" , "T" , "B`" , "R" , "B" , "T`" , "L`" , "Ba" , "B`" , "L`"},
                {"B`" , "L" , "L" , "F" , "B" , "R`" , "F`" , "F`" , "Ba`" , "B`" , "Ba" , "R" , "R" , "L" , "R" , "B`" , "L" , "Ba" , "L" , "Ba"},
                {"L`" , "F`" , "Ba`" , "R" , "B`" , "B`" , "T" , "R`" , "B" , "L" , "T`" , "R" , "Ba`" , "B`" , "R`" , "T`" , "F`" , "L" , "B`" , "Ba"},
                {"F`" , "Ba`" , "Ba`" , "B" , "Ba" , "T`" , "L" , "Ba`" , "T`" , "B" , "T`" , "F`" , "R" , "Ba`" , "T`" , "F" , "T`" , "T`" , "R" , "R"},
                {"L`" , "R`" , "L`" , "L`" , "Ba" , "L" , "F`" , "F`" , "Ba" , "L" , "R`" , "T`" , "T`" , "Ba`" , "F`" , "L" , "B" , "R`" , "Ba`" , "R`"},
                {"B" , "L`" , "B`" , "L`" , "R`" , "R`" , "L" , "B`" , "L" , "R" , "F`" , "T`" , "L`" , "T" , "B`" , "B`" , "T" , "Ba`" , "F`" , "B`"},
                {"R" , "L" , "Ba" , "B`" , "T`" , "T`" , "L`" , "B`" , "F" , "F" , "Ba" , "B" , "Ba`" , "R" , "T`" , "R`" , "B" , "T`" , "F`" , "F`"},
                {"L`" , "T`" , "F`" , "T" , "R`" , "F`" , "R" , "F" , "R" , "T" , "F" , "T" , "T" , "L`" , "L`" , "F`" , "Ba`" , "B" , "L" , "R`"},
                {"F" , "T`" , "Ba" , "R`" , "T`" , "T`" , "F`" , "T" , "T" , "B" , "R`" , "F" , "R`" , "R`" , "F" , "R" , "F" , "R" , "F" , "T"},
                {"B`" , "R`" , "Ba`" , "T" , "B" , "R" , "R" , "T" , "R" , "T" , "Ba`" , "Ba`" , "B`" , "L" , "R" , "F`" , "R" , "F`" , "F`" , "T`"},
                {"T" , "B" , "B" , "L`" , "R`" , "Ba`" , "R" , "F" , "L" , "T`" , "B" , "Ba" , "T`" , "L" , "L" , "B`" , "T" , "B`" , "Ba" , "F`"},
                {"Ba" , "R" , "T`" , "B`" , "Ba`" , "B" , "R" , "T`" , "R" , "F`" , "L`" , "F" , "L" , "T`" , "R`" , "R`" , "T`" , "Ba`" , "F`" , "T`"},
                {"Ba`" , "F" , "B" , "L" , "Ba`" , "R`" , "Ba`" , "B" , "L`" , "F" , "L" , "F`" , "R" , "F`" , "L`" , "Ba`" , "F`" , "Ba" , "R" , "B"},
                {"B" , "T`" , "Ba`" , "B`" , "Ba`" , "L`" , "Ba`" , "F" , "T" , "T" , "Ba`" , "L" , "T" , "T" , "B" , "Ba`" , "R`" , "Ba`" , "R`" , "B"},
                {"B`" , "F`" , "B`" , "B`" , "Ba" , "Ba" , "R`" , "F`" , "B" , "T`" , "R`" , "L" , "T`" , "B" , "L" , "F`" , "Ba`" , "L" , "B`" , "Ba"},
                {"L" , "L" , "R`" , "B`" , "R`" , "F" , "F" , "Ba`" , "L`" , "L`" , "R" , "B`" , "F`" , "R" , "B" , "T`" , "R`" , "B`" , "T" , "L`"},
                {"B" , "L`" , "T" , "B`" , "T" , "B" , "R" , "L" , "T`" , "R`" , "R`" , "L`" , "Ba" , "F" , "Ba" , "Ba" , "L`" , "B`" , "T" , "R`"},
                {"F`" , "R`" , "T" , "R`" , "R`" , "F" , "B`" , "T`" , "R" , "B" , "L" , "L" , "T`" , "T`" , "B" , "B" , "R" , "B`" , "L" , "T`"},
                {"L" , "F" , "T`" , "Ba" , "F" , "Ba`" , "F" , "R" , "T" , "L`" , "B`" , "F" , "B`" , "T`" , "R" , "F`" , "R`" , "R`" , "F" , "F"},
                {"Ba" , "R`" , "Ba" , "R`" , "B`" , "R" , "B`" , "T" , "Ba`" , "R`" , "B" , "Ba`" , "F" , "Ba" , "B`" , "Ba" , "B`" , "L" , "T`" , "B"},
                {"R" , "T`" , "R`" , "B`" , "F`" , "T`" , "Ba" , "T`" , "R" , "T" , "Ba`" , "F" , "R`" , "R`" , "F`" , "B`" , "F" , "B`" , "B`" , "T"},
                {"Ba`" , "B" , "T`" , "F" , "L`" , "F" , "F" , "B`" , "F" , "R" , "R" , "B" , "L`" , "L`" , "F" , "B`" , "F" , "Ba`" , "B" , "T`"},
                {"Ba" , "R" , "Ba`" , "L`" , "F" , "B`" , "B`" , "L`" , "L`" , "R" , "R" , "Ba" , "T" , "F" , "Ba`" , "Ba`" , "T`" , "B`" , "L`" , "B`"},
                {"Ba" , "B`" , "L" , "R`" , "B`" , "F" , "Ba`" , "L" , "Ba" , "T" , "T" , "B`" , "Ba`" , "B`" , "L`" , "F" , "Ba`" , "T`" , "R" , "L`"},
                {"F`" , "L`" , "T`" , "L`" , "Ba" , "F`" , "L" , "Ba`" , "F`" , "F`" , "T" , "R" , "T" , "L`" , "T" , "B" , "R" , "R" , "Ba`" , "T`"},
                {"F" , "Ba" , "F" , "F" , "T`" , "F`" , "Ba" , "B" , "B" , "L`" , "F`" , "T" , "Ba" , "T`" , "T`" , "B`" , "B`" , "R`" , "Ba`" , "L`"},
                {"B" , "R" , "L" , "B`" , "T" , "B" , "L`" , "F" , "F" , "L`" , "Ba`" , "R`" , "L`" , "B`" , "L" , "B" , "T" , "T" , "B`" , "L"},
                {"B`" , "L`" , "Ba" , "R`" , "F" , "Ba" , "L" , "R" , "F`" , "B" , "T" , "B" , "Ba`" , "T`" , "R`" , "B`" , "F" , "L`" , "Ba`" , "B"},
                {"B`" , "B`" , "Ba" , "R" , "L" , "T" , "F" , "T" , "L`" , "L`" , "Ba`" , "L" , "T`" , "R`" , "B`" , "B`" , "L" , "F" , "F" , "T"},
                {"T" , "B" , "R" , "Ba`" , "F" , "T" , "L`" , "Ba`" , "T" , "R" , "F" , "Ba`" , "F" , "B`" , "L" , "T" , "T" , "R`" , "B`" , "B`"},
                {"F`" , "F`" , "R" , "B`" , "L" , "L" , "Ba" , "T`" , "R" , "B" , "B" , "F" , "B" , "B" , "R" , "B" , "F" , "B`" , "L" , "Ba"},
                {"F" , "Ba`" , "B" , "F" , "R`" , "B`" , "Ba" , "Ba" , "F`" , "T`" , "Ba`" , "F`" , "F`" , "B`" , "F" , "T" , "F" , "B`" , "F" , "R`"},
                {"F`" , "Ba" , "R" , "Ba`" , "B`" , "R" , "F" , "T`" , "T`" , "R" , "R" , "Ba" , "R`" , "Ba" , "F" , "T" , "B" , "L" , "R`" , "R`"},
                {"T`" , "F`" , "T" , "F" , "L" , "Ba" , "B" , "Ba" , "L" , "T`" , "R`" , "L`" , "L`" , "T`" , "F" , "R" , "B`" , "B`" , "R" , "F`"},
                {"R" , "T`" , "Ba`" , "F" , "F" , "T`" , "T`" , "Ba" , "L`" , "R`" , "F`" , "B`" , "T" , "F" , "R`" , "Ba`" , "L" , "Ba`" , "L`" , "T`"}
        };

        for (String[] scramble : scrambles) {
            // Apply scramble to cube
            cubeMoves.performMoves(scramble);

            // Solve the OLL (Orientation of the Last Layer)
            crossSolver.solveWhiteCross();
            f2LSolver.solveSecondLayer();
            ollSolver.solveOLL();

            // Test the expected output for the OLL state (Top face should be all yellow)
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    assertEquals('Y', cube.getCube()[RubiksCube.TOP][i][j]);
                }
            }
        }
    }

    // Test PLL solving stage with multiple scrambles
    @Test
    void testPLLWithScrambles() {
        String[][] scrambles = {
                {"R`" , "L" , "F`" , "R" , "F" , "T`" , "F`" , "B`" , "Ba" , "R" , "F`" , "R" , "B`" , "T" , "F" , "Ba`" , "T" , "R`" , "T`" , "R"},  // Scramble 1
                {"B" , "T`" , "B`" , "R`" , "B" , "B" , "L`" , "F`" , "L`" , "R`" , "B" , "Ba" , "L`" , "B" , "B" , "T`" , "T`" , "L" , "L" , "Ba`"},// Scramble 2
                {"L`" , "R`" , "L`" , "T" , "Ba" , "L" , "F`" , "R`" , "T" , "T" , "Ba" , "T`" , "B" , "L`" , "F`" , "Ba`" , "L" , "F`" , "F`" , "R`"},
                {"B" , "Ba" , "B`" , "R`" , "T`" , "F`" , "Ba" , "B`" , "R`" , "T`" , "B" , "L`" , "B`" , "T" , "R" , "B" , "Ba`" , "F`" , "R" , "F"},
                {"F`" , "T`" , "T`" , "L" , "L" , "F`" , "B`" , "L`" , "T" , "F" , "T" , "Ba`" , "F" , "T" , "Ba" , "R" , "F" , "T`" , "Ba`" ,  "T`"},
                {"Ba" , "T" , "Ba`" , "F`" , "B" , "R" , "B" , "R`" , "R`" , "F" , "R" , "T`" , "L" , "T`" , "F`" , "R" , "B" , "R" , "L`" , "B`"},
                {"F" , "F" , "R" , "R" , "F" , "Ba" , "Ba" , "B" , "Ba`" , "L" , "F`" , "Ba" , "L`" , "F" , "R`" , "R`" , "F" , "R`" , "B`" , "B`"},
                {"F" , "L" , "F" , "T" , "L" , "B" , "R" , "F" , "T`" , "R" , "T" , "B`" , "R`" , "L`" , "L`" , "R`" , "T" , "Ba`" , "B" , "R"},
                {"T`" , "L`" , "R`" , "L`" , "Ba" , "R`" , "R`" , "Ba" , "Ba" , "T`" , "F`" , "R" , "R" , "Ba" , "L`" , "R`" , "B" , "L" , "R`" , "R`"},
                {"F" , "T" , "Ba`" , "L`" , "F" , "T" , "L`" , "Ba`" , "L" , "B`" , "Ba" , "R`" , "Ba`" , "F" , "T" , "B`" , "Ba" , "R" , "R" , "Ba"},
                {"Ba`" , "T" , "T" , "L" , "Ba`" , "F`" , "L" , "B`" , "F" , "T" , "R`" , "F`" , "T`" , "L`" , "L`" , "R`" , "L`" , "T`" , "T`" , "Ba`"},
                {"F`" , "T`" , "B" , "R" , "L" , "B" , "B" , "Ba`" , "F`" , "Ba" , "L" , "R`" , "F" , "F" , "R`" , "Ba" , "T" , "B`" , "Ba" , "B"},
                {"L`" , "Ba`" , "L`" , "R" , "L`" , "T" , "R" , "F`" , "B`" , "T" , "T" , "B`" , "B`" , "R`" , "F`" , "B" , "F`" , "Ba`" , "Ba`" , "L`"},
                {"R" , "T" , "R`" , "L`" , "L`" , "R`" , "T`" , "Ba" , "T`" , "F" , "R`" , "L" , "T" , "F`" , "L" , "Ba`" , "L`" , "B" , "T" , "Ba`"},
                {"T" , "T" , "L" , "Ba`" , "Ba`" , "T`" , "L" , "B`" , "B`" , "T`" , "Ba" , "T" , "B`" , "R" , "B" , "T`" , "L`" , "Ba" , "B`" , "L`"},
                {"B`" , "L" , "L" , "F" , "B" , "R`" , "F`" , "F`" , "Ba`" , "B`" , "Ba" , "R" , "R" , "L" , "R" , "B`" , "L" , "Ba" , "L" , "Ba"},
                {"L`" , "F`" , "Ba`" , "R" , "B`" , "B`" , "T" , "R`" , "B" , "L" , "T`" , "R" , "Ba`" , "B`" , "R`" , "T`" , "F`" , "L" , "B`" , "Ba"},
                {"F`" , "Ba`" , "Ba`" , "B" , "Ba" , "T`" , "L" , "Ba`" , "T`" , "B" , "T`" , "F`" , "R" , "Ba`" , "T`" , "F" , "T`" , "T`" , "R" , "R"},
                {"L`" , "R`" , "L`" , "L`" , "Ba" , "L" , "F`" , "F`" , "Ba" , "L" , "R`" , "T`" , "T`" , "Ba`" , "F`" , "L" , "B" , "R`" , "Ba`" , "R`"},
                {"B" , "L`" , "B`" , "L`" , "R`" , "R`" , "L" , "B`" , "L" , "R" , "F`" , "T`" , "L`" , "T" , "B`" , "B`" , "T" , "Ba`" , "F`" , "B`"},
                {"R" , "L" , "Ba" , "B`" , "T`" , "T`" , "L`" , "B`" , "F" , "F" , "Ba" , "B" , "Ba`" , "R" , "T`" , "R`" , "B" , "T`" , "F`" , "F`"},
                {"L`" , "T`" , "F`" , "T" , "R`" , "F`" , "R" , "F" , "R" , "T" , "F" , "T" , "T" , "L`" , "L`" , "F`" , "Ba`" , "B" , "L" , "R`"},
                {"F" , "T`" , "Ba" , "R`" , "T`" , "T`" , "F`" , "T" , "T" , "B" , "R`" , "F" , "R`" , "R`" , "F" , "R" , "F" , "R" , "F" , "T"},
                {"B`" , "R`" , "Ba`" , "T" , "B" , "R" , "R" , "T" , "R" , "T" , "Ba`" , "Ba`" , "B`" , "L" , "R" , "F`" , "R" , "F`" , "F`" , "T`"},
                {"T" , "B" , "B" , "L`" , "R`" , "Ba`" , "R" , "F" , "L" , "T`" , "B" , "Ba" , "T`" , "L" , "L" , "B`" , "T" , "B`" , "Ba" , "F`"},
                {"Ba" , "R" , "T`" , "B`" , "Ba`" , "B" , "R" , "T`" , "R" , "F`" , "L`" , "F" , "L" , "T`" , "R`" , "R`" , "T`" , "Ba`" , "F`" , "T`"},
                {"Ba`" , "F" , "B" , "L" , "Ba`" , "R`" , "Ba`" , "B" , "L`" , "F" , "L" , "F`" , "R" , "F`" , "L`" , "Ba`" , "F`" , "Ba" , "R" , "B"},
                {"B" , "T`" , "Ba`" , "B`" , "Ba`" , "L`" , "Ba`" , "F" , "T" , "T" , "Ba`" , "L" , "T" , "T" , "B" , "Ba`" , "R`" , "Ba`" , "R`" , "B"},
                {"B`" , "F`" , "B`" , "B`" , "Ba" , "Ba" , "R`" , "F`" , "B" , "T`" , "R`" , "L" , "T`" , "B" , "L" , "F`" , "Ba`" , "L" , "B`" , "Ba"},
                {"L" , "L" , "R`" , "B`" , "R`" , "F" , "F" , "Ba`" , "L`" , "L`" , "R" , "B`" , "F`" , "R" , "B" , "T`" , "R`" , "B`" , "T" , "L`"},
                {"B" , "L`" , "T" , "B`" , "T" , "B" , "R" , "L" , "T`" , "R`" , "R`" , "L`" , "Ba" , "F" , "Ba" , "Ba" , "L`" , "B`" , "T" , "R`"},
                {"F`" , "R`" , "T" , "R`" , "R`" , "F" , "B`" , "T`" , "R" , "B" , "L" , "L" , "T`" , "T`" , "B" , "B" , "R" , "B`" , "L" , "T`"},
                {"L" , "F" , "T`" , "Ba" , "F" , "Ba`" , "F" , "R" , "T" , "L`" , "B`" , "F" , "B`" , "T`" , "R" , "F`" , "R`" , "R`" , "F" , "F"},
                {"Ba" , "R`" , "Ba" , "R`" , "B`" , "R" , "B`" , "T" , "Ba`" , "R`" , "B" , "Ba`" , "F" , "Ba" , "B`" , "Ba" , "B`" , "L" , "T`" , "B"},
                {"R" , "T`" , "R`" , "B`" , "F`" , "T`" , "Ba" , "T`" , "R" , "T" , "Ba`" , "F" , "R`" , "R`" , "F`" , "B`" , "F" , "B`" , "B`" , "T"},
                {"Ba`" , "B" , "T`" , "F" , "L`" , "F" , "F" , "B`" , "F" , "R" , "R" , "B" , "L`" , "L`" , "F" , "B`" , "F" , "Ba`" , "B" , "T`"},
                {"Ba" , "R" , "Ba`" , "L`" , "F" , "B`" , "B`" , "L`" , "L`" , "R" , "R" , "Ba" , "T" , "F" , "Ba`" , "Ba`" , "T`" , "B`" , "L`" , "B`"},
                {"Ba" , "B`" , "L" , "R`" , "B`" , "F" , "Ba`" , "L" , "Ba" , "T" , "T" , "B`" , "Ba`" , "B`" , "L`" , "F" , "Ba`" , "T`" , "R" , "L`"},
                {"F`" , "L`" , "T`" , "L`" , "Ba" , "F`" , "L" , "Ba`" , "F`" , "F`" , "T" , "R" , "T" , "L`" , "T" , "B" , "R" , "R" , "Ba`" , "T`"},
                {"F" , "Ba" , "F" , "F" , "T`" , "F`" , "Ba" , "B" , "B" , "L`" , "F`" , "T" , "Ba" , "T`" , "T`" , "B`" , "B`" , "R`" , "Ba`" , "L`"},
                {"B" , "R" , "L" , "B`" , "T" , "B" , "L`" , "F" , "F" , "L`" , "Ba`" , "R`" , "L`" , "B`" , "L" , "B" , "T" , "T" , "B`" , "L"},
                {"B`" , "L`" , "Ba" , "R`" , "F" , "Ba" , "L" , "R" , "F`" , "B" , "T" , "B" , "Ba`" , "T`" , "R`" , "B`" , "F" , "L`" , "Ba`" , "B"},
                {"B`" , "B`" , "Ba" , "R" , "L" , "T" , "F" , "T" , "L`" , "L`" , "Ba`" , "L" , "T`" , "R`" , "B`" , "B`" , "L" , "F" , "F" , "T"},
                {"T" , "B" , "R" , "Ba`" , "F" , "T" , "L`" , "Ba`" , "T" , "R" , "F" , "Ba`" , "F" , "B`" , "L" , "T" , "T" , "R`" , "B`" , "B`"},
                {"F`" , "F`" , "R" , "B`" , "L" , "L" , "Ba" , "T`" , "R" , "B" , "B" , "F" , "B" , "B" , "R" , "B" , "F" , "B`" , "L" , "Ba"},
                {"F" , "Ba`" , "B" , "F" , "R`" , "B`" , "Ba" , "Ba" , "F`" , "T`" , "Ba`" , "F`" , "F`" , "B`" , "F" , "T" , "F" , "B`" , "F" , "R`"},
                {"F`" , "Ba" , "R" , "Ba`" , "B`" , "R" , "F" , "T`" , "T`" , "R" , "R" , "Ba" , "R`" , "Ba" , "F" , "T" , "B" , "L" , "R`" , "R`"},
                {"T`" , "F`" , "T" , "F" , "L" , "Ba" , "B" , "Ba" , "L" , "T`" , "R`" , "L`" , "L`" , "T`" , "F" , "R" , "B`" , "B`" , "R" , "F`"},
                {"R" , "T`" , "Ba`" , "F" , "F" , "T`" , "T`" , "Ba" , "L`" , "R`" , "F`" , "B`" , "T" , "F" , "R`" , "Ba`" , "L" , "Ba`" , "L`" , "T`"}
        };

        for (String[] scramble : scrambles) {
            // Apply scramble to cube
            cubeMoves.performMoves(scramble);

            // Solve the PLL (Permutation of the Last Layer)
            crossSolver.solveWhiteCross();
            f2LSolver.solveSecondLayer();
            ollSolver.solveOLL();
            pllSolver.solvePLL();

            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][0][0]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][0][1]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][0][2]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][1][0]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][1][1]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][1][2]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][2][0]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][2][1]);
            assertEquals('W', cube.getCube()[RubiksCube.BOTTOM][2][2]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][0][0]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][0][1]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][0][2]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][1][0]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][1][1]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][1][2]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][2][0]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][2][1]);
            assertEquals('G', cube.getCube()[RubiksCube.FRONT][2][2]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][0][0]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][0][1]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][0][2]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][1][0]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][1][1]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][1][2]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][2][0]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][2][1]);
            assertEquals('R', cube.getCube()[RubiksCube.LEFT][2][2]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][0][0]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][0][1]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][0][2]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][1][0]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][1][1]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][1][2]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][2][0]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][2][1]);
            assertEquals('B', cube.getCube()[RubiksCube.BACK][2][2]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][0][0]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][0][1]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][0][2]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][1][0]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][1][1]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][1][2]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][2][0]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][2][1]);
            assertEquals('O', cube.getCube()[RubiksCube.RIGHT][2][2]);
            assertEquals('Y', cube.getCube()[RubiksCube.TOP][0][0]);
            assertEquals('Y', cube.getCube()[RubiksCube.TOP][0][1]);
            assertEquals('Y', cube.getCube()[RubiksCube.TOP][0][2]);
            assertEquals('Y', cube.getCube()[RubiksCube.TOP][1][0]);
            assertEquals('Y', cube.getCube()[RubiksCube.TOP][1][1]);
            assertEquals('Y', cube.getCube()[RubiksCube.TOP][1][2]);
            assertEquals('Y', cube.getCube()[RubiksCube.TOP][2][0]);
            assertEquals('Y', cube.getCube()[RubiksCube.TOP][2][1]);
            assertEquals('Y', cube.getCube()[RubiksCube.TOP][2][2]);
        }
    }
}
