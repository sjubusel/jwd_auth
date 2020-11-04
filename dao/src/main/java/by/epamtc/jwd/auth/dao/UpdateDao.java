package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;

public interface UpdateDao {
    boolean updatePatientPhoto(String targetFileName, AuthUser user) throws DaoException;
}
