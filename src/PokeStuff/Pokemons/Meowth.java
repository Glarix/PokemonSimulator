package PokeStuff.Pokemons;

import Items.Item;
import PokeStuff.Ability;
import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Meowth extends Pokemon {

    public Meowth(ArrayList<Item> items) {
        super("Meowth", 41, 3,
                0, 4, 2, items);
        super.setAbility1(new Ability("abilitate 1", 5, false, true, 4, true, false));
        super.setAbility2(new Ability("abilitate 2", 1, false, true, 3, true, false));
    }
}
