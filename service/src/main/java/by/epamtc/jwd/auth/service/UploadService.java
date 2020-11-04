package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;

public interface UploadService {
    boolean updatePatientPhoto(String targetFileName, AuthUser user);
}
