package com.truecaller.assignment.pawntour.service;

import com.truecaller.assignment.pawntour.exception.PawnTourNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;


import static org.mockito.Mockito.*;
/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-02
 */
public class PawnTourTest {
	@InjectMocks
	PawnTour pawnTour;

	@Mock
	WarnsdorffTour warnsdorffTour;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		pawnTour = mock(PawnTour.class);
	}

	@Test
	public void pawnTourTest(){
		doNothing().when(pawnTour).start();
		pawnTour.start();
		verify(pawnTour,times(1)).start();
	}

	@Test(expected = PawnTourNotFoundException.class)
	public void pawnTourExceptionTest(){
		when(warnsdorffTour.getNextMove(new int[10][10], Collections.emptyList(),Position.newPosition(0,0),1,10,0)).thenReturn(false);
		doThrow(PawnTourNotFoundException.class).when(pawnTour).start();
		pawnTour.start();
	}


}
