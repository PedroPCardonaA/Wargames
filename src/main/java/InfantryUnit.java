
public class InfantryUnit extends Unit{
    public InfantryUnit(String NAME, int health, int attack, int armor) throws IllegalArgumentException {
        super(NAME, health, attack, armor);
    }

    public InfantryUnit(String NAME, int health) {
        super(NAME,health,15,10);
    }
    @Override
    protected int getAttackBonus(Unit opponent){
        if(opponent instanceof CavalryUnit)return 4;
        return 2;
    }
    @Override
    protected int getResistBonus(Unit mainUnit){
        if(mainUnit instanceof CavalryUnit)return 3;
        return 2;
    }
    @Override
    protected Unit clone(){
        return new InfantryUnit(this.getNAME(),this.getHealth(),this.getATTACK(),this.getARMOR());
    }
}
