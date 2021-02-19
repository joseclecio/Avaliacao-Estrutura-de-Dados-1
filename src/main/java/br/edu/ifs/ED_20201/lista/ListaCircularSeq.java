package br.edu.ifs.ED_20201.lista;

public class ListaCircularSeq<T extends Comparable<T>> extends Lista<T>{

    private No<T> head;
    private int size;


    public ListaCircularSeq(Class<T> dataType){
        head=null;
        size=0;

    }

    @Override
    public void incluir(T elemento) throws Exception {

        No<T> newNode = new No<T>(elemento);
        if (null == head) {
            /* When list is empty */
            head = newNode;
        } else {
            No<T> temp = head;
            while (temp.prox != head) {
                temp = temp.prox;
            }
            temp.prox = newNode;
        }
        newNode.prox = head;
        size++;

    }

    @Override
    public void incluirInicio(T elemento) throws Exception {

        No<T> newNode = new No<T>(elemento);
        if (head == null) {
            head = newNode;
            head.prox = head;
        } else {
            newNode.prox = head;
            head = newNode;
        }
        size++;

    }

    @Override
    public void incluir(T elemento, int posicao) throws Exception {

        if (posicao < 0 || posicao > size) {
            throw new IllegalArgumentException("Posicao inválida");
        }
        /* Conditions check passed, let's insert the node */
        No<T> newNode = new No<T>(elemento);
        No<T> tempNode = head;
        No<T> prevNode = null;
        for (int i = 0; i < posicao; i++) {
            if (tempNode.prox == head) {
                break;
            }
            prevNode = tempNode;
            tempNode = tempNode.prox;
        }
        assert prevNode != null;
        prevNode.prox = newNode;
        newNode.prox = tempNode;
        size++;

    }

    @Override
    public T get(int posicao) throws Exception {
        if(posicao>=0 && posicao< size){
            if (posicao == 0) {
                return head.getDado();
            }else{
                No<T> aux = head;
                for (int i = 0; i < posicao; i++) {
                    aux = aux.setProx();
                }
                return aux.getDado();
            }
        } else {
            throw new Exception("Posicao inexistente na lista.");
        }
    }

    @Override
    public int getPosElemento(T elemento) throws Exception {
        if (contem(elemento)) {
            No<T> aux = head;
            int cont = 0;
            while(elemento != aux.getDado()){
                cont ++;
                aux = aux.setProx();
            }
            return cont;
        } else {
            throw new Exception("Valor inexistente na lista.");
        }
    }

    @Override
    public void remover(int posicao) throws Exception {

        if (posicao < 0 || posicao >= size) {
            throw new IllegalArgumentException("Posicao inválida");
        }
        No<T> current = head, previous = head;
        for (int i = 0; i < posicao; i++) {
            if (current.prox == head) {
                break;
            }
            previous = current;
            current = current.prox;
        }
        if (posicao == 0) {
            deleteFromBeginning();
        } else {
            previous.prox = current.prox;
            size--;
        }


    }

    @Override
    public void limpar() {
        head = null;
        size = 0;
    }

    @Override
    public int getTamanho() {
        return size;

    }

    @Override
    public boolean contem(T elemento) throws Exception {
        for (int i = 0; i < getTamanho(); i++) {
            No<T> noAtual = recuperarNo(i);
            if (noAtual.getDado() != null && noAtual.getDado().equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    public void deleteFromBeginning() {
        No<T> temp = head;
        while (temp.prox != head) {
            temp = temp.prox;
        }
        temp.prox = head.prox;
        head = head.prox;
        size--;
    }


    //classe auxiliar para recuperar o no
    private No<T> recuperarNo(int posicao) {
        if (posicao >= getTamanho()) {
            throw new IllegalArgumentException(String.format("Posição inválida [%d]", posicao));
        }
        No<T> resultado = null;
        for (int i = 0; i <= posicao; i++) {
            if (i == 0) {
                resultado = this.head;
            } else {
                resultado = resultado.setProx();
            }
        }
        return resultado;
    }


}
