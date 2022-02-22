package edu.ntnu.idatt2001.pedropca;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfantryUnitTest {

    @Nested
    class TestingOfMethodGetAttackBonus {
        @Test
        void getAttackBonusAgainstInfantry() {
            assertEquals(2, new InfantryUnit("Infantry",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
        }
        @Test
        void getAttackBonusAgainstRanged(){
            assertEquals(2, new InfantryUnit("Infantry",100).getAttackBonus(new RangedUnit("Ranged",100)));
        }
        @Test
        void getAttackBonusAgainstCavalry(){
            assertEquals(4, new InfantryUnit("Infantry",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
        }
    }

    @Nested
    class TestingOfMethodGetResistBonus {
        @Test
        void getResistBonusAgainstInfantry() {
            assertEquals(3, new InfantryUnit("Infantry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
        }
        @Test
        void getResistBonusAgainstRanged(){
            assertEquals(2, new InfantryUnit("Infantry",100).getResistBonus(new RangedUnit("Ranged",100)));
        }
        @Test
        void getResistBonusAgainstCavalry(){
            assertEquals(5, new InfantryUnit("Infantry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
        }
    }
    @Nested
    class TestingOfMethodClone{
        @Test
        void makingACloneOfAFootman(){
            Unit footman = new InfantryUnit("Footman",100);
            Unit footmanCopy = footman.clone();
            assertEquals(footman.toString(),footmanCopy.toString());
        }
        @Test
        void makingACloneOfAGrunt(){
            Unit grunt = new InfantryUnit("Grunt",100,30,12,4,75,50,200);
            Unit gruntClone = grunt.clone();
            assertEquals(grunt.toString(),gruntClone.toString());
        }

        @Test
        void makingACloneOfARouge(){
            Unit rouge = new InfantryUnit("Rouge",125,15,12,1,70,25,150);
            Unit rougeClone = rouge.clone();
            assertEquals(rouge.toString(),rougeClone.toString());
        }
    }

}