package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public abstract class Comando implements Operable{  
    // implementamos esto para usar template method ya que todos incrmentan pc pero hacen otras operaciones

    Programable microBackUp;

    @Override
    public void execute(Programable micro) {
        microBackUp = micro.copiar();
        this.doExecute(micro);  // este orden es importante por el requerimiento 
        micro.incProgramCounter();
    }

    protected abstract void doExecute(Programable micro);

    @Override
    public void unDo(Programable micro) {
        micro.copiarDesde(microBackUp);
    }
    
}
