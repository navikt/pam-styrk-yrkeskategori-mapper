package no.nav.pam.yrkeskategorimapper;

import no.nav.pam.yrkeskategorimapper.domain.Occupation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StyrkParser {

    public static List<Occupation> parse(InputStream csvData) throws IOException {
        List<CSVRecord> parseResult = parseCsv(csvData);

        return Objects.requireNonNull(parseResult).stream().map(
                StyrkParser::mapToCategoryCodeAndTrim).collect(Collectors.toList());
    }

    private static Occupation mapToCategoryCodeAndTrim(CSVRecord record) {
        return new Occupation(record.get(0).trim(), record.get(1).trim(), record.get(2).trim(),
                record.get(3).trim());
    }

    private static List<CSVRecord> parseCsv(InputStream data) throws IOException {

        Reader bufferedReader = new InputStreamReader(data, StandardCharsets.UTF_8);
        CSVParser parse = CSVParser.parse(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';'));

        return parse.getRecords();
    }
}