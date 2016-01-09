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
 * @version 1.0
 */
@Entity
@Table(name = "Clienti", catalog = "InvoiGest", schema = "")
@NamedQueries({
    @NamedQuery(name = "Clienti.findAll", query = "SELECT e FROM Clienti e"),
    @NamedQuery(name = "Clienti.findByIDCliente", query = "SELECT e FROM Clienti e WHERE e.iDCliente = :iDCliente"),
    @NamedQuery(name = "Clienti.ragionesociale", query = "SELECT e FROM Clienti e WHERE UPPER(e.ragionesociale) LIKE '%:ragionesociale% '"),
    @NamedQuery(name = "Clienti.codicefiscale", query = "SELECT e FROM Clienti e WHERE UPPER(e.codicefiscale) = :codicefiscale"),
    @NamedQuery(name = "Clienti.partitaiva", query = "SELECT e FROM Clienti e WHERE UPPER(e.partitaiva) = :partitaiva"),
    @NamedQuery(name = "Clienti.citta", query = "SELECT e FROM Clienti e WHERE UPPER(e.citta) = :citta"),
    @NamedQuery(name = "Clienti.indirizzo", query = "SELECT e FROM Clienti e WHERE UPPER(e.indirizzo) = :indirizzo"),
    @NamedQuery(name = "Clienti.telefono", query = "SELECT e FROM Clienti e WHERE UPPER(e.telefono) = :telefono"),
    @NamedQuery(name = "Clienti.email", query = "SELECT e FROM Clienti e WHERE UPPER(e.email) = :email"),
    @NamedQuery(name = "Clienti.cap", query = "SELECT e FROM Clienti e WHERE UPPER(e.cap) = :cap")})
public class Clienti implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_cliente")
    private Integer iDCliente;
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

    public Clienti() {
    }

    public Clienti(Integer iDCliente) {
        this.iDCliente = iDCliente;
    }

    public Integer getIDCliente() {
        return iDCliente;
    }

    public void setIDCliente(Integer iDCliente) {
        Integer oldIDCliente = this.iDCliente;
        this.iDCliente = iDCliente;
        changeSupport.firePropertyChange("IDCliente", oldIDCliente, iDCliente);
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
        hash += (iDCliente != null ? iDCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clienti)) {
            return false;
        }
        Clienti other = (Clienti) object;
        if ((this.iDCliente == null && other.iDCliente != null) || (this.iDCliente != null && !this.iDCliente.equals(other.iDCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invoigest.Clienti[iDCliente=" + iDCliente + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
