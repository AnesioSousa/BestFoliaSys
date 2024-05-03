package br.uefs.ecomp.bfs.model;


import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Testes de unidade para a classe {@link Bloco}
 */
public class BlocoTest {

	private Bloco b;	
	
	/**
     * Este método é executado antes de cada teste de unidade (testes a seguir), 
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @BeforeEach
    public void setUp() throws Exception {
    	b = new Bloco("Bafo", "Feira de Santana - Presidente Dutra", 10);
    }

    /**
     * Teste de unidade que verifica se os atributos de um bloco são atribuidos e 
     * modificados corretamente. Além disso, ele checa se o método equals que 
     * compara dois blocos foi implementado corretamente.
     */
    @Test
    public void testBasic() {
        assertEquals("Bafo", b.getNome());
        assertEquals("Feira de Santana - Presidente Dutra", b.getLocal());
        assertEquals(10, b.getSaida());
        
        b.setNome("Dormir é o Melhor Remédio");
        b.setLocal("Feira de Santana");        
        b.setSaida(15);
        assertEquals("Dormir é o Melhor Remédio", b.getNome());
        assertEquals("Feira de Santana", b.getLocal());
        assertEquals(15, b.getSaida());
        
        Bloco temp = new Bloco("Dormir é o Melhor Remédio", "Feira de Santana", 15);
        assertTrue(temp.equals(b));
        
        b.setNome("XYZ");        
        assertFalse(temp.equals(b));
        
        b.setLocal("Recife - Marco Zero");
        b.setNome("Dormir é o Melhor Remédio");        
        assertFalse(temp.equals(b));
        
        b.setSaida(20);  
        b.setLocal("Feira de Santana");              
        assertFalse(temp.equals(b));
    }
    
}
