package edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicianUnit;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumTerrain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CavalryUnitTest {
    @Nested
    class TestingOfMethodGetAttackBonus {
        @Nested
        class Positive{
            @Test
            void getAttackBonusAgainstInfantryWhenChargingAndTerrainIsNotPlains() {
                EnumTerrain.setVolcano();
                assertEquals(4, new CavalryUnit("Cavalry",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstInfantryWhenNotChargingAndTerrainIsNotPlains() {
                EnumTerrain.setVolcano();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(2, cavalryUnit.getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenChargingAndTerrainIsNotPlains(){
                EnumTerrain.setVolcano();
                assertEquals(8, new CavalryUnit("Cavalry",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenNotChargingAndTerrainIsNotPlains() {
                EnumTerrain.setVolcano();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(6, cavalryUnit.getAttackBonus(new RangedUnit(" Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenChargingAndTerrainIsNotPlains(){
                EnumTerrain.setVolcano();
                assertEquals(4, new CavalryUnit("Cavalry",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenNotChargingAndTerrainIsNotPlains() {
                EnumTerrain.setVolcano();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(2, cavalryUnit.getAttackBonus(new CavalryUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstInfantryWhenChargingAndTerrainIsPlains() {
                EnumTerrain.setPlains();
                assertEquals(7, new CavalryUnit("Cavalry",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstInfantryWhenNotChargingAndTerrainIsPlains() {
                EnumTerrain.setPlains();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(5, cavalryUnit.getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenChargingAndTerrainIsPlains(){
                EnumTerrain.setPlains();
                assertEquals(11, new CavalryUnit("Cavalry",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenNotChargingAndTerrainIsPlains() {
                EnumTerrain.setPlains();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(9, cavalryUnit.getAttackBonus(new RangedUnit(" Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenChargingAndTerrainIsPlains(){
                EnumTerrain.setPlains();
                assertEquals(7, new CavalryUnit("Cavalry",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenNotChargingAndTerrainIsPlains() {
                EnumTerrain.setPlains();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(5, cavalryUnit.getAttackBonus(new CavalryUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenNotChargingAndTerrainIsPlains() {
                EnumTerrain.setPlains();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(5, cavalryUnit.getAttackBonus(new MagicianUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenNotChargingAndTerrainIsNotPlains() {
                EnumTerrain.setVolcano();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(2, cavalryUnit.getAttackBonus(new MagicianUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenChargingAndTerrainIsPlains() {
                EnumTerrain.setPlains();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(true);
                assertEquals(7, cavalryUnit.getAttackBonus(new MagicianUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenChargingAndTerrainIsNotPlains() {
                EnumTerrain.setVolcano();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(true);
                assertEquals(4, cavalryUnit.getAttackBonus(new MagicianUnit(" Cavalry",100)));
            }
        }
    }

    @Nested
    class TestingOfMethodGetResistBonus {
        @Nested
        class Positive{
            @Test
            void getResistBonusAgainstInfantryWhenTerrainIsNotForestOrVolcano() {
                EnumTerrain.setHill();
                assertEquals(2, new CavalryUnit("Cavalry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsNotForestOrVolcano(){
                EnumTerrain.setHill();
                assertEquals(7, new CavalryUnit("Cavalry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsNotForestOrVolcano(){
                EnumTerrain.setHill();
                assertEquals(4, new CavalryUnit("Cavalry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsNotForestOrVolcano(){
                EnumTerrain.setHill();
                assertEquals(1, new CavalryUnit("Cavalry",100).getResistBonus(new MagicianUnit("Magician",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenTerrainIsVolcano() {
                EnumTerrain.setVolcano();
                assertEquals(-3, new CavalryUnit("Cavalry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(2, new CavalryUnit("Cavalry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(-1, new CavalryUnit("Cavalry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(-4, new CavalryUnit("Cavalry",100).getResistBonus(new MagicianUnit("Magician",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenTerrainIsForest() {
                EnumTerrain.setForest();
                assertEquals(0, new CavalryUnit("Cavalry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsForest(){
                EnumTerrain.setForest();
                assertEquals(0, new CavalryUnit("Cavalry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsForest(){
                EnumTerrain.setForest();
                assertEquals(0, new CavalryUnit("Cavalry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsForest(){
                EnumTerrain.setForest();
                assertEquals(0, new CavalryUnit("Cavalry",100).getResistBonus(new MagicianUnit("Magician",100)));
            }
        }
    }
    @Nested
    class TestingOfMethodClone{
        @Nested
        class Positive{
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
    }

    @Nested
    class TestingOfOverrideMethodAttack{
        @Nested
        class Positive{
            @Test
            void AttackOfACavalryThatIsCharging(){
                EnumTerrain.setForest();
                CavalryUnit cavalry = new CavalryUnit("Cavalry",100);
                cavalry.attack(new InfantryUnit("Infantry",100));
                assertEquals(false,cavalry.getCharging());
            }
            @Test
            void AttackOfACavalryThatIsNotCharging(){
                EnumTerrain.setForest();
                CavalryUnit cavalry = new CavalryUnit("Cavalry",100);
                cavalry.setCharging(false);
                cavalry.attack(new InfantryUnit("Infantry",100));
                assertEquals(false,cavalry.getCharging());
            }
        }
    }
}