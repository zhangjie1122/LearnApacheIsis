package domainapp.dom.pet;

/**
 * Created by zj on 2015/12/19.
 */

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import domainapp.dom.enums.PetSpecies;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.util.ObjectContracts;

@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple",
        table = "pets"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
        column = "id")
@javax.jdo.annotations.Version(
        strategy = VersionStrategy.DATE_TIME,
        column = "version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findBySpecies", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.pets.Pet "
                        + "WHERE species == :species "),
        @javax.jdo.annotations.Query(
                name = "findByName", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.pets.Pet "
                        + "WHERE name.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.Unique(name = "Pet_name_UNQ", members = {"name"})
@DomainObject
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT,
        cssClassFa = "fa-flag"
)
public class Pet implements Comparable<Pet> {
    @javax.jdo.annotations.Column(allowsNull = "false")
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @javax.jdo.annotations.Column(allowsNull = "false")
    private PetSpecies species;
    public PetSpecies getSpecies() {
        return this.species;
    }
    public void setSpecies(PetSpecies species) {
        this.species= species;
    }

    @Override
    public int compareTo(final Pet other) {
        return ObjectContracts.compare(this, other, "name");
    }

    @javax.inject.Inject
    private DomainObjectContainer container;
}
