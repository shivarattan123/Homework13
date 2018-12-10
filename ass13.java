public final class LinkedBag<T> implements BagInterface<T> {

	private Node firstNode; // Feference to first node
	private int numberOfEntries;

	public LinkedBag() {
-		firstNode = null;
		numberOfEntries = 0;
	} // end default constructor
	
private class Node {

		private T data; // Entry in bag
		private Node next; // link to next node

		private Node(T dataPortion) {
			this(dataPortion, null);
		} // end constructor

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		} // end constructor
	} // end Node

/**
	 * Removes one unspecified entry from this bag, if possible.
	 * 
	 * @return Either the removed entry, if the removal was successful, or null.
	 */
	public T remove() {
		T result = null;
		// Modify to remove random entry.

		if (firstNode != null) {
			Random rd = new Random();
			int index = rd.nextInt(numberOfEntries) + 1, count = 1;
			Node temp = firstNode;
			while (temp != null && count != index) {
				count++;
				temp = temp.next;
			}
		
			T c = temp.data;
			temp.data = firstNode.data;
			firstNode.data = c;
		}

		if (firstNode != null) {
			result = firstNode.data;
			firstNode = firstNode.next; // Remove first node from chain
			numberOfEntries--;
		} // end if

		return result;
	} // end remove

/*********************************************************************
	 * 
	 * METHODS TO BE COMPLETED
	 * 
	 * 
	 ************************************************************************/

	/**
	 * Check to see if two bags are equals.
	 * 
	 * @param aBag Another object to check this bag against.
	 * @return True if the two bags contain the same objects with the same
	 *         frequencies.
	 */
	public boolean equals(LinkedBag<T> aBag) {
		boolean result = false; // result of comparison of bags

		// If both bags are empty then they are equal
		if (isEmpty() && aBag.isEmpty())
			return true;

		// If size of both bags are equal then only both can be equal
		if (getCurrentSize() == aBag.getCurrentSize()) {
			Node temp1 = firstNode;
			while (temp1 != null) {
				Node temp2 = aBag.firstNode;

// compare the frequency of each element of first bag with 
// element of the another bag
				while (temp2 != null) {
					if (temp1.data.equals(temp2.data)
&& getFrequencyOf(temp1.data) == aBag.getFrequencyOf(temp2.data)) {
						break;
					}
					temp2 = temp2.next;
				}
				if (temp2 != null) {
					result = true;
				} else {
					result = false;
					break;
				}
				temp1 = temp1.next;
			}
		}
		return result;
	} // end equals

	/**
	 * Duplicate all the items in a bag.
	 * 
	 * @return True if the duplication is possible.
	 */
	public boolean duplicateAll() {
		boolean success = true; // should always return true
					    // if there is a problem allocating nodes
     		    // an exception will be thrown

		try {

			// Add each node at the beginning of the original bag.
			Node temp = firstNode;
			while (temp != null) {
				success = add(temp.data);
				temp = temp.next;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	} // end duplicateAll

	/**
	 * Remove all duplicate items from a bag
	 */
	public void removeDuplicates() {

		// New bag for unique elements
		Node newFirstNode = null;
		int newNumberOfEntries = 0;

		Node temp1 = firstNode;
		while (temp1 != null) {
			Node temp2 = newFirstNode;

// If there is duplicate element in bag no need to add it in the 
   new bag.
			while (temp2 != null) {
				if (temp1.data.equals(temp2.data)) {
					break;
				}
				temp2 = temp2.next;
			}

			// Add if no duplicate is present in the new bag.
			if (temp2 == null) {
				Node newNode = new Node(temp1.data);
				newNode.next = newFirstNode;
				newFirstNode = newNode;
				newNumberOfEntries++;
			}
			temp1 = temp1.next;
		}

		// Change the original bag to the new bag with unique elements.
		firstNode = newFirstNode;
		numberOfEntries = newNumberOfEntries;
		return;
	} // end removeDuplicates
}
