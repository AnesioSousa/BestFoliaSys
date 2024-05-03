package br.uefs.ecomp.bfs.controller;

import br.uefs.ecomp.bfs.model.*;
import br.uefs.ecomp.bfs.util.*;

public class ControllerBFS {

    private ListaEncadeada blocos = new ListaEncadeada();
    private ListaEncadeada folioes = new ListaEncadeada(); // Lista de foliões em espera de um transporte
    private int idTransportesCount = 0; // Foi necessário criar esse contador aqui por que não tem uma lista de transportes do modo que tem uma de blocos e folioes aqui.
    // Se eu setasse o id do transporte da mesma forma que setei o id de blocos, cada lista de transportes de cada bloco teria 0, 1 , 2... por que no bloco eu uso o tamanho da lista
    // pra setar o id. Isso seria um grande problema para o método obter transporte, que ao receber um id não saberia qual retornar já que poderiam ter vários transportes com id 0, 1 , 2...

    // Método que insere um bloco na lista de blocos, caso ele já não esteja cadastrado.
    public Bloco cadastrarBloco(String nome, String local, int saida) {

        if (blocos.estaVazia()) {
            Bloco bloco = new Bloco(nome, local, saida);
            bloco.setId(blocos.tamanho());
            blocos.insereInicio(bloco);

            return bloco;
        } else {
            Iterador itr = blocos.iterador();
            Bloco aux;

            while (itr.temProximo()) {
                aux = (Bloco) itr.proximo();
                if (aux.getNome().equals(nome) && aux.getLocal().equals(local) && aux.getSaida() == saida) { // Se tiver um bloco já cadastrado com esses dados, retorna null.
                    return null;
                }
            }
        }
        Bloco bloco = new Bloco(nome, local, saida);
        bloco.setId(blocos.tamanho());
        blocos.insereFinal(bloco);

        return bloco;
    }

    // Método que retorna um bloco se baseando no id passado.
    public Bloco obterBloco(int id) {
        Iterador itr = blocos.iterador();
        Bloco aux;

        while (itr.temProximo()) {
            aux = (Bloco) itr.proximo();

            if (aux.getId() == id) {
                return aux;
            }
        }
        return null;
    }

    public Iterador listarBlocos(String local, int dataInicio, int dataFim) {
        Iterador itr1 = blocos.iterador(); // É criado um iterador pra auxiliar o percorrer da lista de blocos.
        Bloco aux1; // Esse auxiliar é utilizado para acessar as células da lista de blocos
        Transporte aux2; // Esse auxiliar é utilizado para acessar as células da lista de transportes de um bloco.

        ListaEncadeada paraRetorno = new ListaEncadeada(); // É criada uma lista que armazenara os blocos que tem pelo menos um transporte que se encaixe no local e período pedidos.

        // MODO 2: lista de blocos que tem pelo menos um transporte que está nessa faixa.
        while (itr1.temProximo()) { // Percorre a lista de blocos
            aux1 = (Bloco) itr1.proximo();
            
            ListaEncadeada acessoTranspBloco = aux1.getTransportes(); // Acesso para a lista de transportes do bloco
            Iterador itr2 = acessoTranspBloco.iterador(); // É criado um iterador para percorrer a lista de transportes de uma celula para tentar encontrar o transporte que é procurado.
            
            while (itr2.temProximo()) { // Percorre a lista de transportes de um bloco
                aux2 = (Transporte) itr2.proximo();
                
                if (aux2.getLocalChegada().equals(local) && aux2.getChegada() >= dataInicio && aux2.getRetorno() <= dataFim){  // Se achou pelo menos um transporte que esteja nessa faixa, copia o bloco pra lista de retorno, e pula para o proximo bloco.
                    paraRetorno.insereFinal(aux1); 
                    break; 
                }
            }
        }

        return paraRetorno.iterador(); // Aqui a lista é retornada.
    }

    // Método que insere um transporte na lista de transportes de um bloco, caso ele já não esteja na lista.
    public Transporte cadastrarTransporte(String nome, String tipo, double valor, int capacidade, int saida, String localSaida, int retorno, String localRetorno, int chegada, String localChegada, Bloco bloco) {
        ListaEncadeada recebeListaB = bloco.getTransportes();

        if (recebeListaB.estaVazia()) {
            Transporte transporte = new Transporte(nome, tipo, valor, capacidade, saida, localSaida, retorno, localRetorno, chegada, localChegada, bloco);
            transporte.setId(idTransportesCount);
            idTransportesCount++;
            recebeListaB.insereInicio(transporte);

            return transporte;
        } else {
            Iterador itr = recebeListaB.iterador();
            Transporte aux;

            while (itr.temProximo()) {
                aux = (Transporte) itr.proximo();
                // Se tiver um transporte já cadastrado com esses dados, retorna null.
                if (aux.getNome().equals(nome) && aux.getTipo().equals(tipo) && aux.getValor() == valor && aux.getCapacidade() == capacidade &&
                        aux.getSaida() == saida && aux.getLocalSaida().equals(localSaida) && aux.getRetorno() == retorno && aux.getLocalRetorno().equals(localRetorno) &&
                        aux.getChegada() == chegada && aux.getLocalChegada().equals(localChegada) && aux.getBloco().equals(bloco)) { 
                    return null;
                }
            }
        }
        Transporte transporte = new Transporte(nome, tipo, valor, capacidade, saida, localSaida, retorno, localRetorno, chegada, localChegada, bloco);
        transporte.setId(idTransportesCount);
        idTransportesCount++;
        recebeListaB.insereFinal(transporte);

        return transporte;
    }
    
