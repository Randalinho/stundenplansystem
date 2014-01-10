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
	
	
	
public class StudiengangForm extends Content {



			/**
			 * Aufbau der Seite, um den Raum anzuzeigen, zu lÃ¶schen und zu bearbeiten
			 */
			
			//final TextBox nachnameTextBox = new TextBox();
			//final TextBox vornameTextBox = new TextBox();
			final FlexTable tabelleSg = new FlexTable();
			final Button createSgButton = new Button ("Studiengang anlegen");
			final Button changeSgButton = new Button("Studiengang bearbeiten");
			final Button deleteSgButton = new Button("Studiengang lÃ¶schen");
			
			private final HTML ueberschrift = new HTML ("<h2>Ãœbersicht der StudiengÃ¤nge<h2>");
			
			final CreateStudiengang createSg = new CreateStudiengang();
		//	final ChangeStudiengang changeSg = new ChangeStudiengang();
			//final DeleteStudiengang deleteSg = new DeleteStudiengang();
			
			final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);			


			
			public void onLoad() {
				
				this.add(ueberschrift);
				showWidget();
				
				
			//int row = tabelleDozent.getRowCount();
				
				
				tabelleSg.setText(0, 0, "Bezeichnung");
				tabelleSg.setCellPadding(10);
				tabelleSg.setText(0, 1, "Funktionen");
				tabelleSg.setWidget(1, 1, deleteSgButton);
				tabelleSg.setWidget(1, 2, changeSgButton);
				
				
				createSgButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
					showCreate();
					}
				});
				
				changeSgButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						showChange();
					}
				});
				
				/*deleteSgButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						showDelete();
					}
				});*/
				
			}
				
			public void showWidget() {
				
				this.add(tabelleSg);
				this.add(createSgButton);
				this.add(changeSgButton);
				this.add(deleteSgButton);
			}
			
			public void showCreate() {
				this.clear();
				this.add(createSg);
			}
			
			public void showChange() {
				this.clear();
				this.add(changeSg);
			}
			
			/*public void showDelete() {
				this.clear();
				this.add(deleteSg);
			}*/
			
			/**public Studiengang updateFlexTable (Studiengang result) {
				for (int i = 0; i < getAllStudiengang.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benÃ¶tigt
					tabelleSg.addItem(getAllStudiengang.get(i).getVorname());
					
				}
			}
		*/

	}




