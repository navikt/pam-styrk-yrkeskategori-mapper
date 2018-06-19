import domain.CategoryCode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapGenerator {

  private static StyrkParser styrkParser = new StyrkParser();

  public static Map<String, CategoryCode> generateHashMap() {

    String mappingFileLocation = "/styrk_category_mapping.csv";

    List<CategoryCode> categoryCodes = styrkParser.parseMappingFile(mappingFileLocation);

    Map<String, CategoryCode> styrkCodeMap = new HashMap<>();

    for (CategoryCode code : categoryCodes) {
      styrkCodeMap.put(code.getStyrkCode(), code);
    }

    return styrkCodeMap;
  }

}
