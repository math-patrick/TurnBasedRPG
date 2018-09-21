package turnbasedrpg.moves;

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
        Moves moveGetter = new Moves();
        Moves[] movesArray = new Moves[3];
        movesArray[0] = moveGetter.getWaterMove(2);
        movesArray[1] = moveGetter.getWaterMove(1);
        this.createPokemon("Squirtle", movesArray);
        return this;
    }
}