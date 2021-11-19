package com.netcracker.edu.interview.domain;


// parent for From and To classes
public class TextCoordinates {
    private int line;
    private int ch;

    public TextCoordinates(int line, int ch) {
        this.line = line;
        this.ch = ch;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setCh(int ch) {
        this.ch = ch;
    }

    public int getLine() {
        return line;
    }

    public int getCh() {
        return ch;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        else if (!(obj instanceof TextCoordinates))
            return false;
        else {
            return this.getLine() == ((TextCoordinates) obj).getLine() && this.getCh() == ((TextCoordinates) obj).getCh();
        }
    }
}
