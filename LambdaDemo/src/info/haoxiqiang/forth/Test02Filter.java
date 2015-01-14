package info.haoxiqiang.forth;

import java.util.List;

/**
 *
 * @author MikeW
 */
public class Test02Filter {
  
  public static void main(String[] args) {

    List<Person> pl = Person.createShortList();
    
    SearchCriteria search = SearchCriteria.getInstance();
    
    System.out.println("\n=== Western Pilot Phone List ===");

    
    pl.stream().filter(search.allPilots("allPilots"))
      .forEach(Person::printWesternName);
    
    pl.stream().filter(p->p.getAge()>16)
    .forEach(Person::printWesternName);
    
   
    System.out.println("\n=== Eastern Draftee Phone List ===");

    pl.stream().filter(search.allPilots("allDraftees"))
      .forEach(Person::printEasternName);
    
  }
}
