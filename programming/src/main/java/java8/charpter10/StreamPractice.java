package java8.charpter10;

import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;

public class StreamPractice {
    public static void main(String[] args) {
        Person person = new Person();
        Car car = new Car();
        Insurance insurance = new Insurance();
        person.setCar(car);
        car.setInsurance(insurance);

        Optional.ofNullable(car).map(Car::getInsurance)
                                            .map(Insurance::getName)
                                            .ifPresent(s -> System.out.println(s));

        //if(insurance != null && "CambridgeInsurance".equals(insurance.getName()))
        Optional.ofNullable(insurance).filter(i -> "CambridgeInsurance".equals(i.getName()))
                            .ifPresent(x -> System.out.println("ok"));


        HashMap<Object, Object> map = new HashMap<>();
        Optional<Object> value = Optional.ofNullable(map.get("key"));

//        assertEquals(5, readDuration(param, "a"));
//        assertEquals(0, readDuration(param, "b"));
//        assertEquals(0, readDuration(param, "c"));
//        assertEquals(0, readDuration(param, "d"));

    }

    public int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalUtility::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                    .map(Person::getCar)
                    .map(Car::getInsurance)
                    .map(Insurance::getName)
                    .orElse("Unknown");
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person,
                                                             Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurace(p, c)));
    }

    private Insurance findCheapestInsurace(Person person, Car car) {
        return null;
    }

}
