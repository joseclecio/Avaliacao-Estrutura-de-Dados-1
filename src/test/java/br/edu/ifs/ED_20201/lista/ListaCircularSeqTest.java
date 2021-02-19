package br.edu.ifs.ED_20201.lista;

import org.junit.jupiter.api.BeforeEach;

public class ListaCircularSeqTest extends ListaTest {
    @BeforeEach
    void setUp() {
        lista = new ListaCircularSeq<>(Integer.class);
    }

}
