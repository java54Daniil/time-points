package telran.time;

public class PlusAdjuster implements TimePointAdjuster {
	TimePoint timePoint;
	
	public PlusAdjuster(TimePoint timePoint) {
		super();
		this.timePoint = timePoint;
	}

	@Override
	public TimePoint adjust(TimePoint point) {
		TimePoint pointSec = point.convert(TimeUnit.SECOND);
		TimePoint timepointSec = timePoint.convert(TimeUnit.SECOND);
		int sumSeconds =pointSec.getAmount() + timepointSec.getAmount();
		TimePoint resSec = new TimePoint(sumSeconds, TimeUnit.SECOND);
		return resSec.convert(point.timeUnint);
	}

}
