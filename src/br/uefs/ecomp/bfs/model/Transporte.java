package br.uefs.ecomp.bfs.model;

import java.util.Objects;
import br.uefs.ecomp.bfs.util.*;

public class Transporte {
    private int id; 
    private int capacidade, saida, retorno, chegada;
    private String nome, tipo, localSaida, localRetorno, localChegada;
    private double valor;
    private Bloco bloco;
    private ListaEncadeada folioes;

    public Transporte(String nome, String tipo, double valor, int capacidade, int saida, String localSaida,  int retorno, String localRetorno,int chegada, String localChegada, Bloco bloco){
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.capacidade = capacidade;
        this.saida = saida;
        this.localSaida = localSaida;
        this.retorno = retorno;
        this.localRetorno = localRetorno;
        this.chegada = chegada;
        this.localChegada = localChegada;
        this.bloco = bloco;
        folioes = new ListaEncadeada();
    }

    public int getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getSaida() {
        return saida;
    }

    public int getRetorno() {
        return retorno;
    }

    public int getChegada() {
        return chegada;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLocalSaida() {
        return localSaida;
    }

    public String getLocalRetorno() {
        return localRetorno;
    }

    public String getLocalChegada() {
        return localChegada;
    }

    public double getValor() {
        return valor;
    }
    
    public Bloco getBloco() {
       return bloco;
    }

    public ListaEncadeada getFolioes() {
        return folioes;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setSaida(int saida) {
        this.saida = saida;
    }

    public void setRetorno(int retorno) {
        this.retorno = retorno;
    }

    public void setChegada(int chegada) {
        this.chegada = chegada;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLocalSaida(String localSaida) {
        this.localSaida = localSaida;
    }

    public void setLocalRetorno(String localRetorno) {
        this.localRetorno = localRetorno;
    }

    public void setLocalChegada(String localChegada) {
        this.localChegada = localChegada;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setBloco(Bloco bloco){
        this.bloco = bloco;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transporte other = (Transporte) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.capacidade != other.capacidade) {
            return false;
        }
        if (this.saida != other.saida) {
            return false;
        }
        if (this.retorno != other.retorno) {
            return false;
        }
        if (this.chegada != other.chegada) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.localSaida, other.localSaida)) {
            return false;
        }
        if (!Objects.equals(this.localRetorno, other.localRetorno)) {
            return false;
        }
        if (!Objects.equals(this.localChegada, other.localChegada)) {
            return false;
        }
        if (!Objects.equals(this.bloco, other.bloco)) {
            return false;
        }
        return true;
    }
    
    
}
