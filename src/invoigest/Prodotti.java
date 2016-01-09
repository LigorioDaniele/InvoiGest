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
@Table(name = "Prodotti", catalog = "InvoiGest", schema = "")
@NamedQueries({
    @NamedQuery(name = "Prodotti.findAll", query = "SELECT pi FROM Prodotti pi"),
    @NamedQuery(name = "Prodotti.findByIDProdotto", query = "SELECT pi FROM Prodotti pi WHERE pi.iDProdotto = :iDProdotto"),
    @NamedQuery(name = "Prodotti.descrizione", query = "SELECT pi FROM Prodotti pi WHERE UPPER(pi.descrizione) LIKE '%:descrizione% '"),
    @NamedQuery(name = "Prodotti.codice", query = "SELECT pi FROM Prodotti pi WHERE UPPER(pi.codice) = :codice"),
    @NamedQuery(name = "Prodotti.quantita", query = "SELECT pi FROM Prodotti pi WHERE UPPER(pi.quantita) = :quantita"),
    @NamedQuery(name = "Prodotti.prezzo", query = "SELECT pi FROM Prodotti pi WHERE UPPER(pi.prezzo) = :prezzo")})
public class Prodotti implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Prodotto")
    private Integer iDProdotto;
    @Column(name = "Descrizione")
    private String descrizione;
    @Column(name = "Codice")
    private String codice;
    @Column(name = "Quantita")
    private Integer quantita;
    @Column(name = "Prezzo")
    private double prezzo;
    @Column(name = "Numerofattura")
    private String numerofattura;

    public Prodotti() {
    }

    public Prodotti(Integer iDProdotto) {
        this.iDProdotto = iDProdotto;
    }

    public Integer getIDProdotto() {
        return iDProdotto;
    }

    public void setIDProdotto(Integer iDProdotto) {
        Integer oldIDProdotto = this.iDProdotto;
        this.iDProdotto = iDProdotto;
        changeSupport.firePropertyChange("IDProdotto", oldIDProdotto, iDProdotto);
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        String oldDescrizione = this.descrizione;
        this.descrizione = descrizione;
        changeSupport.firePropertyChange("Descrizione", oldDescrizione, descrizione);
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        String oldCodice = this.codice;
        this.codice = codice;
        changeSupport.firePropertyChange("Codice", oldCodice, codice);
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        Integer oldQuantita = this.quantita;
        this.quantita = quantita;
        changeSupport.firePropertyChange("Quantita", oldQuantita, quantita);
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        double oldPrezzo = this.prezzo;
        this.prezzo = prezzo;
        changeSupport.firePropertyChange("Prezzo", oldPrezzo, prezzo);
    }

    public String getNumerofattura() {
        return numerofattura;
    }

    public void setNumerofattura(String numerofattura) {
        String oldNumeroFattura = this.numerofattura;
        this.numerofattura = numerofattura;
        changeSupport.firePropertyChange("NumeroFattura", oldNumeroFattura, numerofattura);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDProdotto != null ? iDProdotto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Prodotti)) {
            return false;
        }
        Prodotti other = (Prodotti) object;
        if ((this.iDProdotto == null && other.iDProdotto != null) || (this.iDProdotto != null && !this.iDProdotto.equals(other.iDProdotto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invoigest.Prodotti[iDProdotto=" + iDProdotto + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}

