package interface_grafica;
import javax.swing.*;

import java.awt.*;
import java.util.List;
//import org.bson.types.ObjectId;

import dao.ConsultaDAO;
import model.Consulta;


public class MainUI extends JFrame {

    private JTable tabela;
    private JButton btnNovo, btnEditar, btnExcluir, btnAtualizar;

    public MainUI() {
        super("Consultas Médicas - CRUD com MongoDB");

        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        carregarTabela();
    }

    private void initUI() {
        tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);

        btnNovo = new JButton("Nova Consulta");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnAtualizar = new JButton("Atualizar Lista");

        JPanel panel = new JPanel();
        panel.add(btnNovo);
        panel.add(btnEditar);
        panel.add(btnExcluir);
        panel.add(btnAtualizar);

        add(scroll, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        btnNovo.addActionListener(e -> {
            new FormConsulta(this, null).setVisible(true);
            carregarTabela();
        });

        btnEditar.addActionListener(e -> editar());
        btnExcluir.addActionListener(e -> excluir());
        btnAtualizar.addActionListener(e -> carregarTabela());
    }

    private void carregarTabela() {
        try (ConsultaDAO dao = new ConsultaDAO()) {
            List<Consulta> list = dao.listar();
            tabela.setModel(new TabelaModelConsulta(list));
        }
    }

    private void editar() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta!");
            return;
        }

        Consulta consulta = ((TabelaModelConsulta) tabela.getModel()).getConsulta(row);
        new FormConsulta(this, consulta).setVisible(true);
        carregarTabela();
    }

    private void excluir() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta!");
            return;
        }

        Consulta consulta = ((TabelaModelConsulta) tabela.getModel()).getConsulta(row);

        try (ConsultaDAO dao = new ConsultaDAO()) {
            dao.excluir(consulta.getId());
        }

        carregarTabela();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI().setVisible(true));
    }
}

