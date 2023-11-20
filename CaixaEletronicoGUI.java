import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CaixaEletronicoGUI {
    private ContaCorrente conta;
    private ArrayList<Double> transferenciasRecebidas;

    public CaixaEletronicoGUI(ContaCorrente conta, ArrayList<Double> transferenciasRecebidas) {
        this.conta = conta;
        this.transferenciasRecebidas = transferenciasRecebidas;

        // Criar e configurar a janela
        JFrame frame = new JFrame("Caixa Eletrônico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Criar e configurar os componentes
        JButton sacarButton = new JButton("Sacar");
        JButton depositarButton = new JButton("Depositar");
        JButton transferirButton = new JButton("Transferir");
        JButton saldoFuturoButton = new JButton("Saldo Futuro");

        // Adicionar os ouvintes de ação
        sacarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor para saque:"));
                conta.sacar(valorSaque);
                exibirSaldoAtual();
            }
        });

        depositarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor para depósito:"));
                conta.depositar(valorDeposito);
                exibirSaldoAtual();
            }
        });

        transferirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valorTransferencia = Double
                        .parseDouble(JOptionPane.showInputDialog("Digite o valor para transferência:"));
                try {
                    ContaCorrente destino = escolherDestinoTransferencia(conta);
                    conta.transferir(destino, valorTransferencia);
                    exibirSaldoAtual();
                } catch (TransferenciaException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saldoFuturoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double saldoFuturo = conta.saldoFuturo(transferenciasRecebidas);
                JOptionPane.showMessageDialog(null, "Saldo Futuro: R$ " + saldoFuturo);
            }
        });

        // Configurar o layout
        JPanel panel = new JPanel();
        panel.add(sacarButton);
        panel.add(depositarButton);
        panel.add(transferirButton);
        panel.add(saldoFuturoButton);

        // Adicionar o painel à janela
        frame.getContentPane().add(panel);

        // Exibir a janela
        frame.setVisible(true);
    }

    private void exibirSaldoAtual() {
        JOptionPane.showMessageDialog(null, "Saldo Atual: R$ " + conta.saldo);
    }

    private ContaCorrente escolherDestinoTransferencia(ContaCorrente origem) throws TransferenciaException {
        String[] opcoes = { "PF", "PJ" };
        String tipoConta = (String) JOptionPane.showInputDialog(null, "Escolha o tipo de conta de destino:",
                "Escolher Destino", JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (tipoConta == null) {
            throw new TransferenciaException("Transferência cancelada pelo usuário.");
        }

        if (tipoConta.equalsIgnoreCase("PF")) {
            return new ContaCorrentePessoaFisica("Destino PF", "000.000.000-00", 0.0);
        } else if (tipoConta.equalsIgnoreCase("PJ")) {
            return new ContaCorrentePessoaJuridica("Destino PJ", "000.000.000/0000-00", 0.0);
        } else {
            throw new TransferenciaException("Tipo de conta inválido para transferência.");
        }
    }
    public static void main(String[] args) {
        // Criar instâncias de conta e transferências recebidas
        ContaCorrentePessoaFisica contaPessoaFisica = new ContaCorrentePessoaFisica("Maria da Silva", "021.213.415-21",
                3000.00);
        ArrayList<Double> transferenciasRecebidas = new ArrayList<>();
        transferenciasRecebidas.add(200.00);
        transferenciasRecebidas.add(600.00);
        transferenciasRecebidas.add(250.00);
        transferenciasRecebidas.add(400.00);
        transferenciasRecebidas.add(23.00);
        transferenciasRecebidas.add(300.00);

        // Iniciar a interface gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CaixaEletronicoGUI(contaPessoaFisica, transferenciasRecebidas);
            }
        });
    }
}
