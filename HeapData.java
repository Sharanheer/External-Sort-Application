import java.util.Comparator;

public class HeapData implements Comparator<HeapData>{
	private String s = null;
	private int index;
	
	public HeapData() {
		
	}
	
	public HeapData(String s, int index) {
		this.s = s;
		this.index = index;
	}

	
	
	public String getS() {
		return s;
	}



	public void setS(String s) {
		this.s = s;
	}



	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	@Override
	public int compare(HeapData o1, HeapData o2) {
		// TODO Auto-generated method stub
		return o1.s.substring(0,11).compareTo(o2.s.substring(0,11));
	}
	
	
}
