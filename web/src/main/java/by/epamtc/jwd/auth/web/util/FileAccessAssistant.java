package by.epamtc.jwd.auth.web.util;

import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.web.exception.ControllerException;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

public class FileAccessAssistant {
    private static volatile FileAccessAssistant instance;
    private static final String WEB_INF_PATH_PART = File.separator + "WEB-INF";
    private static final String UPLOAD_WEB_APP_FOLDER_ON_SERVER = "upload";
    private static final String UPLOAD_USER_PHOTO_FOLDER_ON_SERVER
            = "user_photo";

    private String sourceFilesPath;

    private FileAccessAssistant() throws ControllerException {
        initializeSourceFilesPath();
    }

    public static FileAccessAssistant getInstance() {
        FileAccessAssistant localInstance = instance;
        if (localInstance == null) {
            synchronized (FileAccessAssistant.class) {
                localInstance = instance;
                if (localInstance == null) {
                    try {
                        instance = localInstance = new FileAccessAssistant();
                    } catch (ControllerException e) {
                        //TODO add log4j ("ERROR CAUGHT WHILE FINDING A SOURCES FILES" +
                        //                    "PATH")
                        e.printStackTrace();
                    }
                }
            }
        }
        return localInstance;
    }

    public String formTextFilePath(String textName) throws ControllerException {
        if (sourceFilesPath == null) {
            initializeSourceFilesPath();
        }
        if (textName == null) {
            throw new ControllerException("Uploading file is absent");
        }
        return sourceFilesPath + File.separator + textName;
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

    private void initializeSourceFilesPath() throws ControllerException {
        try {
            Enumeration<URL> resources = Thread.currentThread()
                    .getContextClassLoader().getResources(".");
            sourceFilesPath = new File(resources.nextElement().toURI())
                    .getAbsolutePath();
            int contextPathEndIndex = sourceFilesPath
                    .indexOf(WEB_INF_PATH_PART);
            sourceFilesPath = sourceFilesPath.substring(0, contextPathEndIndex);
            int webappsFolderIndex = sourceFilesPath.lastIndexOf(File.separator);
            sourceFilesPath = sourceFilesPath.substring(0, webappsFolderIndex)
                    + File.separator + UPLOAD_WEB_APP_FOLDER_ON_SERVER
                    + File.separator + UPLOAD_USER_PHOTO_FOLDER_ON_SERVER;
        } catch (IOException | URISyntaxException e) {
            throw new ControllerException("ERROR CAUGHT WHILE FINDING A SOURCES" +
                    " FILES PATH", e);
        }
    }
}
