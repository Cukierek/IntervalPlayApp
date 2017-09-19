package infrastructure.repositories;

import model.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IntervalInputCollector {
	public static List<Interval> collectInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many intervals?");
		int amount = scanner.nextInt();
		List<Interval> inputList = new ArrayList<>(amount);
		for (int i = 0; i < amount; i++) {
			Interval interval = Interval.valueOf(scanner.nextInt(), scanner.nextInt());
			inputList.add(interval);
		}
		return inputList;
	}
}
