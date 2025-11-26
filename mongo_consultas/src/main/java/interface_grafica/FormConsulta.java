package interface_grafica;

import dao.ConsultaDAO;
import model.Consulta;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class FormConsulta extends JDialog {

    private JTextField txtPaciente, txtMedico, txtEsp, txtDesc;
    private JCheckBox chkRealizada;
    private JButton btnSalvar;
    private Consulta consulta;
    private final boolean editMode;

    public FormConsulta(JFrame owner, Consulta consulta) {
        super(owner, true);
        this.consulta = consulta;
        this.editMode = consulta != null;

        setTitle(editMode ? "Editar Consulta" : "Nova Consulta");
        setSize(400, 350);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        setLayout(new GridLayout(7, 1));

        txtPaciente = new JTextField(editMode ? consulta.getPaciente() : "");
        txtMedico   = new JTextField(editMode ? consulta.getMedico() : "");
        txtEsp      = new JTextField(editMode ? consulta.getEspecialidade() : "");
        txtDesc     = new JTextField(editMode ? consulta.getDescricao() : "");
        chkRealizada = new JCheckBox("Realizada", editMode && consulta.isRealizada());

        btnSalvar = new JButton("Salvar");

        add(new JLabel("Paciente:"));
        add(txtPaciente);
        add(new JLabel("Médico:"));
        add(txtMedico);
        add(new JLabel("Especialidade:"));
        add(txtEsp);
        add(new JLabel("Descrição:"));
        add(txtDesc);
        add(chkRealizada);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> salvar());
    }

    private void salvar() {
        try (ConsultaDAO dao = new ConsultaDAO()) {
            if (!editMode) {
                consulta = new Consulta(
                        txtPaciente.getText(),
                        txtMedico.getText(),
                        txtEsp.getText(),
                        new Date(),
                        txtDesc.getText()
                );
                dao.salvar(consulta);
            } else {
                consulta.setPaciente(txtPaciente.getText());
                consulta.setMedico(txtMedico.getText());
                consulta.setEspecialidade(txtEsp.getText());
                consulta.setDescricao(txtDesc.getText());
                consulta.setRealizada(chkRealizada.isSelected());
                dao.atualizar(consulta);
            }

            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            dispose();
        }
    }
}
