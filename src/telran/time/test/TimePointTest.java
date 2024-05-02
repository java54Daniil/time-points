package telran.time.test;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;

import telran.time.FutureProximityAdjuster;
import telran.time.PlusAdjuster;
import telran.time.TimePoint;
import telran.time.TimePointAdjuster;
import telran.time.TimeUnit;

class TimePointTest {

	

		@Test
		void testBetween() {
			TimePoint point1 = new TimePoint(10, TimeUnit.HOUR);
			TimePoint point2 = new TimePoint(3600 * 20, TimeUnit.SECOND);
			TimePoint point3 = TimeUnit.MINUTE.between(point1, point2);
			assertEquals(600, point3.getAmount());
			TimePoint point4 = TimeUnit.SECOND.between(point1, point2);
			assertEquals(36000, point4.getAmount());
			TimePoint point5 = TimeUnit.HOUR.between(point1, point2);
			assertEquals(10, point5.getAmount());
		}
		@Test
		void convertTest() {
			TimePoint timePoint = new TimePoint(10, TimeUnit.HOUR);
			TimePoint point1Actual = timePoint.convert(TimeUnit.SECOND);
			assertEquals(36000, point1Actual.getAmount());
		}
		@Test
		void plusAdjusterTest() {
			TimePoint timePoint1 = new TimePoint(10, TimeUnit.HOUR);
			TimePoint timePoint2 = new TimePoint(60, TimeUnit.MINUTE);
			TimePoint actual = timePoint2.with(new PlusAdjuster(timePoint1));
			assertEquals(660, actual.getAmount());
			assertEquals(TimeUnit.MINUTE, actual.getTimeUnit());
		}
		@Test
		void timePointEqualsTest() {
			TimePoint tp1 = new TimePoint(1, TimeUnit.HOUR);
			TimePoint tp2 = new TimePoint(60, TimeUnit.MINUTE);
			TimePoint tp3 = new TimePoint(1, TimeUnit.MINUTE);
			
			assertEquals(tp1, tp2);
			assertNotEquals(tp1, tp3);
		}
		@Test
		void timePointCompareToTest() {
			TimePoint tp1 = new TimePoint(10, TimeUnit.HOUR);
			TimePoint tp2 = new TimePoint(60, TimeUnit.MINUTE);
			TimePoint tp3 = new TimePoint(100, TimeUnit.SECOND);
			TimePoint tp4 = new TimePoint(1, TimeUnit.HOUR);
			assertTrue(tp1.compareTo(tp2) > 0);
			assertTrue(tp3.compareTo(tp1) < 0);
			assertTrue(tp2.compareTo(tp4) == 0);
			
			
		}
		@Test
	    void futureProximityAdjusterTest() {
	            TimePoint tp1 = new TimePoint(60, TimeUnit.MINUTE);
	            TimePoint tp2=  new TimePoint(90, TimeUnit.MINUTE);
	            TimePoint tp3 = new TimePoint(2, TimeUnit.HOUR);
	            TimePoint tp4 = new TimePoint(4, TimeUnit.HOUR);
	            TimePoint tp5 = new TimePoint(120, TimeUnit.MINUTE);
	            TimePoint [] timePoints = {
	                    tp1, tp2, tp3, tp4, tp5
	                    };
	             TimePoint timePoint =  new TimePoint(90, TimeUnit.MINUTE);
	             TimePointAdjuster adjuster = new FutureProximityAdjuster(timePoints);
	             TimePoint actual = timePoint.with(adjuster);
	              assertEquals(120, actual.getAmount());
	              assertEquals(TimeUnit.MINUTE, actual.getTimeUnit());
	              timePoint = new TimePoint(7200, TimeUnit.SECOND);
	              actual = timePoint.with(adjuster);
	              assertEquals(3600 * 4, actual.getAmount());
	              assertEquals(TimeUnit.SECOND, actual.getTimeUnit());
	              timePoint = new TimePoint(4, TimeUnit.HOUR);
	              actual = timePoint.with(adjuster);
	              assertNull(actual);
	     
	}
}
