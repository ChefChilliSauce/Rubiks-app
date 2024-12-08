public class PLL {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;

    public PLL(RubiksCube rubiksCube) {
        // Directly assign the cube array to a member variable
        this.cube = rubiksCube.getCube();
        // Initialize CubeMoves with the RubiksCube instance
        this.cubeMoves = new CubeMoves(rubiksCube);
    }
    public void solvePLL(){
        solveTopCorners();
        solveEdgePieces();
    }
    private void  solveTopCorners(){
        String orientationCase = getOrientationCorners();
        switch (orientationCase){
            case "aligned":
                alignCorners();
                break;
            case "DiagonalOrientation":
                performMoves(new String[]{"algoDiagonal"});
                alignCorners();
                break;
            case "AdjacentOrientation":
                performMoves(new String[]{"algoAdjacent"});
                alignCorners();
                break;
        }
    }
    private String getOrientationCorners(){
        if(isAligned()){
            return "aligned";
        } else if (isDiagonal()) {
            return "DiagonalOrientation";
        } else if (isAdjacent()) {
            return "AdjacentOrientation";
        }
        return "misaligned";
    }
    private boolean isAligned(){
        return (cube[1][0][0] == cube[1][0][2]) && (cube[3][0][0] == cube[3][0][2]);
    }
    private boolean isDiagonal(){
        return (cube[1][0][0] != cube[1][0][2]) && (cube[3][0][0] != cube[3][0][2]);
    }
    private boolean isAdjacent(){
        if((cube[2][0][0] == cube[2][0][2] ) && (cube[4][0][0] != cube[4][0][2])){
            return true;
        } else if ((cube[3][0][0] == cube[3][0][2] ) && (cube[1][0][0] != cube[1][0][2])) {
            performMoves(new String[]{"T`"});
            return true;
        } else if ((cube[4][0][0] == cube[4][0][2] ) && (cube[2][0][0] != cube[2][0][2])) {
            performMoves(new String[]{"T" , "T"});
        } else if ((cube[1][0][0] == cube[1][0][2] ) && (cube[3][0][0] != cube[3][0][2])) {
            performMoves(new String[]{"T"});
            return true;
        }
        return  false;
    }
    private void alignCorners(){
        if(cube[1][0][0] == 'G' && cube[1][0][2] == 'G'){
        } else if (cube[1][0][0] == 'O' && cube[1][0][2] == 'O') {
            performMoves(new String[]{"T`"});
        } else if (cube[1][0][0] == 'B' && cube[1][0][2] == 'B') {
            performMoves(new String[]{"T" , "T"});
        } else if (cube[1][0][0] == 'R' && cube[1][0][2] == 'R') {
            performMoves(new String[]{"T"});
        }
    }
    private  void  solveEdgePieces(){
        String orientationCase = getOrientationEdges();
        switch (orientationCase){
            case "aligned":
                break;
            case "orientationH":
                performMoves(new String[]{"algoH"});
                alignPLL();
                break;
            case "orientationUa":
                performMoves(new String[]{"algoUa"});
                alignPLL();
                break;
            case  "orientationUb":
                performMoves(new String[]{"algoUb"});
                alignPLL();
            case "orientationZ":
                performMoves(new String[]{"algoZ"});
                alignPLL();
        }
    }
    private String  getOrientationEdges(){
        if(isEdgeAligned()){
            return "aligned";
        }
        else if(isH()){
            return "orientationH";
        } else if (isUa()) {
            return "orientationUa";
        } else if (isUb()) {
            return "orientationUb";
        } else if (isZ()) {
            return "orientationZ";
        }
        return "misaligned";
    }
    private boolean isEdgeAligned(){
        return ((cube[1][0][1] == 'G' ) && (cube[3][0][1] == 'B' ) && (cube[2][0][1] == 'R' ) && (cube[4][0][1] == 'O' ));
    }
    private boolean isH(){
        return ((cube[1][0][1] == 'B' ) && (cube[3][0][1] == 'G' ) && (cube[2][0][1] == 'O' ) && (cube[4][0][1] == 'R' ));
    }
    private boolean isUa(){
        if (cube[1][0][1] == 'G' && cube[2][0][1] == 'O' && cube[3][0][1] == 'R' && cube[4][0][1] == 'B'){
            performMoves(new String[]{"T" , "T"});
            return  true;
        }else if (cube[1][0][1] == 'O' && cube[2][0][1] == 'R' && cube[3][0][1] == 'G' && cube[4][0][1] == 'B'){
            performMoves(new String[]{"T"});
            return true;
        } else if (cube[1][0][1] == 'O' && cube[2][0][1] == 'G' && cube[3][0][1] == 'B' && cube[4][0][1] == 'R') {
            return true;
        } else if (cube[1][0][1] == 'B' && cube[2][0][1] == 'G' && cube[3][0][1] == 'R' && cube[4][0][1] == 'O') {
            performMoves(new String[]{"T`"});
            return  true;
        }
        return false;
    }
    private boolean isUb(){
        if (cube[1][0][1] == 'G' && cube[2][0][1] == 'B' && cube[3][0][1] == 'O' && cube[4][0][1] == 'R'){
            performMoves(new String[]{"T" , "T"});
            return  true;
        }else if (cube[1][0][1] == 'B' && cube[2][0][1] == 'R' && cube[3][0][1] == 'O' && cube[4][0][1] == 'G'){
            performMoves(new String[]{"T"});
            return true;
        } else if (cube[1][0][1] == 'R' && cube[2][0][1] == 'O' && cube[3][0][1] == 'B' && cube[4][0][1] == 'G') {
            return true;
        } else if (cube[1][0][1] == 'R' && cube[2][0][1] == 'B' && cube[3][0][1] == 'G' && cube[4][0][1] == 'O') {
            performMoves(new String[]{"T`"});
            return  true;
        }
        return false;
    }
    private boolean isZ(){
        if(cube[1][0][1] == 'R' &&cube[2][0][1] == 'G' && cube[3][0][1] == 'O' && cube[4][0][1] == 'B'){
            performMoves(new String[]{"T"});
            return  true;
        } else return cube[1][0][1] == 'O' && cube[2][0][1] == 'B' && cube[3][0][1] == 'R' && cube[4][0][1] == 'G';
    }
    private void alignPLL(){
        if(cube[1][0][0] == 'G' && cube[1][0][1] == 'G' && cube[1][0][2] == 'G'){
        } else if (cube[1][0][0] == 'O' && cube[1][0][1] == 'O' && cube[1][0][2] == 'O') {
            performMoves(new String[]{"T`"});
        } else if (cube[1][0][0] == 'B' && cube[1][0][1] == 'B' && cube[1][0][2] == 'B') {
            performMoves(new String[]{"T" , "T"});
        } else if (cube[1][0][0] == 'R' && cube[1][0][1] == 'R' && cube[1][0][2] == 'R') {
            performMoves(new String[]{"T"});
        }
    }
    private void performMoves(String[] moves) {
        for (String move : moves) {
            switch (move) {
                case "T":
                    cubeMoves.rotateTopClockwise();
                    break;
                case "T`":
                    cubeMoves.rotateTopCounterClockwise();
                    break;
                case "F":
                    cubeMoves.rotateFrontClockwise();
                    break;
                case "F`":
                    cubeMoves.rotateFrontCounterClockwise();
                    break;
                case "L":
                    cubeMoves.rotateLeftClockwise();
                    break;
                case "L`":
                    cubeMoves.rotateLeftCounterClockwise();
                    break;
                case "Ba":
                    cubeMoves.rotateBackClockwise();
                    break;
                case "Ba`":
                    cubeMoves.rotateBackCounterClockwise();
                    break;
                case "R":
                    cubeMoves.rotateRightClockwise();
                    break;
                case "R`":
                    cubeMoves.rotateRightCounterClockwise();
                    break;
                case "B":
                    cubeMoves.rotateBottomClockwise();
                    break;
                case "B`":
                    cubeMoves.rotateBottomCounterClockwise();
                    break;
                case "f":
                    cubeMoves.rotateDoubleFrontClockwise();
                    break;
                case "f`":
                    cubeMoves.rotateDoubleFrontCounterClockwise();
                    break;
                case "algoDiagonal":
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    break;
                case "algoAdjacent":
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    break;
                case "algoH":
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    break;
                case "algoUa":
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateRightClockwise();
                    break;
                case "algoUb":
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    break;
                case "algoZ":
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    cubeMoves.rotateMiddleRightLeftClockwise();
                    break;
            }
        }
    }
}