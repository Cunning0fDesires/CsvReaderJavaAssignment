package reader.com.util;

import lombok.Getter;

import java.util.Arrays;

@Getter
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

    public static String[] getAllHeaders() {
        return Arrays.stream(values())
                .map(CsvHeaders::getHeaderName)
                .toArray(String[]::new);
    }

}
