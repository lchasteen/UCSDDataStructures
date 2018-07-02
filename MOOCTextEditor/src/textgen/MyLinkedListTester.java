/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<String> addEndList;
	MyLinkedList<String> sizeList;
	MyLinkedList<String> addElementAtList;
	MyLinkedList<String> setElementAtList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		addEndList = new MyLinkedList<>();
		sizeList = new MyLinkedList<>();
		addElementAtList = new MyLinkedList<>();
		setElementAtList = new MyLinkedList<>();
		
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		

		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			list1.remove(4);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		String testAdd[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i" };
		for (String s : testAdd) {
			assertTrue("Adding value: " + s, addEndList.add(s));
			assertEquals("Checking to see if value exists at the end of the list", s, addEndList.get(addEndList.size - 1));
		}
		

		try {
			addEndList.add(null);
			fail("Check NPE");
		}
		catch (NullPointerException e) {
		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		for (int i = 0; i < 1000; i++) {
			sizeList.add(String.valueOf(i));
		}
		assertEquals("Checking the size of the list is equal to 1000.", 1000, sizeList.size);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{	
		 addElementAtList.add("A");
		 assertEquals("Is element 0 = A?", "A", addElementAtList.get(0));
		 addElementAtList.add(0, "K");
		 assertEquals("Is element 0 = K?", "K", addElementAtList.get(0));
		 addElementAtList.add(2, "X");
		 assertEquals("Is element 2 = X?", "X", addElementAtList.get(2));
		 addElementAtList.add(3, "Y");
		 assertEquals("Is element 3 = Y?", "Y", addElementAtList.get(3));
		 addElementAtList.add(4, "Z");
		 assertEquals("Is element 4 = Z?", "Z", addElementAtList.get(4));
		 

		try {
			addElementAtList.add(-1, "sleepy");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			addElementAtList.add(100000, "man");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			addElementAtList.set(1, null);
			fail("Check for NPE");
		}
		catch (NullPointerException e) {
		}
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		for (int i = 0; i < 1000; i++) {
			setElementAtList.add(String.valueOf(i));
			assertEquals("Is value equal to " + i, String.valueOf(i), setElementAtList.get(i));
		}
		assertEquals("Checking the size of the list is equal to 1000.", 1000, setElementAtList.size);
		
		for (int i = 0; i < 1000; i++) {
			setElementAtList.set(i, "derkey");
		}
	    
		assertEquals("Is index 88 = derkey", "derkey", setElementAtList.get(88));
		
		try {
			setElementAtList.set(-1, "value");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			setElementAtList.set(1002, "value");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			setElementAtList.set(1, null);
			fail("Check for NPE");
		}
		catch (NullPointerException e) {
		}
		
	}
	
	
	// TODO: Optionally add more test methods.
	
}
