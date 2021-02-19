package br.edu.ifs.ED_20201.lista;

import org.junit.jupiter.api.BeforeEach;

public class ListaSequencialTest extends ListaTest {
    @BeforeEach
    void setUp() {
        lista = new ListaSequencial<>(Integer.class);
    }

}
