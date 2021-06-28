package it.polito.tdp.food.model;

public class Collegamento {
         Food food1;
         Food food2;
         double peso;
		public Collegamento(Food food1, Food food2, double peso) {
			this.food1 = food1;
			this.food2 = food2;
			this.peso = peso;
		}
		public Food getFood1() {
			return food1;
		}
		public void setFood1(Food food1) {
			this.food1 = food1;
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
			return "Collegamento [food1=" + food1 + ", food2=" + food2 + ", peso=" + peso + "]";
		}
         
}
