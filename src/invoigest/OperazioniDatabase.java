package invoigest;

import java.util.ArrayList;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DanieleLigorio
 * ver 0.9
 *
 * OperazioniDatabase è una classe dove avviene la gestione del database
 */
public class OperazioniDatabase {

    /**
     * Il metodo statico AggiungiDato() aggiunge il dato nella jTable selezionata
     * @param dato
     * @param InvoiGestPUEntityManager
     * @param categoria
     */
    public static void AggiungiDato(Object dato, EntityManager InvoiGestPUEntityManager, String categoria) {
        try {
            //Avvio la connessione
            InvoiGestPUEntityManager.getTransaction().begin();
            if (categoria.contains("Clienti")) {
                Clienti clienti = (Clienti) dato;
                //Aggiungo l'istanza al database
                InvoiGestPUEntityManager.persist(clienti);
            } else if (categoria.contains("Fornitori")) {
                Fornitori fornitori = (Fornitori) dato;
                InvoiGestPUEntityManager.persist(fornitori);
            } else if (categoria.contains("FattureEmesse")) {
                FattureEmesse fattureemesse = (FattureEmesse) dato;
                InvoiGestPUEntityManager.persist(fattureemesse);
                Clienti clienti = (Clienti) dato;
                InvoiGestPUEntityManager.persist(clienti);
            } else if (categoria.contains("FattureRicevute")) {
                FattureRicevute fatturericevute = (FattureRicevute) dato;
                InvoiGestPUEntityManager.persist(fatturericevute);
                Fornitori fornitori = (Fornitori) dato;
                InvoiGestPUEntityManager.persist(fornitori);
            }
            InvoiGestPUEntityManager.flush();
            InvoiGestPUEntityManager.getTransaction().commit();
            //Gestisco le eccezioni
        } catch (IllegalStateException e) {
            System.out.println("Connessione non riuscita");
        } catch (RollbackException e) {
            System.out.println("Impossibile chiudere la connessione");
        } catch (EntityExistsException e) {
            System.out.println("L'entità passata già esiste");
        } catch (IllegalArgumentException e) {
            System.out.println("Quella passata non è un Entità");
        } catch (TransactionRequiredException e) {
        }
    }

    /**
     * Il metodo statico TabellaCompleta restituisce la jtable con tutti
     * i valori della tabella
     * @param categoria
     * @param jTable
     * @param InvoiGestPUEntityManager
     */
    public static void TabellaCompleta(String categoria, JTable jTable, EntityManager InvoiGestPUEntityManager) {
        try {
            DefaultTableModel model = new DefaultTableModel();

            //Compongo la namedQuery
            String namedQuery = categoria + ".findAll";
            //Avvio la connessione
            InvoiGestPUEntityManager.getTransaction().begin();
            //Passo la namedQuery all'EntityManager e setto il parametro
            Query q = InvoiGestPUEntityManager.createNamedQuery(namedQuery);
            //Sincronizzo il database
            InvoiGestPUEntityManager.flush();
            //Termino la Connessione
            InvoiGestPUEntityManager.getTransaction().commit();
            //Aggiorno il model con il risultato della Query
            model = AggiornamentoTabelle.risultatoQuery(q, categoria);

            //Setto il model nella giusta jTable e aggiorno il nome delle colonne
            jTable.setModel(model);

            AggiornamentoTabelle.aggiornaNomeColonne(jTable, categoria);

        } //catturo le eccezioni
        catch (IllegalStateException ey) {
            System.out.println("Connessione non riuscita");
        } catch (RollbackException ey) {
            System.out.println("Impossibile chiudere la connessione");
        } catch (EntityExistsException ey) {
            System.out.println("L'entità passata già esiste");
        } catch (IllegalArgumentException ey) {
            System.out.println("Quella passata non è un Entità");
        } catch (TransactionRequiredException ey) {
        } catch (PersistenceException ey) {
        }
    }

    /**
     * Il metodo statico ModificaDato serve per modificare il date nel database
     * @param dato è l'oggetto da modificare
     * @param InvoiGestPUEntityManager
     * @param categoria
     */
    public static void ModificaDato(Object dato, EntityManager InvoiGestPUEntityManager, String categoria) {
        try {
            //Avvio la connessione
            InvoiGestPUEntityManager.getTransaction().begin();
            //modifico l'istanza al database
            InvoiGestPUEntityManager.merge(dato);
            //Sincronizzo il database
            InvoiGestPUEntityManager.flush();
            //Termino la Connessione
            InvoiGestPUEntityManager.getTransaction().commit();
            //Gestisco le eccezioni
        } catch (IllegalStateException e) {
            System.out.println("Connessione non riuscita");
        } catch (RollbackException e) {
            System.out.println("Impossibile chiudere la connessione");
        } catch (EntityExistsException e) {
            System.out.println("L'entità passata già esiste");
        } catch (IllegalArgumentException e) {
            System.out.println("Quella passata non è un Entità");
        } catch (TransactionRequiredException e) {
        }
    }

    /**
     * Il metodo statico EliminaDato
     * @param jTable
     * @param InvoiGestPUEntityManager
     * @param categoria
     */
    public static void EliminaDato(JTable jTable, EntityManager InvoiGestPUEntityManager, String categoria) {
        try {
            //creo un ArrayList di tipo String
            ArrayList<String> valori = new ArrayList<String>();
            //creo un oggetto
            Object oggettoRimuovi = new Object();
            //Avvio la connessione
            InvoiGestPUEntityManager.getTransaction().begin();
            //Prendo i dati dalla jTable
            valori = AggiornamentoTabelle.datiModifica(jTable);
            //Prendo l'Id della riga selezionata e lo trasformo in int
            int idMod = Integer.parseInt(valori.get(0));
            //recupero l'oggetto da rimuovere
            if (categoria.contains("Clienti")) {
                oggettoRimuovi = InvoiGestPUEntityManager.find(Clienti.class, idMod);
            } else if (categoria.contains("Fornitori")) {
                oggettoRimuovi = InvoiGestPUEntityManager.find(Fornitori.class, idMod);
            } else if (categoria.contains("FattureEmesse")) {
                oggettoRimuovi = InvoiGestPUEntityManager.find(FattureEmesse.class, idMod);
            } else if (categoria.contains("FattureRicevute")) {
                oggettoRimuovi = InvoiGestPUEntityManager.find(FattureRicevute.class, idMod);
            }
            //sincronizzo il database
            InvoiGestPUEntityManager.flush();
            //rimuovo l'oggetto
            InvoiGestPUEntityManager.remove(oggettoRimuovi);
            //sincronizzo il databse
            InvoiGestPUEntityManager.flush();
            //termino la connessione
            InvoiGestPUEntityManager.getTransaction().commit();
            //catturo le eccezioni
        } catch (IllegalStateException ex) {
            System.out.println("Connessione non riuscita");
        } catch (RollbackException ex) {
            System.out.println("Impossibile chiudere la connessione");
        } catch (EntityExistsException ex) {
            System.out.println("L'entità passata già esiste");
        } catch (IllegalArgumentException ex) {
            System.out.println("Quella passata non è un Entità");
        } catch (TransactionRequiredException ex) {
        }
    }
}
