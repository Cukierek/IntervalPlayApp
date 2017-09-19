package merges;

import model.Interval;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class UnsortedMerger implements IntervalMergeStrategy {
	@Override
	public List<Interval> mergeIntervals(List<Interval> intervals) {
		int tests = intervals.size();
		Random random = new Random();
		while(tests > 0) {
			int randomIndex = random.nextInt(intervals.size());
			Interval currentInterval = intervals.get(randomIndex);
			intervals.remove(randomIndex);
			for (Iterator<Interval> i = intervals.iterator(); i.hasNext();) {
				Interval innerInterval = i.next();
				if (currentInterval.overlaps(innerInterval)) {
					currentInterval = currentInterval.combined(innerInterval);
					i.remove();
				}
			}
			tests--;
			intervals.add(currentInterval);
		}
		return intervals;
	}
}
