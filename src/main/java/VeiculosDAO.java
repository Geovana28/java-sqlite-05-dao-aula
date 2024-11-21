import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VeiculosDAO {
    public void createTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            // Utilizar a fábrica de conexões para criar uma Connection SQL:
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connection = connectionFactory.createConnection();

            // Criar um statement baseado em uma string SQL:
            String createTableSQL = """
                      CREATE TABLE IF NOT EXISTS Veiculos (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        marca VARCHAR(256) NOT NULL,
                        modelo VARCHAR(256) NOT NULL,
                        numeroChassi VARCHAR(256) NOT NULL,
                        placa VARCHAR(256) NOT NULL,
                        cor VARCHAR(256) NOT NULL
                      );
                    """;
            statement = connection.createStatement();
            statement.execute(createTableSQL);
            System.out.println("Tabela 'Veiculos' criada ou já existe!");
        } catch (SQLException e) {
            System.err.println("Erro na comunicação com o banco de dados!");
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Veiculos create(Veiculos Veiculos) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Utilizar a fábrica de conexões para criar uma Connection SQL:
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connection = connectionFactory.createConnection();

            // Criar um preparedStatement baseado em uma string SQL:
            String insertSQL = "INSERT INTO Veiculos (marca, modelo, numeroChassi, placa , cor) values (?, ? , ? , ? ,? )";
            preparedStatement = connection.prepareStatement(insertSQL);

            // Preencher os valores no PreparedStatement:
            preparedStatement.setString(1, Veiculos.getmarca());
            preparedStatement.setString(2, Veiculos.getmodelo());
            preparedStatement.setString(3, Veiculos.getnumeroChassi());
            preparedStatement.setString(4, Veiculos.getplaca());
            preparedStatement.setString(5, Veiculos.getcor());
            

            // Executar o comando SQL:
            preparedStatement.execute();
            Veiculos.setId(this.readLastInsertedId(connection));

            System.out.println(
                    "VEICULO GRAVADO NO BANCO DE DADOS: " +
                            "\nID: " + Veiculos.getId() +
                            "\nmarca: " + Veiculos.getmarca() +
                            "\nModelo: " + Veiculos.getmodelo()+
                            "\nNumero do Chassi: " + Veiculos.getnumeroChassi()+
                            "\nPlaca: " + Veiculos.getplaca()+
                            "\nCor: " + Veiculos.getcor());


        } catch (SQLException e) {
            System.err.println("Erro na comunicação com o banco de dados!");
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Veiculos;
    }

    public int readLastInsertedId(Connection connection) {
        int lastInsertedId = -1;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Criar um preparedStatement baseado em uma string SQL:
            String selectSQL = "SELECT MAX(id) AS max_id FROM Veiculos";
            preparedStatement = connection.prepareStatement(selectSQL);

            // Executar o comando SQL:
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt("max_id");
            } else {
                System.out.println("Não foi possível recuperar o último identificador gerado pelo banco de dados!");
            }
        } catch (SQLException e) {
            System.err.println("Erro na comunicação com o banco de dados!");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lastInsertedId;
    }

    public Veiculos read(int id) {
        Veiculos Veiculos = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Utilizar a fábrica de conexões para criar uma Connection SQL:
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connection = connectionFactory.createConnection();

            // Criar um preparedStatement baseado em uma string SQL:
            String selectSQL = "SELECT * FROM Veiculos WHERE id = ?";
            preparedStatement = connection.prepareStatement(selectSQL);

            // Preencher o valor do identificador no PreparedStatement:
            preparedStatement.setInt(1, id);

            // Executar o comando SQL:
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Veiculos = new Veiculos();
                Veiculos.setId(resultSet.getInt("id"));
                Veiculos.setmarca(resultSet.getString("Marca"));
                Veiculos.setmodelo(resultSet.getString("Modelo"));
                Veiculos.setnumeroChassi(resultSet.getString("Numero Chassi"));
                Veiculos.setplaca(resultSet.getString("Placa"));
                Veiculos.setcor(resultSet.getString("Cor"));                
                
                System.out.println(
                        "VEICULO LIDO DO BANCO DE DADOS: " +
                                "\nID: " + Veiculos.getId() +
                                "\nMarca: " + Veiculos.getmarca() +
                                "\nModelo: " + Veiculos.getModelo()+
                                "\nNumero do Chassi: " + Veiculos.getnumeroChassi()+
                                "\nPlaca: " + Veiculos.getplaca()+
                                "\nCor: " + Veiculos.getcor());
            } else {
                System.out.println("Veiculo não encontrado!");
            }
        } catch (SQLException e) {
            System.err.println("Erro na comunicação com o banco de dados!");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Veiculos;
    }

    public void update(Veiculos Veiculos) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Utilizar a fábrica de conexões para criar uma Connection SQL:
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connection = connectionFactory.createConnection();

            // Criar um preparedStatement baseado em uma string SQL:
            String updateSQL = "UPDATE Veiculos SET marca = ?, modelo = ? , numeroChassi = ? , placa = ? , cor = ? , WHERE id = ?";
            preparedStatement = connection.prepareStatement(updateSQL);

            // Preencher os valores no PreparedStatement:
            preparedStatement.setString(1, Veiculos.getmarca());
            preparedStatement.setString(2, Veiculos.getModelo());
            preparedStatement.setString(3, Veiculos.getnumeroChassi());
            preparedStatement.setString(4, Veiculos.getplaca());
            preparedStatement.setString(5, Veiculos.getcor());          
            preparedStatement.setInt(6, Veiculos.getId());

            // Executar o comando SQL:
            preparedStatement.executeUpdate();

            System.out.println("O veiculo " + Veiculos.getmarca() + " foi atualizado no banco de dados!");
        } catch (SQLException e) {
            System.err.println("Erro na comunicação com o banco de dados!");
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Veiculos Veiculos) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Utilizar a fábrica de conexões para criar uma Connection SQL:
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connection = connectionFactory.createConnection();

            // Criar um preparedStatement baseado em uma string SQL:
            String deleteSQL = "DELETE FROM Veiculos WHERE id = ?";
            preparedStatement = connection.prepareStatement(deleteSQL);

            // Preencher os valores no PreparedStatement:
            preparedStatement.setInt(1, Veiculos.getId());

            // Executar o comando SQL:
            preparedStatement.execute();

            System.out.println("O Veiculos " + Veiculos.getmarca() + " foi removido do BD.");
        } catch (SQLException e) {
            System.err.println("Erro na comunicação com o banco de dados!");
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}