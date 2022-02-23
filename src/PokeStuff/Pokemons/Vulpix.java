package PokeStuff.Pokemons;

import Items.Item;
import PokeStuff.Ability;
import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Vulpix extends Pokemon {

    public Vulpix(ArrayList<Item> items) {
        super("Vulpix", 36, 5,
                0, 2, 4, items);
        super.setAbility1(new Ability("abilitate 1", 8, true, false, 6, true, false));
        super.setAbility2(new Ability("abilitate 2", 2, false, true, 7, true, false));
    }
}
