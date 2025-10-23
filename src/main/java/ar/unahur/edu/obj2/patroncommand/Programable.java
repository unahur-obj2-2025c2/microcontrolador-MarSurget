package ar.unahur.edu.obj2.patroncommand;

import java.util.List;

import ar.unahur.edu.obj2.patroncommand.operaciones.Operable;

public interface Programable { // aca describe q sabe hacer un microcontrolador, no  como lo hace internamente

    void run(List<Operable> operaciones);  // ejecuta una lista de operaciones

    void incProgramCounter(); // + el pc --> pasa a la siguiente instrucción

    Integer getProgramCounter();

    void setAcumuladorA(Integer value);

    Integer getAcumuladorA();

    void setAcumuladorB(Integer value);

    Integer getAcumuladorB();

    void setAddr(Integer addr);  //guarda un valor en memoria en la dirección(addr) q le diga 

    Integer getAddr(Integer addr);

    void reset();  // reinicia el microp pone los acumuladores, memoria y pc en estado inicial

}
