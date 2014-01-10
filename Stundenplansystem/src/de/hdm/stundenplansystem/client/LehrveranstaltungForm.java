package de.hdm.stundenplansystem.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DockLayoutPanel;
//import com.google.gwt.user.client.ui.Grid;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.RootLayoutPanel;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.Panel;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;



import de.hdm.stundenplansystem.shared.Verwaltungsklasse;
//import de.hdm.itprojekt.client.ClientsideSettings;
import de.hdm.stundenplansystem.shared.VerwaltungsklasseAsync;
//import de.hdm.itprojekt.shared.Verwaltungsklasse;
//import de.hdm.itprojekt.client.*;
//import de.hdm.itprojekt.client.gui.*;




/**
* @author V. Hofmann
*
*/
public class LehrveranstaltungForm extends Content {


	/**
	 * Aufbau der Seite, um die Lehrveranstaltung anzuzeigen, zu lÃ¶schen und zu bearbeiten
	 */
	
	private final HTML ueberschrift = new HTML ("<h2>Ãœbersicht der Lehrveranstaltungen<h2>");


	final FlexTable tabelleLv = new FlexTable();
	final Button createLvButton = new Button ("Lehrveranstaltung anlegen");
	final Button changeLvButton = new Button("Lehrveranstaltung bearbeiten");
	final Button deleteLvButton = new Button("Lehrveranstaltung lÃ¶schen");
	
	final CreateLehrveranstaltung createLv = new CreateLehrveranstaltung();
	final ChangeLehrveranstaltung changeLv = new ChangeLehrveranstaltung();
	//final DeleteLehrveranstaltung deleteLv = new DeleteLehrveranstaltung();
	
	final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	 
		
	public void onLoad() {
		
		showWidget();
	
		//int row = tabelleDozent.getRowCount();
		
		tabelleLv.setText(0, 0, "Bezeichnung");
		tabelleLv.setCellPadding(10);
		tabelleLv.setText(0, 1, "Semester");
		tabelleLv.setText(0, 2, "Umfang");
		tabelleLv.setText(0, 3, "Funktionen");
		tabelleLv.setWidget(1, 4, deleteLvButton);
		tabelleLv.setWidget(1, 5, changeLvButton);
		
		
		createLvButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				showCreate();
			}
		});
		
		changeLvButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				showChange();
			}
		});
		
		/*deleteLvButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				showDelete();
			}
		});*/
		
	}
		
	public void showWidget() {
		this.clear();
		this.add(ueberschrift);
		this.add(tabelleLv);
		this.add(createLvButton);
		this.add(changeLvButton);
		this.add(deleteLvButton);
	}
	
	public void showCreate() {
		this.clear();			
		this.add(createLv);
	}
	
	public void showChange() {
		this.clear();
		this.add(changeLv);
	}
	
	/*public void showDelete() {
		this.clear();
		this.add(deleteLv);
	}*/
	
	
	/**public Lehrveranstaltung updateFlexTable (Lehrveranstaltung result) {
		for (int i = 0; i < getAllLehrveranstaltung.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benÃ¶tigt
			tabelleLv.addItem(getAllLehrveranstaltung.get(i).getVorname());
			
		}
	}
*/

}
