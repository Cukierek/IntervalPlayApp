package ui;

import model.Interval;

import java.util.List;

public class IntervalPresenter {
	public static void presentIntervals(List<Interval> intervals) {
		intervals.forEach(interval -> System.out.println(interval));
	}
}
