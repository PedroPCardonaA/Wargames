public class RangedUnit extends Unit{
    public RangedUnit(String NAME, int health, int attack, int armor) throws IllegalArgumentException {
        super(NAME, health, attack, armor);
    }

    public RangedUnit(String NAME, int health) {
        super(NAME,health,15,8);
    }

    @Override
    public int getAttackBonus() {
        return 3;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
