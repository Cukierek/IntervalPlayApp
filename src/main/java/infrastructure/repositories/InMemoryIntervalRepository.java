package infrastructure.repositories;

import model.Interval;
import model.repositories.IntervalRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryIntervalRepository implements IntervalRepository {

	private static final List<Interval> REPOSITORY;

	static {

		REPOSITORY = new ArrayList<>();

		REPOSITORY.add(Interval.valueOf(1, 3));
		REPOSITORY.add(Interval.valueOf(4, 6));
		REPOSITORY.add(Interval.valueOf(5, 8));
		REPOSITORY.add(Interval.valueOf(9, 11));
		REPOSITORY.add(Interval.valueOf(11, 20));
		REPOSITORY.add(Interval.valueOf(21, 36));

	}

	@Override
	public List<Interval> getInputIntervals() {
		return REPOSITORY;
	}
}
