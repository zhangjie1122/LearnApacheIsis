package domainapp.dom.Owner;

/**
 * Created by zj on 2015/12/19.
 */

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import domainapp.dom.Enums.Gender;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.util.ObjectContracts;

@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        table = "owner"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
        column = "id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER,
        column = "version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByName", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.Owner.Owner "
                        + "WHERE name.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.Unique(name = "Owner_name_UNQ", members = {"name"})
@DomainObject(bounded = true)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT,
        cssClassFa = "fa-flag"
)
public class Owner implements Comparable<Owner> {
    @javax.jdo.annotations.Column(allowsNull = "fasle")
    private String name;
    @Title(prepend = "", sequence = "1")
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Gender gender;
    public Gender getGender() {
        return this.gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(final Owner other) {
        return ObjectContracts.compare(this, other, "name");
    }

    @javax.inject.Inject
    private DomainObjectContainer container;
}
