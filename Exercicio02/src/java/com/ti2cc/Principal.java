package com.ti2cc;

import java.util.*;

public class Principal {
	public static Scanner ent = new Scanner(System.in);
	
	public static void main ( String[] args ) {
		
		DAO dao = new DAO();
		int opcao = 0;
		int cod = 0;
		int ano = 0;
		String model = "";
		String mark = "";
		Carro carro = new Carro(cod, model, mark, ano);
		Carro[] carros = dao.getCarros();
		
		dao.conectar();	
		
		do {
			System.out.println("Menu:\n");
			System.out.println("0 - Sair");
			System.out.println("1 - Listar");
			System.out.println("2 - Inserir");
			System.out.println("3 - Excluir");
			System.out.println("4 - Atualizar");
			
			System.out.print("\nOpcao: ");
			opcao = ent.nextInt();
		
			switch (opcao) {
				case 0: break;
				case 1:
					carros = dao.getCarros();
					System.out.println("===== Mostrar carros =====\n");
					for (int i = 0; i < carros.length; i++ ) {
						System.out.println(carros[i].toString());
					}
					break;
					
				case 2:
					System.out.println("===== Inserir carro =====\n");
					System.out.print("Codigo: ");
					cod = ent.nextInt();
					System.out.print("Modelo: ");
					model = ent.nextLine();
					System.out.print("Marca: ");
					mark = ent.nextLine();
					System.out.print("Ano::");
					ano = ent.nextInt();
					
					carro = new Carro(cod, model, mark, ano);
					if (dao.inserirCarro(carro) == true) {
						System.out.println("Inserção com sucesso -> " + carro.toString());
					}
					break;
					
				case 3:
					dao.excluirCarro(carro.getCodigo());
					break;
					
				case 4:
					// atualizar modelo
			        System.out.print("Atualizar modelo: ");
			        model = ent.nextLine(); 
			        carro.setModelo(model);
			        dao.atualizarCarro(carro);
			        // atualizar marca
			        System.out.print("Atualizar marca: ");
			        mark = ent.nextLine();
			        carro.setMarca(mark);
			        dao.atualizarCarro(carro);
			        // atualizar ano
			        System.out.print("Atualizar ano: ");
			        ano = ent.nextInt();
			        carro.setAno(ano);
			        dao.atualizarCarro(carro);
					break;
				
				default: 
					System.out.println("err00000: Opcao invalida."); 
					break;
			}		
		} while (opcao != 0);

		dao.close();
	}
}
