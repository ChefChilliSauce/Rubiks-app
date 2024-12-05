public class Main {
    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        CubeMoves moves = new CubeMoves(cube);
        moves.rotateDoubleFrontClockwise();
        moves.rotateDoubleFrontClockwise();
        moves.rotateDoubleFrontClockwise();
        moves.rotateDoubleFrontClockwise();
        moves.rotateDoubleFrontCounterClockwise();
        moves.rotateDoubleFrontCounterClockwise();
        moves.rotateDoubleFrontCounterClockwise();
        moves.rotateDoubleFrontCounterClockwise();
        moves.rotateDoubleBackClockwise();
        moves.rotateDoubleBackClockwise();
        moves.rotateDoubleBackClockwise();
        moves.rotateDoubleBackClockwise();
        moves.rotateDoubleBackCounterClockwise();
        moves.rotateDoubleBackCounterClockwise();
        moves.rotateDoubleBackCounterClockwise();
        moves.rotateDoubleBackCounterClockwise();
        moves.rotateDoubleRightClockwise();
        moves.rotateDoubleRightClockwise();
        moves.rotateDoubleRightClockwise();
        moves.rotateDoubleRightClockwise();
        moves.rotateDoubleRightCounterClockwise();
        moves.rotateDoubleRightCounterClockwise();
        moves.rotateDoubleRightCounterClockwise();
        moves.rotateDoubleRightCounterClockwise();
        moves.rotateDoubleLeftClockwise();
        moves.rotateDoubleLeftClockwise();
        moves.rotateDoubleLeftClockwise();
        moves.rotateDoubleLeftClockwise();
        moves.rotateDoubleLeftCounterClockwise();
        moves.rotateDoubleLeftCounterClockwise();
        moves.rotateDoubleLeftCounterClockwise();
        moves.rotateDoubleLeftCounterClockwise();
        cube.DisplayCube();
    }
}