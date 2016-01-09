package invoigest;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author DanieleLigorio
 * @version 0.9
 *
 * La classe AggiornamentoTabelle contiene dei metodi statici che estraggono
 * dati, effettuano aggiornamenti e controlli nelle Jtable
 */
public class AggiornamentoTabelle {

    /**
     * Il metodo datiModifica() prende i dati della riga selezionata nella
     * jTable che viene passata al metodo e li inserisce nelle varie jTextField
     * della jInternaframe1, per poterli opportunamente modificare.
     *
     * @param jTable
     * @return tramite il cast ho un Arraylist di stringhe con i dati della riga
     * selezionata
     */
    public static ArrayList datiModifica(javax.swing.JTable jTable) {
        // creo un ArrayList
        ArrayList<Object> dati = new ArrayList<Object>();
        // Salvo il numero della riga selezionata
        int indiceRiga = jTable.getSelectedRow();
        // Riempio l'ArrayList dati di Object
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            Object datoCella = jTable.getValueAt(indiceRiga, i);
            dati.add(datoCella);
        }
        // creo un altro ArrayList
        ArrayList<String> valori = new ArrayList<String>();
        // riempio l'ArrayList di Stringhe
        for (int i = 0; i < dati.size(); i++) {
            // Eseguo il cast da Object a String
            String valore = (dati.get(i).toString());
            valori.add(valore);
        }
        return valori;
    }

    /**
     * Il metodo risultatoQuery() ci consente di ottenere il risultato della
     * query nell'istanza della classe DefaultTableModel
     *
     * @param q è la query
     * @param categoria
     * @return l'oggetto model contenente il risultato della query
     */
    public static DefaultTableModel risultatoQuery(Query q, String categoria) {
        // creo un'istanza della classe DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(0, 9);
        // recupero la lista con il risultato della query
        List l = q.getResultList();
        // controllo su quale tabella è stata eseguita la query
        if (categoria.contains("Clienti")) {
            // Scorro la List l
            for (Object o : l) {
                //Eseguo un cast da Object a Clienti
                Clienti p = (Clienti) o;
                //Creo ed inizializzo un vettore data
                String[] dati = {String.valueOf(p.getIDCliente()), p.getRagionesociale(), p.getCodicefiscale(), p.getPartitaiva(), p.getCitta(), p.getIndirizzo(), p.getTelefono(), p.getEmail(), p.getCap()};
                //Aggiungo data a model
                model.addRow(dati);
            }

        } else if (categoria.contains("Fornitori")) {
            // Scorro la List l
            for (Object o : l) {
                //Eseguo un cast da Object a Fornitori
                Fornitori p = (Fornitori) o;
                //Creo ed inizializzo un vettore data
                String[] dati = {String.valueOf(p.getIDFornitore()), p.getRagionesociale(), p.getCodicefiscale(), p.getPartitaiva(), p.getCitta(), p.getIndirizzo(), p.getTelefono(), p.getEmail(), p.getCap()};
                //Aggiungo data a model
                model.addRow(dati);
            }
        }


        //Ritorno mode

        return model;


    }

    /**
     * Il metodo risultatoQueryFatture() ci consente di ottenere il risultato della
     * query nell'istanza della classe DefaultTableModel , usato per le fatture
     *
     * @param q è la query
     * @param categoria
     * @return l'oggetto model contenente il risultato della query
     */
    public static DefaultTableModel risultatoQueryFatture(Query q, String categoria) {
        // creo un'istanza della classe DefaultTableModel
        DefaultTableModel modelfatt = new DefaultTableModel(0, 6);
        // recupero la lista con il risultato della query
        List l = q.getResultList();
        if (categoria.contains("FattureEmesse")) {
            // Scorro la List l
            for (Object o : l) {
                //Eseguo un cast da Object a FattureEmesse
                FattureEmesse p = (FattureEmesse) o;
                //Creo ed inizializzo un vettore data
                String[] dati = {String.valueOf(p.getIDFattureEmesse()), p.getNumerofattura(), p.getDataf(), p.getIva(), p.getTotale(), p.getRagionesociale()};
                //Aggiungo data a model
                modelfatt.addRow(dati);
            }
        } else if (categoria.contains("FattureRicevute")) {
            // Scorro la List l

            for (Object o : l) {
                //Eseguo un cast da Object a FattureRicevute
                FattureRicevute p = (FattureRicevute) o;
                //Creo ed inizializzo un vettore data
                String[] dati = {String.valueOf(p.getIDFattureRicevute()), p.getNumerofattura(), p.getDataf(), p.getIva(), p.getTotale(), p.getRagionesociale()};
                //Aggiungo data a model
                modelfatt.addRow(dati);
            }
        }
        return modelfatt;


    }

    /**
     * Il metodo aggiornaNomeColonne() setta i nomi delle colonne alla jTable
     * passata come parametro e setta il nome della colonna degli ID in base
     * alla categoria su cui si sta operando
     *
     *
     * @param jTable è la jTable su cui verranno settati i nomi delle colonne
     * @param categoria è la categoria su cui si sta lavorando
     */
    public static void aggiornaNomeColonne(javax.swing.JTable jTable, String categoria) {
        //Setto il nome delle colonne alla jTable
        if (categoria.contains("Fornitori")) {
            jTable.getColumnModel().getColumn(0).setHeaderValue("ID" + categoria);
            jTable.getColumnModel().getColumn(1).setHeaderValue("Ragione Sociale");
            jTable.getColumnModel().getColumn(2).setHeaderValue("Codice fiscale");
            jTable.getColumnModel().getColumn(3).setHeaderValue("Partita iva");
            jTable.getColumnModel().getColumn(4).setHeaderValue("Città");
            jTable.getColumnModel().getColumn(5).setHeaderValue("Indirizzo");
            jTable.getColumnModel().getColumn(6).setHeaderValue("Telefono");
            jTable.getColumnModel().getColumn(7).setHeaderValue("Email");
            jTable.getColumnModel().getColumn(8).setHeaderValue("Cap");
        } else if (categoria.contains("Clienti")) {
            jTable.getColumnModel().getColumn(0).setHeaderValue("ID" + categoria);
            jTable.getColumnModel().getColumn(1).setHeaderValue("Ragione Sociale");
            jTable.getColumnModel().getColumn(2).setHeaderValue("Codice fiscale");
            jTable.getColumnModel().getColumn(3).setHeaderValue("Partita iva");
            jTable.getColumnModel().getColumn(4).setHeaderValue("Città");
            jTable.getColumnModel().getColumn(5).setHeaderValue("Indirizzo");
            jTable.getColumnModel().getColumn(6).setHeaderValue("Telefono");
            jTable.getColumnModel().getColumn(7).setHeaderValue("Email");
            jTable.getColumnModel().getColumn(8).setHeaderValue("Cap");
        } else if (categoria.contains("FattureEmesse")) {
            jTable.getColumnModel().getColumn(0).setHeaderValue("ID" + categoria);
            jTable.getColumnModel().getColumn(1).setHeaderValue("Numerofattura");
            jTable.getColumnModel().getColumn(2).setHeaderValue("Data");
            jTable.getColumnModel().getColumn(3).setHeaderValue("Iva");
            jTable.getColumnModel().getColumn(4).setHeaderValue("Totale");
            jTable.getColumnModel().getColumn(5).setHeaderValue("Ragione Sociale");
        } else if (categoria.contains("FattureRicevute")) {
            jTable.getColumnModel().getColumn(0).setHeaderValue("ID" + categoria);
            jTable.getColumnModel().getColumn(1).setHeaderValue("Numerofattura");
            jTable.getColumnModel().getColumn(2).setHeaderValue("Data");
            jTable.getColumnModel().getColumn(3).setHeaderValue("Iva");
            jTable.getColumnModel().getColumn(4).setHeaderValue("Totale");
            jTable.getColumnModel().getColumn(5).setHeaderValue("Ragione Sociale");
        } 
    }
