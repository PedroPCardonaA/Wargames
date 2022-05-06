package edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.RangedUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumTerrain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagicianUnitTest {

    @Nested
    class GetAttackBonusTest{
        @Nested
        class Positive{
            @Test
            void getAttackBonusAgainstInfantryWhenTerrainIsVolcano() {
                EnumTerrain.setVolcano();
                assertTrue(new MagicianUnit("Magician",100).getAttackBonus(new InfantryUnit(" Infantry",100))>4
                        && new MagicianUnit("Magician",100).getAttackBonus(new InfantryUnit(" Infantry",100)) <31);
            }
            @Test
            void getAttackBonusAgainstRangedWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertTrue(new MagicianUnit("Magician",100).getAttackBonus(new RangedUnit("Ranged",100))>4
                        && new MagicianUnit("Magician",100).getAttackBonus(new RangedUnit("Ranged",100)) <31);
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertTrue(new MagicianUnit("Magician",100).getAttackBonus(new CavalryUnit("Cavalry",100))>4
                        && new MagicianUnit("Magician",100).getAttackBonus(new CavalryUnit("Cavalry",100)) <31);
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertTrue(new MagicianUnit("Magician",100).getAttackBonus(new MagicianUnit("Cavalry",100))>4
                        && new MagicianUnit("Magician",100).getAttackBonus(new MagicianUnit("Cavalry",100)) <31);
            }
            @Test
            void getAttackBonusAgainstInfantryWhenTerrainIsNotVolcano() {
                EnumTerrain.setHILL();
                assertTrue(new MagicianUnit("Magician",100).getAttackBonus(new InfantryUnit(" Infantry",100))>-1
                        && new MagicianUnit("Magician",100).getAttackBonus(new InfantryUnit(" Infantry",100)) <26);
            }
            @Test
            void getAttackBonusAgainstRangedWhenTerrainIsNotVolcano(){
                EnumTerrain.setHILL();
                assertTrue(new MagicianUnit("Magician",100).getAttackBonus(new RangedUnit("Ranged",100))>-1
                        && new MagicianUnit("Magician",100).getAttackBonus(new RangedUnit("Ranged",100)) <26);
            }
            @Test
            void getAttackBonusAgainstCavalryWhenTerrainIsNotVolcano(){
                EnumTerrain.setHILL();
                assertTrue(new MagicianUnit("Magician",100).getAttackBonus(new CavalryUnit("Cavalry",100))>-1
                        && new MagicianUnit("Magician",100).getAttackBonus(new CavalryUnit("Cavalry",100)) <26);
            }
            @Test
            void getAttackBonusAgainstMagicianWhenTerrainIsNotVolcano(){
                EnumTerrain.setHILL();
                assertTrue(new MagicianUnit("Magician",100).getAttackBonus(new MagicianUnit("Cavalry",100))>-1
                        && new MagicianUnit("Magician",100).getAttackBonus(new MagicianUnit("Cavalry",100)) <26);
            }
        }
    }

    @Nested
    class GetAttackResistTest{
        @Nested
        class Positive{
            @Test
            void getResistBonusAgainstInfantryWhenTerrainIsVolcano() {
                EnumTerrain.setVolcano();
                assertTrue(new MagicianUnit("Magician",100).getResistBonus(new InfantryUnit(" Infantry",100))>4
                        && new MagicianUnit("Magician",100).getResistBonus(new InfantryUnit(" Infantry",100)) <11);
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertTrue(new MagicianUnit("Magician",100).getResistBonus(new RangedUnit("Ranged",100))>4
                        && new MagicianUnit("Magician",100).getResistBonus(new RangedUnit("Ranged",100)) <11);
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertTrue(new MagicianUnit("Magician",100).getResistBonus(new CavalryUnit("Cavalry",100))>4
                        && new MagicianUnit("Magician",100).getResistBonus(new CavalryUnit("Cavalry",100)) <11);
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsVolcano(){
                EnumTerrain.setVolcano();
                assertTrue(new MagicianUnit("Magician",100).getResistBonus(new MagicianUnit("Cavalry",100))>4
                        && new MagicianUnit("Magician",100).getResistBonus(new MagicianUnit("Cavalry",100)) <11);
            }
            @Test
            void getResistBonusAgainstInfantryWhenTerrainIsNotVolcano() {
                EnumTerrain.setHILL();
                assertTrue(new MagicianUnit("Magician",100).getResistBonus(new InfantryUnit(" Infantry",100))>-1
                        && new MagicianUnit("Magician",100).getResistBonus(new InfantryUnit(" Infantry",100)) <6);
            }
            @Test
            void getResistBonusAgainstRangedWhenTerrainIsNotVolcano(){
                EnumTerrain.setHILL();
                assertTrue(new MagicianUnit("Magician",100).getResistBonus(new RangedUnit("Ranged",100))>-1
                        && new MagicianUnit("Magician",100).getResistBonus(new RangedUnit("Ranged",100)) <6);
            }
            @Test
            void getResistBonusAgainstCavalryWhenTerrainIsNotVolcano(){
                EnumTerrain.setHILL();
                assertTrue(new MagicianUnit("Magician",100).getResistBonus(new CavalryUnit("Cavalry",100))>-1
                        && new MagicianUnit("Magician",100).getResistBonus(new CavalryUnit("Cavalry",100)) <6);
            }
            @Test
            void getResistBonusAgainstMagicianWhenTerrainIsNotVolcano(){
                EnumTerrain.setHILL();
                assertTrue(new MagicianUnit("Magician",100).getResistBonus(new MagicianUnit("Cavalry",100))>-1
                        && new MagicianUnit("Magician",100).getResistBonus(new MagicianUnit("Cavalry",100)) <6);
            }
        }
    }

    @Nested
    class TestingOfMethodClone{
        @Nested
        class Positive{
            @Test
            void makingACloneOfAnArcher(){
                Unit spellsCaster = new MagicianUnit("SpellsCaster",100);
                Unit spellsCasterCopy = spellsCaster.clone();
                assertEquals(spellsCaster.toString(),spellsCasterCopy.toString());
            }
            @Test
            void makingACloneOfARifleman(){
                Unit summoner = new MagicianUnit("Summoner",100,30,12,4,75,50,200);
                Unit summonerClone = summoner.clone();
                assertEquals(summoner.toString(),summonerClone.toString());
            }

            @Test
            void makingACloneOfASpearman(){
                Unit warlock = new MagicianUnit("Warlock",125,15,12,1,70,25,150);
                Unit warlockClone = warlock.clone();
                assertEquals(warlock.toString(),warlockClone.toString());
            }
        }
    }

}