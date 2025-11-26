package model;

import org.bson.types.ObjectId;
import java.util.Date;

public class Consulta {
    private ObjectId id;
    private String paciente;
    private String medico;
    private String especialidade;
    private Date dataHora;
    private String descricao;
    private boolean realizada;

    public Consulta() {}

    public Consulta(String paciente, String medico, String esp, Date dataHora, String desc) {
        this.paciente = paciente;
        this.medico = medico;
        this.especialidade = esp;
        this.dataHora = dataHora;
        this.descricao = desc;
        this.realizada = false;
    }

    public ObjectId getId() {
         return id; 
    }

    public void setId(ObjectId id) { 
        this.id = id; 
    }

    public String getPaciente() { 
        return paciente; 
    }

    public void setPaciente(String paciente) {
         this.paciente = paciente; 
    }

    public String getMedico() { 
        return medico; 
    }

    public void setMedico(String medico) {
         this.medico = medico; 
    }

    public String getEspecialidade() { 
        return especialidade; 
    }

    public void setEspecialidade(String especialidade) {
         this.especialidade = especialidade; 
    }

    public Date getDataHora() {
         return dataHora; 
    }

    public void setDataHora(Date dataHora) {
         this.dataHora = dataHora; 
    }

    public String getDescricao() {
         return descricao;
    }

    public void setDescricao(String descricao) {
         this.descricao = descricao; 
    }

    public boolean isRealizada() {
         return realizada; 
    }

    public void setRealizada(boolean realizada) {
         this.realizada = realizada; 
    }
}

