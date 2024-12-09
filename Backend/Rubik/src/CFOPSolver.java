public class CFOPSolver {
    private final CrossSolver crossSolver;
    private final FLcorners firstcornersolver;
    private final F2L f2lSolver;
    private final OLL ollSolver;
    private final PLL pllSolver;

    public CFOPSolver(RubiksCube rubiksCube) {
        // Initialize solvers with the Rubik's Cube instance
        this.crossSolver = new CrossSolver(rubiksCube);
        this.firstcornersolver = new FLcorners(rubiksCube);
        this.f2lSolver = new F2L(rubiksCube);
        this.ollSolver = new OLL(rubiksCube);
        this.pllSolver = new PLL(rubiksCube);
    }

    // Method to solve the cube using CFOP
    public void solveCube() {
        System.out.println("Solving the White Cross...");
        crossSolver.solveWhiteCross();
        System.out.println("White Cross Solved!");

//        System.out.println("Solving F2L...");
//        f2lSolver.solveSecondLayer();
//        System.out.println("F2L Completed!");
//
//        System.out.println("Solving OLL...");
//        ollSolver.solveOLL();
//        System.out.println("OLL Completed!");
//
//        System.out.println("Solving PLL...");
//        pllSolver.solvePLL();
//        System.out.println("PLL Completed!");
//
//        System.out.println("Rubik's Cube Solved!");
    }
}
