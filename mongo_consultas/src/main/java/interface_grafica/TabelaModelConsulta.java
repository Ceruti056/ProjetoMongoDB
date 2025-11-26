package interface_grafica;


import javax.swing.table.AbstractTableModel;

import java.text.SimpleDateFormat;
import java.util.List;

import model.Consulta;

public class TabelaModelConsulta extends AbstractTableModel {

    private final List<Consulta> consultas;
    
    private final String[] colunas = {
            "Paciente", "Médico", "Especialidade", "Data", "Realizada"
    };

    public TabelaModelConsulta(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Override
    public int getRowCount() { 
        return consultas.size(); 
    }

    @Override
    public int getColumnCount() {
         return colunas.length; 
    }

    @Override
    public String getColumnName(int col) {
         return colunas[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Consulta c = consultas.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return switch (col) {
            case 0 -> c.getPaciente();
            case 1 -> c.getMedico();
            case 2 -> c.getEspecialidade();
            case 3 -> sdf.format(c.getDataHora());
            case 4 -> c.isRealizada() ? "Sim" : "Não";
            default -> "";
        };
    }

    public Consulta getConsulta(int row) {
         return consultas.get(row); 
    }
}

