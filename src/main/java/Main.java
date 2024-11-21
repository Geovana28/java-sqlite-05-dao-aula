public class Main {
    public static void main(String[] args) {
        // Criar o DAO para conexão com o banco de dados:
       VeiculosDAOVeiculosDAO = newVeiculosDAO();

        //Criar a tabelaVeiculos no banco de dados:
       // adicionar  VeiculosDAO.createTable();
        
        // Criar umVeiculos:
       VeiculosVeiculos = newVeiculos();
       Veiculos.setnome("Ana");
       Veiculos.setNascimento("2000-06-07");

        // Salvar oVeiculos no banco de dados:
       Veiculos =VeiculosDAO.create(veiculos);

        // Ler as informações cadastradas no banco de dados:
       VeiculosDAO.read(veiculos.getId());

        // Atualizar as informações doVeiculos:
       Veiculos.setnome("Ana Silva"); 
       VeiculosDAO.update(veiculos); 

        // Ler as informações atualizadas no banco de dados:
       VeiculosDAO.read(veiculos.getId());

        // Remover o usário:
       VeiculosDAO.delete(veiculos);

        // Verificar se as informações foram mesmo removidas:
       VeiculosDAO.read(veiculos.getId());
    }
}