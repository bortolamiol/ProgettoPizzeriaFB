package progettoPizzeriaFB;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class PizzeriaChiusa {

	protected Shell shlPizzeriachiusa;
	private AudioInputStream sound;
	 private Clip clipGallo;
	 private Clip clipGrando;
	private Clip clipZando;
	Display display;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PizzeriaChiusa window = new PizzeriaChiusa();
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
		shlPizzeriachiusa.open();
		shlPizzeriachiusa.layout();
		while (!shlPizzeriachiusa.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPizzeriachiusa = new Shell();
		shlPizzeriachiusa.setSize(450, 488);
		shlPizzeriachiusa.setText("Pizzeria Chiusa");
		
		Button btnApriPizzeria = new Button(shlPizzeriachiusa, SWT.NONE);
		btnApriPizzeria.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String path = "src/progettoPizzeriaFB/" + "gallo.wav";
					sound = AudioSystem.getAudioInputStream(new File(path));
					clipGallo = AudioSystem.getClip();
					clipGallo.open(sound);
					} catch (UnsupportedAudioFileException | IOException e1) {
					e1.printStackTrace();
						} catch (LineUnavailableException e1) {
							e1.printStackTrace();
						}
			
				clipGallo.start();
				PizzeriaHome PH = new PizzeriaHome();
				shlPizzeriachiusa.close();
				PH.open();
			}
		});
		btnApriPizzeria.setBounds(177, 415, 75, 25);
		btnApriPizzeria.setText("Apri Pizzeria");
		
		Label label = new Label(shlPizzeriachiusa, SWT.CENTER);
		label.setImage(SWTResourceManager.getImage(PizzeriaChiusa.class, "/progettoPizzeriaFB/Sorry_closed.gif"));
		label.setBounds(10, 10, 400, 386);
		
		Label label_1 = new Label(shlPizzeriachiusa, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(-33, 402, 467, 2);

	}
}
