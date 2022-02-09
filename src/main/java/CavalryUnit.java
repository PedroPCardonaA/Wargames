public class CavalryUnit extends Unit{
    public CavalryUnit(String NAME, int health, int attack, int armor) throws IllegalArgumentException {
        super(NAME, health, attack, armor);
    }

    public CavalryUnit(String NAME, int health) {
        super(NAME,health,20,12);
    }

    /*@Override
    public int getAttackBonus(boolean charge) {
        if(charge) return 6;
        return 2;
    }*/

    @Override
    public int getAttackBonus() {
        return 6;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
