package br.edu.ifs.ED_20201.lista;

public class ListaEnc<T extends Comparable<T>> extends Lista<T> {

    private No<T> primeiroNo;
    private No<T> ultimoNo;
    private int tamanho;

    public ListaEnc(Class<T> dataType){
        this.primeiroNo = null;
        this.ultimoNo = null;

    }


    @Override
    public void incluir(T elemento) throws Exception {
        No<T> novoNo = new No<>(elemento);
        if (estaVazia()) {
            this.primeiroNo = novoNo;
        } else {
            this.ultimoNo.setProx(novoNo);
        }
        this.ultimoNo = novoNo;
        this.tamanho++;
    }

    @Override
    public void incluirInicio(T elemento) throws Exception {
        incluir(elemento,0);

    }

    @Override
    public void incluir(T elemento, int posicao) throws Exception {
        //Após fazer o método pra aumentar a capacidade, irei remover o comentário
        /*
        if (posicao >= tamanho()) {
            throw new IllegalArgumentException(String.format("Posição inválida [%d]", posicao));
        }
         */

        if (posicao == 0) {
            No<T> novoNo = new No<T>(elemento);
            novoNo.setProx(this.primeiroNo);
            this.primeiroNo = novoNo;
        } else if (posicao == this.getTamanho() - 1) {
            No<T> novoNo = new No<T>(elemento);
            this.ultimoNo.setProx(novoNo);
            this.ultimoNo = novoNo;
        } else {
            No<T> noAnterior = recuperarNo(posicao - 1);
            No<T> noAtual = recuperarNo(posicao);
            No<T> novoNo = new No<T>(elemento);
            noAnterior.setProx(novoNo);
            novoNo.setProx(noAtual);
        }
        this.tamanho++;
    }

    @Override
    public T get(int posicao) throws Exception {
        No<T> no = recuperarNo(posicao);
        if (no != null) {
            return no.getDado();
        }
        return null;
    }

    @Override
    public int getPosElemento(T elemento) throws Exception {
        for (int i = 0; i < getTamanho(); i++) {
            No<T> noAtual = recuperarNo(i);
            if (noAtual.getDado() != null && noAtual.getDado().equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remover(int posicao) throws Exception {
        if (posicao >= getTamanho()) {
            throw new IllegalArgumentException(String.format("Posição inválida [%d]", posicao));
        }
        if (posicao == 0) {
            No<T> proximoNo = this.primeiroNo.getProx();
            this.primeiroNo.setProx(null);
            this.primeiroNo = proximoNo;
        } else if (posicao == getTamanho() - 1) {
            No<T> penultimoNo = recuperarNo(getTamanho() - 2);
            penultimoNo.setProx(null);
            this.ultimoNo = penultimoNo;
        } else {
            No<T> noAnterior = recuperarNo(posicao - 1);
            No<T> proximoNo = recuperarNo(posicao + 1);
            No<T> noAtual = recuperarNo(posicao);
            noAnterior.setProx(proximoNo);
            noAtual.setProx(null);
        }
        this.tamanho--;
    }

    @Override
    public void limpar() {
        tamanho = 0;
        primeiroNo = ultimoNo = null;
    }

    @Override
    public int getTamanho() {
        return this.tamanho;
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

    public boolean estaVazia() {
        return this.tamanho == 0;
    }

    private No<T> recuperarNo(int posicao) {
        if (posicao >= getTamanho()) {
            throw new IllegalArgumentException(String.format("Posição inválida [%d]", posicao));
        }
        No<T> resultado = null;
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
