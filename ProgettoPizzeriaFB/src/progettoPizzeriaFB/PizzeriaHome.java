package progettoPizzeriaFB;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.awt.Window;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.viewers.ListViewer;

public class PizzeriaHome {
	
	
	protected Shell shlPizzeriaFedatobortolamiol;
	private Text txtPizza;
	public Lista lista;
	private String Pizza;
	public int pizzeCoda = 0;
	Lista ls;
	public int chiave = 0;
	List listCoda;
	List listCottura;
	List listPronte;
	List listConsegnate;
	 private AudioInputStream sound;
	 private Clip clip;
	 private Clip clipGrando;
	private Clip clipZando;
	private Clip clipErrore;
	private Clip clipGrandoPizzChiusa;
	Display display;

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
		display = Display.getDefault();
		createContents();
		shlPizzeriaFedatobortolamiol.open();
		shlPizzeriaFedatobortolamiol.layout();
		while (!shlPizzeriaFedatobortolamiol.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void addPizzaInCottura(String pizza) {
		display.asyncExec(new Runnable() {			
			@Override
			public void run() {
				listCottura.add(pizza);
				listCoda.remove(pizza);
			}
		});
		
	}
	public void addPizzaPronta(String pizza) {
		display.asyncExec(new Runnable() {			
			@Override
			public void run() {
				listPronte.add(pizza);
				listCottura.remove(pizza);
			}
		});
		
	}
	
	public void addPizzaConsegnata(String pizza) {
		display.asyncExec(new Runnable() {			
			@Override
			public void run() {
				listConsegnate.add(pizza);
				listPronte.remove(pizza);
				
				//suono di grando che dice buon appetito
				try {
					String path = "src/progettoPizzeriaFB/" + "GrandoBuonAppetito.wav";
					sound = AudioSystem.getAudioInputStream(new File(path));
					clipGrando = AudioSystem.getClip();
					clipGrando.open(sound);
					} catch (UnsupportedAudioFileException | IOException e1) {
					e1.printStackTrace();
						} catch (LineUnavailableException e1) {
							e1.printStackTrace();
						}
			
				 clipGrando.start();
			}
		});
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		lista= new Lista(this);//creato la lista
		
		shlPizzeriaFedatobortolamiol = new Shell();
		shlPizzeriaFedatobortolamiol.setSize(606, 337);
		shlPizzeriaFedatobortolamiol.setText("Pizzeria Fedato&Bortolamiol");
		
		ListViewer listViewer = new ListViewer(shlPizzeriaFedatobortolamiol, SWT.BORDER | SWT.V_SCROLL);
		listCoda = listViewer.getList();
		listCoda.setBounds(10, 123, 116, 159);
		
		ListViewer listViewer_1 = new ListViewer(shlPizzeriaFedatobortolamiol, SWT.BORDER | SWT.V_SCROLL);
		listCottura = listViewer_1.getList();
		listCottura.setBounds(155, 123, 116, 159);
		
		ListViewer listViewer_2 = new ListViewer(shlPizzeriaFedatobortolamiol, SWT.BORDER | SWT.V_SCROLL);
		listPronte = listViewer_2.getList();
		listPronte.setBounds(308, 123, 116, 159);
		
		ListViewer listViewer_3 = new ListViewer(shlPizzeriaFedatobortolamiol, SWT.BORDER | SWT.V_SCROLL);
		listConsegnate = listViewer_3.getList();
		listConsegnate.setBounds(453, 123, 116, 159);
		
		Button btnApri = new Button(shlPizzeriaFedatobortolamiol, SWT.NONE);
		btnApri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				// suono del gallo
				try {
					String path = "src/progettoPizzeriaFB/" + "Gallo.wav";
					sound = AudioSystem.getAudioInputStream(new File(path));
					clip = AudioSystem.getClip();
					clip.open(sound);
					} catch (UnsupportedAudioFileException | IOException e1) {
					e1.printStackTrace();
						} catch (LineUnavailableException e1) {
							e1.printStackTrace();
						}
			
				 clip.start();
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
		btnApri.setBounds(10, 10, 75, 25);
		btnApri.setText("Apri Pizzeria");
		
		Button btnChiudi = new Button(shlPizzeriaFedatobortolamiol, SWT.NONE);
		btnChiudi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					//suono grando che chiude la pizzeria
					String path = "src/progettoPizzeriaFB/" + "GrandoPizzeriaChiusa.wav";
					sound = AudioSystem.getAudioInputStream(new File(path));
					clipGrandoPizzChiusa = AudioSystem.getClip();
					clipGrandoPizzChiusa.open(sound);
					} catch (UnsupportedAudioFileException | IOException e1) {
					e1.printStackTrace();
						} catch (LineUnavailableException e1) {
							e1.printStackTrace();
						}
			
				clipGrandoPizzChiusa.start();
				chiave = 0;
				PizzeriaChiusa pc = new PizzeriaChiusa();
				shlPizzeriaFedatobortolamiol.close();
				pc.open();
				
				
			}
		});
		btnChiudi.setBounds(491, 10, 89, 25);
		btnChiudi.setText("Chiudi Pizzeria");
		
		Button btnCliente = new Button(shlPizzeriaFedatobortolamiol, SWT.NONE);
		btnCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (chiave == 1 && !txtPizza.getText().isEmpty()){
					//suono di zandona'
					try {
						String path = "src/progettoPizzeriaFB/" + "zandona.wav";
						sound = AudioSystem.getAudioInputStream(new File(path));
						clipZando = AudioSystem.getClip();
						clipZando.open(sound);
						} catch (UnsupportedAudioFileException | IOException e1) {
						e1.printStackTrace();
							} catch (LineUnavailableException e1) {
								e1.printStackTrace();
							}
				
					clipZando.start();
					
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
		btnCliente.setBounds(421, 50, 90, 25);
		btnCliente.setText("Ordina pizza");
		
		
		
		Label lblInserisci = new Label(shlPizzeriaFedatobortolamiol, SWT.NONE);
		lblInserisci.setBounds(56, 55, 154, 15);
		lblInserisci.setText("Inserisci qui la tua pizza : ");
		
		txtPizza = new Text(shlPizzeriaFedatobortolamiol, SWT.BORDER);
		txtPizza.setBounds(216, 52, 170, 21);
		
		Label lblInCoda = new Label(shlPizzeriaFedatobortolamiol, SWT.NONE);
		lblInCoda.setBounds(10, 102, 55, 15);
		lblInCoda.setText("In Coda");
		
		Label lblInCottura = new Label(shlPizzeriaFedatobortolamiol, SWT.NONE);
		lblInCottura.setBounds(155, 102, 55, 15);
		lblInCottura.setText("In Cottura");
		
		Label lblPronte = new Label(shlPizzeriaFedatobortolamiol, SWT.NONE);
		lblPronte.setBounds(308, 102, 55, 15);
		lblPronte.setText("Pronte");
		
		Label label = new Label(shlPizzeriaFedatobortolamiol, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 41, 605, 2);
		
		Label label_1 = new Label(shlPizzeriaFedatobortolamiol, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(-25, 94, 615, 2);
		
		
		
		Label lblConsegnate = new Label(shlPizzeriaFedatobortolamiol, SWT.NONE);
		lblConsegnate.setBounds(453, 102, 75, 15);
		lblConsegnate.setText("Consegnate");
		
	

	}
}
