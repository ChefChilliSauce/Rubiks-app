import java.util.List;

public class CrossSolver {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;
    private final List<String> moveRecord;

    public CrossSolver(RubiksCube rubiksCube, List<String> moveRecord) {             //getter methods
        this.cube = rubiksCube.getCube();
        this.cubeMoves = new CubeMoves(rubiksCube);
        this.moveRecord = moveRecord;
    }

    public void solveWhiteCross(){
        solveGreenWhiteEdge();                                                                                                      //method to solve cross
        solveRedWhiteEdge();
        solveBlueWhiteEdge();
        solveOrangeWhiteEdge();
    }

    private  void solveGreenWhiteEdge(){
        if(cube[1][2][1] == 'G' && cube[5][0][1] == 'W'){                                                                       //method to solve green and white edge 1,2,1 and 5,0,1
            return;
        }
        String coordinates = getEdgeCoordinates('G');
        switch (coordinates){
            case "TopAndFront":
                String[] tfSequence = {"T`" , "R`" , "F" , "R"};
                performAndLogMoves(tfSequence);
                break;
            case "TopAndLeft":
                String[] tlSequence = {"T" , "T" , "R`" , "F" , "R"};
                performAndLogMoves(tlSequence);
                break;
            case "TopAndBack":
                String[] tbSequence = {"T" , "R`" , "F" , "R"};
                performAndLogMoves(tbSequence);
                break;
            case "TopAndRight":
                String[] trSequence = {"R`" , "F" , "R"};
                performAndLogMoves(trSequence);
                break;
            case "BottomAndFront":
                String[] bfSequence = {"F" , "F" , "T`" , "R`" , "F" , "R"};
                performAndLogMoves(bfSequence);
                break;
            case "BottomAndLeft":
                String[] blSequence = {"L" , "L" , "T" , "T" , "R`" , "F" , "R"};
                performAndLogMoves(blSequence);
                break;
            case "BottomAndBack":
                String[] bbSequence = {"Ba" , "Ba" , "T" , "R`" , "F" , "R"};
                performAndLogMoves(bbSequence);
                break;
            case "BottomAndRight":
                String[] brSequence = {"R" , "F"};
                performAndLogMoves(brSequence);
                break;
            case "FrontAndLeft":
            case"WhiteLeftAndFront":
                String[] flSequence = {"F`"};
                performAndLogMoves(flSequence);
                break;
            case "FrontAndRight":
            case "WhiteRightAndFront":
                String[] frSequence = {"F"};
                performAndLogMoves(frSequence);
                break;
            case "LeftAndBack":
            case"WhiteBackAndLeft":
                String[] lbSequence = {"L" , "L" , "F" , "L" , "L" , "T`" , "R`" , "F" , "R"};
                performAndLogMoves(lbSequence);
                break;
            case "LeftAndFront":
            case "WhiteFrontAndLeft":
                String[] lfSequence = {"F" , "T`" , "R`" , "F" , "R"};
                performAndLogMoves(lfSequence);
                break;
            case "BackAndRight":
            case "WhiteRightAndBack":
                String[] wrbSequence = {"R`" , "T`" , "R" , "T" , "R`" , "F" , "R"};
                performAndLogMoves(wrbSequence);
                break;
            case "BackAndLeft":
            case"WhiteLeftAndBack":
                String[] wlbSequence = {"L" , "T" , "L`" , "T" , "R`" , "F" , "R"};
                performAndLogMoves(wlbSequence);
                break;
            case "RightAndBack":
            case"WhiteBackAndRight":
                String[] rbSequence = {"Ba" , "T" , "Ba`" , "R`" , "F" , "R"};
                performAndLogMoves(rbSequence);
                break;
            case "RightAndFront":
            case "WhiteFrontAndRight":
                String[] rfSequence = {"F`" , "T`" , "R`" , "F" , "R"};
                performAndLogMoves(rfSequence);
                break;
            case "WhiteTopAndFront":
                String[] wtfSequence = {"F" , "F" };
                performAndLogMoves(wtfSequence);
                break;
            case "WhiteTopAndLeft":
                String[] wtlSequence = {"T`" , "F" , "F"};
                performAndLogMoves(wtlSequence);
                break;
            case "WhiteTopAndBack":
                String[] wtbSequence = {"T" , "T" , "F" , "F"};
                performAndLogMoves(wtbSequence);
                break;
            case "WhiteTopAndRight":
                String[] wtrSequence = {"T" , "F" , "F"};
                performAndLogMoves(wtrSequence);
                break;
            case "WhiteBottomAndLeft":
                String[] wblSequence = {"L" , "L" , "T`" , "F" , "F"};
                performAndLogMoves(wblSequence);
                break;
            case "WhiteBottomAndBack":
                String[] wbbSequence = {"Ba" , "Ba" , "T" , "T" , "F" , "F"};
                performAndLogMoves(wbbSequence);
                break;
            case "WhiteBottomAndRight":
                String[] wbrSequence = {"R" , "R" , "T" , "F" , "F"};
                performAndLogMoves(wbrSequence);
                break;
        }
    }

