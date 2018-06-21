package no.nav.pam.yrkeskategorimapper;


import no.nav.pam.yrkeskategorimapper.domain.Occupation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StyrkCodeConverter {

    public static final String STYRK_MAPPING_RESOURCE = "/styrk_category_mapping.csv";

    private final Map<String, Occupation> occupationMap;

    /**
     * Construct a new instance of mapper.
     * @return Returns mapper object used for conversion between Styrk Code and Occupation Categories 1 and 2 (Yrkeskategoritre 1 og 2)
     * @throws IOException Parser throws IOException on error.
     */
    public static StyrkCodeConverter newInstance() throws IOException {
        Map<String, Occupation> occupationMap = StyrkCodeConverter.generateHashMap();
        return new StyrkCodeConverter(occupationMap);
    }

    private StyrkCodeConverter(Map<String, Occupation> occupationMap) {
        this.occupationMap = occupationMap;
    }

    /**
     * Lookup STYRK code, 1 to 6 digits.
     *
     * <p>Input Styrk Code. Styrk Code will be converted to Occupation domain object and returned as Optional</p>
     * Occupation domain object represents an occupation with occupation category levels 1 and 2 (Yrkeskategori)
     * <p>Only 4 digit Styrk codes are accepted. If more than 4 digits are provided, the excess digits are discarded before conversion.</p>
     *
     * @param styrkCode The Styrk Code to be converted to Occupation Category level 1 and 2 (Yrkeskategori)
     * @return Optional of requested Occupation domain object
     * @throws NullPointerException if provided string is {@code null}
     */
    public Optional<Occupation> lookup(String styrkCode) {

        if (styrkCode.length() > 4) {
            styrkCode = styrkCode.substring(0, 4);
        }

        return Optional.ofNullable(occupationMap.get(styrkCode));
    }

    private static Map<String, Occupation> generateHashMap() throws IOException {
        try (InputStream is = StyrkCodeConverter.class.getResourceAsStream(STYRK_MAPPING_RESOURCE)) {
            // If duplicate STYRK code keys, just select one of them
            return StyrkParser.parse(is).stream().collect(
                    Collectors.toMap(Occupation::getStyrkCode, Function.identity(), (o1, o2) -> o1));
        }
    }

}
