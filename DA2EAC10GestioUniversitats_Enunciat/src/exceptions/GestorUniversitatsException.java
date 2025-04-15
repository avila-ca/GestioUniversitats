package exceptions;

/**
 *
 */
public class GestorUniversitatsException extends Exception {

    private String codiCausa = "desconegut";
    private String missatge;

    public GestorUniversitatsException(String codiCausa) {
        this.codiCausa = codiCausa;
        
        switch (codiCausa) {
            case "1":
                missatge = "L'opció introduïda no és numèrica";
                break;
            case "2":
                missatge = "Ja no hi caben més Universitats";
                break;
            case "3":
                missatge = "La universitat ja existeix";
                break;
            case "GestorXML.model":
                missatge = "No s'ha pogut crear el model XML per desar la Universitat";
                break;
            case "GestorXML.desar":
                missatge = "No s'ha pogut desar la Universitat a causa d'error d'entrada/sortida";
                break;
            case "GestorXML.carrega":
                missatge = "No s'ha pogut carregar la Universitat a causa d'error d'entrada/sortida";
                break;
            default:
                missatge = "Error desconegut";
                break;
        }
    }

    public String getMessage() {
        return "Amb el codi de causa:  " + codiCausa + ", s'ha generat el següent missatge: " + missatge;
    }
}
