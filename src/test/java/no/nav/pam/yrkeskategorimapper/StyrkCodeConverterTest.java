package no.nav.pam.yrkeskategorimapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Optional;
import no.nav.pam.yrkeskategorimapper.domain.Occupation;
import org.junit.Test;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class StyrkCodeConverterTest {

  private Occupation testOccupation1 = new Occupation(
      "110",
      "Offiserer fra fenrik og høyere grad",
      "Sikkerhet og beredskap",
      "Forsvar/militære"
  );

  private Optional optionalTestOccupation1 = Optional.of(testOccupation1);

  private Occupation testOccupation2 = new Occupation(
      "5131",
      "Servitører",
      "Reiseliv og mat",
      "Restaurant"
  );

  private Optional optionalTestOccupation2 = Optional.of(testOccupation2);

  private Occupation testOccupationZero = new Occupation(
      "0",
      "Ikke konvertert",
      "Uoppgitt/ ikke identifiserbare",
      "Ikke identifiserbare"
  );

  private Optional optionalTestOccupationZero = Optional.of(testOccupationZero);


  @Test
  public void styrkCodeConverterShouldReturnCorrectHashMap() throws IOException {

    StyrkCodeConverter styrkCodeConverter = StyrkCodeConverter.newInstance();

    assertTrue(optionalTestOccupation1.equals(styrkCodeConverter.lookup("110")));
    assertTrue(optionalTestOccupation2.equals(styrkCodeConverter.lookup("5131")));
  }

  @Test
  public void styrkCodeConverterShouldReturnOptionalEmptyIfKeyDoesNotExistInMap()
      throws IOException {
    StyrkCodeConverter styrkCodeConverter = StyrkCodeConverter.newInstance();

    String NON_EXISTENT_STYRK_CODE = "9999";
    Optional<Occupation> result = styrkCodeConverter.lookup(NON_EXISTENT_STYRK_CODE);

    assertFalse(result.isPresent());
  }

  @Test
  public void styrkCodeConverterShouldReturnOptionalEmptyIfKeyIsInvalid()
      throws IOException {
    StyrkCodeConverter styrkCodeConverter = StyrkCodeConverter.newInstance();

    String INVALID_STYRK_CODE = "INVALID STYRK CODE";
    Optional<Occupation> result = styrkCodeConverter.lookup(INVALID_STYRK_CODE);

    assertFalse(result.isPresent());
  }

  @Test
  public void styrkCodeConverterShouldRemoveExcessDigitsFromInputStringAndReturnValueForFourDigitKey()
      throws IOException {
    StyrkCodeConverter styrkCodeConverter = StyrkCodeConverter.newInstance();

    String SIX_DIGIT_STYRK_CODE = "513199";

    Optional<Occupation> result = styrkCodeConverter.lookup(SIX_DIGIT_STYRK_CODE);

    assertEquals(optionalTestOccupation2, result);
  }


  @Test
  public void styrkCodeConverterShouldReturnCorrectOccupationForThreeDigitStyrkCodes()
      throws IOException {
    StyrkCodeConverter styrkCodeConverter = StyrkCodeConverter.newInstance();

    String THREE_DIGIT_STYRK_CODE = "110";

    Optional<Occupation> result = styrkCodeConverter.lookup(THREE_DIGIT_STYRK_CODE);

    assertEquals(optionalTestOccupation1, result);
  }

  @Test
  public void styrkCodeConverterShouldReturnEmptyOccupationForStyrkCodeZero()
      throws IOException {
    StyrkCodeConverter styrkCodeConverter = StyrkCodeConverter.newInstance();

    String STYRK_CODE_ZERO = "0";

    Optional<Occupation> result = styrkCodeConverter.lookup(STYRK_CODE_ZERO);

    assertEquals(optionalTestOccupationZero, result);
  }
}
