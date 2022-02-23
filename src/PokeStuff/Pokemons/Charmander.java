package PokeStuff.Pokemons;

import Items.Item;
import PokeStuff.Ability;
import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Charmander extends Pokemon {

    public Charmander(ArrayList<Item> items) {
        super("Charmander", 50, 4,
                0, 3, 2, items);
        super.setAbility1(new Ability("abilitate 1", 4, true, false, 4, true, false));
        super.setAbility2(new Ability("abilitate 2", 7, false, false, 6, true, false));
    }
}
