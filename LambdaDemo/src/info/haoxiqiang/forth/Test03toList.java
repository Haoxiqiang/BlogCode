package info.haoxiqiang.forth;

import info.haoxiqiang.first.Gender;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author MikeW
 */
public class Test03toList {
  
  public static void main(String[] args) {
    
    List<Person> pl = Person.createShortList();
    
    SearchCriteria search = SearchCriteria.getInstance();
    
    // Make a new list after filtering.
    Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
    List<Person> pilotList = pl
            .stream()
            .filter(allDraftees)
            .collect(Collectors.toList());
    
    System.out.println("\n=== Western Pilot Phone List ===");
    pilotList.forEach(Person::printWesternName);

  }

}
