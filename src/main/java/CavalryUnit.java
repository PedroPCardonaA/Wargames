
public class CavalryUnit extends Unit{

    private Boolean isCharging = true;

    public CavalryUnit(String NAME, int health, int ATTACK, int ARMOR, int ATTACK_SPEED_PER_SECOND)
            throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR, ATTACK_SPEED_PER_SECOND);
    }

    public CavalryUnit(String NAME, int health) {
        super(NAME,health,20,12, 2);
    }

    @Override
    protected int getAttackBonus(Unit opponent) {
        if(isCharging && opponent instanceof RangedUnit){
            this.setCharging(false);
            return 8;}
        else if( isCharging){
            this.setCharging(false);
            return 6;}
        else if(opponent instanceof RangedUnit){
            return 4;}
        else{
            return 2;}
    }

    /*@Override
    public int getAttackBonus(Unit opponent) {
        return 6;
    }*/

    @Override
    protected int getResistBonus(Unit mainUnit) {
        if(mainUnit instanceof RangedUnit)return 7;
        if(mainUnit instanceof InfantryUnit) return -2;
        return 4;
    }
    @Override
    protected Unit clone(){
        return new CavalryUnit(this.getNAME(),this.getHealth(),
                this.getATTACK(),this.getARMOR(),this.getATTACK_SPEED_PER_SECOND());
    }

    public void setCharging(Boolean charging) {
        isCharging = charging;
    }
}
