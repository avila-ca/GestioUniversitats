package persistencia;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import exceptions.GestorUniversitatsException;
import interfaces.ProveedorPersistencia;
import model.Universitat;
import model.Campus;
import model.AulaEstandard;
import model.AulaInformatica;
import model.Laboratori;

/**
 *
 */
public class GestorXML implements ProveedorPersistencia {

    private Document doc;
    private Universitat universitat;

    public Document getDoc() {
        return doc;
    }

    public Universitat getUniversitat() {
        return universitat;
    }

    @Override
    public void desarUniversitat(String nomFitxer, Universitat universitat) throws GestorUniversitatsException {
        construeixModel(universitat);
        desarModel(nomFitxer);
    }

    @Override
    public void carregarUniversitat(String nomFitxer) throws GestorUniversitatsException {
        carregarFitxer(nomFitxer);
        llegirFitxerUniversitat();
    }

    /**
     * construeixModel
     * 
     * Paràmetres: Universitat a partir de la qual volem construir el model
     *
     * Acció: 
     * - Llegir els atributs de l'objecte universitat passat per paràmetre per construir 
     *   un model (document XML) sobre el Document doc (atribut de GestorXML).
     *
     * - L'arrel del document XML és "universitat". Aquesta arrel, l'heu d'afegir a doc. Un 
     *   cop fet això, heu de recórrer l'ArrayList campus de la universitat passat per
     *   paràmetre i per a cada campus, afegir un fill a doc. Cada fill tindrà
     *   com atributs els atributs de l'objecte (nom, ubicacio i les aules)
     *
     * - En el cas de les aules de cada campus, heu de recórrer l'ArrayList 
     *   aules del campus corresponent i per a cada aula, afegir un fill a doc,
     *   tenint en compte que hi haurà tres tipus d'elements, un per les aules estàndard, un altre per les
     *   aules d'informàtica i un altre per als laboratoris. Cada fill tindrà com atributs els atributs de 
     *   l'objecte (codi, número d'aula i cost per dia, etc.)
     *
     * - Si heu de gestionar alguna excepció relacionada amb la construcció del model,
     *   heu de llençar una excepció de tipus GestorUniversitatsException amb codi 
     *   "GestorXML.model".
     *
     * Retorn: cap
     */
    public void construeixModel(Universitat universitat) throws GestorUniversitatsException {
        //Es construeix el document model
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Element fill, net;

        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new GestorUniversitatsException("GestorXML.model");
        }

        this.doc = (Document) builder.newDocument();

        //Element arrel
        Element arrel = doc.createElement("universitat");
        arrel.setAttribute("nom", universitat.getNomUniversitat());
        arrel.setAttribute("ubicacio", universitat.getUbicacioSeu());
        doc.appendChild(arrel);

