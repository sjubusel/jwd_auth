package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.UploadService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.exception.UploadServiceException;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProfileChangePatientPhoto implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UploadService uploadService = serviceFactory.getUploadService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);

        Part filePart = req.getPart(AppParameter.PHOTO_UPLOAD);
        String targetFileName = formFileName(filePart);
        InputStream iFileStreamFromClient = filePart.getInputStream();

        if (targetFileName == null) {
            sendRedirectWithIncorrectFileNameMessage(req, res);
            return;
        }

        boolean isUploaded;
        try {
            isUploaded = uploadService.updatePatientPhoto(targetFileName,
                    iFileStreamFromClient, user);
        } catch (UploadServiceException e) {
            // TODO log4j that FILE is uploaded, but info is not.
            sendRedirectWithTechError(req, res);
            return;
        } catch (ServiceException e) {
            // TODO log4j
            // не получилось сформировать пути к серверу IOException | URISyntaxException → FileAccessAssistant
            // передан null вместо файла → FileAccessAssistant инициализация
            // не получилось создать файл для передачи в outputstream uploadPatientPhotoToServer
            // не получилось произвести запись через outputstream
            e.printStackTrace();
            sendRedirectWithTechError(req, res);
            return;
        }

        if (!isUploaded) {
            sendRedirectWithValidationError(req, res);
            return;
        }

        sendRedirectWithSuccessMessage(req, res);
    }

    private void sendRedirectWithTechError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_TECH_ERROR);
    }

    private void sendRedirectWithValidationError(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_VALIDATION_ERROR);
    }

    private void sendRedirectWithIncorrectFileNameMessage(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_INCORRECT_FILE_NAME);
    }

    private void sendRedirectWithSuccessMessage(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + CommandPath
                .SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO_SUCCESS_UPLOAD);
    }

    public String formFileName(final Part part) {
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
}