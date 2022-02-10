public class CavalryUnit extends Unit{
    private Boolean isCharging = true;
    public CavalryUnit(String NAME, int health, int attack, int armor) throws IllegalArgumentException {
        super(NAME, health, attack, armor);
    }

    public CavalryUnit(String NAME, int health) {
        super(NAME,health,20,12);
    }

    @Override
    public int getAttackBonus(Unit opponent) {
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
    public int getResistBonus() {
        return 2;
    }

    public void setCharging(Boolean charging) {
        isCharging = charging;
    }
}
