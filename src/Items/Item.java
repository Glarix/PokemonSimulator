/**
 * Class to represent an Item
 */
package Items;

public class Item {

    private String name;
    private int HPBonus;
    private int attackBonus;
    private int specialAttackBonus;
    private int defenceBonus;
    private int specilaDefenceBonus;

    public Item(String name, int HPBonus, int attackBonus,
                int specialAttackBonus, int defenceBonus, int specilaDefenceBonus) {
        this.name = name;
        this.HPBonus = HPBonus;
        this.attackBonus = attackBonus;
        this.specialAttackBonus = specialAttackBonus;
        this.defenceBonus = defenceBonus;
        this.specilaDefenceBonus = specilaDefenceBonus;
    }

    public String getName() {
        return name;
    }

    public int getHPBonus() {
        return HPBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getSpecialAttackBonus() {
        return specialAttackBonus;
    }

    public int getDefenceBonus() {
        return defenceBonus;
    }

    public int getSpecilaDefenceBonus() {
        return specilaDefenceBonus;
    }

    /**
     * Method used for .contains() method of ArrayList
     * @param obj the object to compare
     * @return true if objects are equal / false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this.name.equals(((Item)obj).getName()))
            return true;
        return false;
    }
}
