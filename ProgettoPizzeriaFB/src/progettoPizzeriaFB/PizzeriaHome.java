package progettoPizzeriaFB;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PizzeriaHome {

	protected Shell shell;
	private Text txtPizza;
	public Lista lista;
	private String Pizza;
	public List listCoda;
	public List listCottura;
	public int pizzeCoda = 0;
	
	Pizzaiolo pizzaiolo = new Pizzaiolo(lista);
	
	Cliente c = new Cliente(Pizza, lista);
	Thread ThreadCliente = new Thread(c);

	Lista ls = new Lista();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PizzeriaHome window = new PizzeriaHome();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		lista= new Lista();//creato la lista
		
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		List listCoda = new List(shell, SWT.BORDER);
		listCoda.setBounds(10, 93, 116, 159);
		
		List listCottura = new List(shell, SWT.BORDER);
		listCottura.setBounds(145, 92, 116, 160);
		
		List listPronte = new List(shell, SWT.BORDER);
		listPronte.setBounds(308, 93, 116, 159);
		
		Button btnApri = new Button(shell, SWT.NONE);
		btnApri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Thread pizzaiolo1 = new Thread();
				pizzaiolo1.start();
				Thread pizzaiolo2 = new Thread();
				pizzaiolo2.start();
				
				//ls.pizzaInLista();
				
				System.out.println("Ho creato i due pizzaioli!");
			}
		});
		btnApri.setBounds(10, 61, 75, 25);
		btnApri.setText("Apri Pizzeria");
		
		Button btnChiudi = new Button(shell, SWT.NONE);
		btnChiudi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnChiudi.setBounds(145, 61, 89, 25);
		btnChiudi.setText("Chiudi Pizzeria");
		
		Button btnCliente = new Button(shell, SWT.NONE);
		btnCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Pizza = txtPizza.getText();
				
			
				ThreadCliente.start();
				
				listCoda.add(Pizza);
				pizzeCoda++;
			}
		});
		btnCliente.setBounds(308, 61, 100, 25);
		btnCliente.setText("Arriva un cliente");
		
		
		
		Label lblInserisci = new Label(shell, SWT.NONE);
		lblInserisci.setBounds(10, 22, 154, 15);
		lblInserisci.setText("Inserisci qui la tua pizza : ");
		
		txtPizza = new Text(shell, SWT.BORDER);
		txtPizza.setBounds(185, 22, 170, 21);

	}
}
