import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String inputFilePath = "input.json";                                                                                                                                                                                                                  // Paths to input and output JSON files
            String outputFilePath = "Output.json";

            ObjectMapper objectMapper = new ObjectMapper();                                                                                                                                                                                 // Initialize JSON mapper

            ScrambleInput input = objectMapper.readValue(new File(inputFilePath), ScrambleInput.class);                                                                                                         // Read input scrambles from JSON

            ScrambleOutput output = new ScrambleOutput();                                                                                                                                                                                       // Create the output structure
            output.entries = new ArrayList<>();
            RubiksCube cube = new RubiksCube();                                                                                                                                                                                                     // Initialize Rubik's Cube and CubeMoves handler
            CubeMoves moves = new CubeMoves(cube);
            CFOPSolver cfopSolver = new CFOPSolver(cube);

            for (List<String> scramble : input.scrambles) {                                                                                                                                                                                          // Process each scramble
                cube.initiateCube();                                                                                                                                                                                                                                  // Reset the cube to solved state

                String[] scrambleArray = scramble.toArray(new String[0]);                                                                                                                                                                  // Convert List<String> to String[] for performMoves

                String formattedScramble = Arrays.toString(scrambleArray)                                                                                                                                                             // Format and log the scramble
                        .replace("[", "{\"")
                        .replace("]", "\"}")
                        .replace(", ", "\", \"");
                System.out.println("Formatted Scramble: " + formattedScramble);

                moves.performMoves(scrambleArray);                                                                                                                                                                                                 // Apply scramble to the cube
                char[][][] scrambledState = captureCubeState(cube);                                                                                                                                                                         // Capture scrambled state

                System.out.println("Solving Cross...");                                                                                                                                                                                                   // Solve White Cross
                cfopSolver.solveCross();
                List<String> crossMoves = cfopSolver.getCrossMoveRecord();
                char[][][] crossState = captureCubeState(cube);
                cube.DisplayCube();

                System.out.println("Solving F2L...");                                                                                                                                                                                                       // Solve F2L
                cfopSolver.solveF2L();
                List<String> f2lMoves = cfopSolver.getF2LMoveRecord();
                char[][][] f2lState = captureCubeState(cube);
                cube.DisplayCube();

                System.out.println("Solving OLL...");                                                                                                                                                                                                       // Solve OLL
                cfopSolver.solveOLL();
                List<String> ollMoves = cfopSolver.getOLLMoveRecord();
                char[][][] ollState = captureCubeState(cube);
                cube.DisplayCube();

                System.out.println("Solving PLL...");                                                                                                                                                                                                       // Solve PLL
                cfopSolver.solvePLL();
                List<String> pllMoves = cfopSolver.getPLLMoveRecord();
                char[][][] pllState = captureCubeState(cube);
                cube.DisplayCube();

                ScrambleOutputEntry entry = new ScrambleOutputEntry(                                                                                                                                                                    // Add entry to the output
                        scrambledState,
                        new CFOPStage(crossMoves, crossState),
                        new CFOPStage(f2lMoves, f2lState),
                        new CFOPStage(ollMoves, ollState),
                        new CFOPStage(pllMoves, pllState)
                );
                output.entries.add(entry);
            }
            objectMapper.writeValue(new File(outputFilePath), output);                                                                                                                                                                   // Write output to JSON
            System.out.println("Output written to: " + outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static char[][][] captureCubeState(RubiksCube cube) {
        char[][][] currentState = cube.getCube();                                                                                                                                                                                                       // Direct reference to the main cube array
        char[][][] snapshot = new char[6][3][3];
        for (int face = 0; face < 6; face++) {
            for (int row = 0; row < 3; row++) {
                System.arraycopy(currentState[face][row], 0, snapshot[face][row], 0, 3);
            }
        }
        return snapshot;
    }
}