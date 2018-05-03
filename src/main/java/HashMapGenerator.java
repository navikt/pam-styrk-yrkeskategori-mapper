import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapGenerator {

  private static StyrkParser styrkParser = new StyrkParser();

  public static Map<String, KategoriKode> generateHashMap() {

    String mappingFileLocation = "/styrk_kategori_mapping.csv";

    List<KategoriKode> kategoriKoder = styrkParser.parseMappingFile(mappingFileLocation);

    Map<String, KategoriKode> styrkKodeMap = new HashMap<>();

    for (KategoriKode kode : kategoriKoder) {
      styrkKodeMap.put(kode.getStyrkKode(), kode);
    }

    return styrkKodeMap;
  }

}
