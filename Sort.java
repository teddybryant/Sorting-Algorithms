public class Sort {
    /* Merge Sort methods */
    public static void mergeSort(int input[]) { //Used to eliminate recursion with prints
        System.out.println("Array before Merge Sort: ");
        printArray(input);
        myMergeSort(input,0,input.length-1);
        System.out.println("Array after Merge Sort: ");
        printArray(input);
    }
    public static void myMergeSort(int arr[], int low, int high) {
        if (low < high) {   //array size >1
            int middle =low+ (high-low)/2;
            myMergeSort(arr, low, middle);  //split into left half
            myMergeSort(arr, middle + 1, high); //split into right half
            merge(arr, low, middle, high);  //call merge on each subarray
        }
    }
    public static void merge(int arr[], int low, int middle, int high) {
        int left[] = new int[middle - low + 1]; //define left and right subarray
        int right[] = new int[high - middle];
        for (int i = 0; i < left.length; ++i) //copy values over 
            left[i] = arr[low + i];
        for (int j = 0; j < right.length; ++j)
            right[j] = arr[middle + 1 + j];
        int i = 0;      //start indexes in left and right at 0 and in merging array at the place to reorder 
        int j = 0;
        int k = low;
        while (i < left.length && j < right.length) { //not reached either ends of the subarrays
            if (left[i] >= right[j]) { //left is bigger add it 
                arr[k] = left[i];
                i++;
            }
            else {      //right but be bigger so add it instead
                arr[k] = right[j]; 
                j++;
            }
            k++;
        }
        while (i < left.length) { //if there are elements still left in left array then just add the rest
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) { //if there are elements still left in right array then just add the rest
            arr[k] = right[j];
            j++;
            k++;
        }
    }


    /* Quick Sort methods */
    public static void quickSort(int[] input) { //once again eliminate recursion with prints
        System.out.println("Array before Quick Sort: ");
        printArray(input);
        myQuickSort(input,0,input.length-1);
        System.out.println("Array after Quick Sort: ");
        printArray(input);
    }
    public static void myQuickSort(int[] arr, int low, int high) { 
        if(low>=high) return;   //base case at smallest array size
        int split = partition(arr, low, high); //partition to reorder the array
        myQuickSort(arr, low, split); //split into two halves: left and right at partition
        myQuickSort(arr, split + 1, high);
    }
    public static int partition(int[] arr, int low, int high) { //this partition pivots from the middle so that time complexity is consistent with sorted arrays
        int pivot = arr[(low + high)/2]; //taking middle to pivot from
        int i = low -1; //move from left to right for more than values
        int j = high +1; //move from right to left for less than values
        while(true) {
            do {
                i++;
            } while(arr[i] > pivot); //move to the right until finding value smaller than pivot
            do {
                j--;
            } while (arr[j] < pivot); //after finding i value to switch, move to the left finding larger value than pivot to switch
            if (i<j) { //make sure the indexes have not cross meaning going past the pivot
                int temp = arr[i]; //just swap the i and j elements
                arr[i] = arr[j];
                arr[j] = temp;
            }
            else
                return j;
        }
    }
    
    
    /* Insertion Sort methods */
    public static void insertionSort(int[] input) { //eliminate parameter issues
        System.out.println("Array before Insertion Sort: ");
        printArray(input);
        myInsertionSort(input);
        System.out.println("Array after Insertion Sort: ");
        printArray(input);
    }
    public static void myInsertionSort(int[] input) {
        int j;
        for(int i = 1; i <input.length; i++) { //go from left to right to reorder the array
            int toInsert = input[i]; //each value is saved to be inserted elsewhere 
            for(j = i; j>0 && toInsert > input[j-1]; j--) //insert value and shift values over
            input[j] = input[j-1];
            input[j] = toInsert;
        }
    }


    /* Upgraded QuickSort methods */
    public static void upgradedQuickSort(int [] input, int d, int k) { //eliminate recursion for prints
        System.out.println("Array before Upgraded Quick Sort: ");
        printArray(input);
        myUpgradedQuickSort(input, d, k, 0, input.length-1);
        System.out.println("Array after Upgraded Quick Sort: ");
        printArray(input);
    }
    public static void myUpgradedQuickSort(int [] arr, int d, int k,int low, int high) {
        if (d<=0) { //if d ever reaches 0 then we just merge sort the subarray, we subtract 1 from d every time and new level is created
            myMergeSort(arr,low,high);
        }
        else if ((high-low) < k) { //if the length of the subarray is less than k value just call insertion sort
            myInsertionSort(arr);
        }
        else {
            int split = partition(arr, low, high); //same as quicksort but just lower d by 1 every recursion
            myUpgradedQuickSort(arr, d-1, k, low, split);
            myUpgradedQuickSort(arr, d-1, k, split + 1, high);
        }
    }


    /* Select methods */
    public static int select(int[] arr, int k) {  //eliminate parameter issues
        return mySelect(arr, 0, arr.length-1, k);
    }
    public static int mySelect(int[] arr, int l, int r, int k) {
            if (k >= 0 && k <= r - l + 1) { //if there is an index for k 
                int pivot = selectPartition(arr, l, r); //uses alternate partition method with pivot as last element, allows pivot-l to be the next largest element
                if (pivot - l == k) //pivot-1 is k so we just return element at index of pivot
                    return arr[pivot];
                if (pivot - l > k) //if larger than recurse with left subarray
                    return mySelect(arr, l, pivot - 1, k);
                return mySelect(arr, pivot + 1, r, k - pivot - 1 + l); //if smaller than recurse with right subarray
            }
            return -1;    //this means no index for k
    }
    public static int selectPartition(int[] arr, int low, int high) { //pivot is the last element so tht we can know the largest
        int pivot = arr[high]; 
        int i = (low - 1); 
        for(int j = low; j <= high - 1; j++) //go through the subarray
        {
            if (arr[j] > pivot) //if found larger value to swap, then get smaller element 
            {
                i++; 
                int temp = arr[i]; //swap the values once finding a pair
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1]; //finally swap with last element
        arr[i+1] = arr[high];
        arr[high] = temp;;
        return (i + 1);
    }


    /* Helper method printArray used to print the arrays */
    public static void printArray(int[] arr) { //goes through array and prints out each element
        for(int i =0; i<arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }
    public static void main(String[] args) { //main method used to test all methods 
        int[] arr1 = {4,2,3,5,1};
        int[] arr2 = {1,3,2,4,5};
        int[] arr3 = {5,1,3,2,4};
        int[] arr4 = {1,2,3,4,5};
        int[] arr5 = {1,2,5,4,3};
        mergeSort(arr1);
        quickSort(arr2);
        insertionSort(arr3);
        upgradedQuickSort(arr4,2,2);
        System.out.println("Testing Select with array");
        printArray(arr5);
        System.out.println("Selecting k=0 value: " + select(arr5,0));
        System.out.println("Selecting k=1 value: " + select(arr5,1));
        System.out.println("Selecting k=2 value: " + select(arr5,2));
    }
}
