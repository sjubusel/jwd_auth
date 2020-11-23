package by.epamtc.jwd.auth.model.visit_info;

import by.epamtc.jwd.auth.model.med_info.Diagnosis;
import by.epamtc.jwd.auth.model.med_info.MedicinePrescription;
import by.epamtc.jwd.auth.model.med_info.Prescription;
import by.epamtc.jwd.auth.model.user_info.PatientInfo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class RefusalReference implements java.io.Serializable {
    private static final long serialVersionUID = 796324894350219577L;

    private int refusalReferenceId;
    private LocalDateTime referenceDatetime;
    private String refusalRecommendations;
    private PatientInfo patientInfo;
    private AdmissionDepartmentVisit visitInfo;
    private List<Diagnosis> diagnoses;
    private List<MedicinePrescription> medPrescriptions;
    private List<Prescription> prescriptions;
    private List<RefusalMedicineRecommendation> refusalMedicineRecommendations;

    public RefusalReference() {
    }

    public RefusalReference(int refusalReferenceId,
            LocalDateTime referenceDatetime, String refusalRecommendations,
            PatientInfo patientInfo, AdmissionDepartmentVisit visitInfo,
            List<Diagnosis> diagnoses, List<MedicinePrescription> medPrescriptions,
            List<Prescription> prescriptions,
            List<RefusalMedicineRecommendation> refusalMedicineRecommendations) {
        this.refusalReferenceId = refusalReferenceId;
        this.referenceDatetime = referenceDatetime;
        this.refusalRecommendations = refusalRecommendations;
        this.patientInfo = patientInfo;
        this.visitInfo = visitInfo;
        this.diagnoses = diagnoses;
        this.medPrescriptions = medPrescriptions;
        this.prescriptions = prescriptions;
        this.refusalMedicineRecommendations = refusalMedicineRecommendations;
    }

    public int getRefusalReferenceId() {
        return refusalReferenceId;
    }

    public void setRefusalReferenceId(int refusalReferenceId) {
        this.refusalReferenceId = refusalReferenceId;
    }

    public LocalDateTime getReferenceDatetime() {
        return referenceDatetime;
    }

    public void setReferenceDatetime(LocalDateTime referenceDatetime) {
        this.referenceDatetime = referenceDatetime;
    }

    public String getRefusalRecommendations() {
        return refusalRecommendations;
    }

    public void setRefusalRecommendations(String refusalRecommendations) {
        this.refusalRecommendations = refusalRecommendations;
    }

    public PatientInfo getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(PatientInfo patientInfo) {
        this.patientInfo = patientInfo;
    }

    public AdmissionDepartmentVisit getVisitInfo() {
        return visitInfo;
    }

    public void setVisitInfo(AdmissionDepartmentVisit visitInfo) {
        this.visitInfo = visitInfo;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<MedicinePrescription> getMedPrescriptions() {
        return medPrescriptions;
    }

    public void setMedPrescriptions(List<MedicinePrescription> medPrescriptions) {
        this.medPrescriptions = medPrescriptions;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<RefusalMedicineRecommendation> getRefusalMedicineRecommendations() {
        return refusalMedicineRecommendations;
    }

    public void setRefusalMedicineRecommendations(List<RefusalMedicineRecommendation> refusalMedicineRecommendations) {
        this.refusalMedicineRecommendations = refusalMedicineRecommendations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RefusalReference that = (RefusalReference) o;

        if (refusalReferenceId != that.refusalReferenceId) {
            return false;
        }
        if ((referenceDatetime != null)
            ? !referenceDatetime.equals(that.referenceDatetime)
            : (that.referenceDatetime != null)) {
            return false;
        }
        if ((refusalRecommendations != null)
            ? !refusalRecommendations.equals(that.refusalRecommendations)
            : (that.refusalRecommendations != null)) {
            return false;
        }
        if ((patientInfo != null) ? !patientInfo.equals(that.patientInfo)
                                  : (that.patientInfo != null)) {
            return false;
        }
        if ((visitInfo != null) ? !visitInfo.equals(that.visitInfo)
                                : (that.visitInfo != null)) {
            return false;
        }
        if ((diagnoses != null) ? !diagnoses.equals(that.diagnoses)
                                : (that.diagnoses != null)) {
            return false;
        }
        if ((medPrescriptions != null)
            ? !medPrescriptions.equals(that.medPrescriptions)
            : (that.medPrescriptions != null)) {
            return false;
        }
        if ((prescriptions != null) ? !prescriptions.equals(that.prescriptions)
                                    : (that.prescriptions != null)) {
            return false;
        }
        return (refusalMedicineRecommendations != null)
               ? refusalMedicineRecommendations.equals(that.refusalMedicineRecommendations)
               : (that.refusalMedicineRecommendations == null);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + refusalReferenceId;
        hash = 31 * hash + (referenceDatetime != null
                            ? referenceDatetime.hashCode()
                            : 0);
        hash = 31 * hash + (refusalRecommendations != null
                            ? refusalRecommendations.hashCode()
                            : 0);
        hash = 31 * hash + (patientInfo != null ? patientInfo.hashCode()
                                                : 0);
        hash = 31 * hash + (visitInfo != null ? visitInfo.hashCode()
                                              : 0);
        hash = 31 * hash + (diagnoses != null ? diagnoses.hashCode()
                                              : 0);
        hash = 31 * hash + (medPrescriptions != null
                            ? medPrescriptions.hashCode()
                            : 0);
        hash = 31 * hash + (prescriptions != null ? prescriptions.hashCode()
                                                  : 0);
        hash = 31 * hash + (refusalMedicineRecommendations != null
                            ? refusalMedicineRecommendations.hashCode()
                            : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "RefusalReference{" +
                "refusalReferenceId=" + refusalReferenceId +
                ", referenceDatetime=" + referenceDatetime +
                ", refusalRecommendations='" + refusalRecommendations + '\'' +
                ", patientInfo=" + patientInfo +
                ", visitInfo=" + visitInfo +
                ", diagnoses=" + Arrays.toString(diagnoses.toArray()) +
                ", medPrescriptions=" + Arrays.toString(medPrescriptions.toArray()) +
                ", prescriptions=" + Arrays.toString(prescriptions.toArray()) +
                ", refusalMedicineRecommendations="
                + Arrays.toString(refusalMedicineRecommendations.toArray()) +
                '}';
    }
}
