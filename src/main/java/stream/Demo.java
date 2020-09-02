package stream;

import java.util.Optional;

public class Demo {
    public static void main(String[] args) {
        Person person = new Person();
        Optional<Person> optPerson = Optional.of(person);
        Optional<Optional<Car>> car = optPerson.map(Person::getCar);
        Insurance insurance = new Insurance();
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);

    }
}
