package utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import us.lsi.common.Preconditions;

public record User(String name, Double actIndex, Set<String> hobbies) {

	public static User ofFormat(String[] format) {
		Double actIndex = Double.valueOf(format[1]);
		Preconditions.checkArgument(actIndex >= 0 && actIndex <= 5);
		Set<String> hobbies = new HashSet<String>(Arrays.asList(format[2].split(";")));
		return new User(format[0], actIndex, hobbies);
	}

	@Override
	public String toString() {
		return this.name;
	}
}