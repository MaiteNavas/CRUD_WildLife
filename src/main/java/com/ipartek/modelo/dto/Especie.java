package com.ipartek.modelo.dto;

public class Especie {
	
	private int id;
	private String especie;
	
	public Especie(int id, String especie) {
		super();
		this.id = id;
		this.especie = especie;
	}
	public Especie() {
		super();
		this.id = 0;
		this.especie = "";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	@Override
	public String toString() {
		return  id + ";" + especie + ";" ;
	}
	
	
	
	
}
