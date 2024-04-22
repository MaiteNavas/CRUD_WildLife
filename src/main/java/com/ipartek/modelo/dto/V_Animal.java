package com.ipartek.modelo.dto;

public class V_Animal extends Animal {
	
	private String especie;

	public V_Animal(int id, String nombre, Double peso, int edad, int fk_id_especie, String especie) {
		super(id, nombre, peso, edad, fk_id_especie);
		this.especie = especie;
	}
	public V_Animal() {
		super();
		this.especie = "";
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	@Override
	public String toString() {
		return "V_Animal [especie=" + especie + ", toString()=" + super.toString() + "]";
	}
	
}
