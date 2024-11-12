public class F2L {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;

    public F2L(RubiksCube rubiksCube) {             //getter method
        // Directly assign the cube array to a member variable
        this.cube = rubiksCube.getCube();
        // Initialize CubeMoves with the RubiksCube instance
        this.cubeMoves = new CubeMoves(rubiksCube);
    }
}
