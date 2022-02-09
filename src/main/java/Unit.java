public abstract class Unit {
    private final String NAME;
    private int health;
    private int attack;
    private int armor;

    public Unit(String NAME, int health, int attack, int armor) throws IllegalArgumentException {
        if(health<0)throw new IllegalArgumentException("The health of a unit cannot be lower than 0.");
        this.NAME = NAME;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
    }

    public String getNAME() {
        return NAME;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract int getAttackBonus();
    public abstract int getResistBonus();

    public void attack(Unit opponent){
        int newHealth= opponent.getHealth() -(this.getAttack()+this.getAttackBonus())+
                (opponent.getAttack()+opponent.getResistBonus());
        opponent.setHealth(Math.max(newHealth, 0));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name of unit: ").append(this.getNAME()).append("\n");
        sb.append("Current health points of unit: ").append(this.getHealth()).append("\n");
        sb.append("Attack points of the unit: ").append(this.getAttack()).append("\n");
        sb.append("Armor points of the unit: ").append(this.getArmor()).append("\n");
        return sb.toString();
    }
}
