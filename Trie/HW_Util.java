/**
        @authors: Alex Clavelle, Kelsey Cameron
Description: Creates a K-ary heap where K is the maximum number of children for a non-leaf node.
 */
import java.util.*;
import java.io.*;

public class HW_Util {
	List<String> input;

	public static void main(String[] args) {
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
		
		
		HashTrie test = new HashTrie(20);
		test.insertString("cat");
		test.insertString("cap");
		test.insertString("dog");
		test.insertString("chips");
		test.insertString("clips");
		test.insertString("horsey");
		test.hashify(test.root);
		System.out.println(test.search(test.root, "cat"));

		
		
		System.out.println(ht.search(ht.root, "cat"));
		for (String s : util.input) {
			if (lt.search(lt.root, s) == false) {
				System.out.println(s + " returned false.");
			}
		}
		
		System.out.printf("\n%d words added",lines);

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