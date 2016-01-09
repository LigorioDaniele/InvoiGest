/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoigest;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Antonello Sanza
 */
@Entity
@Table(name = "FattureEmesse", catalog = "InvoiGest", schema = "")
@NamedQueries({
    @NamedQuery(name = "FattureEmesse.findAll", query = "SELECT fe FROM FattureEmesse fe"),
    @NamedQuery(name = "FattureEmesse.findByIDFattureEmesse", query = "SELECT fe FROM FattureEmesse fe WHERE fe.iDFattureEmesse = :iDFattureEmesse"),
    @NamedQuery(name = "FattureEmesse.numerofattura", query = "SELECT fe FROM FattureEmesse fe WHERE UPPER(fe.numerofattura) LIKE '%:numerofattura% '"),
    @NamedQuery(name = "FattureEmesse.dataf", query = "SELECT fe FROM FattureEmesse fe WHERE UPPER(fe.dataf) = :dataf"),
    @NamedQuery(name = "FattureEmesse.iva", query = "SELECT fe FROM FattureEmesse fe WHERE UPPER(fe.iva) = :iva"),
    @NamedQuery(name = "FattureEmesse.totale", query = "SELECT fe FROM FattureEmesse fe WHERE UPPER(fe.totale) = :totale")})
public class FattureEmesse implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_fattureEmesse")
    private Integer iDFattureEmesse;
    @Column(name = "Numerofattura")
    private String numerofattura;
    @Column(name = "Dataf")
    private String dataf;
    @Column(name = "Iva")
    private String iva;
    @Column(name = "Totale")
    private String totale;
    @Column(name = "Ragionesociale")
    private String ragionesociale;
//    @Column(name = "ID_Prodotto")
//    private Integer iDProdotto;
//    private double prezzo;
    public FattureEmesse() {
    }

    public FattureEmesse(Integer iDFattureEmesse) {
        this.iDFattureEmesse = iDFattureEmesse;
    }

    public Integer getIDFattureEmesse() {
        return iDFattureEmesse;
    }

    public void setIDFattureEmesse(Integer iDFattureEmesse) {
        Integer oldIDFattureEmesse = this.iDFattureEmesse;
        this.iDFattureEmesse = iDFattureEmesse;
        changeSupport.firePropertyChange("IDFattureEmesse", oldIDFattureEmesse, iDFattureEmesse);
    }

    public String getNumerofattura() {
        return numerofattura;
    }

    public void setNumerofattura(String numerofattura) {
        String oldNumeroFattura = this.numerofattura;
        this.numerofattura = numerofattura;
        changeSupport.firePropertyChange("NumeroFattura", oldNumeroFattura, numerofattura);
    }

    public String getDataf() {
        return dataf;
    }

    public void setDataf(String dataf) {
        String oldDataf = this.dataf;
        this.dataf = dataf;
        changeSupport.firePropertyChange("Dataf", oldDataf, dataf);
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        String oldIva = this.iva;
        this.iva = iva;
        changeSupport.firePropertyChange("Iva", oldIva, iva);
    }

    public String getTotale() {
        return totale;
    }

    public void setTotale(String totale) {
        String oldTotale = this.totale;
        this.totale = totale;
        changeSupport.firePropertyChange("Totale", oldTotale, totale);
    }

    public String getRagionesociale() {
        return ragionesociale;
    }

    public void setRagionesociale(String ragionesociale) {
        String oldRagioneS = this.ragionesociale;
        this.ragionesociale = ragionesociale;
        changeSupport.firePropertyChange("Ragione sociale", oldRagioneS, ragionesociale);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDFattureEmesse != null ? iDFattureEmesse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FattureEmesse)) {
            return false;
        }
        FattureEmesse other = (FattureEmesse) object;
        if ((this.iDFattureEmesse == null && other.iDFattureEmesse != null) || (this.iDFattureEmesse != null && !this.iDFattureEmesse.equals(other.iDFattureEmesse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invoigest.FattureEmesse[iDFatturaE=" + iDFattureEmesse + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
