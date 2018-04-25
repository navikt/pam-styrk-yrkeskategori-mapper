import no.nav.pam.styrk.StyrkMappingParser;
import no.nav.pam.styrk.domain.PamStyrkkode;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Map;

public class StyrkMappingParserTest {

    private String csvFil = "\"Styrkkode her\"; \"PAM Nivå 1\"; \"PAM Nivå 2\" \n" +
            "  \"1234\"; \"Nivå 1\"; \"Nivå 2\"\n" +
            "2234; Nivå 1a; Nivå 2a\n" +
            "  \"2123\"; \"Nivå 1b\"; \"Nivå 2b\"; dette er bare noe greier\n" +
            "  \"1234a\"; \"Nivå 1\"; \"Nivå 2\"\n" +
            "  \"12345\"; \"Nivå 1\"; \"Nivå 2\"\n" +
            "  \"12345\"; \"Nivå 1\"; \"Nivå 2\"\n" +
            "  \"1235\"; \"Nivå 1 og Nivå 2\"\n";

    @Test
    public void skalMappeUtenAAFeile() throws Exception {
        Map<String, PamStyrkkode> mapping = StyrkMappingParser.parseStyrkmapping(new ByteArrayInputStream(csvFil.getBytes()));
        assertThat(mapping.size(), equalTo(3));

        assertThat(mapping.get("1234").pamNiva1(), equalTo("Nivå 1"));
        assertThat(mapping.get("2234").pamNiva2(), equalTo("Nivå 2a"));
        assertThat(mapping.get("2123").pamNiva1(), equalTo("Nivå 1b"));
    }
}
