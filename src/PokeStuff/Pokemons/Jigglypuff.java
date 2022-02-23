package PokeStuff.Pokemons;

import Items.Item;
import PokeStuff.Ability;
import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Jigglypuff extends Pokemon {

    public Jigglypuff(ArrayList<Item> items) {
        super("Jigglypuff", 34, 4,
                0, 2, 3, items);
        super.setAbility1(new Ability("abilitate 1", 4, true, false, 4, true, false));
        super.setAbility2(new Ability("abilitate 2", 3, true, false, 4, true, false));
    }
}
