package progettoPizzeriaFB;

import java.awt.List;
import java.util.ArrayList;

public class Lista {
	ArrayList<String> ordinazioniPizze;
	ArrayList<String> pizzePronte;
	ArrayList<String> pizzeCottura;
	ArrayList<String> pizzeConsegnate;
	public List pizzeProntee;
	private PizzeriaHome ph;

	// costruttore
	public Lista(PizzeriaHome ph) {
		pizzeCottura = new ArrayList<String>();
		ordinazioniPizze = new ArrayList<String>();
		pizzePronte = new ArrayList<String>();
		pizzeConsegnate = new ArrayList<String>();
		this.ph = ph;
	}

	// metodi
	public synchronized void ordinaPizza(String NomePizza) {
		ordinazioniPizze.add(NomePizza);
		System.out.println("ho aggiunto una pizza al vettore ordinazione pizze");
		notifyAll();
	}

	public synchronized String pizzaInLista() {
		while (ordinazioniPizze.isEmpty()) { // true se è vuoto
			try {
				System.out.println("sto aspettando");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("HO FINITO DI ASPETTARE");
		String pizza = ordinazioniPizze.remove(0);
		ph.addPizzaInCottura(pizza);
		return pizza;
	}

	public synchronized void pizzaPronta(String NomePizza) {
		pizzeCottura.add(NomePizza);
		pizzePronte.add(NomePizza);
		notifyAll();
		System.out.println("La pizza : " + NomePizza + " è pronta");
		ph.addPizzaPronta(NomePizza);
	}

	public synchronized void prelevaPizza(String NomePizza) {
		while (!pizzePronte.contains(NomePizza)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pizzeConsegnate.add(NomePizza);
		System.out.println("pizzeConsegnate " + NomePizza);
		pizzeCottura.remove(NomePizza);
		pizzePronte.remove(NomePizza);
		ph.addPizzaConsegnata(NomePizza);
		System.out.println("Ho prelevato la pizza " + NomePizza);
	}

	public ArrayList<String> getOrdinazioniPizze() {
		return ordinazioniPizze;
	}

	public void setOrdinazioniPizze(ArrayList<String> ordinazioniPizze) {
		this.ordinazioniPizze = ordinazioniPizze;
	}

	public ArrayList<String> getPizzePronte() {
		return pizzePronte;
	}

	public void setPizzePronte(ArrayList<String> pizzePronte) {
		this.pizzePronte = pizzePronte;
	}

}
