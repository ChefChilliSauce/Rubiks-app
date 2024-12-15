public class CubeMoves {
    final private char[][][] cube;                                          //cube state

    public CubeMoves(RubiksCube rubiksCube) {
        this.cube = rubiksCube.getCube();                       //Getter method for cube
    }

    private void rotateFaceClockwise(int face){
        char temp = cube[face][0][0];                          //Method to rotate face moving clockwise
        cube[face][0][0] = cube[face][2][0];                //[00 01 02]-------------[20 10 00]
        cube[face][2][0] = cube[face][2][2];                //[10 11 12]-------------[21 11 01]
        cube[face][2][2] = cube[face][0][2];                //[20 21 22]-------------[22 12 02]
        cube[face][0][2] = temp;

        temp = cube[face][0][1];
        cube[face][0][1] = cube[face][1][0];
        cube[face][1][0] = cube[face][2][1];
        cube[face][2][1] = cube[face][1][2];
        cube[face][1][2] = temp;
    }

    private void rotateFaceCounterClockwise(int face){
        char temp = cube[face][0][0];                              //Method to rotate face moving Counter-clockwise
        cube[face][0][0] = cube[face][0][2];                    //[00 01 02]-------------[02 12 22]
        cube[face][0][2] = cube[face][2][2];                    //[10 11 12]-------------[01 11 21]
        cube[face][2][2] = cube[face][2][0];                    //[20 21 22]-------------[00 10 20]
        cube[face][2][0] = temp;

        temp = cube[face][0][1];
        cube[face][0][1] = cube[face][1][2];
        cube[face][1][2] = cube[face][2][1];
        cube[face][2][1] = cube[face][1][0];
        cube[face][1][0] = temp;
    }

    public void rotateTopClockwise(){
        rotateFaceClockwise(RubiksCube.TOP);                                                                                                                                                            //Rotates face clockwise
        char[] tempRow = new char[3];                                                                                                                                                                               // Temp array
        System.arraycopy(cube[RubiksCube.FRONT][0], 0, tempRow, 0, 3);                                                       //Copying Rows front to temp, right to front, back to right, left to back and temp to left
        System.arraycopy(cube[RubiksCube.RIGHT][0],0, cube[RubiksCube.FRONT][0],0,3);
        System.arraycopy(cube[RubiksCube.BACK][0], 0, cube[RubiksCube.RIGHT][0], 0, 3);
        System.arraycopy(cube[RubiksCube.LEFT][0], 0, cube[RubiksCube.BACK][0], 0, 3 );
        System.arraycopy(tempRow, 0, cube[RubiksCube.LEFT][0], 0, 3);
    }

    public void rotateTopCounterClockwise(){
        rotateFaceCounterClockwise(RubiksCube.TOP);                                                                                                                                              // rotates face counter-clockwise
        char[] tempRow = new char[3];                                                                                                                                                                              // Temp array
        System.arraycopy(cube[RubiksCube.FRONT][0], 0, tempRow, 0, 3);                                                      //copying rows from front to temp, left to front, back to left, right to back and temp to right
        System.arraycopy(cube[RubiksCube.LEFT][0],0, cube[RubiksCube.FRONT][0], 0, 3);
        System.arraycopy(cube[RubiksCube.BACK][0], 0, cube[RubiksCube.LEFT][0], 0, 3);
        System.arraycopy(cube[RubiksCube.RIGHT][0], 0, cube[RubiksCube.BACK][0], 0, 3);
        System.arraycopy(tempRow, 0, cube[RubiksCube.RIGHT][0], 0, 3);
    }

    public void rotateFrontClockwise(){
        rotateFaceClockwise(RubiksCube.FRONT);                                                                                                                                                      //Rotates face clockwise
        char [] tempRow = new char[3];                                                                                                                                                                              // Temp array
        System.arraycopy(cube[RubiksCube.TOP][2], 0, tempRow, 0, 3);                                                             //copying TOP row to temp, left to top, bottom to left, right to bottom and temp to right
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][2][i] = cube[RubiksCube.LEFT][2-i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.LEFT][i][2] = cube[RubiksCube.BOTTOM][0][i];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][0][i] = cube[RubiksCube.RIGHT][2-i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.RIGHT][i][0] = tempRow[i];
        }
    }

    public void rotateFrontCounterClockwise(){
        rotateFaceCounterClockwise(RubiksCube.FRONT);                                                                                                                                                                             // rotates face counter-clockwise
        char[] tempRow = new char[3];                                                                                                                                                                                                                   // Temp array
        System.arraycopy(cube[RubiksCube.TOP][2], 0, tempRow, 0, 3);                                                                                                  //copying TOP row to temp, right to top, bottom to right, left to bottom and temp to left
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][2][i] = cube[RubiksCube.RIGHT][i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.RIGHT][i][0] = cube[RubiksCube.BOTTOM][0][2-i];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][0][i] = cube[RubiksCube.LEFT][i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.LEFT][i][2] = tempRow[2-i];
        }
    }

    public void rotateBackClockwise(){
        rotateFaceClockwise(RubiksCube.BACK);                                                                                                                                 // rotates face clockwise
        char[] tempRow = new char[3];                                                                                                                                                       // Temp array
        System.arraycopy(cube[RubiksCube.TOP][0], 0, tempRow, 0, 3);                                     //copying TOP row to temp, right to top, bottom to right, left to bottom and temp to left
        for(int i = 0; i < 3; i++) {
            cube[RubiksCube.TOP][0][i] = cube[RubiksCube.RIGHT][i][2];
        }
        for(int i = 0; i < 3; i++) {
            cube[RubiksCube.RIGHT][i][2] = cube[RubiksCube.BOTTOM][2][2-i];
        }
        for(int i = 0; i < 3; i++) {
            cube[RubiksCube.BOTTOM][2][i] = cube[RubiksCube.LEFT][i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.LEFT][2-i][0] = tempRow[i];
        }
    }
    public void rotateBackCounterClockwise(){
        rotateFaceCounterClockwise(RubiksCube.BACK);                                                                                                                    // rotates face counter-clockwise
        char[] tempRow = new char[3];                                                                                                                                                       // temp array
        System.arraycopy(cube[RubiksCube.TOP][0], 0, tempRow, 0, 3);                                    // copying top to temp, left to top, bottom to left, right to bottom and  temp to right
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][0][i] = cube[RubiksCube.LEFT][2-i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.LEFT][i][0] = cube[RubiksCube.BOTTOM][2][i];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][2][i] = cube[RubiksCube.RIGHT][2-i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.RIGHT][i][2] = tempRow[i];
        }
    }
    public void rotateLeftClockwise(){
        rotateFaceClockwise(RubiksCube.LEFT);                                                                                                                                 // rotates face clockwise
        char[] temp = new char[3];                                                                                                                                                              // temp array
        for(int i = 0; i < 3; i++){                                                                                                                                                                     // copying top to temp, back to top, bottom to back, front to bottom and temp to front
            temp[i] = cube[RubiksCube.TOP][i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][i][0] = cube[RubiksCube.BACK][2-i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BACK][i][2] = cube[RubiksCube.BOTTOM][2-i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][i][0] = cube[RubiksCube.FRONT][i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.FRONT][i][0] = temp[i];
        }
    }
    public void rotateLeftCounterClockwise(){
        rotateFaceCounterClockwise(RubiksCube.LEFT);                                                                                                                          // rotates face counter-clockwise
        char[] temp = new char[3];                                                                                                                                                                       // temp array
        for(int i = 0; i < 3; i++){                                                                                                                                                                             //copying top to temp, front to top, bottom to front, back to bottom and temp to back
            temp[i] = cube[RubiksCube.TOP][i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][i][0] = cube[RubiksCube.FRONT][i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.FRONT][i][0] = cube[RubiksCube.BOTTOM][i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][i][0] = cube[RubiksCube.BACK][2-i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BACK][2-i][2] = temp[i];
        }
    }
    public void rotateRightClockwise(){
        rotateFaceClockwise(RubiksCube.RIGHT);                                                                                                                              // rotates face clockwise
        char[] temp = new char[3];                                                                                                                                                              // temp array
        for(int i = 0; i < 3; i++){                                                                                                                                                                     //copying top to temp, front to top, bottom to front, back to bottom and temp to back
            temp[i] = cube[RubiksCube.TOP][i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][i][2] = cube[RubiksCube.FRONT][i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.FRONT][i][2] = cube[RubiksCube.BOTTOM][i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][i][2] = cube[RubiksCube.BACK][2-i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BACK][2-i][0] = temp[i];
        }
    }
    public void rotateRightCounterClockwise(){
        rotateFaceCounterClockwise(RubiksCube.RIGHT);                                                                                                                   // rotates face counter-clockwise
        char[] temp = new char[3];                                                                                                                                                                  // temp array
        for(int i = 0; i < 3; i++){                                                                                                                                                                       //copying top to temp, back to top, bottom to back, front to bottom and temp to front
            temp[i] = cube[RubiksCube.TOP][i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][i][2] = cube[RubiksCube.BACK][2-i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BACK][2-i][0] = cube[RubiksCube.BOTTOM][i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][i][2] = cube[RubiksCube.FRONT][i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.FRONT][i][2] = temp[i];
        }
    }
    public void rotateBottomClockwise(){
        rotateFaceClockwise(RubiksCube.BOTTOM);                                                                                                                                                     // rotates face clockwise
        char[] temp = new char[3];                                                                                                                                                                                          // temp array
        System.arraycopy(cube[RubiksCube.FRONT][2], 0, temp, 0, 3);                                                                  //copying front to temp, left to front, back to left, right to back and temp to right
        System.arraycopy(cube[RubiksCube.LEFT][2], 0, cube[RubiksCube.FRONT][2], 0, 3);
        System.arraycopy(cube[RubiksCube.BACK][2], 0, cube[RubiksCube.LEFT][2], 0, 3);
        System.arraycopy(cube[RubiksCube.RIGHT][2], 0, cube[RubiksCube.BACK][2], 0, 3);
        System.arraycopy(temp, 0, cube[RubiksCube.RIGHT][2], 0, 3);
    }
    public void rotateBottomCounterClockwise(){
        rotateFaceCounterClockwise(RubiksCube.BOTTOM);                                                                                                                                         // rotates face counter-clockwise
        char[] temp = new char[3];                                                                                                                                                                                          // temp array
        System.arraycopy(cube[RubiksCube.FRONT][2], 0, temp, 0, 3);                                                                   //copying front to temp, right to front, back to right, left to back and temp to left
        System.arraycopy(cube[RubiksCube.RIGHT][2], 0, cube[RubiksCube.FRONT][2], 0, 3);
        System.arraycopy(cube[RubiksCube.BACK][2], 0, cube[RubiksCube.RIGHT][2], 0, 3);
        System.arraycopy(cube[RubiksCube.LEFT][2], 0, cube[RubiksCube.BACK][2], 0, 3);
        System.arraycopy(temp, 0, cube[RubiksCube.LEFT][2], 0, 3);
    }
    public void rotateMiddleFrontBackClockwise(){
        char[] temp = new char[3];                                                                                                                                                                                                    // temp array
        System.arraycopy(cube[RubiksCube.TOP][1],0, temp,0,3);                                                                                      //copying top to temp, left to top, bottom to left, right to bottom and temp to right
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][1][i] = cube[RubiksCube.LEFT][2-i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.LEFT][i][1] = cube[RubiksCube.BOTTOM][1][i];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][1][i] = cube[RubiksCube.RIGHT][2-i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.RIGHT][i][1] = temp[i];
        }
    }
    public void rotateMiddleFrontBackCounterClockwise(){
        char[] temp = new char[3];                                                                                                                                                                                                  // temp array
        System.arraycopy(cube[RubiksCube.TOP][1],0, temp,0,3);                                                                                  //copying top to temp, right to top, bottom to right, left to bottom and temp to left
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][1][i] = cube[RubiksCube.RIGHT][i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.RIGHT][i][1] = cube[RubiksCube.BOTTOM][1][2-i];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][1][i] = cube[RubiksCube.LEFT][i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.LEFT][i][1] = temp[2-i];
        }
    }
    public void rotateMiddleRightLeftClockwise() {
        char[] temp = new char[3];                                                                                                                                                                                              // temp array
        for (int i = 0 ; i < 3 ; i++){                                                                                                                                                                                                  //copying top to temp, front to top, bottom to front, back to bottom , temp to back
            temp[i] = cube[RubiksCube.TOP][i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][i][1] = cube[RubiksCube.FRONT][i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.FRONT][i][1] = cube[RubiksCube.BOTTOM][i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][i][1] = cube[RubiksCube.BACK][2-i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BACK][i][1] = temp[2-i];
        }
    }
    public void rotateMiddleRightLeftCounterClockwise() {
        char[] temp = new char[3];                                                                                                                                                                                           // temp array
        for (int i = 0 ; i < 3 ; i++){                                                                                                                                                                                              //copying top to temp, back to top, bottom to back, front to bottom and temp to front
            temp[i] = cube[RubiksCube.TOP][i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][i][1] = cube[RubiksCube.BACK][2-i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BACK][i][1] = cube[RubiksCube.BOTTOM][2-i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][i][1] = cube[RubiksCube.FRONT][i][1];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.FRONT][i][1] = temp[i];
        }
    }
    public void rotateDoubleFrontClockwise(){
        rotateFrontClockwise();                                                                                                                                                                                                   //method to rotate front and the layer between front and back clockwise
        rotateMiddleFrontBackClockwise();
    }
    public void rotateDoubleFrontCounterClockwise(){
        rotateFrontCounterClockwise();                                                                                                                                                                                      //method to rotate front and the layer between front and back counter-clockwise
        rotateMiddleFrontBackCounterClockwise();
    }
    public void rotateDoubleRightClockwise(){
        rotateRightClockwise();                                                                                                                                                                                                     //method to rotate right and the layer between right and left clockwise
        rotateMiddleRightLeftClockwise();
    }
    public void rotateDoubleRightCounterClockwise(){
        rotateRightCounterClockwise();                                                                                                                                                                                          //method to rotate right and the layer between right and left counter-clockwise
        rotateMiddleRightLeftCounterClockwise();
    }
    public void performMoves(String[] moves) {
        for (String move : moves) {                                                                                                                                                                                                 //Method for all moves to improve readability of code converted to a method//
            switch (move) {
                case "T":
                    rotateTopClockwise();
                    break;
                case "T`":
                    rotateTopCounterClockwise();
                    break;
                case "F":
                    rotateFrontClockwise();
                    break;
                case "F`":
                    rotateFrontCounterClockwise();
                    break;
                case "L":
                    rotateLeftClockwise();
                    break;
                case "L`":
                    rotateLeftCounterClockwise();
                    break;
                case "Ba":
                    rotateBackClockwise();
                    break;
                case "Ba`":
                    rotateBackCounterClockwise();
                    break;
                case "R":
                    rotateRightClockwise();
                    break;
                case "R`":
                    rotateRightCounterClockwise();
                    break;
                case "B":
                    rotateBottomClockwise();
                    break;
                case "B`":
                    rotateBottomCounterClockwise();
                    break;
                case"f":
                    rotateDoubleFrontClockwise();
                    break;
                case "f`":
                    rotateDoubleFrontCounterClockwise();
                    break;
                case "r" :
                    rotateDoubleRightClockwise();
                    break;
                case "r`":
                    rotateDoubleRightCounterClockwise();
                    break;
                case "M":
                    rotateMiddleRightLeftClockwise();
                    break;
            }
        }
    }
}