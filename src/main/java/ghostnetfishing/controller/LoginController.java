package ghostnetfishing.controller;

import java.io.Serializable;
import java.util.List;

import ghostnetfishing.dao.PersonDao;
import ghostnetfishing.entities.Person;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private String login;
	private String password;
	private Person loggedInPerson;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Person getLoggedInPerson() {
		return loggedInPerson;
	}

	public void setLoggedInPerson(Person loggedInPerson) {
		this.loggedInPerson = loggedInPerson;
	}

	@PostConstruct
	public void startup() {
		List<Person> persons = PersonDao.findAll();
		if(persons.isEmpty()) {
			// erstelle Dummy User
			PersonDao.save(Person.build("Hans Huber", "+49821123456", "hans", "hansSecret"));
			PersonDao.save(Person.build("Peter Mayer", "+49821789456", "peter", "peterSecret"));
			PersonDao.save(Person.build("Rudi Müller", "+49821775566", "rudi", "rudiSecret"));
			System.out.println("3 Personen erstellt.");
		} else {
			System.out.println(persons.size() + " Personen vorhanden.");
		}
	}

	public String doLogin() {
		Person person = PersonDao.findByLoginAndPassword(this.login, this.password);
		this.password = "";
		if(person != null) {
			this.loggedInPerson = person;
			return "my-salvages.xhtml";
		}
		return "login.xhtml";
	}
	
	public String doLogout() {
		this.loggedInPerson = null;
		this.login = "";
		this.password = "";
		return "login.xhtml";
	}
}
