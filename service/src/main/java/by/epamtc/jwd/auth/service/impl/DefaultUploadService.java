package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.UploadDao;
import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.service.UploadService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.exception.UploadServiceException;
import by.epamtc.jwd.auth.service.util.FileAccessAssistant;
import by.epamtc.jwd.auth.service.validation.UpdateRelatedValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DefaultUploadService implements UploadService {
    private static final Logger logger = LoggerFactory.getLogger
            (DefaultUploadService.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UploadDao uploadDao = daoFactory.getUploadDao();
    private UpdateRelatedValidator updateValidator = new UpdateRelatedValidator();
    private FileAccessAssistant fileAssistant = FileAccessAssistant
            .getInstance();

    @Override
    public boolean updatePatientPhoto(String targetFileName,
            InputStream iFileStreamFromClient, AuthUser user) throws ServiceException {
        if (!updateValidator.isInitDataValidToUpdate(targetFileName,
                iFileStreamFromClient, user)) {
            return false;
        }

        OutputStream oFileStream = null;
        try {
            uploadPatientPhotoToServer(targetFileName, iFileStreamFromClient,
                    oFileStream);
            uploadDao.updatePatientPhoto(targetFileName, user);
        } catch (IOException e) {
            // "You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location."
            // when the output file is not created
            // не получилось создать файл для передачи в outputstream uploadPatientPhotoToServer
            // не получилось произвести запись через outputstream
            throw new ServiceException(e);
        } catch (DaoException e) {
            // Пошло не так с обновлением информации в базе
            throw new UploadServiceException(e);
        } finally {
            closeInOutUploadStreams(iFileStreamFromClient, oFileStream);
        }

        return true;
    }

    private void uploadPatientPhotoToServer(String targetFileName,
            InputStream iFileStream, OutputStream oFileStream)
            throws ServiceException, IOException {
        File targetFile = new File(fileAssistant.formTextFilePath(targetFileName));
        oFileStream = new FileOutputStream(targetFile);

        int read;
        final byte[] bytes = new byte[1024];

        while ((read = iFileStream.read(bytes)) != -1) {
            oFileStream.write(bytes, 0, read);
        }
    }

    private void closeInOutUploadStreams(InputStream iFileStream,
            OutputStream oFileStream) {
        try {
            if (oFileStream != null) {
                oFileStream.close();
            }
            if (iFileStream != null) {
                iFileStream.close();
            }
        } catch (IOException e) {
            logger.error("An error occurred while closing of IO streams,\n" +
                    "which are used during file uploading:\n" +
                    "InputStream: \"{}\",\n" +
                    "OutputStream: \"{}\"\n", iFileStream, oFileStream, e);
        }
    }
}
