package no.nav.pam.yrkeskategorimapper;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import no.nav.pam.yrkeskategorimapper.domain.Occupation;
import org.junit.Test;

public class StyrkCodeConverterTest {

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
  public void styrkCodeConverterShouldReturnCorrectHashMap() throws IOException {

    StyrkCodeConverter styrkCodeConverter = StyrkCodeConverter.newInstance();

    assertTrue(testOccupation1.equals(styrkCodeConverter.lookup("110")));
    assertTrue(testOccupation2.equals(styrkCodeConverter.lookup("5131")));
  }

}
