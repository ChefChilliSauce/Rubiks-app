public class OLL{
final private char[][][] cube;
final private CubeMoves cubeMoves;

    public OLL(RubiksCube rubiksCube) {
        // Directly assign the cube array to a member variable
        this.cube = rubiksCube.getCube();
        // Initialize CubeMoves with the RubiksCube instance
        this.cubeMoves = new CubeMoves(rubiksCube);
    }
    public void solveOLL(){
        solveTopCross();
        solveOLLAlgo();
    }
    private void solveTopCross(){
        String orientationCase = getTopCrossOrientation();
        switch(orientationCase){
            case "orientationCross":
                break;
            case "orientationCentreDot":
                performMoves(new String[]{"dotOLL"});
                break;
            case "orientationI":
                performMoves(new String[]{"iOLL"});
                break;
            case "orientationL":
                performMoves(new String[]{"lOLL"});
                break;
        }
    }
    private String getTopCrossOrientation(){
        if((cube[0][1][1] == 'Y') && (cube[0][0][1] == 'Y') && (cube[0][1][0] == 'Y') && (cube[0][1][2] == 'Y') && (cube[0][2][1] == 'Y')){
            return "orientationCross";
        }
       else if((cube[0][1][1] == 'Y') && (cube[0][0][1] != 'Y') && (cube[0][1][0] != 'Y') && (cube[0][1][2] != 'Y') && (cube[0][2][1] != 'Y')){
            return "orientationCentreDot";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] != 'Y') && (cube[0][1][0] == 'Y') && (cube[0][1][2] == 'Y') && (cube[0][2][1] != 'Y')){
            return "orientationI";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] == 'Y') && (cube[0][1][0] != 'Y') && (cube[0][1][2] != 'Y') && (cube[0][2][1] == 'Y')){
            performMoves(new String[]{"T"});
            return "orientationI";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] != 'Y') && (cube[0][1][0] != 'Y') && (cube[0][1][2] == 'Y') && (cube[0][2][1] == 'Y')){
            return "orientationL";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] != 'Y') && (cube[0][1][0] == 'Y') && (cube[0][1][2] != 'Y') && (cube[0][2][1] == 'Y')){
            performMoves(new  String[]{"T`"});
            return "orientationL";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] == 'Y') && (cube[0][1][0] == 'Y') && (cube[0][1][2] != 'Y') && (cube[0][2][1] != 'Y')){
            performMoves(new  String[]{"T" , "T"});
            return "orientationL";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] == 'Y') && (cube[0][1][0] != 'Y') && (cube[0][1][2] == 'Y') && (cube[0][2][1] != 'Y')){
            performMoves(new  String[]{"T"});
            return "orientationL";
        }
        return"misaligned";
    }
    private void solveOLLAlgo(){
        String orientationCase = getTopOrientation();
        switch(orientationCase){
            case "orientationOLL":
                break;
            case "orientationAntiSune":
                performMoves(new String[]{"algoAntiSune"});
                break;
            case "orientationH":
                performMoves(new String[]{"algoH"});
                break;
            case "orientationLSeg":
                performMoves(new String[]{"algoL"});
                break;
            case "orientationPi":
                performMoves(new String[]{"algoPi"});
                break;
            case "orientationSune":
                performMoves(new String[]{"algoSune"});
                break;
            case "orientationT":
                performMoves(new String[]{"algoT"});
                break;
            case "orientationU":
                performMoves(new String[]{"algoU"});
                break;
        }
    }
    private String getTopOrientation(){
        if(isOLL()){
            return "orientationOLL";
        }
       else if(isAntiSune()){
            return "orientationAntiSune";
        }
        else if (isH()) {
            return "orientationH";
        }
        else if(isL()){
            return "orientationLSeg";
        }
        else if(isPi()){
            return "orientationPi";
        }
        else if(isSune()){
            return "orientationSune";
        }
        else if(isT()){
            return "orientationT";
        }
        else if(isU()){
            return "orientationU";
        }
        return "misaligned";
    }
    private boolean isOLL(){
        return cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][1][0] == 'Y' && cube[0][1][1] == 'Y' && cube[0][1][2] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y';
    }
    private boolean isAntiSune(){
        if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][0] == 'Y' && cube[1][0][0] == 'Y' && cube[4][0][0] == 'Y'){
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[3][0][0] == 'Y' && cube[2][0][0] == 'Y' && cube[1][0][0] == 'Y'){
            performMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' &&  cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[4][0][0] == 'Y' && cube[3][0][0] == 'Y' && cube[2][0][0] == 'Y'){
            performMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[4][0][0] == 'Y' && cube[3][0][0] == 'Y'){
            performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private boolean isH(){
        if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][0] == 'Y' && cube[2][0][2] == 'Y' && cube[4][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[1][0][2] == 'Y' && cube[3][0][0] == 'Y' && cube[3][0][2] == 'Y'){
            performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private boolean isL(){
        if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[1][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][0] == 'Y' && cube[1][0][2] == 'Y'){
            performMoves(new String[]{"T`"});
            return true;
        }
        else if((cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' &&cube[3][0][0] == 'Y' && cube[2][0][2] == 'Y')){
            performMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' &&  cube[4][0][0] == 'Y' && cube[3][0][2] == 'Y'){
            performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private boolean isPi(){
        if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][2] == 'Y' && cube[2][0][0] == 'Y' && cube[2][0][2] == 'Y' && cube[3][0][0] == 'Y'){
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][2] == 'Y' && cube[3][0][0] == 'Y' && cube[3][0][2] == 'Y' && cube[4][0][0] == 'Y'){
            performMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[3][0][2] == 'Y' && cube[4][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            performMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[1][0][2] == 'Y' && cube[2][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private  boolean isSune(){
        if(cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][2] == 'Y' && cube[4][0][2] == 'Y' && cube[3][0][2] == 'Y') {
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][2] == 'Y' && cube[1][0][2] == 'Y' && cube[4][0][2] == 'Y') {
            performMoves(new String[]{"T`"});
            return true;
        }
       else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[3][0][2] == 'Y' && cube[2][0][2] == 'Y' && cube[1][0][2] == 'Y') {
            performMoves(new String[]{"T", "T"});
            return true;
        }
      else  if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[4][0][2] == 'Y' && cube[3][0][2] == 'Y' && cube[2][0][2] == 'Y') {
            performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private  boolean isT(){
        if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[1][0][0] == 'Y' && cube[3][0][2] == 'Y') {
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[2][0][0] == 'Y' && cube[4][0][2] == 'Y') {
            performMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][2] == 'Y' && cube[3][0][0] == 'Y') {
            performMoves(new String[]{"T", "T"});
            return true;
        }
       else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][2] == 'Y' && cube[4][0][0] == 'Y') {
            performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private  boolean isU(){
        if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[1][0][2] == 'Y') {
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[2][0][0] == 'Y' && cube[2][0][2] == 'Y') {
            performMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[3][0][0] == 'Y' && cube[3][0][2] == 'Y') {
            performMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[4][0][0] == 'Y' && cube[4][0][2] == 'Y') {
            performMoves(new String[]{"T"});
            return true;
        }
        return false;
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
                case"dotOLL":
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    cubeMoves.rotateDoubleFrontClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateDoubleFrontCounterClockwise();
                    break;
                case"iOLL":
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    break;
                case "lOLL":
                    cubeMoves.rotateDoubleFrontClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateDoubleFrontCounterClockwise();
                    break;
                case "algoAntiSune":
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    break;
                case "algoH":
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    break;
                case "algoL":
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    cubeMoves.rotateDoubleRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateDoubleRightCounterClockwise();
                    break;
                case "algoPi":
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    break;
                case "algoSune":
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    break;
                case "algoT":
                    cubeMoves.rotateDoubleRightClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopCounterClockwise();
                    cubeMoves.rotateDoubleRightCounterClockwise();
                    cubeMoves.rotateFrontClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateFrontCounterClockwise();
                    break;
                case "algoU":
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateBottomClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightClockwise();
                    cubeMoves.rotateBottomCounterClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateTopClockwise();
                    cubeMoves.rotateRightCounterClockwise();
                    break;
            }
        }
    }
}