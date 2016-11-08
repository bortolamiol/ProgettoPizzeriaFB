package progettoPizzeriaFB;

import java.awt.List;
import java.util.ArrayList;

public class Lista {
	ArrayList<String> ordinazioniPizze;
	ArrayList<String> pizzePronte;
	public List pizzeCottura;
	public List pizzeProntee;

	// costruttore
	public Lista() {
		ordinazioniPizze = new ArrayList<String>();
		pizzePronte = new ArrayList<String>();
	}

	// metodi
	public void ordinaPizza(String NomePizza) {
		ordinazioniPizze.add(NomePizza);
		notifyAll();
	}

	public synchronized String pizzaInLista() {
		while (ordinazioniPizze.isEmpty()) { // true se è vuoto
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("HO FINITO DI ASPETTARE");

		return ordinazioniPizze.get(0);
	}
	
	public void pizzaPronta(String NomePizza){
		pizzeCottura.add(NomePizza);
		pizzePronte.add(NomePizza);
		
		notifyAll();
		
	}
	
	public synchronized void prelevaPizza(String NomePizza){
		while (!pizzePronte.contains(NomePizza)){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pizzePronte.remove(NomePizza);
		
		
	}

}
