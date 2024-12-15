import java.util.List;

public class OLL{
    final private char[][][] cube;
    final private CubeMoves cubeMoves;
    private final List<String> moveRecord;

    public OLL(RubiksCube rubiksCube, List<String> moveRecord) {
        this.cube = rubiksCube.getCube();                                                                                                                                // getter method
        this.cubeMoves = new CubeMoves(rubiksCube);
        this.moveRecord = moveRecord;
    }

    public void solveOLL(){
        solveTopCross();                                                                                                                                                            //Method to solve OLL
        solveOLLAlgo();
    }

    private void solveTopCross(){
        String orientationCase = getTopCrossOrientation();                                                                                                      //method to solve top cross
        switch(orientationCase){
            case "orientationCross":
                break;
            case "orientationCentreDot":
                String[] dotSequence = {"F" , "R" , "T" , "R`" , "T`" , "F`" , "f" , "R" , "T" , "R`" , "T`" , "f`"};
                performAndLogMoves(dotSequence);
                break;
            case "orientationI":
                String[] iSequence = {"F" , "R" , "T" , "R`" , "T`" , "F`"};
                performAndLogMoves(iSequence);
                break;
            case "orientationL":
                String[] lSequence = {"f" , "R" , "T" , "R`" , "T`" , "f`"};
                performAndLogMoves(lSequence);
                break;
        }
    }

