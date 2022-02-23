import Battle.Arena;
import Battle.Coach;

import java.util.ArrayList;

public class Play {


    public static void main(String[] args) {
        String logType = null;

        if (args.length > 0) {
            logType = args[0];
        }

        ArrayList<Coach> coaches = InitializeAdventure.createCoaches("C:\\Users\\Danu\\Desktop\\Anul2\\POO\\PROIECTPOKEMON\\Tests\\RandomTest5.txt");
        Arena fightingArena = Arena.Instance(coaches.get(0), coaches.get(1));
        InitializeAdventure.applyAllItemBuffs(coaches.get(0));
        InitializeAdventure.applyAllItemBuffs(coaches.get(1));

        fightingArena.adventure(logType);
    }
}
