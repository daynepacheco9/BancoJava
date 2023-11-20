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


