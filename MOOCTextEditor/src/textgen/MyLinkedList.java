package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		this.head.next = tail;
		this.tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null) {
			throw new NullPointerException("Unable to insert a null element.");
		}
		this.tail = new LLNode<E>(element, tail);
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{	
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		LLNode<E> curr = head.next;
		for (int i = 0; i < size; i++) {
			if (index == i) {
				return curr.data;
			}
			curr = curr.next;
		}
		
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element == null) {
			throw new NullPointerException("Unable to insert a null element.");
		}
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		
		LLNode<E> curr = head.next;
		for (int i = 0; i <= size; i++) {
			if (index == i) {
				LLNode<E> newN = new LLNode<>(element);
				LLNode<E> prev = curr.prev;
				prev.next = newN;
				newN.next = curr;
				newN.prev = prev;
				curr.prev = newN;
				size++;
				break;
			}
			curr = curr.next;
		}
		
	}


	/** Return the size of the list */
	public int size() 
	{	
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		
		LLNode<E> curr = head.next;
		for (int i = 0; i <= size; i++) {
			if (index == i) {
				LLNode<E> prev = curr.prev;
				LLNode<E> next = curr.next;
				LLNode<E> result = curr;
				prev.next = next;
				next.prev = prev;
				size--;
				return result.data;
			}
			curr = curr.next;
		}
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null) {
			throw new NullPointerException("Unable to insert a null element.");
		}
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		
		LLNode<E> curr = head.next;
		for (int i = 0; i <= size; i++) {
			if (index == i) {
				E old = curr.data;
				curr.data = element;
				return old;
			}
			curr = curr.next;
		}
		return null;
	}   
}

class LLNode<E> 
{
	//-- properties --//
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	//-- constructors --//
	/**
	 * Creates a new LLNode with {@code null} prev and next values.
	 * @param e - {@link E} the node data.
	 */
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	/**
	 * Creates a new LLNode as the new tail element.
	 * Links old tail node to this and sets the data for
	 * the old tail node.
	 * @param e - {@link E} the node data.
	 */
	public LLNode(E e, LLNode<E> tail) 
	{	
		tail.data = e;
		this.data = null;
		this.prev = tail;
		tail.next = this;
		this.next = null;
	}

}
