package by.epamtc.jwd.auth.dao.util;

import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.med_info.DepartmentOrigin;
import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicineMeasureUnit;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.user_info.TransportationStatus;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.RefusalMedicineRecommendation;
import by.epamtc.jwd.auth.model.visit_info.VisitReason;
import by.epamtc.jwd.auth.model.visit_info.VisitResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * a class which is created to compile entities which are to be used mostly in
 * visit to the admitting department subsystem
 */
public final class VisitRelatedEntitiesCompiler {
    /**
     * a unique and only instance of this class
     */
    private static volatile VisitRelatedEntitiesCompiler instance;

    /**
     * a private constructor of a Singleton pattern
     */
    private VisitRelatedEntitiesCompiler() {
    }

    /**
     * a method which initialize and provide thread safe instance of a
     * current type
     *
     * @return an unique and only instance of a current class
     */
    public static VisitRelatedEntitiesCompiler getInstance() {
        VisitRelatedEntitiesCompiler localInstance = instance;
        if (localInstance == null) {
            synchronized (VisitRelatedEntitiesCompiler.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new VisitRelatedEntitiesCompiler();
                }
            }
        }
        return localInstance;
    }

    /**
     * a method which generates by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     * object using data stored in java.sql.ResultSet. This object contains shortened
     * information about the visit to the admitting department in compliance with
     * healthcare legislation of the Republic of Belarus
     *
     * @param resultSet  java.sql.ResultSet, which contains data to generate
     *                   by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     * @param startIndex an int value, which is a number of the first column
     *                   in the result set
     * @return a by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit object
     * information about the visit to the admitting department in compliance with
     * healthcare legislation of the Republic of Belarus
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     * @see by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     */
    public AdmissionDepartmentVisit compileShortenedVisit(ResultSet resultSet,
            int startIndex)
            throws SQLException {
        AdmissionDepartmentVisit visit = new AdmissionDepartmentVisit();
        int visitId = resultSet.getInt(startIndex);
        visit.setVisitId(visitId);
        Timestamp timestamp = resultSet.getTimestamp(++startIndex);
        LocalDateTime visitDateTime = timestamp != null
                                      ? timestamp.toLocalDateTime()
                                      : null;
        visit.setVisitDateTime(visitDateTime);
        String lastName = resultSet.getString(++startIndex);
        String firstName = resultSet.getString(++startIndex);
        String middleName = resultSet.getString(++startIndex);
        String personInfo = compileFullName(firstName, middleName, lastName);
        visit.setPatientShortInfo(personInfo);
        String visitDescription = resultSet.getString(++startIndex);
        visit.setPatientVisitDescriptionInfo(visitDescription);
        return visit;
    }

    /**
     * a method which generates by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     * object using data stored in java.sql.ResultSet. This object contains full
     * information about the visit to the admitting department in compliance with
     * healthcare legislation of the Republic of Belarus
     *
     * @param visit      a source AdmissionDepartmentVisit with shorten information
     *                   which is not updated to complete state (full)
     * @param resultSet  java.sql.ResultSet, which contains data to generate
     *                   by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     * @param startIndex an int value, which is a number of the first column
     *                   in the result set
     * @return a by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit object
     * information about the visit to the admitting department in compliance with
     * healthcare legislation of the Republic of Belarus
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     * @see by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit
     */
    public AdmissionDepartmentVisit compileFullVisit(AdmissionDepartmentVisit
            visit, ResultSet resultSet, int startIndex) throws SQLException {
        int personId = resultSet.getInt(startIndex);
        visit.setPatientId(personId);
        String visitReason = resultSet.getString(++startIndex);
        if (visitReason != null) {
            visit.setVisitReason(VisitReason.valueOf(visitReason));
        }
        int doctorId = resultSet.getInt(++startIndex);
        visit.setResponsibleDoctorId(doctorId);
        String doctorLastName = resultSet.getString(++startIndex);
        String doctorFirstName = resultSet.getString(++startIndex);
        String doctorMiddleName = resultSet.getString(++startIndex);
        visit.setResponsibleDoctorInfo(compileFullName(doctorFirstName,
                doctorMiddleName, doctorLastName));
        String transStatus = resultSet.getString(++startIndex);
        if (transStatus != null) {
            visit.setTransportationStatus(TransportationStatus
                    .valueOf(transStatus));
        }
        int paramedicalStaff = resultSet.getInt(++startIndex);
        visit.setResponsibleNonDoctorStaffId(paramedicalStaff);
        String paraLastName = resultSet.getString(++startIndex);
        String paraFirstName = resultSet.getString(++startIndex);
        String paraMiddleName = resultSet.getString(++startIndex);
        visit.setResponsibleNonDoctorStaffInfo(compileFullName(paraFirstName,
                paraMiddleName, paraLastName));
        String complaints = resultSet.getString(++startIndex);
        visit.setPatientComplaints(complaints);
        String result = resultSet.getString(++startIndex);
        if (result != null) {
            visit.setVisitResult(VisitResult.valueOf(result));
        }
        int hospDepartmentId = resultSet.getInt(++startIndex);
        visit.setHospitalizationDepartmentId(hospDepartmentId);
        String hospDepartmentName = resultSet.getString(++startIndex);
        visit.setHospitalizationDepartmentInfo(hospDepartmentName);
        return visit;
    }

    /**
     * a method which generates by.epamtc.jwd.auth.model.med_info.Diagnosis
     * object using data stored in java.sql.ResultSet. This object contains
     * information about an established diagnosis
     *
     * @param resultSet        java.sql.ResultSet, which contains data to generate
     *                         by.epamtc.jwd.auth.model.med_info.Diagnosis
     * @param departmentOrigin an object which describes an origin of
     *                         the department where this diagnosis was established
     * @return a by.epamtc.jwd.auth.model.med_info.Diagnosis object
     * information  about an established diagnosis
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     * @see by.epamtc.jwd.auth.model.med_info.Diagnosis
     */
    public Diagnosis compileDiagnosis(ResultSet resultSet, DepartmentOrigin
            departmentOrigin) throws SQLException {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDepartmentOrigin(departmentOrigin);

        int diagnosisId = resultSet.getInt(1);
        diagnosis.setDiagnosisId(diagnosisId);

        LocalDateTime diagnosisDateTime = null;
        Timestamp timestamp = resultSet.getTimestamp(2);
        if (timestamp != null) {
            diagnosisDateTime = timestamp.toLocalDateTime();
        }
        diagnosis.setDiagnosisDateTime(diagnosisDateTime);

        int diseaseId = resultSet.getInt(3);
        diagnosis.setDiseaseId(diseaseId);

        String diseaseInfo = resultSet.getString(4);
        diagnosis.setDiseaseInfo(diseaseInfo);

        String diagnosisDescription = resultSet.getString(5);
        diagnosis.setDiagnosisDescription(diagnosisDescription);

        int diagnoseDoctorId = resultSet.getInt(6);
        diagnosis.setDiagnoseDoctorId(diagnoseDoctorId);

        String diagnoseDoctorInfo = compileFullName(resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9));
        diagnosis.setDiagnoseDoctorInfo(diagnoseDoctorInfo);

        LocalDateTime cancellationDateTime = null;
        Timestamp cancellationTimestamp = resultSet.getTimestamp(10);
        if (cancellationTimestamp != null) {
            cancellationDateTime = cancellationTimestamp.toLocalDateTime();
        }
        diagnosis.setCancellationDateTime(cancellationDateTime);

        int cancellationDoctorId = resultSet.getInt(11);
        diagnosis.setCancellationDoctorId(cancellationDoctorId);

        String cancellationDoctorInfo = compileFullName(resultSet.getString(12),
                resultSet.getString(13), resultSet.getString(14));
        diagnosis.setCancellationDoctorInfo(cancellationDoctorInfo);

        String cancellationDiagnosisDescription = resultSet.getString(15);
        diagnosis.setCancellationDiagnosisDescription
                (cancellationDiagnosisDescription);

        return diagnosis;
    }

    /**
     * a method which generates by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     * that possesses information about a prescription of a medicine
     *
     * @param rSet java.sql.ResultSet which is a source for an aforementioned
     *             by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     * @return by.epamtc.jwd.auth.model.med_info.MedicinePrescription, which
     * possesses information about a prescription of a medicine
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     * @see by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     */
    public MedicinePrescription compileMedicinePrescription(ResultSet rSet)
            throws SQLException {
        MedicinePrescription prescription = new MedicinePrescription();

        prescription.setDepartmentOrigin(DepartmentOrigin.ADMISSION_DEPARTMENT);

        int prescriptionId = rSet.getInt(1);
        prescription.setPrescriptionId(prescriptionId);

        int originDocumentId = rSet.getInt(2);
        prescription.setOriginDocumentId(originDocumentId);

        int medicineId = rSet.getInt(3);
        prescription.setMedicineId(medicineId);

        String medicineInfo = compileShortenedMedicineInfo(rSet.getString(4),
                rSet.getString(5), rSet.getDouble(6),
                rSet.getDouble(7));
        prescription.setMedicineInfo(medicineInfo);

        LocalDateTime prescriptionDateTime = null;
        Timestamp prescriptionTimestamp = rSet.getTimestamp(8);
        if (prescriptionTimestamp != null) {
            prescriptionDateTime = prescriptionTimestamp.toLocalDateTime();
        }
        prescription.setPrescriptionDateTime(prescriptionDateTime);

        int prescribingStaffId = rSet.getInt(9);
        prescription.setPrescribingStaffId(prescribingStaffId);

        String prescribingStaffInfo = compileFullName(rSet.getString(10),
                rSet.getString(11), rSet.getString(12));
        prescription.setPrescribingStaffInfo(prescribingStaffInfo);

        double dosageQuantity = rSet.getDouble(13);
        prescription.setDosageQuantity(dosageQuantity);

        MedicineMeasureUnit dosageMeasureUnit = receiveMedicineMeasureUnit(rSet
                .getInt(14));
        prescription.setDosageMeasureUnit(dosageMeasureUnit);

        LocalDateTime targetApplicationDateTime = null;
        Timestamp targetTimestamp = rSet.getTimestamp(15);
        if (targetTimestamp != null) {
            targetApplicationDateTime = targetTimestamp.toLocalDateTime();
        }
        prescription.setTargetApplicationDateTime(targetApplicationDateTime);

        int executorStaffId = rSet.getInt(16);
        prescription.setExecutorStaffId(executorStaffId);

        String executorStaffInfo = compileFullName(rSet.getString(17),
                rSet.getString(18), rSet.getString(19));
        prescription.setExecutorStaffInfo(executorStaffInfo);

        LocalDateTime executionDateTime = null;
        Timestamp executionTimestamp = rSet.getTimestamp(20);
        if (executionTimestamp != null) {
            executionDateTime = executionTimestamp.toLocalDateTime();
        }
        prescription.setExecutionDateTime(executionDateTime);

        String executionDescription = rSet.getString(21);
        prescription.setExecutionDescription(executionDescription);

        boolean doesPatientAgree = rSet.getBoolean(22);
        prescription.setDoesPatientAgree(doesPatientAgree);

        String patientDisagreementDescription = rSet.getString(23);
        prescription.setPatientDisagreementDescription(patientDisagreementDescription);

        LocalDateTime patientDisagreementDateTime = null;
        Timestamp disagreementTimestamp = rSet.getTimestamp(24);
        if (disagreementTimestamp != null) {
            patientDisagreementDateTime = disagreementTimestamp.toLocalDateTime();
        }
        prescription.setPatientDisagreementDateTime(patientDisagreementDateTime);

        boolean isPrescriptionComplete = false;
        if ((executionDateTime != null) || (patientDisagreementDateTime != null)) {
            isPrescriptionComplete = true;
        }
        prescription.setPrescriptionComplete(isPrescriptionComplete);

        return prescription;
    }

    /**
     * a method which generates by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     * that possesses information about a prescription of non-medicine origin
     *
     * @param rSet java.sql.ResultSet which is a source for an aforementioned
     *             by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     * @return by.epamtc.jwd.auth.model.med_info.MedicinePrescription, which
     * possesses information about a prescription of of non-medicine origin
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     * @see by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     */
    public Prescription compileNonMedicinePrescription(ResultSet rSet)
            throws SQLException {
        Prescription prescription = new Prescription();
        prescription.setDepartmentOrigin(DepartmentOrigin.ADMISSION_DEPARTMENT);

        int prescriptionId = rSet.getInt(1);
        prescription.setPrescriptionId(prescriptionId);

        int originDocumentId = rSet.getInt(2);
        prescription.setOriginDocumentId(originDocumentId);

        LocalDateTime prescriptionDateTime = null;
        Timestamp prescriptionTimestamp = rSet.getTimestamp(3);
        if (prescriptionTimestamp != null) {
            prescriptionDateTime = prescriptionTimestamp.toLocalDateTime();
        }
        prescription.setPrescriptionDateTime(prescriptionDateTime);

        int prescribingStaffId = rSet.getInt(4);
        prescription.setPrescribingStaffId(prescribingStaffId);

        String prescribingStaffInfo = compileFullName(rSet.getString(5),
                rSet.getString(6), rSet.getString(7));
        prescription.setPrescribingStaffInfo(prescribingStaffInfo);

        String prescriptionDescription = rSet.getString(8);
        prescription.setPrescriptionDescription(prescriptionDescription);

        int responsibleStaffId = rSet.getInt(9);
        prescription.setResponsibleStaffId(responsibleStaffId);

        String responsibleStaffInfo = compileFullName(rSet.getString(10),
                rSet.getString(11), rSet.getString(12));
        prescription.setResponsibleStaffInfo(responsibleStaffInfo);

        int executorStaffId = rSet.getInt(13);
        prescription.setExecutorStaffId(executorStaffId);

        String executorStaffInfo = compileFullName(rSet.getString(14),
                rSet.getString(15), rSet.getString(16));
        prescription.setExecutorStaffInfo(executorStaffInfo);

        LocalDateTime executionDateTime = null;
        Timestamp executionTimestamp = rSet.getTimestamp(17);
        if (executionTimestamp != null) {
            executionDateTime = executionTimestamp.toLocalDateTime();
        }
        prescription.setExecutionDateTime(executionDateTime);

        String executionDescription = rSet.getString(18);
        prescription.setExecutionDescription(executionDescription);

        boolean doesPatientAgree = rSet.getBoolean(19);
        prescription.setDoesPatientAgree(doesPatientAgree);

        String patientDisagreementDescription = rSet.getString(20);
        prescription.setPatientDisagreementDescription
                (patientDisagreementDescription);

        LocalDateTime patientDisagreementDateTime = null;
        Timestamp disagreementTimestamp = rSet.getTimestamp(21);
        if (disagreementTimestamp != null) {
            patientDisagreementDateTime = disagreementTimestamp.toLocalDateTime();
        }
        prescription.setPatientDisagreementDateTime(patientDisagreementDateTime);

        boolean isPrescriptionComplete = false;
        if ((executionDateTime != null) || (patientDisagreementDateTime != null)) {
            isPrescriptionComplete = true;
        }
        prescription.setPrescriptionComplete(isPrescriptionComplete);

        return prescription;
    }

    /**
     * a method which updates already existing by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     * with information about a patient
     *
     * @param prescription already existing by.epamtc.jwd.auth.model.med_info.MedicinePrescription
     *                     object which is to be updated
     * @param resultSet    java.sql.ResultSet which is a source of updating data
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     */
    public void updateMedPrescriptionWithPatientInfo
    (MedicinePrescription prescription, ResultSet resultSet)
            throws SQLException {
        int patientId = resultSet.getInt(25);
        prescription.setPatientId(patientId);
        String patientInfo = compileFullName(resultSet.getString(26),
                resultSet.getString(27), resultSet.getString(28));
        prescription.setPatientInfo(patientInfo);
    }

    /**
     * a method which updates already existing by.epamtc.jwd.auth.model.med_info.Prescription
     * with information about a patient
     *
     * @param prescription already existing by.epamtc.jwd.auth.model.med_info.Prescription
     *                     object which is to be updated
     * @param resultSet    java.sql.ResultSet which is a source of updating data
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     */
    public void updateNonMedPrescriptionWithPatientInfo(
            Prescription prescription, ResultSet resultSet) throws SQLException {
        int patientId = resultSet.getInt(22);
        prescription.setPatientId(patientId);
        String patientInfo = compileFullName(resultSet.getString(23),
                resultSet.getString(24), resultSet.getString(25));
        prescription.setPatientInfo(patientInfo);
    }

    /**
     * a method that generates by.epamtc.jwd.auth.model.visit_info.RefusalMedicineRecommendation
     * which contains information about a recommendation of a medicine which is
     * established when a patient gets a refusal to his hospitalization
     *
     * @param resultSet java.sql.ResultSet, which is a source for data that make
     *                  up an aforementioned by.epamtc.jwd.auth.model.visit_info.RefusalMedicineRecommendation
     * @return by.epamtc.jwd.auth.model.visit_info.RefusalMedicineRecommendation
     * that contains information about a recommendation of a medicine which is
     * established when a patient gets a refusal to his hospitalization
     * @throws SQLException throws if some issues with database interaction
     *                      occur
     */
    public RefusalMedicineRecommendation compileRefusalMedicineRecommendation(
            ResultSet resultSet) throws SQLException {
        int recommendationId = resultSet.getInt(1);
        int visitId = resultSet.getInt(2);
        LocalDateTime recommendationDateTime = null;
        Timestamp recomTimestamp = resultSet.getTimestamp(3);
        if (recomTimestamp != null) {
            recommendationDateTime = recomTimestamp.toLocalDateTime();
        }
        int medicineId = resultSet.getInt(4);
        String medicineInfo = compileShortenedMedicineInfo(resultSet
                        .getString(5), resultSet.getString(6),
                resultSet.getDouble(7), resultSet.getDouble(8));
        String intakeInstructions = resultSet.getString(9);

        return new RefusalMedicineRecommendation(recommendationId, visitId,
                recommendationDateTime, medicineId, medicineInfo,
                intakeInstructions);
    }

    /**
     * a private method which creates String that contains identification data
     * about a person. Implementation of this method must be widen in compliance
     * with future needs
     *
     * @param firstName  a String, which represents a person's first name
     * @param middleName a String, which represents a person's middle name
     * @param lastName   a String, which represents a person's last name
     * @return a String that contains identification data about a person
     */
    private String compileFullName(String firstName, String middleName,
            String lastName) {
        return middleName != null
               ? lastName + AppConstant.ONE_WHITESPACE + firstName
                       + AppConstant.ONE_WHITESPACE + middleName
               : lastName + AppConstant.ONE_WHITESPACE + firstName;
    }

    /**
     * a method which finds an equivalent of by.epamtc.jwd.auth.model.med_info.MedicineMeasureUnit
     * enum by its id
     *
     * @param unitId id which by.epamtc.jwd.auth.model.med_info.MedicineMeasureUnit
     *               is to be found by
     * @return by.epamtc.jwd.auth.model.med_info.MedicineMeasureUnit, which
     * corresponds <code>unitId</code>
     */
    private MedicineMeasureUnit receiveMedicineMeasureUnit(int unitId) {
        for (MedicineMeasureUnit measureUnit : MedicineMeasureUnit.values()) {
            if (unitId == measureUnit.getMeasureUnitId()) {
                return measureUnit;
            }
        }
        return null;
    }

    /**
     * a private method which generates a String description of a medicine. It's
     * a temporary method, if it is needed, it will be refactored into a
     * separate class
     *
     * @param medicineName       a naming of a medicine
     * @param dosageFormTypeName a naming of a form of a dosage of a medicine
     * @param dosageMgValue      a dosage of a main component of a medicine in
     *                           milligram(s)
     * @param dosageMlValue      a dosage of a medicine in millilitre(s) if this
     *                           parameter is applicable for this medicine
     * @return a String which contains a shortened information about a medicine
     */
    private String compileShortenedMedicineInfo(String medicineName,
            String dosageFormTypeName, double dosageMgValue,
            double dosageMlValue) {
        if (dosageMlValue > 0) {
            return medicineName + AppConstant.ONE_WHITESPACE
                    + dosageFormTypeName + AppConstant.ONE_WHITESPACE
                    + dosageMgValue + AppConstant.ONE_WHITESPACE
                    + AppConstant.MG_SUFFIX + AppConstant.ONE_WHITESPACE
                    + AppConstant.SLASH + AppConstant.ONE_WHITESPACE + dosageMlValue
                    + AppConstant.ONE_WHITESPACE + AppConstant.ML_SUFFIX;

        }
        return medicineName + AppConstant.ONE_WHITESPACE
                + dosageFormTypeName + AppConstant.ONE_WHITESPACE
                + dosageMgValue + AppConstant.ONE_WHITESPACE
                + AppConstant.MG_SUFFIX;
    }
}
