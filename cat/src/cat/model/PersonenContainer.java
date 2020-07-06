package cat.model;

import static cat.settings.Pfade.DATEI_PFAD_PERSONENLISTE;
import static cat.settings.Pfade.DATEI_PFAD_ALLE_PERSONEN;
import static cat.settings.Pfade.DATEI_PFAD_COUNTER;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class PersonenContainer {

	public double counter = 0;
	private static final double MAX_COUNTER = 7;
	@SuppressWarnings("unused")
	private double auslastungsgrad = 0;
	private static PersonenContainer derContainer;
	private ArrayList<Person> allePersonen = new ArrayList<>();
	
	// Konstruktor
	public PersonenContainer(){
		// ladeCounter();
		// allePersonenAusDateiLaden();
	}
	
	// Container zurückgeben, es gibt nur einen
	public static PersonenContainer getContainer() {
		if (derContainer == null)
			derContainer = new PersonenContainer();
		derContainer.laden();
		return derContainer;
	}
	
	// Allgemeine Getter und Setter
	public ArrayList<Person> getAllePersonen() {
		return allePersonen;
	}

	public double getCounter() {
		return counter;
	}

	public double getAuslastungsgrad() {
		double auslastungsgrad = 0.0;
		try {
			auslastungsgrad = Math.round((counter/MAX_COUNTER)*100);
			return auslastungsgrad;
		} catch (Exception e) {
			return 0.0;
		}
	}

	public void setAuslastungsgrad(double auslastungsgrad) {
		this.auslastungsgrad = auslastungsgrad;
	}
	
	// Counter und Personenliste laden
	private void laden() {
		allePersonenAusDateiLaden();
		counterAusDateiLaden();
	}
	
	// Counter und Personenliste speichern
	private void speichern() {
		allePersonenInDateiSpeichern();
		counterInDateiSpeichern();
	}

	// dem Container eine Person (als Referenz auf ein Objekt) hinzufügen
	public void fügePersonHinzu(Person einzufügendePerson) throws Exception {
		if(einzufügendePerson.getName().isEmpty())
			throw new Exception("Bitte geben Sie einen Namen ein!");
		
		if(getAuslastungsgrad() >= 100.0)
			throw new Exception("Das Gebäude ist ausgelastet!");
		
		Person indexPerson = null;
		for (Person person : allePersonen) {
			if (einzufügendePerson.getName().equalsIgnoreCase(person.getName())) {
				indexPerson = person;
				break;
			}
		}

		if (indexPerson == null) {
			allePersonen.add(einzufügendePerson);
			counter = getCounter() + einzufügendePerson.getEinflussfaktor();
			speichern();
		}
		else
			throw new Exception("Person bereits im Gebäude!");
	}

	// eine Person aus dem Container anhand des Namens entfernen
	public void entfernePerson(String name) throws Exception {
		if(name.isEmpty())
			throw new Exception("Bitte geben Sie einen Namen ein!");
		boolean personEntfernt = false;
		for (int i = 0; i < allePersonen.size(); i++) {
			if (allePersonen.get(i).getName().equalsIgnoreCase(name)) {
				counter -= allePersonen.get(i).getEinflussfaktor();
				allePersonen.remove(i);
				personEntfernt = true;
				speichern();
				break;
			}

		}

		if (!personEntfernt)
			throw new Exception("Person nicht gefunden!");
	}

	// eine Liste aller Personen als txt-Datei ausgeben
	public void druckePersonenliste() throws Exception {
		if(allePersonen.isEmpty())
			throw new Exception("Keine Personen im Gebäude!");
		try (RandomAccessFile datei = new RandomAccessFile(DATEI_PFAD_PERSONENLISTE, "rw")) {
			for (Person person : allePersonen)
				datei.writeBytes(person.toString());
			datei.close();
		} catch (FileNotFoundException e) {
			StackTraceElement[] s = e.getStackTrace();
			System.err.println("Fehler in: " + s[0].getMethodName());
			System.err.println("Datei nicht gefunden!");
			System.err.println("Angegebener Pfad: " + DATEI_PFAD_PERSONENLISTE);
		} catch (IOException e) {
			StackTraceElement[] s = e.getStackTrace();
			System.err.println("Fehler in: " + s[0].getMethodName());
		}	
	}
	
	// die Liste aller Personen in eine Datei speichern und serialisieren
	public void allePersonenInDateiSpeichern() {
		try (FileOutputStream fileOutputStream = new FileOutputStream(DATEI_PFAD_ALLE_PERSONEN);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(allePersonen);
		} catch (FileNotFoundException e) {
			StackTraceElement[] s = e.getStackTrace();
			System.err.println("Fehler in: " + s[0].getMethodName());
			System.err.println("Datei nicht gefunden!");
			System.err.println("Angegebener Pfad: " + DATEI_PFAD_ALLE_PERSONEN);
		} catch (IOException e) {
			StackTraceElement[] s = e.getStackTrace();
			System.err.println("Fehler in: " + s[0].getMethodName());
		}
	}

	// die Liste aller Personen aus einer Datei laden und deserialisieren
	@SuppressWarnings("unchecked")
	public void allePersonenAusDateiLaden() {
		try (FileInputStream fileInputStream = new FileInputStream(DATEI_PFAD_ALLE_PERSONEN);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
			allePersonen = ((ArrayList<Person>) objectInputStream.readObject());
		} catch (FileNotFoundException e) {
			// StackTraceElement[] s = e.getStackTrace();
			// System.err.println("Fehler in: " + s[0].getMethodName());
			// System.err.println("Datei nicht gefunden!");
			// System.err.println("Angegebener Pfad: " + DATEI_PFAD_ALLE_PERSONEN);
		} catch (ClassNotFoundException e) {
			StackTraceElement[] s = e.getStackTrace();
			System.err.println("Fehler in: " + s[0].getMethodName());
			System.err.println("Klasse nicht gefunden!");
			System.err.println("Angegebener Pfad: " + DATEI_PFAD_ALLE_PERSONEN);
		} catch (IOException e) {
			StackTraceElement[] s = e.getStackTrace();
			System.err.println("Fehler in: " + s[0].getMethodName());
		}
	}
	
	// den Counter in eine Datei speichern
	public void counterInDateiSpeichern() {
		try (RandomAccessFile datei = new RandomAccessFile(DATEI_PFAD_COUNTER, "rw")) {
			datei.writeDouble(counter);
			datei.close();
		} catch (FileNotFoundException e) {
			// StackTraceElement[] s = e.getStackTrace();
			// System.err.println("Fehler in: " + s[0].getMethodName());
			// System.err.println("Datei nicht gefunden!");
			// System.err.println("Angegebener Pfad: " + DATEI_PFAD_COUNTER);
		} catch (IOException e) {
			// StackTraceElement[] s = e.getStackTrace();
			// System.err.println("Fehler in: " + s[0].getMethodName());
		}	
	}

	// den Counter aus einer Datei laden
	public void counterAusDateiLaden() {
		try (RandomAccessFile datei = new RandomAccessFile(DATEI_PFAD_COUNTER, "rw")) {
			datei.seek(0);
			counter = datei.readDouble();
			datei.close();
		} catch (FileNotFoundException e) {
			// StackTraceElement[] s = e.getStackTrace();
			// System.err.println("Fehler in: " + s[0].getMethodName());
			// System.err.println("Datei nicht gefunden!");
			// System.err.println("Angegebener Pfad: " + DATEI_PFAD_COUNTER);
		} catch (IOException e) {
			// StackTraceElement[] s = e.getStackTrace();
			// System.err.println("Fehler in: " + s[0].getMethodName());
		}	
	}
}
