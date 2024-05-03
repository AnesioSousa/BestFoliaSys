package br.uefs.ecomp.bfs.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.uefs.ecomp.bfs.model.Bloco;
import br.uefs.ecomp.bfs.model.Foliao;
import br.uefs.ecomp.bfs.model.Transporte;
import br.uefs.ecomp.bfs.util.Iterador;

public class ControllerBFSTest {

	private Bloco b1, b2, b3, b4, b5, b6, testB;	
	private Foliao f1, f2, f3, testF;
	private Transporte t1, t2, t3, t4, t5, t6, testT;
	private Iterador itr;
        
	ControllerBFS controller = new ControllerBFS();

	@BeforeEach
	public void setUp() throws Exception {		
	}

	@Test
	public void testCadastrarBloco() {
		assertEquals(0, controller.getBlocos().tamanho());
		b1 = controller.cadastrarBloco("Dormir é o Melhor Remédio", "Feira de Santana", 01);
                assertEquals(0, b1.getId()); // Teste criado por mim.
		assertEquals(1, controller.getBlocos().tamanho());
		b2 = controller.cadastrarBloco("A Velha Debaixo da Cama", "Olinda", 02);
                assertEquals(1, b2.getId()); // Teste criado por mim.
		b3 = controller.cadastrarBloco("Os Mascarados", "Aracaju", 03);
                assertEquals(2, b3.getId()); // Teste criado por mim.
                testB = controller.cadastrarBloco("Dormir é o Melhor Remédio", "Feira de Santana", 01); // Teste criado por mim.
                assertNull(testB); // Teste criado por mim.
		assertEquals(3, controller.getBlocos().tamanho());
	}
	
	@Test
	public void testCadastrarFoliao() {
		assertEquals(0, controller.getFolioes().tamanho());
		f1 = controller.cadastrarFoliao("012345678-90", "666666 SSP/BA", "José Roela", 30);  
		assertEquals(1, controller.getFolioes().tamanho());
		f2 = controller.cadastrarFoliao("000000000-00", "666666 SSP/AC", "Antônio Liso", 20);  
		f3 = controller.cadastrarFoliao("111111111-11", "666666 SSP/PE", "Maria Desespero", 40);
                testF = controller.cadastrarFoliao("111111111-11", "954785 SSP/RS", "Oswaldo Glória", 61); // Teste criado por mim.
                assertNull(testF); // Teste criado por mim.
		assertEquals(3, controller.getFolioes().tamanho());
	}
		
	@Test
	public void testCadastrarTransporte() {
		b1 = controller.cadastrarBloco("Dormir é o Melhor Remédio", "Feira de Santana", 01);
		b2 = controller.cadastrarBloco("A Velha Debaixo da Cama", "Olinda", 02);
		b3 = controller.cadastrarBloco("Os Mascarados", "Aracaju", 03);
				
		t1 = controller.cadastrarTransporte("Viagens Bahia", "Van", 34.8, 1, 01, "Feira - UEFS", 01, "Salvador", 02, "Feira - Rodoviária", b1);
		assertEquals(0, t1.getId()); // Teste criado por mim.
		t2 = controller.cadastrarTransporte("Feira Viagens", "Ônibus", 21.5, 40, 02, "Matinha", 02, "Salvador", 02, "Baraúnas", b2);
		assertEquals(1, t2.getId()); // Teste criado por mim.
		t3 = controller.cadastrarTransporte("Teste", "Carro", 21.5, 40, 01, "XXX", 01, "YYY", 01, "ZZZ", b1);
		assertEquals(2, t3.getId()); // Teste criado por mim.
                
		Iterador iterador = controller.listarTransportes(b1);
		assertTrue(iterador.temProximo());
		Transporte proximoTransporte = (Transporte) iterador.proximo();
		assertSame(t1, proximoTransporte);	
		assertTrue(iterador.temProximo());
		proximoTransporte = (Transporte) iterador.proximo();
		assertSame(t3, proximoTransporte);	
		assertFalse(iterador.temProximo());
		
		iterador = controller.listarTransportes(b2);
		assertTrue(iterador.temProximo());
		proximoTransporte = (Transporte) iterador.proximo();
		assertSame(t2, proximoTransporte);		
		assertFalse(iterador.temProximo());
		
		
		f1 = controller.cadastrarFoliao("012345678-90", "666666 SSP/BA", "José Roela", 30);
		f2 = controller.cadastrarFoliao("000000000-00", "666666 SSP/AC", "Antônio Liso", 20);
		f3 = controller.cadastrarFoliao("111111111-11", "666666 SSP/PE", "Maria Desespero", 40);
		
		
		assertTrue(controller.registrarFoliaoEmTransporte(f1, t1));
		assertTrue(controller.registrarFoliaoEmTransporte(f2, t2));		
		assertTrue(controller.registrarFoliaoEmTransporte(f3, t2));
		assertFalse(controller.registrarFoliaoEmTransporte(f3, t2));		
		
		iterador = controller.listarFolioes(t2);
		assertTrue(iterador.temProximo());
		Foliao proximoFoliao = (Foliao) iterador.proximo();
		assertSame(f2, proximoFoliao);		
		assertTrue(iterador.temProximo());
		proximoFoliao = (Foliao) iterador.proximo();
		assertSame(f3, proximoFoliao);
		assertFalse(iterador.temProximo());
		
		
		iterador = controller.listarFolioes(t1);
		assertTrue(iterador.temProximo());
		proximoFoliao = (Foliao) iterador.proximo();
		assertSame(f1, proximoFoliao);		
		assertFalse(iterador.temProximo());
				
	}
        
