package utils;

public record TripToRide(Integer id, Double distance, Double avgTime) {

	private static int num = 0;

	public static TripToRide ofFormat(String[] format) {
		Integer id = num;
		num++;
		return new TripToRide(id, Double.valueOf(format[2]), Double.valueOf(format[3]));
	}

	@Override
	public String toString() {
		return "(distance: " + distance + ", time: " + avgTime + ")";
	}
}
