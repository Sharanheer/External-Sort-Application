import java.util.Comparator;

public class MyPriorityQueue<t>{
	
	t arr[];
	int total=-1;

	public MyPriorityQueue(int size) {
		
		arr = (t[]) new Object[size];
	}
	
	
	
	public void insert(Object data) {
		
		total++;
		if(total<arr.length) {
			arr[total] = (t) data;
			heapify(total);
		}
		else
			System.out.println("Can't add more data");
		
	}
	
	public t extractMin() {
		t temp;
		if(total==0)
			temp = arr[0];
		else {
			temp = arr[0];
			arr[0] = arr[total];
			minHeapify(0);
		}	
		total--;
		return temp;
	}
	
	public t getMin() {
		return arr[0];
	}
	
	/*public void decreaseKey(t new_data) {
		int temp;
		for(int i=0;i<total;i++) {
			if(arr[i].getVertex() == new_data.getVertex()) {
				heapify(i);
			}	
		}
		
		
	}*/

	public void minHeapify(int i) {
		// TODO Auto-generated method stub
		int l = left(i);
	    int r = right(i);
	    int smallest = i;
	    if (l < total && compare(arr[l],arr[i]) < 0)
	        smallest = l;
	    if (r < total && compare(arr[r],arr[smallest]) < 0)
	        smallest = r;
	    if (smallest != i)
	    {
	        swap(i, smallest);
	        minHeapify(smallest);
	    }
	}



	public void heapify(int i) {
		// TODO Auto-generated method stub
		while (i != 0 && compare(arr[parent(i)],arr[i]) > 0)
	    {
	       swap(i, parent(i));
	       i = parent(i);
	    }
	}
	
	public void swap(int x, int y)
	{
	    t temp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = temp;
	}
	
	public boolean isEmpty() {
		return total==-1;
	}
	
	int parent(int i) { return (i-1)/2; }
	 
   
    int left(int i) { return (2*i + 1); }
 
    
    int right(int i) { return (2*i + 2); }
    
    public int compare(t a, t b) {
    		return (new HeapData().compare((HeapData)a,(HeapData)b));
    }
	
}

