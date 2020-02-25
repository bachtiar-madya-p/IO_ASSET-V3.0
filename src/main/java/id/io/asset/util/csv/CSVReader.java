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

import java.io.ByteArrayInputStream; 
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Singleton
public class CSVReader {

    private static CSVReader instance; 

    private CsvParser parser;
    private CsvParserSettings settings;

    private CSVReader() {
        settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        parser = new CsvParser(settings);
    }

    public List<CSVRecord> read(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(Charset.forName("UTF-8")));
        List<String[]> list = parser.parseAll(inputStream);
        List<CSVRecord> recordList = new ArrayList<CSVRecord>();
        if (list.size() > 0) {
            // Get Headers
            String[] header = list.remove(0);
            list.forEach(arry -> {
                CSVRecord record = new CSVRecord();
                for (int i = 0; i < header.length; i++) {
                    record.put(header[i], arry[i]);
                }
                recordList.add(record);
            });
        }
        return recordList;
    }

    public static CSVReader getInstance() {
        if (instance == null) {
            instance = new CSVReader();
        }
        return instance;
    }
}
