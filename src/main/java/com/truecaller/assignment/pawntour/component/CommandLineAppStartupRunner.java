package com.truecaller.assignment.pawntour.component;

import com.truecaller.assignment.pawntour.service.PawnTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-01
 */
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	private PawnTour pawnTour;

	@Autowired
	public CommandLineAppStartupRunner(PawnTour pawnTour) {
		this.pawnTour = pawnTour;
	}

	/**
	 * Run method will be called once the application started (spring beans are initialized)
	 * This will intern start the service
	 * @param args indicates the board size
	 */

	@Override
	public void run(String... args) {
		if(args.length > 0){
			pawnTour.start(Integer.parseInt(args[0]));
		}else {
			pawnTour.start();
		}

	}
}
