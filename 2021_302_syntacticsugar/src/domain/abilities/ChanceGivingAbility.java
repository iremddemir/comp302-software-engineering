package domain.abilities;

import java.util.ArrayList;

import domain.physicalobjects.PhysicalObject;

public class ChanceGivingAbility extends Ability {
	public ChanceGivingAbility(PhysicalObject appliesTo) {
		super(appliesTo);
	}

	public void perform() {
		
	}

	@Override
	public void revert() {

	}
	
	/*private boolean isActive;
	private AbilityType type;
	
	public ChanceGivingAbility(AbilityType type, boolean isActive) {
		super(type,isActive);
	}
	public void perform(ArrayList<PhysicalObject> physicalObjects) {
		
	}*/

}
