/**
 * Classe que defineix un campus. Es defineix pel seu nom,
 * la seva ubicació, un array d'aules estàndard,
 * un array d'aules d'informàtica i un array de laboratoris.
 */

package model;

import static interfaces.UnitatUniversitat.DADES;

import java.util.ArrayList;
import java.util.List;
import interfaces.UnitatUniversitat;

/**
 *
 */
public class Campus implements UnitatUniversitat {

    private String nomCampus;
    private String ubicacio;

    private List<Aula> aules = new ArrayList<>();

    public Campus(String nomCampus, String ubicacio) {
        this.nomCampus = nomCampus;
        this.ubicacio = ubicacio;
    }

    public String getNomCampus() {
        return this.nomCampus;
    }

    public void setNomCampus(String nomCampus) {
        this.nomCampus = nomCampus;
    }

    public String getUbicacio() {
        return this.ubicacio;
    }

    public void setUbicacio(String ubicacio) {
        this.ubicacio = ubicacio;
    }

    public List<Aula> getAules() {
        return aules;
    }

    public void setAules(List<Aula> aules) {
        this.aules = aules;
    }
    
    public static Campus addCampus() {
        String nom, ubicacio;

        System.out.println("\nNom del campus: ");
        nom = DADES.nextLine();
        System.out.println("\nUbicació del campus: ");
        ubicacio = DADES.nextLine();

        return new Campus(nom, ubicacio);
    }


    @Override
     public void updateUnitatUniversitat() {
        System.out.println("\nNom del campus: " + this.getNomCampus());
        System.out.println("\nEntra el nou nom del campus:");
        this.nomCampus = DADES.nextLine();
        System.out.println("\nUbicació del campus : " + this.getUbicacio());
        System.out.println("\nEntra la nova ubicació del cammpus:");
        this.ubicacio = DADES.nextLine();
    }


    public double costManteniment() {
        double costTotal = 0;

        for (int i = 0; i < aules.size(); i++) {
            costTotal += aules.get(i).costManteniment();
        }

        return costTotal;
    }
    
    @Override
    public void showUnitatUniversitat() {
        System.out.println("\nLes dades del campus " + this.nomCampus + " són:");
        System.out.println("\nUbicació: " + this.getUbicacio());
        System.out.println("\nCost de manteniment: " + this.costManteniment() + " EUR");
    }
    
    public void addAulaEstandard(AulaEstandard aulaEstandard) {
        if (aulaEstandard == null) {
            aulaEstandard = AulaEstandard.addAulaEstandard();
        }

        if (selectAula(1, aulaEstandard.getCodi()) == -1) {
            aules.add(aulaEstandard);
        } else {
            System.out.println("\nAula Estàndard ja existeix");
        }
    }

    public void addAulaInformatica(AulaInformatica aulaInformatica) {
        if (aulaInformatica == null) {
            aulaInformatica = AulaInformatica.addAulaInformatica();
        }

        if (selectAula(2, aulaInformatica.getCodi()) == -1) {
            aules.add(aulaInformatica);
        } else {
            System.out.println("\nAula d'Informàtica ja existeix");
        }
    }

    public void addLaboratori(Laboratori laboratori) {
        if (laboratori == null) {
            laboratori = Laboratori.addLaboratori();
        }

        if (selectAula(3, laboratori.getCodi()) == -1) {
            aules.add(laboratori);
        } else {
            System.out.println("\nLaboratori ja existeix");
        }
    }

    public int selectAula(int tipusAula, String codi) {
        if (codi == null) {
            //Demanem quin tipus d'aula es vol seleccionar i el seu codi
            switch (tipusAula) {
                case 1:
                    System.out.println("Codi de l'aula estàndard: ");
                    break;
                case 2:
                    System.out.println("Codi de l'aula d'informàtica: ");
                    break;
                case 3:
                    System.out.println("Codi del laboratori: ");
                    break;
            }

            codi = DADES.nextLine();
        }

        int posAula = -1; //Posició que ocupa l'aula seleccionat dins l'array d'aules del campus

        //Seleccionem la posició que ocupa l'aula dins l'array d'aules del campus
        for (int i = 0; i < aules.size(); i++) {
            if (aules.get(i) instanceof AulaEstandard && tipusAula == 1) {
                if (((AulaEstandard) aules.get(i)).getCodi().equals(codi)) {
                    return i;
                }
            } else if (aules.get(i) instanceof AulaInformatica && tipusAula == 2) {
                if (((AulaInformatica) aules.get(i)).getCodi().equals(codi)) {
                    return i;
                }
            } else if (aules.get(i) instanceof Laboratori && tipusAula == 3) {
                if (((Laboratori) aules.get(i)).getCodi().equals(codi)) {
                    return i;
                }
            }
        }

        return posAula;
    }
}