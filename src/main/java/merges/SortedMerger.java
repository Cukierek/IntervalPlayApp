package merges;

import model.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedMerger implements IntervalMergeStrategy {
	@Override
	public List<Interval> mergeIntervals(List<Interval> intervals) {
		List<Interval> output = new ArrayList<>();
		intervals.sort(Comparator.reverseOrder());

		intervals.forEach(interval -> System.out.println(interval));

		for (int i = 1; i < intervals.size(); i++) {
			Interval previous = intervals.get(i - 1);
			Interval current = intervals.get(i);
			if (current.overlaps(previous)) {
				while (previous.overlaps(current)) {
					previous = current.combined(previous);
					i++;
					current = intervals.get(i);
				}
				output.add(previous);
			} else {
				output.add(previous);
			}
		}

		return output;
	}
}
