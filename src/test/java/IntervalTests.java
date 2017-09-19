import model.Interval;
import org.junit.Assert;
import org.junit.Test;

import java.nio.channels.IllegalChannelGroupException;
import java.util.ArrayList;
import java.util.List;

public class IntervalTests {

	private Interval first;
	private Interval second;

	@Test
	public void regularOverlap() {

		// GIVEN
		first = Interval.valueOf(1,4);
		second = Interval.valueOf(3, 6);

		// WHEN
		boolean actual = first.overlaps(second);

		// THEN
		boolean expected = true;
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void borderOverlap() {

		// GIVEN
		first = Interval.valueOf(1,3);
		second = Interval.valueOf(3, 6);

		// WHEN
		boolean actual = first.overlaps(second);

		// THEN
		boolean expected = true;
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void inclusion() {

		// GIVEN
		first = Interval.valueOf(2,4);
		second = Interval.valueOf(3, 6);

		// WHEN
		boolean actual = first.overlaps(second);

		// THEN
		boolean expected = true;
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void notOverlapping() {

		// GIVEN
		first = Interval.valueOf(1,3);
		second = Interval.valueOf(4, 6);

		// WHEN
		boolean actual = first.overlaps(second);

		// THEN
		boolean expected = false;
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void combining() {

		// GIVEN
		first = Interval.valueOf(1,3);
		second = Interval.valueOf(2, 6);

		// WHEN
		Interval actual = first.combined(second);

		// THEN
		Interval expected = Interval.valueOf(1, 6);
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void cloning() {

		// GIVEN
		first = Interval.valueOf(1,3);

		// WHEN
		Interval actual = Interval.valueOf(first);

		// THEN
		Interval expected = Interval.valueOf(1, 3);
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void reversing() {

		// GIVEN
		first = Interval.valueOf(3,1);

		// WHEN
		Interval actual = Interval.valueOf(first);

		// THEN
		Interval expected = Interval.valueOf(1, 3);
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void comparing() {

		// GIVEN
		first = Interval.valueOf(1,3);
		second = Interval.valueOf(4, 6);

		List<Interval> actualList = new ArrayList<>();

		actualList.add(second);
		actualList.add(first);

		// WHEN
		actualList.sort((o1, o2) -> o1.compareTo(o2));

		// THEN
		List<Interval> expectedList = new ArrayList<>();

		expectedList.add(Interval.valueOf(1, 3));
		expectedList.add(Interval.valueOf(4, 6));

		Assert.assertEquals(expectedList, actualList);

	}

	@Test
	public void stringing() {

		// GIVEN
		first = Interval.valueOf(1,3);

		// WHEN
		String actual = first.toString();

		// THEN
		String expected = "<1, 3>";
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void boundariesReached() {

		// GIVEN
		first = Interval.valueOf(0,1000000000);
		second = Interval.valueOf(-1000000000,-1);

		// WHEN
		Interval actual = first.combined(second);

		// THEN
		Interval expected = Interval.valueOf(-1000000000, 1000000000);
		Assert.assertEquals(expected, actual);

	}

	@Test(expected = IllegalArgumentException.class)
	public void boundariesCrossed() {

		// GIVEN, WHEN
		first = Interval.valueOf(0,1000000001);

	}
}
