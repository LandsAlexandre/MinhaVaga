/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgd;

import minhavagaweb.model.cdp.Estacionamento;
import minhavagaweb.model.cdp.Localizacao;
import minhavagaweb.model.cdp.Vaga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import minhavagaweb.model.cdp.Estacionamento;
import minhavagaweb.model.cdp.Localizacao;
import minhavagaweb.model.cdp.TipoVaga;
import minhavagaweb.model.cdp.Vaga;
import minhavagaweb.model.persistencia.Conector;

public class VagaDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM vaga ";
    private static final String INSERT = "INSERT INTO vaga (id_vaga,cobertura,"
            + "status,id_estacionamento,id_localizacao,id_id_tipo) VALUES (?,?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM vaga WHERE id_vaga = ?;";
    private static final String UPDATE = "UPDATE vaga SET (cobertura,"
=======
import minhavagaweb.model.*;
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class VagaDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {

    private final String SELECT = "SELECT * FROM vaga ";
    private final String INSERT = "INSERT INTO vaga (id_vaga,cobertura,"
            + "status,id_estacionamento,id_localizacao,id_tipo) VALUES (?,?,?,?,?,?);";
    private final String DELETE = "DELETE FROM vaga WHERE id_vaga = ?;";
    private final String UPDATE = "UPDATE vaga SET (cobertura,"
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
            + "status,id_estacionamento,id_localizacao,id_id_tipo)"
            + " = (?,?,?,?,?) WHERE id_vaga = ?;";

    private static final String ID_VAGA = "id_vaga";
    private static final String COBERTURA = "cobertura";
    private static final String STATUS = "status";
    private static final String ID_ESTACIONAMENTO = "id_estacionamento";
    private static final String ID_LOCAL = "id_localizacao";
    private static final String ID_TIPO = "id_tipo";
    private static final String ORDER = "ORDER BY id_vaga ASC";

    List<Vaga> vagas = new ArrayList<>();

    @Override
<<<<<<< HEAD
    public List<G> getAll() throws SQLException, ClassNotFoundException {
=======
    public List<GenericType> getAll() throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.execute();
        ResultSet result = statement.executeQuery();

        Vaga vaga;
        while (result.next()) {
            vaga = new Vaga();
<<<<<<< HEAD
            vaga.setId(result.getInt(VagaDAOImpl.ID_VAGA));
            vaga.setStatus(result.getBoolean(VagaDAOImpl.STATUS));
            vaga.setCobertura(result.getBoolean(VagaDAOImpl.COBERTURA));

            EstacionamentoDAOImpl dao1 = new EstacionamentoDAOImpl();
            Estacionamento e = (Estacionamento) dao1.getById(result.getInt(VagaDAOImpl.ID_ESTACIONAMENTO));
            vaga.setEstacionamento(e);

            LocalizacaoDAOImpl dao2 = new LocalizacaoDAOImpl();
            Localizacao local = (Localizacao) dao2.getById(result.getInt(VagaDAOImpl.ID_LOCAL));
            vaga.setLocal(local);
            int tipo = result.getInt(VagaDAOImpl.ID_TIPO);
=======
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
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

            switch (tipo) {
                case 1:
                    vaga.setTipo(TipoVaga.COMUM);
<<<<<<< HEAD
                    break;
                case 2:
                    vaga.setTipo(TipoVaga.MOTO);
                    break;
                case 3:
                    vaga.setTipo(TipoVaga.IDOSO);
                    break;
                case 4:
                    vaga.setTipo(TipoVaga.DEFICIENTE);
                    break;
                default:
                    break;
            }
=======
                case 2:
                    vaga.setTipo(TipoVaga.MOTO);
                case 3:
                    vaga.setTipo(TipoVaga.IDOSO);
                case 4:
                    vaga.setTipo(TipoVaga.DEFICIENTE);
            };
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

            vagas.add(vaga);
        }

        this.closeConnection(connection);
<<<<<<< HEAD
        return (List<G>) vagas;
    }

    @Override
    public G getById(int id) throws SQLException, ClassNotFoundException {
=======
        return (List<GenericType>) vagas;
    }

    @Override
    public GenericType getById(int id) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Vaga vaga = null;
        if (vagas.isEmpty()) {
            vagas = (List<Vaga>) this.getAll();
        }
        for (Vaga p : vagas) {
            if (p.getId() == id) {
                vaga = p;
            }
        }
        return (G) vaga;
    }

    @Override
<<<<<<< HEAD
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
=======
    public boolean insert(GenericType obj) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);

        Boolean cobertura = ((Vaga) obj).isCobertura();
        Boolean status = ((Vaga) obj).isStatus();
        int id_estacionamento = ((Vaga) obj).getEstacionamento().getId();
        int id_local = ((Vaga) obj).getLocal().getId();
        TipoVaga id_tipo = ((Vaga) obj).getTipo();

<<<<<<< HEAD
        statement.setInt(1, this.getNextId(ORDER, SELECT, ID_VAGA));
=======
        statement.setInt(1, this.getNextId());
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        statement.setBoolean(2, cobertura);
        statement.setBoolean(3, status);
        statement.setInt(4, id_estacionamento);
        statement.setInt(5, id_local);
        statement.setInt(6, id_tipo.getValue());

        boolean stat = statement.execute();
        this.closeConnection(connection);
        return stat;
    }

    @Override
<<<<<<< HEAD
    public void update(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        Boolean cobertura = ((Vaga) obj).isCobertura();
        Boolean status = ((Vaga) obj).isStatus();
        int idEstacionamento = ((Vaga) obj).getEstacionamento().getId();
        int idLocal = ((Vaga) obj).getLocal().getId();
        TipoVaga idTipo = ((Vaga) obj).getTipo();
=======
    public void update(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);

        Boolean cobertura = ((Vaga) obj).isCobertura();
        Boolean status = ((Vaga) obj).isStatus();
        int id_estacionamento = ((Vaga) obj).getEstacionamento().getId();
        int id_local = ((Vaga) obj).getLocal().getId();
        TipoVaga id_tipo = ((Vaga) obj).getTipo();
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        int id = ((Vaga) obj).getId();

        statement.setBoolean(1, cobertura);
        statement.setBoolean(2, status);
<<<<<<< HEAD
        statement.setInt(3, idEstacionamento);
        statement.setInt(4, idLocal);
        statement.setInt(5, idTipo.getValue());
=======
        statement.setInt(3, id_estacionamento);
        statement.setInt(4, id_local);
        statement.setInt(5, id_tipo.getValue());
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        statement.setInt(6, id);

        statement.execute();
        this.closeConnection(connection);
<<<<<<< HEAD

    }

    @Override
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Vaga) obj).getId());
        statement.execute();
        this.closeConnection(connection);
=======

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
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }
}
