import java.util.Scanner;

public class GerenciamentoBanco {



    private static Banco banco = new Banco("Banco Exemplo");

     static void criarConta(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cliente cliente = new Cliente(nome, senha);
        Conta conta;

        System.out.println("Tipo de Conta: 1. Corrente  2. Poupança");
        int tipoConta = scanner.nextInt();

        if (tipoConta == 1) {
            conta = new ContaCorrente(cliente);
        } else if (tipoConta == 2) {
            conta = new ContaPoupanca(cliente);
        } else {
            System.out.println("Tipo de conta inválido.");
            return;
        }

        banco.adicionarConta(conta);
        System.out.println("Conta criada com sucesso! Número da Conta: " + conta.getNumero());
    }

     static void entrarConta(Scanner scanner) {
        System.out.print("Número da Conta: ");
        int numeroConta = scanner.nextInt();
        scanner.nextLine(); // consumir nova linha
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Conta conta = banco.buscarConta(numeroConta);

        if (conta != null && conta.cliente.autenticar(senha)) {
            realizarOperacoes(scanner, conta);
        } else {
            System.out.println("Conta não encontrada ou senha incorreta.");
        }
    }

     static void realizarOperacoes(Scanner scanner, Conta conta) {
        int opcao;

        do {
            System.out.println("=== Operações ===");
            System.out.println("1. Sacar");
            System.out.println("2. Depositar");
            System.out.println("3. Transferir");
            System.out.println("4. Imprimir Extrato");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Valor do Saque: ");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                    break;
                case 2:
                    System.out.print("Valor do Depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    break;
                case 3:
                    System.out.print("Número da Conta Destino: ");
                    int numeroContaDestino = scanner.nextInt();
                    System.out.print("Valor da Transferência: ");
                    double valorTransferencia = scanner.nextDouble();
                    Conta contaDestino = banco.buscarConta(numeroContaDestino);
                    if (contaDestino != null) {
                        conta.transferir(valorTransferencia, contaDestino);
                    } else {
                        System.out.println("Conta destino não encontrada.");
                    }
                    break;
                case 4:
                    conta.imprimirExtrato();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
