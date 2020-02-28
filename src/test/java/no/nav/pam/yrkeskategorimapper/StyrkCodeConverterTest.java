package no.nav.pam.yrkeskategorimapper;

import no.nav.pam.yrkeskategorimapper.domain.Occupation;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;

public class StyrkCodeConverterTest {

    private final Occupation testOccupation1 = new Occupation(
            "110",
            "Offiserer fra fenrik og høyere grad",
            "Sikkerhet og beredskap",
            "Forsvar/militære"
    );

    private final Occupation testOccupation2 = new Occupation(
            "5131",
            "Servitører",
            "Reiseliv og mat",
            "Restaurant"
    );

    private final Occupation testOccupation3 = new Occupation(
            "2342",
            "Førskolelærere",
            "Utdanning",
            "Barnehage"
    );

    private final Occupation testOccupationZero = new Occupation(
            "0",
            "Ikke konvertert",
            "Uoppgitt/ ikke identifiserbare",
            "Ikke identifiserbare"
    );

    StyrkCodeConverter styrkCodeConverter = new StyrkCodeConverter();

    @Test
    public void styrkCodeConverterShouldReturnCorrectHashMap() throws IOException {

        assertTrue(Optional.of(testOccupation1).equals(styrkCodeConverter.lookup("110")));
        assertTrue(Optional.of(testOccupation2).equals(styrkCodeConverter.lookup("5131")));
        assertTrue(Optional.of(testOccupation3).equals(styrkCodeConverter.lookup("2342.03")));
        assertTrue(Optional.of(testOccupation3).equals(styrkCodeConverter.lookupPyrk("2342")));
        System.out.println(styrkCodeConverter.showPyrkOccupations());
    }

    @Test
    public void styrkCodeConverterShouldReturnOptionalEmptyIfKeyDoesNotExistInMap()
            throws IOException {

        String NON_EXISTENT_STYRK_CODE = "9999";
        Optional<Occupation> result = styrkCodeConverter.lookup(NON_EXISTENT_STYRK_CODE);

        assertFalse(result.isPresent());
    }

    @Test
    public void styrkCodeConverterShouldReturnOptionalEmptyIfKeyIsInvalid()
            throws IOException {

        String INVALID_STYRK_CODE = "INVALID STYRK CODE";
        Optional<Occupation> result = styrkCodeConverter.lookup(INVALID_STYRK_CODE);

        assertFalse(result.isPresent());
    }

    @Test
    public void styrkCodeConverterShouldRemoveExcessDigitsFromInputStringAndReturnValueForFourDigitKey()
            throws IOException {

        String SIX_DIGIT_STYRK_CODE = "513199";

        Optional<Occupation> result = styrkCodeConverter.lookup(SIX_DIGIT_STYRK_CODE);

        assertEquals(Optional.of(testOccupation2), result);
    }


    @Test
    public void styrkCodeConverterShouldReturnCorrectOccupationForThreeDigitStyrkCodes()
            throws IOException {

        String THREE_DIGIT_STYRK_CODE = "110";

        Optional<Occupation> result = styrkCodeConverter.lookup(THREE_DIGIT_STYRK_CODE);

        assertEquals(Optional.of(testOccupation1), result);
    }

    @Test
    public void styrkCodeConverterShouldReturnEmptyOccupationForStyrkCodeZero()
            throws IOException {

        String STYRK_CODE_ZERO = "0";

        Optional<Occupation> result = styrkCodeConverter.lookup(STYRK_CODE_ZERO);

        assertEquals(Optional.of(testOccupationZero), result);
    }

    @Test(expected = NullPointerException.class)
    public void styrkCodeConverterShouldThrowExceptionForNullInput()
            throws IOException {

        styrkCodeConverter.lookup(null);
    }
}
