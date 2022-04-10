package edu.ntnu.idatt2001.pedropca.wargames.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumTerrainTest {
    @Nested
    class ValuesOfTheEnumTerrainTest{
        @Nested
        class Positive{
            @Test
            void getValueOfForest(){
                assertNotNull(EnumTerrain.Terrain.FOREST);
            }
            @Test
            void getValueOfPlains(){
                assertNotNull(EnumTerrain.Terrain.PLAINS);
            }
            @Test
            void getValueOfHill(){
                assertNotNull(EnumTerrain.Terrain.HILL);
            }
            @Test
            void getValueOfVolcano(){
                assertNotNull(EnumTerrain.Terrain.VOLCANO);
            }
        }
    }

}