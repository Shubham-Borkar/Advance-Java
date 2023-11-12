package com.sunbeaminfo.pojos;

public enum Course {
	DAC(80), DMC(78), DBDA(85), DITISS(77);

	private int minScore;

	private Course(int minSCore) {
		this.minScore = minSCore;
	}

	public int getMinScore() {
		return minScore;
	}
	
}
