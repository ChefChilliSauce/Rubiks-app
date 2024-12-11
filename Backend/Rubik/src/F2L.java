public class F2L {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;

    public F2L(RubiksCube rubiksCube) {             //getter method
        // Directly assign the cube array to a member variable
        this.cube = rubiksCube.getCube();
        // Initialize CubeMoves with the RubiksCube instance
        this.cubeMoves = new CubeMoves(rubiksCube);
    }

    public void solveSecondLayer() {
        solveFrontLeftCorner();
        solveFrontRightCorner();
        solveBackRightCorner();
        solveBackLeftCorner();
        solveFrontLeftEdge();
        solveFrontRightEdge();
        solveBackRightEdge();
        solveBackLeftEdge();
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
                cubeMoves.performMoves(new String[]{"T`", "L`", "T", "L"});
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
                cubeMoves.performMoves(new String[]{"R", "T", "R`"});
                break;
            case "BottomBackRightCorner":
                cubeMoves.performMoves(new String[]{"Ba", "T", "T" , "Ba`"});
                break;
            case "BottomBackLeftCorner":
                cubeMoves.performMoves(new String[]{"Ba`", "T`", "Ba"});
                break;
        }
    }

    private void alignTopLayerC1(String pos) {
        switch (pos) {
            case "TopFrontRightCorner":
                cubeMoves.performMoves(new String[]{"T"});
                break;
            case "TopBackRightCorner":
                cubeMoves.performMoves(new String[]{"T", "T"});
                break;
            case "TopBackLeftCorner":
                cubeMoves.performMoves(new String[]{"T`"});
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
                cubeMoves.performMoves(new String[]{"T", "R", "T`", "R`"});
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
                cubeMoves.performMoves(new String[]{"Ba", "T", "Ba`"});
                break;
            case "BottomBackLeftCorner":
                cubeMoves.performMoves(new String[]{"Ba`", "T", "T" , "Ba"});
                break;
        }
    }

    private void alignTopLayerC2(String pos) {
        switch (pos) {
            case "TopFrontLeftCorner":
                cubeMoves.performMoves(new String[]{"T`"});
                break;
            case "TopBackRightCorner":
                cubeMoves.performMoves(new String[]{"T"});
                break;
            case "TopBackLeftCorner":
                cubeMoves.performMoves(new String[]{"T", "T"});
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
                cubeMoves.performMoves(new String[]{"Ba", "T", "Ba`", "T`"});
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
            cubeMoves.performMoves(new String[]{"Ba`", "T", "T", "Ba"});
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
                cubeMoves.performMoves(new String[]{"T", "T"});
                break;
            case "TopFrontRightCorner":
                cubeMoves.performMoves(new String[]{"T`"});
                break;
            case "TopBackLeftCorner":
                cubeMoves.performMoves(new String[]{"T"});
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
                cubeMoves.performMoves(new String[]{"T`", "Ba`", "T", "Ba"});
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
                cubeMoves.performMoves(new String[]{"T", "T"});
                break;
            case "TopBackRightCorner":
                cubeMoves.performMoves(new String[]{"T`"});
                break;
            case "TopFrontLeftCorner":
                cubeMoves.performMoves(new String[]{"T"});
                break;
        }
    }
    private void executeRepeatingMoves(String moves, int times) {
        for (int i = 0; i < times; i++) {
            String[] movesArray = moves.trim().split("\\s+");
            cubeMoves.performMoves(movesArray);
        }
    }
    private void solveFrontLeftEdge() {
        String orientationCase = getCornerGROrientationCase();
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontRGOrientation"):
                cubeMoves.performMoves(new String[]{"T", "T"});
                cubeMoves.performAlgoF2L(new String[]{"RGAlgo"});
                break;
            case ("LeftRGOrientation"):
                cubeMoves.performMoves(new String[]{"T"});
                cubeMoves.performAlgoF2L(new String[]{"RGAlgo"});
                break;
            case("BackRGOrientation"):
                cubeMoves.performAlgoF2L(new String[]{"RGAlgo"});
                break;
            case ("RightRGOrientation"):
                cubeMoves.performMoves(new String[]{"T`"});
                cubeMoves.performAlgoF2L(new String[]{"RGAlgo"});
                break;
            case("FrontGROrientation"):
                cubeMoves.performMoves(new String[]{"T`"});
                cubeMoves.performAlgoF2L(new String[]{"GRAlgo"});
                break;
            case ("LeftGROrientation"):
                cubeMoves.performMoves(new String[]{"T", "T"});
                cubeMoves.performAlgoF2L(new String[]{"GRAlgo"});
                break;
            case("BackGROrientation"):
                cubeMoves.performMoves(new String[]{"T"});
                cubeMoves.performAlgoF2L(new String[]{"GRAlgo"});
                break;
            case ("RightGROrientation"):
                cubeMoves.performAlgoF2L(new String[]{"GRAlgo"});
                break;
            default:
                handleMisalignedFrontLeft();
                break;
        }
    }
    private String getCornerGROrientationCase() {
        if(cube[1][1][0] == 'G' && cube[2][1][2] == 'R'){
            return "correctOrientation";
        }
        else if (cube[1][0][1] == 'R' && cube[0][2][1] == 'G'){
            return "FrontRGOrientation";
        }
        else if (cube[2][0][1] == 'R' && cube[0][1][0] == 'G'){
            return "LeftRGOrientation";
        }
        else if (cube[3][0][1] == 'R' && cube[0][0][1] == 'G'){
            return "BackRGOrientation";
        }
        else if (cube[4][0][1] == 'R' && cube[0][1][2] == 'G'){
            return "RightRGOrientation";
        }
        else if (cube[1][0][1] == 'G' && cube[0][2][1] == 'R'){
            return "FrontGROrientation";
        }
        else if (cube[2][0][1] == 'G' && cube[0][1][0] == 'R'){
            return "LeftGROrientation";
        }
        else if (cube[3][0][1] == 'G' && cube[0][0][1] == 'R'){
            return "BackGROrientation";
        }
        else if (cube[4][0][1] == 'G' && cube[0][1][2] == 'R'){
            return "RightGROrientation";
        }
        return "misaligned";
    }
    private void   handleMisalignedFrontLeft() {
        if(cube[1][1][0] == 'R' && cube[2][1][2] == 'G'){
            cubeMoves.performAlgoF2L(new String[]{"E1Algo"});
            solveFrontLeftEdge();
        }
        else if ((cube[1][1][2] == 'R' && cube[4][1][0] == 'G') || (cube[1][1][2] == 'G' && cube[4][1][0] == 'R')){
            cubeMoves.performAlgoF2L(new String[]{"E2Algo"});
            solveFrontLeftEdge();
        }
        else if ((cube[3][1][0] == 'R' && cube[4][1][2] == 'G') ||  (cube[3][1][0] == 'G' && cube[4][1][2] == 'R')){
            cubeMoves.performAlgoF2L(new String[]{"E3Algo"});
            solveFrontLeftEdge();
        }
        else if ((cube[3][1][2] == 'R' && cube[2][1][0] == 'G') ||  (cube[3][1][2] == 'G' && cube[2][1][0] == 'R')){
            cubeMoves.performAlgoF2L(new String[]{"E4Algo"});
            solveFrontLeftEdge();
        }
    }
    private void solveFrontRightEdge() {
        String orientationCase = getCornerGOOrientationCase();
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontOGOrientation"):
                cubeMoves.performMoves(new String[]{"T", "T"});
                cubeMoves.performAlgoF2L(new String[]{"OGAlgo"});
                break;
            case ("LeftOGOrientation"):
                cubeMoves.performMoves(new String[]{"T"});
                cubeMoves.performAlgoF2L(new String[]{"OGAlgo"});
                break;
            case("BackOGOrientation"):
                cubeMoves.performAlgoF2L(new String[]{"OGAlgo"});
                break;
            case ("RightOGOrientation"):
                cubeMoves.performMoves(new String[]{"T`"});
                cubeMoves.performAlgoF2L(new String[]{"OGAlgo"});
                break;
            case("FrontGOOrientation"):
                cubeMoves.performMoves(new String[]{"T"});
                cubeMoves.performAlgoF2L(new String[]{"GOAlgo"});
                break;
            case ("LeftGOOrientation"):
                cubeMoves.performAlgoF2L(new String[]{"GOAlgo"});
                break;
            case("BackGOOrientation"):
                cubeMoves.performMoves(new String[]{"T`"});
                cubeMoves.performAlgoF2L(new String[]{"GOAlgo"});
                break;
            case ("RightGOOrientation"):
                cubeMoves.performMoves(new String[]{"T", "T"});
                cubeMoves.performAlgoF2L(new String[]{"GOAlgo"});
                break;
            default:
                handleMisalignedFrontRight();
                break;
        }
    }
    private String getCornerGOOrientationCase() {
        if(cube[1][1][2] == 'G' && cube[4][1][0] == 'O'){
            return "correctOrientation";
        }
        else if (cube[1][0][1] == 'O' && cube[0][2][1] == 'G'){
            return "FrontOGOrientation";
        }
        else if (cube[2][0][1] == 'O' && cube[0][1][0] == 'G'){
            return "LeftOGOrientation";
        }
        else if (cube[3][0][1] == 'O' && cube[0][0][1] == 'G'){
            return "BackOGOrientation";
        }
        else if (cube[4][0][1] == 'O' && cube[0][1][2] == 'G'){
            return "RightOGOrientation";
        }
        else if (cube[1][0][1] == 'G' && cube[0][2][1] == 'O'){
            return "FrontGOOrientation";
        }
        else if (cube[2][0][1] == 'G' && cube[0][1][0] == 'O'){
            return "LeftGOOrientation";
        }
        else if (cube[3][0][1] == 'G' && cube[0][0][1] == 'O'){
            return "BackGOOrientation";
        }
        else if (cube[4][0][1] == 'G' && cube[0][1][2] == 'O'){
            return "RightGOOrientation";
        }
        return "misaligned";
    }
    private void   handleMisalignedFrontRight() {
        if (cube[1][1][2] == 'O' && cube[4][1][0] == 'G'){
            cubeMoves.performAlgoF2L(new String[]{"E2Algo"});
            solveFrontRightEdge();
        }
        else if ((cube[3][1][0] == 'O' && cube[4][1][2] == 'G') ||  (cube[3][1][0] == 'G' && cube[4][1][2] == 'O')){
            cubeMoves.performAlgoF2L(new String[]{"E3Algo"});
            solveFrontRightEdge();
        }
        else if ((cube[3][1][2] == 'O' && cube[2][1][0] == 'G') ||  (cube[3][1][2] == 'G' && cube[2][1][0] == 'O')){
            cubeMoves.performAlgoF2L(new String[]{"E4Algo"});
            solveFrontRightEdge();
        }
    }
    private void solveBackRightEdge() {
        String orientationCase = getCornerBOOrientationCase();
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontOBOrientation"):
                cubeMoves.performAlgoF2L(new String[]{"OBAlgo"});
                break;
            case ("LeftOBOrientation"):
                cubeMoves.performMoves(new String[]{"T`"});
                cubeMoves.performAlgoF2L(new String[]{"OBAlgo"});
                break;
            case("BackOBOrientation"):
                cubeMoves.performMoves(new String[]{"T", "T"});
                cubeMoves.performAlgoF2L(new String[]{"OBAlgo"});
                break;
            case ("RightOBOrientation"):
                cubeMoves.performMoves(new String[]{"T"});
                cubeMoves.performAlgoF2L(new String[]{"OBAlgo"});
                break;
            case("FrontBOOrientation"):
                cubeMoves.performMoves(new String[]{"T"});
                cubeMoves.performAlgoF2L(new String[]{"BOAlgo"});
                break;
            case ("LeftBOOrientation"):
                cubeMoves.performAlgoF2L(new String[]{"BOAlgo"});
                break;
            case("BackBOOrientation"):
                cubeMoves.performMoves(new String[]{"T`"});
                cubeMoves.performAlgoF2L(new String[]{"BOAlgo"});
                break;
            case ("RightBOOrientation"):
                cubeMoves.performMoves(new String[]{"T", "T"});
                cubeMoves.performAlgoF2L(new String[]{"BOAlgo"});
                break;
            default:
                handleMisalignedBackRight();
                break;
        }
    }
    private String getCornerBOOrientationCase() {
        if(cube[3][1][0] == 'B' && cube[4][1][2] == 'O'){
            return "correctOrientation";
        }
        else if (cube[1][0][1] == 'O' && cube[0][2][1] == 'B'){
            return "FrontOBOrientation";
        }
        else if (cube[2][0][1] == 'O' && cube[0][1][0] == 'B'){
            return "LeftOBOrientation";
        }
        else if (cube[3][0][1] == 'O' && cube[0][0][1] == 'B'){
            return "BackOBOrientation";
        }
        else if (cube[4][0][1] == 'O' && cube[0][1][2] == 'B'){
            return "RightOBOrientation";
        }
        else if (cube[1][0][1] == 'B' && cube[0][2][1] == 'O'){
            return "FrontBOOrientation";
        }
        else if (cube[2][0][1] == 'B' && cube[0][1][0] == 'O'){
            return "LeftBOOrientation";
        }
        else if (cube[3][0][1] == 'B' && cube[0][0][1] == 'O'){
            return "BackBOOrientation";
        }
        else if (cube[4][0][1] == 'B' && cube[0][1][2] == 'O'){
            return "RightBOOrientation";
        }
        return "misaligned";
    }
    private void   handleMisalignedBackRight() {
        if (cube[3][1][0] == 'O' && cube[4][1][2] == 'B'){
            cubeMoves.performAlgoF2L(new String[]{"E3Algo"});
            solveBackRightEdge();
        }
        else if ((cube[3][1][2] == 'O' && cube[2][1][0] == 'B') ||  (cube[3][1][2] == 'B' && cube[2][1][0] == 'O')){
            cubeMoves.performAlgoF2L(new String[]{"E4Algo"});
            solveBackRightEdge();
        }
    }
    private void solveBackLeftEdge() {
        String orientationCase = getCornerBROrientationCase();
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontRBOrientation"):
                cubeMoves.performAlgoF2L(new String[]{"RBAlgo"});
                break;
            case ("LeftRBOrientation"):
                cubeMoves.performMoves(new String[]{"T`"});
                cubeMoves.performAlgoF2L(new String[]{"RBAlgo"});
                break;
            case("BackRBOrientation"):
                cubeMoves.performMoves(new String[]{"T", "T"});
                cubeMoves.performAlgoF2L(new String[]{"RBAlgo"});
                break;
            case ("RightRBOrientation"):
                cubeMoves.performMoves(new String[]{"T"});
                cubeMoves.performAlgoF2L(new String[]{"RBAlgo"});
                break;
            case("FrontBROrientation"):
                cubeMoves.performMoves(new String[]{"T`"});
                cubeMoves.performAlgoF2L(new String[]{"BRAlgo"});
                break;
            case ("LeftBROrientation"):
                cubeMoves.performMoves(new String[]{"T" , "T"});
                cubeMoves.performAlgoF2L(new String[]{"BRAlgo"});
                break;
            case("BackBROrientation"):
                cubeMoves.performMoves(new String[]{"T"});
                cubeMoves.performAlgoF2L(new String[]{"BRAlgo"});
                break;
            case ("RightBROrientation"):
                cubeMoves.performAlgoF2L(new String[]{"BRAlgo"});
                break;
            default:
                handleMisalignedBackLeft();
                break;
        }
    }
    private String getCornerBROrientationCase() {
        if(cube[3][1][2] == 'B' && cube[2][1][0] == 'R'){
            return "correctOrientation";
        }
        else if (cube[1][0][1] == 'R' && cube[0][2][1] == 'B'){
            return "FrontRBOrientation";
        }
        else if (cube[2][0][1] == 'R' && cube[0][1][0] == 'B'){
            return "LeftRBOrientation";
        }
        else if (cube[3][0][1] == 'R' && cube[0][0][1] == 'B'){
            return "BackRBOrientation";
        }
        else if (cube[4][0][1] == 'R' && cube[0][1][2] == 'B'){
            return "RightRBOrientation";
        }
        else if (cube[1][0][1] == 'B' && cube[0][2][1] == 'R'){
            return "FrontBROrientation";
        }
        else if (cube[2][0][1] == 'B' && cube[0][1][0] == 'R'){
            return "LeftBROrientation";
        }
        else if (cube[3][0][1] == 'B' && cube[0][0][1] == 'R'){
            return "BackBROrientation";
        }
        else if (cube[4][0][1] == 'B' && cube[0][1][2] == 'R'){
            return "RightBROrientation";
        }
        return "misaligned";
    }
    private void   handleMisalignedBackLeft() {
        if (cube[3][1][2] == 'R' && cube[2][1][0] == 'B'){
            cubeMoves.performAlgoF2L(new String[]{"E4Algo"});
            solveBackLeftEdge();
        }
    }
}