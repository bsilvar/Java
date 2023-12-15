import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GridMonitor implements GridMonitorInterface {
	// Instance Data
	private Scanner scan;
	private int row, col;
	private double[][] baseGrid, sumGrid, avgGrid, deltaGrid;
	private boolean[][] dangerGrid;
	
	// Constructor
	public GridMonitor(String fileName) throws FileNotFoundException {	
		scan = new Scanner(new File(fileName));	// scanning file given in constructor
		row = scan.nextInt();				// grab first token for # of rows
		col = scan.nextInt();				// grab second token for # of columns
		baseGrid = new double[row][col];	// instantiating all arrays w/ the given # of rows & columns.
		sumGrid = new double[row][col];
		avgGrid = new double[row][col];	
		deltaGrid = new double[row][col]; 
		dangerGrid = new boolean[row][col];
		// populating baseGrid from file
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				baseGrid[i][j] = scan.nextDouble();
			}
		}
		scan.close();
	}
	
	// Public Methods
	public double[][] getBaseGrid() {
		double[][] gridCopy = new double[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				gridCopy[i][j] = baseGrid[i][j]; // Creating a copy of the base grid
			}
		}
		return gridCopy;		
	}

	public double[][] getSurroundingSumGrid() {
		double[][] gridCopy = new double[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				double sum = 0;				
				// top cell
				if(i - 1 < 0) {					// if no cell is above the current one,
					sum += baseGrid[i][j];		// add value of current cell
				} else {						// if there is a cell above,
					sum += baseGrid[i - 1][j];	// add cell above
				}				
				// left cell
				if(j - 1 < 0) {					// if no cell is to the left of the current one,
					sum += baseGrid[i][j];		// add value of current cell
				} else {						// if there is,
					sum += baseGrid[i][j - 1];	// add cell on the left
				}				
				// bottom cell
				if(i + 1 >= row) {				// if no cell is below the current one,
					sum += baseGrid[i][j];      // add value of current cell
				} else {						// if there is,
					sum += baseGrid[i + 1][j];  // add cell below
				}					
				// right cell
				if(j + 1 >= col) {				// if no cell is to the right of the current one,
					sum += baseGrid[i][j];		// add value of current cell
				} else {						// if there is,
					sum += baseGrid[i][j + 1];  // add cell on the right
				}				
				sumGrid[i][j] = sum;			// populating sumGrid			
				gridCopy[i][j] = sumGrid[i][j];	// copy of sumGrid
			}
		}
		return gridCopy;
	}

	public double[][] getSurroundingAvgGrid() {
		getSurroundingSumGrid();
		double[][] gridCopy = new double[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				avgGrid[i][j] = sumGrid[i][j] / 4;	// getting the average of each cell
				gridCopy[i][j] = avgGrid[i][j];		// copy of avgGrid
			}
		}
		return gridCopy;
	}

	public double[][] getDeltaGrid() {
		getSurroundingAvgGrid();
		double[][] gridCopy = new double[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				deltaGrid[i][j] = Math.abs(avgGrid[i][j] / 2);	// max delta of each cell
				gridCopy[i][j] = deltaGrid[i][j];				// copy of deltaGrid
			}
		}
		return gridCopy;
	}

	public boolean[][] getDangerGrid() {
		getDeltaGrid();
		boolean[][] gridCopy = new boolean[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {			
				if(baseGrid[i][j] < avgGrid[i][j] - deltaGrid[i][j] || 
				   baseGrid[i][j] > avgGrid[i][j] + deltaGrid[i][j]) 
				{
					dangerGrid[i][j] = true;  // cells that are in danger
				} else {
					dangerGrid[i][j] = false; // cells that are not in danger
				}	
				gridCopy[i][j] = dangerGrid[i][j]; // copy of dangerGrid
			}
		}
		return gridCopy;
	}
	
	public String toString() {
		String result = "";
		for(int i = 0; i < baseGrid.length; i++) {
			for(int j = 0; j < baseGrid[0].length; j++) {
				result += baseGrid[i][j] + " | ";
			}
			result += "\n";
		}		
		return result;
	}
}

