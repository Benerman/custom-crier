package com.customCrier;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

public class GoogleSheetAPI {
    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static Object API_KEY;
    private static String SPREADSHEET_ID;

    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public void setSpreadsheetId(String spreadsheetId) {
        SPREADSHEET_ID = spreadsheetId;
    }

    private static Sheets getAllSheets() {
        NetHttpTransport transport = new NetHttpTransport.Builder().build();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        HttpRequestInitializer httpRequestInitializer = request -> request.setInterceptor(intercepted -> intercepted.getUrl().set("key", API_KEY));
        return new Sheets.Builder(transport, jsonFactory, httpRequestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public void getSheet(String range) throws IOException {
        Sheets service = getAllSheets();
        ValueRange response = service.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Name, Major");
            for (List row : values) {
                System.out.printf("%s, %s\n", row.get(0), row.get(1));
            }
        }
    }

    public static ValueRange getSheetValues(Sheets service, String range) throws IOException {
        return service.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
    }

}
