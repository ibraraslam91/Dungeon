package org;

public class DLinkedPriorityQueue<T> implements PriorityQueueADT<T> {
	private DPriorityNode<T> front;
	private DPriorityNode<T> rear;
	private int count;
	
	public DLinkedPriorityQueue() {
	//Create an empty priority queue - need to create a DLL object?? 
		front = null;
		rear = null;		
		}

	//Add element to the queue
	public void add(T element, double priority){
		DPriorityNode<T> item = new DPriorityNode(element, priority);
		if(isEmpty()){
			front = rear = item;
		}else{
			//Need to set previous too?
			rear.setNext(item);
			item.setPrev(rear);
			rear = item;
		}

		count++;
	}
	
	//Check if empty 
	public boolean isEmpty() {
		//Commenting out original code for now
		return front == null;

	}
	
	//Change the priority of a specified node to a specified double
	//NOTE: remove exceptions from project if not working at all
	public void updatePriority(T element, double newPriority) throws InvalidElementException {
		//Try adding a found variable??
		boolean found = false;

		DPriorityNode<T> currnetNode = front;
		while (currnetNode !=null){
			if(currnetNode.getElement() == element){
				currnetNode.setPriority(newPriority);
				found = true;
			}else
				currnetNode = currnetNode.getNext();
		}
		if(!found){
			throw new InvalidElementException("The item was not found");
		}
	}
	//Return the count variable indicating how many items there are in the queue
	public int size() {
		return count;
	}
	
	public String toString(){
		String s = "";
		DPriorityNode<T> loopNode = front;
		while (loopNode!=null){
			s  += loopNode.toString() + ", ";
			loopNode = loopNode.getNext();
		}
		return s;
	}
	
	public T removeMin() throws EmptyPriorityQueueException{
		if(isEmpty()){
			throw new EmptyPriorityQueueException("The priority queue is empty");
		}

		//Trying to change this from a large value to front's priority
		double min = front.getPriority();
		//Trying to change minNode from null to front
		DPriorityNode<T> minNode = front;
		DPriorityNode<T> searchNode = front;
		T minObject = front.getElement();

		for(int i = 0; i < count; i++){
			double thisPriority = searchNode.getPriority();
			//Updates minNode variable to current node if its priority is less than current min
			//Sets minObject to the object stored in the minNode
			if(thisPriority < min){
				min = thisPriority;
				minNode = searchNode;
				minObject = minNode.getElement();
			}
			if(searchNode.getNext() != null){
				searchNode = searchNode.getNext();
			}
		}
		//Min = front = rear case
		if (minNode == front && minNode == rear){
			//If min is only object, list is now empty again
			front = null;
			rear = null;
			count--;
		} else if (minNode == front) {
			//Min = front but isn't only node case
//			DPriorityNode<T> second = front.getNext();
//			second.setPrev(null);
//			front = second;
			front = front.getNext();
			front.setPrev(null);
			count--;
		} else if (minNode == rear){
			//Min = rear but isn't only node case
//			DPriorityNode<T> secondToLast = rear.getPrev();
//			secondToLast.setNext(null);
			rear = rear.getPrev();
			rear.setNext(null);

			count--;
		} else {
		if(minNode.getPrev() != null && minNode.getNext() != null){
			DPriorityNode<T> previousNode = minNode.getPrev();
			DPriorityNode<T> nextNode = minNode.getNext();
			//Change pointers to eliminate the node
			previousNode.setNext(nextNode);
			nextNode.setPrev(previousNode);
			count--;
		}
		}
		return minObject;

	}
	
}