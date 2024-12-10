public class Main {
    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        CubeMoves moves = new CubeMoves(cube);
        CFOPSolver cfopSolver = new CFOPSolver(cube);
       moves. rotateTopCounterClockwise();
        moves.rotateLeftClockwise();
        moves.rotateTopCounterClockwise();
      moves.rotateBottomCounterClockwise();
       moves.rotateFrontClockwise();
     moves.rotateBackClockwise();
        moves.rotateRightCounterClockwise();
        moves.rotateBottomCounterClockwise();
        cfopSolver.solveCube();
        cube.DisplayCube();
    }
}