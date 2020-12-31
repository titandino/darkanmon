package com.darkan.pkmn.data.enums;

public enum GCVersion {
	None(0),
	FR(1),
	LG(2),
	S(8),
	R(9),
	E(10),
	CXD(11);
	
	private int id;
	
	private GCVersion(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
