package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.AppParameter;
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
        final Part filePart = req.getPart(AppParameter.PHOTO_UPLOAD);
        final String targetFileName = getFileName(filePart);

        OutputStream oFileStream = null;
        InputStream iFileStream = null;
        File targetFile;

        try {
            targetFile = new File(fileAssistant.formTextFilePath(targetFileName));
            oFileStream = new FileOutputStream(targetFile);
            iFileStream = filePart.getInputStream();

            int read;
            final byte[] bytes = new byte[1024];

            while ((read = iFileStream.read(bytes)) != -1) {
                oFileStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException fne) {
            // TODO log4j "You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location."
            // TODO add redirect
        } catch (ControllerException e) {
            // TODO log4j "You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location."
            // TODO add redirect
        } finally {
            closeInOutUploadStreams(oFileStream, iFileStream);
        }

        req.getRequestDispatcher(CommandPath.SUBPROFILE_CHANGE_PATIENT_INFO_JSP)
                .forward(req, res);
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader(AppParameter
                .HEADER_CONTENT_DISPOSITION);
        for (String content : partHeader.split(AppConstant.SEMICOLON)) {
            if (content.trim().startsWith(AppParameter
                    .HEADER_CONTENT_DISPOSITION_FILENAME)) {
                int startValueIndex = content.indexOf(AppConstant
                        .KEY_VALUE_PAIR_DELIMITER) + 1;
                String srcFileName = content.substring(startValueIndex).trim()
                        .replace(AppConstant.QUOTE_MARK, AppConstant.EMPTY);

                String[] srcFileNamePart = srcFileName.split(AppConstant.REGEX_DOT);
                String srcFileNaming = srcFileNamePart[0];
                String srcFileExtension = srcFileNamePart[1];
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern(AppConstant.SIMPLE_LOCAL_DATE_TIME_FORMAT);
                return srcFileNaming + AppConstant.UNDERSCORE
                        + now.format(formatter) + AppConstant.DOT
                        + srcFileExtension;
            }
        }
        return null;
    }

    private void closeInOutUploadStreams(OutputStream oFileStream,
            InputStream iFileStream) throws IOException {
        if (oFileStream != null) {
            oFileStream.close();
        }
        if (iFileStream != null) {
            iFileStream.close();
        }
    }

}