    private  void solveRedWhiteEdge() {
        if (cube[2][2][1] == 'R' && cube[5][1][0] == 'W') {                                                                                                                                 //method to solve red and white edge 2,2,1 and 5,1,0
            return;
        }
        String coordinates = getEdgeCoordinates('R');
        switch (coordinates){
            case "TopAndFront":
                String[] tfSequence = {"F`" , "L" , "F" };
                performAndLogMoves(tfSequence);
                break;
            case "TopAndLeft":
                String[] tlSequence = {"T`" ,  "F`" , "L" , "F"};
                performAndLogMoves(tlSequence);
                break;
            case "TopAndBack":
                String[] tbSequence = {"T" , "T" ,  "F`" , "L" , "F"};
                performAndLogMoves(tbSequence);
                break;
            case "TopAndRight":
                String[] trSequence = {"T" ,  "F`" , "L" , "F"};
                performAndLogMoves(trSequence);
                break;
            case "BottomAndLeft":
                String[] blSequence = {"L" , "L" , "T`" ,  "F`" , "L" , "F"};
                performAndLogMoves(blSequence);
                break;
            case "BottomAndBack":
                String[] bbSequence = {"Ba" , "Ba" , "T" , "T" ,  "F`" , "L" , "F"};
                performAndLogMoves(bbSequence);
                break;
            case "BottomAndRight":
                String[] brSequence = {"R" , "R" , "T" ,  "F`" , "L" , "F"};
                performAndLogMoves(brSequence);
                break;
            case "FrontAndLeft":
            case"WhiteLeftAndFront":
                String[] flSequence = {"L`" , "T`" ,  "F`" , "L" , "F"};
                performAndLogMoves(flSequence);
                break;
            case "FrontAndRight":
            case "WhiteRightAndFront":
                String[] frSequence = {"R" ,"T" ,  "R`" , "F`" , "L" , "F"};
                performAndLogMoves(frSequence);
                break;
            case "LeftAndBack":
            case"WhiteBackAndLeft":
                String[] lbSequence = {"L`"};
                performAndLogMoves(lbSequence);
                break;
            case "LeftAndFront":
            case "WhiteFrontAndLeft":
                String[] lfSequence = {"L"};
                performAndLogMoves(lfSequence);
                break;
            case "BackAndRight":
            case "WhiteRightAndBack":
                String[] wrbSequence = {"R`" , "T" ,  "F`" , "L" , "F" , "R"};
                performAndLogMoves(wrbSequence);
                break;
            case "BackAndLeft":
            case"WhiteLeftAndBack":
                String[] wlbSequence = {"L" , "T`" ,  "F`" , "L" , "F"};
                performAndLogMoves(wlbSequence);
                break;
            case "RightAndBack":
            case"WhiteBackAndRight":
                String[] rbSequence = {"Ba" , "T" , "T" , "F`" , "L" , "F" , "Ba"};
                performAndLogMoves(rbSequence);
                break;
            case "RightAndFront":
            case "WhiteFrontAndRight":
                String[] rfSequence = {"F" , "F" , "L" , "F" , "F"};
                performAndLogMoves(rfSequence);
                break;
            case "WhiteTopAndFront":
                String[] wtfSequence = {"T" , "L" , "L"};
                performAndLogMoves(wtfSequence);
                break;
            case "WhiteTopAndLeft":
                String[] wtlSequence = {"L" , "L"};
                performAndLogMoves(wtlSequence);
                break;
            case "WhiteTopAndBack":
                String[] wtbSequence = {"T`"  , "L" , "L"};
                performAndLogMoves(wtbSequence);
                break;
            case "WhiteTopAndRight":
                String[] wtrSequence = {"T" , "T" , "L" , "L"};
                performAndLogMoves(wtrSequence);
                break;
            case "WhiteBottomAndBack":
                String[] wbbSequence = {"Ba" , "Ba" , "T`" , "L" , "L"};
                performAndLogMoves(wbbSequence);
                break;
            case "WhiteBottomAndRight":
                String[] wbrSequence = {"R" , "R" , "T" , "T" , "L" , "L"};
                performAndLogMoves(wbrSequence);
                break;
        }
    }

