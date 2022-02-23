/**
 * Class to represent a coach object
 */
package Battle;

import PokeStuff.Pokemon;

import java.util.ArrayList;

public class Coach {

    private String name;
    private int age;
    private ArrayList<Pokemon> pokemons;

    public Coach(String name, int age, ArrayList<Pokemon> pokemons) {
        this.name = name;
        this.age = age;
        this.pokemons = pokemons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }


}
