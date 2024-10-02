/*
 * Implementations for a Class that performs an insertion sort on arrays
 * @author Beau Fontenot
 */


public class SortAlg {

    /*
     * sortA - sorts the inputted array using insertion sort
     * @param arr - arrau for sorting
     */
    public static void sortA(int[] arr)
    {
        // Start from the second element
        for (int i = 1; i < arr.length; i++)
        {
            int key = arr[i]; // The element to be positioned in the sorted part of the array
            int j = i - 1; // Index of the last element in the sorted part

            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key)
            {
                arr[j + 1] = arr[j]; // Shift element to the right
                j = j - 1; // Move to the next element to the left
            }
            arr[j + 1] = key; // Insert the key at the correct position
        }
    }


    // Unneeded helper method
    //private static int helper()
    //{
    //Implement this method
    //}

}
