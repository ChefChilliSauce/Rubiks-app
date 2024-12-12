public class Main {

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        CubeMoves moves = new CubeMoves(cube);
        CFOPSolver cfopSolver = new CFOPSolver(cube);
//        moves.rotateTopCounterClockwise();
//        moves.rotateLeftClockwise();
//        moves.rotateTopCounterClockwise();
//        moves.rotateBottomCounterClockwise();
//        moves.rotateFrontClockwise();
//        moves.rotateBackClockwise();
//        moves.rotateRightCounterClockwise();
//        moves.rotateBottomCounterClockwise();
//        moves.rotateBottomClockwise();
//        moves.rotateTopCounterClockwise();
//        moves.rotateBottomCounterClockwise();
//        moves.rotateRightCounterClockwise();
//        moves.rotateBottomClockwise();
//        moves.rotateBottomClockwise();
//        moves.rotateLeftCounterClockwise();
//        moves.rotateFrontCounterClockwise();
//        moves.rotateLeftCounterClockwise();
//        moves.rotateRightCounterClockwise();
//        moves.rotateBottomClockwise();
//        moves.rotateBackClockwise();
//        moves.rotateLeftCounterClockwise();
//        moves.rotateBottomClockwise();
//        moves.rotateBottomClockwise();
//        moves.rotateTopCounterClockwise();
//        moves.rotateTopCounterClockwise();
//        moves.rotateLeftClockwise();
//        moves.rotateLeftClockwise();
//        moves.rotateBackCounterClockwise();
        cfopSolver.solveCube();
        cube.DisplayCube();
    }
}