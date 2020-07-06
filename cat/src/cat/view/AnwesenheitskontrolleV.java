package cat.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cat.control.AnwesenheitskontrolleC;

public class AnwesenheitskontrolleV extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private AnwesenheitskontrolleC anwesenheitskontrolleC;

	private JLabel lblInfo = new JLabel("Info: ");
	private JLabel lblInfoText = new JLabel("-");
	private JLabel lblAuslastung = new JLabel("Auslastungsgrad: ");
	private JLabel lblAuslastungText = new JLabel();
	private JLabel lblName = new JLabel("Name: ");
	private JTextField txtName = new JTextField();
	private JLabel lblMitarbeiterAuswahl = new JLabel("Mitarbeiter: ");
	private JCheckBox cbMitarbeiterAuswahl = new JCheckBox("Ja", false);
	private JButton btnGeb�udeBetreten = new JButton("Geb�ude betreten");
	private JButton btnGeb�udeVerlassen = new JButton("Geb�ude verlassen");
	private JButton btnListeAusgeben = new JButton("Liste ausgeben");
	private JPanel pnlFormular = new JPanel();
	private JPanel pnlSchaltfl�chen = new JPanel();
	private JPanel pnlGesamt = new JPanel();
	
	// Konstruktor
	public AnwesenheitskontrolleV() {
		initComponents();
	}

	// Verbindung zum Controller herstellen
	public void setAnwesenheitskontrolleC(AnwesenheitskontrolleC anwesenheitskontrolleC) {
		this.anwesenheitskontrolleC = anwesenheitskontrolleC;
		// Den Auslastungsgrad erstmalig setzen
		anwesenheitskontrolleC.auslastungInitialisieren();
	}
	
	// Elemente des Fensters initialisieren
	private void initComponents() {
		// Fenster grunds�tzlich einstellen
		setTitle("CAT - Anwesehnheitskontrolle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(500, 300));
		setLocationRelativeTo(null);
		setResizable(false);

		// Panel Formular einstellen
		pnlFormular.setLayout(new GridLayout(4, 2, 5, 5));
		pnlFormular.setBorder(new EmptyBorder(0, 20, 0, 20));
		pnlFormular.add(lblInfo);
		pnlFormular.add(lblInfoText);
		pnlFormular.add(lblAuslastung);
		pnlFormular.add(lblAuslastungText);
		pnlFormular.add(lblName);
		pnlFormular.add(txtName);
		pnlFormular.add(lblMitarbeiterAuswahl);
		pnlFormular.add(cbMitarbeiterAuswahl);

		// Buttons einrichten
		btnGeb�udeBetreten.setBackground(new Color(0, 89, 179));
		btnGeb�udeBetreten.setForeground(new Color(255, 255, 255));
		btnGeb�udeVerlassen.setBackground(new Color(0, 89, 179));
		btnGeb�udeVerlassen.setForeground(new Color(255, 255, 255));
		btnListeAusgeben.setBackground(new Color(0, 89, 179));
		btnListeAusgeben.setForeground(new Color(255, 255, 255));
		
		// ActionListener zu Buttons hinzuf�gen
		btnGeb�udeBetreten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anwesenheitskontrolleC.geb�udeBetreten(txtName.getText(), cbMitarbeiterAuswahl.isSelected());
			};
		});
		
		btnGeb�udeVerlassen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				anwesenheitskontrolleC.geb�udeVerlassen(txtName.getText());
			};
		});
		
		btnListeAusgeben.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				anwesenheitskontrolleC.listeAusgeben();
			};
		});

		// Panel Schaltfl�chen einstellen
		pnlSchaltfl�chen.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
		pnlSchaltfl�chen.add(btnGeb�udeBetreten);
		pnlSchaltfl�chen.add(btnGeb�udeVerlassen);
		pnlSchaltfl�chen.add(btnListeAusgeben);

		// Panel Gesamt einstellen
		pnlGesamt.setLayout(new BorderLayout());
		pnlGesamt.add(pnlFormular, BorderLayout.CENTER);
		pnlGesamt.add(pnlSchaltfl�chen, BorderLayout.SOUTH);

		// Das Panel Gesamt dem Fenster hinzuf�gen
		getContentPane().add(pnlGesamt);
		
		// Das Fenster sichtbar machen
		setVisible(true);
	}

	// Infotext setzen
	public void setInfo(String message) {
		lblInfoText.setText(message);
		
	}
	
	// Auslastungsgrad setzen
	public void setAuslastung(double auslastungsgrad) {
		if(auslastungsgrad <= 85.0)
			lblAuslastungText.setForeground(new Color(0, 128, 0));
		else if(auslastungsgrad > 85.0 && auslastungsgrad <= 90.0)
			lblAuslastungText.setForeground(new Color(230, 115, 0));
		else if (auslastungsgrad > 90.0)
			lblAuslastungText.setForeground(new Color(230, 38, 0));
		
		lblAuslastungText.setText(auslastungsgrad + "%");
	}
	
	// Formular zur�cksetzen
	public void reset() {
		cbMitarbeiterAuswahl.setSelected(false);
		txtName.setText("");
	}
}
