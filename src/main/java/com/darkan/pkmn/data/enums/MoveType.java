package com.darkan.pkmn.data.enums;

public enum MoveType {
	Any(-1),
	Normal(0),
	Fighting(1),
	Flying(2),
	Poison(3),
	Ground(4),
	Rock(5),
	Bug(6),
	Ghost(7),
	Steel(8),
	Fire(9),
	Water(10),
	Grass(11),
	Electric(12),
	Psychic(13),
	Ice(14),
	Dragon(15),
	Dark(16),
	Fairy(17);
	
	private int id;
	
	private MoveType(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
