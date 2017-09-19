import merges.IntervalMergeStrategy;
import model.Interval;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeTest {

	private IntervalMergeStrategy intervalMerger;

	public MergeTest(IntervalMergeStrategy intervalMerger) {
		this.intervalMerger = intervalMerger;
	}

	// <GIVEN>

	private static final List<Interval> REGULAR_INPUT;
	private static final List<Interval> SINGLE_ITEM;
	private static final List<Interval> ONE_ELEMENT_INTERVALS;
	private static final List<Interval> DUPLICATE_INTERVALS;
	private static final List<Interval> ONE_INTERVAL_OVER_ALL;
	private static final List<Interval> DUMB_USER_INPUT; // contains regular input, one element intervals and duplicates mixed together

	static {
		REGULAR_INPUT = new ArrayList<>();

		REGULAR_INPUT.add(Interval.valueOf(1, 3));
		REGULAR_INPUT.add(Interval.valueOf(4, 6));
		REGULAR_INPUT.add(Interval.valueOf(5, 8));
		REGULAR_INPUT.add(Interval.valueOf(9, 11));
		REGULAR_INPUT.add(Interval.valueOf(11, 20));
		REGULAR_INPUT.add(Interval.valueOf(21, 36));

		SINGLE_ITEM = new ArrayList<>();

		SINGLE_ITEM.add(Interval.valueOf(4, 20));

		ONE_ELEMENT_INTERVALS = new ArrayList<>();

		ONE_ELEMENT_INTERVALS.add(Interval.valueOf(1, 1));
		ONE_ELEMENT_INTERVALS.add(Interval.valueOf(2, 2));
		ONE_ELEMENT_INTERVALS.add(Interval.valueOf(3, 3));

		DUPLICATE_INTERVALS = new ArrayList<>();

		DUPLICATE_INTERVALS.add(Interval.valueOf(1, 3));
		DUPLICATE_INTERVALS.add(Interval.valueOf(1, 3));
		DUPLICATE_INTERVALS.add(Interval.valueOf(1, 3));
		DUPLICATE_INTERVALS.add(Interval.valueOf(3, 1));
		DUPLICATE_INTERVALS.add(Interval.valueOf(4, 8));
		DUPLICATE_INTERVALS.add(Interval.valueOf(4, 8));
		DUPLICATE_INTERVALS.add(Interval.valueOf(4, 8));

		ONE_INTERVAL_OVER_ALL = new ArrayList<>(REGULAR_INPUT);

		ONE_INTERVAL_OVER_ALL.add(Interval.valueOf(-50, 50));

		DUMB_USER_INPUT = new ArrayList<>();

		DUMB_USER_INPUT.add(Interval.valueOf(1, 3));
		DUMB_USER_INPUT.add(Interval.valueOf(1, 3));
		DUMB_USER_INPUT.add(Interval.valueOf(1, 3));
		DUMB_USER_INPUT.add(Interval.valueOf(4, 6));
		DUMB_USER_INPUT.add(Interval.valueOf(5, 8));
		DUMB_USER_INPUT.add(Interval.valueOf(9, 11));
		DUMB_USER_INPUT.add(Interval.valueOf(11, 11));
		DUMB_USER_INPUT.add(Interval.valueOf(11, 20));
		DUMB_USER_INPUT.add(Interval.valueOf(11, 20));
		DUMB_USER_INPUT.add(Interval.valueOf(21, 36));
	}

	// </GIVEN>

	@Test
	public void regularInput() {

		// WHEN

		List<Interval> actual = intervalMerger.mergeIntervals(REGULAR_INPUT);
		actual.sort(Comparator.naturalOrder());

		// THEN
		List<Interval> expected = new ArrayList<>();
		expected.add(Interval.valueOf(1, 3));
		expected.add(Interval.valueOf(4, 8));
		expected.add(Interval.valueOf(9, 20));
		expected.add(Interval.valueOf(21, 36));

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void singleItem() {

		// WHEN
		List<Interval> actual = intervalMerger.mergeIntervals(SINGLE_ITEM);

		// THEN
		List<Interval> expected = new ArrayList<>();
		expected.add(Interval.valueOf(4, 20));

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void oneElementIntervals() {

		// WHEN
		List<Interval> actual = intervalMerger.mergeIntervals(ONE_ELEMENT_INTERVALS);
		actual.sort(Comparator.naturalOrder());

		// THEN
		List<Interval> expected = new ArrayList<>();
		expected.add(Interval.valueOf(1, 1));
		expected.add(Interval.valueOf(2, 2));
		expected.add(Interval.valueOf(3, 3));

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void duplicates() {

		// WHEN
		List<Interval> actual = intervalMerger.mergeIntervals(DUPLICATE_INTERVALS);
		actual.sort(Comparator.naturalOrder());

		// THEN
		List<Interval> expected = new ArrayList<>();
		expected.add(Interval.valueOf(1, 3));
		expected.add(Interval.valueOf(4, 8));

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void oneToRuleThemAll() {

		// WHEN
		List<Interval> actual = intervalMerger.mergeIntervals(ONE_INTERVAL_OVER_ALL);

		// THEN
		List<Interval> expected = new ArrayList<>();
		expected.add(Interval.valueOf(-50, 50));

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void mixedInput() {

		// WHEN
		List<Interval> actual = intervalMerger.mergeIntervals(DUMB_USER_INPUT);
		actual.sort(Comparator.naturalOrder());

		// THEN
		List<Interval> expected = new ArrayList<>();
		expected.add(Interval.valueOf(1, 3));
		expected.add(Interval.valueOf(4, 8));
		expected.add(Interval.valueOf(9, 20));
		expected.add(Interval.valueOf(21, 36));

		Assert.assertEquals(expected, actual);

	}

	void test() {
		regularInput();
		singleItem();
		oneElementIntervals();
		duplicates();
		oneToRuleThemAll();
		mixedInput();
	}
}
