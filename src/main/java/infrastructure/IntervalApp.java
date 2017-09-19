package infrastructure;

import infrastructure.repositories.InMemoryIntervalRepository;
import infrastructure.repositories.IntervalInputCollector;
import merges.IntervalMergeStrategy;
import merges.UnsortedMerger;
import model.repositories.IntervalRepository;

public class IntervalApp {

	private static final boolean SCANNER_MODE = false;
	private static final IntervalMergeStrategy INTERVAL_MERGER = new UnsortedMerger();
	// private static final IntervalMergeStrategy INTERVAL_MERGER = new StackMerger();
	// private static final IntervalMergeStrategy INTERVAL_MERGER = new SortedMerger();
	private static final IntervalRepository INTERVAL_REPOSITORY = new InMemoryIntervalRepository();
	private static final IntervalInputCollector INTERVAL_INPUT_COLLECTOR = new IntervalInputCollector();

	public static void main(String[] args) {
		IntervalMergerApp.start(SCANNER_MODE, INTERVAL_MERGER, INTERVAL_REPOSITORY, INTERVAL_INPUT_COLLECTOR);
	}
}