    private  void solveBlueWhiteEdge() {
        if (cube[3][2][1] == 'B' && cube[5][2][1] == 'W') {                                                                                                                                                     //method to solve blue and white edge 3,2,1 and 5,2,1
            return;
        }
        String coordinates = getEdgeCoordinates('B');
        switch (coordinates){
            case "TopAndFront":
                String[] tfSequence = {"T`" , "R" , "Ba`" , "R`" };
                performAndLogMoves(tfSequence);
                break;
            case "TopAndLeft":
                String[] tlSequence = {"T" ,  "T" ,  "R" , "Ba`" , "R`" };
                performAndLogMoves(tlSequence);
                break;
            case "TopAndBack":
                String[] tbSequence = {"T" ,  "R" , "Ba`" , "R`" };
                performAndLogMoves(tbSequence);
                break;
            case "TopAndRight":
                String[] trSequence = { "R" , "Ba`" , "R`" };
                performAndLogMoves(trSequence);
                break;
            case "BottomAndBack":
                String[] bbSequence = {"Ba" , "Ba" , "T" ,  "R" , "Ba`" , "R`" };
                performAndLogMoves(bbSequence);
                break;
            case "BottomAndRight":
                String[] brSequence = {"R`" , "Ba`"};
                performAndLogMoves(brSequence);
                break;
            case "FrontAndLeft":
            case"WhiteLeftAndFront":
                String[] flSequence = {"F" , "T" ,  "T" , "Ba" , "Ba" , "F`"};
                performAndLogMoves(flSequence);
                break;
            case "FrontAndRight":
            case "WhiteRightAndFront":
                String[] frSequence = {"F`" , "T" ,  "T" , "Ba" , "Ba" , "F"};
                performAndLogMoves(frSequence);
                break;
            case "LeftAndBack":
            case"WhiteBackAndLeft":
                String[] lbSequence = {"Ba`" , "T" ,  "R" , "Ba`" , "R`"};
                performAndLogMoves(lbSequence);
                break;
            case "LeftAndFront":
            case "WhiteFrontAndLeft":
                String[] lfSequence = {"F" , "T`" ,  "R" , "Ba`" , "R`" , "F`"};
                performAndLogMoves(lfSequence);
                break;
            case "BackAndRight":
            case "WhiteRightAndBack":
                String[] wrbSequence = {"Ba`"};
                performAndLogMoves(wrbSequence);
                break;
            case "BackAndLeft":
            case"WhiteLeftAndBack":
                String[] wlbSequence = {"Ba"};
                performAndLogMoves(wlbSequence);
                break;
            case "RightAndBack":
            case"WhiteBackAndRight":
                String[] rbSequence = {"R`" , "T`" , "R" , "Ba"  , "Ba"};
                performAndLogMoves(rbSequence);
                break;
            case "RightAndFront":
            case "WhiteFrontAndRight":
                String[] rfSequence = {"R" , "T`" , "Ba" , "Ba" , "R`"};
                performAndLogMoves(rfSequence);
                break;
            case "WhiteTopAndFront":
                String[] wtfSequence = {"T" , "T" , "Ba" , "Ba"};
                performAndLogMoves(wtfSequence);
                break;
            case "WhiteTopAndLeft":
                String[] wtlSequence = {"T" , "Ba" , "Ba"};
                performAndLogMoves(wtlSequence);
                break;
            case "WhiteTopAndBack":
                String[] wtbSequence = { "Ba" , "Ba"};
                performAndLogMoves(wtbSequence);
                break;
            case "WhiteTopAndRight":
                String[] wtrSequence = {"T`" , "Ba" , "Ba"};
                performAndLogMoves(wtrSequence);
                break;
            case "WhiteBottomAndRight":
                String[] wbbSequence = {"R" , "R" , "T`" , "Ba" , "Ba"};
                performAndLogMoves(wbbSequence);
                break;
        }
    }