    // Método que ao receber um id, retorna o transporte que tem esse id.
    public Transporte obterTransporte(int id) {
        Iterador itr1 = blocos.iterador(); // É criado um iterador pra auxiliar o percorrer da lista de blocos.
        Bloco aux1; // Esse auxiliar é utilizado para acessar as células da lista de blocos
        Transporte aux2; // Esse auxiliar é utilizado para acessar as células da lista de transportes de um bloco.
        
        while (itr1.temProximo()) { // Percorre a lista de blocos
            aux1 = (Bloco) itr1.proximo();
            ListaEncadeada acessoTranspBloco = aux1.getTransportes(); // Acesso para a lista de transportes do bloco
            Iterador itr2 = acessoTranspBloco.iterador(); // É criado um iterador para percorrer a lista de transportes de uma celula para tentar encontrar o transporte que é procurado.
            
            while (itr2.temProximo()) { // Percorre a lista de transportes de um bloco
                aux2 = (Transporte) itr2.proximo();
                if(aux2.getId() == id){ // Se for encontrado o transporte com o id passado, retorna ele.
                    return aux2;
                }
            }
        }
        
        return null; // Se não for encontrado transporte com o id passado, retorna nulo.
    }
    
    // Método que ao receber um bloco, retorna um iterador da lista de seus transportes.
    public Iterador listarTransportes(Bloco bloco) {
        ListaEncadeada aux = bloco.getTransportes();

        return aux.iterador();
    }

    // Método que insere um folião na lista de foliões, caso ele já não esteja cadastrado.
    public Foliao cadastrarFoliao(String cpf, String rg, String nome, int idade) {
        if (folioes.estaVazia()) {
            Foliao foliao = new Foliao(cpf, rg, nome, idade);
            folioes.insereInicio(foliao);

            return foliao;
        } else {
            Iterador itr = folioes.iterador();
            Foliao aux;

            while (itr.temProximo()) {
                aux = (Foliao) itr.proximo();
                if (aux.getCpf().equals(cpf)) { // Se tiver um folião já cadastrado com esse dado, retorna null.
                    return null;
                }
            }
        }
        Foliao foliao = new Foliao(cpf, rg, nome, idade);
        folioes.insereFinal(foliao);

        return foliao;
    }
    
    // Método que recebe um cpf e retorna o folião que tem esse cpf. 
    public Foliao obterFoliao(String cpf) {
        Iterador itr = folioes.iterador();
        Foliao aux;

        while (itr.temProximo()) {
            aux = (Foliao) itr.proximo();

            if (aux.getCpf().equals(cpf)) {
                return aux;
            }
        }
        return null;
    }

    // Método que insere um determinado folião na lista de foliões de um determinado transporte.
    // Se o folião já estiver cadastrado nesse transporte, retorna false. Se não, insere o folião da lista de foliões do transporte e retorna true.
    public boolean registrarFoliaoEmTransporte(Foliao foliao, Transporte transporte) {
        ListaEncadeada recebeListaF = transporte.getFolioes(); // É criado um auxiliar que vai "pegar" a lista de foliões do transporte.
        int qtdVagas = transporte.getCapacidade() - recebeListaF.tamanho(); // É criado um outro auxiliar que verifica se tem vagas no transporte.
        
        // Essa condição verifica se a lista está vazia. Se estiver, já insere o folião.
        if (recebeListaF.estaVazia()) {
            recebeListaF.insereInicio(foliao);
            
            return true;
        } else if (qtdVagas == 0) {// Se as vagas já estiverem todas ocupadas, não sera possível inserir um folião nesse transporte.

            return false; 
        } else { // Se ja tiver folioes no transporte, mas ainda ter vagas:
            Iterador itr = recebeListaF.iterador(); // Cria um iterador para auxiliar o percorrer da lista de foliões.
            Foliao aux;

            while (itr.temProximo()) { // Percorre a lista.
                aux = (Foliao) itr.proximo();
                if (aux.equals(foliao)) { // Se esse folião já estiver registrado nesse transporte retorna false.

                    return false;
                }
            }
        }
        // Aqui não precisa percorrer para inserir no final por que lá na classe "listaEncadeada" eu criei um indicador de último nó de lista.  
        recebeListaF.insereFinal(foliao); // Insere o folião.
        
        return true;
    }
    
    // Método que ao receber um transporte, retorna um iterador da lista de seus foliões
    public Iterador listarFolioes(Transporte transporte) {
        ListaEncadeada recebeLista = transporte.getFolioes();

        return recebeLista.iterador();
    }

    // Sem esses métodos get não seria possível acessar as listas de foliões e de blocos na classe ControllerBFSTest.
    public ListaEncadeada getBlocos() {
        return blocos;
    }

    public ListaEncadeada getFolioes() {
        return folioes;
    }

}
