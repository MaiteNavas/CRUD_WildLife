package com.ipartek.modelo.dto;

public class Animal {
	
	private int id;
	private String nombre;
	private Double peso;
	private int edad;
	private int fk_id_especie;
	
	public Animal(int id, String nombre, Double peso, int edad, int fk_id_especie) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.peso = peso;
		this.edad = edad;
		this.fk_id_especie = fk_id_especie;
	}
	
	public Animal() {
		super();
		this.id = 0;
		this.nombre = "";
		this.peso = 0.0;
		this.edad = 0;
		this.fk_id_especie = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getFk_id_especie() {
		return fk_id_especie;
	}

	public void setFk_id_especie(int fk_id_especie) {
		this.fk_id_especie = fk_id_especie;
	}

	@Override
	public String toString() {
		return  id + ";" + nombre + ";" + peso + ";" + edad + ";"
				+ fk_id_especie + ";";
	}
	
}
