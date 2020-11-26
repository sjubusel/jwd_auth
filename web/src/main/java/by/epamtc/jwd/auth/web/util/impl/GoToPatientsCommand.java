package by.epamtc.jwd.auth.web.util.impl;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.service.PatientService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.service.exception.ServiceException;
import by.epamtc.jwd.auth.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToPatientsCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(
            GoToPatientsCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PatientService patientService = serviceFactory.getPatientService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        AdmissionDepartmentVisit visitInfo = null;
        List<Diagnosis> diagnoses = null;
        List<MedicinePrescription> medPrescriptionss = null;
        List<Prescription> prescriptions = null;

        try {
            visitInfo = patientService.fetchFullVisitIfExist(user);
            diagnoses = patientService.fetchDiagnosesForPatientDuringVisit(user,
                    visitInfo.getVisitId());
            medPrescriptionss = patientService.fetchMedPrescriptionsFinishedDuringVisit(
                    user, visitInfo.getVisitId());
            prescriptions = patientService.fetchPrescriptionsFinishedDuringVisit(
                    user, visitInfo.getVisitId());
        } catch (ServiceException e) {
            logger.error("An error while fetching full visit information " +
                    "if it exists.", e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if (visitInfo == null || diagnoses == null || medPrescriptionss == null
                || prescriptions == null) {
            if (req.getAttribute(AppAttribute.REQUEST_ERROR) == null) {
                req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                        .REQUEST_ERROR_VALUE_VAL);
            }
        } else {
            if (diagnoses.size() > 0) {
                req.setAttribute(AppAttribute.REQUEST_DIAGNOSES, diagnoses);
            }
            if (medPrescriptionss.size() > 0) {
                req.setAttribute(AppAttribute.REQUEST_MEDICINE_PRESCRIPTIONS,
                        medPrescriptionss);
            }
            if (prescriptions.size() > 0) {
                req.setAttribute(AppAttribute.REQUEST_PRESCRIPTIONS, prescriptions);
            }
        }

        req.setAttribute(AppAttribute.REQUEST_CONTROLLED_VISIT_INFO, visitInfo);
        req.getRequestDispatcher(CommandPath.PATIENTS_JSP).forward(req, res);
    }
}
