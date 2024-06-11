package sociedad;

import java.util.HashSet;
import java.util.Set;

public class SociedadLimitada extends Sociedad{
    int positionsLimit;
    public SociedadLimitada(int numeroDePlazas){
        super();
        if (numeroDePlazas > 0){
            positionsLimit = numeroDePlazas;
        }else {
            throw new SociedadException("Args no validos");
        }
    }
    @Override
    public void inscribir (String nombre, int identificador, String actividad){
        if (this.inscritos(actividad) == null || this.inscritos(actividad).size() < positionsLimit ){
            super.inscribir(nombre, identificador, actividad);
        }else {
            throw new SociedadException("No quedan plazas");
        }
    }

}
