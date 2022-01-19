package domain;

import domain.abilities.Ability;
import domain.abilities.AbilityFactory;
import domain.abilities.AbilityType;
import domain.abilities.UsefulAbilityType;
import domain.physicalobjects.engines.AbilityEngine;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String username;
    private int lives;
    private double score;
    private List<AbilityType> abilities;

    public Player(){
        this.abilities = new ArrayList<>();
        this.score = 0;
        this.lives = Constants.PLAYER_INITIAL_LIVES;
    }

    public int getLives() {
        return lives;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public List<AbilityType> getAbilities() {
        return new ArrayList<>(abilities);
    }

    public void addAbility(AbilityType abilityType){
        if (abilityType == AbilityType.ChanceGivingAbility) {
            setLives(lives + 1);
        }else if (abilityType == AbilityType.UnstoppableBallAbility){
            abilities.add(abilityType);
            Game.getInstance().useAbility(AbilityType.UnstoppableBallAbility);
        } else {
            abilities.add(abilityType);
        }
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) { this.username = username; }

    public boolean removeAbility(AbilityType type) {
        AbilityType a = abilities.stream().filter(ability -> ability == type).findFirst().orElse(null);
        if (a == null) return false;
        else return abilities.remove(a);
    }
}
