package sis.search;

import sis.util.StringUtil;

import java.io.*;
import java.net.*;

public class Search {
    private URL url;
    private String searchString;
    private int matches = 0;
    private Exception exception = null;

    public Search(String urlString, String searchString) throws IOException {
        this.url = new URL(urlString);
        this.searchString = searchString;
    }

    public String getText() {
        return searchString;
    }
    public String getUrl() {
        return url.toString();
    }
    public int matches() {
        return matches;
    }
    public Exception getError() {
        return exception;
    }

    public boolean errored() {
        return exception != null;
    }

    public void execute() {
        try {
            searchUrl();
        }
        catch (IOException e) {
            exception = e;
        }
    }

    private void searchUrl() throws IOException {
        URLConnection connection = url.openConnection();
        InputStream input = connection.getInputStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null)
                matches += StringUtil.occurrences(line, searchString);
        }
    }

    private InputStream getInputStream(URL url) throws IOException {
        if (url.getProtocol().startsWith("http")) {
            URLConnection connection = url.openConnection();
            return connection.getInputStream();
        }
        else if (url.getProtocol().equals("file")) {
            return new FileInputStream(url.getPath());
        }
        return null;
    }
}