    private String getTopCrossOrientation(){
        if((cube[0][1][1] == 'Y') && (cube[0][0][1] == 'Y') && (cube[0][1][0] == 'Y') && (cube[0][1][2] == 'Y') && (cube[0][2][1] == 'Y')){                                     //method to understand the top face orientation for cross
            return "orientationCross";
        }
       else if((cube[0][1][1] == 'Y') && (cube[0][0][1] != 'Y') && (cube[0][1][0] != 'Y') && (cube[0][1][2] != 'Y') && (cube[0][2][1] != 'Y')){
            return "orientationCentreDot";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] != 'Y') && (cube[0][1][0] == 'Y') && (cube[0][1][2] == 'Y') && (cube[0][2][1] != 'Y')){
            return "orientationI";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] == 'Y') && (cube[0][1][0] != 'Y') && (cube[0][1][2] != 'Y') && (cube[0][2][1] == 'Y')){
            performAndLogMoves(new String[]{"T"});
            return "orientationI";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] != 'Y') && (cube[0][1][0] != 'Y') && (cube[0][1][2] == 'Y') && (cube[0][2][1] == 'Y')){
            return "orientationL";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] != 'Y') && (cube[0][1][0] == 'Y') && (cube[0][1][2] != 'Y') && (cube[0][2][1] == 'Y')){
            performAndLogMoves(new  String[]{"T`"});
            return "orientationL";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] == 'Y') && (cube[0][1][0] == 'Y') && (cube[0][1][2] != 'Y') && (cube[0][2][1] != 'Y')){
            performAndLogMoves(new  String[]{"T" , "T"});
            return "orientationL";
        }
        else if((cube[0][1][1] == 'Y') && (cube[0][0][1] == 'Y') && (cube[0][1][0] != 'Y') && (cube[0][1][2] == 'Y') && (cube[0][2][1] != 'Y')){
            performAndLogMoves(new  String[]{"T"});
            return "orientationL";
        }
        return"misaligned";
    }

    private void solveOLLAlgo(){
        String orientationCase = getTopOrientation();                                                                                                                                                       //method to solve yellow face
        switch(orientationCase){
            case "orientationOLL":
                break;
            case "orientationAntiSune":
                String[] antiSuneSequence = {"R" , "T" , "T" , "R`" , "T`" , "R" , "T`" , "R`"};
                performAndLogMoves(antiSuneSequence);
                break;
            case "orientationH":
                String[] hSequence = {"R" , "T" , "R`" , "T" , "R" ,"T`" , "R`" , "T" , "R" , "T" , "T" , "R`"};
                performAndLogMoves(hSequence);
                break;
            case "orientationLSeg":
                String[] lSequence = {"F" , "R`" , "F`" , "r" , "T" , "R" , "T`" , "r`"};
                performAndLogMoves(lSequence);
                break;
            case "orientationPi":
                String[] piSequence = {"R" , "T" , "T" , "R" , "R" , "T`" ,"R" , "R" , "T`" , "R" , "R" , "T" , "T" , "R"};
                performAndLogMoves(piSequence);
                break;
            case "orientationSune":
                String[] suneSequence = {"R" , "T" , "R`" , "T" , "R" , "T" , "T" , "R`"};
                performAndLogMoves(suneSequence);
                break;
            case "orientationT":
                String[] tSequence = {"r" , "T" , "R`" , "T`" , "r`" , "F" , "R" ,"F`"};
                performAndLogMoves(tSequence);
                break;
            case "orientationU":
                String[] uSequence = {"R" , "R" , "B" , "R`" , "T" , "T" , "R" , "B`" , "R`" , "T" , "T" , "R`"};
                performAndLogMoves(uSequence);
                break;
        }
    }

    private String getTopOrientation(){
        if(isOLL()){                                                                                                                                                                                                                        //method to get top orientation
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
            performAndLogMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' &&  cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[4][0][0] == 'Y' && cube[3][0][0] == 'Y' && cube[2][0][0] == 'Y'){
            performAndLogMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[4][0][0] == 'Y' && cube[3][0][0] == 'Y'){
            performAndLogMoves(new String[]{"T"});
            return true;
        }
        return false;
    }

    private boolean isH(){
        if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][0] == 'Y' && cube[2][0][2] == 'Y' && cube[4][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[1][0][2] == 'Y' && cube[3][0][0] == 'Y' && cube[3][0][2] == 'Y'){
            performAndLogMoves(new String[]{"T"});
            return true;
        }
        return false;
    }

    private boolean isL(){
        if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[1][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][0] == 'Y' && cube[1][0][2] == 'Y'){
            performAndLogMoves(new String[]{"T`"});
            return true;
        }
        else if((cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' &&cube[3][0][0] == 'Y' && cube[2][0][2] == 'Y')){
            performAndLogMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' &&  cube[4][0][0] == 'Y' && cube[3][0][2] == 'Y'){
            performAndLogMoves(new String[]{"T"});
            return true;
        }
        return false;
    }

    private boolean isPi(){
        if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][2] == 'Y' && cube[2][0][0] == 'Y' && cube[2][0][2] == 'Y' && cube[3][0][0] == 'Y'){
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][2] == 'Y' && cube[3][0][0] == 'Y' && cube[3][0][2] == 'Y' && cube[4][0][0] == 'Y'){
            performAndLogMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[3][0][2] == 'Y' && cube[4][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            performAndLogMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[1][0][2] == 'Y' && cube[2][0][0] == 'Y' && cube[4][0][2] == 'Y'){
            performAndLogMoves(new String[]{"T"});
            return true;
        }
        return false;
    }

    private  boolean isSune(){
        if(cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][2] == 'Y' && cube[4][0][2] == 'Y' && cube[3][0][2] == 'Y') {
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][2] == 'Y' && cube[1][0][2] == 'Y' && cube[4][0][2] == 'Y') {
            performAndLogMoves(new String[]{"T`"});
            return true;
        }
       else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[3][0][2] == 'Y' && cube[2][0][2] == 'Y' && cube[1][0][2] == 'Y') {
            performAndLogMoves(new String[]{"T", "T"});
            return true;
        }
      else  if(cube[0][0][1] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[4][0][2] == 'Y' && cube[3][0][2] == 'Y' && cube[2][0][2] == 'Y') {
            performAndLogMoves(new String[]{"T"});
            return true;
        }
        return false;
    }

    private  boolean isT(){
        if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[1][0][0] == 'Y' && cube[3][0][2] == 'Y') {
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[2][0][0] == 'Y' && cube[4][0][2] == 'Y') {
            performAndLogMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][2] == 'Y' && cube[3][0][0] == 'Y') {
            performAndLogMoves(new String[]{"T", "T"});
            return true;
        }
       else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[2][0][2] == 'Y' && cube[4][0][0] == 'Y') {
            performAndLogMoves(new String[]{"T"});
            return true;
        }
        return false;
    }

    private  boolean isU(){
        if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[1][0][0] == 'Y' && cube[1][0][2] == 'Y') {
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][0][2] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[2][0][0] == 'Y' && cube[2][0][2] == 'Y') {
            performAndLogMoves(new String[]{"T`"});
            return true;
        }
        else if(cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[0][2][2] == 'Y' && cube[3][0][0] == 'Y' && cube[3][0][2] == 'Y') {
            performAndLogMoves(new String[]{"T" , "T"});
            return true;
        }
        else if(cube[0][0][0] == 'Y' && cube[0][0][1] == 'Y' && cube[0][2][0] == 'Y' && cube[0][2][1] == 'Y' && cube[4][0][0] == 'Y' && cube[4][0][2] == 'Y') {
            performAndLogMoves(new String[]{"T"});
            return true;
        }
        return false;
    }

    private void performAndLogMoves(String[] moves) {
        cubeMoves.performMoves(moves);                                                                                                                                                                            //perform moves
        moveRecord.addAll(List.of(moves));                                                                                                                                                                          // Log moves into the shared list
    }
}