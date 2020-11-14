package by.epamtc.jwd.auth.web.util.impl.substaff;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.model.constant.RegistrationInfoPattern;
import by.epamtc.jwd.auth.model.med_info.DepartmentOrigin;
import by.epamtc.jwd.auth.model.med_info.MedicineMeasureUnit;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
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
import java.time.LocalDateTime;

public class EstablishMedicinePrescriptionCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger
            (EstablishMedicinePrescriptionCommand.class);

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VisitService visitService = serviceFactory.getVisitService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        String visitId = req.getParameter(AppParameter.VISIT_ID);
        String medicineStrId = req.getParameter(AppParameter.HIDDEN_MEDICINE_ID);
        String targetApplicationDateTimeInput = req.getParameter(AppParameter
                .APPLICATION_DATE_TIME);
        String dosage = req.getParameter(AppParameter.DOSAGE);
        String medicineMeasureUnit = req.getParameter(AppParameter
                .MEDICINE_MEASURE_UNIT);
        MedicinePrescription prescription = compileMedicinePrescription(visitId,
                medicineStrId, targetApplicationDateTimeInput, dosage,
                medicineMeasureUnit);

        boolean isChanged;

        try {
            isChanged = visitService.establishMedicinePrescription(prescription,
                    visitId, user);
        } catch (ServiceException e) {
            logger.error("An error while changing patient's complaints.\n" +
                            "Params{user={}, visitStrId={}, medicineStrId={}" +
                            ", dosage={}, targetApplicationDateTimeInput={}," +
                            " medicineMeasureUnit={}}",
                    user, visitId, medicineStrId, dosage,
                    targetApplicationDateTimeInput, medicineMeasureUnit, e);
//            sendRedirectWithTechError(req, res, visitStrId);
            return;
        }

        if (!isChanged) {
//            sendRedirectWithValidationError(req, res, visitStrId);
            return;
        }

//        sendRedirectWithSuccessMessage(req, res, visitStrId);

    }

    private MedicinePrescription compileMedicinePrescription(String visitStrId,
            String medicineStrId, String targetApplicationDateTimeInput,
            String dosage, String medicineMeasureUnit) {
        MedicinePrescription prescription = new MedicinePrescription();
        prescription.setDepartmentOrigin(DepartmentOrigin.ADMISSION_DEPARTMENT);
        if (visitStrId.matches(RegistrationInfoPattern.DIGITS)) {
            prescription.setOriginDocumentId(Integer.parseInt(visitStrId));
        }

        if (medicineStrId.matches(RegistrationInfoPattern.DIGITS)) {
            prescription.setMedicineId(Integer.parseInt(medicineStrId));
        }

        try {
            LocalDateTime dateTime = LocalDateTime.parse(targetApplicationDateTimeInput);
            prescription.setTargetApplicationDateTime(dateTime);

            double dosageQuantity = Double.parseDouble(dosage);
            prescription.setDosageQuantity(dosageQuantity);

            MedicineMeasureUnit unit = MedicineMeasureUnit.valueOf(medicineMeasureUnit);
            prescription.setDosageMeasureUnit(unit);
        } catch (IllegalArgumentException e) {
            logger.error("An error while MedicinePrescription compilation." +
                            " Params{targetApplicationDateTimeInput={}, dosage={}," +
                            " medicineMeasureUnit={}}", targetApplicationDateTimeInput,
                    dosage, medicineMeasureUnit, e);
        }

        return prescription;
    }
}
