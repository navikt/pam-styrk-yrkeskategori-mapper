package no.nav.pam.yrkeskategorimapper;


import no.nav.pam.yrkeskategorimapper.domain.Occupation;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StyrkCodeConverter {

    public static final String STYRK_MAPPING_RESOURCE = "/styrk_category_mapping.csv";

    private final Map<String, Occupation> occupationMap = generateHashMap();
    private final Map<String, Occupation> pyrkMap = generatePyrkMap(occupationMap);


    /**
     * Lookup STYRK code, 1 to 6 digits.
     *
     * <p>Input Styrk Code. Styrk Code will be converted to Occupation domain object and returned as Optional</p>
     * Occupation domain object represents an occupation with occupation category levels 1 and 2 (Yrkeskategori)
     * <p>Only 4 digit Styrk codes are accepted. If more than 4 digits are provided, the excess digits are discarded before conversion.</p>
     *<p>If less than 4 digit, leading zeroes will be added </p>
     * @param styrkCode The Styrk Code to be converted to Occupation Category level 1 and 2 (Yrkeskategori)
     * @return Optional of requested Occupation domain object
     * @throws NullPointerException if provided string is {@code null}
     */
    public Optional<Occupation> lookup(String styrkCode) {
        int length = styrkCode.length();
        if (length < 4) {
            StringBuffer leadingZeros = new StringBuffer();
            for (int i = 4; i>length; i--) {
                leadingZeros.append(0);
            }
            styrkCode = leadingZeros.toString()+styrkCode;
        }
        else if (styrkCode.length() > 4) {
            styrkCode = styrkCode.substring(0, 4);
        }
        return Optional.ofNullable(occupationMap.get(styrkCode));
    }

    public Optional<Occupation> lookupPyrk(String kode) {
        return Optional.ofNullable(pyrkMap.get(kode));
    }

    private Map<String, Occupation> generateHashMap() {
        try (InputStream is = StyrkCodeConverter.class.getResourceAsStream(STYRK_MAPPING_RESOURCE)) {
            // If duplicate STYRK code keys, just select one of them
            return StyrkParser.parse(is).stream().collect(
                    Collectors.toMap(Occupation::getStyrkCode, Function.identity(), (o1, o2) -> o1));
        }
        catch (IOException e) {
            throw new RuntimeException("StyrKodeConvertert failed to initialize",e);
        }
    }

    private Map<String, Occupation> generatePyrkMap(Map<String,Occupation> occupationMap){
        Map<String,Occupation> pyrks = new HashMap<>();
        Set<String> pyrkcats = new HashSet<>();
        List<Occupation> occupations = new ArrayList<Occupation>(occupationMap.values());
        Collections.sort(occupations);
        occupations.forEach(o -> {
            String pyrk = o.getCategoryLevel1()+" - " + o.getCategoryLevel2();
            if (!pyrkcats.contains(pyrk))  {
                pyrkcats.add(pyrk);
                pyrks.put(o.getStyrkCode(), o);
            }
        });
        return pyrks;
    }

    public String showPyrkOccupations() {
        StringBuffer buffer = new StringBuffer();
        List<Occupation>occupations = new ArrayList<Occupation>(pyrkMap.values());
        Collections.sort(occupations);
        occupations.forEach(o -> {
            buffer.append(o.getStyrkCode()+" "+o.getCategoryLevel1()+" - " +o.getCategoryLevel2()+"\n");
        });
        return buffer.toString();
    }

    public Map<String, Occupation> getOccupationMap() {
        return occupationMap;
    }

    public Map<String, Occupation> getPyrkMap() {
        return pyrkMap;
    }

}
