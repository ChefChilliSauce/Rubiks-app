public class PLL {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;

    public PLL(RubiksCube rubiksCube) {
        this.cube = rubiksCube.getCube();                                      // Directly assign the cube array to a member variable
        this.cubeMoves = new CubeMoves(rubiksCube);             // Initialize CubeMoves with the RubiksCube instance
    }

    public void solvePLL(){//method to PLL
        solveTopCorners();
        solveEdgePieces();
    }

    private void  solveTopCorners(){//method to solve the wrongly oriented corner piece by executing algorithms
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

    private String getOrientationCorners(){//method to return the orientation of corner pieces
        if(isAligned()){
            return "aligned";
        } else if (isDiagonal()) {
            return "DiagonalOrientation";
        } else if (isAdjacent()) {
            return "AdjacentOrientation";
        }
        return "misaligned";
    }

    private boolean isAligned(){//checks whether the corners are already aligned
        return (cube[1][0][0] == cube[1][0][2]) && (cube[3][0][0] == cube[3][0][2]);
    }

    private boolean isDiagonal(){//checks for the PLL conditions where the correct corners are diagonally opposite to each other
        return (cube[1][0][0] != cube[1][0][2]) && (cube[3][0][0] != cube[3][0][2]);
    }

    private boolean isAdjacent(){//checks for the PLL conditions where the correct corners are adjacent to each other
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

    private void alignCorners(){//method to align the corners of the top layer to correct position
         if (cube[1][0][0] == 'O' && cube[1][0][2] == 'O') {//statements checks for whether [1][0][0] and [1][0][2] is Orange Red or Blue
            performMoves(new String[]{"T`"});                   // no need to check for Green as in that case it is correctly oriented
        } else if (cube[1][0][0] == 'B' && cube[1][0][2] == 'B') {
            performMoves(new String[]{"T" , "T"});
        } else if (cube[1][0][0] == 'R' && cube[1][0][2] == 'R') {
            performMoves(new String[]{"T"});
        }
    }

    private  void  solveEdgePieces(){//method to solve the wrongly oriented edge piece by executing algorithms
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
                break;
            case "orientationZ":
                performMoves(new String[]{"algoZ"});
                alignPLL();
                break;
        }
    }

    private String  getOrientationEdges(){//method to return the orientation of edge pieces
        if(isEdgeAligned()){
            return "aligned";
        } else if(isH()){
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

    private boolean isEdgeAligned(){//method to check if the edge pieces are already aligned after fixing the PLL corners
        return ((cube[1][0][1] == 'G' ) && (cube[3][0][1] == 'B' ) && (cube[2][0][1] == 'R' ) && (cube[4][0][1] == 'O' ));
    }

    private boolean isH(){//method to solve a PLL condition where the edge pieces are in the opposite face eg: GBG, BGB, ORO, ROR
        return ((cube[1][0][1] == 'B' ) && (cube[3][0][1] == 'G' ) && (cube[2][0][1] == 'O' ) && (cube[4][0][1] == 'R' ));
    }

    private boolean isUa(){//method to solve a PLL condition where one edge piece is in places but rest are in acyclic order
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

    private boolean isUb(){//method to solve a PLL condition where one edge piece is in places but rest are in cyclic order
        if (cube[1][0][1] == 'G' && cube[2][0][1] == 'B' && cube[3][0][1] == 'O' && cube[4][0][1] == 'R'){
            performMoves(new String[]{"T" , "T"});
            return  true;
        }else if ((cube[1][0][1] == 'B') && (cube[2][0][1] == 'R') && (cube[3][0][1] == 'O') && (cube[4][0][1] == 'G')){
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

    private boolean isZ(){ //method to solve the PLL edge pieces which are adjacently opposite to each other
        if(cube[1][0][1] == 'R' &&cube[2][0][1] == 'G' && cube[3][0][1] == 'O' && cube[4][0][1] == 'B'){
            performMoves(new String[]{"T"});
            return  true;
        } else return cube[1][0][1] == 'O' && cube[2][0][1] == 'B' && cube[3][0][1] == 'R' && cube[4][0][1] == 'G';
    }

    private void alignPLL(){ //A method  to align the orientation of top layer after solve
        if (cube[1][0][0] == 'O' && cube[1][0][1] == 'O' && cube[1][0][2] == 'O') { //statements checks for [1][0][0] - [1][0][2] is whether Orange Red or Blue
            performMoves(new String[]{"T`"});                                                          // no need to check for green as in the case it will be already correctly oriented
        } else if (cube[1][0][0] == 'B' && cube[1][0][1] == 'B' && cube[1][0][2] == 'B') {
            performMoves(new String[]{"T" , "T"});
        } else if (cube[1][0][0] == 'R' && cube[1][0][1] == 'R' && cube[1][0][2] == 'R') {
            performMoves(new String[]{"T"});
        }
    }
    private void performMoves(String[] moves) {//methods to performs moves by calling this function and passing the string value
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