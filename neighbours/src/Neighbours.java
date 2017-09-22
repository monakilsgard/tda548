import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.System.*;

/*
 *  Program to simulate segregation.
 *  See : http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/
 *
 * NOTE:
 * - JavaFX first calls method init() and then method start() far below.
 * - To test uncomment call to test() first in init() method!
 *
 */
// Extends Application because of JavaFX (just accept for now)


/* PLAN
- Skapa en array
- Shuffla array
- Stoppa in array i matris
- Skapa begränsad värld (matris ned 3x3)
- Skapa en matris med Actor-objekt (BLUE/RED/NONE)
- Definiera vad State (UNSATISFIED/NA/SATISFIED) är (metod)
- Gå igenom hela listan och detektera vad som är vad (updateWorld)
    - Flytta till ledig plats varje varv (berätta vad ledig plats är)
- Säg åt hur hantera saker utanför matrisen/world (isValidLocation)
- Stoppa in final värden i world

Metoder:
isValidLocation (Check if inside world)
updateWorld ???? program??(% of surrounding neighbours that are like me)

REGLER:
Startvärde: isValidLocation
If t = 70%, agent X is satisfied if at least 70% of its neighbors are also X
If fewer than 70% are X, then the agent is not satisfied, and it will want to change its location in the grid

When an agent is not satisfied, it can be moved to any vacant location in the grid. Any algorithm can be used to choose
this new location. For example, a randomly selected cell may be chosen, or the agent could move to the nearest available location.
All dissatisfied agents must be moved in the same round. After the round is complete, a new round begins, and
dissatisfied agents are once again moved to new locations in the grid. These rounds continue until all agents in the
neighborhood are satisfied with their location.


 */

public class Neighbours extends Application {

    // Enumeration type for the Actors
    enum Actor {
        BLUE, NONE, RED   // Type only has 3 values, NONEs are white
    }

    // Enumeration type for the state of an Actor
    enum State {
        UNSATISFIED,
        NA,        // Not applicable (NA), used for NONEs
        SATISFIED
    }

    final static Random rand = new Random();

    // Below is the *only* accepted instance variable (i.e. variables outside any method)
    // This variable is accessible from any method
    Actor[][] world;              // The world is a square matrix of Actors

    // This is the method called by the timer to update the world approx each 1/60 sec.
    void updateWorld() {
        // % of surrounding neighbours that are like me
        final double threshold = 0.7;
        // Find all satisfied
        //world = siwtchPlaces();

        State [][] tmp = checkUnsatisfied(world, threshold);
        int [] emptyArray = checkEmpty()
        Actor [][] thisWorld = switchPlaces(tmp, world,)



    }

    // This method initializes the world variable with a random distribution of Actors
    // Method automatically called by JavaFX runtime
    // That's why we must have "@Override" and "public" (just accept for now)
    @Override
    public void init() {
      //  test();    // <---------------- Uncomment to TEST!

        // %-distribution of RED, BLUE and NONE
        double[] dist = {0.25, 0.25, 0.50};
        // Number of locations (places) in world (square)
        int nLocations = 900;
        Actor[] generateArr = generateDistributionArray (nLocations , dist[0], dist[1], dist[2]);
        Actor[] shuffleArr = shuffleArray (generateArr);
        world = toMatrix(shuffleArr);


    }








    // ----------- Utility methods -----------------

    // TODO need any utilities add here (= methods possible reusable for other programs)

    //CREATES ARRAY (for world matrix)
    Actor[] generateDistributionArray(int x, double y, double z, double w) {

        Actor[] arr = new Actor[x];
        int BLUE = (int) StrictMath.round(arr.length * y); // 25% BLUE
        int RED = (int) StrictMath.round(arr.length * z); // 25% RED
        int NONE = (int) StrictMath.round(arr.length * w); // 50% WHITE

        for (int i = 0; i < BLUE; i++) {

            Actor valueofBlue = Actor.BLUE;
            arr[i] = valueofBlue;

        }

        for (int i = BLUE; i < BLUE + RED; i++) {

            Actor valueofRed = Actor.RED;
            arr[i] = valueofRed;
        }


        for (int i = BLUE + RED; i < arr.length; i++) {

            Actor valueofNone = Actor.NONE;
            arr[i] = valueofNone;
        }

        return arr;
    }

    // SHUFFLES ARRAY
    Actor[] shuffleArray(Actor[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1); // gör random mellan 0 och i + 1 (i = max)
            Actor a = array[index];
            array[index] = array[i]; //sätter värdet på i på slumpat index
            array[i] = a; // flyttar/bytar plats på indexet man flyttade till med startindexet


        }
        return array;
    }

    // TURN ARRAY TO MATRIX
    Actor[][] toMatrix(Actor[] array) {

        int r = (int) (StrictMath.round(StrictMath.sqrt(array.length)));
        int c = (int) (StrictMath.round(StrictMath.sqrt(array.length)));

        Actor[][] matrix = new Actor[r][c];

        for (int i = 0; i < array.length; i++) {

            r = i / matrix.length;
            c = i % matrix.length;

            matrix[r][c] = array[i];
        }
        return matrix;
    }

    // Creates Array with Actor.NONE
    int[] checkEmpty(Actor[][] matrix) {
        int count = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[r][c] == Actor.NONE) {
                    count++;

                }
            }
        }

        int[] noneArr = new int[count];
        int indexInArr = 0;

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[r][c] == Actor.NONE) {
                    int index = r * matrix.length + c;
                    noneArr[indexInArr] = index;
                    indexInArr++;

                }
            }
        }
        return noneArr;
    }

