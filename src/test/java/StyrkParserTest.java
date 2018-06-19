import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.CategoryCode;
import java.util.List;
import org.junit.jupiter.api.Test;

class StyrkParserTest {

  @Test
  void styrkParserShouldReturnCorrectCategoryCodes() {

    CategoryCode testCategoryCode1 = new CategoryCode(
        "110",
        "Offiserer fra fenrik og høyere grad",
        "Sikkerhet og beredskap",
        "Forsvar/militære"
    );

    CategoryCode testCategoryCode2 = new CategoryCode(
        "5131",
        "Servitører",
        "Reiseliv og mat",
        "Restaurant"
    );

    String mappingFileLocation = "/styrk_category_mapping.csv";

    StyrkParser styrkParser = new StyrkParser();

    List<CategoryCode> categoryCodes = styrkParser.parseMappingFile(mappingFileLocation);

    assertEquals(testCategoryCode1.getStyrkCode(), categoryCodes.get(1).getStyrkCode());
    assertEquals(testCategoryCode1.getStyrkDescription(), categoryCodes.get(1).getStyrkDescription());
    assertEquals(testCategoryCode1.getCategoryLevel1(), categoryCodes.get(1).getCategoryLevel1());
    assertEquals(testCategoryCode1.getCategoryLevel2(), categoryCodes.get(1).getCategoryLevel2());

    assertEquals(testCategoryCode2.getStyrkCode(), categoryCodes.get(339).getStyrkCode());
    assertEquals(testCategoryCode2.getStyrkDescription(), categoryCodes.get(339).getStyrkDescription());
    assertEquals(testCategoryCode2.getCategoryLevel1(), categoryCodes.get(339).getCategoryLevel1());
    assertEquals(testCategoryCode2.getCategoryLevel2(), categoryCodes.get(339).getCategoryLevel2());

  }

  @Test
  void styrkParserShouldThrowExceptionAndLogErrorMessageIfInputFileDoesntExist() {

    String INVALID_FILE_LOCATION = "FooBar";
    StyrkParser styrkParser = new StyrkParser();

    Exception exception = assertThrows(Exception.class, () -> {
      List<CategoryCode> categoryCodes = styrkParser.parseMappingFile(INVALID_FILE_LOCATION);
    });

  }

}
