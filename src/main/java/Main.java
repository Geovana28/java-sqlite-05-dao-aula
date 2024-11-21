public class Main {
    public static void main(String[] args) {
        VeiculosDAO veiculosDAO = new VeiculosDAO();

        veiculosDAO.createTable();

        Veiculos veiculo = new Veiculos();
        veiculo.setmarca("Toyota");
        veiculo.setmodelo("Corolla");
        veiculo.setnumeroChassi("12345XYZ");
        veiculo.setplaca("ABC-1234");
        veiculo.setcor("Prata");

        veiculo = veiculosDAO.create(veiculo);

        veiculosDAO.read(veiculo.getId());

        veiculo.setmarca("Toyota Atualizada");
        veiculosDAO.update(veiculo);

        veiculosDAO.read(veiculo.getId());

        veiculosDAO.delete(veiculo);

        veiculosDAO.read(veiculo.getId());
    }
}
