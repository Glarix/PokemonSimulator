package PokeStuff.Pokemons;

import Items.Item;
import PokeStuff.Ability;
import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Eevee extends Pokemon {

    public Eevee(ArrayList<Item> items) {
        super("Eevee", 39, 0,
                4, 3, 3, items);
        super.setAbility1(new Ability("abilitate 1", 5, false, false, 3, true, false));
        super.setAbility2(new Ability("abilitate 2", 3, true, false, 3, true, false));
    }
}
