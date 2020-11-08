package by.epamtc.jwd.auth.web.util.impl.subprofile;

import by.epamtc.jwd.auth.model.auth_info.AuthUser;
import by.epamtc.jwd.auth.model.constant.AppAttribute;
import by.epamtc.jwd.auth.service.ProfileService;
import by.epamtc.jwd.auth.service.ServiceFactory;
import by.epamtc.jwd.auth.web.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileChangePatientInfo implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProfileService profileService = serviceFactory.getProfileService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AuthUser user = (AuthUser) req.getSession().getAttribute(AppAttribute
                .SESSION_AUTH_USER);
        // вытянуть всю информацию которая пришла сложить всё в объект и направить в сервисы на зменение

        String isNewPhone = req.getParameter("isNewPhone");
        String phoneNumberCountryCode = req.getParameter("phoneNumberCountryCode");
        String phoneNumberInnerCode = req.getParameter("phoneNumberInnerCode");
        String phoneNumberInnerNumber = req.getParameter("phoneNumberInnerNumber");
        String maritalStatusInput = req.getParameter("maritalStatusInput");
        String isNewIdDocument = req.getParameter("isNewIdDocument");
        String idDocumentInput = req.getParameter("idDocumentInput");
        String seriesInput = req.getParameter("seriesInput");
        String idDocumentNumberInput = req.getParameter("idDocumentNumberInput");
        String latinHolderNameInput = req.getParameter("latinHolderNameInput");
        String latinHolderSurnameInput = req.getParameter("latinHolderSurnameInput");
        String hiddenCitizenshipInput = req.getParameter("hiddenCitizenshipInput");
        String citizenshipInput = req.getParameter("citizenshipInput");
        String birthdayInput = req.getParameter("birthdayInput");
        String personalNumberInput = req.getParameter("personalNumberInput");
        String gender = req.getParameter("gender");
        String placeOfOriginInput = req.getParameter("placeOfOriginInput");
        String dateOfIssueInput = req.getParameter("dateOfIssueInput");
        String dateOfExpiryInput = req.getParameter("dateOfExpiryInput");
        String issueAuthorityInput = req.getParameter("issueAuthorityInput");
        String isNewAddress = req.getParameter("isNewAddress");
        String zipCodeInput = req.getParameter("zipCodeInput");
        String hiddenCountryInput = req.getParameter("hiddenCountryInput");
        String countryInput = req.getParameter("countryInput");
        String hiddenRegionInput = req.getParameter("hiddenRegionInput");
        String regionInput = req.getParameter("regionInput");
        String hiddenAreaInput = req.getParameter("hiddenAreaInput");
        String areaInput = req.getParameter("areaInput");
        String hiddenSettlementInput = req.getParameter("hiddenSettlementInput");
        String settlementInput = req.getParameter("settlementInput");
        String hiddenRoadInput = req.getParameter("hiddenRoadInput");
        String roadInput = req.getParameter("roadInput");
        String houseInput = req.getParameter("houseInput");
        String buildingInput = req.getParameter("buildingInput");
        String roomInput = req.getParameter("roomInput");
        String inCaseOfEmergencyContactPersonInfoInput = req.getParameter("inCaseOfEmergencyContactPersonInfoInput");
        String isNewEmergencyPhone = req.getParameter("isNewEmergencyPhone");
        String emergencyPhoneNumberCountryCode = req.getParameter("emergencyPhoneNumberCountryCode");
        String emergencyPhoneNumberInnerCode = req.getParameter("emergencyPhoneNumberInnerCode");
        String emergencyPhoneNumberInnerNumber = req.getParameter("emergencyPhoneNumberInnerNumber");
        String bloodTypeInput = req.getParameter("bloodTypeInput");
        String rhBloodGroupInput = req.getParameter("rhBloodGroupInput");
        String disabilityDegreeInput = req.getParameter("disabilityDegreeInput");
        String transportationStatusInput = req.getParameter("transportationStatusInput");
        System.out.println("GOOD");
    }
}
