package edu.ntnu.idatt2001.pedropca.wargames;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.CommanderUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.RangedUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Nested
    class TestingMethodSimulate{
        @Test
        void simulatingABattleWhereTheHordeWins(){
            Army horde = new Army("Horde");
            Army alliance = new Army("Alliance");
            for(int i =0;i<5;i++){
                horde.add(new CavalryUnit("Cavalry",100));
                horde.add(new CavalryUnit("Cavalry",100));
                horde.add(new RangedUnit("Ranged",100));
                horde.add(new RangedUnit("Ranged",100));
                alliance.add(new InfantryUnit("Infantry",100));
            }
            Battle battle = new Battle(horde,alliance);
            assertEquals(horde,battle.simulate());
        }
        @Test
        void simulatingABattleWhereTheAllianceWins(){
            Army horde = new Army("Horde");
            Army alliance = new Army("Alliance");
            for(int i =0;i<5;i++){
                alliance.add(new CavalryUnit("Cavalry",100));
                alliance.add(new CavalryUnit("Cavalry",100));
                horde.add(new RangedUnit("Ranged",100));
                alliance.add(new InfantryUnit("Infantry",100));
            }
            Battle battle = new Battle(horde,alliance);
            assertEquals(alliance,battle.simulate());
        }
        @Test
        void simulatingABattleWhereMayaWins(){
            Army maya = new Army("Maya");
            Army aztecas = new Army("Aztecas");
            for(int i =0;i<5;i++){
                maya.add(new CavalryUnit("Cavalry",100));
                maya.add(new CavalryUnit("Cavalry",100));
                aztecas.add(new RangedUnit("Ranged",100));
                aztecas.add(new InfantryUnit("Infantry",100));
                maya.add(new InfantryUnit("Infantry",100));
            }
            Battle battle = new Battle(maya,aztecas);
            assertEquals(maya,battle.simulate());
        }
        @Test
        void simulatingABattleWhereAztecasWins(){
            Army maya = new Army("Maya");
            Army aztecas = new Army("Aztecas");
            for(int i =0;i<5;i++){
                maya.add(new CavalryUnit("Cavalry",100));
                maya.add(new CavalryUnit("Cavalry",100));
                aztecas.add(new RangedUnit("Ranged",100));
                aztecas.add(new InfantryUnit("Infantry",100));
                maya.add(new InfantryUnit("Infantry",100));
                aztecas.add(new CommanderUnit("Commander",100));
                aztecas.add(new CommanderUnit("Commander",100));
            }
            Battle battle = new Battle(maya,aztecas);
            assertEquals(aztecas,battle.simulate());
        }
        @Test
        void simulatingABattleWithOutWinner(){
            Army horde = new Army("Horde");
            Army alliance = new Army("Alliance");
            Battle battle = new Battle(horde,alliance);
            assertNull(battle.simulate());
        }
    }
    @Nested
    class TestingMethodToString{
        @Test
        void GetToStringOfABattleBetweenTheAllianceAndTheHorde(){
            Battle battle = new Battle(new Army("Alliance"),new Army("Horde"));
            assertEquals("Battle between " + "Alliance" + " and " + "Horde" + ".",battle.toString());
        }
        @Test
        void GetToStringOfABattleBetweenTheHordeAndTheAlliance(){
            Battle battle = new Battle(new Army("Horde"),new Army("Alliance"));
            assertEquals("Battle between " + "Horde" + " and " + "Alliance" + ".",battle.toString());
        }
        @Test
        void GetToStringOfABattleBetweenUkraineAndRussia(){
            Battle battle = new Battle(new Army("Ukraine"),new Army("Russia"));
            assertEquals("Battle between " + "Ukraine" + " and " + "Russia" + ".",battle.toString());
        }
    }
}