public static void aggiornaNomeColonneDettaglio(javax.swing.JTable jTable, String categoria) {
    
            jTable.getColumnModel().getColumn(0).setHeaderValue("Codice");
            jTable.getColumnModel().getColumn(1).setHeaderValue("quantità");
            jTable.getColumnModel().getColumn(2).setHeaderValue("prezzo unitario");
            jTable.getColumnModel().getColumn(3).setHeaderValue("iva");
            jTable.getColumnModel().getColumn(4).setHeaderValue("totale");

       
}
public static void aggiornaNomeColonneDettaglioQuery(javax.swing.JTable jTable, String categoria) {
    
            jTable.getColumnModel().getColumn(0).setHeaderValue("Codice");
            jTable.getColumnModel().getColumn(1).setHeaderValue("quantità");
            jTable.getColumnModel().getColumn(2).setHeaderValue("descrizione");
            jTable.getColumnModel().getColumn(3).setHeaderValue("Totale");
         

       
}
    /**
     * Il metodo controlloDati() esamina le variabili String passate come
     * parametri.incompleto!
     *
     * @param titolo è una stringa passata dall'utente
     * @param autore è una stringa passata dall'utente
     * @param anno è una stringa passata dall'utente
     * @param casa è una stringa passata dall'utente
     * @param genere è una stringa passata dall'utente
     * @param controllo è la variabile boolean che ci viene restituita dal
     * metodo
     * @return la variabile boolean controllo.Quest'ultima sarà true se tutte le
     * variabili String saranno vuote,altrimenti sarà false.
     */
    public static boolean controlloDati(String ragionesociale, String codicefiscale, String partitaiva, String citta, String indirizzo, String telefono, String email, String cap) {
        //Inizializzo la variabile boolean controllo
        boolean controllo = false;
        //Eseguo il controllo sulle String
        if (ragionesociale.isEmpty()) {
            if (codicefiscale.isEmpty()) {
                if (partitaiva.isEmpty()) {
                    if (citta.isEmpty()) {
                        if (indirizzo.isEmpty()) {
                            if (telefono.isEmpty()) {
                                if (email.isEmpty()) {
                                    if (cap.isEmpty()) {

                                        controllo = true;

                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            controllo = false;
        }
        //Ritorno controllo
        return controllo;
    }

    /**
     * Il metodo statico numeroID() ci consente di ottenere il max numero
     * contenuto nella colonna ID della tabella data dalla variabile categoria
     *
     * @param categoria è la stringa che ci permette di scegliere la colonna
     * degli ID
     * @param categoria rappresenta la tabella del database su cui stiamo
     * lavorando
     * @param InvoiGestPUEntityManager è l'entity mananger
     * @param id è il numero cercato
     * @return la variabile int id contenente il numero più alto
     */
    public static int numeroID(String categoriaId, String categoria, javax.persistence.EntityManager InvoiGestPUEntityManager) {
        //Creo la variabile id
        int id;
        //Compongo la namedQuery
        String namedQuery = "SELECT MAX(ID_" + categoriaId + ")FROM " + categoria + ";";
        //Passo la namedQuery all'EntityManager
        Query q = InvoiGestPUEntityManager.createNativeQuery(namedQuery);
        //Recupero il risultato della Query
        String risultato = String.valueOf(q.getSingleResult());
        //Elimino le parentesi [ ]
        String numero = risultato.substring(1, risultato.length() - 1);
        //Eseguo un controllo su substring
        if (numero.contains("null")) {
            id = 0;
        } else {
            //Recupero con un cast il numero
            id = Integer.parseInt(numero);
        }
        //Ritorno id
        return id;
    }

    /**
     * Il metodo statico controlloTabella() ci consente di ottenere la tabella
     * in base alla cateogria selezionata
     *
     * @param categoria rappresenta la tabella del database su cui stiamo
     * lavorando
     * @param jTableclienti un'istanza della classe jTable
     * @param jTablefornitori un'istanza della classe jTable
     * @return table4 restituisce la tabella in cui vengono effettuate le
     * opreazioni in base alla categoria
     */
    public static JTable controlloTabella(String categoria, JTable jTableclienti, JTable jTablefornitori, JTable jTablefattureEmesse, JTable jTablefatturericevute, JTable serviziotable) {
        //Creo una jTable
        JTable table = new JTable();
        //Eseguo un controllo sulla categoria
        if (categoria.contains("Fornitori")) {
            //Aggiorno table
            table = jTablefornitori;
        } else if (categoria.contains("Clienti")) {
            table = jTableclienti;
        } else if (categoria.contains("FattureEmesse")) {
            table = jTablefattureEmesse;
        } else if (categoria.contains("FattureRicevute")) {
            table = jTablefatturericevute;
        } else if (categoria.contains("Prodotti")) {
            table = serviziotable;
        }
        //Ritorno table
        return table;
    }
}
