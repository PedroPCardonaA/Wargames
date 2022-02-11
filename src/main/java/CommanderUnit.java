
public class CommanderUnit extends CavalryUnit{
    public CommanderUnit(String NAME, int health, int attack, int armor) throws IllegalArgumentException {
        super(NAME, health, attack, armor);
    }

    public CommanderUnit(String NAME, int health) {
        super(NAME, health,25,15);
    }

    public void healthPointRegeneration(){
        setHealth(this.getHealth()+2);
    }
}
