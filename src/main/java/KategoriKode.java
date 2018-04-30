import java.util.Objects;

public class KategoriKode {


  private String styrkKode;

  private String styrkKodeTekst;

  private String kategori1;

  private String kategori2;

  public KategoriKode(String styrkKode, String styrkKodeTekst, String kategori1,
      String kategori2) {
    this.styrkKode = styrkKode;
    this.styrkKodeTekst = styrkKodeTekst;
    this.kategori1 = kategori1;
    this.kategori2 = kategori2;
  }

  public String getStyrkKode() {
    return styrkKode;
  }

  public String getStyrkKodeTekst() {
    return styrkKodeTekst;
  }

  public String getKategori1() {
    return kategori1;
  }

  public String getKategori2() {
    return kategori2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KategoriKode that = (KategoriKode) o;
    return Objects.equals(styrkKode, that.styrkKode) &&
        Objects.equals(styrkKodeTekst, that.styrkKodeTekst) &&
        Objects.equals(kategori1, that.kategori1) &&
        Objects.equals(kategori2, that.kategori2);
  }

  @Override
  public int hashCode() {

    return Objects.hash(styrkKode, styrkKodeTekst, kategori1, kategori2);
  }
}
