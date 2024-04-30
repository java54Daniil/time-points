package telran.time;

public class FutureProximityAdjuster implements TimePointAdjuster {
	TimePoint[] timePoints;
	public FutureProximityAdjuster(TimePoint[] points) {
		this.timePoints =points;
	}
	@Override
	public TimePoint adjust(TimePoint point) {

		TimePoint closestPoint = null;
	    long minDiff = Long.MAX_VALUE;
	    for (TimePoint timePoint : timePoints) {
	    	
	        if (timePoint.getAmount() >= point.getAmount()) {
	            long diff = timePoint.getAmount() - point.getAmount();
	            if (diff < minDiff) {
	                minDiff = diff;
	                closestPoint = timePoint;
	            }
	        }
	    }

	    return closestPoint;
	}

}
