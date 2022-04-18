package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonTerrain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfantryUnitTest {
    SingletonTerrain singletonTerrain = SingletonTerrain.getSingletonTerrain();
    @Nested
    class TestingOfMethodGetAttackBonus {
        @Nested
        class Positive{
            @Test
            void getAttackBonusAgainstInfantryWhenTheTerrainIsNotForest() {
                singletonTerrain.setHillsAsTerrain();
                assertEquals(0, new InfantryUnit("Infantry",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenTheTerrainIsNotForest(){
                singletonTerrain.setHillsAsTerrain();
                assertEquals(0, new InfantryUnit("Infantry",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTheTerrainIsNotForest(){
                singletonTerrain.setHillsAsTerrain();
                assertEquals(4, new InfantryUnit("Infantry",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTheTerrainIsNotForest(){
                singletonTerrain.setHillsAsTerrain();
                assertEquals(0, new InfantryUnit("Infantry",100).getAttackBonus(new MagicianUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenIsForest(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(9, new InfantryUnit("Infantry",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTheTerrainIsForest(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(5, new InfantryUnit("Infantry",100).getAttackBonus(new MagicianUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstInfantryWhenTheTerrainIsForest() {
                singletonTerrain.setForestAsTerrain();
                assertEquals(5, new InfantryUnit("Infantry",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenTheTerrainIsForest(){
                singletonTerrain.setForestAsTerrain();
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
                singletonTerrain.setForestAsTerrain();
                assertEquals(6, new InfantryUnit("Infantry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenIsForest(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(3, new InfantryUnit("Infantry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenIsForest(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(8, new InfantryUnit("Infantry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenIsForest(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(3, new InfantryUnit("Infantry",100).getResistBonus(new MagicianUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenIsVolcano() {
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(-2, new InfantryUnit("Infantry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenIsVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(-5, new InfantryUnit("Infantry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenIsVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(0, new InfantryUnit("Infantry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenIsVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(-5, new InfantryUnit("Infantry",100).getResistBonus(new MagicianUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenIsHillOrPlains() {
                singletonTerrain.setPlainsAsTerrain();
                assertEquals(3, new InfantryUnit("Infantry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenIsHillOrPlains(){
                singletonTerrain.setPlainsAsTerrain();
                assertEquals(0, new InfantryUnit("Infantry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenIsHillOrPlains(){
                singletonTerrain.setPlainsAsTerrain();
                assertEquals(5, new InfantryUnit("Infantry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenIsHillOrPlains(){
                singletonTerrain.setPlainsAsTerrain();
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