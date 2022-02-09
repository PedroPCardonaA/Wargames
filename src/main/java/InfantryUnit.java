public class InfantryUnit extends Unit{
    public InfantryUnit(String NAME, int health, int attack, int armor) throws IllegalArgumentException {
        super(NAME, health, attack, armor);
    }

    public InfantryUnit(String NAME, int health) {
        super(NAME,health,15,10);
    }
    @Override
    public int getAttackBonus(){
        return 2;
    }
    @Override
    public int getResistBonus(){
        return 1;
    }
}
