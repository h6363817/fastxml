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
package org.fastxml.exception;

/**
 * Created by weager on 2016/06/07.
 */
public class ParseException extends Exception {

    private int row;
    private int column;

    public ParseException(String message, int row, int column) {
        super(message);
        this.row = row;
        this.column = column;
    }

    public static ParseException tagNotClosed(int row, int column) {
        String cause = String.format("line[%d], column[%d]: tag does not close correctly", row, column);
        return new ParseException(cause, row, column);
    }

    public static ParseException emptyDocument() {
        return new ParseException("document should not be empty", 1, 1);
    }

    public static ParseException otherError(int row, int column) {
        String cause = String.format("line[%d], column[%d]: Other error: invalid parser state", row, column);
        return new ParseException(cause, row, column);
    }

    public static ParseException documentEndUnexpected(int row, int column) {
        String cause = String.format("line[%d], column[%d]: Document end unexpected", row, column);
        return new ParseException(cause, row, column);
    }

    public static ParseException formatError(String msg, int row, int column) {
        String cause = String.format("line[%d], column[%d]: Document has error format: %s", row, column, msg);
        return new ParseException(cause, row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}