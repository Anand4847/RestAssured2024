package PojoDeserialize;

public class GetCourses {

// For creating getter and setter methods in a shortcut manner then select all private variables
	// and then select alt+shift+s, and then select org getter and setters, select
	// all the methods
	// and then click on generate;

	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses courses;
	private String linkedIn;

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public String getlinkedIn() {
		return linkedIn;
	}

	public void setlinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

}
