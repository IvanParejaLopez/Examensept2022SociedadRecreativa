package sociedad;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Sociedad {
    private Set <Socio> membersNotInActivities;
    private Map<String, Set<Socio>> membersInActivities;
    public Sociedad(){
        membersNotInActivities = new HashSet<>(); //ns si poner hashmap o tree
        membersInActivities = new HashMap<>();
    }
    public void leerDeFichero(String fich){
        try {
            File file = new File(fich);
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()){
                    scanner.useDelimiter("[%]");
                    String nombre = scanner.next();
                    Set<String> interests = new HashSet<>();
                    try (Scanner sc = new Scanner(scanner.next())){
                        sc.useDelimiter(",");
                        while (sc.hasNext()){
                            interests.add(sc.next().toLowerCase(Locale.ENGLISH));
                        }
                    }
                    int num = scanner.nextInt();
                    nuevoSocio(new Socio(nombre, interests, num));
                }
            }catch (IOException e){
                throw new IOException(e.getMessage());
            }catch (Exception e){
                //Do nothing
            }
        }catch (IOException e){
            throw new SociedadException("No se ha encontrado el fichero " + fich);
        }
    }

    public void nuevoSocio(Socio m){
        if (!membersNotInActivities.contains(m)){ //ns si esto funcionará
            membersNotInActivities.add(m);
        }
    }
    public Set<Socio> inscritos(String actividad){
        return membersInActivities.getOrDefault(actividad.toLowerCase(), null);
        //puede que sea un pedazo de triple
    }
    protected Socio buscarSocioEnConjunto(Socio s, Set<Socio> ss){
        //no se puede hacer getOrdef??
        List<Socio> socios = new ArrayList<>(ss);
        if (!socios.contains(s)){
            return null;
        }else {
            return socios.get(socios.indexOf(s));
        }
    }
    public void inscribir (String nombre, int identificador, String actividad){
        Set<String> actividades = new HashSet<>(); //hay alguna otra forma?
        actividades.add(actividad);
        Socio socio = new Socio(nombre, actividades, identificador);
        if (buscarSocioEnConjunto(socio, membersNotInActivities) != null){
            Set<Socio> contenidos =
                    membersInActivities.getOrDefault(actividad.toLowerCase(), new HashSet<>());
            contenidos.add(socio);
            membersInActivities.put(actividad.toLowerCase(), contenidos); //idk
        }
    }
    public void guardarSocios(String fich){
        File file = new File(fich);
        try {
            guardarSocios(new PrintWriter(file));
        }catch (IOException e){
            throw new SociedadException("No se ha localizado el fichero");
        }
    }
    public void guardarSocios(PrintWriter pw){
        List<Socio> sociosNoIns = new ArrayList<>(membersNotInActivities);
        for (Socio socio : sociosNoIns){
            pw.println(socio); //ns si poner "\n"
        }
    }
}
