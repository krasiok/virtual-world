//package com.virtual_world.animal;
//
//import com.virtual_world.Direction;
//import com.virtual_world.Position;
//import com.virtual_world.RandomUtil;
//import com.virtual_world.World;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//public class AnimalTest {
//
//    Animal animal;
//
//    // TODO kilka przypadkow w ramach jednej logiki (czyli np. niewychodzenia poza plansze)
//    // mozna sprawdzic testem parametryzowanym
//    @Test
//    void action() {
//
//        // given
//        Position position = new Position(0,0);
//        World world = Mockito.mock(World.class);
//        Mockito.when(world.getColumns())
//                .thenReturn(10);
//        Mockito.when(world.getRows())
//                .thenReturn(10);
//
//        RandomUtil randomUtil = Mockito.mock(RandomUtil.class);
//        Mockito.when(randomUtil.getRandomDirection(Mockito.any()))
//                .thenReturn(Direction.LEFT)
//                .thenReturn(Direction.DOWN);
//
//        animal = createAnimalStub(position, world);
//        animal.setRandomUtil(randomUtil);
//
//        // when
//        animal.action();
//
//        // then
//        Assertions.assertNotEquals(position, animal.getPosition());
//
//    }
//
//    Animal createAnimalStub(Position position, World world) {
//        return new AnimalStub(null, position, world);
//    }
//
//    class AnimalStub extends Animal {
//
//        public AnimalStub(AnimalType animalType, Position position, World world) {
//            super(animalType, position, world);
//        }
//    }
//
//
//}
