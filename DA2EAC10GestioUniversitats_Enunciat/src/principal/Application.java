package principal;

import java.util.Scanner;
import java.util.InputMismatchException;

import persistencia.GestorPersistencia;

import model.Universitat;
import exceptions.GestorUniversitatsException;
import model.AulaEstandard;
import model.AulaInformatica;
import model.Laboratori;

/**
 *
 */
public class Application {
    private final static Scanner DADES = new Scanner(System.in);

    private static Universitat[] universitats = new Universitat[10];
    private static int pUniversitats = 0; //Primera posició buida del vector universitats
    private static Universitat universitatActual = null;
    private static GestorPersistencia gp = new GestorPersistencia();

    public static void main(String[] args) {
        try {
            menuPrincipal();
        } catch (GestorUniversitatsException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    private static void menuPrincipal() throws GestorUniversitatsException {
        int opcio = 0;

        do {
            try {
                System.out.println("\nSelecciona una opció");
                System.out.println("\n0. Sortir");
                System.out.println("\n1. Gestió Universitats");
                System.out.println("\n2. Gestió Campus");
                System.out.println("\n3. Gestió Aules Estàndard");
                System.out.println("\n4. Gestió Aules d'informàtica");
                System.out.println("\n5. Gestió Laboratoris");
                
                opcio = Integer.parseInt(DADES.nextLine());

                switch (opcio) {
                    case 0:
                        break;
                    case 1:
                        menuUniversitats();
                        break;
                    case 2:
                        if (universitatActual != null) {
                            menuCampus();
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar la Universitat al menú Gestió Universitats");
                        }
                        break;
                    case 3:
                        if (universitatActual != null) {
                            menuAules(1);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar la Universitat al menú Gestió Universitats");
                        }
                        break;
                    case 4:
                        if (universitatActual != null) {
                            menuAules(2);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar la Universitat al menú Gestió Universitats");
                        }
                        break;
                    case 5:
                        if (universitatActual != null) {
                            menuAules(3);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar la Universitat al menú Gestió Universitats");
                        }
                        break;
                    default:
                        System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                        break;
                }
            } catch (InputMismatchException e) {
                throw new GestorUniversitatsException("1");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new GestorUniversitatsException("2");
            }
        } while (opcio != 0);
    }

    public static void menuUniversitats() throws GestorUniversitatsException, InputMismatchException, ArrayIndexOutOfBoundsException {
        int opcio;

        do {
            int indexSel;
            
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Seleccionar");
            System.out.println("\n3. Modificar");
            System.out.println("\n4. Llista Universitats");
            System.out.println("\n5. Carregar Universitat");
            System.out.println("\n6. Desar Universitat");
            
            opcio = Integer.parseInt(DADES.nextLine());
            
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    Universitat novaUniversitat = Universitat.addUniversitat();

                    indexSel = selectUniversitat(novaUniversitat);

                    if (indexSel == -1) {
                        universitats[pUniversitats] = novaUniversitat;
                        pUniversitats++;
                    } else {
                        throw new GestorUniversitatsException("3");
                    }

                    break;
                case 2:
                    indexSel = selectUniversitat(null);
                    if (indexSel >= 0) {
                        universitatActual = universitats[indexSel];
                    } else {
                        System.out.println("\nNo existeix aquesta Universitat");
                    }
                    break;
                case 3:
                    indexSel = selectUniversitat(null);
                    if (indexSel >= 0) {
                        universitats[indexSel].updateUnitatUniversitat();
                    } else {
                        System.out.println("\nNo existeix aquesta Universitat");
                    }
                    break;
                case 4:
                    for (int i = 0; i < pUniversitats; i++) {
                        universitats[i].showUnitatUniversitat();
                    }
                    break;
                case 5: //Carregar Universitat
                    System.out.println("\nNom de la universitat que es vol carregar?:");
                    String fitxer = DADES.nextLine();
                    gp.carregarUniversitat("XML", fitxer);
                    universitats[pUniversitats] = (gp.getGestor()).getUniversitat();
                    pUniversitats++;
                    break;

                case 6: //Es desa la universitat actual
                    gp.desarUniversitat("XML", universitatActual.getNomUniversitat(), universitatActual);
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuCampus() throws InputMismatchException {
        int opcio;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Llistat Campus");

            opcio = Integer.parseInt(DADES.nextLine());
            
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    universitatActual.addCampus(null);
                    break;
                case 2:
                    int indexSel = universitatActual.selectCampus(null);
                    if (indexSel >= 0) {
                        universitatActual.getCampusList().get(indexSel).updateUnitatUniversitat();
                    } else {
                        System.out.println("\nNo existeix aquest Campus");
                    }
                    break;
                case 3:
                    for (int i = 0; i < universitatActual.getCampusList().size(); i++) {
                        universitatActual.getCampusList().get(i).showUnitatUniversitat();
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuAules(int tipus) throws InputMismatchException {
        int opcio;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Llistar");

            opcio = Integer.parseInt(DADES.nextLine());
            
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipus) {
                        case 1:
                            universitatActual.addAulaEstandardCampus();
                            break;
                        case 2:
                            universitatActual.addAulaInformaticaCampus();
                            break;
                        case 3:
                            universitatActual.addLaboratoriCampus();
                            break;
                    }
                    break;
                case 2:                   
                    switch (tipus) {
                        case 1:
                            universitatActual.updateAulaEstandardCampus();
                            break;
                        case 2:
                            universitatActual.updateAulaInformaticaCampus();
                            break;
                        case 3:
                            universitatActual.updateLaboratoriCampus();
                            break;
                    }
                    break;
                case 3:
                    for (int i = 0; i < universitatActual.getCampusList().size(); i++) {
                        for (int j = 0; j < universitatActual.getCampusList().get(i).getAules().size(); j++) {
                            if (universitatActual.getCampusList().get(i).getAules().get(j) instanceof AulaEstandard && tipus == 1 ||
                                universitatActual.getCampusList().get(i).getAules().get(j) instanceof AulaInformatica && tipus == 2 ||
                                universitatActual.getCampusList().get(i).getAules().get(j) instanceof Laboratori && tipus == 3) {

                                universitatActual.getCampusList().get(i).getAules().get(j).showUnitatUniversitat();
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static Integer selectUniversitat(Universitat Universitat) {
        String nom;

        if (Universitat == null) {
            System.out.println("\nNom de la Universitat?:");
            nom = DADES.nextLine();
        } else {
            nom = Universitat.getNomUniversitat();
        }

        for (int i = 0; i < pUniversitats; i++) {
            if (universitats[i].getNomUniversitat().equals(nom)) {
                return i;
            }
        }

        return -1;
    }
}