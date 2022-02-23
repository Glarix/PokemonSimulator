package PokeStuff.Pokemons;

import Items.Item;
import PokeStuff.Ability;
import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Psyduck extends Pokemon {

    public Psyduck(ArrayList<Item> items) {
        super("Psyduck", 43, 3,
                0, 3, 3, items);
        super.setAbility1(new Ability("abilitate 1", 2, false, false, 4, true, false));
        super.setAbility2(new Ability("abilitate 2", 2, true, false, 5, true, false));
    }
}
