
package Obec;

public class Obec implements Comparable<Obec>{
    private String nazevObce;
    private int PSC;
    private int pocetMuzu;
    private int pocetZen;
    private int celkem;
    private enumKraje kraj;

    public Obec(String nazevObce, enumKraje kraj, int PSC, int pocetMuzu, int pocetZen) {
        this.kraj = kraj;
        this.nazevObce = nazevObce;
        this.PSC = PSC;
        this.pocetMuzu = pocetMuzu;
        this.pocetZen = pocetZen;
        this.celkem = pocetMuzu + pocetZen;
    }

    public Obec(String nazevObce) {
        this.nazevObce = nazevObce;
    }
    
    

    public int getPSC() {
        return PSC;
    }

    public int getPocetMuzu() {
        return pocetMuzu;
    }

    public int getPocetZen() {
        return pocetZen;
    }

    public int getCelkem() {
        return celkem;
    }

    public String getNazev() {
        return nazevObce;
    }

    public enumKraje getKraj() {
        return kraj;
    }
     @Override
    public int compareTo(Obec o) {
        return nazevObce.compareTo(o.nazevObce);
    }

    @Override
    public String toString() {
        return "Obec: " + nazevObce + "; Kraj: " + kraj  + "; PSC- " + PSC + "; pocetMuzu- " + pocetMuzu + "; pocetZen- " + pocetZen + "; celkem- " + celkem + '.';
    }

   
}
