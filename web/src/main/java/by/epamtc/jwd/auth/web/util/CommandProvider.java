package by.epamtc.jwd.auth.web.util;

import by.epamtc.jwd.auth.model.constant.CommandName;
import by.epamtc.jwd.auth.web.util.impl.ChangeLanguageCommand;
import by.epamtc.jwd.auth.web.util.impl.ErrorCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToAboutUsCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToContactsCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToLoginCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToNewsCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToPatientsCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToProfileCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToRegisterCommand;
import by.epamtc.jwd.auth.web.util.impl.GoToStaffCommand;
import by.epamtc.jwd.auth.web.util.impl.LogOutCommand;
import by.epamtc.jwd.auth.web.util.impl.LoginCommand;
import by.epamtc.jwd.auth.web.util.impl.MainCommand;
import by.epamtc.jwd.auth.web.util.impl.RegisterCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileAllergicReactionsCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangeEmailCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangePasswordCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangePatientInformationCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangeStaffInformationCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileChangeStaffPhotoCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileExtremelyHazardousDiseasesCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileMedicalHistoryPermissionCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileStaffHistoryCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.GoToProfileStaffInformationCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.ProfileAllergicReactionsFoodAddCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.ProfileAllergicReactionsMedicineAddCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.ProfileChangeEmailCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.ProfileChangePasswordCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.ProfileChangePatientInfoCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.ProfileChangePatientPhotoCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.ProfileExtremelyHazardousDiseasesAddCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.ProfileMedicalHistoryPermissionAddCommand;
import by.epamtc.jwd.auth.web.util.impl.subprofile.ProfileMedicalHistoryPermissionDeleteCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.CancelMedicinePrescriptionCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.CancelNonMedicinePrescriptionCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.ChangeComplaintsOfPatientCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.EstablishMedicinePrescriptionCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.EstablishPrescriptionCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.EstablishVisitDiagnosisCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.GoToDoctorViewControlledVisitCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.GoToEstablishDiagnosisCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.GoToEstablishMedPrescriptionCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.GoToEstablishPrescriptionCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.GoToStaffAcceptMedicinePrescriptionCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.GoToStaffNewMedicinePrescriptionsCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.GoToStaffNewNonMedicinePrescriptionsCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.GoToStaffNewVisitsCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.GoToStaffRegisterVisitCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.GoToStaffVisitsOnControlCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.StaffDoctorAcceptPatient;
import by.epamtc.jwd.auth.web.util.impl.substaff.StaffExecuteMedicinePrescriptionCommand;
import by.epamtc.jwd.auth.web.util.impl.substaff.StaffRegisterVisitCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CommandProvider {
    private static volatile CommandProvider instance;

    private final HashMap<String, Command> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.MAIN, new MainCommand());
        repository.put(CommandName.GO_TO_LOGIN, new GoToLoginCommand());
        repository.put(CommandName.LOGIN, new LoginCommand());
        repository.put(CommandName.LOGOUT, new LogOutCommand());
        repository.put(CommandName.GO_TO_REGISTER, new GoToRegisterCommand());
        repository.put(CommandName.REGISTER, new RegisterCommand());
        repository.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        repository.put(CommandName.GO_TO_PROFILE, new GoToProfileCommand());
        repository.put(CommandName.GO_TO_ABOUT_US, new GoToAboutUsCommand());
        repository.put(CommandName.GO_TO_CONTACTS, new GoToContactsCommand());
        repository.put(CommandName.GO_TO_NEWS, new GoToNewsCommand());
        repository.put(CommandName.GO_TO_PATIENTS, new GoToPatientsCommand());
        repository.put(CommandName.GO_TO_STAFF, new GoToStaffCommand());

        repository.put(CommandName.SUBPROFILE_GO_TO_ALLERGIC_REACTIONS,
                new GoToProfileAllergicReactionsCommand());
        repository.put(CommandName.SUBPROFILE_ALLERGIC_REACTIONS_FOOD_ADD,
                new ProfileAllergicReactionsFoodAddCommand());
        repository.put(CommandName.SUBPROFILE_ALLERGIC_REACTIONS_MEDICINE_ADD,
                new ProfileAllergicReactionsMedicineAddCommand());
        repository.put(CommandName.SUBPROFILE_GO_TO_EMAIL_CHANGE,
                new GoToProfileChangeEmailCommand());
        repository.put(CommandName.SUBPROFILE_EMAIL_CHANGE,
                new ProfileChangeEmailCommand());
        repository.put(CommandName.SUBPROFILE_GO_TO_CHANGE_PASSWORD,
                new GoToProfileChangePasswordCommand());
        repository.put(CommandName.SUBPROFILE_CHANGE_PASSWORD,
                new ProfileChangePasswordCommand());
        repository.put(CommandName.SUBPROFILE_GO_TO_CHANGE_PATIENT_INFO,
                new GoToProfileChangePatientInformationCommand());
        repository.put(CommandName.SUBPROFILE_CHANGE_PATIENT_PHOTO,
                new ProfileChangePatientPhotoCommand());
        repository.put(CommandName.SUBPROFILE_CHANGE_PATIENT_INFO,
                new ProfileChangePatientInfoCommand());
        repository.put(CommandName.SUBPROFILE_GO_TO_MEDICAL_HISTORY_PERMISSION,
                new GoToProfileMedicalHistoryPermissionCommand());
        repository.put(CommandName.SUBPROFILE_MEDICAL_HISTORY_PERMISSION_DELETE,
                new ProfileMedicalHistoryPermissionDeleteCommand());
        repository.put(CommandName.SUBPROFILE_MEDICAL_HISTORY_PERMISSION_ADD,
                new ProfileMedicalHistoryPermissionAddCommand());
        repository.put(CommandName.SUBPROFILE_GO_TO_EXTREMELY_HAZARDOUS_DISEASES,
                new GoToProfileExtremelyHazardousDiseasesCommand());
        repository.put(CommandName.SUBPROFILE_EXTREMELY_HAZARDOUS_DISEASES_ADD,
                new ProfileExtremelyHazardousDiseasesAddCommand());

        repository.put(CommandName.SUBPROFILE_GO_TO_CHANGE_STAFF_INFO,
                new GoToProfileChangeStaffInformationCommand());
        repository.put(CommandName.SUBPROFILE_GO_TO_CHANGE_STAFF_PHOTO,
                new GoToProfileChangeStaffPhotoCommand());
        repository.put(CommandName.SUBPROFILE_GO_TO_STAFF_HISTORY,
                new GoToProfileStaffHistoryCommand());
        repository.put(CommandName.SUBPROFILE_GO_TO_STAFF_INFO,
                new GoToProfileStaffInformationCommand());

        repository.put(CommandName.SUBSTAFF_GO_TO_REGISTER_VISIT,
                new GoToStaffRegisterVisitCommand());
        repository.put(CommandName.SUBSTAFF_REGISTER_VISIT,
                new StaffRegisterVisitCommand());
        repository.put(CommandName.SUBSTAFF_GO_TO_STAFF_NEW_VISITS,
                new GoToStaffNewVisitsCommand());
        repository.put(CommandName.SUBSTAFF_DOCTOR_ACCEPT_PATIENT,
                new StaffDoctorAcceptPatient());
        repository.put(CommandName.SUBSTAFF_GO_TO_VISITS_ON_CONTROL,
                new GoToStaffVisitsOnControlCommand());
        repository.put(CommandName.SUBSTAFF_GO_TO_DOCTOR_VIEW_CONTROLLED_VISIT,
                new GoToDoctorViewControlledVisitCommand());
        repository.put(CommandName.SUBSTRAFF_CHANGE_COMPLAINTS_OF_PATIENT,
                new ChangeComplaintsOfPatientCommand());
        repository.put(CommandName.SUBSTAFF_GO_TO_ESTABLISH_DIAGNOSIS,
                new GoToEstablishDiagnosisCommand());
        repository.put(CommandName.SUBSTAFF_ESTABLISH_DIAGNOSIS,
                new EstablishVisitDiagnosisCommand());
        repository.put(CommandName.SUBSTAFF_GO_TO_ESTABLISH_MED_PRESCRIPTION,
                new GoToEstablishMedPrescriptionCommand());
        repository.put(CommandName.SUBSTAFF_ESTABLISH_MEDICINE_PRESCRIPTION,
                new EstablishMedicinePrescriptionCommand());
        repository.put(CommandName.SUBSTAFF_GO_TO_ESTABLISH_PRESCRIPTION,
                new GoToEstablishPrescriptionCommand());
        repository.put(CommandName.SUBSTAFF_ESTABLISH_PRESCRIPTION,
                new EstablishPrescriptionCommand());
        repository.put(CommandName.SUBSTAFF_CANCEL_NON_MEDICINE_PRESCRIPTION,
                new CancelNonMedicinePrescriptionCommand());
        repository.put(CommandName.SUBSTAFF_CANCEL_MEDICINE_PRESCRIPTION,
                new CancelMedicinePrescriptionCommand());
        repository.put(CommandName.SUBSTAFF_GO_TO_NEW_MEDICINE_PRESCRIPTIONS,
                new GoToStaffNewMedicinePrescriptionsCommand());
        repository.put(CommandName.SUBSTAFF_GO_TO_ACCEPT_MEDICINE_PRESCRIPTION,
                new GoToStaffAcceptMedicinePrescriptionCommand());
        repository.put(CommandName.SUBSTAFF_EXECUTE_MEDICINE_PRESCRIPTION,
                new StaffExecuteMedicinePrescriptionCommand());
        repository.put(CommandName.SUBSTAFF_GO_TO_NEW_NON_MED_PRESCRIPTIONS,
                new GoToStaffNewNonMedicinePrescriptionsCommand());

        repository.put(CommandName.ERROR, new ErrorCommand());
    }

    public static CommandProvider getInstance() {
        CommandProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (CommandProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CommandProvider();
                }
            }
        }
        return localInstance;
    }

    public void execute(String commandName, HttpServletRequest req,
            HttpServletResponse res) throws ServletException, IOException {
        Command command = repository.get(commandName);
        if (command == null) {
            command = repository.get(CommandName.ERROR);
        }
        command.execute(req, res);
    }
}