// Create matrix with all Actors marked as UNSATISFIED. To be used together with checkEmpty array to move Actors around.
    State[][] checkUnsatisfied(Actor[][] matrix, double threshold){
        //int[] empty = checkEmpty(matrix);
        State[][] stateOfWorld = new State[matrix.length][matrix.length];
        for (int r = 0; r < matrix.length; r++){
            for (int c = 0; c < matrix.length; c++){
                boolean  unsatisfied = !(isSatisfied(matrix,threshold, r, c));
                if (unsatisfied)  {
                    stateOfWorld[r][c] = State.UNSATISFIED;
                }
            }
        }

        return stateOfWorld;
    }


    // Check if Actor.BLUE/RED is satisfied

    boolean isSatisfied(Actor[][] matrix, double threshold, int personRow, int personCol) {                 //Return True för Satisfied, False för Unsatisfied
        int countNeighbours = 0;
        int countNeighboursLikeMe = 0;

        if (matrix[personRow][personCol] == Actor.NONE) {

            return true;
        }

        for (int row = personRow - 1; row <= personRow + 1; row++) {                                    // Går igenom varje index precis intill
            for (int col = personCol - 1; col <= personCol + 1; col++) {

                boolean valid = isValidLocation(matrix.length, row, col);                              // Kallar på isValidLocation och kollar om r och c existerar i världen

                if (valid) {

                    boolean notMe = !(row == personRow && col == personCol);
                    boolean notNone = matrix[row][col] != Actor.NONE;

                    if (notMe && notNone) {
                        countNeighbours++;
                    }
                    if (matrix[row][col] == matrix[personRow][personCol]) {
                        countNeighboursLikeMe++;

                    }
                }

            }
        }
        boolean checkSatisfied;
        if ((countNeighbours == 0)) {
            checkSatisfied = true;
        }
            else {
            checkSatisfied = (countNeighboursLikeMe / countNeighbours) >= threshold;
        }

        return checkSatisfied;
    }

    // Check if inside world
    boolean isValidLocation(int size, int row, int col) {


        boolean valid = (row >= 0 && col >= 0 && row < size && col < size);

        return valid;
    }



    Actor [][] switchPlaces (State [][] stateworld, Actor [][] prevWorld, int [] noneArray){

        int k = 0;
        for (int r = 0 ; r < stateworld.length ; r++){
            for ( int c = 0 ; c < stateworld.length ; c++){
                if (stateworld [r][c] == State.UNSATISFIED) {
                    Actor tmp = prevWorld [r][c];
                    prevWorld [r][c] = Actor.NONE;
                    int index = noneArray[k];
                    int row = index / prevWorld.length;
                    int col = index % prevWorld.length;
                    prevWorld[row][col] = tmp;

                   k++;
                }
            }
        }
        return prevWorld;
    }














    // ------- Testing -------------------------------------

    // Here you run your tests i.e. call your logic methods
    // to see that they really work
    void test() {
        // A small hard coded world for testing
        world = new Actor[][]{
                {Actor.RED, Actor.RED, Actor.NONE},
                {Actor.NONE, Actor.BLUE, Actor.NONE},
                {Actor.RED, Actor.NONE, Actor.BLUE}
        };

        Actor [][] noneWorld = new Actor[][]{
                {Actor.NONE, Actor.NONE, Actor.NONE},
                {Actor.NONE, Actor.BLUE, Actor.NONE},
                {Actor.NONE, Actor.NONE, Actor.NONE}
        };

        double th = 0.5;   // Simple threshold used for testing

        // A first test!
        int s = world.length;
        out.println(isValidLocation(s, 0, 0));

        out.println(isSatisfied(noneWorld, th, 1, 1));

        // Test for generateDistribution method

        Actor[] arr = (generateDistributionArray(100, 0.25, 0.25, 0.5));

        int blueCount = 0;
        int redCount = 0;
        int noneCount = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == Actor.BLUE) {
                blueCount++;
            }
            if (arr[i] == Actor.RED) {
                redCount++;
            }
            if (arr[i] == Actor.NONE) {
                noneCount++;
            }
        }
        out.println(blueCount == 25);
        out.println(redCount == 25);
        out.println(noneCount == 50);

        int array[] = checkEmpty(world);
        out.println(array[0] == 2);
        out.println(array[1] == 3);
        out.println(array[2] == 5);
        out.println(array[3] == 7);

        /* Move of unsatisfied hard to test because of random */

        exit(0);
    }

    // ---- NOTHING to do below this row, it's JavaFX stuff  ----

    final int width = 400;   // Size for window
    final int height = 400;
    long previousTime = nanoTime();
    final long INTERVAL = 450000000;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Build a scene graph
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().addAll(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Create a timer
        AnimationTimer timer = new AnimationTimer() {
            // This method called by FX, parameter is the current time
            public void handle(long currentNanoTime) {
                long elapsedNanos = currentNanoTime - previousTime;
                if (elapsedNanos > INTERVAL) {
                    updateWorld();
                    renderWorld(gc);
                    previousTime = currentNanoTime;
                }
            }
        };

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation");
        primaryStage.show();

        timer.start();  // Start simulation
    }


    // Render the state of the world to the screen
    public void renderWorld(GraphicsContext g) {
        g.clearRect(0, 0, width, height);
        int size = world.length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int x = 10 * col + 50;
                int y = 10 * row + 50;

                if (world[row][col] == Actor.RED) {
                    g.setFill(Color.RED);
                } else if (world[row][col] == Actor.BLUE) {
                    g.setFill(Color.BLUE);
                } else {
                    g.setFill(Color.WHITE);
                }
                g.fillOval(x, y, 10, 10);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
