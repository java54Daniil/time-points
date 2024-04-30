package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.time.FutureProximityAdjuster;
import telran.time.PlusAdjuster;
import telran.time.TimePoint;
import telran.time.TimeUnit;

class TimePointTest {

	@Test
	void testBetween() {
		TimePoint point1 = new TimePoint(10, TimeUnit.HOURS);
		TimePoint point2 = new TimePoint(3600 * 20, TimeUnit.SECOND);
		TimePoint point3 = TimeUnit.MINUTE.between(point1, point2);
		assertEquals(600, point3.getAmount());
		TimePoint point4 = TimeUnit.SECOND.between(point1, point2);
		assertEquals(36000, point4.getAmount());
		TimePoint point5 = TimeUnit.HOURS.between(point1, point2);
		assertEquals(10, point5.getAmount());
	}

	@Test
	void convertTest() {
		TimePoint timePoint = new TimePoint(10, TimeUnit.HOURS);
		TimePoint point1Actual = timePoint.convert(TimeUnit.SECOND);
		assertEquals(36000, point1Actual.getAmount());
	}

	@Test
	void plusAdjusterTest() {
		TimePoint timePoint1 = new TimePoint(10, TimeUnit.HOURS);
		TimePoint timePoint2 = new TimePoint(60, TimeUnit.MINUTE);
		TimePoint actual = timePoint2.with(new PlusAdjuster(timePoint1));
		assertEquals(660, actual.getAmount());
		assertEquals(TimeUnit.MINUTE, actual.getTimeUnint());
	}

	@Test
	void TimePointEqualsTest() {
		TimePoint point1 = new TimePoint(10, TimeUnit.HOURS);
		TimePoint point2 = new TimePoint(10, TimeUnit.HOURS);
		TimePoint point3 = new TimePoint(600, TimeUnit.MINUTE);
		assertEquals(point1, point2);
		assertNotEquals(point1, point3);
	}

	@Test
	void TimePointCompareToTest() {
		TimePoint point1 = new TimePoint(10, TimeUnit.HOURS);
		TimePoint point2 = new TimePoint(600, TimeUnit.MINUTE);
		TimePoint point3 = new TimePoint(10, TimeUnit.MINUTE);
		assertEquals(0, point1.compareTo(point2));
		int result = point1.compareTo(point3);
		assertTrue(result != 0);

	}

	@Test
	void futureProximityAdjusterTest‎() {
	
		TimePoint[] points = { new TimePoint(10, TimeUnit.SECOND), new TimePoint(20, TimeUnit.SECOND),
				new TimePoint(30, TimeUnit.SECOND), new TimePoint(40, TimeUnit.SECOND),
				new TimePoint(1, TimeUnit.MINUTE), new TimePoint(2, TimeUnit.MINUTE), new TimePoint(3, TimeUnit.MINUTE),
				new TimePoint(4, TimeUnit.MINUTE), new TimePoint(1, TimeUnit.HOURS), new TimePoint(2, TimeUnit.HOURS),
				new TimePoint(3, TimeUnit.HOURS), new TimePoint(4, TimeUnit.HOURS) };

		TimePoint point1 = new TimePoint(5, TimeUnit.SECOND);
		TimePoint point2 = new TimePoint(15, TimeUnit.SECOND);
		TimePoint point3 = new TimePoint(25, TimeUnit.SECOND);
		TimePoint point4 = new TimePoint(35, TimeUnit.SECOND);

		FutureProximityAdjuster adjuster = new FutureProximityAdjuster(points);

		TimePoint result1 = adjuster.adjust(point1);
		TimePoint result2 = adjuster.adjust(point2);
		TimePoint result3 = adjuster.adjust(point3);
		TimePoint result4 = adjuster.adjust(point4);
		

		assertEquals(points[0], result1);
		assertEquals(points[1], result2);
		assertEquals(points[2], result3);
		assertEquals(points[3], result4);
		
	}

}
