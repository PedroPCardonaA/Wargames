package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonTerrain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CavalryUnitTest {
    SingletonTerrain singletonTerrain = SingletonTerrain.getSingletonTerrain();
    @Nested
    class TestingOfMethodGetAttackBonus {
        @Nested
        class Positive{
            @Test
            void getAttackBonusAgainstInfantryWhenChargingAndTerrainIsNotPlains() {
                singletonTerrain.setForestAsTerrain();
                assertEquals(4, new CavalryUnit("Cavalry",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstInfantryWhenNotChargingAndTerrainIsNotPlains() {
                singletonTerrain.setForestAsTerrain();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(2, cavalryUnit.getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenChargingAndTerrainIsNotPlains(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(8, new CavalryUnit("Cavalry",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenNotChargingAndTerrainIsNotPlains() {
                singletonTerrain.setForestAsTerrain();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(6, cavalryUnit.getAttackBonus(new RangedUnit(" Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenChargingAndTerrainIsNotPlains(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(4, new CavalryUnit("Cavalry",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenNotChargingAndTerrainIsNotPlains() {
                singletonTerrain.setForestAsTerrain();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(2, cavalryUnit.getAttackBonus(new CavalryUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstInfantryWhenChargingAndTerrainIsPlains() {
                singletonTerrain.setPlainsAsTerrain();
                assertEquals(7, new CavalryUnit("Cavalry",100).getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstInfantryWhenNotChargingAndTerrainIsPlains() {
                singletonTerrain.setPlainsAsTerrain();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(5, cavalryUnit.getAttackBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenChargingAndTerrainIsPlains(){
                singletonTerrain.setPlainsAsTerrain();
                assertEquals(11, new CavalryUnit("Cavalry",100).getAttackBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstRangedWhenNotChargingAndTerrainIsPlains() {
                singletonTerrain.setPlainsAsTerrain();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(9, cavalryUnit.getAttackBonus(new RangedUnit(" Ranged",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenChargingAndTerrainIsPlains(){
                singletonTerrain.setPlainsAsTerrain();
                assertEquals(7, new CavalryUnit("Cavalry",100).getAttackBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenNotChargingAndTerrainIsPlains() {
                singletonTerrain.setPlainsAsTerrain();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(5, cavalryUnit.getAttackBonus(new CavalryUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenNotChargingAndTerrainIsPlains() {
                singletonTerrain.setPlainsAsTerrain();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(5, cavalryUnit.getAttackBonus(new MagicianUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenNotChargingAndTerrainIsNotPlains() {
                singletonTerrain.setVolcanoAsTerrain();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(false);
                assertEquals(2, cavalryUnit.getAttackBonus(new MagicianUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenChargingAndTerrainIsPlains() {
                singletonTerrain.setPlainsAsTerrain();
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
                cavalryUnit.setCharging(true);
                assertEquals(7, cavalryUnit.getAttackBonus(new MagicianUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenChargingAndTerrainIsNotPlains() {
                singletonTerrain.setVolcanoAsTerrain();
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
                singletonTerrain.setPlainsAsTerrain();
                assertEquals(2, new CavalryUnit("Cavalry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsNotForestOrVolcano(){
                singletonTerrain.setPlainsAsTerrain();
                assertEquals(7, new CavalryUnit("Cavalry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsNotForestOrVolcano(){
                singletonTerrain.setPlainsAsTerrain();
                assertEquals(4, new CavalryUnit("Cavalry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsNotForestOrVolcano(){
                singletonTerrain.setPlainsAsTerrain();
                assertEquals(1, new CavalryUnit("Cavalry",100).getResistBonus(new MagicianUnit("Magician",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenTerrainIsVolcano() {
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(-3, new CavalryUnit("Cavalry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(2, new CavalryUnit("Cavalry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(-1, new CavalryUnit("Cavalry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsVolcano(){
                singletonTerrain.setVolcanoAsTerrain();
                assertEquals(-4, new CavalryUnit("Cavalry",100).getResistBonus(new MagicianUnit("Magician",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenTerrainIsForest() {
                singletonTerrain.setForestAsTerrain();
                assertEquals(0, new CavalryUnit("Cavalry",100).getResistBonus(new InfantryUnit(" Infantry",100)));
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsForest(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(0, new CavalryUnit("Cavalry",100).getResistBonus(new RangedUnit("Ranged",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsForest(){
                singletonTerrain.setForestAsTerrain();
                assertEquals(0, new CavalryUnit("Cavalry",100).getResistBonus(new CavalryUnit("Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsForest(){
                singletonTerrain.setForestAsTerrain();
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
                singletonTerrain.setPlainsAsTerrain();
                CavalryUnit cavalry = new CavalryUnit("Cavalry",100);
                cavalry.attack(new InfantryUnit("Infantry",100));
                assertEquals(false,cavalry.getCharging());
            }
            @Test
            void AttackOfACavalryThatIsNotCharging(){
                singletonTerrain.setPlainsAsTerrain();
                CavalryUnit cavalry = new CavalryUnit("Cavalry",100);
                cavalry.setCharging(false);
                cavalry.attack(new InfantryUnit("Infantry",100));
                assertEquals(false,cavalry.getCharging());
            }
        }
    }
}