import java.util.ArrayList;
import java.util.Scanner;

abstract class ContaCorrente {
    protected String nome;
    protected double saldo;

    public ContaCorrente(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public abstract void sacar(double valor);

    public abstract void depositar(double valor);

    public abstract void transferir(ContaCorrente destino, double valor);

    public abstract double saldoFuturo(ArrayList<Double> transferenciasRecebidas);
}
class ContaCorrentePessoaFisica extends ContaCorrente {
    private String cpf;

    public ContaCorrentePessoaFisica(String nome, String cpf, double saldo) {
        super(nome, saldo);
        this.cpf = cpf;
    }

    public String getCPF() {
        return cpf;
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
            System.out.println("Saldo atual: R$ " + saldo);
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para saque.");
        }
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso!");
            System.out.println("Saldo atual: R$ " + saldo);
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    @Override
    public void transferir(ContaCorrente destino, double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            destino.depositar(valor);
            System.out.println("Transferência realizada com sucesso!");
            System.out.println("Saldo atual: R$ " + saldo);
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para transferência.");
        }
    }

    @Override
    public double saldoFuturo(ArrayList<Double> transferenciasRecebidas) {
        double saldoFuturo = saldo;
        for (Double valor : transferenciasRecebidas) {
            saldoFuturo += valor;
        }
        return saldoFuturo;
    }
}
class ContaCorrentePessoaJuridica extends ContaCorrente {
    private String cnpj;

    public ContaCorrentePessoaJuridica(String nome, String cnpj, double saldo) {
        super(nome, saldo);
        this.cnpj = cnpj;
    }

    public String getCNPJ() {
        return cnpj;
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
            System.out.println("Saldo atual: R$ " + saldo);
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para saque.");
        }
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso!");
            System.out.println("Saldo atual: R$ " + saldo);
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    @Override
    public void transferir(ContaCorrente destino, double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            destino.depositar(valor);
            System.out.println("Transferência realizada com sucesso!");
            System.out.println("Saldo atual: R$ " + saldo);
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para transferência.");
        }
    }

    @Override
    public double saldoFuturo(ArrayList<Double> transferenciasRecebidas) {
        double saldoFuturo = saldo;
        for (Double valor : transferenciasRecebidas) {
            saldoFuturo += valor;
        }
        return saldoFuturo;
    }
}

class TransferenciaException extends Exception {
    public TransferenciaException(String message) {
        super(message);
    }
}
public class CaixaEletronico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ContaCorrentePessoaFisica contaPessoaFisica = new ContaCorrentePessoaFisica("Maria da Silva", "021.213.415-21",
                3000.00);
        ContaCorrentePessoaJuridica contaPessoaJuridica = new ContaCorrentePessoaJuridica("Restaurante da Maria",
                "021.213.415/0001-21", 5000.00);

        ArrayList<Double> transferenciasRecebidas = new ArrayList<>();
        transferenciasRecebidas.add(200.00);
        transferenciasRecebidas.add(600.00);
        transferenciasRecebidas.add(250.00);
        transferenciasRecebidas.add(400.00);
        transferenciasRecebidas.add(23.00);
        transferenciasRecebidas.add(300.00);

        System.out.println(
                "Bem-vindo ao Banco Bradescão! Digite PF para acessar a pessoa física ou PJ para pessoa jurídica");
        String opcao = scanner.nextLine();

        switch (opcao.toUpperCase()) {
            case "PF":
                exibirDadosConta(contaPessoaFisica);
                menuConta(scanner, contaPessoaFisica, transferenciasRecebidas);
                break;
            case "PJ":
                exibirDadosConta(contaPessoaJuridica);
                menuConta(scanner, contaPessoaJuridica, transferenciasRecebidas);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void exibirDadosConta(ContaCorrente conta) {
        if (conta instanceof ContaCorrentePessoaFisica) {
            ContaCorrentePessoaFisica contaPessoaFisica = (ContaCorrentePessoaFisica) conta;
            System.out.println("Nome: " + contaPessoaFisica.nome);
            System.out.println("CPF: " + contaPessoaFisica.getCPF());
            System.out.println("Saldo: R$ " + contaPessoaFisica.saldo);
        } else if (conta instanceof ContaCorrentePessoaJuridica) {
            ContaCorrentePessoaJuridica contaPessoaJuridica = (ContaCorrentePessoaJuridica) conta;
            System.out.println("Nome da Empresa: " + contaPessoaJuridica.nome);
            System.out.println("CNPJ: " + contaPessoaJuridica.getCNPJ());
            System.out.println("Saldo: R$ " + contaPessoaJuridica.saldo);
        }
    }

    private static void menuConta(Scanner scanner, ContaCorrente conta, ArrayList<Double> transferenciasRecebidas) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1- Sacar");
            System.out.println("2- Depositar");
            System.out.println("3- Transferir");
            System.out.println("4- Saldo Futuro");
            System.out.println("5- Sair");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("Digite o valor para saque:");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                    break;
                case 2:
                    System.out.println("Digite o valor para depósito:");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    break;
                case 3:
                    System.out.println("Digite o valor para transferência:");
                    double valorTransferencia = scanner.nextDouble();
                    try {
                        ContaCorrente destino = escolherDestinoTransferencia(conta);
                        conta.transferir(destino, valorTransferencia);
                    } catch (TransferenciaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    double saldoFuturo = conta.saldoFuturo(transferenciasRecebidas);
                    System.out.println("Saldo Futuro: R$ " + saldoFuturo);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static ContaCorrente escolherDestinoTransferencia(ContaCorrente origem) throws TransferenciaException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o tipo da conta de destino (PF ou PJ):");
        String tipoConta = scanner.next();

        if (tipoConta.equalsIgnoreCase("PF")) {
            return new ContaCorrentePessoaFisica("Destino PF", "000.000.000-00", 0.0);
        } else if (tipoConta.equalsIgnoreCase("PJ")) {
            return new ContaCorrentePessoaJuridica("Destino PJ", "000.000.000/0000-00", 0.0);
        } else {
            throw new TransferenciaException("Tipo de conta inválido para transferência.");
        }
    }
}



