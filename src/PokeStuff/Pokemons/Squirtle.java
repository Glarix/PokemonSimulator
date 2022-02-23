package PokeStuff.Pokemons;

import Items.Item;
import PokeStuff.Ability;
import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Squirtle extends Pokemon {

    public Squirtle(ArrayList<Item> items) {
        super("Squirtle", 60, 0,
                3, 5, 5, items);
        super.setAbility1(new Ability("abilitate 1", 4, false, false, 3, true, false));
        super.setAbility2(new Ability("abilitate 2", 2, true, false, 2, true, false));
    }
}
