package it.marcotamagno.searcher4amazon;

/**
 * Created by marco on 11/12/2016.
 */

public class StringGenerator {

    public StringGenerator() {

    }

    public String string(int sconto,String nomeCategoria,String name){
        String string;
        String categoria;

        if(sconto < 0){
            sconto = 0;
        }
        if(sconto > 99){
            sconto = 99;
        }
        categoria = CodeSelector(nomeCategoria);
        string = new String("https://www.amazon.it/s/ref=as_li_ss_tl?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&url=rh=n:"+categoria+",k:"+name+"&field-pct-off="+sconto+"-&linkCode=ll2&tag=chronache-21&linkId=d5091c4e67823c70575cd349c4317d60");
        return string;
    }

    public String CodeSelector(String categoria){
        switch(categoria){
            case "Elettronica":
                return "412609031";
            case "Informatica":
                return "425916031";
            case "Auto e Moto":
                return "1571280031";
            case "Casa e Cucina":
                return "524015031";
            case "Valigeria":
                return "4091096031";
            case "Industria e Scienza":
                return "5866068031";
            case "Fai da te":
                return "2454160031";
            case "Sport e tempo libero":
                return "524012031";
            case "Scarpe e borse":
                return "524006031";
            case "Giardino e giardinaggio":
                return "635016031";
            case "Giochi e giocattoli":
                return "523997031";
            case "Abbigliamento Donna":
                return "2892859031";
            case "Abbigliamento Uomo":
                return "2892862031";
            case "Strumenti musicali e DJ":
                return "3628629031";
            case "Illuminazione":
                return "1571292031";
            case "Orologi":
                return "524009031";
            case "App e Giochi":
                return "1661660031";
            case "Libri":
                return "411663031";
            case "Bellezza":
                return "6198082031";
            case "Libri in altre lingue":
                return "433842031";
            case "Prima Infanzia":
                return "1571286031";
            case "Kindle Store":
                return "133140011";
            case "Gioielli":
                return "2454163031";
            case "Videogiochi":
                return"412603031";
            case "Musica Digitale":
                return "1748203031";
            case "Salute e cura della persona":
                return "1571289031";
            case "Software":
                return "412612031";
            case "Alimentari e cura della casa":
                return "6198092031";
            case "CD e vinili":
                return "412600031";
            case "Film e TV":
                return "412606031";
            case "Handmade":
                return "9699425031";
            case "Cancelleria e Prodotti per ufficio":
                return "3606310031";
            case "Altro":
                return "10407973031";
            default:
                return "10407973031";
        }
    }
}