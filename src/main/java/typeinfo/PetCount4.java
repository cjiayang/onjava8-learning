package typeinfo;

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

public class PetCount4 {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Pet.class);
        Pets.stream()
                .limit(20)
                .peek(counter::count)
                .forEach(p -> System.out.print(
                        p.getClass().getSimpleName() + " "));
        System.out.println();
        System.out.println("n" + counter);
    }
}
