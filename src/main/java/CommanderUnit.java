
public class CommanderUnit extends CavalryUnit{
    public CommanderUnit(String NAME, int health, int ATTACK, int ARMOR, int ATTACK_SPEED_PER_SECOND)
            throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR,ATTACK_SPEED_PER_SECOND);
    }

    public CommanderUnit(String NAME, int health) {
        super(NAME, health,25,15,1);
    }

    private void healthPointRegeneration(){
        setHealth(this.getHealth()+2);
    }

    @Override
    public void attack(Unit opponent){
        super.attack(opponent);
        this.healthPointRegeneration();
    }
}
