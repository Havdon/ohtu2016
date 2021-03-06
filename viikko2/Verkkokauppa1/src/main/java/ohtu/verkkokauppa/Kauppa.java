package ohtu.verkkokauppa;

public class Kauppa {

    private IVarasto varasto;
    private IPankki pankki;
    private IOstoskori ostoskori;
    private IViitegeneraattori viitegeneraattori;
    private String kaupanTili;
    
    public Kauppa(IViitegeneraattori viitegeneraattori, IOstoskori ostoskori, IPankki pankki, IVarasto varasto) {
        this.viitegeneraattori  = viitegeneraattori;
        this.ostoskori = ostoskori;
        this.pankki = pankki;
        this.varasto = varasto;
        kaupanTili = "33333-44455";
    }
    
    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        ITuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            ITuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
