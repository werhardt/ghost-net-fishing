package ghostnetfishing.controller;

import java.io.Serializable;
import java.util.List;

import ghostnetfishing.dao.GhostNetDao;
import ghostnetfishing.dao.PersonDao;
import ghostnetfishing.entities.GhostNet;
import ghostnetfishing.entities.Person;
import ghostnetfishing.helper.AppConstants;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class SummaryController implements Serializable {

	public List<GhostNet> getActiveSalvages(Person person) {
		return GhostNetDao.findBySalvagerId(person.getId());
	}
	
	public List<GhostNet> getReportedNets() {
		return GhostNetDao.findByStatus(AppConstants.GHOSTNET_STATUS_REPORTED);
	}
	
	public List<GhostNet> getPendingNets(int salvagerId) {
		return GhostNetDao.findPendingNets(salvagerId);
	}
	
	public String reportNetSalvaged(int netId) {
		GhostNet net = GhostNetDao.findById(netId);
		net.setStatus(AppConstants.GHOSTNET_STATUS_SALVAGED);
		GhostNetDao.update(net);
		return "my-salvages.xhtml";
	}
	
	public String reportSalvagePending(int netId, int salvagerId) {
		GhostNet net = GhostNetDao.findById(netId);
		Person person = PersonDao.findById(salvagerId);
		net.setStatus(AppConstants.GHOSTNET_STATUS_PENDING);
		net.setSalvager(person);
		GhostNetDao.update(net);
		return "open-salvages.xhtml";
	}
}
