package progettoPizzeriaFB;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PizzeriaHome {
	
	
	protected Shell shell;
	private Text txtPizza;
	public Lista lista;
	private String Pizza;
	public int pizzeCoda = 0;
	Lista ls = new Lista();
	public int chiave = 0;
	


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
				Pizzaiolo pizzaiolo1 = new Pizzaiolo(lista);
				Pizzaiolo pizzaiolo2 = new Pizzaiolo(lista);
				Thread t1 = new Thread(pizzaiolo1);
				t1.setName("Fedato");
				t1.start();
				Thread t2 = new Thread(pizzaiolo2);
				t2.setName("Bortolamiol");
				t2.start();
				chiave = 1;
				System.out.println("Ho creato i due pizzaioli!");
			}
		});
		btnApri.setBounds(10, 61, 75, 25);
		btnApri.setText("Apri Pizzeria");
		
		Button btnChiudi = new Button(shell, SWT.NONE);
		btnChiudi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				chiave = 0;
			}
		});
		btnChiudi.setBounds(145, 61, 89, 25);
		btnChiudi.setText("Chiudi Pizzeria");
		
		Button btnCliente = new Button(shell, SWT.NONE);
		btnCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (chiave == 1){
					Pizza = txtPizza.getText();
					if (Pizza.isEmpty()){
						JOptionPane.showMessageDialog(null, "Inserisci il nome della pizza", "ERRORE", JOptionPane.ERROR_MESSAGE);//crea il messaggio d'errore
					}
					else {
						Cliente c = new Cliente(Pizza, lista);
						Thread ThreadCliente = new Thread(c);
						ThreadCliente.start();
						
						listCoda.add(Pizza);
						pizzeCoda++;	
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Devi prima cliccare il bottone che apre la pizzeria", "ERRORE", JOptionPane.ERROR_MESSAGE);//crea il messaggio d'errore
				}
				
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
