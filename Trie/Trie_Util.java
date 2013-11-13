public class Trie_Util {

	public static void main(String[] args) {
		ListTrie lt = new ListTrie();
		lt.insertString("test");
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
		boolean contains = lt.tSearch("alex");
		System.out.println(contains);
	}
}
