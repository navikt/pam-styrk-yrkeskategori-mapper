package no.nav.pam.yrkeskategorimapper;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import no.nav.pam.yrkeskategorimapper.domain.Occupation;
import java.util.List;
import org.junit.Test;


public class StyrkParserTest {

  @Test
  public void styrkParserShouldReturnCorrectCategoryCodes() throws IOException {

    Occupation testOccupation1 = new Occupation(
        "110",
        "Offiserer fra fenrik og høyere grad",
        "Sikkerhet og beredskap",
        "Forsvar/militære"
    );

    Occupation testOccupation2 = new Occupation(
        "5131",
        "Servitører",
        "Reiseliv og mat",
        "Restaurant"
    );

    StyrkParser styrkParser = StyrkParser.newInstance();

    List<Occupation> occupationList = styrkParser.getOccupationsFromFile();

    int testOccupation1Index = 1;
    int testOccupation2Index = 339;

    assertEquals(testOccupation1, occupationList.get(testOccupation1Index));
    assertEquals(testOccupation2, occupationList.get(testOccupation2Index));
  }

}
