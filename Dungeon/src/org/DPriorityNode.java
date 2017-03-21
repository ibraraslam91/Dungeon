package org;

public class DPriorityNode<T> {
	//Declare the instance variables
	private T element;
	private DPriorityNode<T> next;
	private DPriorityNode<T> prev;
	private double priority;
	
	//Constructor with no attributes provided
	public DPriorityNode(){
		element=null;
		next=null;
		prev=null;
		//Cast to double or set to 0??
		priority=(Double) null;
	}
	
	//Constructor with data element and priority passed
	public DPriorityNode(T data, double prio){
		next = null;
		prev = null;
		element=data;
		priority=prio;
	}
	
	//Return priority of this node
	public double getPriority(){
		return priority;
	}
	
	//Return data element of this node
	public T getElement(){
		return element;
	}
	
	//Return next node in linked list
	public DPriorityNode<T> getNext(){
		return next;
	}
	
	public DPriorityNode<T> getPrev(){
		return prev;
	}
	
	public void setNext(DPriorityNode<T> node){
		next = node;
	}
	
	public void setPriority(double newPrio){
		priority = newPrio;
	}
	
	public void setElement(T elem){
		element = elem;
	}
	
	public void setPrev(DPriorityNode<T> prevNode){
		prev = prevNode;
	}
}