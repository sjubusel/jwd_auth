package by.epamtc.jwd.auth.service.util;

import by.epamtc.jwd.auth.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

public final class FileAccessAssistant {
    private static final Logger logger = LoggerFactory.getLogger
            (FileAccessAssistant.class);
    private static volatile FileAccessAssistant instance;
    private static final String WEB_INF_PATH_PART = File.separator + "WEB-INF";
    private static final String UPLOAD_WEB_APP_FOLDER_ON_SERVER = "upload";
    private static final String UPLOAD_USER_PHOTO_FOLDER_ON_SERVER
            = "user_photo";

    private String sourceFilesPath;

    private FileAccessAssistant() throws ServiceException {
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
                    } catch (ServiceException e) {
                        logger.error("An error occurred while initialization of \n" +
                                "by.epamtc.jwd.auth.service.util.FileAccessAssistant\n" +
                                "when You either did not specify a file to \n" +
                                "upload or are trying to upload a file to a \n" +
                                "protected or nonexistent location", e);
                    }
                }
            }
        }
        return localInstance;
    }

    public String formTextFilePath(String textName) throws ServiceException {
        if (sourceFilesPath == null) {
            initializeSourceFilesPath();
        }
        if (textName == null) {
            // передан null вместо файла
            throw new ServiceException("Uploading file is absent");
        }
        return sourceFilesPath + File.separator + textName;
    }

    private void initializeSourceFilesPath() throws ServiceException {
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
            // не получилось сформировать пути к серверу
            throw new ServiceException("Error caught while finding a sources" +
                    " files path", e);
        }
    }
}
