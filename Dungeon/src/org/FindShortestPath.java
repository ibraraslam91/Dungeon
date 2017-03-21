package org;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FindShortestPath {
	public static void main(String[] args){
		Dungeon d = null;
		//Add file does not exist exception handling here
		try {
		d = new Dungeon(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("The file does not exist.");
		} catch (IOException f){
			System.out.println("IO Exception was thrown.");
		}
		//Create empty priority queue
		DLinkedPriorityQueue<Hexagon> container = new DLinkedPriorityQueue<Hexagon>();
	
		//Get the starting chamber 
		Hexagon start = d.getStart();


		
		//Ad the starting chamber to the priority queue
		container.add(start, 0);
		
		//Mark the starting chamber as enqueued
		start.markEnqueued();
		
		//Create a found boolean variable
		boolean found = false;
		
		//While loop to continue until exit has been marked as found
		//Is using the found variable correct or need to change this?
		while(!container.isEmpty()  && !found){
			//Store item to be removed in a Hexagon object 
			//Is this what they meant as the current chamber?? 
			//Did it that way for now - might need to change
			Hexagon current = container.removeMin();
			//Mark as dequeued
			current.markDequeued();



			//Determine if the container is adjacent to any dragons
			boolean nearDragon = false;
			for(int i=0; i < 5; i++){
				//Check if neighbour of that index is null (?)
				if(current.getNeighbour(i) != null){
					Hexagon neighbour = current.getNeighbour(i);
					if(neighbour.isDragon()){
						nearDragon = true;
					}
				}
			}
			
			//If this chamber is the exit, set found variable to true and exit loop
			if(current.isExit()){
				//Will the printing process work here?
				boolean stop = false;
				while(stop == false){
					System.out.println(current);
					if(current.getPredecessor() != null){
						current = current.getPredecessor();
					} else {
						stop = true;
					}
				}
				found = true;
			} 
			
			//Then check if current chamber has a dragon
			//Restart loop if it does
			else if(current.isDragon()){
				//Does the body actually 0need anything??
				found = false;
			//Check is current chamber is adjacent to a dragon
			} else if(nearDragon) {
				//Does the body need anything?
				found = false;
			} else {
			//Now insert the main algorithm
				for(int i = 0; i < 5; i++){
					//Commenting this out and shifting around for now
					//Hexagon neighbour = current.getNeighbour(i);
					//Combined conditions into if statement - make sure that works okay
					if(current.getNeighbour(i) != null && !current.getNeighbour(i).isWall() && !current.getNeighbour(i).isMarkedDequeued()){
						int D = current.getDistanceToStart() + 1; 
						Hexagon neighbour = current.getNeighbour(i);

						boolean modified = false;
						if(D < neighbour.getDistanceToStart()){
							neighbour.setDistanceToStart(D);
							modified =true;
						}
						neighbour.setPredecessor(current);

						if(neighbour.isMarkedEnqueued() && modified){

							double newPrio = neighbour.getDistanceToStart() + neighbour.getDistanceToExit(d);
							container.updatePriority(neighbour, newPrio);
						}
						//Should this be in else or should it be done regardless??
								//Trying to remove it as an else
								//ALSO trying to remove it from if statement (D <)
								double initialPriority = neighbour.getDistanceToStart() + neighbour.getDistanceToExit(d);
								container.add(neighbour, initialPriority);
								neighbour.markEnqueued();
					}
				}
			}
		}
	}
}
