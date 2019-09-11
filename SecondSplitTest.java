import static org.junit.Assert.*;

import org.junit.Test;

public class SecondSplitTest {

	@Test
	public void twoSplitLeft() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(9);
		t.insert(15);
		t.insert(1);

		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "15";
		assertEquals(expected, t.search(15));
		assertEquals(expected, t.search(17));
		assertEquals(expected, t.search(11));

		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
		
		t.insert(20);
		expected = "15 20";
		assertEquals(expected, t.search(20));
		assertEquals(expected, t.search(19));
		assertEquals(expected, t.search(21));
		t.insert(14);
		expected = "20";
		assertEquals(expected, t.search(20));
		expected = "9 15";
		assertEquals(expected, t.search(9));
		assertEquals(expected, t.search(15));
		expected = "14";
		assertEquals(expected, t.search(14));
		assertEquals(expected, t.search(13));
		
	}

	@Test
	public void decreasing() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(15);
		t.insert(14);
		t.insert(13);
		t.insert(12);
		t.insert(11);
		t.insert(10);
		t.insert(9);
		t.insert(8);
		t.insert(7);
		t.insert(6);
		t.insert(5);
		t.insert(4);
		t.insert(3);
		t.insert(2);
		t.insert(1);
		t.search(15);
		t.search(14);
		t.search(13);
		t.search(12);
		t.search(11);
		t.search(10);
		t.search(9);
		t.search(8);
		t.search(7);
		t.search(6);
		t.search(5);
		t.search(4);
		t.search(3);
		t.search(2);
		t.search(1);
	}

}
