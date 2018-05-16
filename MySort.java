import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.channels.FileChannel;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.List;
public class MySort {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		try {	

			if(Integer.parseInt(args[0]) == 2){			
			//Reading
			Path file = Paths.get("/input/data-2GB.in");
			//int arr_size = (int) (file.length()/4000);
			List<String> lines = Files.readAllLines(file);
			String arra[] = lines.toArray(new String[lines.size()]);
			System.out.println("arr size: "+arra.length);
			//Sorting
			MQuickSort quicksort = new MQuickSort(arra);
                        ForkJoinPool pool = new ForkJoinPool();
                        pool.invoke(quicksort);
			//Writing
			BufferedWriter br = new BufferedWriter(new FileWriter("/tmp/2GBFinalOutput.log"));
			for(String x: arra)
				br.write(x+"\r\n");
			br.close();

			}
			else if(Integer.parseInt(args[0]) == 20){
				
			Scanner sc;			
			File file = new File("/input/data-20GB.in");
			int arr_size = (int) (file.length()/400);
			String[] al = new String[arr_size];   	//Create a empty Array 
			sc = new Scanner(file);				// Access the file
			int count = 1;
			while(sc.hasNext() && count <= 4) {
				int index=0;
				while(sc.hasNext() && index<arr_size){ 		
					 al[index] = new String(sc.nextLine());
					 index++;
				 }
				//System.out.println("Size of each block read: "+al[0].length());
				
				//QuickSort ms = new QuickSort();
				//ms.quickSort(al);
				
				MQuickSort quicksort = new MQuickSort(al);
				ForkJoinPool pool = new ForkJoinPool();
				pool.invoke(quicksort);

				String out_filename = count+"output.log";
				
				BufferedWriter br = new BufferedWriter(new FileWriter("/tmp/"+out_filename));
                                for(String x: al)
                                    br.write(x+"\r\n");
                                br.close();

				count++;
			}
			al = null;
			//k-way merger step
			//count variable gives number of different sorted list which needs to be merged
			//We use this count variable to create array lists
			Merger mg = new Merger();
			mg.merger(count-1,arr_size);
			sc.close();
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not found");
		}
		finally {
			
		}
	}

}


