package no.nav.pam.yrkeskategorimapper;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import no.nav.pam.yrkeskategorimapper.domain.Occupation;

public class StyrkCodeConverter {

  private final Map<String, Occupation> occupationMap;

  public static StyrkCodeConverter newInstance() throws IOException {
    Map<String, Occupation> occupationMap = StyrkCodeConverter.generateHashMap();
    return new StyrkCodeConverter(occupationMap);
  }

  private StyrkCodeConverter(Map<String, Occupation> occupationMap) {
    this.occupationMap = occupationMap;
  }

  public Occupation lookup(String styrkCode){
    return occupationMap.get(styrkCode);
  }

  private static Map<String, Occupation> generateHashMap() throws IOException {

    StyrkParser styrkParser = StyrkParser.newInstance();

    List<Occupation> occupationList = styrkParser.getOccupationsFromFile();

    Map<String, Occupation> occupationMap = new HashMap<>();

    for (Occupation occupation : occupationList) {
      occupationMap.put(occupation.getStyrkCode(), occupation);
    }

    return occupationMap;
  }

}
