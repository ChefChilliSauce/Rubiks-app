public class RubiksCube {
    //making a 3D array for cube [FACE][3(GRID)][3(GRID)]
    private final char[][][] cube = new char[6][3][3];
    //array for cube face colors
    private static final char [] colors = {'Y', 'G', 'R', 'B', 'O', 'W'};
    //array for face names
    private static final String [] faceNames = {"Top", "Front", "Left", "Back", "Right", "Bottom"};
    //face constants
    public static final int TOP = 0;
    public static final int FRONT = 1;
    public static final int LEFT = 2;
    public static final int BACK = 3;
    public static final int RIGHT = 4;
    public static final int BOTTOM = 5;

    public char[][][] getCube() {//getter method
        return cube;
    }
    public RubiksCube (){
        //method call to initiate the cube
        initiateCube();
    }
    private void initiateCube(){
        //cube initiation eg:cube[0][0][0] = 'Y'
        for(int face = 0; face < 6; face++){
            for(int i = 0; i <3; i++){
                for(int j = 0; j <3; j++){
                    cube[face][i][j] = colors[face];
                }
            }
        }
    }
    public void DisplayCube(){
        for(int face = 0; face < 6; face++){
            System.out.println("Face: " + faceNames[face]);
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    System.out.print(cube[face][i][j] + " " );
                }
                System.out.println();
            }
        }
    }
}
