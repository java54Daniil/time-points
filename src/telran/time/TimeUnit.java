package telran.time;

public enum TimeUnit {
	HOURS(3600), MINUTE(60), SECOND(1);
	int value;
	TimeUnit(int value){
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public TimePoint between(TimePoint point1,TimePoint point2) {
		long difference;
	    switch (this) {
	        case HOURS:
	            difference = point2.convert(TimeUnit.HOURS).getAmount() - point1.convert(TimeUnit.HOURS).getAmount();
	            break;
	        case MINUTE:
	            difference = point2.convert(TimeUnit.MINUTE).getAmount() - point1.convert(TimeUnit.MINUTE).getAmount();
	            break;
	        case SECOND:
	        default:
	            difference = point2.convert(TimeUnit.SECOND).getAmount() - point1.convert(TimeUnit.SECOND).getAmount();
	            break;
	    }
	    return new TimePoint((int) difference, this);
	}
}
