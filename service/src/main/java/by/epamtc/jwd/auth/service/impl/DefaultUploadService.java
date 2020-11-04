package by.epamtc.jwd.auth.service.impl;

import by.epamtc.jwd.auth.dao.DaoFactory;
import by.epamtc.jwd.auth.dao.UpdateDao;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.service.UploadService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.service.util.FileAccessAssistant;
import by.epamtc.jwd.auth.service.validation.UpdateRelatedValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DefaultUploadService implements UploadService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UpdateDao updateDao = daoFactory.getUpdateDao();
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
        } catch (FileNotFoundException e) {
            // "You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location."
            // when the output file is not created
            // не получилось создать файл для передачи в outputstream uploadPatientPhotoToServer
            throw new ServiceException(e);
        } catch (IOException e) {
            // "You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location."
            // не получилось произвести запись через outputstream
            throw new ServiceException(e);
        } finally {
            closeInOutUploadStreams(iFileStreamFromClient, oFileStream);
        }
//        catch (ServiceException e) {
//            // TODO log4j "You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location."
//            // TODO add redirect
//            // передан null вместо файла
//            // не получилось сформировать пути к серверу
//        }
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
            //TODO lof4j
        }
    }
}
