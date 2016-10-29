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
		ls.ordinaPizza(Pizza);	
	
	}

}
