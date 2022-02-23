/**
 * Class to represent an ItemBuilder pattern
 */
package Items;

public class ItemBuilder {

    // Using Builder Pattern

    private String name;
    private int HPBonus;
    private int attackBonus;
    private int specialAttackBonus;
    private int defenceBonus;
    private int specilaDefenceBonus;


    public ItemBuilder(String name) {
        this.name = name;
    }

    public ItemBuilder withHPBonus(int HPBonus) {
        HPBonus = checkAttribute(HPBonus);
        this.HPBonus = HPBonus;
        return this;
    }

    public ItemBuilder withAttackBonus(int attackBonus) {
        attackBonus = checkAttribute(attackBonus);
        this.attackBonus = attackBonus;
        return this;
    }

    public ItemBuilder withSpecialAttackBonus(int specialAttackBonus) {
        specialAttackBonus = checkAttribute(specialAttackBonus);
        this.specialAttackBonus = specialAttackBonus;
        return this;
    }

    public ItemBuilder withDefenceBonus(int defenceBonus) {
        defenceBonus = checkAttribute(defenceBonus);
        this.defenceBonus = defenceBonus;
        return this;
    }

    public ItemBuilder withSpecialDefenceBonus(int specialDefenceBonus) {
        specialDefenceBonus = checkAttribute(specialDefenceBonus);
        this.specilaDefenceBonus = specialDefenceBonus;
        return this;
    }

    /**
     * Method to build the final version of item
     * @return the item
     */
    public Item build() {
        if (validateItem(this)) {
            return new Item(this.name, this.HPBonus, this.attackBonus,
                            this.specialAttackBonus, this.defenceBonus,
                            this.specilaDefenceBonus);
        }
        throw new IllegalArgumentException();
    }

    /**
     * Method to check validity of an attribute
     * @param attribute the attribute
     * @return attribute's valid value
     */
    private int checkAttribute(int attribute) {
        if (attribute < 0)
            attribute = 0;
        return attribute;
    }

    /**
     * Method to validate the item
     * @param ib the item builder
     * @return false if item doesn't have a name / true if it does
     */
    private boolean validateItem(ItemBuilder ib) {
        if (ib.name == null)
            return false;
        return true;
    }


}
