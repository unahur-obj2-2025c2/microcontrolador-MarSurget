package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public class Nop extends Comando{

    @Override
    public void doExecute(Programable micro) { 
         // no incremento pc aca porque ya lo hace el super
    }

}
