public class Trie_Util {

	public static void main(String[] args) {
		ListTrie lt = new ListTrie();
		lt.insertString("test");
		lt.insertString("task");
		lt.insertString("tassle");
		
		lt.insertString("alex");
		lt.insertString("kelsey");
		lt.insertString("eclipse");
		lt.insertString("intellij");
		lt.insertString("ecmascript");
		lt.insertString("java");
		lt.insertString("android");
		lt.insertString("linux");
		lt.insertString("javascript");
		lt.insertString("gcc");
		lt.insertString("csc");
		lt.insertString("computer");
		
		int diff = ListTrie.diffIndex("roch", "richardson");
		System.out.println(diff);
		
	}
}
