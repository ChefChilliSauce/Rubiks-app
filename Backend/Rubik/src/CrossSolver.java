public class CrossSolver {
    final private char[][][] cube;
    final private CubeMoves cubeMoves;
    int count;
    char[] isVacant = new char[4];
    char[] isWhiteConflict = new char[4];

    public CrossSolver(RubiksCube rubiksCube) {
        this.cube = rubiksCube.getCube(); // Directly assign the cube array to a member variable
        this.cubeMoves = new CubeMoves(rubiksCube); // Initialize CubeMoves with the RubiksCube instance
    }
    public void solveWhiteCross(){
                checkWhiteOnTop();
                moveWhitesToTop();
    }
    private boolean isWhiteOnBottomCritical() {
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
        switch (face) {
            case RubiksCube.FRONT:
                if (i == 0) {
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
        }
    }

    private void checkWhiteOnTop(){
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
    private int getVacantIndex(int face, int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (isVacant[k] == 'N') {
                return k;
            }
        }
        return -1;
    }
    private int getBottomWhiteVacantIndex(int face, int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (isWhiteConflict[k] == 'N') {
                return k;
            }
        }
        return -1;
    }
    private void bottomLayerWhiteConflict(int face, int i, int vacantIndex) {
        switch (face) {
            case RubiksCube.FRONT:
                if (i == 0) {
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
                    }
                }
        }
    }
    private void moveEdgeToTop(int face, int i, int j, int vacantIndex, int bottomWhiteVacantIndex) {
        if (vacantIndex < 0 || vacantIndex > 3) return;

        if (isWhiteOnBottomCritical()) {
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
                            bottomLayerWhiteConflict(face,i, vacantIndex);
                            break;
                    }
                }
            case RubiksCube.RIGHT:
                if (i == 0 && j == 1) {
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateRightClockwise",
                                    "rotateBackClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateRightClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateRightClockwise",
                                    "rotateTopCounterClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopClockwise"
                            });
                            break;
                        case 3:
                            performMoves(new String[]{
                                    "rotateRightClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise"
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
                            bottomLayerWhiteConflict(face,i, vacantIndex);
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
                                    "rotateTopClockwise"
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
                            bottomLayerWhiteConflict(face,i, vacantIndex);
                            break;
                    }
                }
            case RubiksCube.LEFT:
                if (i == 0 && j == 1) {
                    switch (vacantIndex) {
                        case 0:
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "rotateBackCounterClockwise"
                            });
                            break;
                        case 1:
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateTopCounterClockwise"
                            });
                            break;
                        case 2:
                            performMoves(new String[]{
                                    "rotateLeftCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise",
                                    "rotateBackCounterClockwise",
                                    "rotateTopClockwise",
                                    "rotateTopClockwise"
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
                            bottomLayerWhiteConflict(face,i, vacantIndex);
                            break;
                    }
                }
        }
    }
    private void performMoves(String[] moves) {
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
    private void moveWhitesToTop() {
        for (int face = 1; face < 5; face++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if ((i == 0 && (j == 0 || j == 2)) || (i == 1 && j == 1) || (i == 2 && (j == 0 || j == 2))) {
                        continue;
                    }
                    if (cube[face][i][j] == 'W') {
                        int vacantIndex = getVacantIndex(face, i, j);
                        int bottomWhiteVacantIndex = getBottomWhiteVacantIndex(face, i, j);
                        if (vacantIndex >= 0) {
                            moveEdgeToTop(face, i, j, vacantIndex, bottomWhiteVacantIndex);
                        }
                    }
                }
            }
        }
    }
}

