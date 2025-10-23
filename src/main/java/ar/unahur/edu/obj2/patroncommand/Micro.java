package ar.unahur.edu.obj2.patroncommand;

import java.util.List;

import ar.unahur.edu.obj2.patroncommand.operaciones.Operable;

public class Micro implements Programable { // simulador 
    private Integer a; // Dos acumuladores de tipo Integer, identificados como A y B.
    private Integer b;
    private Integer pc = 0; // indica la dirección en la memoria de programa de la próxima instrucción a ejecutar.
                            // Se incrementa cada vez que el microcontrolador ejecuta una instrucción.
    private Integer[] memoria; // Un área de 1024 valores enteros correspondiente a la memoria de datos. -- direcciones --> addr

    public Micro() {  // el micro se inicialice vacío
        this.a = 0;
        this.b = 0;
        this.pc = 0;
        this.memoria = new Integer[1024];
    }


    @Override
    public Integer getAcumuladorA() {
        return a;
    }
    @Override
    public Integer getAcumuladorB() {
        return b;
    }
    @Override
    public Integer getAddr(Integer addr) {
        if (addr < 0 || addr >= memoria.length) {
        // armar excepcion 
        throw new IllegalArgumentException("Direccion fuera de rango");
        }
        return memoria[addr];
    }
    @Override
    public Integer getProgramCounter() {
        return pc;
    }
    @Override
    public void incProgramCounter() {
        pc++;
    }
    @Override
    public void reset() {
        pc = 0;
        a = 0;
        b = 0;

        // inicializa el contador -> int i = 0
        // es la condición para que siga el bucle --> i < memoria.length
        // incrementa i en 1 cada vez. --> i++
        for (int i = 0; i < memoria.length; i++) {
            memoria[i] = 0;
        }


    }
    @Override
    public void run(List<Operable> operaciones) {
        while (pc < operaciones.size()){  // los índices de listas van de 0 a size() - 1 por eso no uso <=
            try{
                operaciones.get(pc).execute(this);
                pc++;
            }catch(Exception e){
                System.out.println("El eeror en la instruccion en pc = " + pc + ": " + e.getMessage());
                break;
            }
            }
    }
    @Override
    public void setAcumuladorA(Integer value) {
        a = value;        
    }
    @Override
    public void setAcumuladorB(Integer value) {
        b = value;        
        
    }
    @Override
    public void setAddr(Integer addr) {  // STR addr | Guarda el valor del acumulador A en la posición addr de la memoria de datos.
        if (addr < 0 || addr >= memoria.length) {
        // armar excepcion 
        throw new IllegalArgumentException("Direccion fuera de rango");
        }
        memoria[addr]= a; // lo armo asi por la utilidad en la operacion STR
    }


}
