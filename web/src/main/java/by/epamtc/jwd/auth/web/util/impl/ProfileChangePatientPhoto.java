package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.UploadService;
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

public class ProfileChangePatientPhoto implements Command {
    private FileAccessAssistant fileAssistant = FileAccessAssistant
            .getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UploadService uploadService = serviceFactory.getUploadService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        Part filePart = req.getPart(AppParameter.PHOTO_UPLOAD);
        OutputStream oFileStream = null;
        InputStream iFileStream = null;

        String targetFileName = null;


        try {
            targetFileName = uploadPatientPhotoAndReturnItsName(filePart,
                    iFileStream, oFileStream);
        } catch (FileNotFoundException fne) {
            // TODO log4j "You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location."
            // TODO add redirect
        } catch (ControllerException e) {
            // TODO log4j "You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location."
            // TODO add redirect
        } finally {
            closeInOutUploadStreams(iFileStream, oFileStream);
        }

        // TODO REDIRECT???
        if (!uploadService.updatePatientPhoto(targetFileName, user)) {

        }

        req.getRequestDispatcher(CommandPath.SUBPROFILE_CHANGE_PATIENT_INFO_JSP)
                .forward(req, res);
    }

    private String uploadPatientPhotoAndReturnItsName(Part filePart,
            InputStream iFileStream, OutputStream oFileStream)
            throws ControllerException, IOException {
        String targetFileName = fileAssistant.formFileName(filePart);
        File targetFile = new File(fileAssistant.formTextFilePath(targetFileName));
        oFileStream = new FileOutputStream(targetFile);
        iFileStream = filePart.getInputStream();

        int read;
        final byte[] bytes = new byte[1024];

        while ((read = iFileStream.read(bytes)) != -1) {
            oFileStream.write(bytes, 0, read);
        }
        return targetFileName;
    }

    private void closeInOutUploadStreams(InputStream iFileStream,
            OutputStream oFileStream) throws IOException {
        if (oFileStream != null) {
            oFileStream.close();
        }
        if (iFileStream != null) {
            iFileStream.close();
        }
    }

}