package merges;

import model.Interval;

import java.util.List;

public interface IntervalMergeStrategy {
	List<Interval> mergeIntervals(List<Interval> intervals);
}
