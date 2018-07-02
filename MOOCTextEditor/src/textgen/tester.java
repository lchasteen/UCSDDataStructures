package textgen;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.LinkedList;

public class tester {

	public static void main(String[] args) {
		MyLinkedList<String> ll = new MyLinkedList<>();
		//LinkedList<String> ll = new LinkedList<>();
		ll.add("0");
		ll.add("1");
		ll.add("2");
		ll.add("3");
		ll.add("4");
		//ll.add(1, "9");
		
		System.out.println("removed old element....." + ll.set(3, "99"));
		
		//System.out.println("removed...." + ll.remove(0));	
		
//		System.out.println(ll.get(0));
//		System.out.println(ll.get(1));
//		System.out.println(ll.get(4));
		
		
		for (String s : ll) {
			System.out.println(s);
		}
		//System.out.println(ll.get(1));
	}

}
