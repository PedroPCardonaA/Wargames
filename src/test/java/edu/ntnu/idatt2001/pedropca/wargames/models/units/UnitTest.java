package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    @Nested
    class ConstructorTesting{
        @Nested
        class Positive {
            @Test
            void CreatingAUnitWithSupportedData(){
                try {
                    new Unit(" Footman",100,15,10,2,
                            "Melee",75,20,150) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                }catch (Exception e){
                    fail(e.getMessage());
                }
            }
            @Test
            void CreatingAUnitWithTheLowerBoundOfDefinedZone(){
                try {
                    new Unit(" Footman",0,0,0,0,"Melee",0
                            ,0,100) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                }catch (Exception e){
                    fail(e.getMessage());
                }
            }

            @Test
            void CreatingAUnitWithTheHigherBoundOfDefinedZone(){
                try {
                    new Unit(" Footman",110,1110,11110,11110,"Melee",100,100,10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                }catch (Exception e){
                    fail(e.getMessage());
                }
            }
        }

        @Nested
        class Negative {
            @Test
            void CreatingAUnitWithOutName() {
                try {
                    new Unit("", 110, 1110, 11110, 11110, "Melee", 100, 100, 10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals( "All unit must have a name. Define a name for the unit.",e.getMessage());
                }
            }

            @Test
            void CreatingAUnitWithHealthLowerThanZero() {
                try {
                    new Unit("Footman", -10, 1110, 11110, 11110, "Melee", 100, 100, 10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals( "The health points of a unit cannot be lower than 0. Define the health points above 0.",e.getMessage());
                }
            }

            @Test
            void CreatingAUnitWithAttackLowerThanZero() {
                try {
                    new Unit("Footman", 10, -1110, 11110, 11110, "Melee", 100, 100, 10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals( "The attack points of a unit cannot be lower than 0. Define the attack points above 0.",e.getMessage());
                }
            }

            @Test
            void CreatingAUnitWithArmorLowerThanZero() {
                try {
                    new Unit("Footman", 10, 1110, -11110, 11110, "Melee", 100, 100, 10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals( "The armor points of a unit cannot be lower than 0. Define the armor points above 0.",e.getMessage());
                }
            }

            @Test
            void CreatingAUnitWithAttackSpeedLowerThanZero() {
                try {
                    new Unit("Footman", 10, 1110, 11110, -11110, "Melee", 100, 100, 10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals( "The attack speed of a unit cannot be lower than 0. Define the attack speed above 0.",e.getMessage());
                }
            }

            @Test
            void CreatingAUnitWithHitRateLowerThanZero() {
                try {
                    new Unit("Footman", 10, 1110, 11110, 11110, "Melee", -100, 100, 10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals("The hit rate of a unit cannot be lower than 0. Define the hit rate between 0 to 100.",e.getMessage());
                }
            }

            @Test
            void CreatingAUnitWithHitRateAboveHundred() {
                try {
                    new Unit("Footman", 10, 1110, 11110, 11110, "Melee", 1100, 100, 10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals("The hit rate of a unit cannot be above than 100. Define the hit rate between 0 to 100.",e.getMessage());
                }
            }

            @Test
            void CreatingAUnitWithCriticRateLowerThanZero() {
                try {
                    new Unit("Footman", 10, 1110, 11110, 11110, "Melee", 100, -100, 10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals("The critic rate of a unit cannot be lower than 0. Define the hit rate between 0 to 100.",e.getMessage());
                }
            }

            @Test
            void CreatingAUnitWithCriticRateAboveHundred() {
                try {
                    new Unit("Footman", 10, 1110, 11110, 11110, "Melee", 100, 1100, 10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals("The critic rate of a unit cannot be above than 100. Define the hit rate between 0 to 100.",e.getMessage());
                }
            }

            @Test
            void CreatingAUnitWithCriticDamageLowerThanHundred() {
                try {
                    new Unit("Footman", 10, 1110, 11110, 11110, "Melee", 100, 100, -10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals("The critic damage must be above 100 because it represent how much extra damage is made in comparison of a norma damage. Define the critic damage above 100.",e.getMessage());
                }
            }
        }
    }

    @Nested
    class TestingOfMethodSetHealth {
        @Nested
        class Positive{
            @Test
            void SetHealthOfAUnitToAboveZero(){
                Unit testUnit = new Unit("Footman", 10, 1110, 11110, 11110, "Melee", 100, 100, 10000) {
                    @Override
                    protected int getAttackBonus(Unit opponent) {
                        return 0;
                    }

                    @Override
                    protected int getResistBonus(Unit mainUnit) {
                        return 0;
                    }

                    @Override
                    protected Unit clone() {
                        return null;
                    }
                };
                testUnit.setHealth(100);
                assertEquals(100,testUnit.getHealth());
            }
            @Test
            void SetHealthOfAUnitToZero(){
                Unit testUnit = new Unit("Footman", 10, 1110, 11110, 11110, "Melee", 100, 100, 10000) {
                    @Override
                    protected int getAttackBonus(Unit opponent) {
                        return 0;
                    }

                    @Override
                    protected int getResistBonus(Unit mainUnit) {
                        return 0;
                    }

                    @Override
                    protected Unit clone() {
                        return null;
                    }
                };
                testUnit.setHealth(0);
                assertEquals(0,testUnit.getHealth());
            }
        }
        @Nested
        class Negative{
            @Test
            void SetHealthOfAUnitUnderZero(){
                try {
                    Unit testUnit=  new Unit("Footman", 10, 1110, 11110, 11110, "Melee", 100, 100, 10000) {
                        @Override
                        protected int getAttackBonus(Unit opponent) {
                            return 0;
                        }

                        @Override
                        protected int getResistBonus(Unit mainUnit) {
                            return 0;
                        }

                        @Override
                        protected Unit clone() {
                            return null;
                        }
                    };
                    testUnit.setHealth(-100);
                    fail("Method did not throw IllegalArgumentException as expected");
                } catch (IllegalArgumentException e) {
                    assertEquals("The health points of a unit cannot be lower than 0. Define the health points above 0.",e.getMessage());
                }
            }
        }
    }
    @Nested
    class TestingOfMethodToString{
        @Nested
        class Positive{
            @Test
            void GetTheStringOfAFootman(){
                Unit footman = new Unit(" Footman",100,15,10,2,
                        "Melee",75,20,150) {
                    @Override
                    protected int getAttackBonus(Unit opponent) {
                        return 0;
                    }

                    @Override
                    protected int getResistBonus(Unit mainUnit) {
                        return 0;
                    }

                    @Override
                    protected Unit clone() {
                        return null;
                    }
                };

                assertEquals(String.format("%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|"
                        ,"Footman",100,"Melee",15, 10,2,75,20,150), footman.toString());
            }
            @Test
            void GetTheStringOfASpearman(){
                Unit footman = new Unit(" Spearman",80,25,5,3,
                        "Ranged",60,40,175) {
                    @Override
                    protected int getAttackBonus(Unit opponent) {
                        return 0;
                    }

                    @Override
                    protected int getResistBonus(Unit mainUnit) {
                        return 0;
                    }

                    @Override
                    protected Unit clone() {
                        return null;
                    }
                };

                assertEquals(String.format("%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|"
                        ,"Spearman",80,"ranged",25, 5,3,60,40,175), footman.toString());
            }
            @Test
            void GetTheStringOfMountainKing(){
                Unit footman = new Unit(" Mountain king",180,30,20,2,
                        "Melee",90,30,175) {
                    @Override
                    protected int getAttackBonus(Unit opponent) {
                        return 0;
                    }

                    @Override
                    protected int getResistBonus(Unit mainUnit) {
                        return 0;
                    }

                    @Override
                    protected Unit clone() {
                        return null;
                    }
                };

                assertEquals(String.format("%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|"
                        ,"Mountain king",180,"Melee",30, 20,2,90,30,175), footman.toString());
            }
        }
    }
}