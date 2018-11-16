/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg.moves;

/**
 *
 * @author Zhaetar
 */
public class PokemonList extends Pokemon {
    public Pokemon getPokemonByID(String v) {
        switch (v) {
            case "6": 
                return getCharizard();
            case "9":
                return getBlastoise();
            default:
                return getSquirtle();
        }
    }
    
    public Pokemon getSquirtle() {
        Moves[] movesArray = new Moves[4];
        movesArray[0] = new MoveList().getNormalMove(1);
        movesArray[1] = new MoveList().getNormalMove(2);
        movesArray[2] = new MoveList().getWaterMove(1);
        movesArray[3] = new MoveList().getWaterMove(2);
     
        Pokemon Squirtle = new Pokemon();
        Squirtle.setNumber(7);
        Squirtle.setName("Squirtle");
        Squirtle.setMoves(movesArray);
        Squirtle.setLevel(50);
        Squirtle.setPrimaryType(2);
        Squirtle.calculateStats();
        return Squirtle;
    }
    
    public Pokemon getCharizard() {
        Moves[] movesArray = new Moves[4];
        movesArray[0] = new MoveList().getNormalMove(4);
        movesArray[1] = new MoveList().getFireMove(1);
        movesArray[2] = new MoveList().getFlyingMove(1);
        movesArray[3] = new MoveList().getNormalMove(3);
     
        Pokemon Charizard = new Pokemon();
        Charizard.setNumber(6);
        Charizard.setName("Charizard");
        Charizard.setMoves(movesArray);
        Charizard.setLevel(50);
        Charizard.setPrimaryType(3);
        Charizard.setSecondaryType(5);
        
        Charizard.setHealthStat(78);
        Charizard.setAttackStat(84, 1);
        Charizard.setAttackStat(109, 2);
        Charizard.setDefenseStat(78, 1);
        Charizard.setDefenseStat(85, 2);
        Charizard.setSpeedStat(100);
        Charizard.calculateStats();
        return Charizard;
    }
    
    public Pokemon getBlastoise() {
        Moves[] movesArray = new Moves[4];
        movesArray[0] = new MoveList().getWaterMove(3);
        movesArray[1] = new MoveList().getWaterMove(4);
        movesArray[2] = new MoveList().getNormalMove(5);
        movesArray[3] = new MoveList().getSteelMove(1);
     
        Pokemon Blastoise = new Pokemon();
        Blastoise.setNumber(9);
        Blastoise.setName("Blastoise");
        Blastoise.setMoves(movesArray);
        Blastoise.setLevel(50);
        Blastoise.setPrimaryType(2);
        
        Blastoise.setHealthStat(79);
        Blastoise.setSpeedStat(85);
        Blastoise.setAttackStat(83, 1);
        Blastoise.setAttackStat(100, 2);
        Blastoise.setDefenseStat(105, 1);
        Blastoise.setDefenseStat(78, 2);
        
        Blastoise.calculateStats();
        return Blastoise;
    }
}
