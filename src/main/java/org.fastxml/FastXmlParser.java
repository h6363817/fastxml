/**
 Copyright 2016 FastXml author(https://github.com/fastxml/fastxml)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package org.fastxml;

import java.nio.charset.Charset;

import org.fastxml.exception.NumberFormatException;
import org.fastxml.exception.ParseException;

/**
 * The interface of FastXml。
 * FastXml doest't support validation.
 * Created by weager on 2016/06/07.
 */
public interface FastXmlParser {

    // The following section is event type in fast xml
    int END_DOCUMENT = -1;
    int START_DOCUMENT = 0;
    int START_TAG = 1;
    int END_TAG = 2; // such as "</xxx>"
    int END_TAG_WITHOUT_TEXT = 3; // such as "/>"
    int ATTRIBUTE_NAME = 4;
    int ATTRIBUTE_VALUE = 5;
    int TEXT = 6;

    /**
     * get current event that has already checked
     * @return
     * @throws ParseException
     */
    int current();

    /**
     * read bytes, move the cursor, and check it's event type
     *
     * @return event type: START_DOCUMENT,END_DOCUMENT,START_TAG,END_TAG,ATTRIBUTE,TEXT
     */
    int next() throws ParseException;

    /**
     * skip the current tag and its descendants by moving cursor.
     * if you find the current tag which you don't want to parse, you can skip this tag to get better performance,
     * FastXml will not waste time and space on this tag and its descendants, just move the cursor forward until find another tag.
     * This method usually be called after next() and isMatch(byte[])
     */
    void skipCurrentTag() throws ParseException;

    /**
     * Encode declared at the beginning of the doc。
     * This method should be called after START_DOCUMENT event.
     * If no encode is declared, return utf-8 as default
     *
     * @return the document encode charset
     */
    Charset getEncode();

    /**
     * get current depth
     *
     * @return current depth
     */
    int getDepth();

    /**
     * get current row No
     *
     * @return current row No
     */
    int getRow();

    /**
     * get current column No
     *
     * @return current column No
     */
    int getColumn();

    /**
     * check the current bytes is the same with expectBytes
     *
     * @param expectBytes
     * @return true if expectBytes is the same with current bytes
     */
    boolean isMatch(byte[] expectBytes);

    /**
     * get current raw bytes copy
     *
     * @return current raw bytes copy
     */
    byte[] getRawBytes();

    /**
     * get Short which convert from current bytes
     *
     * @return Short object or zero if no bytes
     * @throws NumberFormatException
     */
    short getShort() throws NumberFormatException;

    /**
     * get Integer which convert from current bytes
     *
     * @return Integer object or zero if no bytes
     * @throws NumberFormatException
     */
    int getInt() throws NumberFormatException;

    /**
     * get Float which convert from current bytes
     *
     * @return Float object or zero if no bytes
     * @throws NumberFormatException
     */
    float getFloat() throws NumberFormatException;

    /**
     * get Double which convert from current bytes
     *
     * @return Double object or zero if no bytes
     * @throws NumberFormatException
     */
    double getDouble() throws NumberFormatException;

    /**
     * get Long which convert from current bytes
     *
     * @return Long object or zero if no bytes
     * @throws NumberFormatException
     */
    long getLong() throws NumberFormatException;

    /**
     * get the current string from bytes without decoding bytes
     *
     * @return readable string or zero if no bytes
     */
    String getString();

    /**
     * get the current string with decoding bytes if you need
     *
     * @param needDecode if some characters in current string are not ascii
     * @return readable string or zero if no bytes
     */
    String getString(boolean needDecode);
}