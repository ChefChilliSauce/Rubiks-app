public class Main {
    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        CubeMoves moves = new CubeMoves(cube);
        moves.rotateMiddleLeftRightClockwise();
        moves.rotateMiddleLeftRightClockwise();
        moves.rotateMiddleLeftRightClockwise();
        moves.rotateMiddleLeftRightClockwise();
        moves.rotateMiddleLeftRightCounterClockwise();
        moves.rotateMiddleLeftRightCounterClockwise();
        moves.rotateMiddleLeftRightCounterClockwise();
        moves.rotateMiddleLeftRightCounterClockwise();
        cube.DisplayCube();
    }
}