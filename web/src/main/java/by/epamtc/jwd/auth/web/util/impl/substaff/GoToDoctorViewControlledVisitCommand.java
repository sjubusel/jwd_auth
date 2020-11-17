package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.CommandPath;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
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

        AdmissionDepartmentVisit visitInfo = null;
        PatientInfo patientInfo = null;
        AllergicReactionsInfo allergicReactionsInfo = null;
        List<ExtremelyHazardousDiseaseCase> diseaseList = null;
        List<Diagnosis> diagnoses = null;
        List<MedicinePrescription> medicinePrescriptions = null;
        List<Prescription> prescriptions = null;


        try {
            visitInfo = visitService.fetchFullAdmissionDepartmentVisit(visitId);

            AuthUser patientAuthUser = new AuthUser();
            patientAuthUser.setUserId(visitInfo.getPatientId());

            patientInfo = profileService.fetchPatientInfo(patientAuthUser);

            allergicReactionsInfo = profileService.fetchAllergicReactionsInfo
                    (patientAuthUser);

            diseaseList = profileService.fetchCasesOfExtremelyHazardousDiseases
                    (patientAuthUser);

            diagnoses = visitService.fetchInnerHospitalDiagnoses(visitInfo
                    .getPatientId());

            medicinePrescriptions = visitService.fetchVisitMedicinePrescriptions
                    (visitId);

            prescriptions = visitService.fetchVisitPrescriptions(visitId);
        } catch (ServiceException e) {
            logger.error("An error while fetching preparing combined " +
                    "information treatment. Params{visitId={}," +
                    "AuthUser={}}", visitId, user, e);
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_TECH);
        }

        if ((visitInfo == null) || (patientInfo == null)
                || (allergicReactionsInfo == null) || (diseaseList == null)
                || (diagnoses == null) || (medicinePrescriptions == null)
                || (prescriptions == null)) {

            setValidationErrorRequestAttributeIfNecessary(req);

        } else {
            addPossibleRequestAttributes(req, visitInfo, patientInfo,
                    allergicReactionsInfo, diseaseList, diagnoses,
                    medicinePrescriptions, prescriptions);
        }

        req.getRequestDispatcher(CommandPath.SUBSTAFF_VISIT_DETAIL_JSP)
                .forward(req, res);
    }

    private void setValidationErrorRequestAttributeIfNecessary(HttpServletRequest req) {
        if (req.getAttribute(AppAttribute.REQUEST_ERROR) == null) {
            req.setAttribute(AppAttribute.REQUEST_ERROR, AppAttribute
                    .REQUEST_ERROR_VALUE_VAL);
        }
    }

    private void addPossibleRequestAttributes(HttpServletRequest req,
            AdmissionDepartmentVisit visitInfo, PatientInfo patientInfo,
            AllergicReactionsInfo allergicReactionsInfo,
            List<ExtremelyHazardousDiseaseCase> diseaseList,
            List<Diagnosis> diagnoses,
            List<MedicinePrescription> medicinePrescriptions,
            List<Prescription> prescriptions) {

        req.setAttribute(AppAttribute.REQUEST_CONTROLLED_VISIT_INFO,
                visitInfo);

        req.setAttribute(AppAttribute.REQUEST_PATIENT_INFO, patientInfo);

        if (allergicReactionsInfo.getAllergicFoodReactions().size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_ALLERGIC_FOOD_REACTIONS,
                    allergicReactionsInfo.getAllergicFoodReactions());
        }

        if (allergicReactionsInfo.getAllergicMedicineReactions().size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_ALLERGIC_MEDICINE_REACTIONS,
                    allergicReactionsInfo.getAllergicMedicineReactions());
        }

        if (diseaseList.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_EXTEREMELY_HAZARDOUS_DISEASES,
                    diseaseList);
        }

        if (diagnoses.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_ALL_TIME_DIAGNOSES,
                    diagnoses);
        }

        if (medicinePrescriptions.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_MEDICINE_PRESCRIPTIONS,
                    medicinePrescriptions);
        }

        if (prescriptions.size() > 0) {
            req.setAttribute(AppAttribute.REQUEST_PRESCRIPTIONS,
                    prescriptions);
        }

        String changeResult = req.getParameter(AppParameter.CHANGE_RESULT);
        if (changeResult != null) {
            req.setAttribute(AppParameter.CHANGE_RESULT, changeResult);
        }

        String cancelPrescriptionResult = req.getParameter(AppParameter
                .CANCEL_PRESCRIPTION_RESULT);
        if (cancelPrescriptionResult != null) {
            req.setAttribute(AppParameter.CANCEL_PRESCRIPTION_RESULT,
                    cancelPrescriptionResult);
        }

        String cancelMedPrescriptionResult = req.getParameter(AppParameter
                .CANCEL_MED_PRESCRIPTION_RESULT);
        if (cancelMedPrescriptionResult != null) {
            req.setAttribute(AppParameter.CANCEL_MED_PRESCRIPTION_RESULT,
                    cancelMedPrescriptionResult);
        }
    }
}
