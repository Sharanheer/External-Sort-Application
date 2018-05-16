import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Stream;

public class Merger {
	
	int chunk_size = 10000000, file_size;
	//ArrayList<ArrayList<String>> master = new ArrayList<ArrayList<String>>();
	String[][] master;
	int line_read[], current_index[];
	
	public void merger(int count, int arr_size) throws IOException {
		
		file_size = arr_size;
		master = new String[count+1][];
		line_read = new int[count+1];
		current_index = new int[count+1];
		File file = new File("/tmp/2GBFinalOutput.log");
		file.createNewFile();
		//As per formula chunk_size = B
		//int chunk_size = 5;
		for(int i=0; i<count; i++) {
			//ArrayList<String> a = new ArrayList<String>();
			master[i] = new String[chunk_size];
			//master.add(a);
			 
		}
		 //For output one more array of size chunk is created
		//master.add(new ArrayList<String>());
		master[count] = new String[chunk_size];
		current_index[count]=0;
		
		//Initialize all the input array list from the output files
		for(int i=0; i<count; i++)
			initialize(master[i], i+1);
		
		MyPriorityQueue<HeapData> pqueue = new MyPriorityQueue<HeapData>(count);
		//PriorityQueue<HeapData> pqueue = new PriorityQueue<HeapData>();
		
		//Initialize Priority heap with values from input array lists
		initialize_queue(pqueue, count);
		
		
		//Once initialization is done, we extract the min element from the heap and add it to the output file
		//After adding to the output arraylist, if output arraylist is full, copy the content of the output arraylist to the output file on the disk
		while(!pqueue.isEmpty()) {
			HeapData min = pqueue.extractMin();
			master[count][current_index[count]] = min.getS();
			current_index[count] = current_index[count]+1;
			if(current_index[count]-1 == chunk_size-1) {
				//Store the content of the output arraylist to the output file and delete the content from the arraylist
				FileWriter writer = new FileWriter(file, true);
				int start=0;
				while(start <= current_index[count]-1) {
					//System.out.println(x);
					
					writer.write(master[count][start]+"\r\n");
					start++;	
				}	
				current_index[count] = 0;
				writer.flush();
				writer.close();
			}
			if(current_index[min.getIndex()-1] <= chunk_size-1){// && file_size-line_read[min.getIndex()-1] > 0) {
				pqueue.insert(new HeapData(master[min.getIndex()-1][current_index[min.getIndex()-1]], min.getIndex()));
				current_index[min.getIndex()-1] = current_index[min.getIndex()-1]+1;
			}
			else {
				if(line_read[min.getIndex()-1] < file_size) {
					try (Stream<String> lines = Files.lines(Paths.get("/tmp/"+min.getIndex()+"output.log"))) {
						Stream<String> line2 = lines.skip(line_read[min.getIndex()-1]);
					    Iterator s = line2.iterator();
					    int index = 0;
					    while(s.hasNext() && index < chunk_size) {
					    		master[min.getIndex()-1][index] = s.next().toString();
					    		index++;
					    } 
					    current_index[min.getIndex()-1] = 0;
					    line_read[min.getIndex()-1] += index;
					    //pqueue.insert(new HeapData(master.get(min.getIndex()-1).remove(0), min.getIndex()));
					    pqueue.insert(new HeapData(master[min.getIndex()-1][current_index[min.getIndex()-1]], min.getIndex()));
						current_index[min.getIndex()-1] = current_index[min.getIndex()-1]+1;
					}
					 
					
				}
			}
		}
		//dump whatever is remaining in the output array to the output file
		/*FileWriter writer = new FileWriter(file, true);
		int start=0;
		while(start <= current_index[count]-1) {
			//System.out.println(x);
			
			writer.write(master[count][start]+"\r\n");
			start++;	
		}	
		current_index[count] = 0;
		writer.flush();
		writer.close();
		*/
	}
	
	//private void initialize_queue(MyPriorityQueue<HeapData> pqueue, int count) {
	private void initialize_queue(MyPriorityQueue<HeapData> pqueue, int count) {
		// TODO Auto-generated method stub
		for(int i=0; i<count; i++) {
			pqueue.insert(new HeapData(master[i][0],i+1));
			//master.get(i).remove(0);
			//master[i][0] = null;
			current_index[i] = current_index[i]+1;
		}
		
		
	}
	//Initialize all the array list with data from the intermediate output files 
	private void initialize(String[] list, int out_file_ref) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
			File file = new File("/tmp/"+out_file_ref+"output.log");
			Scanner sc = new Scanner(file);
			int index = 0;
			while(sc.hasNext() && index < chunk_size){ 								
				 list[index] = new String(sc.nextLine());	
				 index++;
			}
			current_index[out_file_ref-1] = 0;
			line_read[out_file_ref-1] = chunk_size;
	}
	
}

