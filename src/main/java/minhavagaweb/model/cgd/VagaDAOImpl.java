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
import java.util.logging.Level;
import java.util.logging.Logger;
import minhavagaweb.model.cdp.Estacionamento;
import minhavagaweb.model.cdp.Localizacao;
import minhavagaweb.model.cdp.TipoVaga;
import minhavagaweb.model.cdp.Vaga;
import minhavagaweb.model.utilitarioPersistencia.Conector;

public class VagaDAOImpl<GENERICTYPE> implements GenericDAO<GENERICTYPE> {

    private static final String SELECT = "SELECT * FROM vaga ";
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

    List<Vaga> vagas = new ArrayList<>();

    @Override
    public List<GENERICTYPE> getAll() {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
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
                    Localizacao local;
                    local = (Localizacao) dao2.getById(result.getInt(VagaDAOImpl.ID_LOCAL));
                    vaga.setLocal(local);
                    int tipo = result.getInt(this.ID_TIPO);

                    switch (tipo){
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
            } catch (SQLException ex) {
                Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (List<GENERICTYPE>) vagas;
    }

    @Override
    public GENERICTYPE getById(int id) {
        Vaga vaga = null;
        if (vagas.isEmpty()) {
            vagas = (List<Vaga>) this.getAll();
        }
        for (Vaga p : vagas) {
            if (p.getId() == id) {
                vaga = p;
            }
        }
        return (GENERICTYPE) vaga;
    }

    @Override
    public void insert(GENERICTYPE obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {

                Boolean cobertura = ((Vaga) obj).isCobertura();
                Boolean status = ((Vaga) obj).isStatus();
                int idEstacionamento = ((Vaga) obj).getEstacionamento().getId();
                int idLocal = ((Vaga) obj).getLocal().getId();
                TipoVaga idTipo = ((Vaga) obj).getTipo();

                statement.setInt(1, this.getNextId());
                statement.setBoolean(2, cobertura);
                statement.setBoolean(3, status);
                statement.setInt(4, idEstacionamento);
                statement.setInt(5, idLocal);
                statement.setInt(6, idTipo.getValue());

                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GENERICTYPE obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {

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
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(GENERICTYPE obj) {
        try (Connection connection = Conector.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, ((Vaga) obj).getId());
            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getNextId() {
        int res = -0;
        String order = "ORDER BY id_vaga ASC;";
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT + order,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                statement.execute();
                ResultSet result = statement.executeQuery();
                if (result.last()) {
                    res = result.getInt(this.ID_VAGA);
                    return res + 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

}
