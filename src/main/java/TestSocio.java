import sociedad.Socio;

import java.util.HashSet;
import java.util.Set;

public class TestSocio {
    public static void main(String[] args) {
        Set<String> gustos1= new HashSet<>();
        gustos1.add("Senderismo");
        gustos1.add("Escalada");
        Socio socio1 = new Socio( "Layton Kor", gustos1, 24);

        Set<String> gustos2= new HashSet<>(); //ns si vale poner Set a secas
        Socio socio2 = new Socio( "layton kor", gustos2, 24);

        System.out.println(socio1);
        System.out.println(socio2);

        if (socio1.equals(socio2)){
            System.out.println( "Son el mismo socio" );
        }else {
            System.out.println( "Son socios distintos" );
        }
    }
}
