public class FLcorners {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;

    public FLcorners(RubiksCube rubiksCube) {
        // Directly assign the cube array to a member variable
        this.cube = rubiksCube.getCube();
        // Initialize CubeMoves with the RubiksCube instance
        this.cubeMoves = new CubeMoves(rubiksCube);
    }

    public void solveWhiteCorners() {
        solveFrontLeftCorner();
        solveFrontRightCorner();
        solveBackRightCorner();
        solveBackLeftCorner();
    }

    private void solveFrontLeftCorner() {
        String orientationCase = getCornerRGWOrientationCase();
        switch (orientationCase) {
            case "correctlyOriented":
                break;
            case "GRWOrientation":
                executeRepeatingMoves("L` T` L T", 4);
                break;
            case "RWGOrientation":
                executeRepeatingMoves("L` T` L T", 2);
                break;
            case "topWRGOrientation":
                performMoves(new String[]{"T`", "L`", "T", "L"});
                break;
            case "topGWROrientation":
                executeRepeatingMoves("L` T` L T", 1);
                break;
            case "topRGWOrientation":
                executeRepeatingMoves("L` T` L T", 3);
                break;
            default:
                handleMisalignedFrontLeftCorner();
                break;
        }
    }
    private String getCornerRGWOrientationCase() {
        // Logic to determine the orientation of the corner piece and return a case identifier
        if (cube[5][0][0] == 'W' && cube[1][2][0] == 'G') {
            return "correctlyOriented";
        } else if (cube[5][0][0] == 'R' && cube[1][2][0] == 'W') {
            return "GRWOrientation";
        } else if (cube[5][0][0] == 'G' && cube[2][2][0] == 'R') {
            return "RWGOrientation";
        } else if (cube[1][0][0] == 'W' && cube[0][2][0] == 'G') {
            return "topWRGOrientation";
        } else if (cube[1][0][0] == 'G' && cube[0][2][0] == 'R') {
            return "topGWROrientation";
        }else if (cube[1][0][0] == 'R' && cube[0][2][0] == 'W') {
            return "topRGWOrientation";
        }
        return "misalignedOrOther";
    }
    private void   handleMisalignedFrontLeftCorner() {
        String position = positionTopOrBottomC1();
        if (position.equals("BottomFrontRightCorner") || position.equals( "BottomBackLeftCorner") || position.equals( "BottomBackRightCorner")) {
            movePieceToTopC1(position);
            solveFrontLeftCorner(); // Recursively call with piece in the top corner
        } else if (position.equals("TopFrontRightCorner") || position.equals( "TopBackLeftCorner") || position.equals( "TopBackRightCorner")) {
            alignTopLayerC1(position);
            solveFrontLeftCorner(); // Recursively call for correct positioning
        }
    }

