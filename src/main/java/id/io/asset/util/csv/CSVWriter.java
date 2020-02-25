/**
  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
  *
  * Copyright (c) 2019 IO-Teknologi Indonesia, and individual contributors
  * as indicated by the @author tags. All Rights Reserved
  *
  * The contents of this file are subject to the terms of the
  * Common Development and Distribution License (the License).
  *
  * Everyone is permitted to copy and distribute verbatim copies
  * of this license document, but changing it is not allowed.
  *
  */
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
