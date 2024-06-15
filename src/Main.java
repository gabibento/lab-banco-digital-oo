import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("=== Banco Exemplo ===");
			System.out.println("1. Criar Conta");
			System.out.println("2. Entrar na Conta");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
				case 1:
					GerenciamentoBanco.criarConta(scanner);
					break;
				case 2:
					GerenciamentoBanco.entrarConta(scanner);
					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida.");
			}
		} while (opcao != 0);

		scanner.close();
	}

}
