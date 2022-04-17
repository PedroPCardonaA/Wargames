package edu.ntnu.idatt2001.pedropca.wargames.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingletonTerrainTest {

    @Nested
    class getSingletonArmiesTest{

        @Nested
        class GetSingletonArmiesTest{

            @Nested
            class Positive{

                @Test
                void makingAnInstanceOfSingletonArmy(){
                    try {
                        SingletonTerrain.getSingletonTerrain();
                    }catch (Exception e){
                        fail();
                    }
                }

                @Test
                void checkIfSingletonArmiesIsUnique(){
                    SingletonTerrain singletonTerrain1 = SingletonTerrain.getSingletonTerrain();
                    singletonTerrain1.setVolcanoAsTerrain();
                    assertEquals("VOLCANO",singletonTerrain1.getTerrain());
                    SingletonTerrain singletonTerrain2 = SingletonTerrain.getSingletonTerrain();
                    assertEquals("VOLCANO",singletonTerrain2.getTerrain());
                }
            }
        }
    }

    @Nested
    class setForestAsTerrainTest{
        @Nested
        class Positive{
            @Test
            void setForestAsTerrain(){
                SingletonTerrain singletonTerrain1 = SingletonTerrain.getSingletonTerrain();
                singletonTerrain1.setForestAsTerrain();
                assertEquals("FOREST",singletonTerrain1.getTerrain());
            }
        }
    }
    @Nested
    class setHillAsTerrainTest{
        @Nested
        class Positive{
            @Test
            void setHillsAsTerrain(){
                SingletonTerrain singletonTerrain1 = SingletonTerrain.getSingletonTerrain();
                singletonTerrain1.setHillsAsTerrain();
                assertEquals("HILL",singletonTerrain1.getTerrain());
            }
        }
    }
    @Nested
    class setPlainsAsTerrainTest{
        @Nested
        class Positive{
            @Test
            void setPlainsAsTerrain(){
                SingletonTerrain singletonTerrain1 = SingletonTerrain.getSingletonTerrain();
                singletonTerrain1.setPlainsAsTerrain();
                assertEquals("PLAINS",singletonTerrain1.getTerrain());
            }
        }
    }
    @Nested
    class setVolcanoAsTerrainTest{
        @Nested
        class Positive{
            @Test
            void setVolcanoAsTerrain(){
                SingletonTerrain singletonTerrain1 = SingletonTerrain.getSingletonTerrain();
                singletonTerrain1.setVolcanoAsTerrain();
                assertEquals("VOLCANO",singletonTerrain1.getTerrain());
            }
        }
    }
}