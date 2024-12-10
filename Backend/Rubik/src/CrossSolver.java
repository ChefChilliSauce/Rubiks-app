public class CrossSolver {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;

    public CrossSolver(RubiksCube rubiksCube) {             //getter method
        this.cube = rubiksCube.getCube();
        this.cubeMoves = new CubeMoves(rubiksCube);
    }
    public void solveWhiteCross(){
        solveGreenWhiteEdge();
        solveRedWhiteEdge();
        solveBlueWhiteEdge();
        solveOrangeWhiteEdge();
    }
    private  void solveGreenWhiteEdge(){
        if(cube[1][2][1] == 'G' && cube[5][0][1] == 'W'){
            return;
        }
        String coordinates = getEdgeCoordinates('G');
        switch (coordinates){
            case "TopAndFront":
                performMoves(new String[]{"T`" , "R`" , "F" , "R"});
                break;
            case "TopAndLeft":
                performMoves(new String[]{"T" , "T" , "R`" , "F" , "R"});
                break;
            case "TopAndBack":
                performMoves(new String[]{"T" , "R`" , "F" , "R"});
                break;
            case "TopAndRight":
                performMoves(new String[]{"R`" , "F" , "R"});
                break;
            case "BottomAndFront":
                performMoves(new String[]{"F" , "F" , "T`" , "R`" , "F" , "R"});
                break;
            case "BottomAndLeft":
                performMoves(new String[]{"L" , "L" , "T`" , "T`" , "R`" , "F" , "R"});
                break;
            case "BottomAndBack":
                performMoves(new String[]{"Ba" , "Ba" , "T" , "R`" , "F" , "R"});
                break;
            case "BottomAndRight":
                performMoves(new String[]{"R`" , "F"});
                break;
            case "FrontAndLeft":
            case"WhiteLeftAndFront":
                performMoves(new String[]{"F`"});
                break;
            case "FrontAndRight":
            case "WhiteRightAndFront":
                performMoves(new String[]{"F"});
                break;
            case "LeftAndBack":
            case"WhiteBackAndLeft":
                performMoves(new String[]{"L" , "L" , "F" , "L" , "L" , "T`" , "R`" , "F" , "R"});
                break;
            case "LeftAndFront":
            case "WhiteFrontAndLeft":
                performMoves(new String[]{"F" , "T`" , "R`" , "F" , "R"});
                break;
            case "BackAndRight":
            case "WhiteRightAndBack":
                performMoves(new String[]{"R`" , "T`" , "R" , "T" , "R`" , "F" , "R"});
                break;
            case "BackAndLeft":
            case"WhiteLeftAndBack":
                performMoves(new String[]{"L" , "T" , "L`" , "T" , "R`" , "F" , "R"});
                break;
            case "RightAndBack":
            case"WhiteBackAndRight":
                performMoves(new String[]{"Ba" , "T" , "Ba`" , "R`" , "F" , "R"});
                break;
            case "RightAndFront":
            case "WhiteFrontAndRight":
                performMoves(new String[]{"F`" , "T`" , "R`" , "F" , "R"});
                break;
            case "WhiteTopAndFront":
                performMoves(new String[]{"F" , "F" });
                break;
            case "WhiteTopAndLeft":
                performMoves(new String[]{"T`" , "F" , "F"});
                break;
            case "WhiteTopAndBack":
                performMoves(new String[]{"T" , "T" , "F" , "F"});
                break;
            case "WhiteTopAndRight":
                performMoves(new String[]{"T" , "F" , "F"});
                break;
            case "WhiteBottomAndLeft":
                performMoves(new String[]{"L" , "L" , "T`" , "F" , "F"});
                break;
            case "WhiteBottomAndBack":
                performMoves(new String[]{"Ba" , "Ba" , "T" , "T" , "F" , "F"});
                break;
            case "WhiteBottomAndRight":
                performMoves(new String[]{"R" , "R" , "T" , "F" , "F"});
                break;
        }
    }
    private  void solveRedWhiteEdge() {
        if (cube[2][2][1] == 'R' && cube[5][1][0] == 'W') {
            return;
        }
        String coordinates = getEdgeCoordinates('R');
        switch (coordinates){
            case "TopAndFront":
                performMoves(new String[]{"F`" , "L" , "F" });
                break;
            case "TopAndLeft":
                performMoves(new String[]{"T`" ,  "F`" , "L" , "F"});
                break;
            case "TopAndBack":
                performMoves(new String[]{"T" , "T" ,  "F`" , "L" , "F"});
                break;
            case "TopAndRight":
                performMoves(new String[]{"T" ,  "F`" , "L" , "F"});
                break;
            case "BottomAndLeft":
                performMoves(new String[]{"L" , "L" , "T`" ,  "F`" , "L" , "F"});
                break;
            case "BottomAndBack":
                performMoves(new String[]{"Ba" , "Ba" , "T" , "T" ,  "F`" , "L" , "F"});
                break;
            case "BottomAndRight":
                performMoves(new String[]{"R" , "R" , "T" ,  "F`" , "L" , "F"});
                break;
            case "FrontAndLeft":
            case"WhiteLeftAndFront":
                performMoves(new String[]{"L`" , "T`" ,  "F`" , "L" , "F"});
                break;
            case "FrontAndRight":
            case "WhiteRightAndFront":
                performMoves(new String[]{"R`" ,"T" ,  "F`" , "L" , "F" , "R`"});
                break;
            case "LeftAndBack":
            case"WhiteBackAndLeft":
                performMoves(new String[]{"L`"});
                break;
            case "LeftAndFront":
            case "WhiteFrontAndLeft":
                performMoves(new String[]{"L"});
                break;
            case "BackAndRight":
            case "WhiteRightAndBack":
                performMoves(new String[]{"R`" , "T" ,  "F`" , "L" , "F" , "R"});
                break;
            case "BackAndLeft":
            case"WhiteLeftAndBack":
                performMoves(new String[]{"L" , "T`" ,  "F`" , "L" , "F"});
                break;
            case "RightAndBack":
            case"WhiteBackAndRight":
                performMoves(new String[]{"Ba" , "T" , "T" , "F`" , "L" , "F" , "Ba"});
                break;
            case "RightAndFront":
            case "WhiteFrontAndRight":
                performMoves(new String[]{"F" , "F" , "L" , "F" , "F"});
                break;
            case "WhiteTopAndFront":
                performMoves(new String[]{"T" , "L" , "L"});
                break;
            case "WhiteTopAndLeft":
                performMoves(new String[]{"L" , "L"});
                break;
            case "WhiteTopAndBack":
                performMoves(new String[]{"T`"  , "L" , "L"});
                break;
            case "WhiteTopAndRight":
                performMoves(new String[]{"T" , "T" , "L" , "L"});
                break;
            case "WhiteBottomAndBack":
                performMoves(new String[]{"Ba" , "Ba" , "T`" , "L" , "L"});
                break;
            case "WhiteBottomAndRight":
                performMoves(new String[]{"R" , "R" , "T" , "T" , "L" , "L"});
                break;
        }
    }
    private  void solveBlueWhiteEdge() {
        if (cube[2][2][1] == 'B' && cube[5][1][0] == 'W') {
            return;
        }
        String coordinates = getEdgeCoordinates('B');
        switch (coordinates){
            case "TopAndFront":
                performMoves(new String[]{"T`" , "R" , "Ba`" , "R`" });
                break;
            case "TopAndLeft":
                performMoves(new String[]{"T" ,  "T" ,  "R" , "Ba`" , "R`" });
                break;
            case "TopAndBack":
                performMoves(new String[]{"T" ,  "R" , "Ba`" , "R`" });
                break;
            case "TopAndRight":
                performMoves(new String[]{ "R" , "Ba`" , "R`" });
                break;
            case "BottomAndBack":
                performMoves(new String[]{"Ba" , "Ba" , "T" ,  "R" , "Ba`" , "R`" });
                break;
            case "BottomAndRight":
                performMoves(new String[]{"R`" , "Ba`"});
                break;
            case "FrontAndLeft":
            case"WhiteLeftAndFront":
                performMoves(new String[]{"F" , "T`" ,  "T" , "Ba" , "Ba" , "F`"});
                break;
            case "FrontAndRight":
            case "WhiteRightAndFront":
                performMoves(new String[]{"F`" , "T`" ,  "T" , "Ba" , "Ba" , "F"});
                break;
            case "LeftAndBack":
            case"WhiteBackAndLeft":
                performMoves(new String[]{"Ba" , "T" ,  "R" , "Ba`" , "R`"});
                break;
            case "LeftAndFront":
            case "WhiteFrontAndLeft":
                performMoves(new String[]{"F" , "T`" ,  "R" , "Ba`" , "R`" , "F`"});
                break;
            case "BackAndRight":
            case "WhiteRightAndBack":
                performMoves(new String[]{"Ba`"});
                break;
            case "BackAndLeft":
            case"WhiteLeftAndBack":
                performMoves(new String[]{"Ba"});
                break;
            case "RightAndBack":
            case"WhiteBackAndRight":
                performMoves(new String[]{"R`" , "T`" , "R" , "Ba"  , "Ba"});
                break;
            case "RightAndFront":
            case "WhiteFrontAndRight":
                performMoves(new String[]{"R`" , "T`" , "Ba" , "Ba" , "R"});
                break;
            case "WhiteTopAndFront":
                performMoves(new String[]{"T" , "T" , "Ba" , "Ba"});
                break;
            case "WhiteTopAndLeft":
                performMoves(new String[]{"T" , "Ba" , "Ba"});
                break;
            case "WhiteTopAndBack":
                performMoves(new String[]{ "Ba" , "Ba"});
                break;
            case "WhiteTopAndRight":
                performMoves(new String[]{"T`" , "Ba" , "Ba"});
                break;
            case "WhiteBottomAndRight":
                performMoves(new String[]{"R" , "R" , "T`" , "Ba" , "Ba"});
                break;
        }
    }
    private  void solveOrangeWhiteEdge() {
        if (cube[2][2][1] == 'O' && cube[5][1][0] == 'W') {
            return;
        }
        String coordinates = getEdgeCoordinates('O');
        switch (coordinates){
            case "TopAndFront":
                performMoves(new String[]{"F`" , "R`" , "F"});
                break;
            case "TopAndLeft":
                performMoves(new String[]{"T`" , "F`" , "R`" , "F" });
                break;
            case "TopAndBack":
                performMoves(new String[]{"T" , "T" ,  "F`" , "R`" , "F" });
                break;
            case "TopAndRight":
                performMoves(new String[]{ "T" , "F`" , "R`" , "F" });
                break;
            case "BottomAndRight":
                performMoves(new String[]{"R" , "R" , "T" , "F`" , "R`" , "F"});
                break;
            case "FrontAndLeft":
            case"WhiteLeftAndFront":
                performMoves(new String[]{"F" , "T`" ,  "R" , "R" , "F`"});
                break;
            case "FrontAndRight":
            case "WhiteRightAndFront":
                performMoves(new String[]{"F`" , "T`" ,  "F" , "R" , "R"});
                break;
            case "LeftAndBack":
            case"WhiteBackAndLeft":
                performMoves(new String[]{"L" , "T" ,  "T" , "R" , "R" , "L`"});
                break;
            case "LeftAndFront":
            case "WhiteFrontAndLeft":
                performMoves(new String[]{"L`" , "T" ,  "T" , "R" , "R" , "L"});
                break;
            case "BackAndRight":
            case "WhiteRightAndBack":
                performMoves(new String[]{"Ba" , "T" , "Ba`" , "R" , "R"});
                break;
            case "BackAndLeft":
            case"WhiteLeftAndBack":
                performMoves(new String[]{"Ba`" , "T" , "Ba" , "R" , "R"});
                break;
            case "RightAndBack":
            case"WhiteBackAndRight":
                performMoves(new String[]{"R" });
                break;
            case "RightAndFront":
            case "WhiteFrontAndRight":
                performMoves(new String[]{"R`"});
                break;
            case "WhiteTopAndFront":
                performMoves(new String[]{"T`" , "R" , "R"});
                break;
            case "WhiteTopAndLeft":
                performMoves(new String[]{"T" , "T" , "R" , "R"});
                break;
            case "WhiteTopAndBack":
                performMoves(new String[]{ "T" , "R" , "R"});
                break;
            case "WhiteTopAndRight":
                performMoves(new String[]{"R" , "R"});
                break;
        }
    }
    private String getEdgeCoordinates(char color1){
        char[] varArray = {'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'i' , 'j' , 'k' , 'l' , 'm' , 'n' , 'o' , 'p' };
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
        return switch (var) {
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
        return switch (var) {
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

    private void performMoves(String[] moves) {//Method for all moves to improve readability of code converted to a method
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
            }
        }
    }
}