package domainapp.dom.Owner;

/**
 * Created by zj on 2015/12/19.
 */

import java.util.List;

import domainapp.dom.Enums.Gender;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.query.QueryDefault;

@DomainService(repositoryFor = Owner.class)
@DomainServiceLayout(menuOrder = "20")
public class Owners {
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<Owner> listAll() {
        return container.allInstances(Owner.class);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<Owner> findByName(@ParameterLayout(named = "Name") final String name) {
        return container.allMatches(new QueryDefault<>(Owner.class, "findByName", "name", name));
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public Owner create(@ParameterLayout(named = "Name") final String name,
                        @ParameterLayout(named = "Gender") final Gender gender) {
        Owner obj = container.newTransientInstance(Owner.class);
        obj.setName(name);
        obj.setGender(gender);
        container.persistIfNotAlready(obj);
        return obj;
    }

    @javax.inject.Inject
    DomainObjectContainer container;
}
