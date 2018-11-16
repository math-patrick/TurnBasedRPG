/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg.moves;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author matheus.oliveira
 */
public class Combat implements Serializable {

    Pokemon player1;
    Pokemon player2;
    
    CombatLog combatLog1;
    CombatLog combatLog2;
    
    int first;
    
    public boolean isReady() {
        return !(player1 == null || player2 == null);
    }

    public int calculateSpeedPriority() {
        if (player1.getSpeedValue() > player2.getSpeedValue()) {
            return 1;
        } else {
            return 2;
        }
    }

    public void calculateDamage(int moveID, int moveID2) {
        Pokemon attacker;
        Pokemon defender;
        Moves attackerMove;
        Moves defenderMove;
        
        if (calculateSpeedPriority() == 1) {
            attacker = player1;
            defender = player1;
            attackerMove = attacker.getPokemonMove(moveID);
            defenderMove = defender.getPokemonMove(moveID);
            first = 1;
        } else {
            attacker = player2;
            defender = player2;
            attackerMove = attacker.getPokemonMove(moveID2);
            defenderMove = defender.getPokemonMove(moveID2);
            first = 2;
        }

        double dmgFirst = DamageCalculation(attacker, defender, attackerMove);
        double healthDefender = defender.getHealthValue() - dmgFirst;
        double healthAttacker = attacker.getHealthValue();

        if (healthDefender < 0) {
            healthDefender = 0;
        } else {
            double dmgSecond = damageCalc(defender, attacker, defenderMove);
            healthAttacker = healthAttacker - dmgSecond;
            
            if (healthAttacker < 0) {
                healthAttacker = 0;
            }
        }
        
        defender.setHealthValue(healthDefender);
        attacker.setHealthValue(healthAttacker);
        
        setPlayer1((calculateSpeedPriority() == 1) ? attacker : defender);
        setPlayer2((calculateSpeedPriority() == 1) ? defender : attacker);
    }
    

    public Pokemon getPlayer1() {
        return this.player1;
    }

    public Pokemon getPlayer2() {
        return this.player2;
    }

    public void setPlayer1(Pokemon p1) {
        this.player1 = p1;
    }

    public void setPlayer2(Pokemon p2) {
        this.player2 = p2;
    }
}
