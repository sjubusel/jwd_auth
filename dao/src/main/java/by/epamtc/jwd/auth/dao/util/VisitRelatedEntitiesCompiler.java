package by.epamtc.jwd.auth.dao.util;

import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.med_info.*;
import by.epamtc.jwd.auth.model.user_info.TransportationStatus;
import by.epamtc.jwd.auth.model.visit_info.AdmissionDepartmentVisit;
import by.epamtc.jwd.auth.model.visit_info.VisitReason;
import by.epamtc.jwd.auth.model.visit_info.VisitResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class VisitRelatedEntitiesCompiler {
    private static volatile VisitRelatedEntitiesCompiler instance;

    private VisitRelatedEntitiesCompiler() {
    }

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

    public AdmissionDepartmentVisit compileShortenedVisit(ResultSet resultSet)
            throws SQLException {
        AdmissionDepartmentVisit visit = new AdmissionDepartmentVisit();
        int visitId = resultSet.getInt(1);
        visit.setVisitId(visitId);
        Timestamp timestamp = resultSet.getTimestamp(2);
        LocalDateTime visitDateTime = timestamp != null
                                      ? timestamp.toLocalDateTime()
                                      : null;
        visit.setVisitDateTime(visitDateTime);
        String lastName = resultSet.getString(3);
        String firstName = resultSet.getString(4);
        String middleName = resultSet.getString(5);
        String personInfo = compileFullName(firstName, middleName, lastName);
        visit.setPatientShortInfo(personInfo);
        String visitDescription = resultSet.getString(6);
        visit.setPatientVisitDescriptionInfo(visitDescription);
        return visit;
    }

    public AdmissionDepartmentVisit compileFullVisit(AdmissionDepartmentVisit
            visit, ResultSet resultSet) throws SQLException {
        int personId = resultSet.getInt(7);
        visit.setPatientId(personId);
        String visitReason = resultSet.getString(8);
        if (visitReason != null) {
            visit.setVisitReason(VisitReason.valueOf(visitReason));
        }
        int doctorId = resultSet.getInt(9);
        visit.setResponsibleDoctorId(doctorId);
        String doctorLastName = resultSet.getString(10);
        String doctorFirstName = resultSet.getString(11);
        String doctorMiddleName = resultSet.getString(12);
        visit.setResponsibleDoctorInfo(compileFullName(doctorFirstName,
                doctorMiddleName, doctorLastName));
        String transStatus = resultSet.getString(13);
        if (transStatus != null) {
            visit.setTransportationStatus(TransportationStatus
                    .valueOf(transStatus));
        }
        int paramedicalStaff = resultSet.getInt(14);
        visit.setResponsibleNonDoctorStaffId(paramedicalStaff);
        String paraLastName = resultSet.getString(15);
        String paraFirstName = resultSet.getString(16);
        String paraMiddleName = resultSet.getString(17);
        visit.setResponsibleNonDoctorStaffInfo(compileFullName(paraFirstName,
                paraMiddleName, paraLastName));
        String complaints = resultSet.getString(18);
        visit.setPatientComplaints(complaints);
        String result = resultSet.getString(19);
        if (result != null) {
            visit.setVisitResult(VisitResult.valueOf(result));
        }
        int hospDepartmentId = resultSet.getInt(20);
        visit.setHospitalizationDepartmentId(hospDepartmentId);
        String hospDepartmentName = resultSet.getString(21);
        visit.setHospitalizationDepartmentInfo(hospDepartmentName);
        return visit;
    }

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

    public void updateMedPrescriptionWithPatientInfo
            (MedicinePrescription prescription, ResultSet resultSet)
            throws SQLException {
        int patientId = resultSet.getInt(25);
        prescription.setPatientId(patientId);
        String patientInfo = compileFullName(resultSet.getString(26),
                resultSet.getString(27), resultSet.getString(28));
        prescription.setPatientInfo(patientInfo);
    }

    public void updateNonMedPrescriptionWithPatientInfo(
            Prescription prescription, ResultSet resultSet) throws SQLException {
        int patientId = resultSet.getInt(22);
        prescription.setPatientId(patientId);
        String patientInfo = compileFullName(resultSet.getString(23),
                resultSet.getString(24), resultSet.getString(25));
        prescription.setPatientInfo(patientInfo);
    }

    private String compileFullName(String firstName, String middleName,
            String lastName) {
        return middleName != null
               ? lastName + AppConstant.ONE_WHITESPACE + firstName
                       + AppConstant.ONE_WHITESPACE + middleName
               : lastName + AppConstant.ONE_WHITESPACE + firstName;
    }

    private MedicineMeasureUnit receiveMedicineMeasureUnit(int unitId) {
        for (MedicineMeasureUnit measureUnit : MedicineMeasureUnit.values()) {
            if (unitId == measureUnit.getMeasureUnitId()) {
                return measureUnit;
            }
        }
        return null;
    }

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
