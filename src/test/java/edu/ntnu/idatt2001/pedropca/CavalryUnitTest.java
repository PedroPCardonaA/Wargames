package edu.ntnu.idatt2001.pedropca;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CavalryUnitTest {

    @Nested
    class TestingOfMethodGetAttackBonus {
        @Test
        void getAttackBonusAgainstInfantryWhenCharging() {
            assertEquals(4, new CavalryUnit("Cavalry",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
        }
        @Test
        void getAttackBonusAgainstInfantryWhenNotCharging() {
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
            cavalryUnit.setCharging(false);
            assertEquals(2, cavalryUnit.getAttackBonus(new InfantryUnit(" Infantry",100)));
        }
        @Test
        void getAttackBonusAgainstRangedWhenCharging(){
            assertEquals(8, new CavalryUnit("Cavalry",100).getAttackBonus(new RangedUnit("Ranged",100)));
        }
        @Test
        void getAttackBonusAgainstRangedWhenNotCharging() {
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
            cavalryUnit.setCharging(false);
            assertEquals(6, cavalryUnit.getAttackBonus(new RangedUnit(" Ranged",100)));
        }
        @Test
        void getAttackBonusAgainstCavalryWhenCharging(){
            assertEquals(4, new CavalryUnit("Cavalry",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
        }
        @Test
        void getAttackBonusAgainstCavalryWhenNotCharging() {
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
            cavalryUnit.setCharging(false);
            assertEquals(2, cavalryUnit.getAttackBonus(new CavalryUnit(" Cavalry",100)));
        }
    }

    @Nested
    class TestingOfMethodGetResistBonus {
        @Test
        void getResistBonusAgainstInfantry() {
            assertEquals(-2, new CavalryUnit("Cavalry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
        }
        @Test
        void getResistBonusAgainstRanged(){
            assertEquals(7, new CavalryUnit("Cavalry",100).getResistBonus(new RangedUnit("Ranged",100)));
        }
        @Test
        void getResistBonusAgainstCavalry(){
            assertEquals(4, new CavalryUnit("Cavalry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
        }
    }
    @Nested
    class TestingOfMethodClone{
        @Test
        void makingACloneOfAKnightThatIsCharging(){
            Unit Knight = new CavalryUnit("Knight",100);
            Unit KnightCopy = Knight.clone();
            assertEquals(Knight.toString(),KnightCopy.toString());
        }
        @Test
        void makingACloneOfARaiderThatIsNotCharging(){
            CavalryUnit raider = new CavalryUnit("Raider",100,30,12,4,75,50,200);
            raider.setCharging(false);
            Unit raiderClone = raider.clone();
            assertEquals(raider.toString(),raiderClone.toString());
        }

        @Test
        void makingACloneOfASpiderRaiderThatIsCharging(){
            Unit spiderRaider = new InfantryUnit("Spider Raider",125,15,12,1,70,25,150);
            Unit spiderRaiderClone = spiderRaider.clone();
            assertEquals(spiderRaider.toString(),spiderRaiderClone.toString());
        }
    }

    @Nested
    class TestingOfOverrideMethodAttack{
        @Test
        void AttackOfACavalryThatIsCharging(){
            CavalryUnit cavalry = new CavalryUnit("Cavalry",100);
            cavalry.attack(new InfantryUnit("Infantry",100));
            assertEquals(false,cavalry.getCharging());
        }
        @Test
        void AttackOfACavalryThatIsNotCharging(){
            CavalryUnit cavalry = new CavalryUnit("Cavalry",100);
            cavalry.setCharging(false);
            cavalry.attack(new InfantryUnit("Infantry",100));
            assertEquals(false,cavalry.getCharging());
        }
    }
}