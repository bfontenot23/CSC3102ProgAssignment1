/*
 * Implementations for the driver matrix multiplication method
 * @author Beau Fontenot
 */

import java.util.Scanner;

public class MatrixMul {
    /*
     * This driver creates two 2D matrices of RandMatrix class from the user input size
     * it then sorts left matrix rows and right matrix columns
     * then it multiplies left matrix with the right matrix and saves it as new 2D matrix
     * prints: random left and right matrices, sorted left and right matrices, new matrix
     */

    //Global scanner
    static Scanner in = new Scanner(System.in);

    /*
     * multiplyMatrix - multiplies the given matrices with dot product (assumes that matrixLeft and matrixRight are square matrices of the same size)
     * @param matrixLeft - left matrix for multiplication
     * @param matrixRight - right matrix for multiplication
     * @return - resulting matrix after multiplication
     */
    public static RandMatrix multiplyMatrix(RandMatrix matrixLeft, RandMatrix matrixRight)
    {
        int size = matrixLeft.getSize(); // assume that A and B are square matrices of the same size

        // convert RandMatrix class matrices to int array arrays
        int[][] matrixA = matrixLeft.getMatrix();
        int[][] matrixB = matrixRight.getMatrix();
        int[][] result = new int[size][size];


        // Multiply the matrices
        // The outer loop iterates over each row of matrixA
        for (int i = 0; i < size; i++)
        {
            // The middle loop iterates over each column of matrixB
            for (int j = 0; j < size; j++)
            {
                // Initialize the result for the current position (i, j)
                result[i][j] = 0;

                // The inner loop iterates over the elements of the row from matrixA
                // and the corresponding elements of the column from matrixB
                for (int k = 0; k < size; k++)
                    // Accumulate the product of the corresponding elements
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
            }
        }

        // set up return variable to be of the RandMatrix class
        RandMatrix resultMatrix = new RandMatrix(0);
        resultMatrix.setMatrix(result);

        return resultMatrix;
    }

    /*
     * inputRange - allows user to input an integer between the two given integers.
     * @param lower - defines the lower bound of the input range
     * @param upper - defines the upper bound of the input range (use Integer.MAX_VALUE for no upper bound)
     * @return - a user defined integer between the specified bounds
     */
    public static int inputRange(int lower, int upper)
    {
        int val = 0; // Variable to store the user input
        boolean done = false; // Flag to indicate if a valid input has been received
        do
        {
            if(upper != Integer.MAX_VALUE) // Check if there's an upper bound
            {
                if(in.hasNextInt()) // Check if the next input is an integer
                {
                    val = in.nextInt();

                    // Validate the input against the specified bounds
                    if (val <lower || val > upper) System.out.printf("[!] Please enter between %d and %d\n",lower,upper);
                    else done = true;
                }
                else // Invalid input
                {
                    System.out.println("[!] Please enter an integer");
                    in.next();
                }
            }
            else // No upper bound case
            {
                if(in.hasNextInt()) // Check if the next input is an integer
                {
                    val = in.nextInt();

                    // Validate against the lower bound
                    if (val < lower) System.out.printf("[!] Please enter %d or above\n",lower);
                    else done = true;
                }
                else
                {
                    System.out.println("[!] Please enter an integer");
                    in.next();
                }
            }
        } while (!done);

        return val;
    }

    public static void main(String[] args) {

        int size=0;
        String confirm = "";
        boolean done = false;

        // set the size variable >0
        do
        {
            System.out.print("Enter the size of the matrices: ");
            size = inputRange(0, Integer.MAX_VALUE);

            if(size >= 1000) // edge case warning
            {
                System.out.println("[!] The inputted value (" + size + ") is very large and may cause\nexcessive memory usage, long calculation times, and/or may produce\na result that will cause a crash due to exceeding the integer limit.  \n\nWould you still like to proceed? (Y/N): ");
                confirm = in.next();
                if(confirm.toLowerCase().contains("y")) done = true;
            }
            else done = true;
        } while(!done);


        RandMatrix matrixLeft = new RandMatrix(size);
        RandMatrix matrixRight = new RandMatrix(size);

        System.out.println("Random Matrix Left:\n" + matrixLeft);
        System.out.println("Random Matrix Right:\n" + matrixRight);

        matrixLeft.sortMat(false);
        matrixRight.sortMat(true);

        System.out.println("Sorted Matrix Left (rows):\n" + matrixLeft);
        System.out.println("Sorted Matrix Right (columns):\n" + matrixRight);

        RandMatrix result = multiplyMatrix(matrixLeft, matrixRight);

        System.out.println("Resulting Matrix after multiplication:\n" + result);

        //close global static scanner
        in.close();
    }


}
