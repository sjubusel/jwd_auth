package by.epamtc.jwd.auth.web.util;

import by.epamtc.jwd.auth.model.constant.AppParameter;
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

        String newPhoneNumber = compileNewPhone(req);
        patientInfo.setPhoneNumber(newPhoneNumber);

        String maritalStatusInput = req.getParameter(AppParameter.MARITAL_STATUS);
        MaritalStatus maritalStatus = compileMaritalStatus(maritalStatusInput);
        patientInfo.setMaritalStatus(maritalStatus);

        IdentityDocument identityDocument = compileIdentityDocument(req);
        patientInfo.setIdentityDocument(identityDocument);

        Address address = compileAddress(req);
        patientInfo.setHomeAddress(address);

        String contactPersonInput = req.getParameter(AppParameter
                .IN_CASE_OF_EMERGENCY_CONTACT_PERSON_INFO);
        contactPersonInput = compileNullReferenceIfEmpty(contactPersonInput);
        patientInfo.setInCaseOfEmergencyContactPersonInfo(contactPersonInput);

        String emergencyNumber = compileEmergencyNumber(req);
        patientInfo.setInCaseOfEmergencyContactPersonPhone(emergencyNumber);

        String bloodTypeInput = req.getParameter(AppParameter.BLOOD_TYPE);
        BloodType bloodTypeEnum = compileBloodTypeEnum(bloodTypeInput);
        patientInfo.setBloodType(bloodTypeEnum);

        String rhBloodGroupInput = req.getParameter(AppParameter
                .RH_BLOOD_GROUP);
        RhBloodGroup rhBloodGroupEnum = compileRhBloodGroupEnum(rhBloodGroupInput);
        patientInfo.setRhBloodGroup(rhBloodGroupEnum);

        String disabilityDegreeInput = req.getParameter(AppParameter
                .DISABILITY_DEGREE);
        DisabilityDegree disabilityDegreeEnum = compileDisabilityDegree
                (disabilityDegreeInput);
        patientInfo.setDisabilityDegree(disabilityDegreeEnum);

        String transportationStatusInput = req.getParameter(AppParameter
                .TRANSPORTATION_STATUS);
        TransportationStatus transportationStatus = compileTransStatusEnum
                (transportationStatusInput);
        patientInfo.setTransportationStatus(transportationStatus);

        return patientInfo;
    }

    private String compileNewPhone(HttpServletRequest req) {
        boolean isNewPhone = Boolean.parseBoolean(req.getParameter(AppParameter
                .IS_NEW_PHONE));
        if (isNewPhone) {
            String phoneNumberCountryCode = req.getParameter(AppParameter.NUMBER_COUNTRY_CODE);
            String phoneNumberInnerCode = req.getParameter(AppParameter.NUMBER_INNER_CODE);
            String phoneNumberInnerNumber = req.getParameter(AppParameter.INNER_NUMBER);
            return regInfCompiler.compilePhoneNumber(phoneNumberCountryCode,
                    phoneNumberInnerCode, phoneNumberInnerNumber);
        }
        return null;
    }

    private DisabilityDegree compileDisabilityDegree(String disabilityDegreeInput) {
        if (disabilityDegreeInput != null) {
            DisabilityDegree[] disabilityDegrees = DisabilityDegree.values();
            for (DisabilityDegree disabilityDegree : disabilityDegrees) {
                if (Integer.parseInt(disabilityDegreeInput) == disabilityDegree.getValue()) {
                    return disabilityDegree;
                }
            }
        }
        return null;
    }

    private String compileNullReferenceIfEmpty(String input) {
        if (input.equals("") || input.matches("[\\s]+")) {
            input = null;
        }
        return input;
    }

    private MaritalStatus compileMaritalStatus(String maritalStatusInput) {
        if (maritalStatusInput != null) {
            return MaritalStatus.valueOf(maritalStatusInput);
        }
        return null;
    }

    private IdentityDocument compileIdentityDocument(HttpServletRequest req)
            throws RuntimeException {
        boolean isNewIdDocument = Boolean.parseBoolean(req
                .getParameter(AppParameter.IS_NEW_DOCUMENT));
        if (isNewIdDocument) {
            IdentityDocument identityDocument = new IdentityDocument();

            String idDocumentInput = req.getParameter(AppParameter
                    .ID_DOCUMENT);
            IdentificationDocumentType documentType = IdentificationDocumentType
                    .valueOf(idDocumentInput);
            identityDocument.setIdentificationDocumentType(documentType);

            String seriesInput = req.getParameter(AppParameter.SERIES);
            seriesInput = compileNullReferenceIfEmpty(seriesInput);
            identityDocument.setSeries(seriesInput);

            String idDocNumberInput = req.getParameter(AppParameter
                    .ID_DOCUMENT_NUMBER);
            idDocNumberInput = compileNullReferenceIfEmpty(idDocNumberInput);
            identityDocument.setDocumentNumber(Integer.parseInt(idDocNumberInput));

            String latinHolderNameInput = req.getParameter(AppParameter
                    .LATIN_NAME);
            latinHolderNameInput = compileNullReferenceIfEmpty(latinHolderNameInput);
            identityDocument.setLatinHolderName(latinHolderNameInput);

            String latinHolderSurnameInput = req.getParameter(AppParameter
                    .LATIN_SURNAME);
            latinHolderSurnameInput = compileNullReferenceIfEmpty(latinHolderSurnameInput);
            identityDocument.setLatinHolderSurName(latinHolderSurnameInput);

            String hiddenCitizenshipInput = req.getParameter(AppParameter
                    .HIDDEN_CITIZENSHIP);
            hiddenCitizenshipInput = compileNullReferenceIfEmpty(hiddenCitizenshipInput);
            identityDocument.setCitizenShip(hiddenCitizenshipInput);

            String birthdayInput = req.getParameter(AppParameter.CHANGE_BIRTHDAY);
            LocalDate birthDay = regInfCompiler.compileBirthdayDate(birthdayInput);
            identityDocument.setBirthday(birthDay);

            String personalNumberInput = req.getParameter(AppParameter
                    .PERSONAL_NUMBER);
            personalNumberInput = compileNullReferenceIfEmpty(personalNumberInput);
            identityDocument.setPersonalNumber(personalNumberInput);

            String gender = req.getParameter(AppParameter.GENDER);
            Gender genderEnum = regInfCompiler.compileGenderEnum(gender);
            identityDocument.setGender(genderEnum);

            String placeOfOriginInput = req.getParameter(AppParameter
                    .PLACE_OF_ORIGIN);
            placeOfOriginInput = compileNullReferenceIfEmpty(placeOfOriginInput);
            identityDocument.setPlaceOfOrigin(placeOfOriginInput);

            String isDate = req.getParameter(AppParameter.DATE_OF_ISSUE);
            LocalDate dateOfIssue = regInfCompiler.compileBirthdayDate(isDate);
            identityDocument.setDateOfIssue(dateOfIssue);

            String dateOfExpiryInput = req.getParameter(AppParameter
                    .DATE_OF_EXPIRY);
            LocalDate dateOfExpiry = regInfCompiler.compileBirthdayDate(dateOfExpiryInput);
            identityDocument.setDateOfExpiry(dateOfExpiry);

            String issueAuthorityInput = req.getParameter(AppParameter
                    .ISSUEING_AUTHORITY);
            issueAuthorityInput = compileNullReferenceIfEmpty(issueAuthorityInput);
            identityDocument.setIssueAuthority(issueAuthorityInput);

            return identityDocument;
        }
        return null;
    }

    private Address compileAddress(HttpServletRequest req) {
        boolean isNewAddress = Boolean.parseBoolean(req.getParameter
                (AppParameter.IS_NEW_ADDRESS));
        if (isNewAddress) {
            Address address = new Address();

            String zipCodeInput = req.getParameter(AppParameter.ZIP_CODE);
            zipCodeInput = compileNullReferenceIfEmpty(zipCodeInput);
            address.setZipCode(zipCodeInput);

            String hiddenCountryInput = req.getParameter(AppParameter
                    .HIDDEN_COUNTRY);
            hiddenCountryInput = compileNullReferenceIfEmpty(hiddenCountryInput);
            address.setCountry(hiddenCountryInput);

            String hiddenRegionInput = req.getParameter(AppParameter
                    .HIDDEN_REGION);
            hiddenRegionInput = compileNullReferenceIfEmpty(hiddenRegionInput);
            address.setRegion(hiddenRegionInput);

            String hiddenAreaInput = req.getParameter(AppParameter.HIDDEN_AREA);
            hiddenAreaInput = compileNullReferenceIfEmpty(hiddenAreaInput);
            address.setArea(hiddenAreaInput);

            String hiddenSettlementInput = req.getParameter(AppParameter
                    .HIDDEN_SETTLEMENT);
            hiddenSettlementInput = compileNullReferenceIfEmpty(hiddenSettlementInput);
            address.setSettlement(hiddenSettlementInput);

            String hiddenRoadInput = req.getParameter(AppParameter.HIDDEN_ROAD);
            hiddenRoadInput = compileNullReferenceIfEmpty(hiddenRoadInput);
            address.setRoad(hiddenRoadInput);

            String houseInput = req.getParameter(AppParameter.HOUSE);
            houseInput = compileNullReferenceIfEmpty(houseInput);
            address.setHouse(houseInput);

            String buildingInput = req.getParameter(AppParameter.BUILDING);
            buildingInput = compileNullReferenceIfEmpty(buildingInput);
            address.setBuilding(buildingInput);

            String roomInput = req.getParameter(AppParameter.ROOM);
            roomInput = compileNullReferenceIfEmpty(roomInput);
            address.setRoom(roomInput);

            return address;
        }
        return null;
    }

    private String compileEmergencyNumber(HttpServletRequest req) {
        boolean isNewEmergencyPhone = Boolean.parseBoolean(req.getParameter
                (AppParameter.IS_NEW_EMERGENCY_PHONE));
        if (isNewEmergencyPhone) {
            String emergencyCountryCode = req.getParameter(AppParameter
                    .EMERGENCY_NUMBER_COUNTRY_CODE);
            String emergencyInnerCode = req.getParameter(AppParameter
                    .EMERGENCY_NUMBER_INNER_CODE);
            String emergencyInnerNumber = req.getParameter(AppParameter
                    .EMERGENCY_INNER_NUMBER);
            return regInfCompiler.compilePhoneNumber(emergencyCountryCode,
                    emergencyInnerCode, emergencyInnerNumber);
        }
        return null;
    }

    private BloodType compileBloodTypeEnum(String bloodTypeInput) {
        if (bloodTypeInput != null) {
            BloodType[] bloodTypes = BloodType.values();
            for (BloodType bloodType : bloodTypes) {
                if (bloodTypeInput.equals(bloodType.getValue())) {
                    return bloodType;
                }
            }
        }
        return null;
    }

    private RhBloodGroup compileRhBloodGroupEnum(String rhBloodGroupInput) {
        if (rhBloodGroupInput != null) {
            RhBloodGroup[] rhBloodGroups = RhBloodGroup.values();
            for (RhBloodGroup rhBloodGroup : rhBloodGroups) {
                if (rhBloodGroupInput.equals(rhBloodGroup.getValue())) {
                    return rhBloodGroup;
                }
            }
        }
        return null;
    }

    private TransportationStatus compileTransStatusEnum(String transportationStatusInput) {
        if (transportationStatusInput != null) {
            return TransportationStatus.valueOf(transportationStatusInput);
        }
        return null;
    }
}
