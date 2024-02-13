package ghostnetfishing.controller;

import java.io.Serializable;

import ghostnetfishing.dao.GhostNetDao;
import ghostnetfishing.dao.PersonDao;
import ghostnetfishing.entities.GhostNet;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ReportController implements Serializable {

	private String reporterName;
	private String reporterPhone;
	private String locationLatitude;
	private String locationLongitude;
	private String size;
	
	
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	public String getReporterPhone() {
		return reporterPhone;
	}
	public void setReporterPhone(String reporterPhone) {
		this.reporterPhone = reporterPhone;
	}
	public String getLocationLatitude() {
		return locationLatitude;
	}
	public void setLocationLatitude(String locationLatitude) {
		this.locationLatitude = locationLatitude;
	}
	public String getLocationLongitude() {
		return locationLongitude;
	}
	public void setLocationLongitude(String locationLongitude) {
		this.locationLongitude = locationLongitude;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public String reportGhostNet() {
		GhostNet net = GhostNet.build(this.reporterName, this.reporterPhone, this.locationLatitude, this.locationLongitude, this.size);
		if(net.getReporter() != null) {
			PersonDao.save(net.getReporter());
		}
		GhostNetDao.save(net);
		
		this.reporterName = "";
		this.reporterPhone = "";
		this.locationLatitude = "";
		this.locationLongitude = "";
		this.size = "";
		
		return "login.xhtml";
	}
}
