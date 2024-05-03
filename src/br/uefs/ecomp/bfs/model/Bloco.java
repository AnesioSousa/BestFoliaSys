package br.uefs.ecomp.bfs.model;

import java.util.Objects;
import br.uefs.ecomp.bfs.util.*;

public class Bloco {
    private int id;
    private int saida;
    private String nome, local;
    private ListaEncadeada transportes;
    
    public Bloco(String nome, String local, int saida){
        this.nome = nome;
        this.local = local;
        this.saida = saida;
        transportes = new ListaEncadeada();
    }

    public int getId() {
        return id;
    }

    public int getSaida() {
        return saida;
    }

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public ListaEncadeada getTransportes() {
        return transportes;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setSaida(int saida) {
        this.saida = saida;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocal(String local) {
        this.local = local;
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
        final Bloco other = (Bloco) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.saida != other.saida) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        return true;
    }

  
    
    
}