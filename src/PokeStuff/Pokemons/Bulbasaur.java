package PokeStuff.Pokemons;

import Items.Item;
import PokeStuff.Ability;
import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Bulbasaur extends Pokemon {

    public Bulbasaur(ArrayList<Item> items) {
        super("Bulbasaur", 42, 0,
                5, 3, 1, items);
        super.setAbility1(new Ability("abilitate 1", 6, false, false, 4, true, false));
        super.setAbility2(new Ability("abilitate 2", 5, false, false, 3, true, false));
    }
}
