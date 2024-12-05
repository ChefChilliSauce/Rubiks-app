public class CubeMoves {
    final private char[][][] cube;//cube state

    public CubeMoves(RubiksCube rubiksCube) {
        //Getter method for cube
        this.cube = rubiksCube.getCube();
    }

    public void rotateFaceClockwise(int face){
        //Method to rotate face moving clockwise
        /*
         * [00 01 02]-------------[20 10 00]
         * [10 11 12]-------------[21 11 01]
         * [20 21 22]-------------[22 12 02]
        */

        char temp = cube[face][0][0];
        cube[face][0][0] = cube[face][2][0];
        cube[face][2][0] = cube[face][2][2];
        cube[face][2][2] = cube[face][0][2];
        cube[face][0][2] = temp;

        temp = cube[face][0][1];
        cube[face][0][1] = cube[face][1][0];
        cube[face][1][0] = cube[face][2][1];
        cube[face][2][1] = cube[face][1][2];
        cube[face][1][2] = temp;
    }

    public void rotateFaceCounterClockwise(int face){
        //Method to rotate face moving Counter-clockwise
        /*
         * [00 01 02]-------------[02 12 22]
         * [10 11 12]-------------[01 11 21]
         * [20 21 22]-------------[00 10 20]
        */

        char temp = cube[face][0][0];
        cube[face][0][0] = cube[face][0][2];
        cube[face][0][2] = cube[face][2][2];
        cube[face][2][2] = cube[face][2][0];
        cube[face][2][0] = temp;

        temp = cube[face][0][1];
        cube[face][0][1] = cube[face][1][2];
        cube[face][1][2] = cube[face][2][1];
        cube[face][2][1] = cube[face][1][0];
        cube[face][1][0] = temp;
    }

    public void rotateTopClockwise(){

        rotateFaceClockwise(RubiksCube.TOP);//Rotates face clockwise

        char[] tempRow = new char[3]; // Temp array
        //Copying Rows front to temp, right to front, back to right, left to back and temp to left
        System.arraycopy(cube[RubiksCube.FRONT][0], 0, tempRow, 0, 3);
        System.arraycopy(cube[RubiksCube.RIGHT][0],0, cube[RubiksCube.FRONT][0],0,3);
        System.arraycopy(cube[RubiksCube.BACK][0], 0, cube[RubiksCube.RIGHT][0], 0, 3);
        System.arraycopy(cube[RubiksCube.LEFT][0], 0, cube[RubiksCube.BACK][0], 0, 3 );
        System.arraycopy(tempRow, 0, cube[RubiksCube.LEFT][0], 0, 3);
    }



    public void rotateTopCounterClockwise(){

        rotateFaceCounterClockwise(RubiksCube.TOP);// rotates face counter-clockwise


        char[] tempRow = new char[3]; // Temp array
        //copying rows from front to temp, left to front, back to left, right to back and temp to right
        System.arraycopy(cube[RubiksCube.FRONT][0], 0, tempRow, 0, 3);
        System.arraycopy(cube[RubiksCube.LEFT][0],0, cube[RubiksCube.FRONT][0], 0, 3);
        System.arraycopy(cube[RubiksCube.BACK][0], 0, cube[RubiksCube.LEFT][0], 0, 3);
        System.arraycopy(cube[RubiksCube.RIGHT][0], 0, cube[RubiksCube.BACK][0], 0, 3);
        System.arraycopy(tempRow, 0, cube[RubiksCube.RIGHT][0], 0, 3);
    }

    public void rotateFrontClockwise(){

        rotateFaceClockwise(RubiksCube.FRONT);//Rotates face clockwise

        char [] tempRow = new char[3];// Temp array
        //copying TOP row to temp
        System.arraycopy(cube[RubiksCube.TOP][2], 0, tempRow, 0, 3);
        //Bottom row of TOP face is replaced by reversed right-most column[02,12,22] of left face
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][2][i] = cube[RubiksCube.LEFT][2-i][2];
        }
        //left face right most-column is replaced by first row of bottom face
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.LEFT][i][2] = cube[RubiksCube.BOTTOM][0][i];
        }
        //first row of bottom face is replaced by reversed left-most column[00,10,20] of right face
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][0][i] = cube[RubiksCube.RIGHT][2-i][0];
        }
        //left-most column of right face is replaced by tempRow var i.e. TOP face
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.RIGHT][i][0] = tempRow[i];
        }
    }

    public void rotateFrontCounterClockwise(){

        rotateFaceCounterClockwise(RubiksCube.FRONT);// rotates face counter-clockwise
        //copying TOP row to temp
        char[] tempRow = new char[3];// Temp array
        //copying TOP row to temp
        System.arraycopy(cube[RubiksCube.TOP][2], 0, tempRow, 0, 3);
        //Bottom row of TOP face is replaced by left-most column[00,10,20] of right face
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][2][i] = cube[RubiksCube.RIGHT][i][0];
        }
        //left-most column of right face is replaced by reversed first row of bottom face
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.RIGHT][i][0] = cube[RubiksCube.BOTTOM][0][2-i];
        }
        //first row of bottom face is replaced by right-most column[02][12][22] of left face
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][0][i] = cube[RubiksCube.LEFT][i][2];
        }
        //right-most column of left face is replaced by tempRow var i.e. TOP face
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.LEFT][i][2] = tempRow[2-i];
        }
    }

    public void rotateBackClockwise(){
        rotateFaceClockwise(RubiksCube.BACK);

        char[] tempRow = new char[3];
        System.arraycopy(cube[RubiksCube.TOP][0], 0, tempRow, 0, 3);
        for(int i = 0; i < 3; i++) {
            cube[RubiksCube.TOP][0][i] = cube[RubiksCube.RIGHT][2-i][2];
        }
        for(int i = 0; i < 3; i++) {
            cube[RubiksCube.RIGHT][i][2] = cube[RubiksCube.BOTTOM][2][i];
        }
        for(int i = 0; i < 3; i++) {
            cube[RubiksCube.BOTTOM][2][i] = cube[RubiksCube.LEFT][2-i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.LEFT][i][0] = tempRow[i];
        }
    }
    public void rotateBackCounterClockwise(){
        rotateFaceCounterClockwise(RubiksCube.BACK);
        char[] tempRow = new char[3];
        System.arraycopy(cube[RubiksCube.TOP][0], 0, tempRow, 0, 3);
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.TOP][0][i] = cube[RubiksCube.LEFT][i][0];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.LEFT][i][0] = cube[RubiksCube.BOTTOM][2][2-i];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.BOTTOM][2][i] = cube[RubiksCube.RIGHT][i][2];
        }
        for(int i = 0; i < 3; i++){
            cube[RubiksCube.RIGHT][2-i][2] = tempRow[i];
        }
    }
    public void rotateLeftClockwise(){
        rotateFaceClockwise(RubiksCube.LEFT);
        char[] temp = new char[3];
        for(int i = 0; i < 3; i++){
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
        rotateFaceCounterClockwise(RubiksCube.LEFT);
        char[] temp = new char[3];
        for(int i = 0; i < 3; i++){
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
        rotateFaceClockwise(RubiksCube.RIGHT);
        char[] temp = new char[3];
        for(int i = 0; i < 3; i++){
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
        rotateFaceCounterClockwise(RubiksCube.RIGHT);
        char[] temp = new char[3];
        for(int i = 0; i < 3; i++){
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
        rotateFaceClockwise(RubiksCube.BOTTOM);
        char[] temp = new char[3];
        System.arraycopy(cube[RubiksCube.FRONT][2], 0, temp, 0, 3);
        System.arraycopy(cube[RubiksCube.LEFT][2], 0, cube[RubiksCube.FRONT][2], 0, 3);
        System.arraycopy(cube[RubiksCube.BACK][2], 0, cube[RubiksCube.LEFT][2], 0, 3);
        System.arraycopy(cube[RubiksCube.RIGHT][2], 0, cube[RubiksCube.BACK][2], 0, 3);
        System.arraycopy(temp, 0, cube[RubiksCube.RIGHT][2], 0, 3);
    }
    public void rotateBottomCounterClockwise(){
        rotateFaceCounterClockwise(RubiksCube.BOTTOM);
        char[] temp = new char[3];
        System.arraycopy(cube[RubiksCube.FRONT][2], 0, temp, 0, 3);
        System.arraycopy(cube[RubiksCube.RIGHT][2], 0, cube[RubiksCube.FRONT][2], 0, 3);
        System.arraycopy(cube[RubiksCube.BACK][2], 0, cube[RubiksCube.RIGHT][2], 0, 3);
        System.arraycopy(cube[RubiksCube.LEFT][2], 0, cube[RubiksCube.BACK][2], 0, 3);
        System.arraycopy(temp, 0, cube[RubiksCube.LEFT][2], 0, 3);
    }
    public void rotateMiddleFrontBackClockwise(){
        char[] temp = new char[3];
        System.arraycopy(cube[RubiksCube.TOP][1],0, temp,0,3);
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
        char[] temp = new char[3];
        System.arraycopy(cube[RubiksCube.TOP][1],0, temp,0,3);
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
    public void rotateMiddleBackFrontClockwise(){
        rotateMiddleFrontBackCounterClockwise();
    }
    public void rotateMiddleBackFrontCounterClockwise(){
        rotateMiddleFrontBackClockwise();
    }
}