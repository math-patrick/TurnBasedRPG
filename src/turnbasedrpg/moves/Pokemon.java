package turnbasedrpg.moves;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author matheus.oliveira
 */
public class Pokemon {
    String name = "";
    int level = 1;
    Moves[] moves;
    
    public Pokemon () {
    }
    
    public void createPokemon(String name, Moves[] moves) {
        this.name = name;
        this.moves = moves;
    }
    
    public Moves getPokemonMove(int arrayPosition) {
        return this.moves[arrayPosition];
    }
    
    public Pokemon getSquirtle() {
        Moves[] movesArray = new Moves[4];
        movesArray[0] = new Moves().getNormalMove(1);
        movesArray[1] = new Moves().getNormalMove(2);
        movesArray[2] = new Moves().getWaterMove(1);
        movesArray[3] = new Moves().getWaterMove(2);
        System.out.println(movesArray[0].getName());
        System.out.println(movesArray[1].getName());
        System.out.println(movesArray[2].getName());
        System.out.println(movesArray[3].getName());
        this.createPokemon("Squirtle", movesArray);
        return this;
    }
}