    private String positionTopOrBottomC1() {
        if ((cube[5][0][2] == 'W' && cube[1][2][2] == 'R' &&  cube[4][2][0] == 'G')  ||
                (cube[5][0][2] == 'R' && cube[1][2][2] == 'G' &&  cube[4][2][0] == 'W') ||
                (cube[5][0][2] == 'G' && cube[1][2][2] == 'W' &&  cube[4][2][0] == 'R')) {
            return "BottomFrontRightCorner";
        }
        else  if ((cube[5][2][2] == 'G' && cube[3][2][0] == 'R' &&  cube[4][2][2] == 'W')  ||
                (cube[5][2][2] == 'W' && cube[3][2][0] == 'G' &&  cube[4][2][2] == 'R') ||
                (cube[5][2][2] == 'R' && cube[3][2][0] == 'W' &&  cube[4][2][2] == 'G')) {
            return "BottomBackRightCorner";
        }
        else if ((cube[5][2][0] == 'G' && cube[3][2][0] == 'W' &&  cube[2][2][0] == 'R')  ||
                (cube[5][2][0] == 'W' && cube[3][2][0] == 'R' &&  cube[2][2][0] == 'G') ||
                (cube[5][2][0] == 'R' && cube[3][2][0] == 'G' &&  cube[2][2][0] == 'W')) {
            return "BottomBackLeftCorner";
        }
        else if ((cube[1][0][2] == 'R' && cube[4][0][0] == 'W' &&  cube[0][2][2] == 'G')  ||
                (cube[1][0][2] == 'G' && cube[4][0][0] == 'R' &&  cube[0][2][2] == 'W') ||
                (cube[1][0][2] == 'W' && cube[4][0][0] == 'G' &&  cube[0][2][2] == 'R')) {
            return "TopFrontRightCorner";
        }
        else  if ((cube[3][0][0] == 'W' && cube[4][0][2] == 'R' &&  cube[0][0][2] == 'G')  ||
                (cube[3][0][0] == 'R' && cube[4][0][2] == 'G' &&  cube[0][0][2] == 'W') ||
                (cube[3][0][0] == 'G' && cube[4][0][2] == 'W' &&  cube[0][0][2] == 'R')) {
            return "TopBackRightCorner" ;
        }
        else if ((cube[3][0][2] == 'R' && cube[2][0][0] == 'W' &&  cube[0][0][0] == 'G')  ||
                (cube[3][0][2] == 'G' && cube[2][0][0] == 'R' &&  cube[0][0][0] == 'W') ||
                (cube[3][0][2] == 'W' && cube[2][0][0] == 'G' &&  cube[0][0][0] == 'R')) {
            return "TopBackLeftCorner";
        }
        else {
            return "NotInBottomOrTop";
        }
    }


    private void movePieceToTopC1(String pos) {
        switch (pos) {
            case "BottomFrontRightCorner":
                performMoves(new String[]{"R", "T", "R`"});
                break;
            case "BottomBackRightCorner":
                performMoves(new String[]{"Ba", "T", "T" , "Ba`"});
                break;
            case "BottomBackLeftCorner":
                performMoves(new String[]{"Ba`", "T`", "Ba"});
                break;
        }
    }

    private void alignTopLayerC1(String pos) {
        switch (pos) {
            case "TopFrontRightCorner":
                performMoves(new String[]{"T"});
                break;
            case "TopBackRightCorner":
                performMoves(new String[]{"T", "T"});
                break;
            case "TopBackLeftCorner":
                performMoves(new String[]{"T`"});
                break;
        }
    }
    private void solveFrontRightCorner() {
        String orientationCase = getCornerROWOrientationCase();
        System.out.println(orientationCase);
        switch (orientationCase) {
            case "correctlyOriented":
                break;
            case "OWGOrientation":
                executeRepeatingMoves("R T R` T`", 4);
                break;
            case "WGOOrientation":
                executeRepeatingMoves("R T R` T`", 2);
                break;
            case "topWOGOrientation":
                performMoves(new String[]{"T", "R", "T`", "R`"});
                break;
            case "topGWOrientation":
                executeRepeatingMoves("R T R` T`", 1);
                break;
            case "topOGWOrientation":
                executeRepeatingMoves("R T R` T`", 3);
                break;
            default:
                // Handle cases where the piece is on a different bottom corner or on the different top corner
                handleMisalignedFrontRightCorner();
                break;
        }
    }
    private String getCornerROWOrientationCase() {
        // Logic to determine the orientation of the corner piece and return a case identifier
        if (cube[5][0][2] == 'W' && cube[1][2][2] == 'G') {
            return "correctlyOriented";
        } else if (cube[5][0][2] == 'G' && cube[1][2][2] == 'O') {
            System.out.println("Here");
            return "OWGOrientation";
        } else if (cube[5][0][2] == 'O' && cube[1][2][2] == 'W') {
            System.out.println("how");
        return "WGOOrientation";
    }else if (cube[1][0][2] == 'W' && cube[0][2][2] == 'G') {
            return "topWOGOrientation";
        } else if (cube[1][0][2] == 'G' && cube[0][2][2] == 'O') {
            return "topGWOrientation";
        }else if (cube[1][0][2] == 'O' && cube[0][2][2] == 'W') {
            return "topOGWOrientation";
        }
        return "misalignedOrOther";
    }
    private void   handleMisalignedFrontRightCorner() {
        String position = positionTopOrBottomC2();
        if (position.equals( "BottomBackLeftCorner") || position.equals( "BottomBackRightCorner")) {
            movePieceToTopC2(position);
            solveFrontRightCorner(); // Recursively call with piece in the top corner
        } else if (position.equals("TopFrontLeftCorner") || position.equals( "TopBackLeftCorner") || position.equals( "TopBackRightCorner")) {
            alignTopLayerC2(position);
            solveFrontRightCorner(); // Recursively call for correct positioning
        }
    }

