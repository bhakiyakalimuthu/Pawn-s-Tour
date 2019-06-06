package com.truecaller.assignment.pawntour.service;

import com.truecaller.assignment.pawntour.exception.PawnTourNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-01
 */

@Service
public class PawnTour {

	private WarnsdorffTour warnsdorffTour;

	@Value("${default.board.size}")
	private int defaultBoardSize;

	@Autowired
	public PawnTour(WarnsdorffTour warnsdorffTour) {
		this.warnsdorffTour = warnsdorffTour;
	}

	public void start(){
		//-- default board size  would be 10,If size value not provided via command line
		this.start(defaultBoardSize);
	}

	/**
	 * Starting point to proceed the pawn tour
	 * @param initSize indicates the board size
	 */
	public void start(int initSize){
		//-- starting to get prerequisite data
		int size = initSize;
		int unvisitedValue = 0;

		System.out.println(
				String.format("\nWelcome to the pawn tour,the board sizes are: %s X %s", size, size)
		);
		System.out.println();

		List<Position> pawnMovements = getPawnMovementRule();

		//-- represent the maximum number of times this app will try to find a path
		//-- each attempt will restart/clean the board and get a new starting position
		int maxIterations = 100;
		//-- a flag to indicate if the iteration was successful
		boolean pathNotFound = true;

		//-- startTime will be used to calculate the total duration that it takes to find at least one pawn path
		long startTime = System.currentTimeMillis();

		while (shouldKeepTouring(maxIterations, pathNotFound)) {

			int[][] gameBoard = createAndInitializeBoard(size, unvisitedValue);

			//-- instead of giving a manual initial position, we will use a random generated one
			//-- following the basic rules:
			//-- It should be inside the gameBoard ( 0 >= position < size )
			Position initialPosition = getInitialPosition(size);
			System.out.println(
					"The initial position of the pawn is: [row:" + initialPosition.getX() + ", column:"
							+ initialPosition.getY() + "]");

			if (warnsdorffTour.getNextMove(gameBoard, pawnMovements , initialPosition, 1, size, unvisitedValue)) {
				System.out.println("Found path for pawn tour");
				pathNotFound = false;
			} else {

				System.out.println("Cannot find path for pawn");
				System.out.println("retrying...");
				throw new PawnTourNotFoundException("Cannot find path for pawn");
			}
		}

		System.out.println(String.format("Time taken to complete the entire valid tour : [%sms] ",System.currentTimeMillis() - startTime));
	}


	private List<Position> getPawnMovementRule(){
		return List.of(
				Position.newPosition(0,-3),  // West      
				Position.newPosition(0, 3),  // East      
				Position.newPosition(3, 0),  // South     
				Position.newPosition(-3,0),  // North     
				Position.newPosition(2,-2),  // SouthWest 
				Position.newPosition(2, 2),  // SouthEast 
				Position.newPosition(-2,-2), // NorthWest 
				Position.newPosition(-2,2)   // NorthEast 
		);
	}

	/**
	 * Helper method that will indicate if we should keep touring to find a path for the Pawn
	 * @param currentIteration this is the current iteration, giving that we are decreasing, its value be greater than 0
	 * @param pathNotFound basically if during the current iteration a solution was found
	 * @return true if another iteration must be performed, otherwise false
	 */
	private boolean shouldKeepTouring(int currentIteration, boolean pathNotFound) {
		return pathNotFound && currentIteration > 0;
	}
	
	/**
	 * Calculates the initial position in the board using random values
	 * @param size indicates the bound for the random generated integer (this value is excluded)
	 * @return a new position representing the starting position in the board
	 */
	private Position getInitialPosition(int size) {
		Random random = new Random();
		return Position.newPosition(random.nextInt(size), random.nextInt(size));
	}


	/**
	 * Creates and Initializes the board
	 * @param size indicates the board size
	 * @param unvisitedValue indicates the value which will be used to indicate that a square has not been visited yet
	 * @return a new board of size x size
	 */
	private int[][] createAndInitializeBoard(int size, int unvisitedValue) {
		int[][] board = new int[size][size];

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				board[x][y] = unvisitedValue;
			}
		}

		return board;
	}
}
