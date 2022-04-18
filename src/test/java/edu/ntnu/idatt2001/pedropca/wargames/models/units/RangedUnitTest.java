package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonTerrain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangedUnitTest {
    SingletonTerrain singletonTerrain = SingletonTerrain.getSingletonTerrain();
    @Nested
    class TestingOfMethodGetAttackBonus {
        @Nested
        class Positive{
            @Test
            void getAttackBonusAgainstInfantryWhenTerrainIsPlainsOrVolcano() {
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(7, new RangedUnit("Ranged",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenTerrainIsPlainsOrVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(4, new RangedUnit("Ranged",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTerrainIsPlainsOrVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(4, new RangedUnit("Ranged",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTerrainIsPlainsOrVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(4, new RangedUnit("Ranged",100).getAttackBonus(new MagicianUnit("Cavalry",100)));
            }

            @Test
            void getAttackBonusAgainstInfantryWhenTerrainIsHills() {
                singletonTerrain.setHillsAsTerrain();
                assertEquals(10, new RangedUnit("Ranged",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedTWhenTerrainIsHills(){
                singletonTerrain.setHillsAsTerrain();
                assertEquals(7, new RangedUnit("Ranged",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTerrainIsHills(){
                singletonTerrain.setHillsAsTerrain();
                assertEquals(7, new RangedUnit("Ranged",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTerrainIsHills(){
                singletonTerrain.setHillsAsTerrain();
                assertEquals(7, new RangedUnit("Ranged",100).getAttackBonus(new MagicianUnit("Cavalry",100)));
            }

            @Test
            void getAttackBonusAgainstInfantryWhenTerrainIsForest() {
                singletonTerrain.setForestAsTerrain();
                assertEquals(5, new RangedUnit("Ranged",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenTerrainIsForest(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(2, new RangedUnit("Ranged",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTerrainIsForest(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(2, new RangedUnit("Ranged",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTerrainIsForest(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(2, new RangedUnit("Ranged",100).getAttackBonus(new MagicianUnit("Cavalry",100)));
            }
        }
    }
    @Nested
    class TestingOfMethodGetResistBonus {
        @Nested
        class Positive{
            @Test
            void getResistBonusAgainstInfantryWhenTerrainIsNotVolcano() {
                singletonTerrain.setForestAsTerrain();
                assertEquals(2, new RangedUnit("Ranged",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsNotVolcano(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(1, new RangedUnit("Ranged",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsNotVolcano(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(0, new RangedUnit("Ranged",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsNotVolcano(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(1, new RangedUnit("Ranged",100).getResistBonus(new MagicianUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenTerrainIsVolcano() {
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(-3, new RangedUnit("Ranged",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(-4, new RangedUnit("Ranged",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(-5, new RangedUnit("Ranged",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(-4, new RangedUnit("Ranged",100).getResistBonus(new MagicianUnit("Cavalry",100)));
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