package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangedUnitTest {
    @Nested
    class TestingOfMethodGetAttackBonus {
        @Nested
        class Positive{
            @Test
            void getAttackBonusAgainstInfantry() {
                assertEquals(7, new RangedUnit("Ranged",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRanged(){
                assertEquals(4, new RangedUnit("Ranged",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalry(){
                assertEquals(4, new RangedUnit("Ranged",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
        }
    }
    @Nested
    class TestingOfMethodGetResistBonus {
        @Nested
        class Positive{
            @Test
            void getResistBonusAgainstInfantry() {
                assertEquals(2, new RangedUnit("Ranged",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRanged(){
                assertEquals(1, new RangedUnit("Ranged",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalry(){
                assertEquals(0, new RangedUnit("Ranged",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
        }
    }
    @Nested
    class TestingOfMethodClone{
        @Nested
        class Positive{
            @Test
            void makingACloneOfAnArcher(){
                Unit archer = new RangedUnit("Archer",100);
                Unit archerCopy = archer.clone();
                assertEquals(archer.toString(),archerCopy.toString());
            }
            @Test
            void makingACloneOfARifleman(){
                Unit rifleman = new RangedUnit("Rifleman",100,30,12,4,75,50,200);
                Unit riflemanClone = rifleman.clone();
                assertEquals(rifleman.toString(),riflemanClone.toString());
            }

            @Test
            void makingACloneOfASpearman(){
                Unit spearman = new RangedUnit("Spearman",125,15,12,1,70,25,150);
                Unit spearmanClone = spearman.clone();
                assertEquals(spearman.toString(),spearmanClone.toString());
            }
        }
    }

}