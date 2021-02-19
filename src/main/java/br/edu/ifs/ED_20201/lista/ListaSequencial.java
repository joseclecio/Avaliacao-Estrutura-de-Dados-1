package br.edu.ifs.ED_20201.lista;

public class ListaSequencial<T extends Comparable<T>> extends Lista<T>{

    private ListaSequencial<T> proxima;
    private T elemento;
    private ListaSequencial<T> anterior;

    private ListaSequencial<T> primeira;
    private ListaSequencial<T> ultima;

    private int totalDeElementos;

    /* Construtor */
    public ListaSequencial(ListaSequencial<T> proxima, T elemento) {
        this.proxima = proxima;
        this.elemento = elemento;
    }

    /* Construtor */
    public ListaSequencial(T elemento) {
        this.elemento = elemento;
    }

    /* Alterar o próximo elemento */
    public void setProxima(ListaSequencial<T> proxima) {
        this.proxima = proxima;
    }

    /* Recuperar o próximo elemento */
    public ListaSequencial<T> getProxima() {
        return proxima;
    }

    /* Recuperar o elemento */
    public T getElemento() {
        return elemento;
    }

    /* */
    public ListaSequencial<T> getAnterior() {
        return anterior;
    }

    /* */
    public void setAnterior(ListaSequencial<T> anterior) {
        this.anterior = anterior;
    }


    public ListaSequencial(Class<T> dataType){
        proxima=null;
        anterior=null;
        proxima = null;
        ultima=null;
        totalDeElementos=0;
    }


    @Override
    public void incluir(T elemento) throws Exception {

        if (this.totalDeElementos == 0) {
            this.incluirInicio(elemento);
        } else {
            ListaSequencial<T> nova = new ListaSequencial<T>(elemento);
            this.ultima.setProxima(nova);
            this.ultima = nova;
            this.totalDeElementos++;
        }

    }

    @Override
    public void incluirInicio(T elemento) throws Exception {

        this.primeira = new ListaSequencial<T>(this.primeira, elemento);
        if(this.totalDeElementos == 0){
            // caso especial da lista vazia
            this.ultima = this.primeira;
        }
        this.totalDeElementos++;

    }

    @Override
    public void incluir(T elemento, int posicao) throws Exception {

        if(posicao == 0){ // No começo.
            this.incluirInicio(elemento);
        } else if(posicao == this.totalDeElementos){ // No fim.
            this.incluir(elemento);
        } else {
            ListaSequencial<T> anterior = this.pegaCelula(posicao - 1);
            ListaSequencial<T> nova = new ListaSequencial<T>(anterior.getProxima(), elemento);
            anterior.setProxima(nova);
            this.totalDeElementos++;
        }

    }

    @Override
    public T get(int posicao) throws Exception {
        return this.pegaCelula(posicao).getElemento();

    }

    @Override
    public int getPosElemento(T elemento) throws Exception {
        for (int i = 0; i < getTamanho(); i++) {
            ListaSequencial<T> celulaAtual = pegaCelula(i);
            if (celulaAtual.getElemento() != null && celulaAtual.getElemento().equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remover(int posicao) throws Exception {

        if (!this.posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("Posição não existe");
        }
        if (posicao == 0) {
            this.removeDoComeco();
        } else if (posicao == this.totalDeElementos - 1) {
            this.removeDoFim();
        } else {
            ListaSequencial<T> anterior = this.pegaCelula(posicao - 1);
            ListaSequencial<T> atual = anterior.getProxima();
            ListaSequencial<T> proxima = atual.getProxima();
            anterior.setProxima(proxima);
            proxima.setAnterior(anterior);
            this.totalDeElementos--;
        }

    }

    @Override
    public void limpar() {
        totalDeElementos = 0;
        primeira = ultima = null;
    }

    @Override
    public int getTamanho() {
        return this.totalDeElementos;

    }

    @Override
    public boolean contem(T elemento) throws Exception {
        ListaSequencial<T> atual = this.primeira;
        while (atual != null) {
            if (atual.getElemento().equals(elemento)) {
                return true;
            }
            atual = atual.getProxima();
        }
        return false;
    }

    //---------------------------Implementação da lista a partir das Celulas

    private boolean posicaoOcupada(int posicao){
        return posicao >= 0 && posicao < this.totalDeElementos;
    }

    private ListaSequencial<T> pegaCelula(int posicao) {
        if(!this.posicaoOcupada(posicao)){
            throw new IllegalArgumentException("Posição não existe");
        }
        ListaSequencial<T> atual = primeira;
        for (int i = 0; i < posicao; i++) {
            atual = atual.getProxima();
        }
        return atual;
    }

    public void removeDoComeco() {
        if (!this.posicaoOcupada(0)) {
            throw new IllegalArgumentException("Posição não existe");
        }
        this.primeira = this.primeira.getProxima();
        this.totalDeElementos--;
        if (this.totalDeElementos == 0) {
            this.ultima = null;
        }
    }

    public void removeDoFim() {
        if (!this.posicaoOcupada(this.totalDeElementos - 1)) {
            throw new IllegalArgumentException("Posição não existe");
        }
        if (this.totalDeElementos == 1) {
            this.removeDoComeco();
        } else {
            ListaSequencial<T> penultima = this.ultima.getAnterior();
            penultima.setProxima(null);
            this.ultima = penultima;
            this.totalDeElementos--;
        }
    }

    /*
    //----------------------Aumentar tamanho
    private static Object aumentaTamanho (Object oldArray, int newSize) {
        int oldSize = java.lang.reflect.Array.getLength(oldArray);
        Class elementType = oldArray.getClass().getComponentType();
        Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
        int preserveLength = Math.min(oldSize, newSize);
        if (preserveLength > 0)
            System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
        return newArray;
    }

     */

}
