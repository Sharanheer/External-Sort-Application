import java.util.List;
import java.util.concurrent.RecursiveAction;
 
public class MQuickSort extends RecursiveAction {
 
    private String[] arr;
    private int l;
    private int r;
 
    public MQuickSort(String[] arr){
        this.arr=arr;
        this.l = 0;
        this.r = arr.length - 1;
    }
 
    public MQuickSort(String[] arr, int l, int r){
        this.arr = arr;
        this.l = l;
        this.r = r;
    }
 
    @Override
    protected void compute() {
        if (l < r){
            int pivotIndex = l + ((r - l)/2);
 
            pivotIndex = partition(pivotIndex);
 
            invokeAll(new MQuickSort(arr, l, pivotIndex-1),
                      new MQuickSort(arr, pivotIndex+1, r));
        }
 
    }
 
    private int partition(int pivotIndex){
        String pivotValue = arr[pivotIndex];
 
        swap(pivotIndex, r);
 
        int storeIndex = l;
        for (int i=l; i<r; i++){
            if (arr[i].compareTo(pivotValue) < 0){
                swap(i, storeIndex);
                storeIndex++;
            }
        }
 
        swap(storeIndex, r);
 
        return storeIndex;
    }
 
    private void swap(int i, int j){
        if (i != j){
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
           
        }
    }
}

