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
public class MoveList extends Moves {
    
    public Moves getNormalMove(int id) {
        Moves move = new Moves();
        move.setType(1);
        switch (id) {
            case 1:
                // Tackle
                move.setName("Investida");
                move.setPP(35);
                move.setPower(40);
                move.setCategory(1);
                break;
            case 2:
                // Tail Whip
                move.setName("Chicote de Calda");
                move.setPP(30);
                move.setPower(0);
                move.setCategory(3);
                move.setStatChange(1);
                move.setStatChangeType(3);
                break;
            case 3:
                // Slash
                move.setName("Talhada");
                move.setDesc("O alvo é atacado com um golpe de garras ou lâminas.");
                move.setPP(20);
                move.setPower(70);
                move.setCategory(1);
                break;
            case 4:
                // Scary Face
                move.setName("Cara Assustadora");
                move.setDesc("O usuário assusta o alvo com uma cara assustadora para reduzir drasticamente sua velocidade.");                         
                move.setPP(10);
                move.setPower(0);
                move.setCategory(3);
                move.setStatChange(2);
                move.setStatChangeType(5);
                break;
            case 5:
                // Skull Bash
                move.setName("Quebra Crânio");
                move.setDesc("O usuário encolhe a cabeça e depois bate no alvo.");
                move.setPP(10);
                move.setPower(70);
                move.setCategory(1);
                break;
        }
        return move;
    }
    
    public Moves getWaterMove(int id) {
        Moves waterMove = new Moves();
        waterMove.setType(2);
        switch (id) {
            case 1:
                // Water Gun
                waterMove.setName("Jato de Água");
                waterMove.setPP(25);
                waterMove.setPower(40);
                waterMove.setCategory(2);
                break;
            case 2:
                // Withdraw
                waterMove.setName("Retirada");
                waterMove.setPP(40);
                waterMove.setPower(0);
                waterMove.setCategory(3);
                waterMove.setStatChange(2);
                waterMove.setStatChangeType(3);
                break;
            case 3:
                // Water Pulse
                waterMove.setName("Vibração de Água");
                waterMove.setDesc("O usuário ataca o alvo com uma explosão de água pulsante.");
                waterMove.setPP(20);
                waterMove.setPower(60);
                waterMove.setCategory(2);
                break;
            case 4:
                // Aqua Tail
                waterMove.setName("Cauda de Água");
                waterMove.setDesc("O usuário ataca balançando a cauda como se fosse uma onda viciosa em uma tempestade violenta.");
                waterMove.setPP(10);
                waterMove.setPower(90);
                waterMove.setCategory(1);
                break;
        }
        return waterMove;
    }
    
    public Moves getFireMove(int id) {
        Moves fireMove = new Moves();
        fireMove.setType(3);
        switch (id) {
            case 1:
                // Flamethrower
                fireMove.setName("Lança Chamas");
                fireMove.setDesc("O alvo é queimado com uma intensa explosão de fogo.");
                fireMove.setPP(15);
                fireMove.setPower(90);
                fireMove.setCategory(2);
                break;
        }
        return fireMove;
    }
    
    public Moves getFlyingMove(int id) {
        Moves flyingMove = new Moves();
        flyingMove.setType(5);
        switch (id) {
            case 1:
                // Wing Attack
                flyingMove.setName("Ataque de Asas");
                flyingMove.setDesc("O alvo é atingido por grandes e imponentes asas abertas para causar dano.");
                flyingMove.setPP(35);
                flyingMove.setPower(60);
                flyingMove.setCategory(1);
                break;
        }
        return flyingMove;
    }
    
    public Moves getSteelMove(int id) {
        Moves steelMove = new Moves();
        steelMove.setType(6);
        switch (id) {
            case 1:
                // Iron Defense
                steelMove.setName("Defesa de Ferro");
                steelMove.setPP(15);
                steelMove.setPower(0);
                steelMove.setCategory(3);
                steelMove.setStatChange(2);
                steelMove.setStatChangeType(3);
                break;
        }
        return steelMove;
    }
}
