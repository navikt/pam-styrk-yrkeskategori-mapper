import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class StyrkParserTest {

  @Test
  void styrkParserSkalReturnererRiktigeKategoriKoder() throws IOException {

    KategoriKode testKategoriKode1 = new KategoriKode(
        "110",
        "Offiserer fra fenrik og høyere grad",
        "Sikkerhet og beredskap",
        "Forsvar/militære");

    KategoriKode testKategoriKode2 = new KategoriKode(
        "5131",
        "Servitører",
        "Reiseliv, mat og overnatting",
        "Restaurant og forpleining");

    StyrkParser styrkParser = new StyrkParser();

    List<KategoriKode> kategoriKodes = styrkParser.parseMappingFile();

    assertEquals(testKategoriKode1.getStyrkKode(), kategoriKodes.get(1).getStyrkKode());
    assertEquals(testKategoriKode1.getStyrkKodeTekst(), kategoriKodes.get(1).getStyrkKodeTekst());
    assertEquals(testKategoriKode1.getKategori1(), kategoriKodes.get(1).getKategori1());
    assertEquals(testKategoriKode1.getKategori2(), kategoriKodes.get(1).getKategori2());

    assertEquals(testKategoriKode2.getStyrkKode(), kategoriKodes.get(8).getStyrkKode());
    assertEquals(testKategoriKode2.getStyrkKodeTekst(), kategoriKodes.get(8).getStyrkKodeTekst());
    assertEquals(testKategoriKode2.getKategori1(), kategoriKodes.get(8).getKategori1());
    assertEquals(testKategoriKode2.getKategori2(), kategoriKodes.get(8).getKategori2());

  }

}
