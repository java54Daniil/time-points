package telran.time;


public class TimePoint implements Comparable<TimePoint> {
	int amount;
	TimeUnit timeUnint;

	public TimePoint(int amount, TimeUnit timeUnint) {
		super();
		this.amount = amount;
		this.timeUnint = timeUnint;
	}

	public int getAmount() {
		return amount;
	}

	public TimeUnit getTimeUnit() {
		return timeUnint;
	}

	public TimePoint convert(TimeUnit unit) {
		// return new TimePoint with a given TimeUnit
		return new TimePoint(amount * timeUnint.getValue() / unit.getValue(), unit);
	}

	public TimePoint with(TimePointAdjuster adjuster) {
		// returns new TimePoint based on any TimePointAdjuster
		return adjuster.adjust(this);
	}

	@Override
	public int compareTo(TimePoint o) {
		return Integer.compare(amount, o.convert(timeUnint).amount);
	}

	@Override
	public boolean equals(Object obj) {

		boolean result = false;
		if(obj != null && obj instanceof TimePoint) {
			result = compareTo((TimePoint)obj) == 0;
		}
		return result;
	}
}