package edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.RangedUnit;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumTerrain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HealerUnitTest {
    @Nested
    class TestOfGetAttackBonusMethod{
        @Nested
        class Positive{
            @Test
            void getAttackBonusAgainstCavalryWhenTheTerrainIsForest() {
                EnumTerrain.setForest();
                assertEquals(-7, new HealerUnit("Healer",100).getAttackBonus(new CavalryUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTheTerrainIsVolcano() {
                EnumTerrain.setVolcano();
                assertEquals(-10, new HealerUnit("Healer",100).getAttackBonus(new CavalryUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTheTerrainIsNotVolcanoAndNotForest() {
                EnumTerrain.setHILL();
                assertEquals(-5, new HealerUnit("Healer",100).getAttackBonus(new CavalryUnit(" Cavalry",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTheTerrainIsForest() {
                EnumTerrain.setForest();
                assertEquals(23, new HealerUnit("Healer",100).getAttackBonus(new MagicianUnit("Magician",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTheTerrainIsVolcano() {
                EnumTerrain.setVolcano();
                assertEquals(20, new HealerUnit("Healer",100).getAttackBonus(new MagicianUnit("Magician",100)));
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTheTerrainIsNotVolcanoAndNotForest() {
                EnumTerrain.setHILL();
                assertEquals(25, new HealerUnit("Healer",100).getAttackBonus(new MagicianUnit("Magician",100)));
            }
            @Test
            void getAttackBonusAgainstHealerRangedAndInfantryWhenTheTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(-5, new HealerUnit("Healer",100).getAttackBonus(new InfantryUnit("Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstHealerRangedAndInfantryWhenTheTerrainIsForest(){
                EnumTerrain.setForest();
                assertEquals(-2, new HealerUnit("Healer",100).getAttackBonus(new InfantryUnit("Infantry",100)));
            }
            @Test
            void getAttackBonusAgainstHealerRangedAndInfantryWhenTheTerrainIsNotForestAndNotVolcano(){
                EnumTerrain.setHILL();
                assertEquals(0, new HealerUnit("Healer",100).getAttackBonus(new InfantryUnit("Infantry",100)));
            }
        }
    }
    @Nested
    class TestOfGetResistBonusMethod{
        @Nested
        class Positive{
            @Test
            void getResistBonusAgainstCavalryWhenTheTerrainIsVolcano() {
                EnumTerrain.setVolcano();
                assertEquals(-5, new HealerUnit("Healer",100).getResistBonus(new CavalryUnit(" Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstCavalryWhenTheTerrainIsNotVolcano() {
                EnumTerrain.setPLAINS();
                assertEquals(0, new HealerUnit("Healer",100).getResistBonus(new CavalryUnit(" Cavalry",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenTheTerrainIsVolcano() {
                EnumTerrain.setVolcano();
                assertEquals(-2, new HealerUnit("Healer",100).getResistBonus(new InfantryUnit("Infantry",100)));
            }
            @Test
            void getResistBonusAgainstInfantryWhenTheTerrainIsNotVolcano() {
                EnumTerrain.setPLAINS();
                assertEquals(3, new HealerUnit("Healer",100).getResistBonus(new InfantryUnit("Infantry",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTheTerrainIsVolcano() {
                EnumTerrain.setVolcano();
                assertEquals(15, new HealerUnit("Healer",100).getResistBonus(new MagicianUnit("Magician",100)));
            }
            @Test
            void getResistBonusAgainstMagicianWhenTheTerrainIsNotVolcano() {
                EnumTerrain.setPLAINS();
                assertEquals(20, new HealerUnit("Healer",100).getResistBonus(new MagicianUnit("Magician",100)));
            }
            @Test
            void getResistBonusAgainstHealerAndRangedWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertEquals(0, new HealerUnit("Healer",100).getResistBonus(new HealerUnit("Healer",100)));
            }
            @Test
            void getResistBonusAgainstHealerAndRangedWhenTerrainIsNotVolcano(){
                EnumTerrain.setHILL();
                assertEquals(5, new HealerUnit("Healer",100).getResistBonus(new HealerUnit("Healer",100)));
            }
        }
    }
    @Nested
    class TestOfCloneMethod{
        @Nested
        class Positive{
            @Test
            void makingACloneOfADruid(){
                Unit Druid = new HealerUnit("Druid",100);
                Unit DruidCopy = Druid.clone();
                assertEquals(Druid.toString(),DruidCopy.toString());
            }
            @Test
            void makingACloneOfAPriest(){
                Unit Priest = new HealerUnit("Priest",100,30,12,4,75,50,200);
                Unit PriestClone = Priest.clone();
                assertEquals(Priest.toString(),PriestClone.toString());
            }

            @Test
            void makingACloneOfABard(){
                Unit Bard = new HealerUnit("Bard",125,15,12,1,70,25,150);
                Unit BardClone = Bard.clone();
                assertEquals(Bard.toString(),BardClone.toString());
            }
        }
    }
    @Nested
    class TestOfMagicSpell{
        @Nested
        class Positive{
            @Test
            void healAnGroupOfDamagedUnits(){
                List<Unit> unitMix = new ArrayList<>();
                for (int i =0;i<50;i++){
                    unitMix.add(new CavalryUnit("CAVALRY",100));
                    unitMix.add(new RangedUnit("Ranged",100));
                    unitMix.add(new InfantryUnit("Infantry",100));
                }
                unitMix.get(12).setHealth(50);
                new HealerUnit("Pries",100).magicSpell(unitMix);
                assertEquals(80,unitMix.get(12).getHealth());
            }
            @Test
            void tryToHealAnGroupOfUndamagedUnits(){
                List<Unit> unitMix = new ArrayList<>();
                for (int i =0;i<50;i++){
                    unitMix.add(new CavalryUnit("CAVALRY",100));
                    unitMix.add(new RangedUnit("Ranged",100));
                    unitMix.add(new InfantryUnit("Infantry",100));
                }
                HealerUnit priest= new HealerUnit("Pries",100);
                unitMix.get(12).setHealth(100);
                priest.magicSpell(unitMix);
                assertEquals(100,unitMix.get(12).getHealth());
                assertEquals(priest.getMaxMana(),priest.getMana());
            }
        }
    }

}