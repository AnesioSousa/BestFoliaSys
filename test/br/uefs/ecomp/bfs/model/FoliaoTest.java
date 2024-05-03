package br.uefs.ecomp.bfs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testes de unidade para a classe {@link Foliao}
 */
public class FoliaoTest {

	private Foliao f;
	
	
	/**
     * Este método é executado antes de cada teste de unidade (testes a seguir), 
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @BeforeEach
    public void setUp() throws Exception {
        f = new Foliao("012345678-90", "666666 SSP/BA", "José Roela", 30);        
    }

    /**
     * Teste de unidade que verifica se os atributos de um folião são atribuidos e 
     * modificados corretamente. Além disso, ele checa se o método equals que 
     * compara dois foliões foi implementado corretamente.
     */
    @Test
    public void testBasic() {
        assertEquals("012345678-90", f.getCpf());
        assertEquals("666666 SSP/BA", f.getRg());
        assertEquals("José Roela", f.getNome());
        assertEquals(30, f.getIdade());
        
        f.setCpf("000000000-00");
        f.setRg("000000 SSP/AC");
        f.setNome("Ana Cecilia da Cruz");
        f.setIdade(20);
        assertEquals("000000000-00", f.getCpf());
        assertEquals("000000 SSP/AC", f.getRg());  
        assertEquals("Ana Cecilia da Cruz", f.getNome());
        assertEquals(20, f.getIdade());
        
        Foliao temp = new Foliao("000000000-00", "000000 SSP/AC", "Ana Cecilia da Cruz", 20);
        assertTrue(temp.equals(f));
        
        f.setCpf("000000000-01");        
        assertFalse(temp.equals(f));
        
        f.setRg("000001 SSP/PB");         
        f.setCpf("000000000-00");
        assertFalse(temp.equals(f));
        
        f.setNome("Carlos Correia");         
        f.setRg("000000 SSP/AC"); 
        assertFalse(temp.equals(f));
        
        f.setIdade(40);
        f.setNome("Ana Cecilia da Cruz");
        assertFalse(temp.equals(f));
    }
}
