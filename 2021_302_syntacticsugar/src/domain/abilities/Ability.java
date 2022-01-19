package domain.abilities;

import domain.physicalobjects.PhysicalObject;
import domain.services.Service;
import domain.services.ServiceAttachable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Ability extends ServiceAttachable {

	private PhysicalObject appliesTo;
	private ArrayList<PhysicalObject> physicalObjectsList;

	public Ability(PhysicalObject appliesTo){
		this(appliesTo, null);
	}

	public Ability(PhysicalObject appliesTo, List<Service> services){
		super(new ArrayList<Service>());
		this.appliesTo = appliesTo;
	}

	public Ability(ArrayList<PhysicalObject> physicalObjects){
		super(new ArrayList<Service>());
		this.physicalObjectsList = physicalObjects;
	}

	public ArrayList<PhysicalObject> getPhysicalObjectsList(){
		return physicalObjectsList;
	}

	public abstract void perform();
	public abstract void revert();

	public PhysicalObject getAppliesTo() {
		return appliesTo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ability ability = (Ability) o;
		return Objects.equals(appliesTo, ability.appliesTo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(appliesTo);
	}
}