    private String positionTopOrBottomC2() {
        if ((cube[5][2][2] == 'G' && cube[3][2][0] == 'W' &&  cube[4][2][2] == 'O')  ||
                (cube[5][2][2] == 'O' && cube[3][2][0] == 'G' &&  cube[4][2][2] == 'W') ||
                (cube[5][2][2] == 'W' && cube[3][2][0] == 'O' &&  cube[4][2][2] == 'G')) {
            return "BottomBackRightCorner";
        }
        else if ((cube[5][2][0] == 'G' && cube[3][2][2] == 'O' &&  cube[2][2][0] == 'W')  ||
                (cube[5][2][0] == 'W' && cube[3][2][2] == 'G' &&  cube[2][2][0] == 'O') ||
                (cube[5][2][0] == 'O' && cube[3][2][2] == 'W' &&  cube[2][2][0] == 'G')) {
            return "BottomBackLeftCorner";
        }
        else if ((cube[3][0][0] == 'W' && cube[4][0][2] == 'G' &&  cube[0][0][2] == 'O')  ||
                (cube[3][0][0] == 'G' && cube[4][0][2] == 'O' &&  cube[0][0][2] == 'W') ||
                (cube[3][0][0] == 'O' && cube[4][0][2] == 'W' &&  cube[0][0][2] == 'G')) {
            return "TopBackRightCorner";
        }
        else  if ((cube[2][0][0] == 'O' && cube[3][0][2] == 'W' &&  cube[0][0][0] == 'G')  ||
                (cube[2][0][0] == 'W' && cube[3][0][2] == 'G' &&  cube[0][0][0] == 'O') ||
                (cube[2][0][0] == 'G' && cube[3][0][2] == 'O' &&  cube[0][0][0] == 'W')) {
            return "TopBackLeftCorner" ;
        }
        else if ((cube[1][0][0] == 'W' && cube[2][0][2] == 'G' &&  cube[0][2][0] == 'O')  ||
                (cube[1][0][0] == 'G' && cube[2][0][2] == 'O' &&  cube[0][2][0] == 'W') ||
                (cube[1][0][0] == 'O' && cube[2][0][2] == 'W' &&  cube[0][2][0] == 'G')) {
            return "TopFrontLeftCorner";
        }
        else {
            return "NotInBottomOrTop";
        }
    }


    private void movePieceToTopC2(String pos) {
        switch (pos) {
            case "BottomBackRightCorner":
                performMoves(new String[]{"Ba", "T", "Ba`"});
                break;
            case "BottomBackLeftCorner":
                performMoves(new String[]{"Ba`", "T", "T" , "Ba"});
                break;
        }
    }

