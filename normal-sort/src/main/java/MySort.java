/**
 * @author zhangyuxiao
 * @date 2021-07-22 15:20
 * @description
 */
public class MySort {

    @ToSort
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    @ToSort
    public static void selectionSort(int[] nums) {
        int minIndex, temp;
        for (int i = 0; i < nums.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
    }

    @ToSort
    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int key = nums[i], j = i - 1;
            while (j >= 0 && key < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }

    @ToSort
    public static void mergeSort(int[] nums) {
        forMergesort(nums, 0, nums.length - 1);
    }

    private static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = 0, j = low, k = mid + 1;
        while (j <= mid && k <= high) {
            if (nums[j] < nums[k]) {
                temp[i++] = nums[j++];
            } else {
                temp[i++] = nums[k++];
            }
        }
        while (j <= mid) {
            temp[i++] = nums[j++];
        }
        while (k <= high) {
            temp[i++] = nums[k++];
        }
        System.arraycopy(temp, 0, nums, low, temp.length);
    }

    private static void forMergesort(int[] nums, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            forMergesort(nums, low, mid);
            forMergesort(nums, mid + 1, high);
            merge(nums, low, mid, high);
        }
    }

    @ToSort
    public static void quickSort(int[] nums) {
        forQuickSort(nums, 0, nums.length - 1);
    }

    private static void forQuickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low, j = high, pivot = nums[low];
        while (i < j) {
            while (i < j && pivot <= nums[j]) {
                j--;
            }
            nums[i] = nums[j];
            while (i < j && pivot >= nums[i]) {
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = pivot;
        forQuickSort(nums, low, i-1);
        forQuickSort(nums, i + 1, high);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
