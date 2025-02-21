package com.ti2cc;

public class Carro {
	private int codigo;
	private String modelo;
	private String marca;
	private int ano;
	
	public Carro() {
		this.codigo = -1;
		this.modelo = "";
		this.marca = "";
		this.ano = 0;
	}
	
	public Carro (int codigo, String modelo, String marca, int ano) {
		this.codigo = codigo;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
	}
	
	public int getCodigo () {
		return codigo;
	}
	
	public void setCodigo (int codigo) {
		this.codigo = codigo;
	}
	
	public String getModelo () {
		return modelo;
	}
	
	public void setModelo (String modelo) {
		this.modelo = modelo;
	}
	
	public String getMarca () {
		return marca;
	}
	
	public void setMarca (String marca) {
		this.marca = marca;
	}
	
	public int getAno () {
		return ano;
	}
	
	public void setAno (int ano) {
		this.ano = ano;
	}
	
	@Override
	public String toString () {
		return "Carro [codigo=" + codigo + ", modelo=" + modelo + ", marca=" + marca + ", ano=" + ano + "]";
	}
}
