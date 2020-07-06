package cat.control;

import cat.model.Kunde;
import cat.model.Mitarbeiter;
import cat.model.PersonenContainer;
import cat.view.AnwesenheitskontrolleV;

public class AnwesenheitskontrolleC {
	private AnwesenheitskontrolleV  anwesenheitskontrolleV;
	private PersonenContainer personenContainer;
	
	// Konstruktor
	// Verbindung zu Model und View herstellen
	public AnwesenheitskontrolleC(AnwesenheitskontrolleV anwesenheitskontrolleV, PersonenContainer personenContainer) {
		this.anwesenheitskontrolleV = anwesenheitskontrolleV;
		this.personenContainer = personenContainer;
	}
	
	// Person verlässt das Gebäude
	public void gebäudeVerlassen(String name) {
		try {
			personenContainer.entfernePerson(name);
			anwesenheitskontrolleV.setAuslastung(personenContainer.getAuslastungsgrad());
			anwesenheitskontrolleV.setInfo("Person entfernt!");
			anwesenheitskontrolleV.reset();
		} catch (Exception e) {
			anwesenheitskontrolleV.setInfo(e.getMessage());
		}
	}
	
	// Person betritt das Gebäude
	public void gebäudeBetreten(String name, boolean istMitarbeiter) {
		if(istMitarbeiter) {
			try {
				personenContainer.fügePersonHinzu(new Mitarbeiter(name));
				anwesenheitskontrolleV.setAuslastung(personenContainer.getAuslastungsgrad());
				anwesenheitskontrolleV.setInfo("Mitarbeiter hinzugefügt!");
			}catch (Exception e) {
				anwesenheitskontrolleV.setInfo(e.getMessage());
			}
		} else
			try {
				personenContainer.fügePersonHinzu(new Kunde(name));
				anwesenheitskontrolleV.setAuslastung(personenContainer.getAuslastungsgrad());
				anwesenheitskontrolleV.setInfo("Kunde hinzugefügt!");
			} catch (Exception e) {
				anwesenheitskontrolleV.setInfo(e.getMessage());
			}		
		anwesenheitskontrolleV.reset();
	}
	
	// Anwesenheitsliste ausgeben
	public void listeAusgeben() {
		try {
			personenContainer.druckePersonenliste();
			anwesenheitskontrolleV.setInfo("Liste gedruckt!");
		} catch (Exception e) {
			anwesenheitskontrolleV.setInfo(e.getMessage());
		}
	}
	
	public void auslastungInitialisieren() {
		anwesenheitskontrolleV.setAuslastung(personenContainer.getAuslastungsgrad());
	}
}
