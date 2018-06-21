package no.nav.pam.yrkeskategorimapper;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

  public Optional<Occupation> lookup(String styrkCode){

    if(styrkCode.length() > 4) {
      String fourDigitStyrkCode = styrkCode.substring(0, 4);
      return Optional.ofNullable(occupationMap.get(fourDigitStyrkCode));
    }
    return Optional.ofNullable(occupationMap.get(styrkCode));
  }

  private static Map<String, Occupation> generateHashMap() throws IOException {

    StyrkParser styrkParser = StyrkParser.newInstance();

    List<Occupation> occupationList = styrkParser.getOccupationsFromFile();

    Map<String, Occupation> occupationMap = new HashMap<>();

    for (Occupation occupation : occupationList) {
      if(!occupationMap.containsKey(occupation.getStyrkCode())) {
        occupationMap.put(occupation.getStyrkCode(), occupation);
      }
    }

    return occupationMap;
  }

}
