package br.uefs.ecomp.bfs.util;


public class ListaEncadeada implements ILista {

    // Foram criados dois auxiliares do tipo No, para uma melhor manipuação da lista. O chamado "primeiro" indica o primeiro item da lista,
    // e o chamado "último" indica o último item da lista.
    private No primeiro;
    private No ultimo;
    private int qtdDeNos; // Contador de nós da lista, toda vez que um nó é criado e adicionado a lista ele incrementa, e toda vez que um nó é removido da lista, ele decrementa.
    
    // Classe utilizada para criação de nós(células), de lista.
    private class No {

        private Object info; // Informação sobre o nó.
        private No prox; // Indicador de próximo item.

        public No(Object info) {
            this.info = info;
        }

        public No getProx() {
            return prox;
        }

        public void setProx(No prox) {
            this.prox = prox;
        }

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }

    }

    // Método que retorna true se a lista estiver vazia, e false se não.
    @Override
    public boolean estaVazia() {
        return this.qtdDeNos == 0;
    }
    
    // Método que retorna o tamanho da lista.
    @Override
    public int tamanho() {
        return this.qtdDeNos;
    }
    
    // Método que insere um nó no inicio da lista.
    @Override
    public void insereInicio(Object o) {
        No novo = new No(o); // Cria um novo nó.
        novo.setProx(this.primeiro); // Faz esse novo nó apontar para o atual primeiro nó da lista.
        this.primeiro = novo; // Seta o novo nó como o primeiro nó da lista.

        if (estaVazia()) { // Se a lista estiver vazia, o primeiro nó recebe o identificador de primeiro e último.
            this.ultimo = this.primeiro; // Como só tem um nó na lista, ele é o primeiro e o último.
        }
        qtdDeNos++;
    }
    
    // Método que insere um nó no fim da lista.
    @Override
    public void insereFinal(Object o) {
        if (estaVazia()) { // Se a lista estiver vazia, chama o método para inserir um novo nó no inicio, 
            this.insereInicio(o); // por que nesse caso o inicio seria também o final, por ele ser o único nó da lista.                                                                                                 
        } else {
            No novo = new No(o); // Cria um nó.
            novo.setProx(null); // Aponta esse novo nó para nulo, ja que será o novo último nó da lista.
            this.ultimo.setProx(novo); // O antigo último nó da lista aponta para o novo último nó.
            this.ultimo = novo; // O auxiliar indicador do último nó da lista passa a indicar esse novo nó como o último da lista.
            this.qtdDeNos++;
        }
    }
    
    // Método que remove um nó do inicio da lista.
    @Override
    public Object removeInicio() {
        if (estaVazia()){ // Se a lista estiver vazia retorna null.
            return null;
        }

        No removido = this.primeiro; // Cria um auxiliar, que indica o nó que será removido.
        this.primeiro = primeiro.getProx(); // Faz indicador de primeiro pular do atual primeiro para o novo primeiro.
        this.qtdDeNos--;

        if (estaVazia()) {   // Esse if é pra dizer que se o nó que foi removido era o único nó da lista, 
            this.ultimo = null; // o indicador de último nó recebe null.
        }

        return removido.getInfo(); // Retorna o nó removido.
    }
    
    // Método que remove um nó no final da lista.
    @Override
    public Object removeUltimo() {
        No penultimo; // Cria um auxiliar que ira percorrer a lista até que ele indique o penúltimo item da lista.
        No last = this.ultimo; // Cria outro auxiliar que fica posicionado no último nó da lista(o nó que será removido).
        switch (this.qtdDeNos) {
            case 0: // Se a lista estiver vazia,
                return null; // retorna nulo.
            case 1: // Se a lista tiver um item,
                removeInicio(); // chama o método de remoção no início, já que se na lista só existe um nó, ele é o último e o primeiro.
                break;
            default: // Se não estiver vazia, e tiver mais de um item:
                penultimo = this.primeiro; // o auxiliar vai pro inicio da lista, 
                while (penultimo.getProx() != last) { // e percorre até ele ficar posicionado no penultimo item da lista.
                    penultimo = penultimo.getProx();
                }
                penultimo.setProx(null); // Já que o penúltimo será a partir de agora o novo último, não tera uma proxima célula. Sendo assim, a seu próximo é atribuido nulo.
                this.ultimo = penultimo; // O indicador de último nó passa a indicar o novo último nó.
                this.qtdDeNos--;
                break;
        }
        return last.getInfo(); // É retornada a célula removida(antigo último nó).
    }
    
    // Método que retorna um objeto indicado pelo index passado.
    @Override
    public Object recupera(int index) {
        int contador = 0;
        
        if (estaVazia()) // Se a lista estiver vazia, retorna nulo ao chamador.
            return null;
        
        if(index < 0 || index > this.qtdDeNos){ // Se o index passado for menor que zero ou maior que a quantidade de elementos, retorna nulo ao chamador.
            return null;
        }
        
        Iterador runner = this.iterador(); // Aqui é criada uma instância do iterador. Nele não é usado o new por que o iterador depende da estrutura a ser percorrida, esse "this" é pra dizer que o que ele vai percorrer é essa lista.
        Object aux = runner.proximo();
        while(runner.temProximo() && contador != index){ // Esse laço percorre a lista atrás do objeto que está no index passado.
            aux = runner.proximo();
            contador++;
        }

        return aux; // Após encontrar, retorna o objeto.
    }
    
    // Classe que implementa a interface Iterador.
    private class Cursor implements Iterador {

        private No andador = primeiro;

        @Override
        public boolean temProximo() {
            return andador != null;
        }

        @Override
        public Object proximo() {
            No capta = andador;
            andador = andador.getProx();

            return capta.getInfo();
        }
    }
    
    // Método que quando chamado, retorna um novo iterador.
    @Override
    public Iterador iterador() {
        return new Cursor();
    }

}
