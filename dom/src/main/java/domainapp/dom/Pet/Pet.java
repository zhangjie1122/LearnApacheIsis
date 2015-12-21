package domainapp.dom.Pet;

/**
 * Created by zj on 2015/12/19.
 */

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import domainapp.dom.Enums.PetSpecies;
import domainapp.dom.Owner.Owner;
import domainapp.dom.Owner.Owners;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.util.ObjectContracts;

import java.util.Collection;

@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        table = "pet"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
        column = "id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER,
        column = "version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findBySpecies", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.Pet.Pet "
                        + "WHERE species == :species "),
        @javax.jdo.annotations.Query(
                name = "findByName", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.Pet.Pet "
                        + "WHERE name.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.Unique(name = "Pet_name_UNQ", members = {"name"})
@DomainObject(bounded = true)
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

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Owner owner;
    public Owner getOwner() {
        return this.owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Collection<Owner> autoCompleteOwner(final String name){
        return owners.findByName(name);
    }

    public String iconName() {
        return getSpecies().name();
    }

    @Override
    public int compareTo(final Pet other) {
        return ObjectContracts.compare(this, other, "name");
    }

    @javax.inject.Inject
    private DomainObjectContainer container;

    @javax.inject.Inject
    private Owners owners;
}
