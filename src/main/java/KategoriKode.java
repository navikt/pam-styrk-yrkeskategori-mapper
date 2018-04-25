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

}
