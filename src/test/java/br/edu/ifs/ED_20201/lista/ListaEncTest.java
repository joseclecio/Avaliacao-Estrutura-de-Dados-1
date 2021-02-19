package br.edu.ifs.ED_20201.lista;

import br.edu.ifs.ED_20201.fila.FilaInteiros;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ListaEncTest extends ListaTest {
    @BeforeEach
    public void setUp() {
        lista = new ListaEnc<>(Integer.class);
    }
}