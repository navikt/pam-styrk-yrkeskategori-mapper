package no.nav.pam.styrk;

import no.nav.pam.styrk.domain.PamStyrkkode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class StyrkMappingParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(StyrkMappingParser.class);

    public static Map<String, PamStyrkkode> parseStyrkmapping(InputStream styrkStream) {
        Map<String, PamStyrkkode> mapping = new HashMap<String, PamStyrkkode>();
        int antallLinjerLest = 0;
        if (styrkStream != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(styrkStream))) {
                String csvLinje = null;
                while ((csvLinje = br.readLine()) != null) {
                    antallLinjerLest++;
                    PamStyrkkode pamStyrkkode = parseCsvLinje(antallLinjerLest, csvLinje);
                    if (pamStyrkkode != null) {
                        mapping.put(pamStyrkkode.styrkkode(), pamStyrkkode);
                    }
                }
            } catch (IOException e) {
                LOGGER.warn("Greide ikke å lese styrkmappingen: {}", e.getMessage(), e);
            }
        }

        LOGGER.info("Leste inn {} styrkkoder fra {} linjer med mapping", mapping.size(), antallLinjerLest);
        return mapping;
    }

    private static PamStyrkkode parseCsvLinje(int linjenr, String csvLinje) {
        String[] csvfelt = csvLinje.split(";", -1);
        PamStyrkkode mappetKode = null;

        if (csvfelt.length < 3) {
            LOGGER.info("Linje {} i csvfilen inneholder for få felt ({} felt)", linjenr, csvfelt.length);
        } else {
            String styrkkode = trimFelt(csvfelt[0]);
            if (styrkkode.length() != 4 || styrkkode.matches("[^\\d]")) {
                LOGGER.info("Linje {} starter med {} som ikke er en styrkkode. Linjen ignoreres.", linjenr, styrkkode);
            } else{
                mappetKode = new PamStyrkkode(trimFelt(styrkkode),
                        trimFelt(csvfelt[1]),
                        trimFelt(csvfelt[2]));
            }
        }

        return mappetKode;
    }

    private static String trimFelt(String felt) {
        String trimmetFelt = felt.trim();
        if (trimmetFelt.startsWith("\"")) {
            trimmetFelt = trimmetFelt.substring(1);
        }
        if (trimmetFelt.endsWith("\"")) {
            trimmetFelt = trimmetFelt.substring(0, trimmetFelt.length()-1);
        }

        return trimmetFelt;
    }
}
