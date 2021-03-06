package ch.ethz.matsim.mode_choice.utils;

import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.router.Dijkstra;
import org.matsim.core.router.DijkstraFactory;
import org.matsim.core.router.util.LeastCostPathCalculator;
import org.matsim.core.router.util.TravelDisutility;
import org.matsim.core.router.util.TravelTime;
import org.matsim.vehicles.Vehicle;

public class BlockingThreadSafeDijkstra implements ThreadSafeLeastCostPathCalculator {
	final private Dijkstra delegate;
	
	public BlockingThreadSafeDijkstra(Network network, TravelDisutility travelDisutility, TravelTime travelTime) {
		DijkstraFactory factory = new DijkstraFactory();
		delegate = (Dijkstra) factory.createPathCalculator(network, travelDisutility, travelTime);
	}

	@Override
	public Path calcLeastCostPath(Node fromNode, Node toNode, double startTime, Person person, Vehicle vehicle) {
		synchronized(delegate) {
			return delegate.calcLeastCostPath(fromNode, toNode, startTime, person, vehicle);
		}
	}
}
