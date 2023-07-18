package webserver;

import java.io.*;
import java.net.Socket;

import container.Servlet;
import container.DispatcherServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.GetParser;
import parser.PostParser;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(), connection.getPort());
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            DataOutputStream dos = new DataOutputStream(out);
            String line = "";
            String startLine = br.readLine();
            logger.debug("startLine = {}", startLine);
            HTTPServletRequest request = null;
            if (startLine.split(" ")[0].equals("POST")) {
                request = new PostParser().getProperRequest(startLine, br);
            }else{
                request = new GetParser().getProperRequest(startLine, br);
            }
            Servlet servlet = DispatcherServlet.findServlet(request);
            HTTPServletResponse response = new HTTPServletResponse(dos);
            servlet.service(request, response);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


}
