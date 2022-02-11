
public class RangedUnit extends Unit{
    public RangedUnit(String NAME, int health, int attack, int armor) throws IllegalArgumentException {
        super(NAME, health, attack, armor);
    }

    public RangedUnit(String NAME, int health) {
        super(NAME,health,15,8);
    }

    @Override
    protected int getAttackBonus(Unit opponent) {
        if(opponent instanceof InfantryUnit)return 7;
        return 4;
    }

    @Override
    protected int getResistBonus(Unit mainUnit) {
        if(mainUnit instanceof InfantryUnit)return 2;
        if(mainUnit instanceof CavalryUnit) return -2;
        return 1;
    }
    @Override
    protected Unit clone(){
        return new RangedUnit(this.getNAME(),this.getHealth(),this.getATTACK(),this.getARMOR());
    }
}
