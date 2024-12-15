import java.util.List;
public class PLL {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;
    private final List<String> moveRecord;

    public PLL(RubiksCube rubiksCube, List<String> moveRecord) {
        this.cube = rubiksCube.getCube();                                                                                                                                                           // Directly assign the cube array to a member variable
        this.cubeMoves = new CubeMoves(rubiksCube);                                                                                                                                   // Initialize CubeMoves with the RubiksCube instance
        this.moveRecord = moveRecord;
    }

    public void solvePLL(){
        solveTopCorners();                                                                                                                                                                                        //method to PLL
        solveEdgePieces();
    }

    private void  solveTopCorners(){
        String orientationCase = getOrientationCorners();                                                                                                                                    //method to solve the wrongly oriented corner piece by executing algorithms
        switch (orientationCase){
            case "aligned":
                alignCorners();
                break;
            case "DiagonalOrientation":
                String[] diagonalSequence = {"F", "R", "T`", "R`", "T`", "R", "T", "R`", "F`", "R", "T", "R`", "T`", "R`", "F", "R", "F`"};
                performAndLogMoves(diagonalSequence);
                alignCorners();
                break;
            case "AdjacentOrientation":
                String[] adjSequence = new String[]{"R", "T", "R`", "T`", "R`", "F", "R", "R", "T`", "R`", "T`", "R", "T", "R`", "F`"};
                performAndLogMoves(adjSequence);
                alignCorners();
                break;
        }
    }

    private String getOrientationCorners(){
        if(isAligned()){                                                                                                                                                                                                    //method to return the orientation of corner pieces
            return "aligned";
        } else if (isDiagonal()) {
            return "DiagonalOrientation";
        } else if (isAdjacentFront()) {
            performAndLogMoves(new String[]{"T"});
            return "AdjacentOrientation";
        } else if (isAdjacentLeft()) {
            return "AdjacentOrientation";
        } else if (isAdjacentBack()) {
            performAndLogMoves(new String[]{"T`"});
            return "AdjacentOrientation";
        } else if (isAdjacentRight()) {
            performAndLogMoves(new String[]{"T" , "T"});
            return "AdjacentOrientation";
        }
        return "misaligned";
    }

    private boolean isAligned(){
        return (cube[1][0][0] == cube[1][0][2]) && (cube[3][0][0] == cube[3][0][2]);                                                                                                       //checks whether the corners are already aligned
    }

    private boolean isDiagonal(){
        return ((cube[1][0][0] != cube[1][0][2]) && (cube[3][0][0] != cube[3][0][2])) && ((cube[2][0][0] != cube[2][0][2]) && (cube[4][0][0] != cube[4][0][2]));                            //checks for the PLL conditions where the correct corners are diagonally opposite to each other
    }

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

    private void alignCorners(){
         if (cube[1][0][0] == 'O' && cube[1][0][2] == 'O') {                                                                                        //method to align the corners of the top layer to correct position
            performAndLogMoves(new String[]{"T`"});                                                                                            //statements checks for whether [1][0][0] and [1][0][2] is Orange Red or Blue
        } else if (cube[1][0][0] == 'B' && cube[1][0][2] == 'B') {                                                                                // no need to check for Green as in that case it is correctly oriented
            performAndLogMoves(new String[]{"T" , "T"});
        } else if (cube[1][0][0] == 'R' && cube[1][0][2] == 'R') {
            performAndLogMoves(new String[]{"T"});
        }
    }

    private  void  solveEdgePieces(){
        String orientationCase = getOrientationEdges();                                                                                          //method to solve the wrongly oriented edge piece by executing algorithms
        switch (orientationCase){
            case "aligned":
                break;
            case "orientationH":
                String[] hSequence = {"M" , "M" , "T" , "M" , "M" , "T" , "T" , "M" , "M" , "T" , "M" , "M"};
                performAndLogMoves(hSequence);
                alignPLL();
                break;
            case "orientationUa":
                String[]uaSequence = {"R" , "T`" , "R" , "T" , "R" , "T" , "R" , "T`" , "R`" , "T`" , "R" , "R"};
                performAndLogMoves(uaSequence);
                alignPLL();
                break;
            case  "orientationUb":
                String[] ubSequence = {"R" , "R" , "T" , "R" , "T" , "R`" , "T`" , "R`" , "T`" , "R`" , "T" , "R`"};
                performAndLogMoves(ubSequence);
                alignPLL();
                break;
            case "orientationZ":
                String[] zSequence =  {"M" , "T`" , "M" , "M" , "T`" , "M" , "M" , "T`" , "M" , "T" , "T" , "M" , "M"};
                performAndLogMoves(zSequence);
                alignPLL();
                break;
        }
    }

