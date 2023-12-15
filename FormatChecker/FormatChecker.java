import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FormatChecker {

    public static void main(String[] args) {

        String fileName = "TBD";

        for(int n = 0; n < args.length; n++) { // loop for command-line arguments
            try {
                fileName = args[n]; // [file1 ... fileN]
                Scanner fileScan = new Scanner(new File(fileName)); // Scanner object to read the given file
                String firstLine = fileScan.nextLine(); // String to hold the first line of the file
                String[] dimensions = firstLine.split(" "); // splits string into an array of Strings

                int row = Integer.parseInt(dimensions[0]); // parse 1st element in String array to get # of rows
                int col = Integer.parseInt(dimensions[1]); // parse 2nd element in String array to get # of columns

                if(dimensions.length > 2) { // checks dimensions do not exceed 2D (for invalid4.dat)
                    fileScan.close();
                    throw new GridSizeException("Too many dimensions");
                } else {
                    int rowCount = 0;
                    while(fileScan.hasNextLine()) { // loop to read entire file
                        rowCount++;
                        if(rowCount > row) { // counted more rows than was specified in file
                            fileScan.close();
                            throw new GridSizeException("There are more rows of data than specified");
                        }
                        String nextLine = fileScan.nextLine(); // String to hold the next line in the file
                        String[] numbers = nextLine.split(" "); // splits string into an array of Strings
                        if(numbers.length < col) { // fewer columns than expected
                            fileScan.close();
                            throw new GridSizeException("There are fewer columns of data than specified");
                        }
                        if(numbers.length > col) { // more columns than expected
                            fileScan.close();
                            throw new GridSizeException("There are more columns of data than specified");
                        }
                        double[] values = new double[col]; // 1D array to hold the numbers in current row of the grid
                        for(int i = 0; i < col; i++) {
                            values[i] = Double.parseDouble(numbers[i]);
                            /* populates "values", the 1D array of doubles,
                               by parsing each string in the array of Strings "numbers" */
                        }

                    }
                    fileScan.close();
                    System.out.println(fileName);
                    System.out.println("VALID\n");
                }

            } catch(FileNotFoundException e){
                System.out.println(fileName);
                System.out.println(e.toString());
                System.out.println("INVALID\n");
            } catch(NumberFormatException e) {
                System.out.println(fileName);
                System.out.println(e.toString());
                System.out.println("INVALID\n");
            } catch(InputMismatchException e) {
                System.out.println(fileName);
                System.out.println(e.toString());
                System.out.println("INVALID\n");
            } catch(GridSizeException e) {
                System.out.println(fileName);
                System.out.println(e.toString());
                System.out.println("INVALID\n");
            }

        }

    }

}

