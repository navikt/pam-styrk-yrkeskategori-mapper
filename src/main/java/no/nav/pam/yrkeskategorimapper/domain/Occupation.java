package no.nav.pam.yrkeskategorimapper.domain;

import java.util.Objects;

/**
 * Domain object represents an occupation with styrk code, styrk description, category levels 1 and 2 (Yrksekategoritre nivå 1 og 2).
 */
public class Occupation implements Comparable<Occupation> {

    private final String styrkCode;

    private final String styrkDescription;

    private final String categoryLevel1;

    private final String categoryLevel2;

    public Occupation(String styrkCode, String styrkDescription, String categoryLevel1,
                      String categoryLevel2) {
        this.styrkCode = styrkCode;
        this.styrkDescription = styrkDescription;
        this.categoryLevel1 = categoryLevel1;
        this.categoryLevel2 = categoryLevel2;
    }

    public String getStyrkCode() {
        return styrkCode;
    }

    public String getStyrkDescription() {
        return styrkDescription;
    }

    public String getCategoryLevel1() {
        return categoryLevel1;
    }

    public String getCategoryLevel2() {
        return categoryLevel2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Occupation that = (Occupation) o;
        return Objects.equals(styrkCode, that.styrkCode) &&
                Objects.equals(styrkDescription, that.styrkDescription) &&
                Objects.equals(categoryLevel1, that.categoryLevel1) &&
                Objects.equals(categoryLevel2, that.categoryLevel2);
    }

    @Override
    public int compareTo(Occupation o) {
        if (this.getStyrkCode().compareTo(o.getStyrkCode())>0) return 1;
        if (this.getStyrkCode().compareTo(o.getStyrkCode())<0) return -1;
        return 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(styrkCode, styrkDescription, categoryLevel1, categoryLevel2);
    }
}
