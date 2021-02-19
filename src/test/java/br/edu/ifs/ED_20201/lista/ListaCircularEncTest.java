package br.edu.ifs.ED_20201.lista;

import org.junit.jupiter.api.BeforeEach;

class ListaCircularEncTest extends ListaTest {
    @BeforeEach
    public void setUp() {
        lista = new ListaCircularEnc<>(Integer.class);
    }
}