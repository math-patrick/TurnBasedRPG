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
 * @author Zhaetar
 */
public class DamageCalculation implements Serializable  {

    String message;
    double damage; 

    public void calculateDamage(Pokemon attacker, Pokemon defender, Moves move) {
        Random rand = new Random();
        //  Fórmula de calculo de dano: Damage = ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100
        int category = move.getCategory();
        double damage = (((((2 * attacker.getLevel() / 5.0)) + 2)
                * move.getPower()
                * (attacker.getAttackTemp(category) / defender.getDefenseTemp(category))) / 50.0) + 2;
        boolean isCrit = rand.nextInt(24) == 1;
        double randomNumber = (rand.nextInt(16) + 85) / 100.0;
        double effective = 1;
        double STAB = (int) ((attacker.getPrimaryType() == move.getType() || attacker.getSecondaryType() == move.getType())
                ? 1.5 : 1);
        double modifier = (1 * (isCrit ? 1.5 : 1) * (randomNumber) * STAB * effective);
        
        setDamage(damage * modifier);
        
        String message = attacker.getName() + " utilizou " + move.getName() + " e causou " + Math.round(getDamage()) + " de dano!";
        if (isCrit) {
            message += " Um ataque critíco!";
        }
        if (effective!=1) {
            message+= (effective > 1) ? " Super efetivo!" : " Não foi muito efetivo..";
        }
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

}
