package pl.umcs.clinicmanager.user;



public enum Role {
    PATIENT("PATIENT"), DOCTOR("DOCTOR"), ADMIN("ADMIN");

    public final String value;

    Role(String value) {
        this.value = value;
    }
}
