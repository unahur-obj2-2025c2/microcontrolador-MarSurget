package ar.unahur.edu.obj2.patroncommand.invoker;

import java.util.ArrayList;
import java.util.List;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;
import ar.unahur.edu.obj2.patroncommand.operaciones.Operable;

public class Programa {
    List<Operable> operaciones ;

    public Programa(List<Operable> operaciones) {
        this.operaciones = new ArrayList<>();
    }

    public void agregarOp(Operable unaOperacion){
        operaciones.add(unaOperacion);
    }

    public void vaciarLista(){
        operaciones.clear();;
    }

    public void ejecutar(Programable micro){
        micro.run(operaciones);
    }

    public void resetar(Programable micro){
        micro.reset();;
    }

    public void agregarOperaciones(List<Operable> oper){
        operaciones.addAll(oper);
    }
    
}
