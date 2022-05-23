package edu.ntnu.idatt2001.pedropca.wargames.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumTerrainTest {
    @Nested
    class TestOfSetForestMethod{
        @Nested
        class positive{
            @Test
            void setForestAsTerrain(){
                EnumTerrain.setForest();
                assertEquals(EnumTerrain.FOREST,EnumTerrain.getTerrain());
            }
        }
    }

    @Nested
    class TestOfSetHillMethod{
        @Nested
        class positive{
            @Test
            void setHillAsTerrain(){
                EnumTerrain.setHill();
                assertEquals(EnumTerrain.HILL,EnumTerrain.getTerrain());
            }
        }
    }

    @Nested
    class TestOfSetPlainsMethod{
        @Nested
        class positive{
            @Test
            void setPlainsAsTerrain(){
                EnumTerrain.setPlains();
                assertEquals(EnumTerrain.PLAINS,EnumTerrain.getTerrain());
            }
        }
    }

    @Nested
    class TestOfSetVolcanoMethod{
        @Nested
        class positive{
            @Test
            void setVolcanoAsTerrain(){
                EnumTerrain.setVolcano();
                assertEquals(EnumTerrain.VOLCANO,EnumTerrain.getTerrain());
            }
        }
    }

}