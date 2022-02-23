/**
 * Utilities class
 */
package Util;

import Items.Item;
import Items.ItemBuilder;
import PokeStuff.Pokemon;

import java.util.Locale;

public abstract class Utils {

    /**
     * Builder method to create an item
     * @param name the name of the item
     * @return an Item object
     */
    public static Item createItem(String name) {
        Item item = null;
        name = name.toUpperCase(Locale.ROOT);

        switch (name) {
            case "SCUT":
                item = new ItemBuilder(name)
                        .withDefenceBonus(2)
                        .withSpecialDefenceBonus(2)
                        .build();
                break;
            case "VESTA":
                item = new ItemBuilder(name)
                        .withHPBonus(10)
                        .build();
                break;
            case "SABIUTA":
                item = new ItemBuilder(name)
                        .withAttackBonus(3)
                        .build();
                break;
            case "BAGHETA MAGICA":
                item = new ItemBuilder(name)
                        .withSpecialAttackBonus(3)
                        .build();
                break;
            case "VITAMINE":
                item = new ItemBuilder(name)
                        .withHPBonus(2)
                        .withAttackBonus(2)
                        .withSpecialAttackBonus(2)
                        .build();
                break;
            case "BRAD DE CRACIUN":
                item = new ItemBuilder(name)
                        .withAttackBonus(3)
                        .withDefenceBonus(1)
                        .build();
                break;
            case "PELERINA":
                item = new ItemBuilder(name)
                        .withSpecialDefenceBonus(3)
                        .build();
                break;
            default:
                item = null;
        }
        return item;
    }

    /**
     * Method to buff the stats of a winner pokemon
     * @param winnerPokemon the pokemon to be buffed
     */
    public static Pokemon buffWinner(Pokemon winnerPokemon) {
        winnerPokemon.setBaseHP(winnerPokemon.getBaseHP() + 1);
        winnerPokemon.setHP(winnerPokemon.getBaseHP());
        if (winnerPokemon.getNormalAttack() != 0)
            winnerPokemon.setNormalAttack(winnerPokemon.getNormalAttack() + 1);
        if (winnerPokemon.getSpecialAttack() != 0)
            winnerPokemon.setSpecialAttack(winnerPokemon.getSpecialAttack() + 1);
        if (winnerPokemon.getDef() != 0)
            winnerPokemon.setDef(winnerPokemon.getDef() + 1);
        if (winnerPokemon.getSpecialDef() != 0)
            winnerPokemon.setSpecialDef(winnerPokemon.getSpecialDef() + 1);
        winnerPokemon.setDodge(false);
        winnerPokemon.setStunned(false);
        return winnerPokemon;
    }

    /**
     * Method to reset the stats of a pokemon
     * @param pokemon the pokemon to be reset
     */
    public static void resetPokemon(Pokemon pokemon) {
        pokemon.setHP(pokemon.getBaseHP());
        if (pokemon.getAbility1() != null)
            pokemon.getAbility1().setCurrentCd(0);
        if (pokemon.getAbility2() != null)
            pokemon.getAbility2().setCurrentCd(0);
        pokemon.setStunned(false);
        pokemon.setDodge(false);
    }
}
