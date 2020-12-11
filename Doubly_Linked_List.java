
/**
 * Maman 15:
 * This class defines a sorted doubly linked list of integers with various methods
 * @author Maayan Chechik 
 * ID#: 214307472
 * Date: 08/06/19
 */
import java.util.Scanner;

public class IntListTwo {
	private IntNodeTwo _head, _tail;
	public Scanner reader;

	public IntListTwo() {
		_head = null;
		_tail = null;
	}

	public IntListTwo(IntNodeTwo h, IntNodeTwo t) {
		_head = h;
		_tail = t;
	}

	/**
	 * This method receives an integer and adds a node to the list with that integer
	 * value. The new node is inserted in the correct placement according to its
	 * value. The method updates the links between the nodes accordingly.
	 * 
	 * The run-time complexity is O(n), where n is the length of the list, because
	 * in the worst-case scenario, the method goes through the whole list in order
	 * to find the correct place for the new node. The space complexity is O(1),
	 * because there is a constant amount of variables to be set in the memory.
	 * 
	 * @param num is the value of the new node that is to be added to the list.
	 */
	public void addNumber(int num) {
		IntNodeTwo node = new IntNodeTwo(num);// The new node.
		// If the list is empty, make the new node the list.
		if (_head == null) {
			_head = node;
			_tail = node;
			return;
		}
		// If the value of the head is already bigger than the given number, place
		// the new number in the beginning as the new head.
		if (_head.getNum() >= num) {
			node.setNext(_head);
			_head.setPrev(node);
			_head = node;
			return;
		}
		// This loop goes through the list until it reaches the end of the list
		// or the first number that is bigger then num.
		// The complexity is O(n), where n is the length of the list, since
		// in the worst case scenario it will go through the whole list.
		IntNodeTwo current = _head;// This node is used to go through the list.
		while (current != _tail && current.getNum() < num) {
			current = current.getNext();
		}
		// If num is still bigger then current, then current has to be equal
		// to tail. So we need to put a new node after current and set the tail
		// to the new node.
		if (current.getNum() < num) {
			_tail = node;
			current.setNext(node);
			node.setPrev(current);
			return;
		}
		// Else, new node should be before current.
		node.setPrev(current.getPrev());
		current.getPrev().setNext(node);
		node.setNext(current);
		current.setPrev(node);
	}

	/**
	 * This method receives an integer and removes from the list a node with this
	 * value. If there is no node with this value it does nothing.
	 * 
	 * The run-time complexity is O(n), where n is the length of the list, because
	 * in the worst-case scenario, the method goes through the whole list in order
	 * to find the node that has to be remove. The space complexity is O(1), because
	 * there is a constant amount of variables to be set in the memory.
	 * 
	 * @param num is the value of the node that is to be removed from the list.
	 */
	public void removeNumber(int num) {
		// If the list is empty, don't do anything.
		if (_head == null) {
			return;
		}
		// If the head's value is num, make the next node the head (if
		if (_head.getNum() == num) {
			_head = _head.getNext();
			// If there was only one node in the list, and it got erased the tail should be
			// updated to null as well.
			if (_head == null)
				_tail = null;
			return;
		}
		// If there is only one node in the list(it's value is the not num we already
		// checked) then don't do anything.
		if (_head == _tail)
			return;
		// This loop goes through the list until current is the tail or the node with
		// the wanted value.
		IntNodeTwo current = _head.getNext();
		while ((current != _tail) && (current.getNum() != num)) {
			current = current.getNext();
		}
		// If the value of current is num, then it has to be removed.
		if (current.getNum() == num) {
			// The previous node's "next" pointer should skip current node.
			current.getPrev().setNext(current.getNext());
			// If current is tail then the tail should be updated
			if (current == _tail) {
				_tail = current.getPrev();
				// Otherwise the next node's "prev" pointer should skip current node.
			} else {
				current.getNext().setPrev(current.getPrev());
			}
		} // else, if the given value is not in the list, do nothing
	}

	/**
	 * This method uses the Scanner object to receive integers from the user, and
	 * adds new nodes with these values to the list. It finishes when the user
	 * enters the value -9999.
	 * 
	 * The run-time complexity is O(n^2), where n is the number of integers entered
	 * by the user, because the method adds n new nodes to the list, and for each
	 * new node it calls the method addNumber. The method addNumber has a complexity
	 * of O(n) as previously explained in the method's API. Therefore, the overall
	 * complexity of the method is O of n times n, which is O(n^2). The space
	 * complexity is O(n), where n is the number of integers entered by the user,
	 * because for each integer the method calls the addNumber method, therefore it
	 * defines n new variables in the memory.
	 */
	public void readToList() {
		reader = new Scanner(System.in);
		System.out.println("Please enter any list of integers to create a doubly linked list of integers, you may enter -9999 to stop");
		int num = reader.nextInt();
		// The complexity of this loops is O(n), where n is the amount of numbers
		// the user enters until it enters -9999
		while (num != -9999) {
			// The complexity of this operations is O(n) because the run-time complexity
			// of the addNumber method is O(n)
			addNumber(num);
			num = reader.nextInt();
		}
	}

