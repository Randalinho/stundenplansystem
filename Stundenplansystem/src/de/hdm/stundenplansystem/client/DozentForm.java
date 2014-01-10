package de.hdm.stundenplansystem.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import de.hdm.stundenplansystem.shared.*;
import de.hdm.stundenplansystem.shared.bo.Dozent;
import de.hdm.stundenplansystem.shared.VerwaltungsklasseAsync;

/**
 * Formular für die Darstellung des selektierten Kunden
 * 
 * @author Thies, Espich
 *
 */

public class DozentForm extends Content {
	
	private final HTML ueberschrift = new HTML ("<h2>Ãœbersicht der Dozenten<h2>");

	  final TextBox tbvorname = new TextBox ();
	  final TextBox tbnachname = new TextBox ();
	  final Button anlegen = new Button ("neuen Dozent anlegen");
	  final Button bearbeiten = new Button ("Dozent bearbeiten");
	  final Button loeschen = new Button ("Dozent löschen");
	  		
      final CreateDozent createD = new CreateDozent();	
	//  final ChangeDozent changeD = new ChangeDozent();
	  
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	  Dozent shownDozent = null; 
	  TreeViewModel tvm = null;
	  
	  public DozentForm() {
		  Grid dozentGrid = new Grid (4, 2);
			this.add(dozentGrid);
		  
			Label lbvorname = new Label("Vorname");
			dozentGrid.setWidget(0, 0, lbvorname);
			dozentGrid.setWidget(0, 1, tbvorname);

			Label lbnachname = new Label("Nachname");
			dozentGrid.setWidget(1, 0, lbnachname);
			dozentGrid.setWidget(1, 1, tbnachname);
			
			Label lbfunktionen = new Label ("Funktionen");
			dozentGrid.setWidget(2, 0, bearbeiten);
			dozentGrid.setWidget(3, 0, loeschen);
			}
	  
		public void onLoad() {
			
			setTvm();
			getSelectedData();
			
			/**
			 * Definition der Buttons anlegen, lÃ¶schen und bearbeiten
			 */
			anlegen.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
				showCreate();
				}
			});
			
		/**	bearbeiten.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					showChange();
				}
			}); */
			
			loeschen.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event){
					verwaltungsSvc.deleteDozent(shownDozent, new AsyncCallback<Void>() {
						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Der Studiengang konnte nicht gelöscht werden." +
							  		"Er ist in ein oder mehreren Stundenplaneinträgen eingetragen");
						  }

						  @Override
						  public void onSuccess(Void result) {
							  Window.alert ("Erfolgreich gelöscht.");
						  } 	
						});
				  }
			});
	  		this.clear();
		  }

		/**
		 * Zugriff auf die Klasse CreateDozent zum Erstellen eines Dozenten
		 */
		public void showCreate() {
			this.clear();
			this.add(createD);
		}

		/**
		 * Zugriff auf die Klasse ChangeDozent zum Bearbeiten eines Dozenten
		 
		public void showChange() {
			this.clear();
			this.add(changeD);
		}*/

		public void setTvm(TreeViewModel tvm) {
			this.tvm = tvm;
		}
		
		public void getSelectedData(){
			verwaltungsSvc.getDozentbyId(id, new AsyncCallback<Dozent>(){
				@Override
				public void onFailure(Throwable caught) {
				}
				
				@Override
				public void onSuccess(Dozent result) {
					if (result != null) {
						setSelected(result);
					}
				}
			});
		}
		
		public void setSelected(Dozent d){
			if (d != null) {
				shownDozent = d;
				setFields();
			} else {
				clearFields();
			}
		}
		public void setFields(){
			tbvorname.setText(shownDozent.getVorname());
			tbnachname.setText(shownDozent.getNachname());
		}
		public void clearFields(){
			tbvorname.setText("");
			tbnachname.setText("");
		}
}