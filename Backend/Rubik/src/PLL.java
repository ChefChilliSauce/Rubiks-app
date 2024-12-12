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
                cubeMoves.performAlgoPLL(new String[]{"algoDiagonal"});
                alignCorners();
                break;
            case "AdjacentOrientation":
                cubeMoves.performAlgoPLL(new String[]{"algoAdjacent"});
                alignCorners();
                break;
        }
    }

    private String getOrientationCorners(){//method to return the orientation of corner pieces
        if(isAligned()){
            return "aligned";
        } else if (isDiagonal()) {
            return "DiagonalOrientation";
        } else if (isAdjacentFront()) {
            cubeMoves.performMoves(new String[]{"T"});
            return "AdjacentOrientation";
        } else if (isAdjacentLeft()) {
            return "AdjacentOrientation";
        } else if (isAdjacentBack()) {
            cubeMoves.performMoves(new String[]{"T`"});
            return "AdjacentOrientation";
        } else if (isAdjacentRight()) {
            cubeMoves.performMoves(new String[]{"T" , "T"});
            return "AdjacentOrientation";
        }
        return "misaligned";
    }

    private boolean isAligned(){//checks whether the corners are already aligned
        return (cube[1][0][0] == cube[1][0][2]) && (cube[3][0][0] == cube[3][0][2]);
    }

    private boolean isDiagonal(){//checks for the PLL conditions where the correct corners are diagonally opposite to each other
        return ((cube[1][0][0] != cube[1][0][2]) && (cube[3][0][0] != cube[3][0][2])) && ((cube[2][0][0] != cube[2][0][2]) && (cube[4][0][0] != cube[4][0][2]));
    }

