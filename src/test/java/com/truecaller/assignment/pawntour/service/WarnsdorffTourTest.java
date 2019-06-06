package com.truecaller.assignment.pawntour.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Mockito.mock;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-02
 */
public class WarnsdorffTourTest {

	@InjectMocks
	WarnsdorffTour warnsdorffTour;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		warnsdorffTour = mock(WarnsdorffTour.class);
	}

	@Test
	public void warnsdorffTourTest() {

		Boolean test  = warnsdorffTour.getNextMove(new int[10][10],Collections.emptyList(),Position.newPosition(9,9),1,10,0);
		Assert.assertEquals(false,test);

	}

}