	/**
	 * This method returns a string representation of this doubly linked list, like
	 * so: {integer, integer, integer....}
	 * 
	 * The run-time complexity is O(n), where n is the length of the list, because
	 * the method has to go through each node of the list in order to add it's value
	 * to the string. The space complexity is O(1), because the amount of variables
	 * set in the memory is constant.
	 */
	public String toString() {
		String st = "{";
		// If the list is empty, head = current = tail = null, so program will not enter
		// loop.
		IntNodeTwo current = _head;
		while (current != _tail) {
			st += current.getNum() + ", ";
			current = current.getNext();
		}
		// If list not empty, add tail value as a special "fence" case, since it
		// shouldn't have a comma at the end.
		if (_head != null)
			st += _tail.getNum();
		st += "}";
		return st;
	}

	/**
	 * This method returns the length of this list i.e. the number of nodes in this
	 * list.
	 * 
	 * The run-time complexity is O(n), where n is the length of the list, because
	 * the method has to go through each node in the list and count it. The space
	 * complexity is O(1) because there is a constant amount of variables to be set
	 * in the memory
	 * 
	 * @return the length of this list.
	 */
	public int length() {
		int length = 0;
		IntNodeTwo current = _head;
		// This loop goes through the whole list until it passes the tail
		while (current != null) {
			length++;
			current = current.getNext();
		}
		return length;
	}

	/**
	 * This method returns the sum of the nodes' values in this list.
	 * 
	 * The run-time complexity is O(n), where n is the length of the list, because
	 * the method has to go through each node in the list and add it to the sum. The
	 * The space complexity is O(1) because there is a constant amount of variables
	 * to be set in the memory
	 * 
	 * @return the length of this list.
	 */
	public int sum() {
		int sum = 0;
		IntNodeTwo current = _head;
		// This loop goes through the whole list until it passes the tail
		while (current != null) {
			sum += current.getNum();
			current = current.getNext();
		}
		return sum;
	}

	/**
	 * This method returns the length of the longest sub-list, from this list, whose
	 * sum is even.
	 * 
	 * The run-time complexity is O(), where n is the length of this list, because
	 * the method has to go through the list in order to count the number of odd
	 * numbers and find the last odd number in the list.
	 * 
	 * The space complexity is O(1), because there is a constant amount of variables
	 * to be set in the memory
	 * 
	 * @return the length of the longest sub-list with an even sum.
	 */
	public int maxLength() {
		int iOddFirst = -1; // index of first odd number
		int iOddLast = 0;
		int numOdds = 0;
		int index = 0;
		// Go through list, count odd numbers and save indices of the first and the last
		// odd
		// number.
		IntNodeTwo current = _head;
		// This loop goes through the list until it passes the tail, so the run-time
		// complexity of this loop is O(n), where n is length of the list.
		while (current != null) {
			if (current.getNum() % 2 != 0) {
				numOdds++;
				if (iOddFirst == -1) {
					iOddFirst = index;
				}
				iOddLast = index;
			}
			index++;
			current = current.getNext();
		}
		// If there is an even amount of odd numbers, their sum is even.
		if (numOdds % 2 == 0) {
			return length();
		} else {
			// If there is an odd amount of odd numbers, then we have to cut out one of the
			// odd numbers.
			// Check which odd number is closest to the end and return the length of the
			// list without it.
			if (iOddFirst < (length() - 1 - iOddLast)) {
				return length() - iOddFirst - 1;
			} else {
				return iOddLast;
			}
		}
	}

	/**
	 * This method receives a number and returns whether or not there is a sub-list
	 * whose values' average is the given number.
	 * 
	 * The run-time complexity is O(n), where n is the length of the list, because
	 * the method goes through the list from both sides towards the middle, and in
	 * the worst case scenario, it has to go through the whole list once. The space
	 * complexity is O(1), because there is a constant amount of variables to be set
	 * in the memory.
	 * 
	 * @param num is the average the method is trying to find in the averages of the
	 *            sub-lists of this list.
	 * @return true if there is a sub-list in this list whose values' average is
	 *         num.
	 */
	public boolean isAverage(double num) {
		if (_head == null)
			return false;
		if (_head.getNum() == num || _tail.getNum() == num)
			return true;
		int sum = sum();
		int length = length();
		double average = sum / (double) length;// casting double to make division correct, since average is double and
												// length is int.
		IntNodeTwo st = _head;
		IntNodeTwo end = _tail;
		// Reduce the average from each side of the list until the correct average
		// is found, or there is only one node left.
		// This loop has the complexity of O(n), when n equals the length of the list,
		// because in the worst case scenario, it goes through the whole list once.
		while (st != end) {
			// return true if the correct average is found in the list.
			if (average == num)
				return true;
			// If the average is smaller then num, then the only way to increase
			// the average is to remove the left-most node from the average.
			if (average < num) {
				sum = sum - st.getNum();
				length--;
				average = sum / (double) length;
				st = st.getNext();
			}
			// If the average is larger then num, then the only way to decrease
			// the average is to remove the right-most node from the average.
			if (average > num) {
				sum = sum - end.getNum();
				length--;
				average = sum / (double) length;
				end = end.getPrev();
			}
		}
		// If the last node left has the correct average, return true.
		if (st.getNum() == num)
			return true;
		// Otherwise, if no sub-list reached the correct average, return false
		return false;
	}

}