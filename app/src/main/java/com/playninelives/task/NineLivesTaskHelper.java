package com.playninelives.task;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownServiceException;

public class NineLivesTaskHelper {
    protected final String PROTOCOL = "http";
    protected final String HOSTNAME = "159.203.17.69";
    protected final String PORT = "8080";
    protected final String APP_PATH = "NineLivesWebService";
    protected final String FULL_PATH = PROTOCOL + "://" + HOSTNAME + ":" + PORT + "/" + APP_PATH + "/";

    private NineLivesTask task;
    public Gson gson;

    public NineLivesTaskHelper(NineLivesTask task) {
        this.task = task;
        gson = new Gson();
    }

    protected String getFullPath() {
        return FULL_PATH + task.getPath();
    }

    protected String[] encode(String... strings) {
        try {
            for (int i = 0; i < strings.length; i++) {
                strings[i] = URLEncoder.encode(strings[i], "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public URL createUrl(String... paths) {
        URL url = null;

        String path = joinString(encode(paths), "/");
        //String path = joinString(paths, "/");

        try {
            url = new URL(getFullPath() + path);
        } catch (MalformedURLException e) {
            System.out.println("Unable to create URL");
            e.printStackTrace();
        }

        return url;
    }

    protected String joinString(String[] strings, String delimiter) {
        StringBuilder sb = new StringBuilder();
        String appendage;

        for (int i = 0; i < strings.length; i++) {
            appendage = strings[i];
            if (i < strings.length - 1) {
                appendage += delimiter;
            }
            sb.append(appendage);
        }

        return sb.toString();
    }

    public String downloadUrl(URL url) {
        System.out.println("CHRIS: " + url);
        InputStream inputStream = null;
        HttpURLConnection connection = getHttpUrlConnection(url);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        String response = "";

        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setDoInput(true);

        try {
            connection.setRequestMethod("GET");
            connection.connect();
            inputStream = connection.getInputStream();
            response = getContent(inputStream);
        } catch (ProtocolException e) {
            System.out.println("Unable to set HttpURLConnection request method.");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Invalid encoding type provided.");
            e.printStackTrace();
        } catch (UnknownServiceException e) {
            System.out.println("Unknown service.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("HttpURLConnection unable to connect.");
            e.printStackTrace();
        } finally {
            close(inputStream);
            connection.disconnect();
        }

        return response;
    }

    protected String getContent(InputStream stream) throws UnsupportedEncodingException {
        return readLines(new BufferedReader(new InputStreamReader(stream, "UTF-8")));
    }

    protected String readLines(BufferedReader reader) {
        StringBuilder output = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
        } catch (IOException e) {
            System.out.println("Unable to read buffer.");
            e.printStackTrace();
        }

        return output.toString();

    }

    protected HttpURLConnection getHttpUrlConnection(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("Unable to create HttpURLConnection.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // do nothing
            }
        }
    }
}
