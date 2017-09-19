package infrastructure;

import infrastructure.repositories.IntervalInputCollector;
import merges.IntervalMergeStrategy;
import model.Interval;
import ui.IntervalPresenter;
import model.repositories.IntervalRepository;

import java.util.*;

public class IntervalMergerApp {

	static void start(boolean scannerMode, IntervalMergeStrategy intervalMerger, IntervalRepository intervalRepository,
			IntervalInputCollector intervalInputCollector) {
		if (scannerMode) scannerModeStart(intervalMerger, intervalInputCollector);
		else repositoryModeStart(intervalMerger, intervalRepository);
	}

	private static void scannerModeStart(IntervalMergeStrategy intervalMerger, IntervalInputCollector intervalInputCollector) {
		List<Interval> inputList = intervalInputCollector.collectInput();
		IntervalPresenter.presentIntervals((intervalMerger.mergeIntervals(inputList)));
	}

	private static void repositoryModeStart(IntervalMergeStrategy intervalMerger, IntervalRepository intervalRepository) {
		List<Interval> inputList = intervalRepository.getInputIntervals();
		IntervalPresenter.presentIntervals((intervalMerger.mergeIntervals(inputList)));
	}
}
