package utils;

public record TaskRelation(Integer id) {

	private static int num = 0;

	public static TaskRelation ofFormat(String[] format) {
		Integer id = num;
		num++;
		return new TaskRelation(id);
	}

	@Override
	public String toString() {
		return "Relation-" + id;
	}
}