        // Método de teste criado por mim
        @Test
        public void testObter(){
                Bloco blc1, blc2, blc3, blc4;
                Transporte trsp1, trsp2, trsp3, trsp4;
                Foliao foli1, foli2, foli3, foli4;
                
                b1 = controller.cadastrarBloco("Dormir é o Melhor Remédio", "Feira de Santana", 01);
                b2 = controller.cadastrarBloco("A Velha Debaixo da Cama", "Olinda", 02);
                b3 = controller.cadastrarBloco("Os Mascarados", "Aracaju", 03);
                
                t1 = controller.cadastrarTransporte("Viagens Bahia", "Van", 34.8, 10, 01, "Feira - UEFS", 01, "Salvador", 02, "Feira - Rodoviária", b1);
                t2 = controller.cadastrarTransporte("Feira Viagens", "Ônibus", 21.5, 40, 02, "Matinha", 02, "Salvador", 02, "Baraúnas", b2);
                t3 = controller.cadastrarTransporte("Teste", "Carro", 21.5, 40, 01, "XXX", 01, "YYY", 01, "ZZZ", b1);
                
                f1 = controller.cadastrarFoliao("012345678-90", "666666 SSP/BA", "José Roela", 30);
		f2 = controller.cadastrarFoliao("000000000-00", "666666 SSP/AC", "Antônio Liso", 20);
		f3 = controller.cadastrarFoliao("111111111-11", "666666 SSP/PE", "Maria Desespero", 40);
                
                blc1 = controller.obterBloco(0);
                assertSame(blc1.getNome(), b1.getNome());
                
                blc2 = controller.obterBloco(1);
                assertSame(blc2.getLocal(), b2.getLocal());
                
                blc3 = controller.obterBloco(2);
                assertSame(blc3.getSaida(), b3.getSaida());
                
                blc4 = controller.obterBloco(887);
                assertSame(blc4, null);
                
                trsp1 = controller.obterTransporte(0);
                assertSame(trsp1.getNome(), "Viagens Bahia");
                
                trsp2 = controller.obterTransporte(1);
                assertSame(trsp2.getTipo(),"Ônibus");
                
                trsp3 = controller.obterTransporte(2);
                assertEquals(21.5,trsp3.getValor(), 0.0);
                
                trsp4 = controller.obterTransporte(47);
                assertSame(trsp4, null);
                
                foli1 = controller.obterFoliao("012345678-90");
                assertSame(foli1.getRg(), "666666 SSP/BA");
                
                foli2 = controller.obterFoliao("000000000-00");
                assertSame(foli2.getNome(), "Antônio Liso");
                
                foli3 = controller.obterFoliao("111111111-11");
                assertSame(foli3.getIdade(), 40);
                
                foli4 = controller.obterFoliao("994488774-33");
                assertSame(foli4, null);
        }        
        
        // Método de teste criado por mim
        @Test
        public void testListarBlocos(){
                b1 = controller.cadastrarBloco("Dormir é o Melhor Remédio", "Feira de Santana", 01);
                b2 = controller.cadastrarBloco("A Velha Debaixo da Cama", "Olinda", 02);
                b3 = controller.cadastrarBloco("Os Mascarados", "Aracaju", 03);
                b4 = controller.cadastrarBloco("Os filhos da mãe Joana", "Pernambuco", 04);
                
                
                t1 = controller.cadastrarTransporte("Viagens Bahia", "Van", 34.8, 10, 01, "Feira - UEFS", 01, "Salvador", 02, "Feira - Rodoviária", b1);
                t2 = controller.cadastrarTransporte("Feira Viagens", "Ônibus", 21.5, 40, 02, "Matinha", 02, "Salvador", 02, "Baraúnas", b2);
                t3 = controller.cadastrarTransporte("Jurupinga Trip", "KOMBI", 21.5, 12, 01, "XXX", 05, "YYY", 02, "Pelourinho", b1);
                t4 = controller.cadastrarTransporte("Teste", "Carro", 21.5, 40, 01, "XXX", 05, "YYY", 02, "Pelourinho", b1);
                t5 = controller.cadastrarTransporte("Teste", "Carro", 21.5, 40, 01, "XXX", 01, "YYY", 01, "ZZZ", b3);
                t6 = controller.cadastrarTransporte("TesteT6", "AVIÃOT6", 21.5, 40, 01, "XXX", 04, "YYY", 03, "Pelourinho", b4);
                
                //                                 10        9   7
                itr = controller.listarBlocos("Pelourinho", 02, 05);
                
                Bloco aux = (Bloco) itr.proximo();
                assertSame(aux.getId(), 0);
                
                aux = (Bloco) itr.proximo();
                assertSame(aux.getId(), 3);
                
                assertFalse(itr.temProximo());
                
                itr = controller.listarBlocos("ZZZ", 01, 01);
                
                aux = (Bloco) itr.proximo();
                assertSame(aux.getId(), 2);
                
                assertFalse(itr.temProximo());
                
        }
        
}
