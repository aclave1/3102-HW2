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
		String filepath = "Trie/in.txt";
		HW_Util util = new HW_Util();
		ListTrie lt = new ListTrie();

		util.loadfile(filepath);
		
		int lines = 0;
		for (String s : util.input) {
			System.out.println(lines);
			lt.insertString(s);
			lines++;
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
			// System.out.println("File load unsuccessfull. ");
			return false;
	}
}