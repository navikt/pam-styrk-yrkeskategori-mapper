import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class StyrkParser {

  private final static Logger LOGGER = Logger.getLogger(StyrkParser.class.getName());

  public List<KategoriKode> parseMappingFile(String mappingFileLocation) {

    List<CSVRecord> parseResult = parse(mappingFileLocation);

    return Objects.requireNonNull(parseResult).stream().map(this::mapToKategorKodeAndTrim).collect(
        Collectors.toList());
  }

  private KategoriKode mapToKategorKodeAndTrim(CSVRecord record) {
    return new KategoriKode(record.get(0).trim(), record.get(1).trim(), record.get(2).trim(),
        record.get(3).trim());
  }

  private List<CSVRecord> parse(String mappingFileLocation) {
    try (InputStream inputStream = getClass().getResourceAsStream(mappingFileLocation);
        Reader bufferedReader = new InputStreamReader(inputStream);
        CSVParser parse = CSVParser.parse(bufferedReader, CSVFormat.DEFAULT.withDelimiter(';'))) {

      return parse.getRecords();

    } catch (Exception error) {
      LOGGER.warning("Feil under parsing av mapping-fil. Feilmelding: " + error.getMessage());
    }
    return null;
  }

}
