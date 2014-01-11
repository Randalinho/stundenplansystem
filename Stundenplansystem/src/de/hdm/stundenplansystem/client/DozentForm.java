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
import de.hdm.stundenplansystem.client.NavTreeViewModel;

/**
 * Formular für die Darstellung des selektierten Kunden
 * 
 * @author Thies, Espich
 *
 */

public class DozentForm extends Content {
	
	private final HTML ueberschrift = new HTML ("<h2>Ãœbersicht der Dozenten<h2>");
	private final HTML ueberschriftAenderung = new HTML ("<h2>Dozenten bearbeiten<h2>");

	  final TextBox tbvorname = new TextBox ();
	  final TextBox tbnachname = new TextBox ();
	  final Label lbnachname = new Label ("Vorname");
	  final Label lbvorname = new Label ("Nachname");
	  final Button bearbeiten = new Button ("Dozent bearbeiten");
	  final Button loeschen = new Button ("Dozent löschen");
	  final Button speichern = new Button ("Änderungen speichern");
	  			  
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	  Dozent shownDozent = null; 
	  NavTreeViewModel tvm = null;
	  
	  public DozentForm() {
		  Grid dozentGrid = new Grid (4, 2);
		    this.add(ueberschrift);
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
			
			bearbeiten.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					showWidget();
				}
			});
			
			  speichern.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {

					  boolean allFilled = true;
				  
					  if (tbnachname.getValue().isEmpty() 
							  ||tbvorname.getValue().isEmpty()) {	
						  allFilled = false;
					  Window.alert ("Bitte fÃ¼llen Sie alle Felder aus."); } 
					  
					  if (allFilled == true) {
						  shownDozent.setNachname(tbnachname.getText().trim());
						  shownDozent.setVorname(tbvorname.getText().trim());
						  						  
						  verwaltungsSvc.changeDozent(shownDozent, new  AsyncCallback<Dozent> () {

							  @Override
							  public void onFailure (Throwable caught) {
								  Window.alert("Der Dozent konnte nicht bearbeitet werden.");
							  }

							  @Override
							  public void onSuccess(Dozent result) {
								  tbnachname.setText("");
								  tbvorname.setText("");
								  tvm.updateDozent(shownDozent);
								  Window.alert ("Erfolgreich gespeichert.");
							  } 	
							});
					  }
				  }
				  }); 
			
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
							  tvm.deleteDozent(shownDozent);
							  Window.alert ("Erfolgreich gelöscht.");
						  } 	
						});
				  }
			});
	  		this.clear();
		  }

		public void setTvm(NavTreeViewModel tvm) {
			this.tvm = tvm;
		}
		
		public void getSelectedData(){
			verwaltungsSvc.getDozentById(dId, new AsyncCallback<Dozent>(){
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
		
		  public void showWidget(){
			  	 this.add(ueberschriftAenderung);
				 this.add(lbnachname);
				 this.add(tbnachname);
				 this.add(lbvorname);
				 this.add(tbvorname);
				 this.add(speichern);
			  }
}