import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.CategoryCode;
import java.util.Map;
import org.junit.jupiter.api.Test;

class HashMapGeneratorTest {

  private CategoryCode testCategoryCode1 = new CategoryCode(
      "110",
      "Offiserer fra fenrik og høyere grad",
      "Sikkerhet og beredskap",
      "Forsvar/militære"
  );

  private CategoryCode testCategoryCode2 = new CategoryCode(
      "5131",
      "Servitører",
      "Reiseliv og mat",
      "Restaurant"
  );

  @Test
  void hashMapGeneratorShouldReturnCorrectHashMap() {

    Map<String, CategoryCode> map = HashMapGenerator
        .generateHashMap();

    assertTrue(testCategoryCode1.equals(map.get("110")));
    assertTrue(testCategoryCode2.equals(map.get("5131")));
  }

}
