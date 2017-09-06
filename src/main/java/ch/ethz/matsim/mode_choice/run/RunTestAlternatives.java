package ch.ethz.matsim.mode_choice.run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.router.StageActivityTypesImpl;
import org.matsim.core.router.TripStructureUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.misc.Counter;
import org.matsim.pt.PtConstants;

import ch.ethz.matsim.mode_choice.alternatives.ChainAlternatives;
import ch.ethz.matsim.mode_choice.alternatives.TripChainAlternatives;

public class RunTestAlternatives {
	static public void main(String[] args) {
		Config config = ConfigUtils.loadConfig(args[0]);
		Scenario scenario = ScenarioUtils.loadScenario(config);
		
		Iterator<? extends Person> personIterator = scenario.getPopulation().getPersons().values().iterator();
		
		while (personIterator.hasNext()) {
			int count = TripStructureUtils.getActivities(personIterator.next().getSelectedPlan(), new StageActivityTypesImpl(PtConstants.TRANSIT_ACTIVITY_TYPE)).size();
			
			if (count > 10) {
				personIterator.remove();
			}
		}
		
		List<String> chainModes = Arrays.asList("car", "bike");
		List<String> nonChainModes = Arrays.asList("pt", "walk");
		
		ChainAlternatives chainAlternatives = new TripChainAlternatives();
		
		Counter counter = new Counter("", "");
		
		for (Person person : scenario.getPopulation().getPersons().values()) {
			chainAlternatives.getTripChainAlternatives(person.getSelectedPlan(), chainModes, nonChainModes);

			counter.incCounter();
		}
		
		System.out.println("FINISH");
	}
}
