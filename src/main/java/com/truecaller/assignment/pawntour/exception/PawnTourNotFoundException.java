package com.truecaller.assignment.pawntour.exception;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-02
 */
public class PawnTourNotFoundException extends RuntimeException {

	/**
	 * PawnTourNotFoundException constructor will be called if pawn tour is not found
	 * @param message indicates exception message
	 */
	public PawnTourNotFoundException(String message) {
		super(message);
	}
}
