public class CrossSolver {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;
    int count;
    // array to check the white edges on TOP layer
    char[] isVacant = new char[4];
    // array helping in resolving the white bottom layer movement conflict
    char[] isWhiteConflict = new char[4];

    public CrossSolver(RubiksCube rubiksCube) {             //getter method
        // Directly assign the cube array to a member variable
        this.cube = rubiksCube.getCube();
        // Initialize CubeMoves with the RubiksCube instance
        this.cubeMoves = new CubeMoves(rubiksCube);
    }
    public void solveWhiteCross(){
        //Method steps to solve white cube
        //Step I: Check how many whites are on the top already
        checkWhiteOnTop();
        //Step II: Move the whites on Faces Front, Right, Back and Left to top
        moveWhitesToTop();
    }
    private boolean isWhiteOnBottomCritical() {      //Checking whites on bottom critical for moves
        for (int i = 0; i < 4; i++) {
            int x = (i == 0) ? 0 : (i == 1) ? 1 : (i == 2) ? 1 : 2;
            int y = (i == 0) ? 1 : (i == 1) ? 0 : (i == 2) ? 2 : 1;
            if (cube[RubiksCube.TOP][x][y] == 'W') {
                count++;
                isWhiteConflict[i] = 'Y';
            } else {
                isWhiteConflict[i] = 'N';
            }
        }
        int[][] criticalPositions = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
        for (int[] pos : criticalPositions) {
            int x = pos[0];
            int y = pos[1];
            if (cube[RubiksCube.BOTTOM][x][y] == 'W') {
                return true;
            }
        }
        return false;
    }
    private void protectBottomWhiteEdges(int face, int i, int j, int vacantIndex, int bottomWhiteVacantIndex) {
        //Method to protect the position of the whites on the bottom layer by using temporary moves
        switch (face) {
            case RubiksCube.FRONT:
                if (i == 0 && j == 1) {
                    switch (vacantIndex) {
                        case 0:
                            switch (bottomWhiteVacantIndex){
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{});
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                            }
                            break;
                        case 1:
                            switch (bottomWhiteVacantIndex){
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{});
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                            }
                            break;
                        case 2:
                            switch (bottomWhiteVacantIndex){
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise",
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 2:
                                    performMoves(new String[]{});
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                            }
                            break;
                    }
                }
                else if(i == 1 && j == 0){
                    switch (vacantIndex) {
                        case 0:
                            switch (bottomWhiteVacantIndex){
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{});
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                            }
                            break;
                        case 1:
                            switch (bottomWhiteVacantIndex){
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{});
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                            }
                            break;
                        case 2:
                            switch (bottomWhiteVacantIndex){
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{});
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise",
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                            }
                            break;
                    }
                }
                else if(i==1 && j==2){
                    switch (vacantIndex) {
                        case 0, 1, 2:
                            switch (bottomWhiteVacantIndex){
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 2:
                                    performMoves(new String[]{});
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                            }
                            break;
                    }
                }
            case RubiksCube.RIGHT:
                if (i == 0 && j == 1) {
                    switch (vacantIndex) {
                        case 0, 1, 2, 3:
                            switch (bottomWhiteVacantIndex) {
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                    });
                                    break;
                            }
                            break;
                    }
                }
                else if (i == 1 && j == 0){
                    switch (vacantIndex) {
                        case 0, 1, 2, 3:
                            switch (bottomWhiteVacantIndex) {
                                case 0:
                                    performMoves(new String[]{});
                                    break;
                                case 1:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                            }
                            break;
                    }
                }
                else if (i == 1 && j == 2){
                    switch (vacantIndex) {
                        case 0, 1, 2, 3:
                            switch (bottomWhiteVacantIndex) {
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"});
                                    break;
                                case 1:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{});
                                    break;
                            }
                            break;
                    }
                }
            case RubiksCube.BACK:
                if (i == 0 && j == 1) {
                    switch (vacantIndex) {
                        case 0, 1, 2, 3:
                            switch (bottomWhiteVacantIndex) {
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{});
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                            }
                            break;
                    }
                }
                else if (i == 1 && j == 0){
                    switch (vacantIndex) {
                        case 0, 1, 2, 3:
                            switch (bottomWhiteVacantIndex) {
                                case 0:
                                    performMoves(new String[]{ "rotateBottomClockwise"});
                                    break;
                                case 1:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 2:
                                    performMoves(new String[]{});
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                            }
                            break;
                    }
                }
                else if (i == 1 && j == 2){
                    switch (vacantIndex) {
                        case 0, 1, 2, 3:
                            switch (bottomWhiteVacantIndex) {
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{});
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                            }
                            break;
                    }
                }
            case RubiksCube.LEFT:
                if (i == 0 && j == 1) {
                    switch (vacantIndex) {
                        case 0, 1, 2, 3:
                            switch (bottomWhiteVacantIndex) {
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{});
                                    break;
                            }
                            break;
                    }
                }
                else if (i == 1 && j == 0){
                    switch (vacantIndex) {
                        case 0, 1, 2, 3:
                            switch (bottomWhiteVacantIndex) {
                                case 0:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 1:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{});
                                    break;
                            }
                            break;
                    }
                }
                else if (i == 1 && j == 2){
                    switch (vacantIndex) {
                        case 0, 1, 2, 3:
                            switch (bottomWhiteVacantIndex) {
                                case 0:
                                    performMoves(new String[]{});
                                    break;
                                case 1:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise"
                                    });
                                    break;
                                case 2:
                                    performMoves(new String[]{
                                            "rotateBottomCounterClockwise"
                                    });
                                    break;
                                case 3:
                                    performMoves(new String[]{
                                            "rotateBottomClockwise",
                                            "rotateBottomClockwise"
                                    });
                                    break;
                            }
                            break;
                    }
                }
        }
    }

    private void checkWhiteOnTop(){ //Method to check and store the pos of the whites already on the top
        for (int i = 0; i < 4; i++) {
            int x = (i == 0) ? 0 : (i == 1) ? 1 : (i == 2) ? 1 : 2;
            int y = (i == 0) ? 1 : (i == 1) ? 0 : (i == 2) ? 2 : 1;
            if (cube[RubiksCube.TOP][x][y] == 'W') {
                count++;
                isVacant[i] = 'Y';
            } else {
                isVacant[i] = 'N';
            }
        }
    }
    private int getVacantIndex(int face, int i, int j) {//Method to get the index of the vacant edge on top with no white
        for (int k = 0; k < 4; k++) {
            if (isVacant[k] == 'N') {
                return k;
            }
        }
        return -1;
    }
    private int getBottomWhiteVacantIndex(int face, int i, int j) {//Method to check which edge don't have white in bottom to avoid conflict moves
        for (int k = 0; k < 4; k++) {
            if (isWhiteConflict[k] == 'N') {
                return k;
            }
        }
        return -1;
    }
    private void bottomLayerWhiteConflict(int face, int vacantIndex) {// Method to handle the conflict moves of bottom layer of each face
        switch (face) {                                                      // i.e. (Face)(2)(1) as it directly impacts the bottom layer
            case RubiksCube.FRONT:                                           // Face: FRONT, RIGHT, BACK, LEFT/-
                    switch (vacantIndex) {
                        case 0:
                            if (cube[RubiksCube.BOTTOM][1][0] == 'W' && cube[RubiksCube.BOTTOM][1][2] == 'W' && cube[RubiksCube.BOTTOM][2][1] == 'W'){
                                performMoves(new String[]{
                                        "rotateFrontClockwise",
                                        "rotateTopCounterClockwise",
                                        "rotateBottomCounterClockwise",
                                        "rotateLeftCounterClockwise",
                                        "rotateTopClockwise"
                                });
                            }
                            else if (cube[RubiksCube.BOTTOM][1][0] != 'W'){
                                performMoves(new String[]{
                                        "rotateFrontClockwise",
                                        "rotateTopCounterClockwise",
                                        "rotateLeftCounterClockwise",
                                        "rotateTopClockwise"
                                });
                            }
                            else if (cube[RubiksCube.BOTTOM][2][1] != 'W'){
                                performMoves(new String[]{
                                        "rotateFrontClockwise",
                                        "rotateBottomClockwise",
                                        "rotateTopCounterClockwise",
                                        "rotateLeftCounterClockwise",
                                        "rotateTopClockwise"
                                });
                            }
                            else {
                                performMoves(new String[]{
                                        "rotateFrontClockwise",
                                        "rotateBottomClockwise",
                                        "rotateBottomClockwise",
                                        "rotateTopCounterClockwise",
                                        "rotateLeftCounterClockwise",
                                        "rotateTopClockwise"
                                });
                            }
                            break;
                        case 1:
                            if(cube[RubiksCube.BOTTOM][1][0] != 'W'){
                                performMoves(new String[]{
                                        "rotateFrontClockwise",
                                        "rotateLeftCounterClockwise",
                                });
                            }
                            else if(cube[RubiksCube.BOTTOM][1][2] != 'W'){
                                performMoves(new String[]{
                                        "rotateFrontClockwise",
                                        "rotateBottomClockwise",
                                        "rotateBottomClockwise",
                                        "rotateLeftCounterClockwise",
                                });
                            }
                            else if(cube[RubiksCube.BOTTOM][2][1] != 'W'){
                                performMoves(new String[]{
                                        "rotateFrontClockwise",
                                        "rotateBottomClockwise",
                                        "rotateLeftCounterClockwise",
                                });
                            }
                            break;
                        case 2:
                            if(cube[RubiksCube.BOTTOM][1][2] != 'W'){
                                performMoves(new String[]{
                                        "rotateFrontCounterClockwise",
                                        "rotateRightClockwise",
                                });
                            }
                            else if(cube[RubiksCube.BOTTOM][1][0] != 'W'){
                                performMoves(new String[]{
                                        "rotateFrontCounterClockwise",
                                        "rotateBottomClockwise",
                                        "rotateBottomClockwise",
                                        "rotateRightClockwise",
                                });
                            }
                            else if(cube[RubiksCube.BOTTOM][2][1] != 'W'){
                                performMoves(new String[]{
                                        "rotateFrontCounterClockwise",
                                        "rotateBottomCounterClockwise",
                                        "rotateRightClockwise",
                                });
                            }
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateFrontCounterClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateRightClockwise",
                                    "rotateTopClockwise",
                            });
                            break;
                    }
                    break;
            case RubiksCube.RIGHT:
                switch (vacantIndex) {
                    case 0:
                        if (cube[RubiksCube.BOTTOM][0][1] == 'W' && cube[RubiksCube.BOTTOM][1][0] == 'W' && cube[RubiksCube.BOTTOM][2][1] == 'W'){
                            performMoves(new String[]{
                                    "rotateRightCounterClockwise",
                                    "rotateBottomClockwise",
                                    "rotateBackClockwise"
                            });
                        }
                        else if (cube[RubiksCube.BOTTOM][0][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateRightCounterClockwise",
                                    "rotateBottomClockwise",
                                    "rotateBackClockwise"
                            });
                        }
                        else if (cube[RubiksCube.BOTTOM][1][2] != 'W'){
                            performMoves(new String[]{
                                    "rotateRightCounterClockwise",
                                    "rotateBottomClockwise",
                                    "rotateBackClockwise"
                            });
                        }
                        else if (cube[RubiksCube.BOTTOM][2][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateRightCounterClockwise",
                                    "rotateBackClockwise"
                            });
                        }
                        break;
                    case 1:
                        if(cube[RubiksCube.BOTTOM][0][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateRightClockwise",
                                    "rotateTopCounterClockwise",
                                    "RotateFrontCounterClockwise",
                                    "rotateTopClockwise",
                                    "RotateFrontClockwise",
                                    "rotateRightCounterClockwise"
                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][2][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateRightCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateRightClockwise"
                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][1][0] != 'W'){
                            performMoves(new String[]{
                                    "rotateRightClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateBottomClockwise",
                                    "rotateFrontCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateBottomClockwise",
                                    "rotateRightCounterClockwise"
                            });
                        }
                        break;
                    case 2:
                        if(cube[RubiksCube.BOTTOM][0][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateRightCounterClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateBottomClockwise",
                                    "rotateBottomClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopClockwise"
                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][1][0] != 'W'){
                            performMoves(new String[]{
                                    "rotateRightCounterClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopClockwise"
                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][2][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateRightCounterClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopClockwise"
                            });
                        }
                        break;
                    case 3:
                        performMoves(new String[]{
                                "rotateRightClockwise",
                                "rotateFrontCounterClockwise",
                                "rotateRightCounterClockwise"
                        });
                        break;
                }
                break;
            case RubiksCube.BACK:
                switch (vacantIndex) {
                    case 0:
                        if (cube[RubiksCube.BOTTOM][0][1] == 'W' && cube[RubiksCube.BOTTOM][1][0] == 'W' && cube[RubiksCube.BOTTOM][1][2] == 'W'){
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateRightCounterClockwise",
                                    "rotateTopCounterClockwise"
                            });
                        }
                        else if (cube[RubiksCube.BOTTOM][0][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateRightCounterClockwise",
                                    "rotateTopCounterClockwise"
                            });
                        }
                        else if (cube[RubiksCube.BOTTOM][1][0] != 'W'){
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateRightCounterClockwise",
                                    "rotateTopCounterClockwise"
                            });
                        }
                        else {
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateTopClockwise",
                                    "rotateRightCounterClockwise",
                                    "rotateTopCounterClockwise"
                            });
                        }
                        break;
                    case 1:
                        if(cube[RubiksCube.BOTTOM][0][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateBackCounterClockwise",
                                    "RotateBottomCounterClockwise",
                                    "rotateLeftClockwise",
                                    "rotateBottomClockwise",
                                    "RotateBackClockwise"

                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][1][0] != 'W'){
                            performMoves(new String[]{
                                    "rotateBackCounterClockwise",
                                    "rotateLeftClockwise",
                                    "RotateBackClockwise"
                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][1][2] != 'W'){
                            performMoves(new String[]{
                                    "rotateBackCounterClockwise",
                                    "RotateBottomCounterClockwise",
                                    "rotateLeftClockwise",
                                    "rotateBottomClockwise",
                                    "RotateBackClockwise"
                            });
                        }
                        break;
                    case 2:
                        if(cube[RubiksCube.BOTTOM][0][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateBottomClockwise",
                                    "rotateRightCounterClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateBackCounterClockwise"
                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][1][0] != 'W'){
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateBottomClockwise",
                                    "rotateBottomClockwise",
                                    "rotateRightCounterClockwise",
                                    "rotateBottomClockwise",
                                    "rotateBottomClockwise",
                                    "rotateBackCounterClockwise"
                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][1][2] != 'W'){
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateRightCounterClockwise",
                                    "rotateBackCounterClockwise"
                            });
                        }
                        break;
                    case 3:
                        performMoves(new String[]{
                                "rotateTopClockwise",
                                "rotateBackCounterClockwise",
                                "rotateLeftClockwise",
                                "rotateBackClockwise"
                        });
                        break;
                }
                break;
            case RubiksCube.LEFT:
                switch (vacantIndex) {
                    case 0:
                        if (cube[RubiksCube.BOTTOM][0][1] == 'W' && cube[RubiksCube.BOTTOM][1][2] == 'W' && cube[RubiksCube.BOTTOM][2][1] == 'W'){
                            performMoves(new String[]{
                                    "rotateLeftClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateBottomClockwise",
                                    "rotateLeftCounterClockwise"
                            });
                        }
                        else if (cube[RubiksCube.BOTTOM][0][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateLeftClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateBackCounterClockwise"
                            });
                        }
                        else if (cube[RubiksCube.BOTTOM][1][2] != 'W'){
                            performMoves(new String[]{
                                    "rotateLeftClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateBackCounterClockwise"
                            });
                        }
                        else {
                            performMoves(new String[]{
                                    "rotateLeftClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateBackCounterClockwise"
                            });
                        }
                        break;
                    case 1:
                        if(cube[RubiksCube.BOTTOM][0][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "RotateTopCounterClockwise",
                                    "rotateFrontClockwise",
                                    "rotateTopClockwise"

                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][1][2] != 'W'){
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "RotateTopCounterClockwise",
                                    "RotateBottomClockwise",
                                    "rotateFrontClockwise",
                                    "rotateTopClockwise"
                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][2][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "RotateTopCounterClockwise",
                                    "RotateBottomClockwise",
                                    "rotateFrontClockwise",
                                    "rotateTopClockwise"
                            });
                        }
                        break;
                    case 2:
                        if(cube[RubiksCube.BOTTOM][0][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateFrontClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateLeftClockwise"
                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][1][2] != 'W'){
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateFrontClockwise",
                                    "rotateBottomClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateLeftClockwise"
                            });
                        }
                        else if(cube[RubiksCube.BOTTOM][2][1] != 'W'){
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateFrontClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateBottomCounterClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateLeftClockwise"
                            });
                        }
                        break;
                    case 3:
                        performMoves(new String[]{
                                "rotateLeftCounterClockwise",
                                "rotateFrontClockwise",
                                "rotateLeftClockwise"
                        });
                        break;
                }
                break;
        }
    }
    private void moveEdgeToTop(int face, int i, int j, int vacantIndex, int bottomWhiteVacantIndex) {//Method to move whites from edges
                                                                                                     //Face(0)(1)&&(1)(0)&&(1)(2) to top
        if (vacantIndex < 0 || vacantIndex > 3) return;        //To check is it is a valid vacant index

        if (isWhiteOnBottomCritical()) {//Method call to handle the bottom white edges to avoid conflict
            protectBottomWhiteEdges(face,i, j, vacantIndex, bottomWhiteVacantIndex);
        }
        switch (face) {
            case RubiksCube.FRONT:
                if (i == 0 && j == 1) {
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateFrontCounterClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateLeftCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateFrontClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateFrontCounterClockwise",
                                    "rotateLeftCounterClockwise",
                                    "rotateFrontClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateFrontClockwise",
                                    "rotateRightClockwise",
                                    "rotateFrontCounterClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateFrontClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateRightClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                    }
                }
                else if(i == 1 && j == 0){
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateTopCounterClockwise",
                                    "rotateLeftCounterClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateLeftCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateLeftCounterClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                    }
                }
                else if(i == 1 && j == 2){
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateRightClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateRightClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateRightClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateTopCounterClockwise",
                                    "rotateRightClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                    }
                }
                else if(i == 2 && j == 1){
                    switch (vacantIndex) {
                        case 0, 3, 2, 1:
                            bottomLayerWhiteConflict(face, vacantIndex);
                            break;
                    }
                }
            case RubiksCube.RIGHT:
                if (i == 0 && j == 1) {
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateRightClockwise",
                                    "rotateBackClockwise",
                                    "rotateRightCounterClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateRightClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateRightCounterClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateRightClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopClockwise",
                                    "rotateRightCounterClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateRightClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateRightCounterClockwise"
                            });
                            break;
                    }
                }
                else if(i == 1 && j == 0){
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateFrontCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateTopCounterClockwise",
                                    "rotateFrontCounterClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateFrontCounterClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateFrontCounterClockwise"
                            });
                            break;
                    }
                }
                else if(i == 1 && j == 2){
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateBackClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateTopCounterClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise"
                            });
                            break;

                    }
                }
                else if(i == 2 && j == 1){
                    switch (vacantIndex) {
                        case 0, 3, 2, 1:
                            bottomLayerWhiteConflict(face, vacantIndex);
                            break;
                    }
                }
            case RubiksCube.BACK:
                if (i == 0 && j == 1) {
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateLeftClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateLeftClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateLeftClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackCounterClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateBackClockwise",
                                    "rotateTopClockwise",
                                    "rotateLeftClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                    }
                }
                else if(i == 1 && j == 0){
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateRightCounterClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateRightCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateRightCounterClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateTopCounterClockwise",
                                    "rotateRightCounterClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                    }
                }
                else if(i == 1 && j == 2) {
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateTopCounterClockwise",
                                    "rotateLeftClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateLeftClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateLeftClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateLeftClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                    }
                }
                else if(i == 2 && j == 1){
                    switch (vacantIndex) {
                        case 0, 3, 2, 1:
                            bottomLayerWhiteConflict(face, vacantIndex);
                            break;
                    }
                }
            case RubiksCube.LEFT:
                if (i == 0 && j == 1) {
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateLeftClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateLeftClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateLeftClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                    }
                }
                else if(i == 1 && j == 0){
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateBackCounterClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateTopCounterClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                    }
                }
                else if(i == 1 && j == 2) {
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateFrontClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateTopCounterClockwise",
                                    "rotateFrontClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateTopClockwise",
                                    "rotateFrontClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateFrontClockwise"
                            });
                            break;
                    }
                }
                else if(i == 2 && j == 1){
                    switch (vacantIndex) {
                        case 0, 3, 2, 1:
                            bottomLayerWhiteConflict(face, vacantIndex);
                            break;
                    }
                }
        }
    }
    private void performMoves(String[] moves) {//Method for all moves to improve readability of code converted to a method
        for (String move : moves) {
            switch (move) {
                case "rotateTopClockwise":
                cubeMoves.rotateTopClockwise();
                break;
                case "rotateTopCounterClockwise":
                    cubeMoves.rotateTopCounterClockwise();
                    break;
                case "rotateFrontClockwise":
                    cubeMoves.rotateFrontClockwise();
                    break;
                case "rotateFrontCounterClockwise":
                    cubeMoves.rotateFrontCounterClockwise();
                    break;
                case "rotateLeftClockwise":
                    cubeMoves.rotateLeftClockwise();
                    break;
                case "rotateLeftCounterClockwise":
                    cubeMoves.rotateLeftCounterClockwise();
                    break;
                case "rotateBackClockwise":
                    cubeMoves.rotateBackClockwise();
                    break;
                case "rotateBackCounterClockwise":
                    cubeMoves.rotateBackCounterClockwise();
                    break;
                case "rotateRightClockwise":
                    cubeMoves.rotateRightClockwise();
                    break;
                case "rotateRightCounterClockwise":
                    cubeMoves.rotateRightCounterClockwise();
                    break;
                case "rotateBottomClockwise":
                    cubeMoves.rotateBottomClockwise();
                    break;
                case "rotateBottomCounterClockwise":
                    cubeMoves.rotateBottomCounterClockwise();
                    break;
            }
        }
    }
    private void moveWhitesToTop() {//Method to move white to top
        for (int face = 1; face < 5; face++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if ((i == 0 && (j == 0 || j == 2)) || (i == 1 && j == 1) || (i == 2 && (j == 0 || j == 2))) {
                        continue;
                    }
                    if (cube[face][i][j] == 'W') {
                        int vacantIndex = getVacantIndex(face, i, j);
                        int bottomWhiteVacantIndex = getBottomWhiteVacantIndex(face, i, j);
                        if (vacantIndex > 0) {
                            moveEdgeToTop(face, i, j, vacantIndex, bottomWhiteVacantIndex);
                        }
                    }
                }
            }
        }
    }
}