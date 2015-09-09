package com.analytique.entity;

import com.analytique.file.DelimitedFileType;
import org.apache.commons.csv.CSVFormat;



import org.apache.commons.csv.CSVFormat;


public enum AnalytiqueFileType implements DelimitedFileType {

    CUSTOMER(Customer.class);

    private static final String RECORD_SEPARATOR = "\r\n";
    private final CSVFormat csvFormat;

    public static final String DATE_FORMAT_YYYYMMDD_WITHOUT_HYPHENS = "yyyyMMdd";

    AnalytiqueFileType(Class<?> recordType) {
        csvFormat = CSVFormat.newFormat(',').withHeader().withSkipHeaderRecord(true).withRecordSeparator(RECORD_SEPARATOR).withIgnoreEmptyLines(true);
    }

    @Override
    public CSVFormat getCSVFormat() {
        return csvFormat;
    }
}