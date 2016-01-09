package invoigest;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.Action;
import org.jdesktop.application.*;

/**
 * La classe InvoiGestView gestisce e crea l'interfaccia grafica
 *
 * @author Daniele Ligorio
 * @version 0.9
 *
 */
public class InvoiGestView extends FrameView {

    private String categoria = "Scegli..";
    private String ricerca;
    private String campo = "Ragionesociale";
    private String ragionesociale;
    private String codicefiscale;
    private String indirizzo;
    private String citta;
    private String partitaiva;
    private String telefono;
    private String email;
    private String cap;
    //variabili usate per FattureEmesse e ricevute quando vengono inserite
    private String numerofattura;
    private String data;
    private String iva;
    private String totale;
    double Imponibile;
    private String descrizione;
    private String codice;
//variabili prodotti , usati nel metodo che stampa
    String DescrizMod;
    String CodicMod;
    String prezzoo;
    String quantitaa;
    //variabili per calcolare iva
    double contenitoretotali = 0;
    double contenitoreiva = 0;
    private int idMod;
    private Query q;

    /**
     * Costruttore della classe InvoiGestView
     * @param app è un'istanza della classe SingleFrameApplication
     */
    public InvoiGestView(SingleFrameApplication app) {
        super(app);
        // chiamo il metodo initComponents() definito in questa classe
        initComponents();
        // inizializzo la barra di stato
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);
        // Gestisce le azioni della barra di stato
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    /**
     * Il metodo showAboutBox() inizializza il Frame con le informazioni
     * sull'applicazione.
     */
    @Action
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        mainPanel = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jInternalFrameConfermaElimina = new javax.swing.JInternalFrame();
        jDesktopPane6 = new javax.swing.JDesktopPane();
        jLabel6 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jInternalFrameErroreNoDati = new javax.swing.JInternalFrame();
        jDesktopPane7 = new javax.swing.JDesktopPane();
        jButton14 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jInternalFrameErroreCategoria = new javax.swing.JInternalFrame();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        jButton10 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        norigaframe = new javax.swing.JInternalFrame();
        jDesktopPane8 = new javax.swing.JDesktopPane();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jInternalFrameErrorericerca = new javax.swing.JInternalFrame();
        jDesktopPane5 = new javax.swing.JDesktopPane();
        jButton11 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPaneclienti = new javax.swing.JScrollPane();
        jTableclienti = new javax.swing.JTable();
        jScrollPanefornitori = new javax.swing.JScrollPane();
        jTablefornitori = new javax.swing.JTable();
        jScrollPanefattureemesse = new javax.swing.JScrollPane();
        jTablefattureemesse = new javax.swing.JTable();
        jScrollPanefatturericevute = new javax.swing.JScrollPane();
        jTablefatturericevute = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox();
        aggiungibotton = new javax.swing.JButton();
        modificaprincipalebotton = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        eliminabotton = new javax.swing.JButton();
        vairicercabotton = new javax.swing.JButton();
        annullaricercabotton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(invoigest.InvoiGestApp.class).getContext().getResourceMap(InvoiGestView.class);
        InvoiGestPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory(resourceMap.getString("InvoiGestPUEntityManager.persistenceUnit")).createEntityManager(); // NOI18N
        FornitoriQuery = java.beans.Beans.isDesignTime() ? null : InvoiGestPUEntityManager.createQuery(resourceMap.getString("FornitoriQuery.query")); // NOI18N
        FornitoriList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : FornitoriQuery.getResultList();
        ClientiQuery = java.beans.Beans.isDesignTime() ? null : InvoiGestPUEntityManager.createQuery(resourceMap.getString("ClientiQuery.query")); // NOI18N
        ClientiList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : ClientiQuery.getResultList();
        fatturaframe = new javax.swing.JFrame();
        salvabotton = new javax.swing.JButton();
        stampabutton = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ragionesocialetextfield = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        codicefiscaletextfield = new javax.swing.JTextField();
        partitaivatextfield = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        indirizzotextfield = new javax.swing.JTextField();
        captextfield = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cittatextfield = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        telefonotextfield = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        emailtextfield = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        ivatextfield = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        totaletextfield = new javax.swing.JTextField();
        modificabotton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        codicetextfield = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        prezzotextfield = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        quantitatextfield = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        descrizionetext = new javax.swing.JTextArea();
        aggiungivocibottom = new javax.swing.JButton();
        ivaprova = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        numerofatturatextfield = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        datatextfield = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        serviziotable = new javax.swing.JTable();
        aggiornatabellabutton = new javax.swing.JButton();
        FattureEmesseQuery = java.beans.Beans.isDesignTime() ? null : InvoiGestPUEntityManager.createQuery(resourceMap.getString("FattureEmesseQuery.query")); // NOI18N
        FattureEmesseList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : FattureEmesseQuery.getResultList();
        FattureRicevuteQuery = java.beans.Beans.isDesignTime() ? null : InvoiGestPUEntityManager.createQuery(resourceMap.getString("FattureRicevuteQuery.query")); // NOI18N
        FattureRicevuteList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : FattureRicevuteQuery.getResultList();
        ProdottiQuery = java.beans.Beans.isDesignTime() ? null : InvoiGestPUEntityManager.createQuery(resourceMap.getString("ProdottiQuery.query")); // NOI18N
        ProdottiList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : ProdottiQuery.getResultList();

        mainPanel.setMaximumSize(new java.awt.Dimension(1001, 601));
        mainPanel.setMinimumSize(new java.awt.Dimension(999, 599));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

