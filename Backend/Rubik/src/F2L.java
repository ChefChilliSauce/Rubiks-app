import java.util.List;

public class F2L {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;
    private final List<String> moveRecord;

    public F2L(RubiksCube rubiksCube, List<String> moveRecord) {
        this.cube = rubiksCube.getCube();                                                                                                                                                   // getter method Directly assign the cube array to a member variable
        this.cubeMoves = new CubeMoves(rubiksCube);                                                                                                                           // Initialize CubeMoves with the RubiksCube instance
        this.moveRecord = moveRecord;
    }

    public void solveSecondLayer() {
        solveFrontLeftCorner();                                                                                                                                                                       //Method to solve F2L
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
        switch (orientationCase) {                                                                                                                                                                      //method to solve green white red corner
            case "correctlyOriented":
                break;
            case "GRWOrientation":
                executeRepeatingMoves("L` T` L T", 4);
                break;
            case "RWGOrientation":
                executeRepeatingMoves("L` T` L T", 2);
                break;
            case "topWRGOrientation":
                performAndLogMoves(new String[]{"T`", "L`", "T", "L"});
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
        } else if (cube[5][0][0] == 'G' && cube[1][2][0] == 'R') {
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
        else if ((cube[5][2][0] == 'G' && cube[3][2][2] == 'W' &&  cube[2][2][0] == 'R')  ||
                (cube[5][2][0] == 'W' && cube[3][2][2] == 'R' &&  cube[2][2][0] == 'G') ||
                (cube[5][2][0] == 'R' && cube[3][2][2] == 'G' &&  cube[2][2][0] == 'W')) {
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
                performAndLogMoves(new String[]{"R", "T", "R`"});
                break;
            case "BottomBackRightCorner":
                performAndLogMoves(new String[]{"Ba", "T", "T" , "Ba`"});
                break;
            case "BottomBackLeftCorner":
                performAndLogMoves(new String[]{"Ba`", "T`", "Ba"});
                break;
        }
    }

    private void alignTopLayerC1(String pos) {
        switch (pos) {
            case "TopFrontRightCorner":
                performAndLogMoves(new String[]{"T"});
                break;
            case "TopBackRightCorner":
                performAndLogMoves(new String[]{"T", "T"});
                break;
            case "TopBackLeftCorner":
                performAndLogMoves(new String[]{"T`"});
                break;
        }
    }

    private void solveFrontRightCorner() {
        String orientationCase = getCornerGOWOrientationCase();                                                                                                                                                                         //method to solve green white orange corner
        switch (orientationCase) {
            case "correctlyOriented":
                break;
            case "OWGOrientation":
                executeRepeatingMoves("R T R` T`", 2);
                break;
            case "WGOOrientation":
                executeRepeatingMoves("R T R` T`", 4);
                break;
            case "topWOGOrientation":
                performAndLogMoves(new String[]{"T", "R", "T`", "R`"});
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

    private String getCornerGOWOrientationCase() {
        // Logic to determine the orientation of the corner piece and return a case identifier
        if (cube[5][0][2] == 'W' && cube[1][2][2] == 'G') {
            return "correctlyOriented";
        } else if (cube[5][0][2] == 'G' && cube[1][2][2] == 'O') {
            return "OWGOrientation";
        } else if (cube[5][0][2] == 'O' && cube[1][2][2] == 'W') {
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
                performAndLogMoves(new String[]{"Ba", "T", "Ba`"});
                break;
            case "BottomBackLeftCorner":
                performAndLogMoves(new String[]{"Ba`", "T", "T" , "Ba"});
                break;
        }
    }

    private void alignTopLayerC2(String pos) {
        switch (pos) {
            case "TopFrontLeftCorner":
                performAndLogMoves(new String[]{"T`"});
                break;
            case "TopBackRightCorner":
                performAndLogMoves(new String[]{"T"});
                break;
            case "TopBackLeftCorner":
                performAndLogMoves(new String[]{"T", "T"});
                break;
        }
    }

    private void solveBackRightCorner() {
        String orientationCase = getCornerBOWOrientationCase();                                                                                                                                                                                 //method to solve blue white orange corner
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
                performAndLogMoves(new String[]{"Ba", "T", "Ba`", "T`"});
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
            performAndLogMoves(new String[]{"Ba`", "T", "T", "Ba"});
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
                performAndLogMoves(new String[]{"T", "T"});
                break;
            case "TopFrontRightCorner":
                performAndLogMoves(new String[]{"T`"});
                break;
            case "TopBackLeftCorner":
                performAndLogMoves(new String[]{"T"});
                break;
        }
    }

    private void solveBackLeftCorner() {
        String orientationCase = getCornerBRWOrientationCase();                                                                                                                                                                     //method to solve blue white red corner
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
                performAndLogMoves(new String[]{"T`", "Ba`", "T", "Ba"});
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
                performAndLogMoves(new String[]{"T", "T"});
                break;
            case "TopBackRightCorner":
                performAndLogMoves(new String[]{"T`"});
                break;
            case "TopFrontLeftCorner":
                performAndLogMoves(new String[]{"T"});
                break;
        }
    }

    private void executeRepeatingMoves(String moves, int times) {
        for (int i = 0; i < times; i++) {
            String[] movesArray = moves.trim().split("\\s+");
            performAndLogMoves(movesArray);
        }
    }

    private void solveFrontLeftEdge() {
        String orientationCase = getCornerGROrientationCase();                                                                                                                                      //method to solve green red edge
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontRGOrientation"):
                String[] frgSequence = {"T", "T" , "F" , "T" , "F`" , "T`" , "L`" , "T`" , "L"};
                performAndLogMoves(frgSequence);
                break;
            case ("LeftRGOrientation"):
                String[] lrgSequence = {"T" , "F" , "T" , "F`" , "T`" , "L`" , "T`" , "L"};
                performAndLogMoves(lrgSequence);
                break;
            case("BackRGOrientation"):
                String[] brgSequence = {"F" , "T" , "F`" , "T`" , "L`" , "T`" , "L"};
                performAndLogMoves(brgSequence);
                break;
            case ("RightRGOrientation"):
                String[] rrgSequence = {"T`" , "F" , "T" , "F`" , "T`" , "L`" , "T`" , "L"};
                performAndLogMoves(rrgSequence);
                break;
            case("FrontGROrientation"):
                String[] fgrSequence = {"T`" , "L`" , "T`" , "L" , "T" , "F" , "T" , "F`"};
                performAndLogMoves(fgrSequence);
                break;
            case ("LeftGROrientation"):
                String[] lgrSequence = {"T", "T" , "L`" , "T`" , "L" , "T" , "F" , "T" , "F`"};
                performAndLogMoves(lgrSequence);
                break;
            case("BackGROrientation"):
                String[] bgrSequence = {"T" , "L`" , "T`" , "L" , "T" , "F" , "T" , "F`"};
                performAndLogMoves(bgrSequence);
                break;
            case ("RightGROrientation"):
                String[] rgrSequence = {"L`" , "T`" , "L" , "T" , "F" , "T" , "F`"};
                performAndLogMoves(rgrSequence);
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
            String[] mis1Sequence = {"T`" , "L`" , "T" , "L" , "T" , "F" , "T`" , "F`"};
            performAndLogMoves(mis1Sequence);
            solveFrontLeftEdge();
        }
        else if ((cube[1][1][2] == 'R' && cube[4][1][0] == 'G') || (cube[1][1][2] == 'G' && cube[4][1][0] == 'R')){
            String[] mis2Sequence = {"T" , "R" , "T`" , "R`" , "T`" ,"F`" , "T" , "F"};
            performAndLogMoves(mis2Sequence);
            solveFrontLeftEdge();
        }
        else if ((cube[3][1][0] == 'R' && cube[4][1][2] == 'G') ||  (cube[3][1][0] == 'G' && cube[4][1][2] == 'R')){
            String[] mis3Sequence = {"T`" , "R`" , "T" ,"R" , "T" , "Ba" , "T`" , "Ba`"};
            performAndLogMoves(mis3Sequence);
            solveFrontLeftEdge();
        }
        else if ((cube[3][1][2] == 'R' && cube[2][1][0] == 'G') ||  (cube[3][1][2] == 'G' && cube[2][1][0] == 'R')){
            String[] mis4Sequence = {"T" , "L" , "T`" , "L`" , "T`" , "Ba`" , "T" , "Ba"};
            performAndLogMoves(mis4Sequence);
            solveFrontLeftEdge();
        }
    }

