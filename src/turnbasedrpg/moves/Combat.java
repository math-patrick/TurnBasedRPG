/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg.moves;

import java.util.Random;

/**
 *
 * @author matheus.oliveira
 */
public class Combat {
    Pokemon player1;
    Pokemon player2;
    
    public Combat(Pokemon pokemon1, Pokemon pokemon2) {
        player1 = pokemon1;
        player2 = pokemon2;
    }
    
    public void calculateDamage(int moveID, int player) {
        Pokemon attacker = (player == 1) ? player1 : player2;
        Pokemon defender = (player == 1) ? player2 : player1;
        Moves move = attacker.getPokemonMove(moveID);
        
        Random rand = new Random();
        int randomNumber = rand.nextInt(16) + 85;

        //  Fórmula de calculo de dano: Damage = ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100
        int category = move.getFgCategory();
        int damage = ((((2 * attacker.getLevel() / 5 + 2) * attacker.getAttackValue(category) * move.getPower() / defender.getDefenseValue(category)) / 50) + 2) 
                /** STAB * Weakness/Resistance * */ * randomNumber / 100;

        
    }
}