    private  void solveOrangeWhiteEdge() {
        if (cube[4][2][1] == 'O' && cube[5][1][2] == 'W') {                                                                                                                                                     //method to solve green and white edge 4,2,1 and 5,1,2
            return;
        }
        String coordinates = getEdgeCoordinates('O');
        switch (coordinates){
            case "TopAndFront":
                String[] tfSequence = {"F" , "R`" , "F`"};
                performAndLogMoves(tfSequence);
                break;
            case "TopAndLeft":
                String[] tlSequence = {"T`" , "F" , "R`" , "F`" };
                performAndLogMoves(tlSequence);
                break;
            case "TopAndBack":
                String[] tbSequence = {"T" , "T" ,  "F" , "R`" , "F`" };
                performAndLogMoves(tbSequence);
                break;
            case "TopAndRight":
                String[] trSequence = { "T" , "F" , "R`" , "F`" };
                performAndLogMoves(trSequence);
                break;
            case "BottomAndRight":
                String[] brSequence = {"R" , "R" , "T" , "F" , "R`" , "F`"};
                performAndLogMoves(brSequence);
                break;
            case "FrontAndLeft":
            case"WhiteLeftAndFront":
                String[] flSequence = {"F" , "T`" ,  "R" , "R" , "F`"};
                performAndLogMoves(flSequence);
                break;
            case "FrontAndRight":
            case "WhiteRightAndFront":
                String[] frSequence = {"F`" , "T`" ,  "F" , "R" , "R"};
                performAndLogMoves(frSequence);
                break;
            case "LeftAndBack":
            case"WhiteBackAndLeft":
                String[] lbSequence = {"L" , "T" ,  "T" , "R" , "R" , "L`"};
                performAndLogMoves(lbSequence);
                break;
            case "LeftAndFront":
            case "WhiteFrontAndLeft":
                String[] lfSequence = {"L`" , "T" ,  "T" , "R" , "R" , "L"};
                performAndLogMoves(lfSequence);
                break;
            case "BackAndRight":
            case "WhiteRightAndBack":
                String[] wrbSequence = {"Ba" , "T" , "Ba`" , "R" , "R"};
                performAndLogMoves(wrbSequence);
                break;
            case "BackAndLeft":
            case"WhiteLeftAndBack":
                String[] wlbSequence = {"Ba`" , "T" , "Ba" , "R" , "R"};
                performAndLogMoves(wlbSequence);
                break;
            case "RightAndBack":
            case"WhiteBackAndRight":
                String[] rbSequence = {"R" };
                performAndLogMoves(rbSequence);
                break;
            case "RightAndFront":
            case "WhiteFrontAndRight":
                String[] rfSequence = {"R`"};
                performAndLogMoves(rfSequence);
                break;
            case "WhiteTopAndFront":
                String[] wtfSequence = {"T`" , "R" , "R"};
                performAndLogMoves(wtfSequence);
                break;
            case "WhiteTopAndLeft":
                String[] wtlSequence = {"T" , "T" , "R" , "R"};
                performAndLogMoves(wtlSequence);
                break;
            case "WhiteTopAndBack":
                String[] wtbSequence = { "T" , "R" , "R"};
                performAndLogMoves(wtbSequence);
                break;
            case "WhiteTopAndRight":
                String[] wtrSequence = {"R" , "R"};
                performAndLogMoves(wtrSequence);
                break;
        }
    }

