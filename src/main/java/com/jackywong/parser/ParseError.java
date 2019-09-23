package com.jackywong.parser;

/**
 * @Author: 黄子奇
 * @Date: 2019/9/21 15:55
 */
public class ParseError {
    private String currentLine;
    private String msg;
    private Integer line;
    private Integer col;

    public ParseError(String currentLine, String msg, Integer line, Integer col) {
        this.currentLine = currentLine;
        this.msg = msg;
        this.line = line;
        this.col = col;
    }

    public Tuple3<String,Integer,Integer> getLineAndCol(String input,Integer offset) {
        String currInput = input.substring(offset);
        String[] currInputs = currInput.split("\n");
        Integer lineCount = currInputs.length;
        String line = currInputs[lineCount - 1];
        Integer col = currInput.lastIndexOf("\n");
        if(col == -1 ) {
            col = offset + 1;
        } else {
            col = offset - col;
        }
        return new Tuple3<String, Integer, Integer>(line,lineCount,col);
    }

    public String toError() {
        String linemsg = String.format(" line:%d col:%d %s",line,col,msg);
        String line = String.format("\t%s", currentLine);
        String blank = new String(new char[col]).replace("\0", " ");
        String mark = String.format("\t%s^",blank);
        return linemsg + "\n" + line +"\n"+mark+"\n";
    }

    public String getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(String currentLine) {
        this.currentLine = currentLine;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }
}
