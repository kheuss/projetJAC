package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Entite Langue
 */

@Entity
public class Langue extends Model {
    /**
     * Identifiant de Langue
     */
    @Id
    public long id;

    /**
     * le libelle de la langue
     */
    @Constraints.Required
    public String libele;

    /**
     * Relation entre Langue et Profil
     * plusieurs langues sont associees à un profil
     */
    @ManyToOne
    public Profil profil;

    /**
     * Constructeur par defaut
     */
    public Langue() {
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Langue> find = new Finder<Long,Langue>(Langue.class);
}