        jDesktopPane1.setMaximumSize(new java.awt.Dimension(1001, 599));
        jDesktopPane1.setMinimumSize(new java.awt.Dimension(999, 599));
        jDesktopPane1.setName("jDesktopPane1"); // NOI18N
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1000, 600));

        jInternalFrameConfermaElimina.setBackground(resourceMap.getColor("jInternalFrameConfermaElimina.background")); // NOI18N
        jInternalFrameConfermaElimina.setClosable(true);
        jInternalFrameConfermaElimina.setForeground(resourceMap.getColor("jInternalFrameConfermaElimina.foreground")); // NOI18N
        jInternalFrameConfermaElimina.setResizable(true);
        jInternalFrameConfermaElimina.setFont(resourceMap.getFont("jInternalFrameConfermaElimina.font")); // NOI18N
        jInternalFrameConfermaElimina.setName("jInternalFrameConfermaElimina"); // NOI18N
        jInternalFrameConfermaElimina.setPreferredSize(new java.awt.Dimension(310, 200));
        jInternalFrameConfermaElimina.setVisible(true);

        jDesktopPane6.setName("jDesktopPane6"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setBounds(60, 20, 150, 30);
        jDesktopPane6.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton12.setBackground(resourceMap.getColor("jButton12.background")); // NOI18N
        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jButton12.setBounds(60, 60, 60, 25);
        jDesktopPane6.add(jButton12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton13.setBackground(resourceMap.getColor("jButton13.background")); // NOI18N
        jButton13.setText(resourceMap.getString("jButton13.text")); // NOI18N
        jButton13.setName("jButton13"); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jButton13.setBounds(160, 60, 70, 25);
        jDesktopPane6.add(jButton13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jInternalFrameConfermaEliminaLayout = new javax.swing.GroupLayout(jInternalFrameConfermaElimina.getContentPane());
        jInternalFrameConfermaElimina.getContentPane().setLayout(jInternalFrameConfermaEliminaLayout);
        jInternalFrameConfermaEliminaLayout.setHorizontalGroup(
            jInternalFrameConfermaEliminaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameConfermaEliminaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );
        jInternalFrameConfermaEliminaLayout.setVerticalGroup(
            jInternalFrameConfermaEliminaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameConfermaEliminaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jInternalFrameConfermaElimina.setBounds(80, 120, 360, 220);
        jDesktopPane1.add(jInternalFrameConfermaElimina, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jInternalFrameConfermaElimina.setVisible(false);

        jInternalFrameErroreNoDati.setClosable(true);
        jInternalFrameErroreNoDati.setName("jInternalFrameErroreNoDati"); // NOI18N
        jInternalFrameErroreNoDati.setVisible(true);

        jDesktopPane7.setName("jDesktopPane7"); // NOI18N

        jButton14.setText(resourceMap.getString("jButton14.text")); // NOI18N
        jButton14.setName("jButton14"); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jButton14.setBounds(110, 110, 80, -1);
        jDesktopPane7.add(jButton14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setBounds(90, 30, 170, 50);
        jDesktopPane7.add(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jInternalFrameErroreNoDatiLayout = new javax.swing.GroupLayout(jInternalFrameErroreNoDati.getContentPane());
        jInternalFrameErroreNoDati.getContentPane().setLayout(jInternalFrameErroreNoDatiLayout);
        jInternalFrameErroreNoDatiLayout.setHorizontalGroup(
            jInternalFrameErroreNoDatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameErroreNoDatiLayout.createSequentialGroup()
                .addComponent(jDesktopPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jInternalFrameErroreNoDatiLayout.setVerticalGroup(
            jInternalFrameErroreNoDatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameErroreNoDatiLayout.createSequentialGroup()
                .addComponent(jDesktopPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jInternalFrameErroreNoDati.setBounds(80, 370, 310, 200);
        jDesktopPane1.add(jInternalFrameErroreNoDati, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jInternalFrameErroreNoDati.setVisible(false);

        jInternalFrameErroreCategoria.setBackground(resourceMap.getColor("jInternalFrameErroreCategoria.background")); // NOI18N
        jInternalFrameErroreCategoria.setClosable(true);
        jInternalFrameErroreCategoria.setName("jInternalFrameErroreCategoria"); // NOI18N
        jInternalFrameErroreCategoria.setPreferredSize(new java.awt.Dimension(310, 200));
        jInternalFrameErroreCategoria.setVisible(true);

        jDesktopPane4.setName("jDesktopPane4"); // NOI18N

        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jButton10.setBounds(100, 100, 80, -1);
        jDesktopPane4.add(jButton10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setIcon(resourceMap.getIcon("jLabel4.icon")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setBounds(10, 10, 260, 140);
        jDesktopPane4.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setBounds(30, 20, 210, 40);
        jDesktopPane4.add(jLabel29, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jInternalFrameErroreCategoriaLayout = new javax.swing.GroupLayout(jInternalFrameErroreCategoria.getContentPane());
        jInternalFrameErroreCategoria.getContentPane().setLayout(jInternalFrameErroreCategoriaLayout);
        jInternalFrameErroreCategoriaLayout.setHorizontalGroup(
            jInternalFrameErroreCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameErroreCategoriaLayout.createSequentialGroup()
                .addComponent(jDesktopPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addGap(63, 63, 63))
        );
        jInternalFrameErroreCategoriaLayout.setVerticalGroup(
            jInternalFrameErroreCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameErroreCategoriaLayout.createSequentialGroup()
                .addComponent(jDesktopPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        jInternalFrameErroreCategoria.setBounds(450, 190, 310, 190);
        jDesktopPane1.add(jInternalFrameErroreCategoria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jInternalFrameErroreCategoria.setVisible(false);

        norigaframe.setBackground(resourceMap.getColor("norigaframe.background")); // NOI18N
        norigaframe.setClosable(true);
        norigaframe.setResizable(true);
        norigaframe.setFont(resourceMap.getFont("norigaframe.font")); // NOI18N
        norigaframe.setName("norigaframe"); // NOI18N
        norigaframe.setVisible(true);

        jDesktopPane8.setBackground(resourceMap.getColor("jDesktopPane8.background")); // NOI18N
        jDesktopPane8.setFocusable(false);
        jDesktopPane8.setName("jDesktopPane8"); // NOI18N

        jLabel9.setBackground(resourceMap.getColor("jLabel9.background")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setOpaque(true);
        jLabel9.setBounds(10, 10, 200, 30);
        jDesktopPane8.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(60, 60, 90, -1);
        jDesktopPane8.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout norigaframeLayout = new javax.swing.GroupLayout(norigaframe.getContentPane());
        norigaframe.getContentPane().setLayout(norigaframeLayout);
        norigaframeLayout.setHorizontalGroup(
            norigaframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(norigaframeLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jDesktopPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        norigaframeLayout.setVerticalGroup(
            norigaframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(norigaframeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        norigaframe.setBounds(440, 380, 320, 170);
        jDesktopPane1.add(norigaframe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        norigaframe.setVisible(false);

        jInternalFrameErrorericerca.setClosable(true);
        jInternalFrameErrorericerca.setName("jInternalFrameErrorericerca"); // NOI18N
        jInternalFrameErrorericerca.setPreferredSize(new java.awt.Dimension(316, 200));
        jInternalFrameErrorericerca.setVisible(true);

        jDesktopPane5.setName("jDesktopPane5"); // NOI18N

        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jButton11.setBounds(114, 100, 90, -1);
        jDesktopPane5.add(jButton11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setIcon(resourceMap.getIcon("jLabel5.icon")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setOpaque(true);
        jLabel5.setBounds(0, 0, -1, 160);
        jDesktopPane5.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setBounds(40, 20, 260, 50);
        jDesktopPane5.add(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jInternalFrameErrorericercaLayout = new javax.swing.GroupLayout(jInternalFrameErrorericerca.getContentPane());
        jInternalFrameErrorericerca.getContentPane().setLayout(jInternalFrameErrorericercaLayout);
        jInternalFrameErrorericercaLayout.setHorizontalGroup(
            jInternalFrameErrorericercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameErrorericercaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameErrorericercaLayout.setVerticalGroup(
            jInternalFrameErrorericercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameErrorericercaLayout.createSequentialGroup()
                .addComponent(jDesktopPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        jInternalFrameErrorericerca.setBounds(450, 0, 316, 200);
        jDesktopPane1.add(jInternalFrameErrorericerca, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jInternalFrameErrorericerca.setVisible(false);

        jScrollPaneclienti.setName("jScrollPaneclienti"); // NOI18N

        jTableclienti.setName("jTableclienti"); // NOI18N
        jTableclienti.getTableHeader().setReorderingAllowed(false);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ClientiList, jTableclienti, "");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${IDCliente}"));
        columnBinding.setColumnName("IDCliente");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${ragionesociale}"));
        columnBinding.setColumnName("Ragionesociale");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codicefiscale}"));
        columnBinding.setColumnName("Codicefiscale");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${partitaiva}"));
        columnBinding.setColumnName("Partitaiva");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${citta}"));
        columnBinding.setColumnName("Citta");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${indirizzo}"));
        columnBinding.setColumnName("Indirizzo");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${telefono}"));
        columnBinding.setColumnName("Telefono");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${email}"));
        columnBinding.setColumnName("Email");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cap}"));
        columnBinding.setColumnName("Cap");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPaneclienti.setViewportView(jTableclienti);
        jTableclienti.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTableclienti.columnModel.title0")); // NOI18N
        jTableclienti.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTableclienti.columnModel.title1")); // NOI18N
        jTableclienti.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTableclienti.columnModel.title2")); // NOI18N
        jTableclienti.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTableclienti.columnModel.title3")); // NOI18N
        jTableclienti.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTableclienti.columnModel.title4")); // NOI18N
        jTableclienti.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTableclienti.columnModel.title5")); // NOI18N
        jTableclienti.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTableclienti.columnModel.title6")); // NOI18N
        jTableclienti.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTableclienti.columnModel.title7")); // NOI18N
        jTableclienti.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTableclienti.columnModel.title8")); // NOI18N
        jTableclienti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPaneclienti.setBounds(20, 250, 960, 330);
        jDesktopPane1.add(jScrollPaneclienti, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jScrollPaneclienti.setVisible(false);

        jScrollPanefornitori.setName("jScrollPanefornitori"); // NOI18N

        jTablefornitori.setName("jTablefornitori");
        jTablefornitori.getTableHeader().setReorderingAllowed(false);

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, FornitoriList, jTablefornitori);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${IDFornitore}"));
        columnBinding.setColumnName("IDFornitore");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${ragionesociale}"));
        columnBinding.setColumnName("Ragionesociale");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codicefiscale}"));
        columnBinding.setColumnName("Codicefiscale");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${partitaiva}"));
        columnBinding.setColumnName("Partitaiva");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${citta}"));
        columnBinding.setColumnName("Citta");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${indirizzo}"));
        columnBinding.setColumnName("Indirizzo");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${telefono}"));
        columnBinding.setColumnName("Telefono");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${email}"));
        columnBinding.setColumnName("Email");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cap}"));
        columnBinding.setColumnName("Cap");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPanefornitori.setViewportView(jTablefornitori);
        jTablefornitori.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTablefornitori.columnModel.title0")); // NOI18N
        jTablefornitori.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTablefornitori.columnModel.title1")); // NOI18N
        jTablefornitori.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTablefornitori.columnModel.title2")); // NOI18N
        jTablefornitori.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTablefattureR.columnModel.title6")); // NOI18N
        jTablefornitori.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTablefattureR.columnModel.title7")); // NOI18N
        jTablefornitori.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTablefattureR.columnModel.title8")); // NOI18N
        jTablefornitori.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTablefattureR.columnModel.title9")); // NOI18N
        jTablefornitori.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTablefornitori.columnModel.title7")); // NOI18N
        jTablefornitori.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTablefornitori.columnModel.title8")); // NOI18N
        jTablefornitori.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPanefornitori.setBounds(20, 250, 960, 330);
        jDesktopPane1.add(jScrollPanefornitori, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jScrollPanefornitori.setVisible(false);

        jScrollPanefattureemesse.setName("jScrollPanefattureemesse"); // NOI18N

        jTablefattureemesse.setName("jTablefattureemesse"); // NOI18N

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, FattureEmesseList, jTablefattureemesse);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${IDFattureEmesse}"));
        columnBinding.setColumnName("IDFatture Emesse");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${numerofattura}"));
        columnBinding.setColumnName("Numerofattura");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataf}"));
        columnBinding.setColumnName("Dataf");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${iva}"));
        columnBinding.setColumnName("Iva");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totale}"));
        columnBinding.setColumnName("Totale");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${ragionesociale}"));
        columnBinding.setColumnName("Ragionesociale");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPanefattureemesse.setViewportView(jTablefattureemesse);
        jTablefattureemesse.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTablefattureemesse.columnModel.title0")); // NOI18N
        jTablefattureemesse.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTablefattureemesse.columnModel.title1")); // NOI18N
        jTablefattureemesse.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTablefattureemesse.columnModel.title2")); // NOI18N
        jTablefattureemesse.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTablefattureemesse.columnModel.title3")); // NOI18N
        jTablefattureemesse.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTablefattureemesse.columnModel.title4")); // NOI18N
        jTablefattureemesse.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTablefattureemesse.columnModel.title5")); // NOI18N
        jTablefattureemesse.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPanefattureemesse.setBounds(20, 250, 960, 320);
        jDesktopPane1.add(jScrollPanefattureemesse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jScrollPanefattureemesse.setVisible(false);

        jScrollPanefatturericevute.setName("jScrollPanefatturericevute"); // NOI18N

        jTablefatturericevute.setName("jTablefatturericevute"); // NOI18N

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, FattureRicevuteList, jTablefatturericevute);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${IDFattureRicevute}"));
        columnBinding.setColumnName("IDFatture Ricevute");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${numerofattura}"));
        columnBinding.setColumnName("Numerofattura");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataf}"));
        columnBinding.setColumnName("Dataf");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${iva}"));
        columnBinding.setColumnName("Iva");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totale}"));
        columnBinding.setColumnName("Totale");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${ragionesociale}"));
        columnBinding.setColumnName("Ragionesociale");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPanefatturericevute.setViewportView(jTablefatturericevute);
        jTablefatturericevute.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTablefatturericevute.columnModel.title0")); // NOI18N
        jTablefatturericevute.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTablefatturericevute.columnModel.title1")); // NOI18N
        jTablefatturericevute.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTablefatturericevute.columnModel.title3")); // NOI18N
        jTablefatturericevute.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTablefatturericevute.columnModel.title4")); // NOI18N
        jTablefatturericevute.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTablefatturericevute.columnModel.title5")); // NOI18N

        jScrollPanefatturericevute.setBounds(20, 250, 960, 330);
        jDesktopPane1.add(jScrollPanefatturericevute, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jScrollPanefatturericevute.setVisible(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scegli..", "Fornitori", "Clienti", "FattureEmesse", "FattureRicevute" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.setBounds(740, 70, 200, 30);
        jDesktopPane1.add(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        aggiungibotton.setText(resourceMap.getString("aggiungibotton.text")); // NOI18N
        aggiungibotton.setName("aggiungibotton"); // NOI18N
        aggiungibotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aggiungibottonActionPerformed(evt);
            }
        });
        aggiungibotton.setBounds(620, 130, 100, 30);
        jDesktopPane1.add(aggiungibotton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        modificaprincipalebotton.setText(resourceMap.getString("modificaprincipalebotton.text")); // NOI18N
        modificaprincipalebotton.setName("modificaprincipalebotton"); // NOI18N
        modificaprincipalebotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificaprincipalebottonActionPerformed(evt);
            }
        });
        modificaprincipalebotton.setBounds(730, 130, 100, 30);
        jDesktopPane1.add(modificaprincipalebotton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField6.setText(resourceMap.getString("jTextField6.text")); // NOI18N
        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.setBounds(300, 190, 270, 30);
        jDesktopPane1.add(jTextField6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "scegli", "ragionesociale", "codicefiscale", "partitaiva", "citta", "indirizzo", "telefono", "email", "cap", "numerofattura", "iva", "totale" }));
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.setBounds(590, 190, 120, 30);
        jDesktopPane1.add(jComboBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        eliminabotton.setText(resourceMap.getString("eliminabotton.text")); // NOI18N
        eliminabotton.setName("eliminabotton"); // NOI18N
        eliminabotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminabottonActionPerformed(evt);
            }
        });
        eliminabotton.setBounds(840, 130, 100, 30);
        jDesktopPane1.add(eliminabotton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        vairicercabotton.setText(resourceMap.getString("vairicercabotton.text")); // NOI18N
        vairicercabotton.setName("vairicercabotton"); // NOI18N
        vairicercabotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vairicercabottonActionPerformed(evt);
            }
        });
        vairicercabotton.setBounds(730, 190, 70, 30);
        jDesktopPane1.add(vairicercabotton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        annullaricercabotton.setText(resourceMap.getString("annullaricercabotton.text")); // NOI18N
        annullaricercabotton.setName("annullaricercabotton"); // NOI18N
        annullaricercabotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annullaricercabottonActionPerformed(evt);
            }
        });
        annullaricercabotton.setBounds(810, 190, 130, 30);
        jDesktopPane1.add(annullaricercabotton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1001, 601));
        jLabel1.setMinimumSize(new java.awt.Dimension(999, 599));
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setBounds(0, 0, 1000, 600);
        jDesktopPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setIcon(resourceMap.getIcon("jLabel3.icon")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(330, 260, 0, 160);
        jDesktopPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setIcon(resourceMap.getIcon("jLabel7.icon")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setBounds(30, 290, 0, 170);
        jDesktopPane1.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(invoigest.InvoiGestApp.class).getContext().getActionMap(InvoiGestView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 815, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        InvoiGestPUEntityManager.setFlushMode(FlushModeType.AUTO);

        fatturaframe.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        fatturaframe.setMinimumSize(new java.awt.Dimension(1050, 700));
        fatturaframe.setName("fatturaframe"); // NOI18N
        fatturaframe.setResizable(false);
        fatturaframe.getContentPane().setLayout(null);

        salvabotton.setText(resourceMap.getString("salvabotton.text")); // NOI18N
        salvabotton.setName("salvabotton"); // NOI18N
        salvabotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvabottonActionPerformed(evt);
            }
        });
        fatturaframe.getContentPane().add(salvabotton);
        salvabotton.setBounds(325, 613, 72, 25);

        stampabutton.setText(resourceMap.getString("stampabutton.text")); // NOI18N
        stampabutton.setName("stampabutton"); // NOI18N
        stampabutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stampabuttonActionPerformed(evt);
            }
        });
        fatturaframe.getContentPane().add(stampabutton);
        stampabutton.setBounds(108, 613, 88, 25);

        jButton16.setText(resourceMap.getString("jButton16.text")); // NOI18N
        jButton16.setName("jButton16"); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        fatturaframe.getContentPane().add(jButton16);
        jButton16.setBounds(12, 613, 78, 25);

        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel2.setName("jPanel2"); // NOI18N

        ragionesocialetextfield.setName("ragionesocialetextfield"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        codicefiscaletextfield.setName("codicefiscaletextfield"); // NOI18N

        partitaivatextfield.setName("partitaivatextfield"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        indirizzotextfield.setName("indirizzotextfield"); // NOI18N

        captextfield.setName("captextfield"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        cittatextfield.setName("cittatextfield"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        telefonotextfield.setName("telefonotextfield"); // NOI18N

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        emailtextfield.setText(resourceMap.getString("emailtextfield.text")); // NOI18N
        emailtextfield.setName("emailtextfield"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(captextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cittatextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefonotextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel15)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ragionesocialetextfield, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(codicefiscaletextfield, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE))
                                    .addComponent(partitaivatextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(indirizzotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addComponent(emailtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addGap(127, 127, 127)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ragionesocialetextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codicefiscaletextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(partitaivatextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(indirizzotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(captextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cittatextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        fatturaframe.getContentPane().add(jPanel2);
        jPanel2.setBounds(12, 77, 418, 240);

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        fatturaframe.getContentPane().add(jLabel16);
        jLabel16.setBounds(12, 553, 22, 15);

        ivatextfield.setEditable(false);
        ivatextfield.setName("ivatextfield"); // NOI18N
        fatturaframe.getContentPane().add(ivatextfield);
        ivatextfield.setBounds(73, 551, 86, 19);

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        fatturaframe.getContentPane().add(jLabel18);
        jLabel18.setBounds(228, 553, 45, 15);

        totaletextfield.setEditable(false);
        totaletextfield.setName("totaletextfield"); // NOI18N
        fatturaframe.getContentPane().add(totaletextfield);
        totaletextfield.setBounds(328, 551, 135, 19);

        modificabotton.setText(resourceMap.getString("modificabotton.text")); // NOI18N
        modificabotton.setName("modificabotton"); // NOI18N
        modificabotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificabottonActionPerformed(evt);
            }
        });
        fatturaframe.getContentPane().add(modificabotton);
        modificabotton.setBounds(214, 613, 93, 25);

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(600, 187));

        jPanel7.setMaximumSize(new java.awt.Dimension(600, 121));
        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(600, 159));

        codicetextfield.setName("codicetextfield"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        prezzotextfield.setName("prezzotextfield"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        quantitatextfield.setName("quantitatextfield"); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        descrizionetext.setColumns(20);
        descrizionetext.setFont(resourceMap.getFont("descrizionetext.font")); // NOI18N
        descrizionetext.setRows(3);
        descrizionetext.setName("descrizionetext"); // NOI18N
        jScrollPane3.setViewportView(descrizionetext);

        aggiungivocibottom.setText(resourceMap.getString("aggiungivocibottom.text")); // NOI18N
        aggiungivocibottom.setName("aggiungivocibottom"); // NOI18N
        aggiungivocibottom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aggiungivocibottomActionPerformed(evt);
            }
        });

        ivaprova.setText(resourceMap.getString("ivaprova.text")); // NOI18N
        ivaprova.setName("ivaprova"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(codicetextfield, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(quantitatextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prezzotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(ivaprova, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24))))
                    .addComponent(aggiungivocibottom, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                .addGap(110, 110, 110))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel23)
                    .addComponent(jLabel20)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(quantitatextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(prezzotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ivaprova, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24))
                            .addComponent(codicetextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(aggiungivocibottom))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        fatturaframe.getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(12, 335, 1010, 170);

        jPanel6.setName("jPanel6"); // NOI18N

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        numerofatturatextfield.setName("numerofatturatextfield"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        datatextfield.setText(resourceMap.getString("datatextfield.text")); // NOI18N
        datatextfield.setName("datatextfield"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numerofatturatextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(256, 256, 256)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(datatextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(numerofatturatextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(datatextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(122, 122, 122))
        );

        fatturaframe.getContentPane().add(jPanel6);
        jPanel6.setBounds(12, 12, 943, 50);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        serviziotable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codice", "quantità", "Prezzo unitario", "iva", "totale"
            }
        ));
        serviziotable.setMaximumSize(new java.awt.Dimension(300, 64));
        serviziotable.setName("serviziotable"); // NOI18N
        jScrollPane4.setViewportView(serviziotable);
        serviziotable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("serviziotable.columnModel.title0")); // NOI18N
        serviziotable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("serviziotable.columnModel.title1")); // NOI18N
        serviziotable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("serviziotable.columnModel.title2")); // NOI18N
        serviziotable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("serviziotable.columnModel.title3")); // NOI18N
        serviziotable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("serviziotable.columnModel.title4")); // NOI18N

        fatturaframe.getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(471, 129, 551, 188);

        aggiornatabellabutton.setText(resourceMap.getString("aggiornatabellabutton.text")); // NOI18N
        aggiornatabellabutton.setName("aggiornatabellabutton"); // NOI18N
        aggiornatabellabutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aggiornatabellabuttonActionPerformed(evt);
            }
        });
        fatturaframe.getContentPane().add(aggiornatabellabutton);
        aggiornatabellabutton.setBounds(663, 77, 261, 25);

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Il metodo jComboBox1ActionPerformed  permette all'utente  in base alla scelta
     * della categoria di visualizzare la tabella opportuna
     *
     * @param evt rappresenta il click sul pulsante
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        //Dopo aver recuperato l'oggetto su cui è stata eseguita l'azione
        //Esegue un cast da Object a JComboBox

        JComboBox cb1 = (JComboBox) evt.getSource();
        //Recupero il nome della categoria su cui l'utente vuole lavorare
        categoria = (String) cb1.getSelectedItem();
        if (categoria.contains("Fornitori")) {
            jScrollPanefornitori.setVisible(true);
            jScrollPaneclienti.setVisible(false);
            jScrollPanefattureemesse.setVisible(false);
            jScrollPanefatturericevute.setVisible(false);
            AggiornamentoTabelle.aggiornaNomeColonne(jTablefornitori, categoria);
        } else if (categoria.contains("Clienti")) {
            jScrollPaneclienti.setVisible(true);
            jScrollPanefornitori.setVisible(false);
            jScrollPanefattureemesse.setVisible(false);
            jScrollPanefatturericevute.setVisible(false);
            AggiornamentoTabelle.aggiornaNomeColonne(jTableclienti, categoria);
        } else if (categoria.contains("FattureEmesse")) {
            jScrollPanefattureemesse.setVisible(true);
            jScrollPaneclienti.setVisible(false);
            jScrollPanefornitori.setVisible(false);
            jScrollPanefatturericevute.setVisible(false);
            AggiornamentoTabelle.aggiornaNomeColonne(jTablefattureemesse, categoria);
        } else if (categoria.contains("FattureRicevute")) {
            jScrollPanefatturericevute.setVisible(true);
            jScrollPanefattureemesse.setVisible(false);
            jScrollPaneclienti.setVisible(false);
            jScrollPanefornitori.setVisible(false);
            AggiornamentoTabelle.aggiornaNomeColonne(jTablefatturericevute, categoria);
        } else if (categoria.contains("Scegli..")) {
            jScrollPaneclienti.setVisible(false);
            jScrollPanefornitori.setVisible(false);
            jScrollPanefattureemesse.setVisible(false);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed
    /**
     * Il metodo aggiungibottonActionPerformed ci permette di aprire
     * la finestra tramite la quale è possibile inserire dati nel database
     *
     * @param evt rappresenta il click sul pulsante
     */
    private void aggiungibottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aggiungibottonActionPerformed
        //Controllo che sia stata scelta una categoria e in base a questo
        //Apro o la finestra di errore o la finestra per l'aggiunta dei dati
        modificabotton.setVisible(false);
        aggiornatabellabutton.setVisible(false);
        if (categoria.contains("Scegli..")) {
            //Setto visibile il jInternalFrame2
            jInternalFrameErroreCategoria.setVisible(true);
            jDesktopPane4.setVisible(true);
            jLabel4.setVisible(true);
            jButton10.setVisible(true);
        } else {
            //Setto visibile il fatturaFrame
            fatturaframe.setVisible(true);
        }
    }//GEN-LAST:event_aggiungibottonActionPerformed
    /*
     * Il metodo modificaprincipalebottonPerformed ci consente visualizzare i dettagli
     * @param evt rappresenta il click sul pulsante
     */

    @SuppressWarnings("empty-statement")
    private void modificaprincipalebottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificaprincipalebottonActionPerformed
        //Controllo la categoria selezionata
        if (categoria.contains("Scegli..")) {
            //Setto visibile jInternalFrame2
            jInternalFrameErroreCategoria.setVisible(true);
        } else {
            //Prendo in considerazione la tabella su cui devo eseguire l' operazioni
            JTable tabella = AggiornamentoTabelle.controlloTabella(categoria, jTableclienti, jTablefornitori, jTablefattureemesse, jTablefatturericevute, serviziotable);
            //Prendo il numero della riga selezionata
            int riga = tabella.getSelectedRow();
            //Controllo che sia stata selezionata una riga
            if (riga == -1) {
                //Setto visibile norigaframe
                norigaframe.setVisible(true);
            } else {
                fatturaframe.setVisible(true);
                 modificabotton.setVisible(true);
                //creo un ArrayList di String
                ArrayList<String> valori = new ArrayList<String>();
                //recupero i dati della riga
                if (categoria.contains("Clienti")) {
                    valori = AggiornamentoTabelle.datiModifica(jTableclienti);
                    String ragionesocialeMod = valori.get(1);
                    String codicefiscaleMod = valori.get(2);
                    String partitaivaMod = valori.get(3);
                    String cittaMod = valori.get(4);
                    String indirizzoMod = valori.get(5);
                    String telefonoMod = valori.get(6);
                    String emailMod = valori.get(7);
                    String capMod = valori.get(8);
                    //Setto i dati nei TextField corrispondenti
                    ragionesocialetextfield.setText(ragionesocialeMod);
                    codicefiscaletextfield.setText(codicefiscaleMod);
                    partitaivatextfield.setText(partitaivaMod);
                    cittatextfield.setText(cittaMod);
                    indirizzotextfield.setText(indirizzoMod);
                    telefonotextfield.setText(telefonoMod);
                    emailtextfield.setText(emailMod);
                    captextfield.setText(capMod);
                } else if (categoria.contains("Fornitori")) {
                    valori = AggiornamentoTabelle.datiModifica(jTablefornitori);
                    String ragionesocialeMod = valori.get(1);
                    String codicefiscaleMod = valori.get(2);
                    String partitaivaMod = valori.get(3);
                    String cittaMod = valori.get(4);
                    String indirizzoMod = valori.get(5);
                    String telefonoMod = valori.get(6);
                    String emailMod = valori.get(7);
                    String capMod = valori.get(8);
                    //Setto i dati nei TextField corrispondenti
                    ragionesocialetextfield.setText(ragionesocialeMod);
                    codicefiscaletextfield.setText(codicefiscaleMod);
                    partitaivatextfield.setText(partitaivaMod);
                    cittatextfield.setText(cittaMod);
                    indirizzotextfield.setText(indirizzoMod);
                    telefonotextfield.setText(telefonoMod);
                    emailtextfield.setText(emailMod);
                    captextfield.setText(capMod);
                } else if (categoria.contains("FattureEmesse")) {
                    modificabotton.setVisible(false);
                      aggiornatabellabutton.setVisible(true);
                    valori = AggiornamentoTabelle.datiModifica(jTablefattureemesse);
                    String nfatturaMod = valori.get(1);
                    String dMod = valori.get(2);
                    String iMod = valori.get(3);
                    String totMod = valori.get(4);
                    String ragionesocialeMod = valori.get(5);
                    numerofatturatextfield.setText(nfatturaMod);
                    datatextfield.setText(dMod);
                    ivatextfield.setText(iMod);
                    totaletextfield.setText(totMod);
                    ragionesocialetextfield.setText(ragionesocialeMod);
                    ricerca = ragionesocialeMod;
                    campo = "Ragionesociale";
                    //Compongo la stringa queryRicerca contenente la query della ricerca
                    String queryRicerca = "SELECT e FROM Clienti e WHERE UPPER(e." + campo.toLowerCase() + ") LIKE '%" + ricerca + "%'";
                    //Passo la namedQuery all'EntityManager e setto il parametro
                    q = InvoiGestPUEntityManager.createQuery(queryRicerca);
                    List l = q.getResultList();
                    for (Object o : l) {
                        //Eseguo un cast da Object a Clienti
                        Clienti p = (Clienti) o;
                        String codicefiscaleMod = p.getCodicefiscale();
                        String partitaivaMod = p.getPartitaiva();
                        String cittaMod = p.getCitta();
                        String indirizzoMod = p.getIndirizzo();
                        String telefonoMod = p.getTelefono();
                        String emailMod = p.getEmail();
                        String capMod = p.getCap();
                        ragionesocialetextfield.setText(ragionesocialeMod);
                        codicefiscaletextfield.setText(codicefiscaleMod);
                        partitaivatextfield.setText(partitaivaMod);
                        cittatextfield.setText(cittaMod);
                        indirizzotextfield.setText(indirizzoMod);
                        telefonotextfield.setText(telefonoMod);
                        emailtextfield.setText(emailMod);
                        captextfield.setText(capMod);
                        ricerca = numerofatturatextfield.getText();
                    }
                } else if (categoria.contains("FattureRicevute")) {
                    modificabotton.setVisible(false);
                      aggiornatabellabutton.setVisible(true);
                    valori = AggiornamentoTabelle.datiModifica(jTablefatturericevute);
                    String numfatturaMod = valori.get(1);
                    String datMod = valori.get(2);
                    String ivMod = valori.get(3);
                    String totaMod = valori.get(4);
                    String ragionesocialeMod = valori.get(5);
                    numerofatturatextfield.setText(numfatturaMod);
                    datatextfield.setText(datMod);
                    ivatextfield.setText(ivMod);
                    totaletextfield.setText(totaMod);
                    ragionesocialetextfield.setText(ragionesocialeMod);

                    //Compongo la stringa queryRicerca contenente la query della ricerca
                    ricerca = ragionesocialeMod;
                    campo = "Ragionesociale";
                    String queryRicerca = "SELECT r FROM Fornitori r WHERE UPPER(r." + campo.toLowerCase() + ") LIKE '%" + ricerca + "%'";
                    //Passo la namedQuery all'EntityManager e setto il parametro
                    q = InvoiGestPUEntityManager.createQuery(queryRicerca);
                    List l = q.getResultList();
                    for (Object o : l) {
                        //Eseguo un cast da Object a Fornitori
                        Fornitori p = (Fornitori) o;
                        ragionesocialeMod = p.getRagionesociale();
                        String codicefiscalMod = p.getCodicefiscale();
                        String partitaivMod = p.getPartitaiva();
                        String cittMod = p.getCitta();
                        String indirizzMod = p.getIndirizzo();
                        String telefonMod = p.getTelefono();
                        String emaiMod = p.getEmail();
                        String caMod = p.getCap();
                        ragionesocialetextfield.setText(ragionesocialeMod);
                        codicefiscaletextfield.setText(codicefiscalMod);
                        partitaivatextfield.setText(partitaivMod);
                        cittatextfield.setText(cittMod);
                        indirizzotextfield.setText(indirizzMod);
                        telefonotextfield.setText(telefonMod);
                        emailtextfield.setText(emaiMod);
                        captextfield.setText(caMod);
                    }
                }
            }
        }
    }//GEN-LAST:event_modificaprincipalebottonActionPerformed
    /**
     * Il metodo vairicercabottonActionPerformed ci consente di effettuare la ricerca all'interno
     * del database
     * @param evt rappresenta il click sul pulsante
     */
    private void vairicercabottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vairicercabottonActionPerformed
        //Creo due model
        DefaultTableModel model = new DefaultTableModel(0, 9);
        DefaultTableModel model1 = new DefaultTableModel(0, 5);
        //Eseguo il controllo sulla categoria
        if (categoria.contains("Scegli..")) {
            jInternalFrameErroreCategoria.setVisible(true);
        } else {
            jInternalFrameErroreCategoria.setVisible(false);
            //Recupero i dati da ricercare
            ricerca = jTextField6.getText();
            //Controllo che non sia nullo
            if (ricerca.contentEquals("")) {
                jInternalFrameErrorericerca.setVisible(true);
            } else {
                try {
                    if (categoria.contains("Clienti")) {
                        //Compongo la stringa queryRicerca contenente la query della ricerca
                        String queryRicerca = "SELECT e FROM Clienti e WHERE UPPER(e." + campo.toLowerCase() + ") LIKE '%" + ricerca + "%'";
                        //Passo la namedQuery all'EntityManager e setto il parametro
                        q = InvoiGestPUEntityManager.createQuery(queryRicerca);
                        //Aggiorno il model con il risultato della Query
                        model = AggiornamentoTabelle.risultatoQuery(q, categoria);
                    } else if (categoria.contains("Fornitori")) {
                        String queryRicerca = "SELECT r FROM Fornitori r WHERE UPPER(r." + campo.toLowerCase() + ") LIKE '%" + ricerca + "%'";
                        q = InvoiGestPUEntityManager.createQuery(queryRicerca);
                        model = AggiornamentoTabelle.risultatoQuery(q, categoria);
                    } else if (categoria.contains("FattureEmesse")) {
                        String queryRicerca = "SELECT fe FROM FattureEmesse fe WHERE UPPER(fe." + campo.toLowerCase() + ") LIKE '%" + ricerca + "%'";
                        q = InvoiGestPUEntityManager.createQuery(queryRicerca);
                        model1 = AggiornamentoTabelle.risultatoQueryFatture(q, categoria);
                    } else if (categoria.contains("FattureRicevute")) {
                        String queryRicerca = "SELECT fr FROM FattureRicevute fr WHERE UPPER(fr." + campo.toLowerCase() + ") LIKE '%" + ricerca + "%'";
                        q = InvoiGestPUEntityManager.createQuery(queryRicerca);
                        model1 = AggiornamentoTabelle.risultatoQueryFatture(q, categoria);
                    }
                } //Gestisco le eccezioni
                catch (IllegalStateException e) {
                    System.out.println("Connessione non riuscita");
                } catch (IllegalArgumentException e) {
                    System.out.println("Quella passata non è un Entità");
                }
                //Setto il model nella giusta jTable e aggiorno il nome delle colonne
                if (categoria.contains("Clienti")) {
                    jTableclienti.setModel(model);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTableclienti, categoria);
                } else if (categoria.contains("Fornitori")) {
                    jTablefornitori.setModel(model);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTablefornitori, categoria);
                } else if (categoria.contains("FattureEmesse")) {
                    jTablefattureemesse.setModel(model1);

                    AggiornamentoTabelle.aggiornaNomeColonne(jTablefattureemesse, categoria);
                } else if (categoria.contains("FattureRicevute")) {
                    jTablefatturericevute.setModel(model1);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTablefatturericevute, categoria);
                }
            }
        }
    }//GEN-LAST:event_vairicercabottonActionPerformed
    /**
     * Il metodo jComboBox2ActionPerformed ci permette di scegliere la colonna dove
     * effettuare la ricerca
     * @param evt rappresenta il click sul pulsante
     */
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        //Recupero il nome della colonna su cui effettuare la ricerca
        JComboBox cb = (JComboBox) evt.getSource();
        campo = (String) cb.getSelectedItem();
        campo.toLowerCase();

    }//GEN-LAST:event_jComboBox2ActionPerformed
    /**
     * Il metodo eliminabottonActionPerformed ci consente di eliminare la riga selezionata
     *
     * @param evt rappresenta il click sul pulsante
     */
    private void eliminabottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminabottonActionPerformed
        //Eseguo il controllo sulla categoria
        if (categoria.contains("Scegli..")) {
            jInternalFrameErroreCategoria.setVisible(true);
        } else {

            //Prendo in considerazione la tabella su cui devo eseguire l' operazioni
            JTable tabella = AggiornamentoTabelle.controlloTabella(categoria, jTableclienti, jTablefornitori, jTablefattureemesse, jTablefatturericevute, serviziotable);
            //Recupero il numero della riga selezionata
            int riga = tabella.getSelectedRow();
            //Controllo che sia stata selezionata una riga
            if (riga == -1) {
                norigaframe.setVisible(true);
            } else {
                jInternalFrameConfermaElimina.setVisible(true);
            }
        }
    }//GEN-LAST:event_eliminabottonActionPerformed
    /**
     * Il metodo annullaricercabottonActionPerformed ci consente di annullare la ricerca effettuata
     *
     * @param evt rappresenta il click sul pulsante
     */
    private void annullaricercabottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annullaricercabottonActionPerformed

        try {
            //Controllo la categoria
            if (categoria.contains("Scegli..")) {
                jInternalFrameErroreCategoria.setVisible(true);
            } else {
                DefaultTableModel model = new DefaultTableModel(0, 9);
                DefaultTableModel model1 = new DefaultTableModel(0, 5);
                //Compongo la namedQuery
                String namedQuery = categoria + ".findAll";
                //Avvio la connessione
                InvoiGestPUEntityManager.getTransaction().begin();
                //Passo la namedQuery all'EntityManager e setto il parametro
                q = InvoiGestPUEntityManager.createNamedQuery(namedQuery);
                //Sincronizzo il database
                InvoiGestPUEntityManager.flush();
                //Termino la Connessione
                InvoiGestPUEntityManager.getTransaction().commit();
                //Aggiorno i model con il risultato della Query
                model = AggiornamentoTabelle.risultatoQuery(q, categoria);
                model1 = AggiornamentoTabelle.risultatoQueryFatture(q, categoria);
                //Setto i model nella giusta jTable e aggiorno il nome delle colonne
                if (categoria.contains("Clienti")) {
                    jTableclienti.setModel(model);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTableclienti, categoria);
                } else if (categoria.contains("Fornitori")) {
                    jTablefornitori.setModel(model);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTablefornitori, categoria);
                } else if (categoria.contains("FattureEmesse")) {
                    jTablefattureemesse.setModel(model1);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTablefattureemesse, categoria);
                } else if (categoria.contains("FattureRicevute")) {
                    jTablefatturericevute.setModel(model1);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTablefatturericevute, categoria);
                }
            }
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
    }//GEN-LAST:event_annullaricercabottonActionPerformed
    /**

    /**
     * Il metodo jButton11ActionPerformed setta invisibile il jInternalFrameErrorericerca
     * @param evt
     */
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        jInternalFrameErrorericerca.setVisible(false);
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * Il metodo jButton12ActionPerformed cancella la riga selezionata
     * @param evt
     */
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            ArrayList<String> valori = new ArrayList<String>();
            Object oggettoRimuovi = new Object();
            InvoiGestPUEntityManager.getTransaction().begin();
            if (categoria.contains("Clienti")) {
                valori = AggiornamentoTabelle.datiModifica(jTableclienti);
                idMod = Integer.parseInt(valori.get(0));
                oggettoRimuovi = InvoiGestPUEntityManager.find(Clienti.class, idMod);
            } else if (categoria.contains("Fornitori")) {
                valori = AggiornamentoTabelle.datiModifica(jTablefornitori);
                idMod = Integer.parseInt(valori.get(0));
                oggettoRimuovi = InvoiGestPUEntityManager.find(Fornitori.class, idMod);
            } else if (categoria.contains("FattureEmesse")) {
                valori = AggiornamentoTabelle.datiModifica(jTablefattureemesse);
                idMod = Integer.parseInt(valori.get(0));
                oggettoRimuovi = InvoiGestPUEntityManager.find(FattureEmesse.class, idMod);
            } else if (categoria.contains("FattureRicevute")) {
                valori = AggiornamentoTabelle.datiModifica(jTablefatturericevute);
                idMod = Integer.parseInt(valori.get(0));
                oggettoRimuovi = InvoiGestPUEntityManager.find(FattureRicevute.class, idMod);
            }
            InvoiGestPUEntityManager.flush();
            InvoiGestPUEntityManager.remove(oggettoRimuovi);
            InvoiGestPUEntityManager.flush();
            String namedQuery = categoria + ".findAll";
            q = InvoiGestPUEntityManager.createNamedQuery(namedQuery);
            InvoiGestPUEntityManager.flush();
            InvoiGestPUEntityManager.getTransaction().commit();
            DefaultTableModel model = new DefaultTableModel(0, 9);
            DefaultTableModel modelf = new DefaultTableModel(0, 5);
            model = AggiornamentoTabelle.risultatoQuery(q, categoria);
            modelf = AggiornamentoTabelle.risultatoQueryFatture(q, categoria);
            if (categoria.contains("Clienti")) {
                jTableclienti.setModel(model);
                AggiornamentoTabelle.aggiornaNomeColonne(jTableclienti, categoria);
            } else if (categoria.contains("Fornitori")) {
                jTablefornitori.setModel(model);
                AggiornamentoTabelle.aggiornaNomeColonne(jTablefornitori, categoria);
            } else if (categoria.contains("FattureEmesse")) {
                jTablefattureemesse.setModel(modelf);
                AggiornamentoTabelle.aggiornaNomeColonne(jTablefattureemesse, categoria);
            } else if (categoria.contains("FattureRicevute")) {
                jTablefatturericevute.setModel(modelf);
                AggiornamentoTabelle.aggiornaNomeColonne(jTablefatturericevute, categoria);
            }
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
        jInternalFrameConfermaElimina.setVisible(false);
    }//GEN-LAST:event_jButton12ActionPerformed
    /**
     * Il metodo jButton13Actionperformed setta invisibile jInternalFrameConfermaElimina
     * @param evt
     */
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        jInternalFrameConfermaElimina.setVisible(false);
    }//GEN-LAST:event_jButton13ActionPerformed
    /**
     * Il metodo jButton14Actionperformed setta invisibile jInternalFrameErroreNoDati
     * @param evt
     */
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        jInternalFrameErroreNoDati.setVisible(false);
    }//GEN-LAST:event_jButton14ActionPerformed
    /**
     * Il metodo salvabotton prende i valori inseriti dall'utente e li inserisce nella base dati a seconda della categoria selezionata
     * @param evt
     */
    private void salvabottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvabottonActionPerformed
        // TODO add your handling code here:
