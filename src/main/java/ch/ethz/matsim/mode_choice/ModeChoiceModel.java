package ch.ethz.matsim.mode_choice;

import java.util.List;

import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.core.router.TripStructureUtils.Trip;

public interface ModeChoiceModel {
	String chooseMode(ModeChoiceTrip trip);
	List<String> chooseModes(Plan plan);
}
