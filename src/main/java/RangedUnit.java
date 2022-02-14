public class RangedUnit extends Unit{
    public RangedUnit(String NAME, int health, int ATTACK, int ARMOR, int ATTACK_SPEED_PER_SECOND, int HIT_RATE, int CRITIC_RATE, int CRITIC_DAMAGE)
            throws IllegalArgumentException {
        super(NAME,health,ATTACK,ARMOR,ATTACK_SPEED_PER_SECOND,"Ranged",HIT_RATE,CRITIC_RATE,CRITIC_DAMAGE);
    }

    public RangedUnit(String NAME, int health){
        super(NAME,health,15,8,3,"ranged", 65,15,150);
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
        return new RangedUnit(this.getNAME(),this.getHealth(),
                this.getATTACK(),this.getARMOR(),this.getATTACK_SPEED_PER_SECOND(), this.getHIT_RATE(),
                this.getCRITIC_RATE(),this.getCRITIC_DAMAGE());
    }

}
