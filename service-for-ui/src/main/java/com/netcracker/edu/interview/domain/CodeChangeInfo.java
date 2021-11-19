package com.netcracker.edu.interview.domain;

public class CodeChangeInfo {
    private From from;
    private To to;
    private String text;
    private String origin; // need this field for working with CodeMirror lib

    public void setText(String text) {
        this.text = text;
    }

    public CodeChangeInfo(From from, To to, String text, String origin) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "CodeChangeInfo{" +
                "from=" + from +
                ", to=" + to +
                ", text='" + text + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }

    public String getOrigin() {
        return origin;
    }

    public From getFrom() {
        return from;
    }

    public To getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public static class From extends TextCoordinates {
        private int line;
        private int ch;

        public From(int line, int ch) {
            super(line, ch);
        }
    }

    public static class To extends TextCoordinates {
        private int line;
        private int ch;

        public To(int line, int ch) {
            super(line, ch);
        }

    }
}
