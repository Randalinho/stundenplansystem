/**
 * 
 */
package de.hdm.stundenplansystem.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.TreeViewModel.DefaultNodeInfo;

import de.hdm.stundenplansystem.shared.Verwaltungsklasse;
import de.hdm.stundenplansystem.shared.VerwaltungsklasseAsync;
import de.hdm.stundenplansystem.shared.bo.BusinessObjekt;
import de.hdm.stundenplansystem.shared.bo.Dozent;
import de.hdm.stundenplansystem.shared.bo.Lehrveranstaltung;


/**
 * @author C. Rathke, V. Hofmann
 *
 */
public class NavTreeViewModel extends Content implements TreeViewModel {

	
	private DozentForm df;
	private LehrveranstaltungForm lf;
	
	private Dozent selectedDozent = null;
	private Lehrveranstaltung selectedLv = null;
	
	private VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	private ListDataProvider<Dozent> dozentDataProvider;
	private Map<Dozent, ListDataProvider<Lehrveranstaltung>> lvDataProvider = new HashMap<Dozent, ListDataProvider<Lehrveranstaltung>>();

	private ProvidesKey<BusinessObjekt> boKeyProvider = new ProvidesKey<BusinessObjekt>() {
		public Integer getKey(BusinessObjekt bo) {
			if (bo == null) {
				return null;
			}
			if (bo instanceof Dozent) {
				return new Integer(bo.getId());
			} else {
				return new Integer(-bo.getId());
			}
		}
	};
	
	private SingleSelectionModel <BusinessObjekt> selectionModel = new SingleSelectionModel<BusinessObjekt>(boKeyProvider);
	
	 

	public NavTreeViewModel(DozentForm df, LehrveranstaltungForm lf) {
		this.df = df;
		df.setTvm(this);
		this.lf = lf;
		lf.setTvm(this);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				BusinessObjekt selection = selectionModel.getSelectedObject();
				if (selection instanceof Dozent) {
					setSelectedDozent((Dozent) selection);
				} else if (selection instanceof Lehrveranstaltung){
					setSelectedLv((Lehrveranstaltung) selection);
				}
			}
		});
	}
	
	Dozent getSelectedDozent() {
		return selectedDozent;
	}
	
	void setSelectedDozent(Dozent d) {
		selectedDozent = d;
		df.setSelected(d);
		lf.setSelected(null);
	}
	
	Lehrveranstaltung getSelectedLv() {
		return selectedLv;
	}
	
	void setSelectedLv(Lehrveranstaltung lv) {
		selectedLv = lv;
		lf.setSelected(lv);
		df.setSelected(null);
	}
	
	void addDozent(Dozent dozent) {
		dozentDataProvider.getList().add(dozent);
		lvDataProvider.put(dozent, new ListDataProvider<Lehrveranstaltung>());
	}
	
	void updateDozent(Dozent dozent) {
		List<Dozent> dozentList = dozentDataProvider.getList();
		int i = 0;
		for (Dozent d : dozentList) {
			if(d.getId() == i) {
				dozentList.set(i, dozent);
				break;
			} else {
				i++;
			}
		}
	}
	
	void removeDozent(Dozent dozent) {
		dozentDataProvider.getList().remove(dozent);
		lvDataProvider.remove(dozent);
	}
	

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value instanceof String) {
			dozentDataProvider = new ListDataProvider<Dozent>();
			verwaltungsSvc.getAllDozenten(new AsyncCallback<Vector<Dozent>>() {
				public void onFailure(Throwable T) {
					
				}
				
				public void onSuccess(Vector<Dozent> dozenten) {
					for (Dozent d : dozenten) {
						dozentDataProvider.getList().add(d);
					}
				}
			});
			
			//return new DefaultNodeInfo<Dozent>(dozentDataProvider, new DozentCell(), selectionModel, null);
		}
		
		/*if (value instanceof Dozent) {
			// Erzeugen eines ListDataproviders für Account-Daten
			final ListDataProvider<Lehrveranstaltung> lvProvider = new ListDataProvider<Lehrveranstaltung>();
			lvDataProvider.put((Dozent) value, lvProvider);

			verwaltungsSvc.getLvOf((Dozent) value,
					new AsyncCallback<Vector<Lehrveranstaltung>>() {
						public void onFailure(Throwable t) {
						}

						public void onSuccess(Vector<Lehrveranstaltung> lvs) {
							for (Lehrveranstaltung lv : lvs) {
								lvProvider.getList().add(lv);
							}
						}
					});

			// Return a node info that pairs the data with a cell.
			return new DefaultNodeInfo<Lehrveranstaltung>(lvProvider,
					new LvCell(), selectionModel, null);
		}*/
		
		
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		return false;
	}
	
	

}
