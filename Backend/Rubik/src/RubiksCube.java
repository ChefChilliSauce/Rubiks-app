public class RubiksCube {

    private final char[][][] cube = new char[6][3][3];                                                                                                  //making a 3D array for cube [FACE][3(GRID)][3(GRID)]
    private static final char [] colors = {'Y', 'G', 'R', 'B', 'O', 'W'};                                                                              //array for cube face colors
    private static final String [] faceNames = {"Top", "Front", "Left", "Back", "Right", "Bottom"};                     //array for face names
    public static final int TOP = 0;                                                                                                                             //face constants
    public static final int FRONT = 1;
    public static final int LEFT = 2;
    public static final int BACK = 3;
    public static final int RIGHT = 4;
    public static final int BOTTOM = 5;

    public char[][][] getCube() {
        return cube;                                                                                                                                                           // Return the cube state
    }

    public RubiksCube (){
        initiateCube();                                                                                                                                                       //method call to initiate the cube
    }

    public void initiateCube(){
        for(int face = 0; face < 6; face++){                                                                                                                       //cube initiation eg:cube[0][0][0] = 'Y'
            for(int i = 0; i <3; i++){
                for(int j = 0; j <3; j++){
                    cube[face][i][j] = colors[face];
                }
            }
        }
    }

    public void DisplayCube(){
        for(int face = 0; face < 6; face++){                                                                                                                        //Method to display cube as output
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
