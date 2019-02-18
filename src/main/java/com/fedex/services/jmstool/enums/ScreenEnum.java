package com.fedex.services.jmstool.enums;

public enum ScreenEnum {
	CONSUMER(0, "C"),
	PUBLISHER(1, "P"),
	SUB_TESTING(2, "T");

	private int screenNumber;
	private String screenKey;

	ScreenEnum(int screenNumber, String screenKey) {
		this.screenNumber = screenNumber;
		this.screenKey = screenKey;
	}

	public static int getScreenNumber(String screenKey) {
		for (ScreenEnum screenEnum : ScreenEnum.values()) {
			if (screenEnum.screenKey.equals(screenKey)) {
				return screenEnum.screenNumber;
			}
		}
		// If an invalid key is provided, default to the first screen (CONSUMER).
		return 0;
	}

	public static String getScreenKey(int screenNumber) {
		for (ScreenEnum screenEnum : ScreenEnum.values()) {
			if (screenEnum.screenNumber == screenNumber) {
				return screenEnum.screenKey;
			}
		}
		// If an invalid integer is provided, default to the first screen (CONSUMER).
		return "C";
	}
}
