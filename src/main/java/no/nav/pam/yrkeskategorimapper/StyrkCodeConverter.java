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
     * @return
     * @throws IOException
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
     * <p>If more than 4 digits are provided, the excess digits are discarded before looking up the code.</p>
     *
     * TODO complete me
     *
     * @param styrkCode
     * @return
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
                    Collectors.toMap(o -> o.getStyrkCode(), Function.identity(), (o1, o2) -> o1));
        }
    }

}
