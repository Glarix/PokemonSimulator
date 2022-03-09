# Proiect POO 2022 Pokemooooni

### Codreanu Dan 321CB
---

## Description
This project was built as an end of semester project. It simulates the battles between two pokemon coaches. Each coach has 3 unique pokemons that can battle between themselves.

Program Features:

* Three types of battles
    - Vs. Neutrel1
    - Vs. Neutrel2
    - Vs. other Pokemon

* Three different type of attacks
    - Normal/Special attack
    - Ability 1
    - Ability 2

* Posibility of various item buffs

* MultiThreaded battles

* Design Patterns Implementation
---

## Functionality

The information about the two coaches are read from file and the Coaches are created:

```Java
ArrayList<Coach> coaches = InitializeAdventure.createCoaches(FilePath);
```

Before the battle starts, all pokemons recieve their item buffs:

```Java
InitializeAdventure.applyAllItemBuffs(coaches.get(0));
```

After all these preparations an arena can be created for the two coaches to duel and the adventure started:

```Java
Arena fightingArena = Arena.Instance(coaches.get(0), coaches.get(1));

fightingArena.adventure(logType);
```
A logger is implemented to document all results from every fight and it has two working mods:

* Log to console (System.out) - this method is used when there are no parameters in command line at run

* Log to file - when the program has a command line argument the path to the file in which the battles will be logged

.adventure() is the main method that executes all the battles and decides the winner.

---
## Running the program
* With no command line arguments for Console logging
* With path to the logging file for logging results in a file

---
## Core battle logic

An adventure consists of 4 battles.
In the first 3, pokemons are dueling by their number in list and they can battle against Neutrei and it concludes when big pokemons battle against each other. The 4th battle is special because it is between the strongest Pokemons from each coach.

### Battle mechanic

For battle type 1 and 2 the "big" pokemons go against a Neutrel type pokemon. Both battles are happening at the same time using threads and then both are logged. The 3rd type of battle is between the "big" pokemons and after a winner is established it moves on to the next pokemons.

After all 4 adventures are finished the arena closes and program ends.

---
## Design Patterns used

* Singleton - Used in arena class to alwais have a single arena instance if we want to host multiple Duels.

* Builder - used for the item creation. As the items have a lot of parameters but usually they don't have attributes at all of them it seems logic to use this pattern instead of calling a constructor manually with a lot of null values.

* Factory - used to create all the different type of pokemons just by giving the name of the pokemon as a parameter.

* Prototype - used in battles where an attack is executet on a clone of the pokemon so that I can make some verifications after the attack and if needed, to leave the actual pokemon intact (For example when it dodges an attack, even tho the clone recieves damage, the actual pokemon remains the same)

---

## Tests 
There are 10 tests:
* 2 of them are for error cases
* 1 for battless with no items
* 1 for battles with fully equiped Pokemons
* 6 Random and varied tests

---

## POO Final Project 2022