        for (int i = 0; i < universitat.getCampusList().size(); i++) {
            fill = doc.createElement("campus");

            fill.setAttribute("nom", universitat.getCampusList().get(i).getNomCampus());
            fill.setAttribute("ubicacio", universitat.getCampusList().get(i).getUbicacio());

            for (int j = 0; j < universitat.getCampusList().get(i).getAules().size(); j++) {
                if (universitat.getCampusList().get(i).getAules().get(j) instanceof AulaEstandard) {
                    net = doc.createElement("aulaEstandard");
                    
                } else if (universitat.getCampusList().get(i).getAules().get(j) instanceof AulaInformatica) {
                    net = doc.createElement("aulaInformatica");
                    net.setAttribute("areaM2", String.valueOf(((AulaInformatica) universitat.getCampusList().get(i).getAules().get(j)).getAreaEnMetresQuadrats()));
                } else { //Instància de Laboratori
                    net = doc.createElement("laboratori");
                    net.setAttribute("capacitat", String.valueOf(((Laboratori) universitat.getCampusList().get(i).getAules().get(j)).getCapacitat()));
                }

                net.setAttribute("codi", universitat.getCampusList().get(i).getAules().get(j).getCodi());
                net.setAttribute("numeroAula", String.valueOf(universitat.getCampusList().get(i).getAules().get(j).getNumeroAula()));
                net.setAttribute("costPerDia", String.valueOf(universitat.getCampusList().get(i).getAules().get(j).getCostPerDia()));

                fill.appendChild(net);
            }

            arrel.appendChild(fill);
        }
    }

    public void desarModel(String nomFitxer) throws GestorUniversitatsException {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(nomFitxer + ".xml");
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            throw new GestorUniversitatsException("GestorXML.desar");
        }
    }

    public void carregarFitxer(String nomFitxer) throws GestorUniversitatsException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File(nomFitxer + ".xml");
            doc = builder.parse(f);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            throw new GestorUniversitatsException("GestorXML.carrega");
        }
    }

    /**
     * llegirFitxerUniversitat
     * 
     * Paràmetres: cap
     *
     * Acció: 
     * - Llegir el fitxer del vostre sistema i carregar-lo sobre l'atribut doc, per
     *   assignar valors als atributs de la Universitat i la resta d'objectes que formen els
     *   campus de la Universitat.
     *    
     * - Primer heu de crear l'objecte de la classe Universitat a partir de l'arrel del document XML
     *   per després recórrer el doc i per cada fill, afegir un objecte a l'atribut campus 
     *   de la Universitat mitjançant el mètode escaient de la classe Universitat. Recordeu que com que l'arrel conté
     *   els atributs nom i ubicació de la Universitat, al fer Element arrel = doc.getDocumentElement(); 
     *   ja podeu crear l'objecte de la classe.
     *
     * Retorn: cap
     */
    private void llegirFitxerUniversitat() throws GestorUniversitatsException {
        String element;

        Element arrel = doc.getDocumentElement();

        //Es crea l'objecte de la classe Universitat
        universitat = new Universitat(arrel.getAttribute("nom"), arrel.getAttribute("ubicacio"));

        //Recorregut de nodes fill d'un element       
        NodeList llistaFills = arrel.getChildNodes();

        for (int i = 0; i < llistaFills.getLength(); i++) {
            Node fill = llistaFills.item(i);

            if (fill.getNodeType() == Node.ELEMENT_NODE) {                
                Campus nouCampus = new Campus(((Element) fill).getAttribute("nom"),((Element) fill).getAttribute("ubicacio"));

                universitat.addCampus(nouCampus);
                
                NodeList nets = fill.getChildNodes();
                
                for (int j = 0; j < nets.getLength(); j++) {
                    Node net = nets.item(j);
                    
                     if (net != null && net.getNodeType() == Node.ELEMENT_NODE) {
                        element = net.getNodeName();

                        switch (element) {
                            case "AulaEstandard":
                                nouCampus.addAulaEstandard(new AulaEstandard(
                                        ((Element) net).getAttribute("codi"), 
                                        Integer.parseInt(((Element) net).getAttribute("numeroAula")), 
                                        Double.parseDouble(((Element) net).getAttribute("costPerDia"))
                                    )
                                );
                                break;
                            case "AulaInformatica":
                                nouCampus.addAulaInformatica(new AulaInformatica(
                                        ((Element) net).getAttribute("codi"), 
                                        Integer.parseInt(((Element) net).getAttribute("numeroAula")), 
                                        Double.parseDouble(((Element) net).getAttribute("costPerDia")),
                                        Double.parseDouble(((Element) net).getAttribute("areaM2"))
                                    )
                                );

                                break;
                            default: //Laboratori
                                nouCampus.addLaboratori(new Laboratori(
                                        ((Element) net).getAttribute("codi"), 
                                        Integer.parseInt(((Element) net).getAttribute("numeroAula")), 
                                        Double.parseDouble(((Element) net).getAttribute("costPerDia")),
                                        Integer.parseInt((((Element) net).getAttribute("capacitat")))
                                    )
                                );
                                break; 
                        }
                    }
                }
            }
        }
    }
}
