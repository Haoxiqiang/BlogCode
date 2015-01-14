package info.haoxiqiang.forth;

import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;

/**
 *
 * @author MikeW
 */
public class Test04Map {

  public static void main(String[] args) {
    List<Person> pl = Person.createShortList();
    
    SearchCriteria search = SearchCriteria.getInstance();
    
    // Calc average age of pilots old style
    System.out.println("== Calc Old Style ==");
    int sum = 0;
    int count = 0;
    
    for (Person p:pl){
      if (p.getAge() >= 23 && p.getAge() <= 65 ){
        sum = sum + p.getAge();
        count++;
      }
    }
    
    long average = sum / count;
    System.out.println("Total Ages: " + sum);
    System.out.println("Average Age: " + average);
    
    
    // Get sum of ages
    System.out.println("\n== Calc New Style ==");
    Predicate<Person> allPilots = p -> p.getAge() >= 23 && p.getAge() <= 65;
    long totalAge = pl
            .stream()
            .filter(allPilots)
            .mapToInt(p -> p.getAge())
            .sum();

    // Get average of ages
    OptionalDouble averageAge = pl
            .parallelStream()
            .filter(allPilots)
            .mapToDouble(p -> p.getAge())
            .average();

    System.out.println("Total Ages: " + totalAge);
    System.out.println("Average Age: " + averageAge.getAsDouble());    
    
  }
  
}
