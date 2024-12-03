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
        solveFrontLeftEdge();
        solveFrontRightEdge();
        solveBackRightEdge();
        solveBackLeftEdge();
    }

    private void solveFrontLeftEdge() {
        String orientationCase = getCornerGROrientationCase();
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontRGOrientation"):
                performMoves(new String[]{"T", "T", "RGAlgo"});
                break;
            case ("LeftRGOrientation"):
                performMoves(new String[]{"T", "RGAlgo"});
                break;
            case("BackRGOrientation"):
                performMoves(new String[]{"RGAlgo"});
                break;
            case ("RightRGOrientation"):
                performMoves(new String[]{"T`", "RGAlgo"});
                break;
            case("FrontGROrientation"):
                performMoves(new String[]{"T`", "GRAlgo"});
                break;
            case ("LeftGROrientation"):
                performMoves(new String[]{"T", "T" , "GRAlgo"});
                break;
            case("BackGROrientation"):
                performMoves(new String[]{"T", "GRAlgo"});
                break;
            case ("RightGROrientation"):
                performMoves(new String[]{"GRAlgo"});
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
            performMoves(new String[]{"E1Algo"});
            solveFrontLeftEdge();
        }
        else if ((cube[1][1][2] == 'R' && cube[4][1][0] == 'G') || (cube[1][1][2] == 'G' && cube[4][1][0] == 'R')){
            performMoves(new String[]{"E2Algo"});
            solveFrontLeftEdge();
        }
        else if ((cube[3][1][0] == 'R' && cube[4][1][2] == 'G') ||  (cube[3][1][0] == 'G' && cube[4][1][2] == 'R')){
            performMoves(new String[]{"E3Algo"});
            solveFrontLeftEdge();
        }
        else if ((cube[3][1][2] == 'R' && cube[2][1][0] == 'G') ||  (cube[3][1][2] == 'G' && cube[2][1][0] == 'R')){
            performMoves(new String[]{"E4Algo"});
            solveFrontLeftEdge();
        }
    }
    private void solveFrontRightEdge() {
        String orientationCase = getCornerGOOrientationCase();
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontOGOrientation"):
                performMoves(new String[]{"T", "T", "OGAlgo"});
                break;
            case ("LeftOGOrientation"):
                performMoves(new String[]{"T", "OGAlgo"});
                break;
            case("BackOGOrientation"):
                performMoves(new String[]{"OGAlgo"});
                break;
            case ("RightOGOrientation"):
                performMoves(new String[]{"T`", "OGAlgo"});
                break;
            case("FrontGOOrientation"):
                performMoves(new String[]{"T", "GOAlgo"});
                break;
            case ("LeftGOOrientation"):
                performMoves(new String[]{"GOAlgo"});
                break;
            case("BackGOOrientation"):
                performMoves(new String[]{"T`", "GOAlgo"});
                break;
            case ("RightGOOrientation"):
                performMoves(new String[]{"T", "T" ,"GOAlgo"});
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
            performMoves(new String[]{"E2Algo"});
            solveFrontRightEdge();
        }
        else if ((cube[3][1][0] == 'O' && cube[4][1][2] == 'G') ||  (cube[3][1][0] == 'G' && cube[4][1][2] == 'O')){
            performMoves(new String[]{"E3Algo"});
            solveFrontRightEdge();
        }
        else if ((cube[3][1][2] == 'O' && cube[2][1][0] == 'G') ||  (cube[3][1][2] == 'G' && cube[2][1][0] == 'O')){
            performMoves(new String[]{"E4Algo"});
            solveFrontRightEdge();
        }
    }
    private void solveBackRightEdge() {
        String orientationCase = getCornerBOOrientationCase();
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontOBOrientation"):
                performMoves(new String[]{ "OBAlgo"});
                break;
            case ("LeftOBOrientation"):
                performMoves(new String[]{"T`", "OBAlgo"});
                break;
            case("BackOBOrientation"):
                performMoves(new String[]{"T", "T" , "OBAlgo"});
                break;
            case ("RightOBOrientation"):
                performMoves(new String[]{"T", "OBAlgo"});
                break;
            case("FrontBOOrientation"):
                performMoves(new String[]{"T", "BOAlgo"});
                break;
            case ("LeftBOOrientation"):
                performMoves(new String[]{"BOAlgo"});
                break;
            case("BackBOOrientation"):
                performMoves(new String[]{"T`", "BOAlgo"});
                break;
            case ("RightBOOrientation"):
                performMoves(new String[]{"T", "T" ,"BOAlgo"});
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
            performMoves(new String[]{"E3Algo"});
            solveBackRightEdge();
        }
        else if ((cube[3][1][2] == 'O' && cube[2][1][0] == 'B') ||  (cube[3][1][2] == 'B' && cube[2][1][0] == 'O')){
            performMoves(new String[]{"E4Algo"});
            solveBackRightEdge();
        }
    }
    private void solveBackLeftEdge() {
        String orientationCase = getCornerBROrientationCase();
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontRBOrientation"):
                performMoves(new String[]{ "RBAlgo"});
                break;
            case ("LeftRBOrientation"):
                performMoves(new String[]{"T`", "RBAlgo"});
                break;
            case("BackRBOrientation"):
                performMoves(new String[]{"T", "T" , "RBAlgo"});
                break;
            case ("RightRBOrientation"):
                performMoves(new String[]{"T", "RBAlgo"});
                break;
            case("FrontBROrientation"):
                performMoves(new String[]{"T`", "BRAlgo"});
                break;
            case ("LeftBROrientation"):
                performMoves(new String[]{"T" , "T" , "BRAlgo"});
                break;
            case("BackBROrientation"):
                performMoves(new String[]{"T", "BRAlgo"});
                break;
            case ("RightBROrientation"):
                performMoves(new String[]{" srBRAlgo"});
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
            performMoves(new String[]{"E4Algo"});
            solveBackLeftEdge();
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
                case "GRAlgo":
                    cubeMoves.rotateLeftCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateLeftClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    break;
                case"RGAlgo":
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateLeftCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateLeftClockwise();
                case "GOAlgo":
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateFrontClockwise();
                    break;
                case"OGAlgo":
                    cubeMoves.rotateFrontCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();;
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                case "BOAlgo":
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();;
                    cubeMoves.rotateBackClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateBackCounterClockwise();
                    break;
                case"OBAlgo":
                    cubeMoves.rotateBackClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateBackCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();;
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightClockwise();
                case "BRAlgo":
                    cubeMoves.rotateLeftClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateLeftCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();;
                    cubeMoves.rotateBackCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateBackClockwise();
                    break;
                case"RBAlgo":
                    cubeMoves.rotateBackCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateBackClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateLeftClockwise();;
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateLeftCounterClockwise();
                case"E1Algo":
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateLeftCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateLeftClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                case"E2Algo":
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateFrontClockwise();
                case"E3Algo":
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateBackClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateBackCounterClockwise();
                case"E4Algo":
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateLeftClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateLeftCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateBackCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateBackClockwise();
            }
        }
    }
}
