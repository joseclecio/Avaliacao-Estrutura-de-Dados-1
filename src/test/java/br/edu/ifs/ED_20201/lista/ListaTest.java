package br.edu.ifs.ED_20201.lista;

import br.edu.ifs.ED_20201.fila.FilaCheiaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

abstract class ListaTest {

    Lista lista;

    @Test
    void incluirNaListaVazia() throws Exception {
        lista.incluir(1);
        assertThat(lista.getTamanho()).isOne();
        assertThat(lista.get(0)).isEqualTo(1);
    }

    @Test
    void limparTest() throws Exception {
        int qtd = 5;
        for (int i = 0; i < qtd; i++) {
            lista.incluirInicio(i);
        }
        assertThat(lista.getTamanho()).isNotZero();
        lista.limpar();
        assertThat(lista.getTamanho()).isZero();
    }

    @Test
    void getPosicaoMaiorQueQtd() throws Exception{
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> lista.get(11))
                .withMessage("Posição solicitada não existe na lista");
        lista.incluir(11);
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> lista.get(11))
                .withMessage("Posição solicitada não existe na lista");
    }
    @Test
    void incluirInicio() throws Exception {
        int qtd = 5;
        for (int i = 0; i < qtd; i++) {
            lista.incluirInicio(i);
        }
        assertThat(lista.getTamanho()).isEqualTo(qtd);
        assertThat(lista.get(0)).isEqualTo(4);
        assertThat(lista.get(4)).isEqualTo(0);
    }

    @Test
    void testIncluir() throws Exception {
        int qtd = 5;
        for (int i = 0; i < qtd; i++) {
            lista.incluir(i);
        }
        assertThat(lista.getTamanho()).isEqualTo(qtd);
        assertThat(lista.get(0)).isEqualTo(0);
        assertThat(lista.get(4)).isEqualTo(4);
    }

    @Test
    void getPorPosicaoElementoTest() throws Exception {
        int qtd = 5;
        for (int i = 0; i < qtd; i++) {
            lista.incluir(i);
        }
        for (int i = 0; i < qtd; i++) {
            assertThat(lista.get(i)).isEqualTo(i);
        }
    }
    @Test
    void getPorComparacaoElementoTest() throws Exception {
        Integer[] valores = new Integer[]{1,2,3,4,5};
        int qtd = valores.length;

        for (int i = 0; i < qtd; i++) {
            lista.incluir(valores[i]);
        }

        for (int i = 0; i < qtd; i++) {
            assertThat(lista.getPosElemento(valores[i])).isEqualTo(i);
        }
    }

    @Test
    void remover() throws Exception {
        int[] valores = new int[]{1,2,3,4,5};
        int qtd = valores.length;

        for (int i = 0; i < qtd; i++) {
            lista.incluir(valores[i]);
        }
        assertThat(lista.getTamanho()).isEqualTo(5);
        lista.remover(3); // remove o elemento 4
        assertThat(lista.getTamanho()).isEqualTo(4);
        assertThat(lista.get(2)).isEqualTo(3);
        assertThat(lista.get(3)).isEqualTo(5);
    }
    @Test
    public void getTamanhoNaInclusao() throws Exception {
        int[] valores = new int[]{1,2,3,4,5};
        int qtd = valores.length;
        for (int i = 0; i < qtd; i++) {
            lista.incluir(valores[i]);
            assertThat(lista.getTamanho()).isEqualTo(i+1);
        }
    }
    @Test
    public void getTamanhoNaRemocao() throws Exception {
        int[] valores = new int[]{1,2,3,4,5};
        int qtd = valores.length;
        for (int i = 0; i < qtd; i++) {
            lista.incluir(valores[i]);
        }
        for (int i = 0; i < qtd; i++) {
            lista.remover(0);
            assertThat(lista.getTamanho()).isEqualTo(4-i);
        }
    }
    @Test
    public void contem() throws Exception {
        int[] valores = new int[]{1,2,3,4,5};
        int qtd = valores.length;
        assertThat(lista.contem(1)).isFalse();
        for (int i = 0; i < qtd; i++) {
            lista.incluir(valores[i]);
            assertThat(lista.getTamanho()).isEqualTo(i+1);
        }
        for (int i = 0; i < qtd; i++) {
            assertThat(lista.contem(valores[i])).isTrue();
        }
        assertThat(lista.contem(10)).isFalse();
    }
}