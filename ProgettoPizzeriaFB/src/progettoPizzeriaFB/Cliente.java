package progettoPizzeriaFB;

public class Cliente implements Runnable {

	String Pizza;
	Lista ls;

	public Cliente (String Pizza, Lista ls){
		this.ls = ls;
		this.Pizza = Pizza;
	}
	
	@Override
	public void run() {
		//ordina la pizza
		ls.ordinaPizza(Pizza);	
		//controlla se la pizza è pronta
		ls.prelevaPizza(Pizza);
	
	}

}
