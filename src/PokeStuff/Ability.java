/**
 * Class to represent an ability
 */
package PokeStuff;

public class Ability {
    private String name;
    private int dmg;
    private boolean stun;
    private boolean dodge;
    private int cd;
    private int currentCd;
    private boolean realAbility;
    private boolean specialAttack;

    public Ability(String name, int dmg, boolean stun, boolean dodge, int cd, boolean realAbility, boolean specialAttack) {
        this.name = name;
        this.dmg = dmg;
        this.stun = stun;
        this.dodge = dodge;
        this.cd = cd;
        this.realAbility = realAbility;
        this.specialAttack = specialAttack;
        this.currentCd = 0;
    }

    public String getName() {
        return name;
    }

    public int getDmg() {
        return dmg;
    }

    public boolean isStun() {
        return stun;
    }

    public boolean isDodge() {
        return dodge;
    }

    public int getCd() {
        return cd;
    }

    public int getCurrentCd() {
        return currentCd;
    }

    public boolean isRealAbility() {
        return realAbility;
    }

    public boolean isSpecialAttack() {
        return specialAttack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public void setCurrentCd(int currentCd) {
        this.currentCd = currentCd;
    }

    public void setRealAbility(boolean realAbility) {
        this.realAbility = realAbility;
    }

    public void setSpecialAttack(boolean specialAttack) {
        this.specialAttack = specialAttack;
    }
}
