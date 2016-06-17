package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * L'entité Publication
 */
@Entity
public class Publication {
    /**
     * Identifiant de publication
     */
    @Id
    public Long id;

    /**
     * Le titre de la publication
     */
    @Constraints.Required
    public String titre;

    /**
     * le contenu de la publication
     */
    @Constraints.Required
    public String contenu;

    /**
     * La date de la publication
     */
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dueDate = new Date();

    /**
     * L'image associée à la publication
     */
    public String urlImage;

    /**
     * Relation entre Publication et Commentaire
     * Une publication est associée à plusieurs commentaires
     */
    @OneToMany(mappedBy = "publication")
    public List<Commentaire> commentaires;

    /**
     * Relation entre Publication et Membre
     * plusieurs publications sont associées à un seul membre
     */
    @ManyToOne
    public Membre membre;

    /**
     * Constructeur par defaut
     */
    public Publication() {
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Model.Finder<Long, Publication> find = new Model.Finder<Long,Publication>(Publication.class);

}
