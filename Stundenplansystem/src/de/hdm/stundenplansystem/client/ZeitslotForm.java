package de.hdm.stundenplansystem.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
	//import com.google.gwt.user.client.rpc.AsyncCallback;
	import com.google.gwt.user.client.ui.Button;
	//import com.google.gwt.user.client.ui.DockLayoutPanel;
	//import com.google.gwt.user.client.ui.Grid;
	import com.google.gwt.user.client.ui.HTML;
	//import com.google.gwt.user.client.ui.HorizontalPanel;
	//import com.google.gwt.user.client.ui.Label;
	//import com.google.gwt.user.client.ui.RootLayoutPanel;
	//import com.google.gwt.user.client.ui.TextBox;
	//import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;

import de.hdm.stundenplansystem.shared.Verwaltungsklasse;
import de.hdm.stundenplansystem.shared.VerwaltungsklasseAsync;
	//import com.google.gwt.user.client.ui.Panel;
	//import com.google.gwt.user.client.ui.RootPanel;
	//import com.google.gwt.user.client.ui.VerticalPanel;

	//import de.hdm.itprojekt.client.ClientsideSettings;
	//import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
	//import de.hdm.itprojekt.shared.bo.Dozent;
	//import de.hdm.itprojekt.shared.Verwaltungsklasse;
	//import de.hdm.itprojekt.client.*;
	//import de.hdm.itprojekt.client.gui.*;
	
	
public class ZeitslotForm extends Content {


			/**
			 * Aufbau der Seite, um den Raum anzuzeigen, zu lÃ¶schen und zu bearbeiten
			 */
			
			private final HTML ueberschrift = new HTML ("<h2>Ãœbersicht der Zeitslots<h2>");
			
			final FlexTable tabelleZs = new FlexTable();
			final Button createZsButton = new Button ("Zeitslot anlegen");
			final Button changeZsButton = new Button("Zeitslot bearbeiten");
			final Button deleteZsButton = new Button("Zeitslot lÃ¶schen");
			
			final CreateZeitslot createZs = new CreateZeitslot();
			final ChangeZeitslot changeZs = new ChangeZeitslot();
			//final DeleteZeitslot deleteZs = new DeleteZeitslot();
			
			final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
			 
			
			public void onLoad() {
				
				this.add(ueberschrift);
				showWidget();
			
				
			//int row = tabelleDozent.getRowCount();
				
				
				tabelleZs.setText(0, 0, "Montag");
				tabelleZs.setCellPadding(10);
				tabelleZs.setText(0, 1, "Dienstag");
				tabelleZs.setText(0, 2, "Mittwoch");
				tabelleZs.setText(0, 3, "Donnerstag");
				tabelleZs.setText(0, 1, "Freitag");
				tabelleZs.setWidget(1, 4, deleteZsButton);
				tabelleZs.setWidget(1, 5, changeZsButton);

				
				createZsButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
					showCreate();
					}
				});
				
				changeZsButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						showChange();
					}
				});
				
				/*deleteZsButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						showDelete();
					}
				});*/
				
			}
				
			public void showWidget() {
				this.add(tabelleZs);
				this.add(createZsButton);
				this.add(changeZsButton);
				this.add(deleteZsButton);
			}
			
			public void showCreate() {
				this.clear();
				this.add(createZs);
			}
			
			public void showChange() {
				this.clear();
				this.add(changeZs);
			}
			
			/*public void showDelete() {
				this.clear();
				this.add(deleteZs);
			}*/
			
			/**public Zeitslot updateFlexTable (Zeitslot result) {
				for (int i = 0; i < getAllZeitslot.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benÃ¶tigt
					tabelleZs.addItem(getAllZeitslot.get(i).getVorname());
					
				}
			}
		*/

	}




