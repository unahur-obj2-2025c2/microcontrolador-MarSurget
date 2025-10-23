package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.Programable;

public class Add implements Operable{

    @Override
    public void execute(Programable micro) {
        micro.setAcumuladorA(micro.getAcumuladorA() + micro.getAcumuladorB());
        micro.setAcumuladorB(0);
    }

}
