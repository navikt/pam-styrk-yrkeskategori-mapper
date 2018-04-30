import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class StyrkParserTest {

  @Test
  void styrkParserSkalReturnererRiktigeKategoriKoder() {

    KategoriKode testKategoriKode1 = new KategoriKode(
        "110",
        "Offiserer fra fenrik og høyere grad",
        "Sikkerhet og beredskap",
        "Forsvar/militære"
    );

    KategoriKode testKategoriKode2 = new KategoriKode(
        "5131",
        "Servitører",
        "Reiseliv, mat og overnatting",
        "Restaurant og forpleining"
    );

    String mappingFileLocation = "/styrk_kategori_mapping.csv";

    StyrkParser styrkParser = new StyrkParser();

    List<KategoriKode> kategoriKodes = styrkParser.parseMappingFile(mappingFileLocation);

    assertEquals(testKategoriKode1.getStyrkKode(), kategoriKodes.get(1).getStyrkKode());
    assertEquals(testKategoriKode1.getStyrkKodeTekst(), kategoriKodes.get(1).getStyrkKodeTekst());
    assertEquals(testKategoriKode1.getKategori1(), kategoriKodes.get(1).getKategori1());
    assertEquals(testKategoriKode1.getKategori2(), kategoriKodes.get(1).getKategori2());

    assertEquals(testKategoriKode2.getStyrkKode(), kategoriKodes.get(8).getStyrkKode());
    assertEquals(testKategoriKode2.getStyrkKodeTekst(), kategoriKodes.get(8).getStyrkKodeTekst());
    assertEquals(testKategoriKode2.getKategori1(), kategoriKodes.get(8).getKategori1());
    assertEquals(testKategoriKode2.getKategori2(), kategoriKodes.get(8).getKategori2());

  }

  @Test
  void styrkPareserSkalKasteExceptionOgLoggeFeilmelding() {

    String INVALID_FILE_LOCATION = "FooBar";
    StyrkParser styrkParser = new StyrkParser();

    Exception exception = assertThrows(Exception.class, () -> {
      List<KategoriKode> kategoriKodes = styrkParser.parseMappingFile(INVALID_FILE_LOCATION);
    });

  }

}
