/**
        @authors: Alex Clavelle, Kelsey Cameron
Description: Creates a K-ary heap where K is the maximum number of children for a non-leaf node.
 */
import java.util.*;
import java.io.*;
import java.util.Random;
public class HW_Util {
	List<String> input;

	public static void main(String[] args) throws IOException {
		// SUPPLY A FILEPATH TO THE INPUT ON THE COMMAND LINE!!!!!
		HW_Util util = new HW_Util();
		util.loadfile(args[0]);
		ListTrie lt = new ListTrie();
		HashTrie ht = new HashTrie(util.input.size());
		int lines = 0;
		for (String s : util.input) {
			lt.insertString(s);
			ht.insertString(s);
			lines++;
		}
		ht.hashify(ht.root);		
		System.out.printf("\n%d words added\n\n",lines);

		//fill our array with random numers, we'll use this to select random words out of input file
		Random rand = new Random();
		int[] randoms = new int[1000];
		for(int i = 0;i<randoms.length;i++){
			randoms[i] = Math.abs(rand.nextInt() % util.input.size()-1);
		}
		//we'll run 1000 queries on both with the same array to ensure the same input
		System.out.println("Performing 1000 queries on the HashTrie...");
		double start = System.nanoTime();
		for(int i = 0;i<randoms.length;i++){
			ht.search(ht.root, util.input.get(randoms[i]));
		}
		double end = System.nanoTime();
		double HashTrieTime = (end - start)/1000000;
		System.out.printf("1000 queries performed in: %f milliseconds.\n\n",HashTrieTime);
		
		System.out.print("Performing 1000 queries on the ListTrie...\n");
		start = System.nanoTime();
		for(int i = 0;i<randoms.length;i++){
			lt.search(lt.root, util.input.get(randoms[i]));
		}
		end = System.nanoTime();
		double ListTrieTime= (end - start)/1000000;
		System.out.printf("1000 queries performed in: %f milliseconds.\n\n",ListTrieTime);
		
		double ratio = ListTrieTime/HashTrieTime ;
		System.out.printf("HashTrie was %.4f times faster than ListTrie\n",ratio);
		
		

	}
	
	public boolean loadfile(String path) {
		input = new ArrayList<String>();
		Scanner filescn;
		int count = 0;
		try {
			filescn = new Scanner(new File(path));
			while (filescn.hasNextLine()) {
				String line = filescn.nextLine();
				input.add(line);
				count++;
			}
		} catch (Exception e) {

			System.out.println(e);
		}
		if (input.size() == count) {
			System.out.println("File successfully loaded " + count );
			return true;
		} else
			System.out.println("File load unsuccessfull. ");
			return false;
	}
}