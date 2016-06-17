package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * L'Entite Commentaire
 */
@Entity
public class Commentaire {
    /**
     * L'identificateur de l'entite
     */
    @Id
    public Long id;

    /**
     * Le contenu du commentaire
     */
    @Constraints.Required
    public String contenu;

    /**
     * Relation entre commentaire et Publication
     * plusieurs commentaires sont associés à une publication
     */
    @ManyToOne
    public Publication publication;

    /**
     * Relation entre commentaire et membre
     * plusieurs commentaires sont faits par un membre
     */
    @ManyToOne
    public Membre membre;

    public Commentaire() {
    }
    public static Model.Finder<Long, Commentaire> find = new Model.Finder<Long,Commentaire>(Commentaire.class);
}
