package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;

/**
 * a interface which establishes method that will be responsible for
 * uploading any compatible information to the source
 */
public interface UploadDao {
    /**
     * a method which saves to a database information about an uploaded photo
     * of a patient
     *
     * @param targetFileName a name of file with appended relative path to its
     *                       folder, which can be executed by a Tomcat Server
     * @param user           an AuthUser object which identifies a person
     *                       whom photo is uploaded
     * @return true if changes about uploaded photo is added to the source
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean updatePatientPhoto(String targetFileName, AuthUser user)
            throws DaoException;
}
