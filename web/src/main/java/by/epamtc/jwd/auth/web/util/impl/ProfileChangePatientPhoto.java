package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.web.exception.ControllerException;
import by.epamtc.jwd.auth.web.util.Command;
import by.epamtc.jwd.auth.web.util.FileAccessAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProfileChangePatientPhoto implements Command {
    private FileAccessAssistant fileAssistant = FileAccessAssistant
            .getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        final String path = "D:\\25\\wokspace\\EPAM\\jwd_auth\\web\\src\\main\\webapp\\webcontent\\user_photo";
        final Part filePart = req.getPart("photoUploadInput");
        final String targetFileName = getFileName(filePart, user);

        OutputStream oFileStream = null;
        InputStream iFileStream = null;
        File targetFile = new File(path + File.separator + targetFileName);

        try {
            oFileStream = new FileOutputStream(targetFile);
            iFileStream = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = iFileStream.read(bytes)) != -1) {
                oFileStream.write(bytes, 0, read);
            }
//            writer.println("New file " + fileName + " created at " + path);
//            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
//                    new Object[]{fileName, path});
        } catch (FileNotFoundException fne) {
//            writer.println("You either did not specify a file to upload or are "
//                    + "trying to upload a file to a protected or nonexistent "
//                    + "location.");
//            writer.println("<br/> ERROR: " + fne.getMessage());

//            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
//                    new Object[]{fne.getMessage()});
        } finally {
            if (oFileStream != null) {
                oFileStream.close();
            }
            if (iFileStream != null) {
                iFileStream.close();
            }
        }

        req.getRequestDispatcher(CommandPath.SUBPROFILE_CHANGE_PATIENT_INFO_JSP)
                .forward(req, res);
    }

    private String getFileName(final Part part, AuthUser user) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String srcFileName = content.substring(content.indexOf('=') + 1)
                        .trim().replace("\"", "");
                String[] srcFileNamePart = srcFileName.split("\\.");
                String srcFileNaming = srcFileNamePart[0];
                String srcFileExtension = srcFileNamePart[1];
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("yyyyMMddHHmmss");
                return srcFileNaming + "_" + now.format(formatter) + "."
                        + srcFileExtension;
            }
        }
        return null;
    }

}