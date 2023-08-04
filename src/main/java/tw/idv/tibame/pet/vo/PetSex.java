package tw.idv.tibame.pet.vo;

public enum PetSex {
	MALE("0"), FEMALE("1");

	private final String value;

	PetSex(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
