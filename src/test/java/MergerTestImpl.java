import merges.SortedMerger;
import merges.StackMerger;
import merges.UnsortedMerger;
import org.junit.Test;

public class MergerTestImpl {

	MergeTest mergeTest;

	@Test
	public void unsortedMergerTest() {
		mergeTest = new MergeTest(new UnsortedMerger());
		mergeTest.test();
	}

	@Test
	public void sortedMergerTest() {
		mergeTest = new MergeTest(new SortedMerger());
		mergeTest.test();
	}

	@Test
	public void stackMergerTest() {
		mergeTest = new MergeTest(new StackMerger());
		mergeTest.test();
	}

}
