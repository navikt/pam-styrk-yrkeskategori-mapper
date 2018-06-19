package no.nav.pam.yrkeskategorimapper.domain;

import java.util.Objects;

public class Occupation {


  private String styrkCode;

  private String styrkDescription;

  private String categoryLevel1;

  private String categoryLevel2;

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
  public int hashCode() {

    return Objects.hash(styrkCode, styrkDescription, categoryLevel1, categoryLevel2);
  }
}
