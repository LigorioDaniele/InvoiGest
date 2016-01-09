/**           InvoiGest
 * InvoiGest è un software che gestisce un database sql composto da
 * fatture,clienti e fornitori
 * Tramite l'interfaccia grafica è possibile ricercare, modificare, eliminare o 
 * aggiungere dati nel database.
 * 
 * @author Daniele Ligorio Antonello Sanza
 * @version 1.0
 * 
 */
package invoigest;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * La main class dell'applicazione
 */
public class InvoiGestApp extends SingleFrameApplication {

    /**
     * Il metodo startup() lancia l'applicazione,
     * crea e visualizza la GUI iniziale
     */
    @Override
    protected void startup() {

        show(new InvoiGestView(this));
    }

    /**
     *
     * L'Override del metodo configureWindow() ci consente di settare
     * il frame  non modificabile e delle dimensioni 1000x600.
     *
     * @param x rappresenta la lunghezza del frame
     * @param y rappresenta l'altezza dle frame
     * @param root nome del frame iniziale
     */
    @Override
    protected void configureWindow(java.awt.Window root) {

        int x = 1000;
        int y = 600;
        ((java.awt.Frame) root).setSize(x, y);
        ((java.awt.Frame) root).setResizable(false);
        ((java.awt.Frame) root).setLocationRelativeTo(null);
    }

    /**
     * InvoiGestApp() è un metodo statico
     *
     * @return l'istanza della classe InvoiGestApp
     */
    public static InvoiGestApp getApplication() {
        return Application.getInstance(InvoiGestApp.class);
    }

    /**
     *Questo è il metodo principale che serve a lanciare
     * l'applicaione.
     */
    public static void main(String[] args) {
        launch(InvoiGestApp.class, args);
    }
}
