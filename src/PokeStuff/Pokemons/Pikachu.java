package PokeStuff.Pokemons;

import Items.Item;
import PokeStuff.Ability;
import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Pikachu extends Pokemon {

    public Pikachu(ArrayList<Item> items) {
        super("Pikachu", 35, 0,
                4, 2, 3, items);
        super.setAbility1(new Ability("abilitate 1", 6, false, false, 4, true, false));
        super.setAbility2(new Ability("abilitate 2", 4, true, true, 5, true, false));
    }
}
