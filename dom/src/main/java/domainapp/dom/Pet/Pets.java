package domainapp.dom.pet;

/**
 * Created by zj on 2015/12/19.
 */

import java.util.List;

import domainapp.dom.enums.PetSpecies;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.SemanticsOf;

@DomainService(repositoryFor = Pet.class)
@DomainServiceLayout(menuOrder = "20")
public class Pets {
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<Pet> listAll() {
        return container.allInstances(Pet.class);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public Pet create(@ParameterLayout(named = "name") final String name,
                       final PetSpecies species) {
        final Pet obj = container.newTransientInstance(Pet.class);
        obj.setName(name);
        obj.setSpecies(species);
        container.persistIfNotAlready(obj);
        return obj;
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "3")
    public List<Pet> findByName(@ParameterLayout(named = "Name") final String name) {
        return container.allMatches(new QueryDefault<>(Pet.class, "findByName", "name", name));
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "4")
    public List<Pet> findBySpecies(@ParameterLayout(named = "Species") final PetSpecies species) {
        return container.allMatches(new QueryDefault<>(Pet.class, "findBySpecies", "species", species));
    }

    @javax.inject.Inject
    DomainObjectContainer container;
}
