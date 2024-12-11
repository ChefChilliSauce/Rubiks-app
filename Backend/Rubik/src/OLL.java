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
                cubeMoves.performAlgoOLL(new String[]{"algoDotCross"});
                break;
            case "orientationI":
                cubeMoves.performAlgoOLL(new String[]{"algoICross"});
                break;
            case "orientationL":
                cubeMoves.performAlgoOLL(new String[]{"algoLCross"});
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
            cubeMoves.performMoves(new String[]{"T"});
            return "orientationI";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] != 'Y') && (cube[0][1][0] != 'Y') && (cube[0][1][2] == 'Y') && (cube[0][2][1] == 'Y')){
            return "orientationL";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] != 'Y') && (cube[0][1][0] == 'Y') && (cube[0][1][2] != 'Y') && (cube[0][2][1] == 'Y')){
            cubeMoves.performMoves(new  String[]{"T`"});
            return "orientationL";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] == 'Y') && (cube[0][1][0] == 'Y') && (cube[0][1][2] != 'Y') && (cube[0][2][1] != 'Y')){
            cubeMoves.performMoves(new  String[]{"T" , "T"});
            return "orientationL";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] == 'Y') && (cube[0][1][0] != 'Y') && (cube[0][1][2] == 'Y') && (cube[0][2][1] != 'Y')){
            cubeMoves.performMoves(new  String[]{"T"});
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
                cubeMoves.performAlgoOLL(new String[]{"algoAntiSune"});
                break;
            case "orientationH":
                cubeMoves.performAlgoOLL(new String[]{"algoH"});
                break;
            case "orientationLSeg":
                cubeMoves.performAlgoOLL(new String[]{"algoL"});
                break;
            case "orientationPi":
                cubeMoves.performAlgoOLL(new String[]{"algoPi"});
                break;
            case "orientationSune":
                cubeMoves.performAlgoOLL(new String[]{"algoSune"});
                break;
            case "orientationT":
                cubeMoves.performAlgoOLL(new String[]{"algoT"});
                break;
            case "orientationU":
                cubeMoves.performAlgoOLL(new String[]{"algoU"});
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
            cubeMoves.performMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' &&  cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[4][0][0] == 'Y' && cube[3][0][0] == 'Y' && cube[2][0][0] == 'Y'){
            cubeMoves.performMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[4][0][0] == 'Y' && cube[3][0][0] == 'Y'){
            cubeMoves.performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private boolean isH(){
        if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][0] == 'Y' && cube[2][0][2] == 'Y' && cube[4][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[1][0][2] == 'Y' && cube[3][0][0] == 'Y' && cube[3][0][2] == 'Y'){
            cubeMoves.performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private boolean isL(){
        if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[1][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][0] == 'Y' && cube[1][0][2] == 'Y'){
            cubeMoves.performMoves(new String[]{"T`"});
            return true;
        }
        else if((cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' &&cube[3][0][0] == 'Y' && cube[2][0][2] == 'Y')){
            cubeMoves.performMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' &&  cube[4][0][0] == 'Y' && cube[3][0][2] == 'Y'){
            cubeMoves.performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private boolean isPi(){
        if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][2] == 'Y' && cube[2][0][0] == 'Y' && cube[2][0][2] == 'Y' && cube[3][0][0] == 'Y'){
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][2] == 'Y' && cube[3][0][0] == 'Y' && cube[3][0][2] == 'Y' && cube[4][0][0] == 'Y'){
            cubeMoves.performMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[3][0][2] == 'Y' && cube[4][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            cubeMoves.performMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[1][0][2] == 'Y' && cube[2][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            cubeMoves.performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private  boolean isSune(){
        if(cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][2] == 'Y' && cube[4][0][2] == 'Y' && cube[3][0][2] == 'Y') {
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][2] == 'Y' && cube[1][0][2] == 'Y' && cube[4][0][2] == 'Y') {
            cubeMoves.performMoves(new String[]{"T`"});
            return true;
        }
       else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[3][0][2] == 'Y' && cube[2][0][2] == 'Y' && cube[1][0][2] == 'Y') {
            cubeMoves.performMoves(new String[]{"T", "T"});
            return true;
        }
      else  if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[4][0][2] == 'Y' && cube[3][0][2] == 'Y' && cube[2][0][2] == 'Y') {
            cubeMoves.performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private  boolean isT(){
        if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[1][0][0] == 'Y' && cube[3][0][2] == 'Y') {
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[2][0][0] == 'Y' && cube[4][0][2] == 'Y') {
            cubeMoves.performMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][2] == 'Y' && cube[3][0][0] == 'Y') {
            cubeMoves.performMoves(new String[]{"T", "T"});
            return true;
        }
       else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][2] == 'Y' && cube[4][0][0] == 'Y') {
            cubeMoves.performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
    private  boolean isU(){
        if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[1][0][2] == 'Y') {
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[2][0][0] == 'Y' && cube[2][0][2] == 'Y') {
            cubeMoves.performMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[3][0][0] == 'Y' && cube[3][0][2] == 'Y') {
            cubeMoves.performMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[4][0][0] == 'Y' && cube[4][0][2] == 'Y') {
            cubeMoves.performMoves(new String[]{"T"});
            return true;
        }
        return false;
    }
}