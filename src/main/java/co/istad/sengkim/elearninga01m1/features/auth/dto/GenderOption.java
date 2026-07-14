package co.istad.sengkim.elearninga01m1.features.auth.dto;

public enum GenderOption {
    FEMALE("Female"), MALE("Male"), OTHER("Other");
    private String gender;
    GenderOption(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
}