    private String  getOrientationEdges(){
        if(isEdgeAligned()){                                                                                                                                                        //method to return the orientation of edge pieces
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

    private boolean isEdgeAligned(){
        return ((cube[1][0][1] == 'G' ) && (cube[3][0][1] == 'B' ) && (cube[2][0][1] == 'R' ) && (cube[4][0][1] == 'O' ));                                              //method to check if the edge pieces are already aligned after fixing the PLL corners
    }

    private boolean isH(){
        return ((cube[1][0][1] == 'B' ) && (cube[3][0][1] == 'G' ) && (cube[2][0][1] == 'O' ) && (cube[4][0][1] == 'R' ));                      //method to solve a PLL condition where the edge pieces are in the opposite face eg: GBG, BGB, ORO, ROR
    }

    private boolean isUa(){
        if (cube[1][0][1] == 'G' && cube[2][0][1] == 'O' && cube[3][0][1] == 'R' && cube[4][0][1] == 'B'){                                      //method to solve a PLL condition where one edge piece is in places but rest are in acyclic order
            performAndLogMoves(new String[]{"T" , "T"});
            return  true;
        }else if (cube[1][0][1] == 'O' && cube[2][0][1] == 'R' && cube[3][0][1] == 'G' && cube[4][0][1] == 'B'){
            performAndLogMoves(new String[]{"T"});
            return true;
        } else if (cube[1][0][1] == 'O' && cube[2][0][1] == 'G' && cube[3][0][1] == 'B' && cube[4][0][1] == 'R') {
            return true;
        } else if (cube[1][0][1] == 'B' && cube[2][0][1] == 'G' && cube[3][0][1] == 'R' && cube[4][0][1] == 'O') {
            performAndLogMoves(new String[]{"T`"});
            return  true;
        }
        return false;
    }

    private boolean isUb(){
        if (cube[1][0][1] == 'G' && cube[2][0][1] == 'B' && cube[3][0][1] == 'O' && cube[4][0][1] == 'R'){                              //method to solve a PLL condition where one edge piece is in places but rest are in cyclic order
            performAndLogMoves(new String[]{"T" , "T"});
            return  true;
        }else if ((cube[1][0][1] == 'B') && (cube[2][0][1] == 'R') && (cube[3][0][1] == 'O') && (cube[4][0][1] == 'G')){
            performAndLogMoves(new String[]{"T"});
            return true;
        } else if (cube[1][0][1] == 'R' && cube[2][0][1] == 'O' && cube[3][0][1] == 'B' && cube[4][0][1] == 'G') {
            return true;
        } else if (cube[1][0][1] == 'R' && cube[2][0][1] == 'B' && cube[3][0][1] == 'G' && cube[4][0][1] == 'O') {
            performAndLogMoves(new String[]{"T`"});
            return  true;
        }
        return false;
    }

    private boolean isZ(){
        if(cube[1][0][1] == 'R' &&cube[2][0][1] == 'G' && cube[3][0][1] == 'O' && cube[4][0][1] == 'B'){                                //method to solve the PLL edge pieces which are adjacently opposite to each other
            performAndLogMoves(new String[]{"T"});
            return  true;
        } else return cube[1][0][1] == 'O' && cube[2][0][1] == 'B' && cube[3][0][1] == 'R' && cube[4][0][1] == 'G';
    }

    private void alignPLL(){
        if (cube[1][0][0] == 'O' && cube[1][0][1] == 'O' && cube[1][0][2] == 'O') {                                                                                      //A method  to align the orientation of top layer after solve
            performAndLogMoves(new String[]{"T`"});                                                                                                                                  //statements checks for [1][0][0] - [1][0][2] is whether Orange Red or Blue
        } else if (cube[1][0][0] == 'B' && cube[1][0][1] == 'B' && cube[1][0][2] == 'B') {                                                                           // no need to check for green as in the case it will be already correctly oriented
            performAndLogMoves(new String[]{"T" , "T"});
        } else if (cube[1][0][0] == 'R' && cube[1][0][1] == 'R' && cube[1][0][2] == 'R') {
            performAndLogMoves(new String[]{"T"});
        }
    }
    private void performAndLogMoves(String[] moves) {
        cubeMoves.performMoves(moves);                                                                                                                                                        //perform moves
        moveRecord.addAll(List.of(moves));                                                                                                                                                      // Log moves into the PLL list
    }
}