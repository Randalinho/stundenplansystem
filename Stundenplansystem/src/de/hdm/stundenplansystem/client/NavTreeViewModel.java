/**
 * 
 */
package de.hdm.stundenplansystem.client;

//import java.util.HashMap;

import java.util.List;
//import java.util.Map;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
//import com.google.gwt.view.client.TreeViewModel.DefaultNodeInfo;

import de.hdm.stundenplansystem.shared.Verwaltungsklasse;
import de.hdm.stundenplansystem.shared.VerwaltungsklasseAsync;
import de.hdm.stundenplansystem.shared.bo.BusinessObjekt;
import de.hdm.stundenplansystem.shared.bo.Dozent;
import de.hdm.stundenplansystem.shared.bo.Lehrveranstaltung;
import de.hdm.stundenplansystem.shared.bo.Raum;
import de.hdm.stundenplansystem.shared.bo.Semesterverband;
import de.hdm.stundenplansystem.shared.bo.Studiengang;
import de.hdm.stundenplansystem.shared.bo.Zeitslot;


/**
 * @author C. Rathke, V. Hofmann
 *
 */
public class NavTreeViewModel extends Content implements TreeViewModel {

	
	private DozentForm df;
	private LehrveranstaltungForm lf;
	private RaumForm rf;
	private ZeitslotForm zf;
	private SemesterverbandForm svf;
	private StudiengangForm sgf;
	
	
	private Dozent selectedDozent = null;
	private Lehrveranstaltung selectedLv = null;
	private Raum selectedRaum = null;
	private Zeitslot selectedZs = null;
	private Semesterverband selectedSv = null;
	private Studiengang selectedSg = null;
	
	private VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	private ListDataProvider<Dozent> dozentDataProvider;
	private ListDataProvider<Lehrveranstaltung> lvDataProvider;
	private ListDataProvider<Raum> raumDataProvider;
	private ListDataProvider<Zeitslot> zsDataProvider;
	private ListDataProvider<Semesterverband> svDataProvider;
	private ListDataProvider<Studiengang> sgDataProvider;
	//private Map<Dozent, ListDataProvider<Lehrveranstaltung>> lvDataProvider = new HashMap<Dozent, ListDataProvider<Lehrveranstaltung>>();

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
	
	 

