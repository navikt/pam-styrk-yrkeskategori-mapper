package no.nav.pam.yrkeskategorimapper;

import static org.junit.Assert.assertTrue;

import java.util.Map;
import no.nav.pam.yrkeskategorimapper.domain.Occupation;
import org.junit.Test;

public class HashMapGeneratorTest {

  private Occupation testOccupation1 = new Occupation(
      "110",
      "Offiserer fra fenrik og høyere grad",
      "Sikkerhet og beredskap",
      "Forsvar/militære"
  );

  private Occupation testOccupation2 = new Occupation(
      "5131",
      "Servitører",
      "Reiseliv og mat",
      "Restaurant"
  );

 @Test
 public void hashMapGeneratorShouldReturnCorrectHashMap() {

    Map<String, Occupation> map = HashMapGenerator
        .generateHashMap();

    assertTrue(testOccupation1.equals(map.get("110")));
    assertTrue(testOccupation2.equals(map.get("5131")));
  }

}
