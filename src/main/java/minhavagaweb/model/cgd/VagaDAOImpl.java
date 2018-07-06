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
import minhavagaweb.model.cdp.Estacionamento;
import minhavagaweb.model.cdp.Localizacao;
import minhavagaweb.model.cdp.TipoVaga;
import minhavagaweb.model.cdp.Vaga;
import minhavagaweb.model.persistencia.Conector;

public class VagaDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM vaga ;";
    private static final String SELECT_1 = "SELECT * FROM vaga WHERE nome = ?;";
    private static final String INSERT = "INSERT INTO vaga (id_vaga,cobertura,"
            + "status,id_estacionamento,id_localizacao,id_id_tipo) VALUES (?,?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM vaga WHERE id_vaga = ?;";
    private static final String UPDATE = "UPDATE vaga SET (cobertura,"
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
    public List<G> getAll() throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT);
                ResultSet result = statement.executeQuery();) {

            Vaga vaga;
            while (result.next()) {
                vaga = new Vaga();
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

                switch (tipo) {
                    case 1:
                        vaga.setTipo(TipoVaga.COMUM);
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

                vagas.add(vaga);
            }
        } finally {
            this.closeConnection(con);
        }
        return (List<G>) vagas;
    }
/*
    public Vaga getOneVaga(String nome) throws SQLException, ClassNotFoundException {
        Vaga vaga = new Vaga();
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_1)) {
            statement.setString(1, nome);
            ResultSet result = statement.executeQuery();

            result.next();

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

            switch (tipo) {
                case 1:
                    vaga.setTipo(TipoVaga.COMUM);
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
        } finally {
            this.closeConnection(con);
        }
        return vaga;
    }
*/
    @Override
    public G getById(int id) throws SQLException, ClassNotFoundException {
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
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
        boolean stat = false;
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT);) {

            Boolean cobertura = ((Vaga) obj).isCobertura();
            Boolean status = ((Vaga) obj).isStatus();
            int idEstacionamento = ((Vaga) obj).getEstacionamento().getId();
            int idLocal = ((Vaga) obj).getLocal().getId();
            TipoVaga idTipo = ((Vaga) obj).getTipo();

            statement.setInt(1, this.getNextId(SELECT + ORDER, ID_VAGA));
            statement.setBoolean(2, cobertura);
            statement.setBoolean(3, status);
            statement.setInt(4, idEstacionamento);
            statement.setInt(5, idLocal);
            statement.setInt(6, idTipo.getValue());

            stat = statement.execute();
        } finally {
            this.closeConnection(con);
        }
        return stat;
    }

    @Override
    public void update(G obj) throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE);) {

            Boolean cobertura = ((Vaga) obj).isCobertura();
            Boolean status = ((Vaga) obj).isStatus();
            int idEstacionamento = ((Vaga) obj).getEstacionamento().getId();
            int idLocal = ((Vaga) obj).getLocal().getId();
            TipoVaga idTipo = ((Vaga) obj).getTipo();
            int id = ((Vaga) obj).getId();

            statement.setBoolean(1, cobertura);
            statement.setBoolean(2, status);
            statement.setInt(3, idEstacionamento);
            statement.setInt(4, idLocal);
            statement.setInt(5, idTipo.getValue());
            statement.setInt(6, id);

            statement.execute();
        } finally {
            this.closeConnection(con);
        }
    }

    @Override
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, ((Vaga) obj).getId());
            statement.execute();
        } finally {
            this.closeConnection(con);
        }
    }
}
