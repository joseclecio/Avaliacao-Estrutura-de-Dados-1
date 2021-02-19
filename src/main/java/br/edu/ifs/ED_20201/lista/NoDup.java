package br.edu.ifs.ED_20201.lista;

public class NoDup<T> {

    private T dado;
    private NoDup<T> prox;
    private NoDup<T> ant;

    public NoDup(T dado) {
        this.dado = dado;
        this.prox = null;
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    public NoDup<T> getProx() {
        return prox;
    }

    public void setProx(NoDup<T> prox) {
        this.prox = prox;
    }

    public void setAnt(NoDup<T> ant) {
        this.ant = ant;
    }

    public NoDup<T> setAnt() {
        return ant;
    }
}
