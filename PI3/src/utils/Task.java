package utils;

public record Task(String name) {

	public static Task ofFormat(String[] format) {
		return new Task(format[0]);
	}
	
	public static Task valueOf(String name) {
		return new Task(name);
	}
}