//    private boolean isAdjacent(){
//        System.out.println(cube[2][0][0]);
//        System.out.println(cube[2][0][2]);
//        System.out.println(cube[1][0][2]);
//        System.out.println(cube[3][0][0]);
//        if(cube[1][0][0] == cube[1][0][2] && cube[2][0][0] == cube[4][0][2]){
//            //cubeMoves.performMoves(new String[]{"T"});
//            return true;
//        } else if ((cube[2][0][0] == cube[2][0][2] ) && (cube[1][0][2] == cube[3][0][0])) {
//            return true;
//        } else if ((cube[3][0][0] == cube[3][0][2] ) && (cube[2][0][2] == cube[4][0][0])) {
//            //cubeMoves.performMoves(new String[]{"T`"});
//        } else if ((cube[4][0][0] == cube[4][0][2] ) && (cube[1][0][0] == cube[3][0][2])) {
//            //cubeMoves.performMoves(new String[]{"T" , "T"});
//            return true;
//        }
//        return  false;
//    }
    private boolean isAdjacentFront(){
        return(cube[1][0][0] == cube[1][0][2] && cube[2][0][0] == cube[4][0][2]);
    }
    private boolean isAdjacentLeft(){
        return(cube[2][0][0] == cube[2][0][2] ) && (cube[1][0][2] == cube[3][0][0]);
    }
    private boolean isAdjacentBack(){
        return(cube[3][0][0] == cube[3][0][2] ) && (cube[2][0][2] == cube[4][0][0]);
    }
    private boolean isAdjacentRight(){
        return(cube[4][0][0] == cube[4][0][2] ) && (cube[1][0][0] == cube[3][0][2]);
    }

    private void alignCorners(){//method to align the corners of the top layer to correct position
         if (cube[1][0][0] == 'O' && cube[1][0][2] == 'O') {//statements checks for whether [1][0][0] and [1][0][2] is Orange Red or Blue
            cubeMoves.performMoves(new String[]{"T`"});                   // no need to check for Green as in that case it is correctly oriented
        } else if (cube[1][0][0] == 'B' && cube[1][0][2] == 'B') {
            cubeMoves.performMoves(new String[]{"T" , "T"});
        } else if (cube[1][0][0] == 'R' && cube[1][0][2] == 'R') {
            cubeMoves.performMoves(new String[]{"T"});
        }
    }

    private  void  solveEdgePieces(){//method to solve the wrongly oriented edge piece by executing algorithms
        String orientationCase = getOrientationEdges();
        switch (orientationCase){
            case "aligned":
                break;
            case "orientationH":
                cubeMoves.performAlgoPLL(new String[]{"algoH"});
                alignPLL();
                break;
            case "orientationUa":
                cubeMoves.performAlgoPLL(new String[]{"algoUa"});
                alignPLL();
                break;
            case  "orientationUb":
                cubeMoves.performAlgoPLL(new String[]{"algoUb"});
                alignPLL();
                break;
            case "orientationZ":
                cubeMoves.performAlgoPLL(new String[]{"algoZ"});
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
            cubeMoves.performMoves(new String[]{"T" , "T"});
            return  true;
        }else if (cube[1][0][1] == 'O' && cube[2][0][1] == 'R' && cube[3][0][1] == 'G' && cube[4][0][1] == 'B'){
            cubeMoves.performMoves(new String[]{"T"});
            return true;
        } else if (cube[1][0][1] == 'O' && cube[2][0][1] == 'G' && cube[3][0][1] == 'B' && cube[4][0][1] == 'R') {
            return true;
        } else if (cube[1][0][1] == 'B' && cube[2][0][1] == 'G' && cube[3][0][1] == 'R' && cube[4][0][1] == 'O') {
            cubeMoves.performMoves(new String[]{"T`"});
            return  true;
        }
        return false;
    }

    private boolean isUb(){//method to solve a PLL condition where one edge piece is in places but rest are in cyclic order
        if (cube[1][0][1] == 'G' && cube[2][0][1] == 'B' && cube[3][0][1] == 'O' && cube[4][0][1] == 'R'){
            cubeMoves.performMoves(new String[]{"T" , "T"});
            return  true;
        }else if ((cube[1][0][1] == 'B') && (cube[2][0][1] == 'R') && (cube[3][0][1] == 'O') && (cube[4][0][1] == 'G')){
            cubeMoves.performMoves(new String[]{"T"});
            return true;
        } else if (cube[1][0][1] == 'R' && cube[2][0][1] == 'O' && cube[3][0][1] == 'B' && cube[4][0][1] == 'G') {
            return true;
        } else if (cube[1][0][1] == 'R' && cube[2][0][1] == 'B' && cube[3][0][1] == 'G' && cube[4][0][1] == 'O') {
            cubeMoves.performMoves(new String[]{"T`"});
            return  true;
        }
        return false;
    }

    private boolean isZ(){ //method to solve the PLL edge pieces which are adjacently opposite to each other
        if(cube[1][0][1] == 'R' &&cube[2][0][1] == 'G' && cube[3][0][1] == 'O' && cube[4][0][1] == 'B'){
            cubeMoves.performMoves(new String[]{"T"});
            return  true;
        } else return cube[1][0][1] == 'O' && cube[2][0][1] == 'B' && cube[3][0][1] == 'R' && cube[4][0][1] == 'G';
    }

    private void alignPLL(){ //A method  to align the orientation of top layer after solve
        if (cube[1][0][0] == 'O' && cube[1][0][1] == 'O' && cube[1][0][2] == 'O') { //statements checks for [1][0][0] - [1][0][2] is whether Orange Red or Blue
            cubeMoves.performMoves(new String[]{"T`"});                                                          // no need to check for green as in the case it will be already correctly oriented
        } else if (cube[1][0][0] == 'B' && cube[1][0][1] == 'B' && cube[1][0][2] == 'B') {
            cubeMoves.performMoves(new String[]{"T" , "T"});
        } else if (cube[1][0][0] == 'R' && cube[1][0][1] == 'R' && cube[1][0][2] == 'R') {
            cubeMoves.performMoves(new String[]{"T"});
        }
    }
}