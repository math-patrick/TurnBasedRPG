package turnbasedrpg.moves;

/**
 * Move database
 *
 * @author Zhaetar
 */
public class MoveList extends Moves {

    public Moves getNormalMove(int id) {
        Moves move = new Moves();
        move.setType(1);
        switch (id) {
            case 1:
                move.setName("Tackle");
                move.setDesc("A physical attack in which the user charges and slams into the target with its whole body.");
                move.setPP(35);
                move.setPower(40);
                move.setCategory(1);
                break;
            case 2:
                move.setName("Tail Whip");
                move.setDesc("The user wags its tail cutely, making opposing Pokémon less wary and lowering their Defense stat.");
                move.setPP(30);
                move.setPower(0);
                move.setCategory(3);
                move.setStatChange(1);
                move.setStatChangePower(1);
                move.setStatChangeType(3);
                break;
            case 3:
                move.setName("Slash");
                move.setDesc("The target is attacked with a slash of claws or blades. Critical hits land more easily.");
                move.setPP(20);
                move.setPower(70);
                move.setCategory(1);
                break;
            case 4:
                move.setName("Scary face");
                move.setDesc("The user frightens the target with a scary face to harshly lower its Speed stat.");
                move.setPP(10);
                move.setPower(0);
                move.setCategory(3);
                move.setStatChange(1);
                move.setStatChangePower(2);
                move.setStatChangeType(5);
                break;
            case 5:
                move.setName("Skull bash");
                move.setDesc("The user tucks in its head to raise its Defense stat on the first turn, then rams the target on the next turn.");
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
                waterMove.setName("Water gun");
                waterMove.setDesc("The target is blasted with a forceful shot of water.");
                waterMove.setPP(25);
                waterMove.setPower(40);
                waterMove.setCategory(2);
                break;
            case 2:
                waterMove.setName("Withdraw");
                waterMove.setDesc("The user withdraws its body into its hard shell, raising its Defense stat.");
                waterMove.setPP(40);
                waterMove.setPower(0);
                waterMove.setCategory(3);
                waterMove.setStatChange(2);
                waterMove.setStatChangeType(3);
                waterMove.setStatChangePower(2);
                break;
            case 3:
                waterMove.setName("Water Pulse");
                waterMove.setDesc("The user attacks the target with a pulsing blast of water. This may also confuse the target.");
                waterMove.setPP(20);
                waterMove.setPower(60);
                waterMove.setCategory(2);
                break;
            case 4:
                waterMove.setName("Aqua Tail");
                waterMove.setDesc("The user attacks by swinging its tail as if it were a vicious wave in a raging storm.");
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
                fireMove.setName("Flamethrower");
                fireMove.setDesc("The target is scorched with an intense blast of fire. This may also leave the target with a burn.");
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
                flyingMove.setName("Wing Attack");
                flyingMove.setDesc("The target is struck with large, imposing wings spread wide to inflict damage.");
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
                steelMove.setName("Iron Defense");
                steelMove.setDesc("The user hardens its body’s surface like iron, sharply raising its Defense stat.");
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
                Move.setName("Moonblast");
                Move.setDesc("Borrowing the power of the moon, the user attacks the target. This may also lower the target’s Sp. Atk stat.");
                Move.setPP(15);
                Move.setPower(95);
                Move.setCategory(2);
                break;
            case 2:
                Move.setName("Draining Kiss");
                Move.setDesc("The user steals the target’s HP with a kiss. The user’s HP is restored by over half of the damage taken by the target.");
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
                Move.setName("Calm Mind");
                Move.setDesc("O usuário silenciosamente concentra sua mente e acalma seu espírito para aumentar seu ataque especial.");
                Move.setPP(20);
                Move.setPower(0);
                Move.setCategory(3);
                Move.setStatChange(2);
                Move.setStatChangeType(2);
                Move.setStatChangePower(2);
                break;
            case 2:
                Move.setName("Psychic");
                Move.setDesc("The target is hit by a strong telekinetic force.");
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
                Move.setName("Bonemerang");
                Move.setDesc("The user throws the bone it holds. The bone loops to hit the target twice, coming and going.");
                Move.setPP(10);
                Move.setAccuracy(90);
                Move.setPower(100);
                Move.setCategory(1);
                break;
            case 2:
                Move.setName("Bone Club");
                Move.setDesc("The user clubs the target with a bone.");
                Move.setPP(20);
                Move.setAccuracy(85);
                Move.setPower(65);
                Move.setCategory(1);
                break;
        }
        return Move;
    }
}
