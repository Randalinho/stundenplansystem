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
import de.hdm.stundenplansystem.shared.VerwaltungsklasseAsync;

//import de.hdm.itprojekt.client.ClientsideSettings;
//import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
//import de.hdm.itprojekt.shared.bo.Dozent;
//import de.hdm.itprojekt.shared.Verwaltungsklasse;
//import de.hdm.itprojekt.client.*;
//import de.hdm.itprojekt.client.gui.*;



public class RaumForm extends Content {


		/**
		 * Aufbau der Seite, um den Raum anzuzeigen, zu lÃ¶schen und zu bearbeiten
		 */
		
		private final HTML ueberschrift = new HTML ("<h2>Ãœbersicht der RÃ¤ume<h2>");

	
		final FlexTable tabelleRaum = new FlexTable();
		final Button createRaumButton = new Button ("Raum anlegen");
		final Button changeRaumButton = new Button("Raum bearbeiten");
		final Button deleteRaumButton = new Button("Raum lÃ¶schen");
		
		final CreateRaum createRaum = new CreateRaum();
		final ChangeRaum changeRaum = new ChangeRaum();
		//final DeleteRaum deleteRaum = new DeleteRaum();
		
		final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);		


		
		public void onLoad() {
			
			this.add(ueberschrift);
			showWidget();
		
			
			//int row = tabelleDozent.getRowCount();
			
			
			tabelleRaum.setText(0, 0, "Bezeichnung");
			tabelleRaum.setCellPadding(10);
			tabelleRaum.setText(0, 1, "KapazitÃ¤t");
			tabelleRaum.setText(0, 2, "Funktionen");
			tabelleRaum.setWidget(1, 2, deleteRaumButton);
			tabelleRaum.setWidget(1, 3, changeRaumButton);

			
			createRaumButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					showCreate();
				}
			});
			
			changeRaumButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					showChange();
				}
			});
			
			/*deleteRaumButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					showDelete();
				}
			});*/
			
		}
			
		public void showWidget() {
			this.add(tabelleRaum);
			this.add(createRaumButton);
			this.add(changeRaumButton);
			this.add(deleteRaumButton);
		}
		
		public void showCreate() {
			this.clear();
			this.add(createRaum);
		}
		
		public void showChange() {
			this.clear();
			this.add(changeRaum);
		}
		
		/*public void showDelete() {
			this.clear();
			this.add(deleteRaum);
		}*/
		
		
		/**public Raum updateFlexTable (Raum result) {
			for (int i = 0; i < getAllRaum.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benÃ¶tigt
				tabelleRaum.addItem(getAllRaum.get(i).getVorname());
				
			}
		}
	*/

}



