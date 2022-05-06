package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicianUnit;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumTerrain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangedUnitTest {
    @Nested
    class TestingOfMethodGetAttackBonus {
        @Nested
        class Positive{
            @Test
            void getAttackBonusAgainstInfantryWhenTerrainIsPlainsOrVolcano() {
                EnumTerrain.setVolcano();
                assertEquals(7, new RangedUnit("Ranged",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenTerrainIsPlainsOrVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(4, new RangedUnit("Ranged",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTerrainIsPlainsOrVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(4, new RangedUnit("Ranged",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTerrainIsPlainsOrVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(4, new RangedUnit("Ranged",100).getAttackBonus(new MagicianUnit("Cavalry",100)));
            }

            @Test
            void getAttackBonusAgainstInfantryWhenTerrainIsHills() {
                EnumTerrain.setHILL();
                assertEquals(10, new RangedUnit("Ranged",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedTWhenTerrainIsHills(){
                EnumTerrain.setHILL();
                assertEquals(7, new RangedUnit("Ranged",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTerrainIsHills(){
                EnumTerrain.setHILL();
                assertEquals(7, new RangedUnit("Ranged",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTerrainIsHills(){
                EnumTerrain.setHILL();
                assertEquals(7, new RangedUnit("Ranged",100).getAttackBonus(new MagicianUnit("Cavalry",100)));
            }

            @Test
            void getAttackBonusAgainstInfantryWhenTerrainIsForest() {
                EnumTerrain.setForest();
                assertEquals(5, new RangedUnit("Ranged",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenTerrainIsForest(){
                EnumTerrain.setForest();
                assertEquals(2, new RangedUnit("Ranged",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTerrainIsForest(){
                EnumTerrain.setForest();
                assertEquals(2, new RangedUnit("Ranged",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTerrainIsForest(){
                EnumTerrain.setForest();
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
                EnumTerrain.setForest();
                assertEquals(2, new RangedUnit("Ranged",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsNotVolcano(){
                EnumTerrain.setForest();
                assertEquals(1, new RangedUnit("Ranged",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsNotVolcano(){
                EnumTerrain.setForest();
                assertEquals(0, new RangedUnit("Ranged",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsNotVolcano(){
                EnumTerrain.setForest();
                assertEquals(1, new RangedUnit("Ranged",100).getResistBonus(new MagicianUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenTerrainIsVolcano() {
                EnumTerrain.setVolcano();
                assertEquals(-3, new RangedUnit("Ranged",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(-4, new RangedUnit("Ranged",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(-5, new RangedUnit("Ranged",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
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