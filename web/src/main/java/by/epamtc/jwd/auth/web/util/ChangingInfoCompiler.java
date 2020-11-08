package by.epamtc.jwd.auth.web.util;

import by.epamtc.jwd.auth.model.user_info.Address;
import by.epamtc.jwd.auth.model.user_info.BloodType;
import by.epamtc.jwd.auth.model.user_info.DisabilityDegree;
import by.epamtc.jwd.auth.model.user_info.Gender;
import by.epamtc.jwd.auth.model.user_info.IdentificationDocumentType;
import by.epamtc.jwd.auth.model.user_info.IdentityDocument;
import by.epamtc.jwd.auth.model.user_info.MaritalStatus;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;
import by.epamtc.jwd.auth.model.user_info.RhBloodGroup;
import by.epamtc.jwd.auth.model.user_info.TransportationStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class ChangingInfoCompiler {
    private static volatile ChangingInfoCompiler instance;

    private RegistrationInfoCompiler regInfCompiler = RegistrationInfoCompiler
            .getInstance();

    private ChangingInfoCompiler() {
    }

    public static ChangingInfoCompiler getInstance() {
        ChangingInfoCompiler localInstance = instance;
        if (localInstance == null) {
            synchronized (ChangingInfoCompiler.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance
                            = new ChangingInfoCompiler();
                }
            }
        }
        return localInstance;
    }

    public PatientInfo compileChangingPatientInfo(HttpServletRequest req) {
        PatientInfo patientInfo = new PatientInfo();

        boolean isNewPhone = Boolean.parseBoolean(req.getParameter("isNewPhone"));
        if (isNewPhone) {
            String phoneNumberCountryCode = req.getParameter("phoneNumberCountryCode");
            String phoneNumberInnerCode = req.getParameter("phoneNumberInnerCode");
            String phoneNumberInnerNumber = req.getParameter("phoneNumberInnerNumber");
            String newPhoneNumber = regInfCompiler.compilePhoneNumber(phoneNumberCountryCode,
                    phoneNumberInnerCode, phoneNumberInnerNumber);
            patientInfo.setPhoneNumber(newPhoneNumber);
        }

//      compileMaritalStatus(maritalStatusInput)
        String maritalStatusInput = req.getParameter("maritalStatusInput");
        if (maritalStatusInput != null) {
            MaritalStatus maritalStatus = MaritalStatus.valueOf(maritalStatusInput);
            patientInfo.setMaritalStatus(maritalStatus);
        }

        boolean isNewIdDocument = Boolean.parseBoolean(req.getParameter("isNewIdDocument"));
        if (isNewIdDocument) {
            IdentityDocument identityDocument = new IdentityDocument();

            String idDocumentInput = req.getParameter("idDocumentInput");
            // TODO logj4 and handle exception Runtime
            IdentificationDocumentType documentType = IdentificationDocumentType
                    .valueOf(idDocumentInput);
            identityDocument.setIdentificationDocumentType(documentType);

            String seriesInput = req.getParameter("seriesInput");
            seriesInput = compileNullReferenceIfEmpty(seriesInput);
            identityDocument.setSeries(seriesInput);

            String idDocumentNumberInput = req.getParameter("idDocumentNumberInput");
            idDocumentNumberInput = compileNullReferenceIfEmpty(idDocumentNumberInput);
            // TODO logj4 and handle exception Runtime
            identityDocument.setDocumentNumber(Integer.parseInt(idDocumentNumberInput));

            String latinHolderNameInput = req.getParameter("latinHolderNameInput");
            latinHolderNameInput = compileNullReferenceIfEmpty(latinHolderNameInput);
            identityDocument.setLatinHolderName(latinHolderNameInput);

            String latinHolderSurnameInput = req.getParameter("latinHolderSurnameInput");
            latinHolderSurnameInput = compileNullReferenceIfEmpty(latinHolderSurnameInput);
            identityDocument.setLatinHolderSurName(latinHolderSurnameInput);

            String hiddenCitizenshipInput = req.getParameter("hiddenCitizenshipInput");
            hiddenCitizenshipInput = compileNullReferenceIfEmpty(hiddenCitizenshipInput);
            identityDocument.setCitizenShip(hiddenCitizenshipInput);

            String birthdayInput = req.getParameter("birthdayInput");
            LocalDate birthDay = regInfCompiler.compileBirthdayDate(birthdayInput);
            identityDocument.setBirthday(birthDay);

            String personalNumberInput = req.getParameter("personalNumberInput");
            personalNumberInput = compileNullReferenceIfEmpty(personalNumberInput);
            identityDocument.setPersonalNumber(personalNumberInput);

            String gender = req.getParameter("gender");
            Gender genderEnum = regInfCompiler.compileGenderEnum(gender);
            identityDocument.setGender(genderEnum);

            String placeOfOriginInput = req.getParameter("placeOfOriginInput");
            placeOfOriginInput = compileNullReferenceIfEmpty(placeOfOriginInput);
            identityDocument.setPlaceOfOrigin(placeOfOriginInput);

            String dateOfIssueInput = req.getParameter("dateOfIssueInput");
            LocalDate dateOfIssue = regInfCompiler.compileBirthdayDate(dateOfIssueInput);
            identityDocument.setDateOfIssue(dateOfIssue);

            String dateOfExpiryInput = req.getParameter("dateOfExpiryInput");
            LocalDate dateOfExpiry = regInfCompiler.compileBirthdayDate(dateOfExpiryInput);
            identityDocument.setDateOfExpiry(dateOfExpiry);

            String issueAuthorityInput = req.getParameter("issueAuthorityInput");
            issueAuthorityInput = compileNullReferenceIfEmpty(issueAuthorityInput);
            identityDocument.setIssueAuthority(issueAuthorityInput);

            patientInfo.setIdentityDocument(identityDocument);
        }

        boolean isNewAddress = Boolean.parseBoolean(req.getParameter("isNewAddress"));
        if (isNewAddress) {
            Address address = new Address();

            String zipCodeInput = req.getParameter("zipCodeInput");
            zipCodeInput = compileNullReferenceIfEmpty(zipCodeInput);
            address.setZipCode(zipCodeInput);

            String hiddenCountryInput = req.getParameter("hiddenCountryInput");
            hiddenCountryInput = compileNullReferenceIfEmpty(hiddenCountryInput);
            address.setCountry(hiddenCountryInput);

            String hiddenRegionInput = req.getParameter("hiddenRegionInput");
            hiddenRegionInput = compileNullReferenceIfEmpty(hiddenRegionInput);
            address.setRegion(hiddenRegionInput);

            String hiddenAreaInput = req.getParameter("hiddenAreaInput");
            hiddenAreaInput = compileNullReferenceIfEmpty(hiddenAreaInput);
            address.setArea(hiddenAreaInput);

            String hiddenSettlementInput = req.getParameter("hiddenSettlementInput");
            hiddenSettlementInput = compileNullReferenceIfEmpty(hiddenSettlementInput);
            address.setSettlement(hiddenSettlementInput);

            String hiddenRoadInput = req.getParameter("hiddenRoadInput");
            hiddenRoadInput = compileNullReferenceIfEmpty(hiddenRoadInput);
            address.setRoad(hiddenRoadInput);

            String houseInput = req.getParameter("houseInput");
            houseInput = compileNullReferenceIfEmpty(houseInput);
            address.setHouse(houseInput);

            String buildingInput = req.getParameter("buildingInput");
            buildingInput = compileNullReferenceIfEmpty(buildingInput);
            address.setBuilding(buildingInput);

            String roomInput = req.getParameter("roomInput");
            roomInput = compileNullReferenceIfEmpty(roomInput);
            address.setRoom(roomInput);

            patientInfo.setHomeAddress(address);
        }

        String contactPersonInput = req.getParameter("inCaseOfEmergencyContactPersonInfoInput");
        contactPersonInput = compileNullReferenceIfEmpty(contactPersonInput);
        patientInfo.setInCaseOfEmergencyContactPersonInfo(contactPersonInput);

        boolean isNewEmergencyPhone = Boolean.parseBoolean(req.getParameter("isNewEmergencyPhone"));
        if (isNewEmergencyPhone) {
            String emergencyCountryCode = req.getParameter("emergencyPhoneNumberCountryCode");
            String emergencyInnerCode = req.getParameter("emergencyPhoneNumberInnerCode");
            String emergencyInnerNumber = req.getParameter("emergencyPhoneNumberInnerNumber");
            String emergencyNumber = regInfCompiler.compilePhoneNumber(emergencyCountryCode, emergencyInnerCode, emergencyInnerNumber);
            patientInfo.setInCaseOfEmergencyContactPersonPhone(emergencyNumber);
        }

        String bloodTypeInput = req.getParameter("bloodTypeInput");
        if (bloodTypeInput != null) {
            BloodType bloodTypeEnum = null;
            BloodType[] bloodTypes = BloodType.values();
            for (BloodType bloodType : bloodTypes) {
                if (bloodTypeInput.equals(bloodType.getValue())) {
                    bloodTypeEnum = bloodType;
                }
            }
            patientInfo.setBloodType(bloodTypeEnum);
        }

        String rhBloodGroupInput = req.getParameter("rhBloodGroupInput");
        if (rhBloodGroupInput != null) {
            RhBloodGroup rhBloodGroupEnum = null;
            RhBloodGroup[] rhBloodGroups = RhBloodGroup.values();
            for (RhBloodGroup rhBloodGroup : rhBloodGroups) {
                if (rhBloodGroupInput.equals(rhBloodGroup.getValue())) {
                    rhBloodGroupEnum = rhBloodGroup;
                }
            }
            patientInfo.setRhBloodGroup(rhBloodGroupEnum);
        }

        String disabilityDegreeInput = req.getParameter("disabilityDegreeInput");
        if (disabilityDegreeInput != null) {
            DisabilityDegree disabilityDegreeEnum = null;
            DisabilityDegree[] disabilityDegrees = DisabilityDegree.values();
            for (DisabilityDegree disabilityDegree : disabilityDegrees) {
                if (Integer.parseInt(disabilityDegreeInput) == disabilityDegree.getValue()) {
                    disabilityDegreeEnum = disabilityDegree;
                }
            }
            patientInfo.setDisabilityDegree(disabilityDegreeEnum);
        }


        String transportationStatusInput = req.getParameter("transportationStatusInput");
        if (transportationStatusInput != null) {
            TransportationStatus transportationStatus = TransportationStatus
                    .valueOf(transportationStatusInput);
            patientInfo.setTransportationStatus(transportationStatus);
        }

        return patientInfo;
    }

    private String compileNullReferenceIfEmpty(String input) {
        if (input.equals("") || input.matches("[\\s]+")) {
            input = null;
        }
        return input;
    }
}
