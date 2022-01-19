package domain.listeners;

import domain.abilities.Ability;

public class AbilityEvent {
    private Ability ability;
    private boolean isActive;

    public AbilityEvent(Ability ability, boolean isActive){
        this.ability = ability;
        this.isActive = isActive;
    }

    public Ability getAbility() {
        return ability;
    }

    public boolean isActive() {
        return isActive;
    }
}
