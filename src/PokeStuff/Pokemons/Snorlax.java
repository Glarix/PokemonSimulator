package PokeStuff.Pokemons;

import Items.Item;
import PokeStuff.Ability;
import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Snorlax extends Pokemon {

    public Snorlax(ArrayList<Item> items) {
        super("Snorlax", 62, 3,
                0, 6, 4, items);
        super.setAbility1(new Ability("abilitate 1", 4, true, false, 5, true, false));
        super.setAbility2(new Ability("abilitate 2", 0, false, true, 5, true, false));
    }
}
