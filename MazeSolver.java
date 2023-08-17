/* The program solves the maze and gives the path out in a NSEW format. This program uses the MazeCreator program to achieve the creation of the maze.*/
import java.util.Scanner;
public class MazeSolver {
	public static final int[] DIRECTIONS_ROW = {1, 0, -1, 0}; // South, East, North, West
    public static final int[] DIRECTIONS_COL = {0, 1, 0, -1}; // South, East, North, West
    public static final char[] DIRECTIONS_CHAR = {'S', 'E', 'N', 'W'}; // South, East, North, West

    public static String findPath(int[][] maze, int n, int m) { //the function that shows the path
        int numRows = maze.length;
        int numCols = maze[0].length;
        boolean[][] visited = new boolean[numRows][numCols];
        StringBuilder path = new StringBuilder();
        dfs(maze, visited, path, 0, 0, n, m);
        return path.toString();
    }

    private static boolean dfs(int[][] maze, boolean[][] visited, StringBuilder path, int row, int col, int n, int m) { //the function that uses depth first search algorithm to find the path
        int numRows = maze.length;
        int numCols = maze[0].length;

        visited[row][col] = true;

        //checks if the end of the maze has been reached
        if (row == numRows - 1 && col == numCols - 1) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int newRow = row + DIRECTIONS_ROW[i];
            int newCol = col + DIRECTIONS_COL[i];

            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols
                    && !visited[newRow][newCol] && (maze[row][col] & (1 << i)) == 0) {
                path.append(DIRECTIONS_CHAR[i]);

                if (dfs(maze, visited, path, newRow, newCol, n, m)) {
                    return true;
                }

                path.deleteCharAt(path.length() - 1);
            }
        }

        return false;
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
    	String path = MazeSolver.findPath(createdMaze.maze, n, m);
    	System.out.println("\nThe cardinal direction path is: " + path);
        System.out.println("\nExiting...");
    }
}
