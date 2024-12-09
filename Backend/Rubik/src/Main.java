public class Main {
    public static void main(String[] args) {
        RubiksCube rubiksCube = new RubiksCube();
        RubiksCube cube = new RubiksCube();
        CubeMoves moves = new CubeMoves(cube);
        CFOPSolver cfopSolver = new CFOPSolver(rubiksCube);
        moves.rotateLeftCounterClockwise();
        moves.rotateRightClockwise();
        moves.rotateTopClockwise();
        moves.rotateBottomCounterClockwise();
        //moves.rotateBackClockwise();

//       moves. rotateTopCounterClockwise();
//        moves.rotateLeftClockwise();
//        moves.rotateTopCounterClockwise();
//      moves.rotateBottomCounterClockwise();
//       moves.rotateFrontClockwise();
//      moves.rotateBackClockwise();
//        moves.rotateRightCounterClockwise();
//        moves.rotateBottomCounterClockwise();
//        moves.rotateTopClockwise();
//        moves.rotateFrontClockwise();
//        moves.rotateLeftCounterClockwise();

        cube.DisplayCube();
    }
}