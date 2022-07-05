package decorator;

import animals.Animal;
/*
 * class to change color of animals
 */

public class colorDecorator implements IpicDecorator {

    Animal natural_animal;

    public colorDecorator(Animal natural_animal) {
        this.natural_animal = natural_animal;
    }

    @Override
    public void changeAnimal(String color) {

        natural_animal.setColor(color);
        String nm = null;
        switch(natural_animal.getClass().getSimpleName()) {

            case "Lion":
                nm = "lio";
                break;

            case "Bear":
                nm = "bea";
                break;

            case "Elephant":
                nm = "elf";
                break;

            case "Girrafe":
                nm = "grf";
                break;

            case "Turtle":
                nm = "trt";
                break;

        }

        natural_animal.loadImages(nm);
    }


}
