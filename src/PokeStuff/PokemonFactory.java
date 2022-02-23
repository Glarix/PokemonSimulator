/**
 * Factory class responsible for pokemon creation
 */
package PokeStuff;

import Items.Item;
import PokeStuff.Pokemons.*;

import java.util.ArrayList;
import java.util.Locale;

public class PokemonFactory {

    /**
     * Method to create a specific type of pokemon
     * @param pokeName the type of the pokemon
     * @param items list of pokemon's items
     * @return the newly created pokemon
     */
    public Pokemon createPokemon(String pokeName, ArrayList<Item> items) {
        pokeName = pokeName.toUpperCase(Locale.ROOT);

        switch (pokeName) {
            case "NEUTREL1": return new Neutrel1(items);
            case "NEUTREL2": return new Neutrel2(items);
            case "BULBASAUR": return new Bulbasaur(items);
            case "CHARMANDER": return new Charmander(items);
            case "EEVEE": return new Eevee(items);
            case "JIGGLYPUFF": return new Jigglypuff(items);
            case "MEOWTH": return new Meowth(items);
            case "PIKACHU": return new Pikachu(items);
            case "PSYDUCK": return new Psyduck(items);
            case "SNORLAX": return new Snorlax(items);
            case "VULPIX": return new Vulpix(items);
            case "SQUIRTLE": return new Squirtle(items);
            default: return null;
        }
    }
}
