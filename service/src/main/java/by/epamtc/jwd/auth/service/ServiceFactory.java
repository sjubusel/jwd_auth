package by.epamtc.jwd.auth.service;

import by.epamtc.jwd.auth.service.impl.DefaultAuthUserService;
import by.epamtc.jwd.auth.service.impl.DefaultHospitalReportService;
import by.epamtc.jwd.auth.service.impl.DefaultProfileService;
import by.epamtc.jwd.auth.service.impl.DefaultUploadService;

public class ServiceFactory {
    private static volatile ServiceFactory instance;
    private final HospitalReportService hospitalReportService
            = new DefaultHospitalReportService();
    private final AuthUserService authUserService = new DefaultAuthUserService();
    private final ProfileService profileService = new DefaultProfileService();
    private final UploadService uploadService = new DefaultUploadService();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        ServiceFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ServiceFactory();
                }
            }
        }
        return localInstance;
    }

    public HospitalReportService getHospitalReportService() {
        return hospitalReportService;
    }

    public AuthUserService getAuthUserService() {
        return authUserService;
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public UploadService getUploadService() {
        return uploadService;
    }
}
