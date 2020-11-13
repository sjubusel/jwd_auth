package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.user_info.AllergicReactionsInfo;
import by.epamtc.jwd.auth.model.user_info.ExtremelyHazardousDiseaseCase;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.service.ProfileService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.VisitService;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToDoctorViewControlledVisitCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (GoToDoctorViewControlledVisitCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();
    private ProfileService profileService = serviceFactory.getProfileService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String visitId = req.getParameter(AppParameter.VISIT_ID);
        AdmissionDepartmentVisit visitInfo;
        PatientInfo patientInfo = null;
        AllergicReactionsInfo allergicReactionsInfo = null;
        List<ExtremelyHazardousDiseaseCase> diseaseList = null;
        List<Diagnosis> diagnosisList = null;


        try {
            visitInfo = visitService.fetchFullAdmissionDepartmentVisit(visitId);
            AuthUser patientAuthUser = new AuthUser();
            patientAuthUser.setUserId(visitInfo.getPatientId());
            patientInfo = profileService.fetchPatientInfo(patientAuthUser);
            allergicReactionsInfo = profileService.fetchAllergicReactionsInfo
                    (patientAuthUser);
            diseaseList = profileService.fetchCasesOfExtremelyHazardousDiseases
                    (patientAuthUser);
            diagnosisList = visitService.fetchInnerHospitalDiagnoses(visitInfo
                    .getPatientId());
        } catch (ServiceException e) {
            logger.error("An error while fetching preparing combined " +
                    "information treatment. Params{visitId={}," +
                    "AuthUser={}}", visitId, user, e);
        }


    }
}
