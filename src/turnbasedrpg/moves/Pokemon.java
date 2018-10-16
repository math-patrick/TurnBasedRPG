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
        this.createPokemon("Squirtle", movesArray);
        return this;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
