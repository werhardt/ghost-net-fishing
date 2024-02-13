package ghostnetfishing.entities;

import ghostnetfishing.helper.AppConstants;
import ghostnetfishing.helper.Utils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GhostNet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String locationLatitude;
	private String locationLongitude;
	private String size;
	private int status;
	@ManyToOne(optional = true)
	private Person salvager;
	@ManyToOne(optional = true)
	private Person reporter;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Person getSalvager() {
		return salvager;
	}

	public void setSalvager(Person salvager) {
		this.salvager = salvager;
	}

	public Person getReporter() {
		return reporter;
	}

	public void setReporter(Person reporter) {
		this.reporter = reporter;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public String getReporterName() {
		if(this.reporter != null && Utils.isFilled(this.reporter.getName())) {
			return this.reporter.getName();
		}
		return "";
	}
	
	public String getReporterPhone() {
		if(this.reporter != null && Utils.isFilled(this.reporter.getPhone())) {
			return this.reporter.getPhone();
		}
		return "";
	}
	
	public String getSalvagerName() {
		if(this.salvager != null && Utils.isFilled(this.salvager.getName())) {
			return this.salvager.getName();
		}
		return "";
	}
	
	public String getSalvagerPhone() {
		if(this.salvager != null && Utils.isFilled(this.salvager.getPhone())) {
			return this.salvager.getPhone();
		}
		return "";
	}
	
	public static GhostNet build(String reporterName, String reporterPhone, 
			String locationLatitude, String locationLongitude, String size) {
		GhostNet net = new GhostNet();
		net.setLocationLatitude(locationLatitude);
		net.setLocationLongitude(locationLongitude);
		net.setSize(size);
		net.setStatus(AppConstants.GHOSTNET_STATUS_REPORTED);
		if(Utils.isFilled(reporterName) || Utils.isFilled(reporterPhone)) {
			Person reporter = Person.build(reporterName, reporterPhone, "", "");
			net.setReporter(reporter);
		}
		return net;
	}
}
