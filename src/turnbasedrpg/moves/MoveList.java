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
                move.setDesc("O usuário abana seu rabo de maneira fofa, fazendo com que os Pokémon oponentes sejam menos cautelosos e reduzam seu status de Defesa.");
                move.setPP(30);
                move.setPower(0);
                move.setCategory(3);
                move.setStatChange(1);
                move.setStatChangePower(1);
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
                move.setStatChange(1);
                move.setStatChangePower(2);
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
                waterMove.setStatChangePower(2);
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
                steelMove.setDesc("O usuário endurece a superfície de seu corpo como ferro, aumentando drasticamente seu status de defesa.");
                steelMove.setPP(15);
                steelMove.setPower(0);
                steelMove.setCategory(3);
                steelMove.setStatChange(2);
                steelMove.setStatChangePower(2);
                steelMove.setStatChangeType(3);
                break;
        }
        return steelMove;
    }
    
    public Moves getFairyMove(int id) {
        Moves Move = new Moves();
        Move.setType(7);
        switch (id) {
            case 1:
                // Moonblast
                Move.setName("Explosão Lunar");
                Move.setDesc("Tomando emprestado o poder da lua, o usuário ataca o alvo.");
                Move.setPP(15);
                Move.setPower(95);
                Move.setCategory(2);
                break;
            case 2:
                // Draining Kiss
                Move.setName("Beijo Drenante");
                Move.setDesc("O usuário atinge o alvo com um beijo.");
                Move.setPP(10);
                Move.setPower(50);
                Move.setCategory(2);
                break;
        }
        return Move;
    }
    
    public Moves getPsychicMove(int id) {
        Moves Move = new Moves();
        Move.setType(8);
        switch (id) {
            case 1:
                // Calm Mind
                Move.setName("Mente Calma");
                Move.setDesc("O usuário silenciosamente concentra sua mente e acalma seu espírito para aumentar seu ataque especial.");
                Move.setPP(20);
                Move.setPower(0);
                Move.setCategory(3);
                Move.setStatChange(2);
                Move.setStatChangeType(2);
                Move.setStatChangePower(2);
                break;
            case 2:
                // Psychic 
                Move.setName("Psiquico");
                Move.setDesc("O alvo é atingido por uma forte força telecinética.");
                Move.setPP(10);
                Move.setPower(90);
                Move.setCategory(2);
                break;
        }
        return Move;
    }
    
    public Moves getGroundMove(int id) {
        Moves Move = new Moves();
        Move.setType(16);
        switch (id) {
            case 1:
                // Bonemerang
                Move.setName("Ossomerangue");
                Move.setDesc("O usuário joga o osso que ele segura. O osso faz um loop ao redor para acertar o alvo duas vezes - indo e vindo.");
                Move.setPP(10);
                Move.setAccuracy(90);
                Move.setPower(100);
                Move.setCategory(1);
                break;
            case 2:
                // Bone Club
                Move.setName("Clube Ósseo");
                Move.setDesc("O usuário bate no alvo com um osso.");
                Move.setPP(20);
                Move.setAccuracy(85);
                Move.setPower(65);
                Move.setCategory(1);
                break;
        }
        return Move;
    }
}
