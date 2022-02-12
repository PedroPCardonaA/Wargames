
public class CommanderUnit extends CavalryUnit{
    public CommanderUnit(String NAME, int health, int ATTACK, int ARMOR, int ATTACK_SPEED_PER_SECOND, int HIT_RATIO)
            throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR,ATTACK_SPEED_PER_SECOND,HIT_RATIO);
    }

    public CommanderUnit(String NAME, int health) {
        super(NAME, health,25,15,1,90);
    }

    private void healthPointRegeneration(){
        setHealth(this.getHealth()+5);
    }

    @Override
    public void attack(Unit opponent){
        super.attack(opponent);
        this.healthPointRegeneration();
    }
}
