package br.edu.ifs.ED_20201.lista;

public class ListaDup<T extends Comparable<T>> extends Lista<T> {

    private NoDup<T> primeiroNo;
    private NoDup<T> ultimoNo;
    private int tamanho;

    public ListaDup(Class<T> dataType){
        this.primeiroNo = null;
        this.ultimoNo = null;

    }

    /**
     * Adiciona um elemento no final da lista
     * O(1)
     *
     * @param elemento
     * @throws Exception
     */
    @Override
    public void incluir(T elemento) throws Exception {

        NoDup<T> novoNo = new NoDup<T>(elemento);
        if (estaVazia()) {
            this.primeiroNo = novoNo;
            this.ultimoNo = novoNo;
        } else {
            this.ultimoNo.setProx(novoNo);
            novoNo.setAnt(this.ultimoNo);
            this.ultimoNo = novoNo;
        }
        this.tamanho++;

    }

    /**
     * Adiciona um elemento no início da lista
     * O(n)
     *
     * @param elemento
     * @throws Exception
     */
    @Override
    public void incluirInicio(T elemento) throws Exception {
        incluir(elemento,0);

    }

    /**
     * Adiciona um elemento em uma posição específica da lista
     * O(n)
     *
     * @param elemento
     * @param posicao
     * @throws Exception
     */
    @Override
    public void incluir(T elemento, int posicao) throws Exception {

        //Após fazer o método pra aumentar a capacidade, irei remover o comentário
        /*
        if (posicao >= tamanho()) {
            throw new IllegalArgumentException(String.format("Posição inválida [%d]", posicao));
        }
         */

        if (posicao == 0) {
            NoDup<T> novoNo = new NoDup<>(elemento);
            novoNo.setProx(this.primeiroNo);
            //this.primeiroNo.setAnterior(novoNo);
            this.primeiroNo = novoNo;
        } else if (posicao == this.getTamanho() - 1) {
            NoDup<T> novoNo = new NoDup<T>(elemento);
            this.ultimoNo.setProx(novoNo);
            novoNo.setAnt(this.ultimoNo);
            this.ultimoNo = novoNo;
        } else {
            NoDup<T> noAnterior = recuperarNo(posicao - 1);
            NoDup<T> noAtual = recuperarNo(posicao);
            NoDup<T> novoNo = new NoDup<T>(elemento);
            noAnterior.setProx(novoNo);
            novoNo.setProx(noAtual);
            noAtual.setAnt(novoNo);
            novoNo.setAnt(noAnterior);
        }
        this.tamanho++;

    }

    /**
     * Retorna o elemento que está na posição
     * O(1)
     *
     * @param posicao
     * @return
     * @throws Exception
     */
    @Override
    public T get(int posicao) throws Exception {
        NoDup<T> no = recuperarNo(posicao);
        if (no != null) {
            return no.getDado();
        }
        return null;
    }

    /**
     * Retorna a posição do elemento
     * O(n)
     *
     * @param elemento
     * @return
     * @throws Exception
     */
    @Override
    public int getPosElemento(T elemento) throws Exception {
        for (int i = 0; i < getTamanho(); i++) {
            NoDup<T> noAtual = recuperarNo(i);
            if (noAtual.getDado() != null && noAtual.getDado().equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Remove o elemento da posição
     * O(n)
     *
     * @param posicao
     * @throws Exception
     */
    @Override
    public void remover(int posicao) throws Exception {

        if (posicao >= getTamanho()) {
            throw new IllegalArgumentException(String.format("Posição inválida [%d]", posicao));
        }
        if (posicao == 0) {
            NoDup<T> proximoNo = this.primeiroNo.getProx();
            this.primeiroNo.setProx(null);
            this.primeiroNo.setAnt(null);
            this.primeiroNo = proximoNo;
        } else if (posicao == getTamanho() - 1) {
            NoDup<T> penultimoNo = this.ultimoNo.setAnt();
            penultimoNo.setProx(null);
            this.ultimoNo.setAnt(null);
            this.ultimoNo = penultimoNo;
        } else {
            NoDup<T> noAnterior = recuperarNo(posicao - 1);
            NoDup<T> proximoNo = recuperarNo(posicao + 1);
            NoDup<T> noAtual = recuperarNo(posicao);
            noAnterior.setProx(proximoNo);
            proximoNo.setAnt(noAnterior);
            noAtual.setProx(null);
            noAtual.setAnt(null);
        }
        this.tamanho--;

    }

    /**
     * Remove todos os elementos da lista.
     * O(1)
     */
    @Override
    public void limpar() {

        tamanho = 0;
        primeiroNo = ultimoNo = null;



    }

    /**
     * Retorna a quantidade de elementos na lista
     * O(1)
     *
     * @return
     */
    @Override
    public int getTamanho() {
        return this.tamanho;

    }

    /**
     * Indica se contém ou não o elemento na lista
     * O(n)
     *
     * @param elemento
     * @return
     * @throws Exception
     */
    @Override
    public boolean contem(T elemento) throws Exception {
        for (int i = 0; i < getTamanho(); i++) {
            NoDup<T> noAtual = recuperarNo(i);
            if (noAtual.getDado() != null && noAtual.getDado().equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    public boolean estaVazia() {
        return this.tamanho == 0;
    }

    private NoDup<T> recuperarNo(int posicao) {
        if (posicao >= getTamanho()) {
            throw new IllegalArgumentException(String.format("Posição inválida [%d]", posicao));
        }
        NoDup<T> resultado = null;
        for (int i = 0; i <= posicao; i++) {
            if (i == 0) {
                resultado = this.primeiroNo;
            } else {
                resultado = resultado.getProx();
            }
        }
        return resultado;
    }
}
