package com.truecaller.assignment.pawntour.service;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-01
 */

public class Position {

	private  int x;
	private  int y;


	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Just a regular factory method to avoid doing manual creation of Points
	 * @param x value representing the x axe in the board
	 * @param y value representing the y axe in the board
	 * @return The Position
	 */
	public static Position newPosition(int x, int y) {
		return new Position(x,y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * Checks that the nextPosition is inside the board ( more than 0 and less than dimension) and if the pawn has not already visited
	 * @return true if the nextPosition is safe to move to, false otherwise
	 */

	public static boolean isMoveAllowed(int[][] board, Position nextPosition, int dimension, int unvisitedValue) {
		return isInsideTheBoard(dimension, nextPosition) && isUnvisited(board, unvisitedValue, nextPosition);
	}

	public static boolean isUnvisited(int[][] board, int unvisited, Position nextPoint) {
		return board[nextPoint.getX()][nextPoint.getY()] == unvisited;
	}

	public static boolean isInsideTheBoard(int dimension, Position nextPoint) {
		return (nextPoint.getX() >= 0 && nextPoint.getY() >= 0) && (nextPoint.getX() < dimension
				&& nextPoint.getY() < dimension);
	}
}
