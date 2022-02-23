 /**
 * Runnable class to execute a hit to an opponent pokemon
 */
 package Battle;


import PokeStuff.Ability;
import PokeStuff.Pokemon;

public class PokemonAttack implements Runnable{

    private Pokemon you;
    private Pokemon oponnent;
    private Ability attack;

    public PokemonAttack(Pokemon you, Pokemon opponent, Ability attack) {
        this.you = you;
        this.oponnent = opponent;
        this.attack = attack;
    }


    @Override
    public void run() {
        you.hitOpponent(oponnent,attack);
    }
}
