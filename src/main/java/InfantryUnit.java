
public class InfantryUnit extends Unit{


    public InfantryUnit(String NAME, int health, int ATTACK, int ARMOR, int ATTACK_SPEED_PER_SECOND, int HIT_RATE, int CRITIC_RATE, int CRITIC_DAMAGE)
            throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR,ATTACK_SPEED_PER_SECOND,"melee", HIT_RATE,CRITIC_RATE,CRITIC_DAMAGE);
    }

    public InfantryUnit(String NAME, int health) {
        super(NAME,health,15,10,1,"melee",85,20,135);
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
        return new RangedUnit(this.getNAME(),this.getHealth(),
                this.getATTACK(),this.getARMOR(),this.getATTACK_SPEED_PER_SECOND(), this.getHIT_RATE(),
                this.getCRITIC_RATE(),this.getCRITIC_DAMAGE());
    }
}
