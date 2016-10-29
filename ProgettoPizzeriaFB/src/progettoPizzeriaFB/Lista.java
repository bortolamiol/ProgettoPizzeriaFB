package progettoPizzeriaFB;

import java.util.ArrayList;

public class Lista {
	ArrayList<String> OrdinazioniPizze;
	//costruttore
	public Lista(){
		OrdinazioniPizze = new ArrayList<String>();
	}
	
	//metodi
	public void ordinaPizza(String NomePizza){
		OrdinazioniPizze.add(NomePizza);
		notifyAll();
	}
	
	public synchronized String pizzaInLista(){
		while (OrdinazioniPizze.isEmpty()){ //true se è vuoto
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("HO FINIT ODI ASPETTARE");
		
		return OrdinazioniPizze.get(0);
	}
	
}
