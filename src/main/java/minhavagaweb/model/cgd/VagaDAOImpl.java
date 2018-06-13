/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import minhavagaweb.model.*;
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class VagaDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {

    private final String SELECT = "SELECT * FROM vaga ";
    private final String INSERT = "INSERT INTO vaga (id_vaga,cobertura,"
            + "status,id_estacionamento,id_localizacao,id_id_tipo) VALUES (?,?,?,?,?,?);";
    private final String DELETE = "DELETE FROM vaga WHERE id_vaga = ?;";
    private final String UPDATE = "UPDATE vaga SET (cobertura,"
            + "status,id_estacionamento,id_localizacao,id_id_tipo)"
            + " = (?,?,?,?,?) WHERE id_vaga = ?;";

    private final String ID_VAGA = "id_vaga", COBERTURA = "cobertura",
            STATUS = "status", ID_ESTACIONAMENTO = "id_estacionamento",
            ID_LOCAL = "id_localizacao", ID_TIPO = "id_tipo";

    List<Vaga> vagas = new ArrayList<>();

    @Override
    public List<GenericType> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.execute();
        ResultSet result = statement.executeQuery();

        Vaga vaga;
        while (result.next()) {
            vaga = new Vaga();
            vaga.setId(result.getInt(this.ID_VAGA));
            vaga.setStatus(result.getBoolean(this.STATUS));
            vaga.setCobertura(result.getBoolean(this.COBERTURA));

            EstacionamentoDAOImpl dao1 = new EstacionamentoDAOImpl();
            Estacionamento e = (Estacionamento) dao1.getById(result.getInt(this.ID_ESTACIONAMENTO));
            vaga.setEstacionamento(e);

            LocalizacaoDAOImpl dao2 = new LocalizacaoDAOImpl();
            Localizacao local = (Localizacao) dao2.getById(result.getInt(this.ID_LOCAL));
            vaga.setLocal(local);
            int tipo = result.getInt(this.ID_TIPO);

            switch (tipo) {
                case 1:
                    vaga.setTipo(TipoVaga.COMUM);
                case 2:
                    vaga.setTipo(TipoVaga.MOTO);
                case 3:
                    vaga.setTipo(TipoVaga.IDOSO);
                case 4:
                    vaga.setTipo(TipoVaga.DEFICIENTE);
            };

            vagas.add(vaga);
        }

        this.closeConnection(connection);
        return (List<GenericType>) vagas;
    }

    @Override
    public GenericType getById(int id) throws SQLException, ClassNotFoundException {
        Vaga vaga = null;
        if (vagas.isEmpty()) {
            vagas = (List<Vaga>) this.getAll();
        }
        for (Vaga p : vagas) {
            if (p.getId() == id) {
                vaga = p;
            }
        }
        return (GenericType) vaga;
    }

    @Override
    public void insert(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);

        Boolean cobertura = ((Vaga) obj).isCobertura();
        Boolean status = ((Vaga) obj).isStatus();
        int id_estacionamento = ((Vaga) obj).getEstacionamento().getId();
        int id_local = ((Vaga) obj).getLocal().getId();
        TipoVaga id_tipo = ((Vaga) obj).getTipo();

        statement.setInt(1, this.getNextId());
        statement.setBoolean(2, cobertura);
        statement.setBoolean(3, status);
        statement.setInt(4, id_estacionamento);
        statement.setInt(5, id_local);
        statement.setInt(6, id_tipo.getValue());

        statement.execute();
        this.closeConnection(connection);
    }

    @Override
    public void update(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);

        Boolean cobertura = ((Vaga) obj).isCobertura();
        Boolean status = ((Vaga) obj).isStatus();
        int id_estacionamento = ((Vaga) obj).getEstacionamento().getId();
        int id_local = ((Vaga) obj).getLocal().getId();
        TipoVaga id_tipo = ((Vaga) obj).getTipo();
        int id = ((Vaga) obj).getId();

        statement.setBoolean(1, cobertura);
        statement.setBoolean(2, status);
        statement.setInt(3, id_estacionamento);
        statement.setInt(4, id_local);
        statement.setInt(5, id_tipo.getValue());
        statement.setInt(6, id);

        statement.execute();
        this.closeConnection(connection);

    }

    @Override
    public void delete(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Vaga) obj).getId());
        statement.execute();
        this.closeConnection(connection);
    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        int res = -0;
        String ORDER = "ORDER BY id_vaga ASC;";
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + ORDER,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.execute();
        ResultSet result = statement.executeQuery();
        if (result.last()) {
            res = result.getInt(this.ID_VAGA);
            return res + 1;
        }

        this.closeConnection(connection);
        return res;
    }

}
