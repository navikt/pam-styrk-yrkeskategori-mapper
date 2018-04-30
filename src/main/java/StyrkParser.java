import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class StyrkParser {

  private final static Logger LOGGER = Logger.getLogger(StyrkParser.class.getName());

  public List<KategoriKode> parseMappingFile(String mappingFileLocation) throws Exception {

    try {
      InputStream inputStream = getClass().getResourceAsStream(mappingFileLocation);

      Reader bufferedReader = new InputStreamReader(inputStream);
      CSVParser parser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withDelimiter(';'));
      List<CSVRecord> records = parser.getRecords();

      return records.stream()
          .map(this::mapToKategorKodeAndTrim).collect(
              Collectors.toList());
    } catch (Exception error) {
      LOGGER.warning("Klarte ikke parse mapping-fil: " + error.getMessage());
      throw(error);
    }

  }

  private KategoriKode mapToKategorKodeAndTrim(CSVRecord record) {
    return new KategoriKode(record.get(0).trim(), record.get(1).trim(), record.get(2).trim(), record.get(3).trim());
  }

}
