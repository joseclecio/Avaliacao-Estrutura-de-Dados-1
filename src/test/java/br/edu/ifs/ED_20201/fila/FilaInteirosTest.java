package br.edu.ifs.ED_20201.fila;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.*;
public class FilaInteirosTest {

    FilaSequencial<Integer> fila;
    @BeforeEach
    public void setUp() {
        fila = new FilaInteiros();
    }

    @Test
    public void incluir_UmElemento() throws FilaCheiaException, FilaVaziaException {
        fila.incluir(1);
        assertThat(fila.getQtd()).isEqualTo(1);
        assertThat(fila.remover()).isEqualTo(1);
    }

    @Test
    public void incluir_MaisDeUmElemento() throws FilaCheiaException, FilaVaziaException {
        for(int i = 0; i < 5; i++){
            fila.incluir(i);
        }
        assertThat(fila.getQtd()).isEqualTo(5);
        assertThat(fila.remover()).isEqualTo(0);
        int get_qtd = fila.getQtd();
        for(int i = 0; i < get_qtd; i++){
            assertThat(fila.remover()).isEqualTo(i+1);
        }
        assertThat(fila.getQtd()).isZero();
    }

    @Test
    public void removerElementosAposFilaCheiaExceptionTest() throws FilaCheiaException, FilaVaziaException {
        for(int i = 0; i < fila.getQtdMaxSuportada(); i++){
            fila.incluir(i);
        }
        assertThatExceptionOfType(FilaCheiaException.class).isThrownBy(() -> fila.incluir(11));
        assertThat(fila.getQtd()).isEqualTo(fila.getQtdMaxSuportada());

        int get_qtd = fila.getQtd();
        for(int i = 0; i < get_qtd; i++){
            assertThat(fila.remover()).isEqualTo(i);
        }
        assertThat(fila.getQtd()).isZero();
    }

    @Test
    public void lancarFilaCheiaException() throws FilaCheiaException, FilaVaziaException {
        for(int i = 0; i < fila.getQtdMaxSuportada(); i++){
            fila.incluir(i);
        }
        assertThat(fila.getQtd()).isEqualTo(fila.getQtdMaxSuportada());
        assertThatExceptionOfType(FilaCheiaException.class).isThrownBy(() -> fila.incluir(11));
    }

    @Test
    public void incluirAposRemocaoElementosTest() throws FilaCheiaException, FilaVaziaException {
        for(int i = 0; i < fila.getQtdMaxSuportada(); i++){
            fila.incluir(i);
        }
        int get_qtd = fila.getQtd();

        for(int i = 0; i < (get_qtd / 2); i++){
            fila.remover();
        }

        assertThat(fila.getQtd()).isEqualTo(get_qtd/2);
        assertThat(fila.visualizarProximo()).isEqualTo(get_qtd/2);

        for(int i = 0; i < (get_qtd/2); i++){
            fila.incluir(i);
        }

        assertThat(fila.getQtd()).isEqualTo(get_qtd);
        int ultimoItem = (get_qtd/2);

        assertThat(fila.visualizarProximo()).isEqualTo(ultimoItem);

        get_qtd = fila.getQtd();
        for(int i = 0; i < (get_qtd/2); i++){
            assertThat(fila.remover()).isEqualTo(i+5);
        }

        assertThat(fila.visualizarProximo()).isEqualTo(0);
        assertThat(fila.getQtd()).isEqualTo(get_qtd/2);

        for(int i = 0; i < (get_qtd/2); i++){
            assertThat(fila.remover()).isEqualTo(i);
        }
        assertThat(fila.getQtd()).isZero();
    }

    @Test
    public void lancarFilaVaziaException() throws FilaCheiaException, FilaVaziaException {
        fila.incluir(1);
        fila.remover();
        assertThatExceptionOfType(FilaVaziaException.class).isThrownBy(() -> fila.remover());
        assertThat(fila.estaVazia()).isTrue();
        assertThat(fila.getQtd()).isZero();
    }

    @Test
    public void removerElementosTest() throws FilaCheiaException, FilaVaziaException {
        for(int i =0; i < 5; i++){
            fila.incluir(i);
        }
        for(int i = 0; i < 5; i++) {
            assertThat(fila.remover()).isEqualTo(i);
        }
        assertThat(fila.getQtd()).isZero();
    }

    @Test
    public void getQtd_FilaVaziaTest() {
        assertThat(fila.getQtd()).isEqualTo(0);
    }

    @Test
    public void estaVazia_FilaVaziaTest() {
        assertThat(fila.estaVazia()).isTrue();
    }

    @Test
    public void limpar_FilaComItensTest() throws FilaCheiaException {
        fila.incluir(1);
        fila.limpar();
        assertThat(fila.estaVazia()).isTrue();
        assertThat(fila.getQtd()).isZero();
    }

    @Test
    public void limpar_FilaVazia() {
        fila.limpar();
        assertThat(fila.estaVazia()).isTrue();
        assertThat(fila.getQtd()).isZero();
    }

    @Test
    void visualizarProximo() throws FilaCheiaException, FilaVaziaException {
        limpar_FilaVazia();
        for(int i = 0; i < 5; i++){
            fila.incluir(i);
        }
        assertThat(fila.getQtd()).isEqualTo(5);
        assertThat(fila.remover()).isEqualTo(0);
        assertThat(fila.visualizarProximo()).isEqualTo(1);
    }
}