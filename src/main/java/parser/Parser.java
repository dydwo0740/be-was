package parser;

import webserver.HTTPServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public interface Parser {
    public HTTPServletRequest getProperRequest(String startLine, BufferedReader br) throws IOException;


}
