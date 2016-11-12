package progettoPizzeriaFB;
public class Pizzaiolo implements Runnable{

	private Lista lista;
	private String pizza;
	

	public Pizzaiolo(Lista lista) {
		this.lista = lista;

	}
	
	public void run() {
		// gira all'infinito
		while(true){
			// controlla se c'è una pizza
			pizza = lista.pizzaInLista();//ritorna il nome della pizza
			
			// fa la pizza (aspetta)
			System.out.println(Thread.currentThread().getName()+" sta facendo pizza: " + pizza);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// aggiunge una pizza alla lista dele pizze pronte
			lista.pizzaPronta(pizza);
		}
	}
}
