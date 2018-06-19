import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;

class HashMapGeneratorTest {

  private KategoriKode testKategoriKode1 = new KategoriKode(
      "110",
      "Offiserer fra fenrik og høyere grad",
      "Sikkerhet og beredskap",
      "Forsvar/militære"
  );

  private KategoriKode testKategoriKode2 = new KategoriKode(
      "5131",
      "Servitører",
      "Reiseliv og mat",
      "Restaurant"
  );

  @Test
  void hashMapGeneratorShouldReturnCorrectHashMap() {

    Map<String, KategoriKode> map = HashMapGenerator
        .generateHashMap();

    assertTrue(testKategoriKode1.equals(map.get("110")));
    assertTrue(testKategoriKode2.equals(map.get("5131")));
  }

}
