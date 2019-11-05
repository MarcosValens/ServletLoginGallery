import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@WebServlet(name = "upload", urlPatterns = "/upload")
@MultipartConfig(location = "/tmp",
        fileSizeThreshold = 1048576, // 1mb
        maxFileSize = 1048576, // 1mb
        maxRequestSize = 5242880) // 5mb

public class updateController extends HttpServlet {

    private static final String UPLOAD_DIR = "tmp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + UPLOAD_DIR;
        final File folder = new File(uploadFilePath);
        String tomcatDirectory = System.getProperty("catalina.base");
        Part arxiu = req.getPart("file");
        System.out.println("part.getContentType : " + arxiu.getContentType());
        System.out.println("part.getSize : " + arxiu.getSize());
        System.out.println("part.getName : " + arxiu.getName());
        System.out.println("part.getSubmittedFileName : " + arxiu.getSubmittedFileName());
        String nomArxiu = arxiu.getSubmittedFileName();
        arxiu.write(uploadFilePath + File.separator + nomArxiu);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Gallery</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Galeria</h1>\n");
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            /*System.out.println(fileEntry.getAbsolutePath());*/
            System.out.println(this.getServletContext().getRealPath(File.separator)+UPLOAD_DIR+File.separator+fileEntry.getName());
            System.out.println(fileEntry.getAbsolutePath());
            out.println("<img src=\""+fileEntry.getAbsolutePath()+"\"><br/>");
            out.println("<img src=\""+this.getServletContext().getRealPath(File.separator)+UPLOAD_DIR+File.separator+fileEntry.getName()+"\"><br/>");
        }

        out.println(
                "<a href=\"http://localhost:8080/LoginMal_war_exploded/upload\">Subir otro archivo</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
