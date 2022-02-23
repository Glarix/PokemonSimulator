/**
 * Class to represent a generic pokemon
 */
package PokeStuff;

import Items.Item;

import java.util.ArrayList;

public abstract class Pokemon implements Cloneable{

    private String name;
    private int HP;
    private int normalAttack;
    private int specialAttack;
    private int def;
    private int specialDef;
    private Ability ability1;
    private Ability ability2;
    private ArrayList<Item> items;
    private int baseHP;
    private boolean stunned;
    private boolean dodge;

    public Pokemon(String name, int HP, int normalAttack,
                   int specialAttack, int def, int specialDef,
                   ArrayList<Item> items) {
        this.name = name;
        this.HP = HP;
        this.normalAttack = normalAttack;
        this.specialAttack = specialAttack;
        this.def = def;
        this.specialDef = specialDef;
        this.items = items;
        this.baseHP = HP;
        this.stunned = false;
        this.dodge = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setNormalAttack(int normalAttack) {
        this.normalAttack = normalAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setSpecialDef(int specialDef) {
        this.specialDef = specialDef;
    }

    public void setAbility1(Ability ability1) {
        this.ability1 = ability1;
    }

    public void setAbility2(Ability ability2) {
        this.ability2 = ability2;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public int getNormalAttack() {
        return normalAttack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getDef() {
        return def;
    }

    public int getSpecialDef() {
        return specialDef;
    }

    public Ability getAbility1() {
        return ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public boolean isStunned() {
        return stunned;
    }

    public boolean isDodge() {
        return dodge;
    }

    public boolean Alive() {
        if (this.HP > 0)
            return true;
        return false;
    }

    /**
     * Method to apply an item on a pokemon
     * @param item the item to be applied
     */
    public void applyItemBuffs(Item item) {
        this.HP += item.getHPBonus();
        this.baseHP += item.getHPBonus();
        if (this.normalAttack != 0)
            this.normalAttack += item.getAttackBonus();
        if (this.specialAttack != 0)
            this.specialAttack += item.getSpecialAttackBonus();
        if (this.def != 0)
            this.def += item.getDefenceBonus();
        if (this.specialDef != 0)
            this.specialDef += item.getSpecilaDefenceBonus();
    }

    /**
     * Method to simulate a hit with a specific attack type
     * @param opponent the pokemon to be attacked
     * @param attack the type of attack to be executed
     */
    public void hitOpponent(Pokemon opponent, Ability attack) {
        // Reduce the cooldown of the abilities if any
        if (this.ability1 != null && this.ability1.getCurrentCd() > 0)
            this.ability1.setCurrentCd(this.ability1.getCurrentCd() - 1);
        if (this.ability2 != null && this.ability2.getCurrentCd() > 0)
            this.ability2.setCurrentCd(this.ability2.getCurrentCd() - 1);
        // in case pokemon is stunned he does not attack
        if (attack == null) {
            return;
        }

        // checking if attack is an ability or a simple attack
        if (attack.isRealAbility()) {
            opponent.setHP(opponent.getHP() - attack.getDmg());
            if (attack.isStun())
                opponent.setStunned(true);
            attack.setCurrentCd(attack.getCd());
        } else {
            if (attack.isSpecialAttack()) {
                int damageDealt = attack.getDmg() - opponent.getSpecialDef();
                if (damageDealt < 0)
                    damageDealt = 0;
                opponent.setHP(opponent.getHP() - damageDealt);
            }
            else {
                int damageDealt = attack.getDmg() - opponent.getDef();
                if (damageDealt < 0)
                    damageDealt = 0;
                opponent.setHP(opponent.getHP() - damageDealt);
            }

        }

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
