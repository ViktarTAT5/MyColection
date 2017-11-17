package by.htp.myCollection.myLinkedList;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E>, Deque<E> {

	private int size;
	Node<E> first;
	Node<E> last;
	Node<E> iterator = first;

	public MyLinkedList() {
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (first == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		if (o != null) {
			Node<E> temp = first;
			for (int i = 0; i < size; i++) {
				if (o.equals(temp)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		Object[] tepm = new Object[size];
		int i = 0;
		for (Node<E> node = first; node != null; node = node.next) {
			tepm[i++] = node.item;
		}
		return tepm;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		Object[] temp;
		if (a.length < size) {
			temp = new Object[size];
		} else {
			temp = new Object[a.length];
		}
		int i = 0;
		for (Node<E> node = first; node != null; node = node.next) {
			temp[i++] = node.item;
		}
		a = (T[]) temp;
		return a;
	}

	public void addFirst(E element) {
		if (element != null) {
			first = new Node(null, element, first);
			size++;
		}
	}

	public void addLast(E element) {
		if (element != null) {
			last = new Node(null, element, first);
			size++;
		}
	}

	@Override
	public boolean add(E e) {
		if (e != null) {
			Node<E> temp;
			if (size != 0) {
				temp = new Node(last, e, null);
				last.next = temp;
				last = temp;
				size++;
			} else {
				temp = new Node(null, e, null);
				first = temp;
				last = temp;
				size++;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		if (o != null) {
			Node<E> temp = first;
			for (int i = 0; i < size; i++) {
				if (o.equals(temp)) {
					temp.prev.next = temp.next;
					temp.next.prev = temp.prev;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		return addAll(size - 1, c);
	}

	@Override
	public boolean addAll(int index, Collection c) {
		Object[] mas = c.toArray();
		Node<E> node;
		if ((node = getNode(index)) != null) {
			for (Object i : mas) {
				new Node<E>(node.prev, (E) i, node);
				node = node.next;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node<E> cell = getNode(index);
		if (cell != null) {
			return cell.item;
		} else {
			return null;
		}
	}

	public E getFirst() {
		return first.item;
	}

	public E getLast() {
		return last.item;
	}

	private Node<E> getNode(int index) {
		Node<E> temp = null;
		if (index < size) {

			if (index < size / 2) {
				temp = first;
				for (int i = 0; i < index; i++) {
					temp = temp.next;
				}

			} else {
				temp = last;
				for (int i = size - 1; i > index; i--) {
					temp = temp.prev;
				}
			}
		}
		return temp;
	}

	@Override
	public E set(int index, Object element) {
		Node<E> node = getNode(index);
		E e = node.item;
		node.item = (E) element;
		return e;
	}

	@Override
	public void add(int index, Object element) {
		Node<E> node = getNode(index);
		Node<E> temp = new Node(node.prev, element, node);
		node.prev = temp;
	}

	@Override
	public E remove(int index) {
		Node<E> node = getNode(index);
		node.prev.next = node.next;
		node.next.prev = node.prev;
		return node.item;
	}

	@Override
	public int indexOf(Object o) {
		Node<E> temp = null;
		if (o != null) {
			temp = first;
			for (int i = 0; i < size; i++) {
				temp = temp.next;
				if (temp.item.equals(o))
					return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		Node<E> temp = null;
		if (o != null) {
			temp = first;
			for (int i = size; i > 0; i--) {
				temp = temp.next;
				if (temp.item.equals(o))
					return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;

		Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.prev = prev;
			this.next = next;
		}
	}

	@Override
	public boolean offerFirst(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerLast(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E removeFirst() {
		if (first == null)
			return null;
		E e = first.item;
		if (first.next == null) {
			first = null;
			last = null;
		} else {
			first.next.prev = null;
			first = first.next;
		}
		return e;
		
	}

	@Override
	public E removeLast() {
		if (last == null)
			return null;
		E e = last.item;
		if (last.prev == null) {
			first = null;
			last = null;
		} else {
			last.prev.next = null;
			last = last.prev;
		}
		return e;
	}

	@Override
	public E pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(E e) {
		// TODO Auto-generated method stub

	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
