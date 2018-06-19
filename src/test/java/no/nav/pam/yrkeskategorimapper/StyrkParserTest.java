package no.nav.pam.yrkeskategorimapper;

import static org.junit.Assert.assertEquals;

import no.nav.pam.yrkeskategorimapper.domain.Occupation;
import java.util.List;
import org.junit.Test;


public class StyrkParserTest {

  @Test
  public void styrkParserShouldReturnCorrectCategoryCodes() {

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

    String mappingFileLocation = "/styrk_category_mapping.csv";

    no.nav.pam.yrkeskategorimapper.StyrkParser styrkParser = new no.nav.pam.yrkeskategorimapper.StyrkParser();

    List<Occupation> occupations = styrkParser.parseMappingFile(mappingFileLocation);

    assertEquals(testOccupation1.getStyrkCode(), occupations.get(1).getStyrkCode());
    assertEquals(testOccupation1.getStyrkDescription(), occupations.get(1).getStyrkDescription());
    assertEquals(testOccupation1.getCategoryLevel1(), occupations.get(1).getCategoryLevel1());
    assertEquals(testOccupation1.getCategoryLevel2(), occupations.get(1).getCategoryLevel2());

    assertEquals(testOccupation2.getStyrkCode(), occupations.get(339).getStyrkCode());
    assertEquals(testOccupation2.getStyrkDescription(), occupations.get(339).getStyrkDescription());
    assertEquals(testOccupation2.getCategoryLevel1(), occupations.get(339).getCategoryLevel1());
    assertEquals(testOccupation2.getCategoryLevel2(), occupations.get(339).getCategoryLevel2());

  }

  @Test(expected = NullPointerException.class)
  public void styrkParserShouldThrowExceptionAndLogErrorMessageIfInputFileDoesntExist() {

    String INVALID_FILE_LOCATION = "FooBar";
    no.nav.pam.yrkeskategorimapper.StyrkParser styrkParser = new no.nav.pam.yrkeskategorimapper.StyrkParser();

    styrkParser.parseMappingFile(INVALID_FILE_LOCATION);


  }

}
