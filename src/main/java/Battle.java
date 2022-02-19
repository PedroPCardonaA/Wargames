public class Battle {
    private final Army ARMY_1;
    private final Army ARMY_2;

    public Battle(Army ARMY_1, Army ARMY_2) {
        this.ARMY_1 = ARMY_1;
        this.ARMY_2 = ARMY_2;
    }

    public Army simulate(){
        while (this.bothArmiesHarUnits()){
            Unit unitFromArmy1 = ARMY_1.getRandom();
            Unit unitFromArmy2 = ARMY_2.getRandom();
            this.setChargeToInfantryUnit(unitFromArmy1,unitFromArmy2);
            this.combatBetweenUnits(unitFromArmy1,unitFromArmy2);
        }
        return this.checkWinnerArmy();
    }

    @Override
    public String toString() {
        return "Battle between " + ARMY_1.getNAME() + " and " + ARMY_2.getNAME() + ".";
    }

    private void sameAttackType(Unit unitFromArmy1, Unit unitFromArmy2) {
        while (unitFromArmy1.getHealth() > 0 && unitFromArmy2.getHealth() > 0) {
            unitFromArmy1.attack(unitFromArmy2);
            unitFromArmy2.attack(unitFromArmy2);
        }
    }

    private void rangedVSMelee(Unit rangedUnit, Unit meleeUnit){
        for(int i = 0; i<3; i++){
            rangedUnit.attack(meleeUnit);
        }
        while (rangedUnit.getHealth() > 0 && meleeUnit.getHealth() > 0){
            rangedUnit.attack(meleeUnit);
            meleeUnit.attack(rangedUnit);
        }
    }

    private Unit getDefeatedUnit(Unit unitFromArmy1,Unit unitFromArmy2){
        if(unitFromArmy1.getHealth()==0){
            return unitFromArmy1;
        } else {return unitFromArmy2;}
    }

    private void removeDefeatedUnitFromArmy(Unit defeatedUnit){
        if(ARMY_1.getAllUnits().contains(defeatedUnit)){ ARMY_1.remove(defeatedUnit);}
        else {ARMY_2.remove(defeatedUnit);}
    }
    private Army checkWinnerArmy(){
        if(!this.ARMY_1.hasUnit()&&!this.ARMY_2.hasUnit()){return null;}
        else if(this.ARMY_1.hasUnit()){return ARMY_1;}
        else{return ARMY_2;}
    }
    private void combatBetweenUnits(Unit unitFromArmy1,Unit unitFromArmy2){
        if(unitFromArmy1.getATTACK_TYPE().equals("ranged")&& unitFromArmy2.getATTACK_TYPE().equals("melee")){
            this.rangedVSMelee(unitFromArmy1,unitFromArmy2);
        }
        else if(unitFromArmy2.getATTACK_TYPE().equals("ranged")&&unitFromArmy1.getATTACK_TYPE().equals("melee")){
            this.rangedVSMelee(unitFromArmy2,unitFromArmy1);
        }
        else {
            this.sameAttackType(unitFromArmy1,unitFromArmy2);
        }
        this.removeDefeatedUnitFromArmy(this.getDefeatedUnit(unitFromArmy1,unitFromArmy2));
    }
    private void setChargeToInfantryUnit(Unit unitFromArmy1, Unit unitFromArmy2){
        if(unitFromArmy1 instanceof CavalryUnit)((CavalryUnit) unitFromArmy1).setCharging(true);
        if(unitFromArmy2 instanceof CavalryUnit)((CavalryUnit) unitFromArmy2).setCharging(true);
    }

    private boolean bothArmiesHarUnits(){
        return (ARMY_1.hasUnit() && ARMY_2.hasUnit());
    }

}
