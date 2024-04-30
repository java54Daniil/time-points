package telran.time;

import java.util.Objects;

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

	public TimeUnit getTimeUnint() {
		return timeUnint;
	}

	public TimePoint convert(TimeUnit second) {
		// return new TimePoint with a given TimeUnit
		double conversionFactor = timeUnint.getValue() / (double) second.getValue();
		long newAmount = Math.round(amount * conversionFactor);
		return new TimePoint((int) newAmount, second);
	}

	public TimePoint with(TimePointAdjuster adjuster) {
		// returns new TimePoint based on any TimePointAdjuster
		return adjuster.adjust(this);
	}

	@Override
	public int compareTo(TimePoint o) {
		 TimePoint thisInSeconds = convert(TimeUnit.SECOND);
		    TimePoint otherInSeconds = o.convert(TimeUnit.SECOND);
		    return Integer.compare(thisInSeconds.getAmount(), otherInSeconds.getAmount());
	}

	@Override
	public boolean equals(Object obj) {

		 if (this == obj) return true;
		    if (obj == null || getClass() != obj.getClass()) return false;
		    TimePoint other = (TimePoint) obj;
		    return amount == other.amount && timeUnint == other.timeUnint;
	}
	@Override
    public int hashCode() {
        return Objects.hash(amount, timeUnint);
    }
  }
