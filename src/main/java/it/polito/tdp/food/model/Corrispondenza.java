package it.polito.tdp.food.model;

public class Corrispondenza {
     Food food2;
     double peso;
	public Corrispondenza(Food food2, double peso) {
		this.food2 = food2;
		this.peso = peso;
	}
	public Food getFood2() {
		return food2;
	}
	public void setFood2(Food food2) {
		this.food2 = food2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return food2 + " " + peso;
	}
     
}