    private void alignTopLayerC2(String pos) {
        switch (pos) {
            case "TopFrontLeftCorner":
                performMoves(new String[]{"T`"});
                break;
            case "TopBackRightCorner":
                performMoves(new String[]{"T"});
                break;
            case "TopBackLeftCorner":
                performMoves(new String[]{"T", "T"});
                break;
        }
    }
    private void solveBackRightCorner() {
        String orientationCase = getCornerBOWOrientationCase();
        switch (orientationCase) {
            case "correctlyOriented":
                break;
            case "WBOOrientation":
                executeRepeatingMoves("Ba T Ba` T`", 2);
                break;
            case "OWBOrientation":
                executeRepeatingMoves("Ba T Ba` T`", 4);
                break;
            case "topWOBOrientation":
                performMoves(new String[]{"Ba", "T", "Ba`", "T`"});
                break;
            case "topBWOrientation":
                executeRepeatingMoves("T Ba T` Ba`", 1);
                break;
            case "topOBWOrientation":
                executeRepeatingMoves("Ba T Ba` T`", 3);
                break;
            default:
                // Handle cases where the piece is on a different bottom corner or on the different top corner
                handleMisalignedBackRightCorner();
                break;
        }
    }
    private String getCornerBOWOrientationCase() {
        if (cube[5][2][2] == 'W' && cube[4][2][2] == 'O') {
            System.out.println("inside");
            return "correctlyOriented";
        } else if (cube[5][2][2] == 'O' && cube[4][2][2] == 'B') {
            return "WBOOrientation";
        } else if (cube[5][2][2] == 'B' && cube[4][2][2] == 'W') {
            return "OWBOrientation";
        } else if (cube[4][0][2] == 'O' && cube[0][0][2] == 'B') {
            return "topWOBOrientation";
        } else if (cube[4][0][2] == 'W' && cube[0][0][2] == 'O') {
            return "topBWOrientation";
        }else if (cube[4][0][2] == 'B' && cube[0][0][2] == 'W') {
            return "topOBWOrientation";
        }
        return "misalignedOrOther";
    }
    private void   handleMisalignedBackRightCorner() {
        String position = positionTopOrBottomC3();
        if (position.equals( "BottomBackLeftCorner")) {
            performMoves(new String[]{"Ba`", "T", "T", "Ba"});
            solveBackRightCorner(); // Recursively call with piece in the top corner
        } else if (position.equals("TopFrontLeftCorner") || position.equals( "TopBackLeftCorner") || position.equals( "TopFrontRightCorner")) {
            alignTopLayerC3(position);
            solveBackRightCorner(); // Recursively call for correct positioning
        }
    }

    private String positionTopOrBottomC3() {
        if ((cube[5][2][0] == 'O' && cube[3][2][2] == 'B' &&  cube[2][2][0] == 'W')  ||
                (cube[5][2][0] == 'B' && cube[3][2][2] == 'W' &&  cube[2][2][0] == 'O') ||
                (cube[5][2][0] == 'W' && cube[3][2][2] == 'O' &&  cube[2][2][0] == 'B')) {
            return "BottomBackLeftCorner";
        }
        else if ((cube[0][2][2] == 'B' && cube[4][0][0] == 'W' &&  cube[1][0][2] == 'O')  ||
                (cube[0][2][2] == 'W' && cube[4][0][0] == 'O' &&  cube[1][0][2] == 'B') ||
                (cube[0][2][2] == 'O' && cube[4][0][0] == 'B' &&  cube[1][0][2] == 'W')) {
            return "TopFrontRightCorner";
        }
        else  if ((cube[2][0][0] == 'B' && cube[3][0][2] == 'W' &&  cube[0][0][0] == 'O')  ||
                (cube[2][0][0] == 'W' && cube[3][0][2] == 'O' &&  cube[0][0][0] == 'B') ||
                (cube[2][0][0] == 'O' && cube[3][0][2] == 'B' &&  cube[0][0][0] == 'W')) {
            return "TopBackLeftCorner" ;
        }
        else if ((cube[1][0][0] == 'O' && cube[2][0][2] == 'B' &&  cube[0][2][0] == 'W')  ||
                (cube[1][0][0] == 'B' && cube[2][0][2] == 'W' &&  cube[0][2][0] == 'O') ||
                (cube[1][0][0] == 'W' && cube[2][0][2] == 'O' &&  cube[0][2][0] == 'B')) {
            return "TopFrontLeftCorner";
        }
        else {
            return "NotInBottomOrTop";
        }
    }

