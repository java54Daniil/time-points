package telran.time;

import java.util.Comparator;

import telran.util.Arrays;

public class FutureProximityAdjuster implements TimePointAdjuster {
	TimePoint[] timePoints;
	public FutureProximityAdjuster(TimePoint[] points) {
		this.timePoints =Arrays.copy(points);
		Arrays.bubbleSort(timePoints);
	}
	@Override
	public TimePoint adjust(TimePoint point) {

		int index = Arrays.binarySearch(timePoints, point, Comparator.naturalOrder());
		index = index < 0 ? -(index + 1) : getNextIndex(point, index);
		return index == timePoints.length ? null : timePoints[index].convert(point.getTimeUnit());
	}
	private int getNextIndex(TimePoint point, int index) {
		index++;
		while(index < timePoints.length && timePoints[index].equals(point)) {
			index++;
		}
		return index;
	}

}
