package by.epamtc.jwd.auth.dao;

import by.epamtc.jwd.auth.dao.exception.DaoException;
import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.auth_info.AuthenticationInfo;
import by.epamtc.jwd.auth.model.auth_info.RegistrationInfo;

/**
 * an interface which is created to state contracts of authentication-related
 * activities.
 */
public interface AuthUserDao {
    /**
     * a method which returns an AuthUser object with information distinguishing
     * current user from others
     *
     * @param authenticationInfo a bean object, which stores credentials of a
     *                           separate user of the system,
     *                           in order to sing in it
     * @return an AuthUser object which represent a separate user of the system,
     * or null if the password stored in <code>authenticationInfo</code>-argument
     * is incorrect
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     * @see AuthUser
     */
    AuthUser receiveAuthUserIfCorrect(AuthenticationInfo authenticationInfo)
            throws DaoException;

    /**
     * a method which registers a separate user and returns an AuthUser object
     * if a procedure of registration is completed correctly
     *
     * @param registrationInfo an object that consists of all obligatory for
     *                         registration information
     * @return an AuthUser object that represent a newly created user
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     * @see AuthUser
     */
    AuthUser saveAuthUser(RegistrationInfo registrationInfo) throws DaoException;

    /**
     * a method which checks whether an input (login) already exists
     * in the source
     *
     * @param login a String, which is being looked for
     * @return a boolean primitive type that describes absence or presence
     * of an input in the source
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean containsLogin(String login) throws DaoException;

    /**
     * a method which checks whether an input (email) already exists
     * in the source
     *
     * @param email a String, which is being looked for
     * @return a boolean primitive type that describes absence or presence
     * of an input in the source
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    boolean containsEmail(String email) throws DaoException;

    /**
     * a method that changes a "credential-like" parameter of a user (email)
     * in the source if a input (password) is a valid one
     *
     * @param email    a String, which represents a parameter "email" that
     *                 is to be changed
     * @param password a String, which represents a credential that will be
     *                 evaluated: if it is valid, the main arg "email" will
     *                 be changed
     * @param user     an AuthUser object, which conforms to a user which
     *                 performs this action
     * @return a string representation of a changed email
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    String changeEmailIfCorrect(String email, String password, AuthUser user)
            throws DaoException;

    /**
     * a method that changes a credential of a user (password) in the source
     * if an actual password is inputted correctly
     *
     * @param newPassword a password that is to become nes password
     * @param password    an actual password
     * @param user        an AuthUser object, which conforms to a user which
     *                    performs this action
     * @return a string representation of a changed password
     * @throws DaoException throws if problems connected to database interaction
     *                      or taking a connection from ConnectionPool
     *                      will occur
     */
    String changePasswordIfCorrect(String newPassword, String password,
            AuthUser user) throws DaoException;
}
