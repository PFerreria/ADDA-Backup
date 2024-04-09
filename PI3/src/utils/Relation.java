package utils;

import us.lsi.common.Preconditions;

public record Relation(Integer id, Double interactIndex) {

	private static int num = 0;

	public static Relation ofFormat(String[] format) {
		Integer id = num;
		Double interactIndex = Double.valueOf(format[2]);
		Preconditions.checkArgument(interactIndex <= 5. && interactIndex >= 0.);
		num++;
		return new Relation(id, interactIndex);
	}

	@Override
	public String toString() {
		return "(" + interactIndex + ")";
	}
}
