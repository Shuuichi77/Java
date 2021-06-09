package fr.umlv.data;

import java.util.Objects;

public class LinkedLink<E> {
	private Link<E> list;
	private int listLength = 0;
	
	public LinkedLink(Link<E> list) {
		this.list = list;
	}

	/**
	 * Add a new value at the head of the list
	 * @param value The new value we are adding at the head of the list
	 */
	public void add(E value) {
		Objects.requireNonNull(value);
		Link<E> newHead = new Link<E>(value, list);
		this.list = newHead;
		listLength++;
	}
	
	/**
	 * Return the value of the Link number "index" in the LinkedLink
	 * @param index Number of the Link we are looking for
	 * @return The value of the link founded
	 */
	public E get(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("index < 0");
		}
		
		if (index >= listLength) {
			throw new IllegalArgumentException("index too high, not enough values");
		}
		
		Link<E> temp = list;
		for (; index > 0 ; index--) {			
			temp = temp.next();
		}
		
		return temp.value();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Link<E> temp = list;
		
		while (temp != null) {
			builder.append(temp + " ");
			temp = temp.next();
		}
		
		return builder.toString();
	}
	
	
	/**
	 * Check if obj is in the LinkedLink list
	 * @param obj The obj we are looking for
	 * @return True if obj is in list, false if not
	 */
	public boolean contains(Object obj) {
		Objects.requireNonNull(obj);
		
		if (listLength == 0) {
			return false;
		}
		
		Link<E> temp = list;
		for (int i = 0 ; i < listLength ; i++) {
			if (temp.value().equals(obj)) {
				return true;
			}
			
			temp = temp.next();
		}
	
		return false;
	}
}