	public NavTreeViewModel(DozentForm df, LehrveranstaltungForm lf, RaumForm rf, ZeitslotForm zf, StudiengangForm sgf, SemesterverbandForm svf) {
		this.df = df;
		df.setTvm(this);
		this.lf = lf;
		lf.setTvm(this);
		this.rf = rf;
		rf.setTvm(this);
		//this.zf = zf;
		//zf.setTvm(this);
		this.sgf = sgf;
		sgf.setTvm(this);
		this.svf = svf;
		svf.setTvm(this);
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				BusinessObjekt selection = selectionModel.getSelectedObject();
				if (selection instanceof Dozent) {
					setSelectedDozent((Dozent) selection);
				} /*else if (selection instanceof Lehrveranstaltung){
					setSelectedLv((Lehrveranstaltung) selection);
				}*/
				if (selection instanceof Lehrveranstaltung) {
					setSelectedLv((Lehrveranstaltung) selection);
				}
				
				if (selection instanceof Raum) {
					setSelectedRaum((Raum) selection);
				}
				
				/*if (selection instanceof Zeitslot) {
					setSelectedZeitslot((Zeitslot) selection);
				}*/
				
				if (selection instanceof Studiengang) {
					setSelectedSg((Studiengang) selection);
				}
				
				if (selection instanceof Semesterverband) {
					setSelectedSv((Semesterverband) selection);
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
	//	lf.setSelected(null);
	}
	
	Lehrveranstaltung getSelectedLv() {
		return selectedLv;
	}
	
	void setSelectedLv(Lehrveranstaltung lv) {
		selectedLv = lv;
		lf.setSelected(lv);
		//df.setSelected(null);
	}
	
	Raum getSelectedRaum() {
		return selectedRaum;
	}
	
	void setSelectedRaum(Raum r) {
		selectedRaum = r;
		rf.setSelected(r);
	//	lf.setSelected(null);
	}
	
	Zeitslot getSelectedZeitslot() {
		return selectedZs;
	}
	
	/*void setSelectedZeitslot(Zeitslot zs) {
		selectedZs = zs;
		zf.setSelected(zs);
	//	lf.setSelected(null);
	}*/
	
	Studiengang getSelectedSg() {
		return selectedSg;
	}
	
	void setSelectedSg(Studiengang sg) {
		selectedSg = sg;
		sgf.setSelected(sg);
	//	lf.setSelected(null);
	}
	
	Semesterverband getSelectedSv() {
		return selectedSv;
	}
	
	void setSelectedSv(Semesterverband sv) {
		selectedSv = sv;
		svf.setSelected(sv);
	//	lf.setSelected(null);
	}
	
	void addDozent(Dozent dozent) {
		dozentDataProvider.getList().add(dozent);
		//lvDataProvider.put(dozent, new ListDataProvider<Lehrveranstaltung>());
	}
	
	void addRaum(Raum raum) {
		raumDataProvider.getList().add(raum);
		//lvDataProvider.put(dozent, new ListDataProvider<Lehrveranstaltung>());
	}
	
	void addStudiengang(Studiengang studiengang) {
		sgDataProvider.getList().add(studiengang);
		//lvDataProvider.put(dozent, new ListDataProvider<Lehrveranstaltung>());
	}
	
	void addSemesterverband(Semesterverband semesterverband) {
		svDataProvider.getList().add(semesterverband);
		//lvDataProvider.put(dozent, new ListDataProvider<Lehrveranstaltung>());
	}
	
	void addLehrveranstaltung(Lehrveranstaltung lehrveranstaltung) {
		lvDataProvider.getList().add(lehrveranstaltung);
		//lvDataProvider.put(dozent, new ListDataProvider<Lehrveranstaltung>());
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
	
	void updateRaum(Raum raum) {
		List<Raum> raumList = raumDataProvider.getList();
		int i = 0;
		for (Raum r : raumList) {
			if(r.getId() == i) {
				raumList.set(i, raum);
				break;
			} else {
				i++;
			}
		}
	}
	
	void updateLehrveranstaltung(Lehrveranstaltung lehrveranstaltung) {
		List<Lehrveranstaltung> lvList = lvDataProvider.getList();
		int i = 0;
		for (Lehrveranstaltung lv : lvList) {
			if(lv.getId() == i) {
				lvList.set(i, lehrveranstaltung);
				break;
			} else {
				i++;
			}
		}
	}
	
	void updateStudiengang(Studiengang studiengang) {
		List<Studiengang> sgList = sgDataProvider.getList();
		int i = 0;
		for (Studiengang sg : sgList) {
			if(sg.getId() == i) {
				sgList.set(i, studiengang);
				break;
			} else {
				i++;
			}
		}
	}
	
	void updateSemesterverband(Semesterverband semesterverband) {
		List<Semesterverband> svList = svDataProvider.getList();
		int i = 0;
		for (Semesterverband sv : svList) {
			if(sv.getId() == i) {
				svList.set(i, semesterverband);
				break;
			} else {
				i++;
			}
		}
	}
	

	
	void deleteDozent(Dozent dozent) {
		dozentDataProvider.getList().remove(dozent);
		//lvDataProvider.remove(dozent);
	}
	
	void deleteRaum(Raum raum) {
		raumDataProvider.getList().remove(raum);
		//lvDataProvider.remove(dozent);
	}
	
	void deleteLehrveranstaltung(Lehrveranstaltung lehrveranstaltung) {
		lvDataProvider.getList().remove(lehrveranstaltung);
		//lvDataProvider.remove(dozent);
	}
	
	void deleteStudiengang(Studiengang studiengang) {
		sgDataProvider.getList().remove(studiengang);
		//lvDataProvider.remove(dozent);
	}
	
	void deleteSemesterverband(Semesterverband semesterverband) {
		svDataProvider.getList().remove(semesterverband);
		//lvDataProvider.remove(dozent);
	}
	

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value instanceof Dozent) {
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
			
			return new DefaultNodeInfo<Dozent>(dozentDataProvider, new DozentCell(), selectionModel, null);
		}
		
		
		
		if (value instanceof Lehrveranstaltung) {
			lvDataProvider = new ListDataProvider<Lehrveranstaltung>();
			verwaltungsSvc.getAllLehrveranstaltungen(new AsyncCallback<Vector<Lehrveranstaltung>>() {
				public void onFailure(Throwable T) {
					
				}
				
				public void onSuccess(Vector<Lehrveranstaltung> lehrveranstaltungen) {
					for (Lehrveranstaltung lv : lehrveranstaltungen) {
						lvDataProvider.getList().add(lv);
					}
				}
			});
			
			return new DefaultNodeInfo<Lehrveranstaltung>(lvDataProvider, new LehrveranstaltungCell(), selectionModel, null);
		}
		
		if (value instanceof Raum) {
			raumDataProvider = new ListDataProvider<Raum>();
			verwaltungsSvc.getAllRaeume(new AsyncCallback<Vector<Raum>>() {
				public void onFailure(Throwable T) {
					
				}
				
				public void onSuccess(Vector<Raum> raeume) {
					for (Raum r : raeume) {
						raumDataProvider.getList().add(r);
					}
				}
			});
			
			return new DefaultNodeInfo<Raum>(raumDataProvider, new RaumCell(), selectionModel, null);
		}
		
		if (value instanceof Semesterverband) {
			svDataProvider = new ListDataProvider<Semesterverband>();
			verwaltungsSvc.getAllSemesterverbaende(new AsyncCallback<Vector<Semesterverband>>() {
				public void onFailure(Throwable T) {
					
				}
				
				public void onSuccess(Vector<Semesterverband> semesterverbaende) {
					for (Semesterverband sv : semesterverbaende) {
						svDataProvider.getList().add(sv);
					}
				}
			});
			
			return new DefaultNodeInfo<Semesterverband>(svDataProvider, new SemesterverbandCell(), selectionModel, null);
		}
		
		if (value instanceof Studiengang) {
			sgDataProvider = new ListDataProvider<Studiengang>();
			verwaltungsSvc.getAllStudiengaenge(new AsyncCallback<Vector<Studiengang>>() {
				public void onFailure(Throwable T) {
					
				}
				
				public void onSuccess(Vector<Studiengang> studiengaenge) {
					for (Studiengang sg : studiengaenge) {
						sgDataProvider.getList().add(sg);
					}
				}
			});
			
			return new DefaultNodeInfo<Studiengang>(sgDataProvider, new StudiengangCell(), selectionModel, null);
		}
		
		/*if (value instanceof Zeitslot) {
			zsDataProvider = new ListDataProvider<Zeitslot>();
			verwaltungsSvc.getAllZeitslots(new AsyncCallback<Vector<Zeitslot>>() {
				public void onFailure(Throwable T) {
					
				}
				
				public void onSuccess(Vector<Zeitslot> zeitslots) {
					for (Zeitslot zs : zeitslots) {
						zsDataProvider.getList().add(zs);
					}
				}
			});
			
			return new DefaultNodeInfo<Zeitslot>(zsDataProvider, new ZeitslotCell(), selectionModel, null);
		}*/
		
		
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		return false;
	}
	
	

}
