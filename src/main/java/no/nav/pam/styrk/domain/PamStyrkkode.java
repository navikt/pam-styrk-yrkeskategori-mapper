package no.nav.pam.styrk.domain;

/**
 * Mapping mellom STYRK-kode og PAM Nivå 1 og 2
 */
public class PamStyrkkode {
    private String styrkkode;
    private String pamNiva1;
    private String pamNiva2;

    public PamStyrkkode(String styrkkode, String pamNiva1, String pamNiva2) {
        this.styrkkode = styrkkode;
        this.pamNiva1 = pamNiva1;
        this.pamNiva2 = pamNiva2;
    }

    public String styrkkode() {
        return styrkkode;
    }

    public String pamNiva1() {
        return pamNiva1;
    }

    public String pamNiva2() {
        return pamNiva2;
    }


    @Override
    public String toString() {
        return styrkkode + ": " + pamNiva1 + "." + pamNiva2;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        PamStyrkkode that = (PamStyrkkode) object;
        return java.util.Objects.equals(styrkkode, that.styrkkode) &&
                java.util.Objects.equals(pamNiva1, that.pamNiva1) &&
                java.util.Objects.equals(pamNiva2, that.pamNiva2);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), styrkkode, pamNiva1, pamNiva2);
    }
}
