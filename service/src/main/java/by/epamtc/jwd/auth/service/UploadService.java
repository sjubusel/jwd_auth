package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.service.exception.ServiceException;

import java.io.InputStream;

public interface UploadService {
    boolean updatePatientPhoto(String targetFileName,
            InputStream iFileStreamFromClient, AuthUser user)
            throws ServiceException;
}
