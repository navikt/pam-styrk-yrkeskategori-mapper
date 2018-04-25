import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Parser {

  public Parser() {
  }

  private FileReader fileReader;

  {
    try {
      fileReader = new FileReader("src/main/resources/styrk_kategori_mapping.csv");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private Reader bufferedReader = new BufferedReader(fileReader);
  private CSVParser parser;

  {
    try {
      parser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withDelimiter(';'));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public List<KategoriKode> parseFil() throws IOException {

    List<CSVRecord> records = parser.getRecords();

    parser.close();

    return records.stream()
        .map(this::mapToKategorKode).collect(
            Collectors.toList());
  }

  private KategoriKode mapToKategorKode(CSVRecord record) {
    return new KategoriKode(record.get(0), record.get(1), record.get(2), record.get(3));
  }

}
