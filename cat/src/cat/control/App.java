package cat.control;
import cat.model.PersonenContainer;
import cat.view.AnwesenheitskontrolleV;

public class App {
	
	public static void main(String[] args) {
		// Model anlegen
		PersonenContainer personenContainer = PersonenContainer.getContainer(); 
		
		// View anlegen
		AnwesenheitskontrolleV anwesenheitskontrolleV = new AnwesenheitskontrolleV();
		
		// Control anlegen mit Referenz auf View und Model
		AnwesenheitskontrolleC anwesenheitskontrolleC = new AnwesenheitskontrolleC(anwesenheitskontrolleV, personenContainer);
		
		// View eine Referenz auf Control übergeben
		anwesenheitskontrolleV.setAnwesenheitskontrolleC(anwesenheitskontrolleC);	
	}
}
