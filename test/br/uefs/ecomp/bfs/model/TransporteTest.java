package br.uefs.ecomp.bfs.model;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import br.uefs.ecomp.bfs.model.Bloco; IMPORTAÇÃO DO MESMO PACOTE

/**
 * Testes de unidade para a classe {@link Transporte}
 */
public class TransporteTest {
    private Transporte t;
    private Bloco b1, b2;
    
    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir), 
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @BeforeEach
    public void setUp() throws Exception {
    	b1 = new Bloco("Spanta Bebê", "Salvador - Farol da Barra", 22);
        b2 = new Bloco("Massacration", "Salvador - Farol da Barra", 22);
        
    	t = new Transporte("Viagens Bahia", "Van", 34.8, 10, 23, "Feira - UEFS", 24, "Salvador", 25, "Feira - Rodoviária", b1);
    }
   
    /**
     * Teste de unidade que verifica se os atributos de um transporte são 
     * atribuidos e modificados corretamente. Além disso, ele checa se o método 
     * que compara dois transportes foi implementado corretamente.
     */
    @Test
    public void testBasic() {
    	assertEquals("Viagens Bahia", t.getNome());
    	assertEquals("Van", t.getTipo());
        assertEquals(34.8, t.getValor(), 0.0);        
        assertEquals(10, t.getCapacidade());
        assertEquals(23, t.getSaida());
        assertEquals("Feira - UEFS", t.getLocalSaida());
        assertEquals(24, t.getRetorno());
        assertEquals("Salvador", t.getLocalRetorno());
        assertEquals(25, t.getChegada());
        assertEquals("Feira - Rodoviária", t.getLocalChegada());
        assertTrue(b1.equals(t.getBloco()));
        
        t.setNome("Feira Viagens");
        t.setTipo("Ônibus");
        t.setValor(21.5);
        t.setCapacidade(40);        
        t.setSaida(3);
        t.setLocalSaida("Matinha");
    	t.setRetorno(4);
    	t.setLocalRetorno("Farol da Barra");
    	t.setChegada(5);
    	t.setLocalChegada("Baraúnas");    
    	t.setBloco(b2);

        assertEquals("Feira Viagens", t.getNome());
        assertEquals("Ônibus", t.getTipo());
        assertEquals(21.5, t.getValor(),0.0);  
        assertEquals(40, t.getCapacidade());
        assertEquals(3, t.getSaida());
        assertEquals("Matinha", t.getLocalSaida());
        assertEquals(4, t.getRetorno());
        assertEquals("Farol da Barra", t.getLocalRetorno());
        assertEquals(5, t.getChegada());
        assertEquals("Baraúnas", t.getLocalChegada());
        assertTrue(b2.equals(t.getBloco()));
        
        Transporte temp = new Transporte("Feira Viagens", "Ônibus", 21.5, 40, 3, "Matinha", 4, "Farol da Barra", 5, "Baraúnas", b2);
        assertTrue(temp.equals(t));
        
        t.setNome("vvv");        
        assertFalse(temp.equals(t));
        
        t.setTipo("ttt");         
        t.setNome("Feira Viagens");
        assertFalse(temp.equals(t));
        
        t.setValor(0.1);         
        t.setTipo("Ônibus"); 
        assertFalse(temp.equals(t));
        
        t.setCapacidade(10);
        t.setValor(21.5);
        assertFalse(temp.equals(t));
                
        t.setSaida(7);
    	t.setCapacidade(40);
        assertFalse(temp.equals(t));        
        
        t.setRetorno(8);
    	t.setSaida(3);
        assertFalse(temp.equals(t));        

        t.setChegada(9);
    	t.setRetorno(4);
        assertFalse(temp.equals(t));
        
        t.setLocalSaida("ssss");         
        t.setChegada(5);
        assertFalse(temp.equals(t));
        
        t.setLocalRetorno("rrrr");         
        t.setLocalSaida("Matinha");
        assertFalse(temp.equals(t));
        
        t.setLocalChegada("cccc");
        t.setLocalRetorno("Farol da Barra");         
        assertFalse(temp.equals(t));
        
        t.setBloco(b1);
        t.setLocalChegada("Baraúnas");
        assertFalse(temp.getBloco().equals(t.getBloco()));
    }
    
 }
