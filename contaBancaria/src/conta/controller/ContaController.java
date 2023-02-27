package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository{

	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;
	
	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null)
			conta.visualizar();
		
		else 
			System.out.println("A Conta número: " + numero + " não foi encontrada!");
	}

	@Override
	public void listarTodas() {
		for(var conta : listaContas)
			conta.visualizar();
		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("A conta foi Criada!");
	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());
		
		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("A Conta número: " + conta.getNumero() + " foi atualizada!");
		
		}else 
			System.out.println("A Conta número: " + conta.getNumero() + " não foi encontrada!");
		
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			conta.visualizar();
			if(listaContas.remove(conta) == true)
				System.out.println("A Conta número: " + numero + " foi excluída!");		

		}else 
			System.out.println("A Conta número: " + numero + " não foi encontrada!");		
	}

	@Override
	public void sacar(int numero, float valor) {

		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			if (conta.sacar(valor) == true)
				System.out.println("O saque foi efetuado com sucesso!");
		}else
			System.out.println("A conta número: " + numero + "não foi encontrada!");
	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			conta.depositar(valor);
				System.out.println("O depósito foi efetuado com sucesso!");
		}else
			System.out.println("A conta número: " + numero + "não foi encontrada!");
		
	}

	@Override
	public void transferir(int numero, int numeroDestino, float valor) {
		
		var contaOrigem = buscarNaCollection(numero);
		var contaDestino = buscarNaCollection(numeroDestino);
		
		if (contaOrigem != null && contaDestino != null) {
			if(contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("A transferência foi efetuada com sucesso!");
				
			}
			
		}else
			System.out.println("A conta de Origem e/ou Destino não foram encontradas!");
	}
	
	/*Implementar Métodos Auxiliares */
	
	public int gerarNumero() {
		
		
		return ++ numero;
	
}

	public Conta buscarNaCollection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumero() == numero)
				return conta;
			
		}
		
		return null;
		
	}
	
	public int retornaTipo(int numero) {
		
		for (var conta : listaContas) {
			if(conta.getNumero() == numero)
				return conta.getTipo();
			
	}
		
		return 0;
}
}
