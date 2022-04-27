package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import edu.ntnu.idatt2001.pedropca.wargames.util.EnumTerrain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfantryUnitTest {
    @Nested
    class TestingOfMethodGetAttackBonus {
        @Nested
        class Positive{
            @Test
            void getAttackBonusAgainstInfantryWhenTheTerrainIsNotForest() {
                EnumTerrain.setHILL();
                assertEquals(0, new InfantryUnit("Infantry",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenTheTerrainIsNotForest(){
                EnumTerrain.setHILL();
                assertEquals(0, new InfantryUnit("Infantry",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTheTerrainIsNotForest(){
                EnumTerrain.setHILL();
                assertEquals(4, new InfantryUnit("Infantry",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTheTerrainIsNotForest(){
                EnumTerrain.setHILL();
                assertEquals(0, new InfantryUnit("Infantry",100).getAttackBonus(new MagicianUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenIsForest(){
                EnumTerrain.setForest();
                assertEquals(9, new InfantryUnit("Infantry",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTheTerrainIsForest(){
                EnumTerrain.setForest();
                assertEquals(5, new InfantryUnit("Infantry",100).getAttackBonus(new MagicianUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstInfantryWhenTheTerrainIsForest() {
                EnumTerrain.setForest();
                assertEquals(5, new InfantryUnit("Infantry",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenTheTerrainIsForest(){
                EnumTerrain.setForest();
                assertEquals(5, new InfantryUnit("Infantry",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
        }
    }

    @Nested
    class TestingOfMethodGetResistBonus {
        @Nested
        class Positive{
            @Test
            void getResistBonusAgainstInfantryWhenIsForest() {
                EnumTerrain.setForest();
                assertEquals(6, new InfantryUnit("Infantry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenIsForest(){
                EnumTerrain.setForest();
                assertEquals(3, new InfantryUnit("Infantry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenIsForest(){
                EnumTerrain.setForest();
                assertEquals(8, new InfantryUnit("Infantry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenIsForest(){
                EnumTerrain.setForest();
                assertEquals(3, new InfantryUnit("Infantry",100).getResistBonus(new MagicianUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenIsVolcano() {
                EnumTerrain.setVolcano();
                assertEquals(-2, new InfantryUnit("Infantry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenIsVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(-5, new InfantryUnit("Infantry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenIsVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(0, new InfantryUnit("Infantry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenIsVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(-5, new InfantryUnit("Infantry",100).getResistBonus(new MagicianUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenIsHillOrPlains() {
                EnumTerrain.setPLAINS();
                assertEquals(3, new InfantryUnit("Infantry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenIsHillOrPlains(){
                EnumTerrain.setPLAINS();
                assertEquals(0, new InfantryUnit("Infantry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenIsHillOrPlains(){
                EnumTerrain.setPLAINS();
                assertEquals(5, new InfantryUnit("Infantry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenIsHillOrPlains(){
                EnumTerrain.setPLAINS();
                assertEquals(0, new InfantryUnit("Infantry",100).getResistBonus(new MagicianUnit("Cavalry",100)));
            }
        }
    }

    @Nested
    class TestingOfMethodClone{
        @Nested
        class Positive{
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
}