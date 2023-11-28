package com.example.pfi.Helper;

import android.util.Xml;

import com.example.pfi.Logger;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.Function;

public class XMLHelper {
    public static <T> ArrayList<T> initializeObjects(
            String fileName,
            String mainTag,
            String secondaryTag,
            Function<XmlPullParser, T> onTagFound
    ) {
        ArrayList<T> objs = new ArrayList<>();

        try {
            objs = parseObject(
                    ResourcesManager.getContext().getAssets().open(fileName),
                    mainTag,
                    secondaryTag,
                    onTagFound
            );
        } catch (Exception e) {
           Logger.error(e);
        }
        return objs;
    }

    private static <T> ArrayList<T> parseObject(
            InputStream inputStream,
            String mainTag,
            String secondaryTag,
            Function<XmlPullParser, T> onTagFound
    ) throws IOException {
        ArrayList<T> objs = new ArrayList<>();

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            parser.setInput(inputStream, "UTF-8");
            parser.nextTag();

            objs = readObject(parser, mainTag, secondaryTag, onTagFound);
        }
        catch (Exception e){
            Logger.error(e);
        }
        finally {
            inputStream.close();
        }
        return objs;
    }

    private static <T> ArrayList<T> readObject(
            XmlPullParser parser,
            String mainTag,
            String secondaryTag,
            Function<XmlPullParser, T> onTagFound
    ) throws XmlPullParserException, IOException {
        ArrayList<T> objs = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, null, mainTag);

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;

            if (parser.getName().equals(secondaryTag))
                objs.add(onTagFound.apply(parser));
            else
                skip(parser);
        }
        return objs;
    }

    private static void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }

        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next == XmlPullParser.START_TAG) {
                i++;
            } else if (next == XmlPullParser.END_TAG) {
                i--;
            }
        }
    }

    /**
     * Reads the current tag and returns it's text value.
     */
    public static String readField(XmlPullParser parser, String tag) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, tag);
        String readText = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, tag);
        return readText;
    }

    /**
     * @return Text value of the current tag.
     */
    private static String readText(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (xmlPullParser.next() != XmlPullParser.TEXT)
            return "";

        String text = xmlPullParser.getText();
        xmlPullParser.nextTag();
        return text;
    }
}