    private void alignTopLayerC3(String pos) {
        switch (pos) {
            case "TopFrontLeftCorner":
                performMoves(new String[]{"T", "T"});
                break;
            case "TopFrontRightCorner":
                performMoves(new String[]{"T`"});
                break;
            case "TopBackLeftCorner":
                performMoves(new String[]{"T"});
                break;
        }
    }
    private void solveBackLeftCorner() {
        String orientationCase = getCornerBRWOrientationCase();
        switch (orientationCase) {
            case "correctlyOriented":
                break;
            case "RWBOrientation":
                executeRepeatingMoves("Ba` T` Ba T", 4);
                break;
            case "WBROrientation":
                executeRepeatingMoves("Ba` T` Ba T", 2);
                break;
            case "topBWROrientation":
                performMoves(new String[]{"T`", "Ba`", "T", "Ba"});
                break;
            case "topRBWOrientation":
                executeRepeatingMoves("Ba` T` Ba T", 3);
                break;
            case "topWRBOrientation":
                executeRepeatingMoves("Ba` T` Ba T", 1);
                break;
            default:
                // Handle cases where the piece is on a different bottom corner or on the different top corner
                handleMisalignedBackLeftCorner();
                break;
        }
    }
    private String getCornerBRWOrientationCase() {
        // Logic to determine the orientation of the corner piece and return a case identifier
        if (cube[5][2][0] == 'W' && cube[2][2][0] == 'R') {
            return "correctlyOriented";
        } else if (cube[5][2][0] == 'B' && cube[2][2][0] == 'W') {
            return "RWBOrientation";
        } else if (cube[5][2][0] == 'R' && cube[2][2][0] == 'B') {
            return "WBROrientation";
        } else if (cube[2][0][0] == 'W' && cube[0][0][0] == 'R') {
            return "topBWROrientation";
        } else if (cube[2][0][0] == 'B' && cube[0][0][0] == 'W') {
            return "topRBWOrientation";
        }else if (cube[2][0][0] == 'R' && cube[0][0][0] == 'B') {
            return "topWRBOrientation";
        }
        return "misalignedOrOther";
    }
    private void   handleMisalignedBackLeftCorner() {
        String position = positionTopOrBottomC4();
        if(position.equals("TopFrontRightCorner") || position.equals( "TopFrontLeftCorner") || position.equals( "TopBackRightCorner")) {
            alignTopLayerC4(position);
            solveBackLeftCorner(); // Recursively call for correct positioning
        }
    }

    private String positionTopOrBottomC4() {
        if ((cube[1][0][0] == 'W' && cube[0][2][0] == 'R' &&  cube[2][0][2] == 'B')  ||
                (cube[1][0][0] == 'B' && cube[0][2][0] == 'W' &&  cube[2][0][2] == 'R') ||
                (cube[1][0][0] == 'R' && cube[0][2][0] == 'B' &&  cube[2][0][2] == 'W')) {
            return "TopFrontLeftCorner";
        }
        else  if ((cube[1][0][2] == 'B' && cube[4][0][0] == 'W' &&  cube[0][2][2] == 'R')  ||
                (cube[1][0][2] == 'R' && cube[4][0][0] == 'B' &&  cube[0][2][2] == 'W') ||
                (cube[1][0][2] == 'W' && cube[4][0][0] == 'R' &&  cube[0][2][2] == 'B')) {
            return "TopFrontRightCorner" ;
        }
        else if ((cube[4][0][2] == 'B' && cube[0][0][2] == 'R' &&  cube[3][0][0] == 'W')  ||
                (cube[4][0][2] == 'R' && cube[0][0][2] == 'W' &&  cube[3][0][0] == 'B') ||
                (cube[4][0][2] == 'W' && cube[0][0][2] == 'B' &&  cube[3][0][0] == 'R')) {
            return "TopBackRightCorner";
        }
        else {
            return "NotInBottomOrTop";
        }
    }


    private void alignTopLayerC4(String pos) {
        switch (pos) {
            case "TopFrontRightCorner":
                performMoves(new String[]{"T", "T"});
                break;
            case "TopBackRightCorner":
                performMoves(new String[]{"T`"});
                break;
            case "TopFrontLeftCorner":
                performMoves(new String[]{"T"});
                break;
        }
    }
    private void executeRepeatingMoves(String moves, int times) {
        for (int i = 0; i < times; i++) {
            String[] movesArray = moves.trim().split("\\s+");
            performMoves(movesArray);
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
            }
        }
    }
}