package id.io.asset.util.csv;

import java.io.Writer;
import java.util.List;
import javax.inject.Singleton;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

@Singleton
public class CSVWriter {

    private static CSVWriter instance;

    private CsvWriterSettings settings;

    private CSVWriter() {
        settings = new CsvWriterSettings();
    }

    public void write(Writer output, List<String> headerList, List<CSVRecord> recordList) {
        CsvWriter writer = new CsvWriter(output, settings);

        // Write Headers
        writer.writeHeaders(headerList);

        // Write Rows
        recordList.forEach(record -> {
            writer.writeRow(record.getAttributeMap());
        });

        writer.close();
    }

    public static CSVWriter getInstance() {
        if (instance == null) {
            instance = new CSVWriter();
        }
        return instance;
    }
}
