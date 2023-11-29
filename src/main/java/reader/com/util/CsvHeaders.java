package reader.com.util;

import java.util.Arrays;

public enum CsvHeaders {
    CUSTOMERREF("Customer Ref"),
    CUSTOMERNAME("Customer Name"),
    ADDRESSLINE1("Address Line 1"),
    ADDRESSLINE2("Address Line 2"),
    TOWN("Town"),
    COUNTY("County"),
    COUNTRY("Country"),
    POSTCODE("Postcode");

    private final String headerName;

    CsvHeaders(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderValue() {
        return this.headerName;
    }

    public static String[] getAllHeaders() {
        return Arrays.stream(values())
                .map(CsvHeaders::getHeaderValue)
                .toArray(String[]::new);
    }

}
