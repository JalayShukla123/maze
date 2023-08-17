/*This program takes a maze and displays it to the user on the console. After, it asks if the user
 * would like to see the path of the maze. If the user responds with yes, it displays the path the
 * user can take to solve the maze. This program uses the other 2 programs to achieve this feat(MazeCreator to
 * create the maze and MazeSolver to give the path that the printMazeWithPath function uses to print the path.*/
import java.util.Scanner;
public class MazeDisplayer {
	public static void printMazeWithPath(int[][] maze, String path, MazeCreator createdMaze, int n, int m) { //this function prints the maze with the path. the maze is enlarged to allow the '$'character to fit into the maze
	    char[][] displayMaze = new char[n][m];
	    
	    //initializing the display maze with spaces
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < m; j++) {
	            displayMaze[i][j] = ' ';
	        }
	    }

	    //marking the path with "$"
	    int row = 0;
	    int col = 0;
	    displayMaze[row][col] = '$';
	    for (char c : path.toCharArray()) {
	        switch (c) {
	            case 'S':
	                row++;
	                break;
	            case 'E':
	                col++;
	                break;
	            case 'N':
	                row--;
	                break;
	            case 'W':
	                col--;
	                break;
	            default:
	        }
	        displayMaze[row][col] = '$';
	    }

	    //printing the maze with the path displayed
	    for(int i = 0; i < m; i++) {
	    System.out.print("__");
	    }
	    System.out.println();
	    for (int i = 0; i < n; i++) {
	    	if(i == 0 ) {
	    		System.out.print(" ");
	    	}
	    	else {
	    		System.out.print("|");
	    	}
	    	
	        for (int j = 0; j < m; j++) { //the printing of the lines that contain the '$' is done here 
	        	if(j != m - 1 && (maze[i][j] & 2) == 0) {
	        		System.out.print(displayMaze[i][j] + " ");
	        	}
	        	else if(j != m - 1 && (maze[i][j] & 2) != 0) {
	        		System.out.print(displayMaze[i][j] + "|");
	        	}
	        	else {
		            System.out.print(displayMaze[i][j]);
	        	}
	        }
	        System.out.println("|");
	        System.out.print("|");
	        for(int j = 0; j < m; j++) { //the printing of the lines that do not contain the '$' is done here
	        	if ((maze[i][j] & 1) == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print("_");
                }

                if ((maze[i][j] & 2) == 0) {
                    if (((maze[i][j] | maze[i][Math.min(j + 1, m - 1)]) & 1) == 0 && displayMaze[i][j] == ' ') {
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
        MazeCreator createdMaze = new MazeCreator(n, m);
        createdMaze.generateMaze();
        createdMaze.printMaze();
        System.out.println("\nWould you like to print the path as well?(y/n)");
        if(scnr.next().equals("y")) {
        	String path = MazeSolver.findPath(createdMaze.maze, n, m);
            printMazeWithPath(createdMaze.maze, path, createdMaze, n, m);
        	System.out.println("\nThe cardinal direction path is: " + path);
        }
        else {
        }
    	scnr.close();
        System.out.println("\nExiting...");
    }
}
