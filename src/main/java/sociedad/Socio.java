package sociedad;

import java.util.Objects;
import java.util.Set;

public class Socio implements Comparable<Socio>{
    private String name;
    private Set<String> interests;
    private int ident;
    public Socio(String nom, Set<String> in, int i){
        if (nom == null || in == null || i <= 0){
            throw new SociedadException("Args invalidos");
        }else {
            name = nom;
            interests = in;
            /*
            for (String interest : i) {
			interests.add(interest.toLowerCase());
		     }
             */
            ident = i;
        }
    }

    public String getName() {
        return name;
    }

    public Set<String> getInterests() {
        return interests;
    }

    public int getIdent() {
        return ident;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + interests.toString().toLowerCase() + ", " + ident + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socio socio = (Socio) o;
        return ident == socio.ident && this.hashCode() == ((Socio) o).hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), ident);
    }

    @Override
    public int compareTo(Socio o) {
        int cmp = 0;
        cmp = this.name.compareToIgnoreCase(o.name);
        if (cmp == 0){
            cmp = Integer.compare(this.ident, o.ident);
        }
        return cmp;
    }
}