    private String getEdgeCoordinates(char color1){
        char[] varArray = {'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'i' , 'j' , 'k' , 'l' , 'm' , 'n' , 'o' , 'p' };                                                                                                             //method to get coordinates of edge pieces
        for (char var : varArray) {
            int[][] edge = findEdgeCoordinates(var);
            int[] cord1 = edge[0];
            int[] cord2 = edge[1];
            if ((cube[cord1[0]][cord1[1]][cord1[2]] == color1) && (cube[cord2[0]][cord2[1]][cord2[2]] == 'W')){
                return findEdgeName(var);
            } else if ((cube[cord1[0]][cord1[1]][cord1[2]] == 'W') && (cube[cord2[0]][cord2[1]][cord2[2]] == color1)) {
                return "White" + findEdgeName(var);
             }
        }
        return "none";
    }

    private int[][] findEdgeCoordinates(char var) {
        return switch (var) {                                                                                                                                                                                                                   //method with all possible positions for edges
            case 'a' -> new int[][]{{0, 2, 1}, {1, 0, 1}};
            case 'b' -> new int[][]{{0, 1, 0}, {2, 0, 1}};
            case 'c' -> new int[][]{{0, 0, 1}, {3, 0, 1}};
            case 'd' -> new int[][]{{0, 1, 2}, {4, 0, 1}};
            case 'e' -> new int[][]{{5, 0, 1}, {1, 2, 1}};
            case 'f' -> new int[][]{{5, 1, 0}, {2, 2, 1}};
            case 'g' -> new int[][]{{5, 2, 1}, {3, 2, 1}};
            case 'h' -> new int[][]{{5, 1, 2}, {4, 2, 1}};
            case 'i' -> new int[][]{{1, 1, 0}, {2, 1, 2}};
            case 'j' -> new int[][]{{1, 1, 2}, {4, 1, 0}};
            case 'k' -> new int[][]{{2, 1, 0}, {3, 1, 2}};
            case 'l' -> new int[][]{{2, 1, 2}, {1, 1, 0}};
            case 'm' -> new int[][]{{3, 1, 0}, {4, 1, 2}};
            case 'n' -> new int[][]{{3, 1, 2}, {2, 1, 0}};
            case 'o' -> new int[][]{{4, 1, 0}, {3, 1, 0}};
            case 'p' -> new int[][]{{4, 1, 2}, {1, 1, 2}};
            default -> new int[][]{{0,0,0} , {0,0,0}};
        };
    }

    private String findEdgeName(char var) {
        return switch (var) {                                                                                                                                                                                                                               //method for getting the name of the edge where the piece is
            case 'a' -> "TopAndFront";
            case 'b' -> "TopAndLeft";
            case 'c' -> "TopAndBack";
            case 'd' -> "TopAndRight";
            case 'e' -> "BottomAndFront";
            case 'f' -> "BottomAndLeft";
            case 'g' -> "BottomAndBack";
            case 'h' -> "BottomAndRight";
            case 'i' -> "FrontAndLeft";
            case 'j' -> "FrontAndRight";
            case 'k' -> "LeftAndBack";
            case 'l' -> "LeftAndFront";
            case 'm' -> "BackAndRight";
            case 'n' -> "BackAndLeft";
            case 'o' -> "RightAndBack";
            case 'p' -> "RightAndFront";
            default -> "None";
        };
    }

    private void performAndLogMoves(String[] moves) {
        cubeMoves.performMoves(moves);                                                                                                                                                                                    //perform moves
        moveRecord.addAll(List.of(moves));                                                                                                                                                                                  // Log moves into the Cross list
    }
}