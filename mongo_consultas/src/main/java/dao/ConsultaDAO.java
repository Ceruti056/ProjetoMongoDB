package dao;

import config.MongoConfig;
import model.Consulta;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO implements AutoCloseable {

    private final MongoConfig config;
    private final MongoCollection<Consulta> coll;

    public ConsultaDAO() {
        config = new MongoConfig();
        coll = config.getDatabase().getCollection("consultas", Consulta.class);
    }

    public void salvar(Consulta c) {
        coll.insertOne(c);
    }

    public List<Consulta> listar() {
        List<Consulta> list = new ArrayList<>();
        coll.find().into(list);
        return list;
    }

    public void atualizar(Consulta c) {
        coll.updateOne(
                Filters.eq("_id", c.getId()),
                Updates.combine(
                        Updates.set("paciente", c.getPaciente()),
                        Updates.set("medico", c.getMedico()),
                        Updates.set("especialidade", c.getEspecialidade()),
                        Updates.set("dataHora", c.getDataHora()),
                        Updates.set("descricao", c.getDescricao()),
                        Updates.set("realizada", c.isRealizada())
                )
        );
    }

    public void excluir(ObjectId id) {
        coll.deleteOne(Filters.eq("_id", id));
    }

    @Override
    public void close() {
        config.close();
    }
}

