package forfun;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import forfun.MyModel.Person;

public class ObserverPattern {
	public static void main(String[] args) {
		MyModel model = new MyModel();
		MyObserver observer = new MyObserver(model);
		MyObserver observer2 = new MyObserver(model);
		MyObserver observer3 = new MyObserver(model);
		// we change the last name of the person, observer will get notified
		for (Person person : model.getPersons()) {
			person.setLastName(person.getLastName() + "1");
		}
		// we change the name of the person, observer will get notified
		for (Person person : model.getPersons()) {
			person.setFirstName(person.getFirstName() + "1");
		}
	}
}

class MyModel {
	public static final String FIRSTNAME = "firstname";
	public static final String LASTNAME = "lastname";

	private List<Person> persons = new ArrayList<Person>();
	private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

	public List<Person> getPersons() {
		return this.persons;
	}

	public MyModel() {
		persons.add(new Person("Shan", "Ye"));
		persons.add(new Person("Test", "XY"));
	}

	private void notifyListeners(Object object, String property,
			String oldValue, String newValue) {
		for (PropertyChangeListener name : listener) {
			name.propertyChange(new PropertyChangeEvent(this, property,
					oldValue, newValue));
		}
	}

	public void addChangeListener(PropertyChangeListener newListener) {
		this.listener.add(newListener);
	}

	class Person {
		private String firstName;
		private String lastName;

		public Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName() {
			return this.firstName;
		}

		public String getLastName() {
			return this.lastName;
		}

		public void setFirstName(String firstName) {
			notifyListeners(this, FIRSTNAME, this.firstName,
					this.firstName = firstName);
		}

		public void setLastName(String lastName) {
			notifyListeners(this, LASTNAME, this.lastName,
					this.lastName = lastName);
		}
	}
}

class MyObserver implements PropertyChangeListener {

	public MyObserver(MyModel myModel) {
		myModel.addChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("Change Property : " + event.getPropertyName()
				+ " [old -> " + event.getOldValue() + "] | [new ->"
				+ event.getNewValue() + "]");
	}
}
