package no.nav.pam.yrkeskategorimapper;

import no.nav.pam.yrkeskategorimapper.domain.Occupation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapGenerator {

  private static StyrkParser styrkParser = new StyrkParser();

  public static Map<String, Occupation> generateHashMap() {

    String mappingFileLocation = "/styrk_category_mapping.csv";

    List<Occupation> occupations = styrkParser.parseMappingFile(mappingFileLocation);

    Map<String, Occupation> styrkCodeMap = new HashMap<>();

    for (Occupation code : occupations) {
      styrkCodeMap.put(code.getStyrkCode(), code);
    }

    return styrkCodeMap;
  }

}
