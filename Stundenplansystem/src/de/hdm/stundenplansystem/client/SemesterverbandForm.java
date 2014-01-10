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
	
	
public class SemesterverbandForm extends Content {

			/**
			 * Aufbau der Seite, um den Raum anzuzeigen, zu lÃ¶schen und zu bearbeiten
			 */
			
			private final HTML ueberschrift = new HTML ("<h2>Ãœbersicht der SemesterverbÃ¤nde<h2>");

			
			//final Label flexTable = new Label();
			//private VerticalPanel detailsPanel = new VerticalPanel();
			
			//final TextBox nachnameTextBox = new TextBox();
			//final TextBox vornameTextBox = new TextBox();
			final FlexTable tabelleSv = new FlexTable();
			final Button createSvButton = new Button ("Semesterverband anlegen");
			final Button changeSvButton = new Button("Semesterverband bearbeiten");
			final Button deleteSvButton = new Button("Semesterverband lÃ¶schen");
			
			final CreateSemesterverband createSv = new CreateSemesterverband();
			final ChangeSemesterverband changeSv = new ChangeSemesterverband();
			//final DeleteSemesterverband deleteSv = new DeleteSemesterverband();
			
			final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
			 
			
			//final Label valueLabel = new Label();
			


			
			public void onLoad() {
				
				this.add(ueberschrift);
				showWidget();
			
				
			//int row = tabelleDozent.getRowCount();
				
				
				tabelleSv.setText(0, 0, "Jahrgang");
				tabelleSv.setCellPadding(10);
				tabelleSv.setText(0, 1, "Studiengang");
				tabelleSv.setText(0, 2, "Semester");
				tabelleSv.setText(0, 3, "Anzahl");
				tabelleSv.setText(0, 4, "Funktionen");
				tabelleSv.setWidget(1, 4, deleteSvButton);
				tabelleSv.setWidget(1, 5, changeSvButton);

				
				createSvButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						showCreate();
					}
				});
				
				changeSvButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						showChange();
					}
				});
				
				/*deleteSvButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						showDelete();
					}
				});*/
				
			}
				
			public void showWidget() {
				this.add(tabelleSv);
				this.add(createSvButton);
				this.add(changeSvButton);
				this.add(deleteSvButton);
			}
			
			public void showCreate() {
				this.clear();
				this.add(createSv);
			}
			
			public void showChange() {
				this.clear();
				this.add(changeSv);
			}
			
			/*public void showDelete() {
				this.clear();
				this.add(deleteSv);
			}*/
			
			
			/**public Semesterverband updateFlexTable (Semesterverband result) {
				for (int i = 0; i < getAllSemesterverband.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benÃ¶tigt
					tabelleSv.addItem(getAllSemesterverband.get(i).getVorname());
					
				}
			}
		*/

	}



