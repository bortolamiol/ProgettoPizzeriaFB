package progettoPizzeriaFB;
public class Pizzaiolo implements Runnable{

	private Lista lista;


	public Pizzaiolo(Lista lista) {
		this.lista = lista;

	}
	
	public void run() {
		// TODO Auto-generated method stub
		String pizza = lista.pizzaInLista();//ritorna il nome della pizza
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
