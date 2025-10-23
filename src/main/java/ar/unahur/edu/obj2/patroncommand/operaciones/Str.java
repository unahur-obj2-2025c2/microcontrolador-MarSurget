package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.Programable;

public class Str implements Operable {    
    private Integer addr;

    public Str(Integer addr) {
        this.addr = addr;
    }

    @Override
    public void execute(Programable micro) {
        micro.setAddr(addr);
    }
    
}
