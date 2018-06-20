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

    StyrkParser styrkParser = StyrkParser.newInstance();

    List<Occupation> occupationList = styrkParser.getOccupationsFromFile();

    int TEST_OCCUPATION_1_INDEX = 1;

    assertEquals(testOccupation1, occupationList.get(TEST_OCCUPATION_1_INDEX));
  }

}
