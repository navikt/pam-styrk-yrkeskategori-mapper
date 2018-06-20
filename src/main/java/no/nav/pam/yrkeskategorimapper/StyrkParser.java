package no.nav.pam.yrkeskategorimapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import no.nav.pam.yrkeskategorimapper.domain.Occupation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class StyrkParser {

  private final static Logger LOGGER = Logger.getLogger(StyrkParser.class.getName());
  private String mappingFileLocation = "/styrk_category_mapping.csv";

  private List<Occupation> list;

  protected static StyrkParser newInstance() {
    return new StyrkParser();
  }

  private StyrkParser() {

  }

  public List<Occupation> getOccupationsFromFile() throws IOException {
    if (list == null) {
      list = parseMappingFile();
    }
    return list;
  }

  private List<Occupation> parseMappingFile() throws IOException {

    List<CSVRecord> parseResult = parse(mappingFileLocation);

    return Objects.requireNonNull(parseResult).stream().map(this::mapToCategoryCodeAndTrim).collect(
        Collectors.toList());
  }

  private Occupation mapToCategoryCodeAndTrim(CSVRecord record) {
    return new Occupation(record.get(0).trim(), record.get(1).trim(), record.get(2).trim(),
        record.get(3).trim());
  }

  private List<CSVRecord> parse(String mappingFileLocation) throws IOException {
    try (InputStream inputStream = getClass().getResourceAsStream(mappingFileLocation);
        Reader bufferedReader = new InputStreamReader(inputStream);
        CSVParser parse = CSVParser.parse(bufferedReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';'))) {

      return parse.getRecords();

    } catch (Exception error) {
      LOGGER.warning("Feil under parsing av mapping-fil. Feilmelding: " + error.getMessage());
      throw error;
    }
  }

}
