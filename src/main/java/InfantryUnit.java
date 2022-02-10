public class InfantryUnit extends Unit{
    public InfantryUnit(String NAME, int health, int attack, int armor) throws IllegalArgumentException {
        super(NAME, health, attack, armor);
    }

    public InfantryUnit(String NAME, int health) {
        super(NAME,health,15,10);
    }
    @Override
    public int getAttackBonus(Unit opponent){
        if(opponent instanceof CavalryUnit)return 4;
        return 2;
    }
    @Override
    public int getResistBonus(){
        return 3;
    }
}
