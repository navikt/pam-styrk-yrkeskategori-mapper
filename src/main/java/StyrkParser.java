import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class StyrkParser {

  public StyrkParser() {
  }

  private Reader bufferedReader;

  {
    try (InputStream is = getClass().getResourceAsStream("/styrk_kategori_mapping.csv")){
      bufferedReader = new BufferedReader(new InputStreamReader(is));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private CSVParser parser;

  {
    try {
      parser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withDelimiter(';'));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<KategoriKode> parseMappingFile() throws IOException {

    List<CSVRecord> records = parser.getRecords();

    parser.close();

    return records.stream()
        .map(this::mapToKategorKodeAndTrim).collect(
            Collectors.toList());
  }

  private KategoriKode mapToKategorKodeAndTrim(CSVRecord record) {
    return new KategoriKode(record.get(0).trim(), record.get(1).trim(), record.get(2).trim(), record.get(3).trim());
  }

}