stampabutton.setVisible(false);
        ragionesociale = ragionesocialetextfield.getText();
        codicefiscale = codicefiscaletextfield.getText();
        partitaiva = partitaivatextfield.getText();
        citta = cittatextfield.getText();
        indirizzo = indirizzotextfield.getText();
        telefono = telefonotextfield.getText();
        email = emailtextfield.getText();
        cap = captextfield.getText();
        numerofattura = numerofatturatextfield.getText();
        data = datatextfield.getText();
        iva = ivatextfield.getText();//ivatotale
        totale = totaletextfield.getText();
        //Controllo che i dati immessi non siano tutti nulli incompleto
        boolean controllo = AggiornamentoTabelle.controlloDati(ragionesociale, codicefiscale, partitaiva, citta, indirizzo, telefono, email, cap);
        //Catturo le eccezioni
        try {
            if (controllo == true) {
              fatturaframe.setVisible(false);
              jInternalFrameErroreNoDati.setVisible(true);
            } else if (controllo == false) {
//                fatturaframe.setVisible(false);
                DefaultTableModel model = new DefaultTableModel(0, 9);
                DefaultTableModel modelf = new DefaultTableModel(0, 4);
                //Avvio la connessione
                InvoiGestPUEntityManager.getTransaction().begin();
                if (categoria.contains("Clienti")) {
                    //Recupero il massimo ID
                    int id = AggiornamentoTabelle.numeroID("Cliente", categoria, InvoiGestPUEntityManager);
                    //Creo un istanza della classe Cliente
                    Clienti e = new Clienti();
                    e.setIDCliente(id + 1);
                    e.setRagionesociale(ragionesociale);
                    e.setCodicefiscale(codicefiscale);
                    e.setPartitaiva(partitaiva);
                    e.setCitta(citta);
                    e.setIndirizzo(indirizzo);
                    e.setTelefono(telefono);
                    e.setEmail(email);
                    e.setCap(cap);
                    //Aggiungo l'istanza al database
                    InvoiGestPUEntityManager.persist(e);
                    //Sincronizzo il database
                    InvoiGestPUEntityManager.flush();
                    //Recupero l'intera tabella
                    q = InvoiGestPUEntityManager.createNamedQuery("Clienti.findAll");
                    model = AggiornamentoTabelle.risultatoQuery(q, categoria);
                    jTableclienti.setModel(model);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTableclienti, categoria);
                } else if (categoria.contains("Fornitori")) {
                    int id = AggiornamentoTabelle.numeroID("Fornitore", categoria, InvoiGestPUEntityManager);
                    Fornitori r = new Fornitori(id + 1);
                    r.setRagionesociale(ragionesociale);
                    r.setCodicefiscale(codicefiscale);
                    r.setPartitaiva(partitaiva);
                    r.setCitta(citta);
                    r.setIndirizzo(indirizzo);
                    r.setTelefono(telefono);
                    r.setEmail(email);
                    r.setCap(cap);
                    InvoiGestPUEntityManager.persist(r);
                    //Sincronizzo il database
                    InvoiGestPUEntityManager.flush();
                    //Recupero l'intera tabella
                    q = InvoiGestPUEntityManager.createNamedQuery("Fornitori.findAll");
                    model = AggiornamentoTabelle.risultatoQuery(q, categoria);
                    jTablefornitori.setModel(model);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTablefornitori, categoria);
                } else if (categoria.contains("FattureEmesse")) {
                    categoria = "Clienti";
                    int id = AggiornamentoTabelle.numeroID("Cliente", categoria, InvoiGestPUEntityManager);
                    Clienti e = new Clienti();
                    e.setIDCliente(id + 1);
                    e.setRagionesociale(ragionesociale);
                    e.setCodicefiscale(codicefiscale);
                    e.setPartitaiva(partitaiva);
                    e.setCitta(citta);
                    e.setIndirizzo(indirizzo);
                    e.setTelefono(telefono);
                    e.setEmail(email);
                    e.setCap(cap);
                    InvoiGestPUEntityManager.persist(e);
                    InvoiGestPUEntityManager.flush();
                    q = InvoiGestPUEntityManager.createNamedQuery("Clienti.findAll");
                    model = AggiornamentoTabelle.risultatoQuery(q, categoria);
                    jTableclienti.setModel(model);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTableclienti, categoria);
                    categoria = "FattureEmesse";
                    id = AggiornamentoTabelle.numeroID("FattureEmesse", categoria, InvoiGestPUEntityManager);
                    FattureEmesse fe = new FattureEmesse(id + 1);
                    fe.setNumerofattura(numerofattura+"E");
                    fe.setDataf(data);
                    fe.setIva(iva);
                    fe.setTotale(totale);
                    fe.setRagionesociale(ragionesociale);
                    //Aggiungo l'istanza al database
                    InvoiGestPUEntityManager.persist(fe);
                    //Sincronizzo il database
                    InvoiGestPUEntityManager.flush();
                    //Recupero l'intera tabella
                    q = InvoiGestPUEntityManager.createNamedQuery("FattureEmesse.findAll");
                    modelf = AggiornamentoTabelle.risultatoQueryFatture(q, categoria);
                    jTablefattureemesse.setModel(modelf);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTablefattureemesse, categoria);
                } else if (categoria.contains("FattureRicevute")) {
                    categoria = "Fornitori";
                    int id = AggiornamentoTabelle.numeroID("Fornitore", categoria, InvoiGestPUEntityManager);
                    Fornitori r = new Fornitori();
                    r.setIDFornitore(id + 1);
                    r.setRagionesociale(ragionesociale);
                    r.setCodicefiscale(codicefiscale);
                    r.setPartitaiva(partitaiva);
                    r.setCitta(citta);
                    r.setIndirizzo(indirizzo);
                    r.setTelefono(telefono);
                    r.setEmail(email);
                    r.setCap(cap);
                    InvoiGestPUEntityManager.persist(r);
                    InvoiGestPUEntityManager.flush();
                    q = InvoiGestPUEntityManager.createNamedQuery("Fornitori.findAll");
                    model = AggiornamentoTabelle.risultatoQuery(q, categoria);
                    jTablefornitori.setModel(model);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTableclienti, categoria);
                    categoria = "FattureRicevute";
                    id = AggiornamentoTabelle.numeroID("FattureRicevute", categoria, InvoiGestPUEntityManager);
                    FattureRicevute fe = new FattureRicevute(id + 1);
               
                    fe.setNumerofattura(numerofattura+"R");
                    fe.setDataf(data);
                    fe.setIva(iva);
                    fe.setTotale(totale);
                    fe.setRagionesociale(ragionesociale);
                    InvoiGestPUEntityManager.persist(fe);
                    InvoiGestPUEntityManager.flush();
                    q = InvoiGestPUEntityManager.createNamedQuery("FattureRicevute.findAll");
                    modelf = AggiornamentoTabelle.risultatoQueryFatture(q, categoria);
                    jTablefatturericevute.setModel(modelf);
                    AggiornamentoTabelle.aggiornaNomeColonne(jTablefatturericevute, categoria);
                }
                //Termino la Connessione
                InvoiGestPUEntityManager.getTransaction().commit();
            }
        } //Gestisco le eccezioni
        catch (IllegalStateException e) {
            System.out.println("Connessione non riuscita");
        } catch (RollbackException e) {
            System.out.println("Impossibile chiudere la connessione");
        } catch (EntityExistsException e) {
            System.out.println("L'entità passata già esiste");
        } catch (IllegalArgumentException e) {
            System.out.println("Quella passata non è un Entità");
        } catch (TransactionRequiredException e) {
        }

        //Ripulisco tutti i jTextField 
        ragionesocialetextfield.setText("");
        codicefiscaletextfield.setText("");
        partitaivatextfield.setText("");
        indirizzotextfield.setText("");
        cittatextfield.setText("");
        telefonotextfield.setText("");
        emailtextfield.setText("");
        captextfield.setText("");
        numerofatturatextfield.setText("");
        datatextfield.setText("");
        codicetextfield.setText("");
        descrizionetext.setText("");
        totaletextfield.setText("");
        prezzotextfield.setText("");
        ivaprova.setText("");
        quantitatextfield.setText("");
    }//GEN-LAST:event_salvabottonActionPerformed
    /**
     * Il metodo stampabutton prende i valori dal frame  e genera un file pdf
     * @param evt
     */
    private void stampabuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stampabuttonActionPerformed
        ragionesociale = ragionesocialetextfield.getText();
        codicefiscale = codicefiscaletextfield.getText();
        partitaiva = partitaivatextfield.getText();
        citta = cittatextfield.getText();
        indirizzo = indirizzotextfield.getText();
        telefono = telefonotextfield.getText();
        email = emailtextfield.getText();
        cap = captextfield.getText();
        numerofattura = numerofatturatextfield.getText();
        data = datatextfield.getText();
        iva = ivatextfield.getText();//ivatotale
        totale = totaletextfield.getText();
        // Crea un Documento
        if (categoria.contains("FattureEmesse")) {
            //percorso del file
            String filename = ragionesociale + data + "FatturaEmessa.pdf";
            Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
            try {
                try {
                    // otteniamo una istanza di PdfWriter passando il document ed uno stream file
                    PdfWriter.getInstance(document, new FileOutputStream(filename));
                } catch (DocumentException ex) {
                    Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
            }
            // apriamo il documento
            document.open();
            try {
                // aggiunge  paragrafi
                document.add(new Paragraph("                                                                                                                                                                      data:  " + data));
                document.add(new Paragraph("                                                                                                      "));
                document.add(new Paragraph("                                Unibo spa " + "                                                                                                                     "));
                document.add(new Paragraph("                                Programmazione java" + "                                                                                                                " + ragionesociale));
                document.add(new Paragraph("                                Via  san Donato, 44" + "                                                                                                                    " + codicefiscale));
                document.add(new Paragraph("                                40100  Bologna" + "                                                                                                                           " + partitaiva));
                document.add(new Paragraph("                                Tel.  051 333333" + "                                                                                                                        " + indirizzo));
                document.add(new Paragraph("                                Cod.Fisc.  LGNRD892D2D" + "                                                                                                         " + citta + " cap:" + cap));
                document.add(new Paragraph("                                P.IVA   IT021870204" + "                                                                                                                  " + telefono));
                document.add(new Paragraph("                                                     " + "                                                                                                                              email: " + email));
                document.add(new Paragraph("                                                     " + "                                                                                                          "));
                document.add(new Paragraph("                           numero fattura:      " + numerofattura));
                document.add(new Paragraph("               "));
                document.add(new Paragraph("               "));
                PdfPTable table = new PdfPTable(4);
                table.addCell("Codice prodotto");
                table.addCell("Dettaglio");
                table.addCell("Quantità");
                table.addCell("Prezzo");
                table.addCell(CodicMod);
                table.addCell(DescrizMod);
                table.addCell(quantitaa);
                table.addCell(prezzoo);
                document.add(table);
                document.add(new Paragraph("               "));
                document.add(new Paragraph("               "));
                PdfPTable table1 = new PdfPTable(2);
                table1.addCell("Iva totale");
                table1.addCell("Importo totale");
                table1.addCell(iva);
                table1.addCell(totale);
                document.add(table1);


             


            } catch (DocumentException ex) {
                Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
            }
            // chiudiamo il documento
            document.close();
        } else if (categoria.contains("FattureRicevute")) {
            String filename = ragionesociale + data + "FatturaRicevuta.pdf";
            Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
            try {
                try {
                    // otteniamo una istanza di PdfWriter passando il document ed uno stream file
                    PdfWriter.getInstance(document, new FileOutputStream(filename));
                } catch (DocumentException ex) {
                    Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
            }
            // apriamo il documento
            document.open();
            try {
                document.add(new Paragraph("                                                                                                                                                                                          data:  " + data));
                document.add(new Paragraph("                                                                                                      "));
                document.add(new Paragraph("                        " + ragionesociale + "                                                                                                                     "));
                document.add(new Paragraph("                        " + codicefiscale + "                                                                                                                                          Unibo spa"));
                document.add(new Paragraph("                        " + partitaiva + "                                                                                                                                         Programmazione java"));
                document.add(new Paragraph("                        " + citta + "                                                                                                                                         Via  san Donato, 44"));
                document.add(new Paragraph("                        " + indirizzo + "                                                                                                                                         40100  Bologna"));
                document.add(new Paragraph("                        " + telefono + "                                                                                                                                         Tel.  051 333333"));
                document.add(new Paragraph("                        " + email + "                                                                                                                                          Cod.Fisc.  LGNRD892D2D"));
                document.add(new Paragraph("                        " + cap + "                                                                                                                                         P.IVA   IT021870204"));
                document.add(new Paragraph("                                                     " + "                                                                                                          "));
                document.add(new Paragraph("                           numero fattura:      " + numerofattura));
                document.add(new Paragraph("               "));
                document.add(new Paragraph("               "));
                PdfPTable table = new PdfPTable(4);
                table.addCell("Codice prodotto");
                table.addCell("Dettaglio");
                table.addCell("Quantità");
                table.addCell("Prezzo");
                table.addCell(CodicMod);
                table.addCell(DescrizMod);
                table.addCell(quantitaa);
                table.addCell(prezzoo);
                document.add(table);
                document.add(new Paragraph("               "));
                document.add(new Paragraph("               "));
                PdfPTable table1 = new PdfPTable(2);
                table1.addCell("Iva totale");
                table1.addCell("Importo totale");
                table1.addCell(iva);
                table1.addCell(totale);
                document.add(table1);
            } catch (DocumentException ex) {
                Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
            }
            // chiudiamo il documento
            document.close();
        } else if (categoria.contains("Clienti")) {
            String filename = ragionesociale + "Cliente.pdf";
            Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
            try {
                try {
                    // otteniamo una istanza di PdfWriter passando il document ed uno stream file
                    PdfWriter.getInstance(document, new FileOutputStream(filename));
                } catch (DocumentException ex) {
                    Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
            }
            // apriamo il documento
            document.open();
            try {
                document.add(new Paragraph(ragionesociale));
                document.add(new Paragraph(codicefiscale));
                document.add(new Paragraph(partitaiva));
                document.add(new Paragraph(citta));
                document.add(new Paragraph(indirizzo));
                document.add(new Paragraph(telefono));
                document.add(new Paragraph(email));
                document.add(new Paragraph(cap));
            } catch (DocumentException ex) {
                Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
            }
            // chiudiamo il documento
            document.close();
        } else if (categoria.contains("Fornitori")) {
            String filename = ragionesociale + "Fornitore.pdf";
            Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
            try {
                try {
                    // otteniamo una istanza di PdfWriter passando il document ed uno stream file
                    PdfWriter.getInstance(document, new FileOutputStream(filename));
                } catch (DocumentException ex) {
                    Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
            }
            // apriamo il documento
            document.open();
            try {
                document.add(new Paragraph(ragionesociale));
                document.add(new Paragraph(codicefiscale));
                document.add(new Paragraph(partitaiva));
                document.add(new Paragraph(citta));
                document.add(new Paragraph(indirizzo));
                document.add(new Paragraph(telefono));
                document.add(new Paragraph(email));
                document.add(new Paragraph(cap));
            } catch (DocumentException ex) {
                Logger.getLogger(InvoiGestView.class.getName()).log(Level.SEVERE, null, ex);
            }
            // chiudiamo il documento
            document.close();
        }
    }//GEN-LAST:event_stampabuttonActionPerformed
    /**
     * Il metodo jButton16ActionPerformed setta invisibile il frame della fattura e setti i campi come vuoti
     * @param evt
     */
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        ragionesocialetextfield.setText("");
        codicefiscaletextfield.setText("");
        partitaivatextfield.setText("");
        indirizzotextfield.setText("");
        cittatextfield.setText("");
        telefonotextfield.setText("");
        emailtextfield.setText("");
        captextfield.setText("");
        numerofatturatextfield.setText("");
        datatextfield.setText("");
        codicetextfield.setText("");
        descrizionetext.setText("");
        totaletextfield.setText("");
        prezzotextfield.setText("");
        ivatextfield.setText("");
        ivaprova.setText("");
        quantitatextfield.setText("");
        DefaultTableModel dtm = new DefaultTableModel(0, 6);
         dtm.setRowCount(0);
        serviziotable.setModel(dtm);
        AggiornamentoTabelle.aggiornaNomeColonneDettaglio(serviziotable, categoria);
        fatturaframe.setVisible(false);
        contenitoretotali=0;
        contenitoreiva=0;
        stampabutton.setVisible(true);
}//GEN-LAST:event_jButton16ActionPerformed
    /**
     * Il metodo modificabottonActionPerformed effettua modifiche sulle tabelle del database
     * @param evt
     */
    private void modificabottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificabottonActionPerformed
        //Recupero i dati da modificare
        ragionesociale = ragionesocialetextfield.getText();
        codicefiscale = codicefiscaletextfield.getText();
        partitaiva = partitaivatextfield.getText();
        citta = cittatextfield.getText();
        indirizzo = indirizzotextfield.getText();
        telefono = telefonotextfield.getText();
        email = emailtextfield.getText();
        cap = captextfield.getText();
        try {
            //Avvio la connessione
            InvoiGestPUEntityManager.getTransaction().begin();
            //Creo una variabile boolean e la istanzio
            boolean controllo = false;
            //Controllo che i dati inseriti dall'utente non siano tutti nulli
            controllo = AggiornamentoTabelle.controlloDati(ragionesociale, codicefiscale, partitaiva, citta, indirizzo, telefono, email, cap);
            if (controllo == false) {
                //Setto invisibile jInternalFrame1
                fatturaframe.setVisible(false);
                //Creo un ArrayList di tipo String
                ArrayList<String> valori = new ArrayList<String>();
                //Creo una variabile di tipo
                Object oggettoModifica = new Object();
                //Controllo su quale tabella si sta lavorando
                if (categoria.contains("Clienti")) {
                    //Recupero i valori e li aggiungo all'AttayList valori
                    valori = AggiornamentoTabelle.datiModifica(jTableclienti);
                    //Recupero l'Id della riga da modificare
                    idMod = Integer.parseInt(valori.get(0));
                    //Cerco l'Id nel database
                    oggettoModifica = InvoiGestPUEntityManager.find(Clienti.class, idMod);
                    //Eseguo un cast da Object a Clienti
                    Clienti Clienti = (Clienti) oggettoModifica;
                    //Setto Fattura con i nuovi dati
                    Clienti.setRagionesociale(ragionesociale);
                    Clienti.setCodicefiscale(codicefiscale);
                    Clienti.setCitta(citta);
                    Clienti.setIndirizzo(indirizzo);
                    Clienti.setPartitaiva(partitaiva);
                    Clienti.setTelefono(telefono);
                    Clienti.setEmail(email);
                    Clienti.setCap(cap);
                    //Eseguo un cast da Clienti a Object
                    oggettoModifica = (Object) Clienti;
                } else if (categoria.contains("Fornitori")) {
                    //Recupero i valori e li aggiungo all'AttayList valori
                    valori = AggiornamentoTabelle.datiModifica(jTablefornitori);
                    //Recupero l'Id della riga da modificare
                    idMod = Integer.parseInt(valori.get(0));
                    //Cerco Id nel database
                    oggettoModifica = InvoiGestPUEntityManager.find(Fornitori.class, idMod);
                    //Eseguo un cast da Object a Fornitori
                    Fornitori Fornitori = (Fornitori) oggettoModifica;
                    //Setto Fornitori con i nuovi dati
                    Fornitori.setRagionesociale(ragionesociale);
                    Fornitori.setCodicefiscale(codicefiscale);
                    Fornitori.setCitta(citta);
                    Fornitori.setIndirizzo(indirizzo);
                    Fornitori.setPartitaiva(partitaiva);
                    Fornitori.setTelefono(telefono);
                    Fornitori.setEmail(email);
                    Fornitori.setCap(cap);
                    //Eseguo un cast da Fornitori a Object
                    oggettoModifica = (Object) Fornitori;
                }
//                else if(categoria.contains("FattureEmesse")){
                //questa operazione potrebbe essere inserita tra le nuove features in un aggiornamento


                //Modifico l'oggetto
                InvoiGestPUEntityManager.merge(oggettoModifica);
                //Sincronizzo il database
                InvoiGestPUEntityManager.flush();
                //Recupero l'intera tabella
                String namedQuery = categoria + ".findAll";
                //Passo la namedQuery all'EntityManager
                q = InvoiGestPUEntityManager.createNamedQuery(namedQuery);
                //Costruisco un DefaultTableModel è lo inizializzo con i risultati della Query
                DefaultTableModel model = new DefaultTableModel(0, 9);
                //Aggiorno il model con il risultato della query
                model = AggiornamentoTabelle.risultatoQuery(q, categoria);
                if (categoria.contains("Clienti")) {
                    //Aggiorno la tabella
                    jTableclienti.setModel(model);
                    //Aggiorno i nomi delle colonne
                    AggiornamentoTabelle.aggiornaNomeColonne(jTableclienti, categoria);
                } else if (categoria.contains("Fornitori")) {
                    //Aggiorno la tabella
                    jTablefornitori.setModel(model);
                    //Aggiorno i nomi delle colonne
                    AggiornamentoTabelle.aggiornaNomeColonne(jTablefornitori, categoria);
                }
            } else {
                fatturaframe.setVisible(false);
                jInternalFrameErroreNoDati.setVisible(true);
            }
            //Termino la connessione
            InvoiGestPUEntityManager.getTransaction().commit();
        } //catturo le eccezioni
        catch (IllegalStateException ep) {
            System.out.println("Connessione non riuscita");
        } catch (RollbackException ep) {
            System.out.println("Impossibile chiudere la connessione");
        } catch (EntityExistsException ep) {
            System.out.println("L'entità passata già esiste");
        } catch (IllegalArgumentException ep) {
            System.out.println("Quella passata non è un Entità");
        } catch (TransactionRequiredException ep) {
        } catch (PersistenceException ep) {
        }
        //setto invisibile modificabotton
        modificabotton.setVisible(false);
        //Ripulisco tutti i jTextField del jInternalFrame1
        ragionesocialetextfield.setText("");
        codicefiscaletextfield.setText("");
        partitaivatextfield.setText("");
        indirizzotextfield.setText("");
        cittatextfield.setText("");
        telefonotextfield.setText("");
        emailtextfield.setText("");
        captextfield.setText("");
    }//GEN-LAST:event_modificabottonActionPerformed
    /**
     * Il metodo che aggiunge le voci fattura nel model dtm ed effettua l'inserimenti dei valori nella tabella prodotti
     * @param evt
     */
    private void aggiungivocibottomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aggiungivocibottomActionPerformed
        // TODO add your handling code here:
  
       
        DefaultTableModel dtm = (DefaultTableModel) serviziotable.getModel();
        String passaggio ;
        passaggio = categoria;
        descrizione = descrizionetext.getText();
        codice = codicetextfield.getText();
        Integer quantita = Integer.parseInt(quantitatextfield.getText());
        double prezzo = Double.parseDouble(prezzotextfield.getText());
        Integer iva = Integer.parseInt(ivaprova.getText());
        double IVA = 0;
        String Numerofattura = numerofatturatextfield.getText();
        prezzo = (prezzo * quantita);
        IVA = (prezzo * iva) / 100;
        Imponibile = (prezzo + IVA);
        contenitoretotali += Imponibile;
        contenitoreiva += IVA;
        String risultatoconiva = Double.toString(Imponibile);
        String risultatosomma = Double.toString(contenitoretotali);
        String ivaresult = Double.toString(IVA);
        String ivasomma = Double.toString(contenitoreiva);
        dtm.addRow(new Object[]{codicetextfield.getText(), quantitatextfield.getText(), prezzotextfield.getText() + ("€"), ivaresult + ("€"), risultatoconiva + ("€")});
        serviziotable.setModel(dtm);
        AggiornamentoTabelle.aggiornaNomeColonneDettaglio(serviziotable, categoria);
        ivatextfield.setText(ivasomma);
        totaletextfield.setText(risultatosomma);
        InvoiGestPUEntityManager.getTransaction().begin();
        try {
            categoria = "Prodotti";
            int id = AggiornamentoTabelle.numeroID("Prodotto", categoria, InvoiGestPUEntityManager);
            //Creo un istanza della classe Prodotti
            Prodotti pi = new Prodotti();
            pi.setIDProdotto(id + 1);
            pi.setDescrizione(descrizione);
            pi.setCodice(codice);
            pi.setQuantita(quantita);
            pi.setPrezzo(prezzo);
            if (passaggio.contains("FattureEmesse")){
                pi.setNumerofattura(Numerofattura+"E");
            }else  
                pi.setNumerofattura(Numerofattura+"R");
             //Aggiungo l'istanza al database
            InvoiGestPUEntityManager.persist(pi);
            //Sincronizzo il database
            InvoiGestPUEntityManager.flush();
            //Recupero l'intera tabella
            q = InvoiGestPUEntityManager.createNamedQuery("Prodotti.findAll");
            //Termino la Connessione
            InvoiGestPUEntityManager.getTransaction().commit();
            //Gestisco le eccezioni
        } catch (NumberFormatException e) {
            System.out.println("non è un valore calcolabile");
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
        categoria = passaggio;
      

    }//GEN-LAST:event_aggiungivocibottomActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        //setto invisibile jInternalFrame2
        jInternalFrameErroreCategoria.setVisible(false);
}//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        norigaframe.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * Il metodo aggiornatabellabuttonActionPerformed effettua una select nella tabella prodotti e inserisce i valori nel model
     * @param evt
     */
    private void aggiornatabellabuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aggiornatabellabuttonActionPerformed

        String categoriacontenitor = categoria;
        categoria = "Prodotti";
        //recupero tramite query i dati relativi ai prodotti
        String numfatt = numerofatturatextfield.getText();
        ricerca = numfatt;
        campo = "Numerofattura";

        String queryRicercaDettaglio = "SELECT pi FROM Prodotti pi WHERE UPPER(pi." + campo.toLowerCase() + ") LIKE '" + ricerca + "'";
        q = InvoiGestPUEntityManager.createQuery(queryRicercaDettaglio);
        //Aggiorno il model con il risultato della Query
        DefaultTableModel dtm = new DefaultTableModel(0, 4);
        List ls = q.getResultList();
        for (Object os : ls) {
            //Eseguo un cast da Object a prodotti
            Prodotti po = (Prodotti) os;
            DescrizMod = po.getDescrizione();
            Integer quantitMod = po.getQuantita();
            CodicMod = po.getCodice();
            Double prezzMod = po.getPrezzo();
            prezzoo = Double.toString(prezzMod);
            quantitaa = Double.toString(quantitMod);
            String[] dati = {String.valueOf(CodicMod), (quantitaa), (DescrizMod), (prezzoo)};
            dtm.addRow(dati);
            serviziotable.setModel(dtm);
            AggiornamentoTabelle.aggiornaNomeColonneDettaglioQuery(serviziotable, categoria);
            categoria = categoriacontenitor;
        }
    }//GEN-LAST:event_aggiornatabellabuttonActionPerformed
    /**
     * Il metodo
     * @param evt jButton3ActionPerformed cancella i valori dal model che rappresenta i prodotti
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String catego = categoria;
        categoria = "Prodotti";
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setRowCount(0);
        serviziotable.setModel(dtm);
        AggiornamentoTabelle.aggiornaNomeColonne(serviziotable, categoria);
        categoria = catego;
    }//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.util.List<invoigest.Clienti> ClientiList;
    private javax.persistence.Query ClientiQuery;
    private java.util.List<invoigest.FattureEmesse> FattureEmesseList;
    private javax.persistence.Query FattureEmesseQuery;
    private java.util.List<invoigest.FattureRicevute> FattureRicevuteList;
    private javax.persistence.Query FattureRicevuteQuery;
    private java.util.List<invoigest.Fornitori> FornitoriList;
    private javax.persistence.Query FornitoriQuery;
    private javax.persistence.EntityManager InvoiGestPUEntityManager;
    private java.util.List<invoigest.Prodotti> ProdottiList;
    private javax.persistence.Query ProdottiQuery;
    private javax.swing.JButton aggiornatabellabutton;
    private javax.swing.JButton aggiungibotton;
    private javax.swing.JButton aggiungivocibottom;
    private javax.swing.JButton annullaricercabotton;
    private javax.swing.JTextField captextfield;
    private javax.swing.JTextField cittatextfield;
    private javax.swing.JTextField codicefiscaletextfield;
    private javax.swing.JTextField codicetextfield;
    private javax.swing.JTextField datatextfield;
    private javax.swing.JTextArea descrizionetext;
    private javax.swing.JButton eliminabotton;
    private javax.swing.JTextField emailtextfield;
    private javax.swing.JFrame fatturaframe;
    private javax.swing.JTextField indirizzotextfield;
    private javax.swing.JTextField ivaprova;
    private javax.swing.JTextField ivatextfield;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JDesktopPane jDesktopPane5;
    private javax.swing.JDesktopPane jDesktopPane6;
    private javax.swing.JDesktopPane jDesktopPane7;
    private javax.swing.JDesktopPane jDesktopPane8;
    private javax.swing.JInternalFrame jInternalFrameConfermaElimina;
    private javax.swing.JInternalFrame jInternalFrameErroreCategoria;
    private javax.swing.JInternalFrame jInternalFrameErroreNoDati;
    private javax.swing.JInternalFrame jInternalFrameErrorericerca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPaneclienti;
    private javax.swing.JScrollPane jScrollPanefattureemesse;
    private javax.swing.JScrollPane jScrollPanefatturericevute;
    private javax.swing.JScrollPane jScrollPanefornitori;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableclienti;
    private javax.swing.JTable jTablefattureemesse;
    private javax.swing.JTable jTablefatturericevute;
    private javax.swing.JTable jTablefornitori;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton modificabotton;
    private javax.swing.JButton modificaprincipalebotton;
    private javax.swing.JInternalFrame norigaframe;
    private javax.swing.JTextField numerofatturatextfield;
    private javax.swing.JTextField partitaivatextfield;
    private javax.swing.JTextField prezzotextfield;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField quantitatextfield;
    private javax.swing.JTextField ragionesocialetextfield;
    private javax.swing.JButton salvabotton;
    private javax.swing.JTable serviziotable;
    private javax.swing.JButton stampabutton;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextField telefonotextfield;
    private javax.swing.JTextField totaletextfield;
    private javax.swing.JButton vairicercabotton;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
