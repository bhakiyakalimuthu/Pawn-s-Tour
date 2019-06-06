package com.truecaller.assignment.pawntour.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-01
 */

@Service
public class WarnsdorffTour {


	/**
	 * Finds the pawn's next move in the gameBoard using the current position as its base and recursion
	 * It uses the Warnsdorff’s algorithm (heuristic), tries to move to a position that contains the min
	 * number of degrees (graph theory)
	 * basically the Pawn will move to the location where it has the minimum number of possibly allowed moves
	 * @param gameBoard this is the "game" board
	 * @param pawnMovements represent all the pawn pawnMovements
	 * @param currentPosition this is the current location of the pawn in the gameBoard
	 * @param stepNumber represents the step or number of moves the pawn has performed so far
	 * @param dimension the dimensions of the gameBoard
	 * @param unvisitedValue value representing that the pawn has not yet visited a tile|square|location in the board
	 * @return indicates if the the next move can be done or not
	 */
	public boolean getNextMove(int[][] gameBoard, List<Position> pawnMovements, Position currentPosition,
								int stepNumber, int dimension, int unvisitedValue) {

		//-- this will helps us with two situations:
		//-- knowing that the pawn already visited a tile
		//-- during the printing it will be easy to see the path
		gameBoard[currentPosition.getX()][currentPosition.getY()] = stepNumber;

		//-- the idea is to follow the pawn movements, so we print the current state of the gameBoard
		System.out.println("Step: " + stepNumber);
		System.out.println();
		displayGameboard(gameBoard, dimension);
		System.out.println();

		//-- this will gives us the next location in the gameBoard (from current position) that will have the lowest
		//-- degree (number of allowed positions)
		//-- this is the heuristic used to choose the next movement
		Optional<Position> positionWithLowestDegree = findPositionWithLowestDegree(gameBoard, pawnMovements,
				currentPosition, dimension, unvisitedValue);

		//-- Giving that from whatever location in the gameBoard a pawn must be able to move at least to one location
		//-- when we get to the Position where there is not possible next move we can safely say we have finished
		if (positionWithLowestDegree.isEmpty()) {
			return true;
		}

		//-- we recursively call this method again but changing the data with the just found information
		return getNextMove(gameBoard, pawnMovements, positionWithLowestDegree.get(), stepNumber + 1,
				dimension, unvisitedValue);
	}

	/**
	 * Iterates and finds the next position with the lowest degree value
	 * @param gameBoard the "game" board
	 * @param pawnMovements all possible moves that the pawn can perform
	 * @param currentPosition the current location of the pawn
	 * @param boardDimension dimension of the gameBoard
	 * @param unvisitedValue value used to identify whether or not the pawn has already visited a location in the gameBoard
	 * @return The next position the pawn should move to, in the case that it is not possible to move to other location
	 * then this function will return an empty optional
	 */
	private Optional<Position> findPositionWithLowestDegree(int[][] gameBoard, List<Position> pawnMovements, Position currentPosition,
														 int boardDimension, int unvisitedValue) {

		int minimumDegreeValue = Integer.MAX_VALUE;
		Position chosenNextPosition = null;

		for (Position pawnMovement : pawnMovements) {


			//-- we calculate the position in the gameBoard
			Position nextPossiblePosition = Position.newPosition(
					currentPosition.getX() + pawnMovement.getX(),
					currentPosition.getY() + pawnMovement.getY()
			);

			//-- only allowed position must be considered
			if (Position.isMoveAllowed(gameBoard, nextPossiblePosition, boardDimension, unvisitedValue)) {
				//-- we get the degree value for the possible next movement
				int currentPawnMovementDegree = getDegree(gameBoard, pawnMovements, nextPossiblePosition,
						boardDimension, unvisitedValue);
				//-- the idea is to find position with the lowest degree
				if (currentPawnMovementDegree < minimumDegreeValue) {
					minimumDegreeValue = currentPawnMovementDegree;
					chosenNextPosition = nextPossiblePosition;
				}
			}
		}
		return Optional.ofNullable(chosenNextPosition);
	}

	/**
	 * Here we calculate the number of tiles that are allowed to be visited from the current location
	 *
	 * @param gameBoard the "game" board
	 * @param pawnMovements all possible moves that the pawn can perform
	 * @param currentPosition the location from where we will calculate the degree
	 * @param boardDimension dimension of the gameBoard
	 * @param unvisitedValue value used to identify whether or not the pawn has already visited a location in the gameBoard
	 * @return number of tiles that are allowed to be visited from the current location
	 */
	private int getDegree(int[][] gameBoard, List<Position> pawnMovements, Position currentPosition, int boardDimension,
						  int unvisitedValue) {

		int degree = 0;
		for (Position pawnMovement : pawnMovements) {
			Position nextPosition = Position.newPosition(
					currentPosition.getX() + pawnMovement.getX(),
					currentPosition.getY() + pawnMovement.getY());

			if (Position.isMoveAllowed(gameBoard, nextPosition, boardDimension, unvisitedValue)) {
				degree++;
			}
		}
		return degree;
	}

	/**
	 * Helper function that will print in the console the content of the chequerboard with some formatting
	 * @param gameBoard the "game" board
	 * @param dimension dimension value of the chequerboard
	 */
	private void displayGameboard(int[][] gameBoard, int dimension) {
		for (int row = 0; row < dimension; row++) {
			for (int column = 0; column < dimension; column++) {
				System.out.printf("%3d", gameBoard[row][column]);
			}
			System.out.println();
		}
	}
}
