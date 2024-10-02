/*
 * Implementations for a Class that creates a 2D matrix of random numbers and performs sorting operations
 * @author Beau Fontenot
 */

import java.util.Random;

public class RandMatrix {
    private int[][] matrix;
    private int size;   // added this attribute to help with sorting columns
    private boolean sorted;

    /*
     * RandMatrix - Constructor for RandMatrix class
     * @param n - size of the matrix (square, so same number of rows and columns)
     */
    public RandMatrix(int m)
    {
        // initialize variable
        this.size = m;
        this.matrix = new int[this.size][this.size];
        this.sorted = false;

        // Populate the matrix with random numbers between 0 and 100
        Random rand = new Random();

        for(int[] arr : this.matrix)
        {
            for(int i=0; i<arr.length; i++)
                arr[i] = rand.nextInt(0,100);
        }
    }

    /*
     * sortMat - sorts the rows or columns of the matrix
     * @param axisType - axis for sorting (false == rows && true == columns)
     */
    public void sortMat(boolean axisType)
    {
        if(axisType)  // sorting columns
        {
            // Create a temporary matrix that contains the matrix rotated,
            // so that the columns become rows for easier sorting
            int[][] temp = new int[this.size][this.size];

            // Populate the temporary matrix with transposed values
            for(int i=0; i<this.size; i++) // Iterate through each row
            {
                for(int j=0; j<this.size; j++)  // Iterate through each column
                    // Assign the transposed value from the original matrix
                    temp[i][j] = this.matrix[j][i];
            }

            // Sort each row of the temporary array (which corresponds to columns of the original matrix)
            for(int[] arr : temp)
                SortAlg.sortA(arr);

            // Restore the sorted values back to the original matrix
            for(int i=0; i<this.size; i++)
            {
                for(int j=0; j<this.size; j++)
                    // Assign the sorted values back to the original matrix, flipping back the indices
                    this.matrix[i][j] = temp[j][i];
            }
        }
        else // sorting rows
        {
            // Sort each row of the original matrix directly
            for(int[] arr : this.matrix)
                SortAlg.sortA(arr);
        }

        this.sorted = true;
    }




    // I know we weren't supposed to add more methods to this file but
    // it goes against everything I know to not use getter and setter
    // methods for class attributes
    //
    // I also added a toString() method for printing the matrix

    /*
     * getMatrix - retrieves the matrix attribute
     * @return - matrix attribute
     */
    public int[][] getMatrix()
    {
        return matrix;
    }

    /*
     * setMatrix - sets the matrix attribute to the given matrix and adjusts size variable
     * @param matrix - matrix for inputting
     */
    public void setMatrix(int[][] matrix)
    {
        this.matrix = matrix;
        this.size = matrix.length; // assume square matrix
    }


    /*
     * getSize - retrieves the size attribute
     * @return - size attribute
     */
    public int getSize()
    {
        return size;
    }

    /*
     * toString - converts the matrix attribute to a string
     * @return - string form of the matrix
     */
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();

        // append each element of each row to the string
        for (int i = 0; i < this.size; i++)
        {
            for (int j = 0; j < this.size; j++)
                s.append(" ").append(this.matrix[i][j]);

            s.append("\n"); // next row
        }

        return s.toString();
    }

}