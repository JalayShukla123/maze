/*This program creates a random maze and displays it to the console after asking the user what dimensions they would like on the maze.*/
import java.util.*;
public class MazeCreator {
	public int[][] maze;
    public int numRows, numCols;
    private Random rand;
    public MazeCreator(int n, int m) {
        numRows = n;
        numCols = m;
        maze = new int[numRows][numCols];
        rand = new Random();
    }

    public void generateMaze() { //the function that creates the maze
        // Initialize maze with all walls
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                maze[i][j] = 15; // binary representation of 1111, indicating all walls present
            }
        }

        int row = rand.nextInt(numRows);
        int col = rand.nextInt(numCols);

        // Generate maze using depth-first search
        dfs(row, col);

    }

    private void dfs(int row, int col) { //the depth first search function
        int[] directions = {1, 2, 4, 8}; // binary representations of 0001, 0010, 0100, 1000, indicating directions south, east, north, west
        shuffleArray(directions);

        for (int d : directions) {
            int newRow = row;
            int newCol = col;

            switch (d) {
                case 1: // South
                    newRow++;
                    break;
                case 2: // East
                    newCol++;
                    break;
                case 4: // North
                    newRow--;
                    break;
                case 8: // West
                    newCol--;
                    break;
            }

            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols && maze[newRow][newCol] == 15) {
                // Remove wall between current cell and new cell
                int reverseDirection = getReverseDirection(d);
                maze[row][col] &= ~d;
                maze[newRow][newCol] &= ~reverseDirection;

                dfs(newRow, newCol);
            }
        }
    }

    private int getReverseDirection(int d) { //this function gives the opposite direction to the one that's given
        switch (d) {
            case 1: // South
                return 4;
            case 2: // East
                return 8;
            case 4: // North
                return 1;
            case 8: // West
                return 2;
            default:
                return 0;
        }
    }

    private void shuffleArray(int[] array) { //this function randomizes the directions
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public void printMaze() { //this function prints the maze
        for (int j = 0; j < numCols; j++) {
            System.out.print("__");
        }
        System.out.println();

        for (int i = 0; i < numRows; i++) {
        	if(i == 0) {
        		System.out.print(" ");
        	}
        	else {
        		System.out.print("|");
        	}
            for (int j = 0; j < numCols; j++) { //checks to see where the walls are
                if ((maze[i][j] & 1) == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print("_");
                }

                if ((maze[i][j] & 2) == 0) {
                    if (((maze[i][j] | maze[i][Math.min(j+1, numCols-1)]) & 1) == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print("_");
                    }
                } else {
                    System.out.print("|");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) { //main function
    	Scanner scnr = new Scanner(System.in);
    	System.out.println("How many rows?");
    	int n = scnr.nextInt();
    	System.out.println("How many columns?");
    	int m = scnr.nextInt();
    	scnr.close();
        MazeCreator createdMaze = new MazeCreator(n, m);
        createdMaze.generateMaze();
        createdMaze.printMaze();
        System.out.println("Exiting...");
    }
}