    private void solveFrontRightEdge() {
        String orientationCase = getCornerGOOrientationCase();                                                                                                              //method to solve green orange edge
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontOGOrientation"):
                String[] fogSequence = {"T", "T" , "F`" , "T`" , "F" , "T" , "R" , "T" , "R`"};
                performAndLogMoves(fogSequence);
                break;
            case ("LeftOGOrientation"):
                String[] logSequence = {"T" , "F`" , "T`" , "F" , "T" , "R" , "T" , "R`"};
                performAndLogMoves(logSequence);
                break;
            case("BackOGOrientation"):
                String[] bogSequence = {"F`" , "T`" , "F" , "T" , "R" , "T" , "R`"};
                performAndLogMoves(bogSequence);
                break;
            case ("RightOGOrientation"):
                String[] rogSequence = {"T`" , "F`" , "T`" , "F" , "T" , "R" , "T" , "R`"};
                performAndLogMoves(rogSequence);
                break;
            case("FrontGOOrientation"):
                String[] fgoSequence = {"T" , "R" , "T" , "R`" , "T`" , "F`" , "T`" , "F"};
                performAndLogMoves(fgoSequence);
                break;
            case ("LeftGOOrientation"):
                String[] lgoSequence = {"R" , "T" , "R`" , "T`" , "F`" , "T`" , "F"};
                performAndLogMoves(lgoSequence);
                break;
            case("BackGOOrientation"):
                String[] bgoSequence = {"T`" , "R" , "T" , "R`" , "T`" , "F`" , "T`" , "F"};
                performAndLogMoves(bgoSequence);
                break;
            case ("RightGOOrientation"):
                String[] rgoSequence = {"T", "T" , "R" , "T" , "R`" , "T`" , "F`" , "T`" , "F"};
                performAndLogMoves(rgoSequence);
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
            String[] mis1Sequence = {"T" , "R" , "T`" , "R`" , "T`" ,"F`" , "T" , "F"};
            performAndLogMoves(mis1Sequence);
            solveFrontRightEdge();
        }
        else if ((cube[3][1][0] == 'O' && cube[4][1][2] == 'G') ||  (cube[3][1][0] == 'G' && cube[4][1][2] == 'O')){
            String[] mis2Sequence = {"T`" , "R`" , "T" ,"R" , "T" , "Ba" , "T`" , "Ba`"};
            performAndLogMoves(mis2Sequence);
            solveFrontRightEdge();
        }
        else if ((cube[3][1][2] == 'O' && cube[2][1][0] == 'G') ||  (cube[3][1][2] == 'G' && cube[2][1][0] == 'O')){
            String[] mis3Sequence = {"T" , "L" , "T`" , "L`" , "T`" , "Ba`" , "T" , "Ba"};
            performAndLogMoves(mis3Sequence);
            solveFrontRightEdge();
        }
    }

    private void solveBackRightEdge() {
        String orientationCase = getCornerBOOrientationCase();                                                                                                                                      //method to solve blue orange edge
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontOBOrientation"):
                String[] fobSequence = {"Ba" , "T" , "Ba`" , "T`" , "R`" , "T`" , "R"};
                performAndLogMoves(fobSequence);
                break;
            case ("LeftOBOrientation"):
                String[] lobSequence = {"T`" , "Ba" , "T" , "Ba`" , "T`" , "R`" , "T`" , "R"};
                performAndLogMoves(lobSequence);
                break;
            case("BackOBOrientation"):
                String[] bobSequence = {"T", "T" , "Ba" , "T" , "Ba`" , "T`" , "R`" , "T`" , "R"};
                performAndLogMoves(bobSequence);
                break;
            case ("RightOBOrientation"):
                String[] robSequence = {"T" , "Ba" , "T" , "Ba`" , "T`" , "R`" , "T`" , "R"};
                performAndLogMoves(robSequence);
                break;
            case("FrontBOOrientation"):
                String[] fboSequence = {"T" , "R`" , "T`" , "R" , "T" , "Ba" , "T" , "Ba`"};
                performAndLogMoves(fboSequence);
                break;
            case ("LeftBOOrientation"):
                String[] lboSequence = {"R`" , "T`" , "R" , "T" , "Ba" , "T" , "Ba`"};
                performAndLogMoves(lboSequence);
                break;
            case("BackBOOrientation"):
                String[] bboSequence = {"T`" , "R`" , "T`" , "R" , "T" , "Ba" , "T" , "Ba`"};
                performAndLogMoves(bboSequence);
                break;
            case ("RightBOOrientation"):
                String[] rboSequence = {"T", "T" , "R`" , "T`" , "R" , "T" , "Ba" , "T" , "Ba`"};
                performAndLogMoves(rboSequence);
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
            String[] mis1Sequence = {"T`" , "R`" , "T" ,"R" , "T" , "Ba" , "T`" , "Ba`"};
            performAndLogMoves(mis1Sequence);
            solveBackRightEdge();
        }
        else if ((cube[3][1][2] == 'O' && cube[2][1][0] == 'B') ||  (cube[3][1][2] == 'B' && cube[2][1][0] == 'O')){
            String[] mis2Sequence = {"T" , "L" , "T`" , "L`" , "T`" , "Ba`" , "T" , "Ba"};
            performAndLogMoves(mis2Sequence);
            solveBackRightEdge();
        }
    }

    private void solveBackLeftEdge() {
        String orientationCase = getCornerBROrientationCase();                                                                                                                                                              //method to solve blue red edge
        switch (orientationCase) {
            case ("correctOrientation"):
                break;
            case("FrontRBOrientation"):
                String[] frbSequence = {"Ba`" , "T`" , "Ba" , "T" , "L" , "T" , "L`"};
                performAndLogMoves(frbSequence);
                break;
            case ("LeftRBOrientation"):
                String[] lrbSequence = {"T`" , "Ba`" , "T`" , "Ba" , "T" , "L" , "T" , "L`"};
                performAndLogMoves(lrbSequence);
                break;
            case("BackRBOrientation"):
                String[] brbSequence = {"T", "T" , "Ba`" , "T`" , "Ba" , "T" , "L" , "T" , "L`"};
                performAndLogMoves(brbSequence);
                break;
            case ("RightRBOrientation"):
                String[] rrbSequence = {"T" , "Ba`" , "T`" , "Ba" , "T" , "L" , "T" , "L`"};
                performAndLogMoves(rrbSequence);
                break;
            case("FrontBROrientation"):
                String[] fbrSequence = {"T`" , "L" , "T`" , "L`" , "T`" , "Ba`" , "T" , "Ba"};
                performAndLogMoves(fbrSequence);
                break;
            case ("LeftBROrientation"):
                String[] lbrSequence = {"T" , "T" , "L" , "T`" , "L`" , "T`" , "Ba`" , "T" , "Ba"};
                performAndLogMoves(lbrSequence);
                break;
            case("BackBROrientation"):
                String[] bbrSequence = {"T" , "L" , "T`" , "L`" , "T`" , "Ba`" , "T" , "Ba"};
                performAndLogMoves(bbrSequence);
                break;
            case ("RightBROrientation"):
                String[] rbrSequence = {"L" , "T`" , "L`" , "T`" , "Ba`" , "T" , "Ba"};
                performAndLogMoves(rbrSequence);
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
            String[] mis1Sequence = {"T" , "L" , "T`" , "L`" , "T`" , "Ba`" , "T" , "Ba"};
            performAndLogMoves(mis1Sequence);
            solveBackLeftEdge();
        }
    }

    private void performAndLogMoves(String[] moves) {
        cubeMoves.performMoves(moves);                                                                                                                                                                                                            //perform moves
        moveRecord.addAll(List.of(moves));                                                                                                                                                                                                          // Log moves into the F2L list
    }
}