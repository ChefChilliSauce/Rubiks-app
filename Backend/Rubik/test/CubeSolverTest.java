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
                {"S" , "A" , "M" , "P" , "L" , "E"} //All test cases in the text file in test folder
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
                {"S" , "A" , "M" , "P" , "L" , "E"}
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
                {"S" , "A" , "M" , "P" , "L" , "E"}
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
                {"S" , "A" , "M" , "P" , "L" , "E"}
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
