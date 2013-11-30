package com.ggl.fontchooser.model;

public class ComparisonResult {

	private boolean exactMatch;

	private int listIndex;

	public ComparisonResult(int listIndex, boolean exactMatch) {
		this.listIndex = listIndex;
		this.exactMatch = exactMatch;
	}

	public boolean isExactMatch() {
		return exactMatch;
	}

	public int getListIndex() {
		return listIndex;
	}

}
