import static org.junit.jupiter.api.Assertions.assertEquals;

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
      "Reiseliv, mat og overnatting",
      "Restaurant og forpleining"
  );

  @Test
  void hashMapGeneratorShouldReturnCorrectHashMap() {

    Map<String, KategoriKode> map = HashMapGenerator
        .generateHashMap();

    assertEquals(testKategoriKode1, map.get("110"));
    assertEquals(testKategoriKode2, map.get("5131"));
  }

}
