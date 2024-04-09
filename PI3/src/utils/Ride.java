package utils;

import us.lsi.common.Preconditions;

public record Ride(String name, Integer waitTime, Double popularity, Integer duration) {
	public static Ride ofFormat(String[] format) {
		Double popularity = Double.valueOf(format[2]);
		Preconditions.checkArgument(popularity >= 0. && popularity <= 10.);
		return new Ride(format[0], Integer.valueOf(format[1]), popularity, Integer.valueOf(format[3]));
	}
	
	public static Ride valueOf(String name, Integer waitTime, Double popularity, Integer duration) {
		return new Ride(name, waitTime, popularity, duration);
	}
}
