/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoigest;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Antonello Sanza
 * 
 */
@Entity
@Table(name = "Fornitori", catalog = "InvoiGest", schema = "")
@NamedQueries({
    @NamedQuery(name = "Fornitori.findAll", query = "SELECT r FROM Fornitori r"),
    @NamedQuery(name = "Fornitori.findByIDFornitore", query = "SELECT r FROM Fornitori r WHERE r.iDFornitore = :iDFornitore"),
    @NamedQuery(name = "Fornitori.ragionesociale", query = "SELECT r FROM Fornitori r WHERE UPPER(r.ragionesociale) = :ragionesociale"),
    @NamedQuery(name = "Fornitori.codicefiscale", query = "SELECT r FROM Fornitori r WHERE UPPER(r.codicefiscale) = :codicefiscale"),
    @NamedQuery(name = "Fornitori.partitaiva", query = "SELECT r FROM Fornitori r WHERE UPPER(r.partitaiva) = :partitaiva"),
    @NamedQuery(name = "Fornitori.citta", query = "SELECT r FROM Fornitori r WHERE UPPER(r.citta) = :citta"),
    @NamedQuery(name = "Fornitori.indirizzo", query = "SELECT r FROM Fornitori r WHERE UPPER(r.indirizzo) = :indirizzo"),
    @NamedQuery(name = "Fornitori.telefono", query = "SELECT r FROM Fornitori r WHERE UPPER(r.telefono) = :telefono"),
    @NamedQuery(name = "Fornitori.email", query = "SELECT r FROM Fornitori r WHERE UPPER(r.email) = :email"),
    @NamedQuery(name = "Fornitori.cap", query = "SELECT r FROM Fornitori r WHERE UPPER(r.cap) = :cap")})
public class Fornitori implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Fornitore")
    private Integer iDFornitore;
    @Column(name = "Ragionesociale")
    private String ragionesociale;
    @Column(name = "Codicefiscale")
    private String codicefiscale;
    @Column(name = "Partitaiva")
    private String partitaiva;
    @Column(name = "Citta")
    private String citta;
    @Column(name = "Indirizzo")
    private String indirizzo;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Email")
    private String email;
    @Column(name = "Cap")
    private String cap;

    public Fornitori() {
    }

    public Fornitori(Integer iDFornitore) {
        this.iDFornitore = iDFornitore;
    }

    public Integer getIDFornitore() {
        return iDFornitore;
    }

    public void setIDFornitore(Integer iDFornitore) {
        Integer oldIDFornitore = this.iDFornitore;
        this.iDFornitore = iDFornitore;
        changeSupport.firePropertyChange("IDFornitore", oldIDFornitore, iDFornitore);
    }

    public String getRagionesociale() {
        return ragionesociale;
    }

    public void setRagionesociale(String ragionesociale) {
        String oldRagioneS = this.ragionesociale;
        this.ragionesociale = ragionesociale;
        changeSupport.firePropertyChange("Ragione sociale", oldRagioneS, ragionesociale);
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public void setCodicefiscale(String codicefiscale) {
        String oldCodiceF = this.codicefiscale;
        this.codicefiscale = codicefiscale;
        changeSupport.firePropertyChange("Codice fiscale", oldCodiceF, codicefiscale);
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        String oldIndirizzo = this.indirizzo;
        this.indirizzo = indirizzo;
        changeSupport.firePropertyChange("indirizzo", oldIndirizzo, indirizzo);
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        String oldCitta = this.citta;
        this.citta = citta;
        changeSupport.firePropertyChange("Città", oldCitta, citta);
    }

    public String getPartitaiva() {
        return partitaiva;
    }

    public void setPartitaiva(String partitaiva) {
        String oldPartitaI = this.partitaiva;
        this.partitaiva = partitaiva;
        changeSupport.firePropertyChange("Partita iva", oldPartitaI, partitaiva);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        String oldTelefono = this.telefono;
        this.telefono = telefono;
        changeSupport.firePropertyChange("Telefono", oldTelefono, telefono);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("Email", oldEmail, email);
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        String oldCap = this.cap;
        this.cap = cap;
        changeSupport.firePropertyChange("Cap", oldCap, cap);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDFornitore != null ? iDFornitore.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornitori)) {
            return false;
        }
        Fornitori other = (Fornitori) object;
        if ((this.iDFornitore == null && other.iDFornitore != null) || (this.iDFornitore != null && !this.iDFornitore.equals(other.iDFornitore))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invoigest.Fornitori[iDFornitore=" + iDFornitore + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
