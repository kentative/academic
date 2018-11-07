package ksl.academic.structure.stack;

import java.util.ArrayList;
import java.util.Collections;

public class StackFrequency {

	static ArrayList<Entry> items;

	/**
	 * Constructor
	 */
	public StackFrequency() {

		items = new ArrayList<Entry>();
	}

	public Object pop() {
		
		if (items.size() == 0) return null;
		
		int index = items.size() - 1;
		Entry entry = items.get(index);
		entry.decrementFreq();
		if (entry.getFrequency() == 0) {
			items.remove(index);
		}

		return entry.data;
	}

	public void push(Object item) {

		Entry existingEntry = null;
		Entry entry = new Entry(item);
		boolean entryFound = false;
		for (int i = items.size() - 1; i >= 0; i--) {
			existingEntry = items.get(i);
			if (existingEntry.equals(entry)) {
				existingEntry.incrementFreq();
				entryFound = true;
				break;
			}
		}

		if (!entryFound) {
			items.add(entry);
		}

		// add data
		Collections.sort(items);
	}

	private class Entry implements Comparable<Entry> {

		Object data;
		int frequency;

		Entry(Object data) {
			this.data = data;
			frequency = 1;
		}

		public void incrementFreq() {
			frequency++;
		}

		public void decrementFreq() {
			frequency--;
		}

		/**
		 * @return the frequency
		 */
		public int getFrequency() {
			return frequency;
		}

		@Override
		public int compareTo(Entry obj) {
			return frequency - ((Entry) obj).frequency;
		}
		
		public boolean equals(Object obj) {
			if (obj instanceof Entry) {
				return data.equals(((Entry) obj).data);
			}
			return false;
		}
		
		public int hashCode() {			
			return data.hashCode();
		}

	}

	public static void main(String[] args) {

		StackFrequency stack = new StackFrequency();

		for (int i = 0; i < 2; i++) {
			stack.push("Item1");	
		}
		
		for (int i = 0; i < 4; i++) {
			stack.push("Item2");
		}

		for (int i = 0; i < 6; i++) {
			stack.push("Item3");
		}
		
		for (int i = 0; i < 7; i++) {
			stack.push("Item4");
		}
		

		for (int i = 0; i < 19; i++) {
			System.out.println(stack.pop());
		}

	}
}
