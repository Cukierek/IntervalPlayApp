package merges;

import model.Interval;

import java.util.*;

public class StackMerger implements IntervalMergeStrategy {
	@Override
	public List<Interval> mergeIntervals(List<Interval> intervals) {
		Comparator<Interval> comparator = Comparator.naturalOrder();
		intervals.sort(comparator);
		Stack<Interval> stack = new Stack<>();
		stack.push(intervals.get(0));

		for (Interval interval : intervals) {
			if (interval.overlaps(stack.peek())) {
				Interval combined = interval.combined(stack.pop());
				stack.push(combined);
			} else {
				stack.push(interval);
			}
		}

		return stack;
	}
}
