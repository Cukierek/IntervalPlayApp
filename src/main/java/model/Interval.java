package model;

public final class Interval implements Comparable {

	private static final Integer UPPER_BOUNDARY = 1000000000;
	private static final Integer LOWER_BOUNDARY = -1000000000;

	private final Integer lower;
	private final Integer higher;

	private Interval(Integer lower, Integer higher) {
		this.lower = lower;
		this.higher = higher;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Interval)) return false;

		Interval interval = (Interval) o;

		if (lower != null ? !lower.equals(interval.lower) : interval.lower != null) return false;
		return higher != null ? higher.equals(interval.higher) : interval.higher == null;
	}

	@Override
	public int hashCode() {
		int result = lower != null ? lower.hashCode() : 0;
		result = 31 * result + (higher != null ? higher.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return String.format("<%d, %d>", lower, higher);
	}

	@Override
	public int compareTo(Object o) {
		return lower.compareTo(((Interval)o).lower);
	}

	public static Interval valueOf(Integer lo, Integer hi) {
		if(inputOutOfBounds(lo, hi))throw new IllegalArgumentException("Interval attributes have to be between -1000000000 and 1000000000 inclusive.");
		if (lo > hi) return new Interval(hi, lo);
		else return new Interval(lo, hi);
	}

	private static boolean inputOutOfBounds(Integer lo, Integer hi) {
		return lo < LOWER_BOUNDARY || hi > UPPER_BOUNDARY;
	}

	public static Interval valueOf(Interval another) {
		return valueOf(another.lower, another.higher);
	}

	public Interval combined(Interval another) {
		Integer lo = Integer.min(lower, another.lower);
		Integer hi = Integer.max(higher, another.higher);
		return new Interval(lo, hi);
	}

	public boolean overlaps(Interval another) {
		return lower <= another.higher && another.lower <= higher;
	}
}
