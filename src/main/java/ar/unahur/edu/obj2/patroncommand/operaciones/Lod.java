package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.Programable;

public class Lod implements Operable{
    private Integer addr;

    public Lod(Integer addr) {
        this.addr = addr;
    }

    @Override
    public void execute(Programable micro) {
        Integer valor = micro.getAddr(addr);
        micro.setAcumuladorA(valor);
    }
    
}
