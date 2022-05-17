package edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MagicUnitTest {
    @Nested
    class TestOfTheConstructor{
        @Nested
        class Positive{
            @Test
            void creatingAMagicUnitWithZeroMana(){
                try {
                    new MagicUnit("MagicUnit",100,15,10,2,
                            "Ranged",75,20,150,0) {
                        @Override
                        public void magicSpell(List<Unit> target) {

                        }

                        @Override
                        public int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        public int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        public Unit clone() {
                            return null;
                        }
                    };
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void creatingAMagicUnitWithHundredOfMana(){
                try {
                    new MagicUnit("MagicUnit",100,15,10,2,
                            "Ranged",75,20,150,100) {
                        @Override
                        public void magicSpell(List<Unit> target) {

                        }

                        @Override
                        public int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        public int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        public Unit clone() {
                            return null;
                        }
                    };
                }catch (Exception e){
                    fail();
                }
            }
        }
        @Nested
        class Negative{
            @Test
            void creatingAUnitWhitNegativeManaPoints(){
                try {
                    new MagicUnit("MagicUnit",100,15,10,2,
                            "Ranged",75,20,150,-100) {
                        @Override
                        public void magicSpell(List<Unit> target) {

                        }

                        @Override
                        public int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        public int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        public Unit clone() {
                            return null;
                        }
                    };
                    fail();
                }catch (Exception e){
                    assertEquals("The mana points cannot be negative. Defined it as zero or above zero!",e.getMessage());
                }
            }
        }
    }

    @Nested
    class TestOfMethodSetMana{
        @Nested
        class Positive{
            @Test
            void setZeroAsManaOfAMagicUnit(){
                try {
                    MagicUnit magicUnit =new MagicUnit("MagicUnit",100,15,10,2,
                            "Ranged",75,20,150,100) {
                        @Override
                        public void magicSpell(List<Unit> target) {

                        }

                        @Override
                        public int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        public int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        public Unit clone() {
                            return null;
                        }
                    };
                    magicUnit.setMana(0);
                    assertEquals(0,magicUnit.getMana());
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void setHundredAsManaOfAMagicUnit(){
                try {
                    MagicUnit magicUnit =new MagicUnit("MagicUnit",100,15,10,2,
                            "Ranged",75,20,150,1000) {
                        @Override
                        public void magicSpell(List<Unit> target) {

                        }

                        @Override
                        public int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        public int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        public Unit clone() {
                            return null;
                        }
                    };
                    magicUnit.setMana(100);
                    assertEquals(100,magicUnit.getMana());
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void tryingToSetManaOverMaxManaOfTheMagicUnit(){
                try {
                    MagicUnit magicUnit =new MagicUnit("MagicUnit",100,15,10,2,
                            "Ranged",75,20,150,100) {
                        @Override
                        public void magicSpell(List<Unit> target) {

                        }

                        @Override
                        public int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        public int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        public Unit clone() {
                            return null;
                        }
                    };
                    magicUnit.setMana(1000);
                    assertEquals(100,magicUnit.getMana());
                }catch (Exception e){
                    fail();
                }
            }
        }
        @Nested
        class Negative{
            @Test
            void setNegativeManaPoint(){
                try {
                    MagicUnit magicUnit =new MagicUnit("MagicUnit",100,15,10,2,
                            "Ranged",75,20,150,100) {
                        @Override
                        public void magicSpell(List<Unit> target) {

                        }

                        @Override
                        public int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        public int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        public Unit clone() {
                            return null;
                        }
                    };
                    magicUnit.setMana(-100);
                    fail();
                }catch (Exception e){
                    assertEquals("The mana points cannot be negative. Defined it as zero or above zero!",e.getMessage());
                }
            }
        }
    }

    @Nested
    class TestOfTheOverrideMethodAttack{
        @Nested
        class Positive{
            @Test
            void checkTheManaAfterTheManaRegenerationWhenTheUnitHasAllTheMana(){
                MagicUnit magicUnit = new MagicUnit("MagicUnit",100,15,10,2,
                        "Ranged",75,20,150,50) {
                    @Override
                    public void magicSpell(List<Unit> target) {

                    }

                    @Override
                    public int getAttackBonus(Unit opponent) {
                        return 0;
                    }

                    @Override
                    public int getResistBonus(Unit mainUnit) {
                        return 0;
                    }

                    @Override
                    public Unit clone() {
                        return null;
                    }
                };
                magicUnit.attack(new HealerUnit("Healer",100));
                assertEquals(50,magicUnit.getMana());
            }
            @Test
            void checkTheManaAfterTheManaRegenerationWhenTheUnitDoesNotHaveAllTheMana(){
                MagicUnit magicUnit = new MagicUnit("MagicUnit",100,15,10,2,
                        "Ranged",75,20,150,50) {
                    @Override
                    public void magicSpell(List<Unit> target) {

                    }

                    @Override
                    public int getAttackBonus(Unit opponent) {
                        return 0;
                    }

                    @Override
                    public int getResistBonus(Unit mainUnit) {
                        return 0;
                    }

                    @Override
                    public Unit clone() {
                        return null;
                    }
                };
                magicUnit.setMana(0);
                magicUnit.attack(new HealerUnit("Healer",100));
                assertEquals(5,magicUnit.getMana());
            }
        }
    }
}
