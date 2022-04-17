package edu.ntnu.idatt2001.pedropca.wargames.util;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitFactoryTest {

    @Nested
    class CreateUnitTest{
        @Nested
        class Positive{
            @Test
            void createAInfantryUnit(){
                UnitFactory factory = new UnitFactory();
                Unit infantry = factory.createUnit("InfantryUnit","Infantry",100);
                assertTrue(infantry instanceof InfantryUnit);
            }
        }
    }

    @Test
    void createUnit() {
    }

    @Test
    void createAListOfUnits() {
    }
}