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
    int damage;
    boolean isCrit;
    int effective = 1;
    private String moveUsed;
    int lastPlayer;

    public boolean isReady() {
        return !(player1 == null || player2 == null);
    }

    public Pokemon calculateSpeedPriority() {
        if (player1.getSpeedValue() > player2.getSpeedValue()) {
            return player1;
        } else {
            return player2;
        }
    }

    public void calculateDamage(int moveID, int player) {
        setLastPlayer(player);
        setDamage(0);
        setIsCrit(false);
        setEffective(0);
        setMoveUsed("");

        Pokemon attacker = (player == 1) ? player1 : player2;
        Pokemon defender = (player == 1) ? player2 : player1;
        Moves move = attacker.getPokemonMove(moveID);

        Random rand = new Random();

        //  Fórmula de calculo de dano: Damage = ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100
        int category = move.getCategory();
        setMoveUsed(move.getName());

        System.out.println("Attacker level: " + attacker.getLevel());
        System.out.println("Attacker attack: " + attacker.getAttackValue(category));
        System.out.println("Attacker defense: " + defender.getDefenseValue(category));
        double damage = (((((2 * attacker.getLevel() / 5.0)) + 2)
                * move.getPower()
                * (attacker.getAttackValue(category) / defender.getDefenseValue(category))) / 50.0) + 2;

        setIsCrit(rand.nextInt(24) == 1);
        double randomNumber = (rand.nextInt(16) + 85) / 100.0;
        double effective = 1;
        double STAB = (int) ((attacker.getPrimaryType() == move.getType() || attacker.getSecondaryType() == move.getType())
                ? 1.5 : 1);
        double modifier = (1 * (getIsCrit() ? 1.5 : 1) * (randomNumber) * STAB * effective);
        System.out.println(damage);
        System.out.println(modifier);
        System.out.println(randomNumber);
        setDamage((int) (damage * modifier));
        /**
         * STAB * Weakness/Resistance *
         */

        double newHealth = defender.getHealthValue() - getDamage();
        if (newHealth < 0) {
            newHealth = 0;
        }

        defender.setHealthValue(newHealth);

        setPlayer1((player == 1) ? attacker : defender);
        setPlayer2((player == 1) ? defender : attacker);
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean getIsCrit() {
        return isCrit;
    }

    public void setIsCrit(boolean isCrit) {
        this.isCrit = isCrit;
    }

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
    }

    public String getMoveUsed() {
        return moveUsed;
    }

    public void setMoveUsed(String moveUsed) {
        this.moveUsed = moveUsed;
    }

    public int getLastPlayer() {
        return lastPlayer;
    }

    public void setLastPlayer(int lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

}
