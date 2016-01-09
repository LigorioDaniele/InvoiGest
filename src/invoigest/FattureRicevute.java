/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoigest;

/**
 *
 * @author Antonello Sanza
 */
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "FattureRicevute", catalog = "InvoiGest", schema = "")
@NamedQueries({
    @NamedQuery(name = "FattureRicevute.findAll", query = "SELECT fr FROM FattureRicevute fr"),
    @NamedQuery(name = "FattureRicevute.findByIDFattureRicevute", query = "SELECT fr FROM FattureRicevute fr WHERE fr.iDFattureRicevute = :iDFattureRicevute"),
    @NamedQuery(name = "FattureRicevute.numerofattura", query = "SELECT fr FROM FattureRicevute fr WHERE UPPER(fr.numerofattura) LIKE '%:numerofattura% '"),
    @NamedQuery(name = "FattureRicevute.dataf", query = "SELECT fr FROM FattureRicevute fr WHERE UPPER(fr.dataf) = :dataf"),
    @NamedQuery(name = "FattureRicevute.iva", query = "SELECT fr FROM FattureRicevute fr WHERE UPPER(fr.iva) = :iva"),
    @NamedQuery(name = "FattureRicevute.totale", query = "SELECT fr FROM FattureRicevute fr WHERE UPPER(fr.totale) = :totale")})
public class FattureRicevute implements Serializable {
    //  public Clienti Clienti;

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_fatturericevute")
    private Integer iDFattureRicevute;
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

    public FattureRicevute() {
    }

    public FattureRicevute(Integer iDFattureRicevute) {
        this.iDFattureRicevute = iDFattureRicevute;
    }

    public Integer getIDFattureRicevute() {
        return iDFattureRicevute;
    }

    public void setIDFattureRicevute(Integer iDFattureRicevute) {
        Integer oldIDFattureRicevute = this.iDFattureRicevute;
        this.iDFattureRicevute = iDFattureRicevute;
        changeSupport.firePropertyChange("IDFattureRicevute", oldIDFattureRicevute, iDFattureRicevute);
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
        hash += (iDFattureRicevute != null ? iDFattureRicevute.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FattureRicevute)) {
            return false;
        }
        FattureRicevute other = (FattureRicevute) object;
        if ((this.iDFattureRicevute == null && other.iDFattureRicevute != null) || (this.iDFattureRicevute != null && !this.iDFattureRicevute.equals(other.iDFattureRicevute))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invoigest.FattureRicevute[iDFatturaE=" + iDFattureRicevute + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
