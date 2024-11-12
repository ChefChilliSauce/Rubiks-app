public class FLcorners {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;

    public FLcorners(RubiksCube rubiksCube) {             //getter method
        // Directly assign the cube array to a member variable
        this.cube = rubiksCube.getCube();
        // Initialize CubeMoves with the RubiksCube instance
        this.cubeMoves = new CubeMoves(rubiksCube);
    }
    private void solvewhitecorners(){
        solveCorner();
    }
private  void  solveCorner(){
        
}
    private void    performMoves(String[] moves) {//Method for all moves to improve readability of code converted to a method
        for (String move : moves) {
            switch (move) {
                case "rotateTopClockwise":
                    cubeMoves.rotateTopClockwise();
                    break;
                case "rotateTopCounterClockwise":
                    cubeMoves.rotateTopCounterClockwise();
                    break;
                case "rotateFrontClockwise":
                    cubeMoves.rotateFrontClockwise();
                    break;
                case "rotateFrontCounterClockwise":
                    cubeMoves.rotateFrontCounterClockwise();
                    break;
                case "rotateLeftClockwise":
                    cubeMoves.rotateLeftClockwise();
                    break;
                case "rotateLeftCounterClockwise":
                    cubeMoves.rotateLeftCounterClockwise();
                    break;
                case "rotateBackClockwise":
                    cubeMoves.rotateBackClockwise();
                    break;
                case "rotateBackCounterClockwise":
                    cubeMoves.rotateBackCounterClockwise();
                    break;
                case "rotateRightClockwise":
                    cubeMoves.rotateRightClockwise();
                    break;
                case "rotateRightCounterClockwise":
                    cubeMoves.rotateRightCounterClockwise();
                    break;
                case "rotateBottomClockwise":
                    cubeMoves.rotateBottomClockwise();
                    break;
                case "rotateBottomCounterClockwise":
                    cubeMoves.rotateBottomCounterClockwise();
                    break;
            }
        }
    }
}
