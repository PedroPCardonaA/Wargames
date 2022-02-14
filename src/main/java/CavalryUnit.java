
public class CavalryUnit extends Unit{

    private Boolean isCharging = true;

    public CavalryUnit(String NAME, int health, int ATTACK, int ARMOR, int ATTACK_SPEED_PER_SECOND, int HIT_RATE, int CRITIC_RATE, int CRITIC_DAMAGE)
            throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR, ATTACK_SPEED_PER_SECOND,"melee",HIT_RATE,CRITIC_RATE,CRITIC_DAMAGE);
    }

    public CavalryUnit(String NAME, int health) {
        super(NAME,health,20,12, 2,"melee",70,25,145);
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
        return new RangedUnit(this.getNAME(),this.getHealth(),
                this.getATTACK(),this.getARMOR(),this.getATTACK_SPEED_PER_SECOND(), this.getHIT_RATE(),
                this.getCRITIC_RATE(),this.getCRITIC_DAMAGE());
    }

    public void setCharging(Boolean charging) {
        isCharging = charging;
    }


}
