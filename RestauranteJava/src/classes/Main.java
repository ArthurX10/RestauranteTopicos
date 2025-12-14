package main;

import DAO.PratoDAO;
import DAO.PedidoDao;
import Classes.Prato;
import classes.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PratoDAO pratoDAO = new PratoDAO();
        PedidoDao pedidoDAO = new PedidoDao();

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE RESTAURANTE =====");
            System.out.println("1 - Cadastrar prato");
            System.out.println("2 - Listar pratos");
            System.out.println("3 - Pesquisar prato");
            System.out.println("4 - Atualizar prato");
            System.out.println("5 - Excluir prato");
            System.out.println("6 - Criar pedido");
            System.out.println("7 - Listar pedidos");
            System.out.println("8 - Atualizar pedido");
            System.out.println("9 - Excluir pedido");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1 -> cadastrarPrato(sc, pratoDAO);
                case 2 -> listarPratos(pratoDAO);
                case 3 -> pesquisarPrato(sc, pratoDAO);
                case 4 -> atualizarPrato(sc, pratoDAO);
                case 5 -> excluirPrato(sc, pratoDAO);
                case 6 -> criarPedido(sc, pratoDAO, pedidoDAO);
                case 7 -> listarPedidos(pedidoDAO);
                case 8 -> atualizarPedido(sc, pratoDAO, pedidoDAO);
                case 9 -> excluirPedido(sc, pedidoDAO);
                case 0 -> System.out.println("Sistema encerrado.");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }

    private static void cadastrarPrato(Scanner sc, PratoDAO dao) {

        Prato p = new Prato();

        System.out.print("Nome: ");
        p.setNome(sc.nextLine());

        System.out.print("Categoria: ");
        p.setCategoria(sc.nextLine());

        System.out.print("Preço: ");
        p.setPreco(sc.nextDouble());
        sc.nextLine();

        System.out.print("Disponível (true/false): ");
        p.setDisp(sc.nextBoolean());
        sc.nextLine();

        System.out.print("Descrição: ");
        p.setDescricao(sc.nextLine());

        dao.adicionarPrato(p);
        System.out.println("Prato cadastrado com sucesso!");
    }

    private static void listarPratos(PratoDAO dao) {
        List<Prato> pratos = dao.listarPrato();
        System.out.println("\n--- PRATOS ---");
        pratos.forEach(System.out::println);
    }

    private static void pesquisarPrato(Scanner sc, PratoDAO dao) {
        System.out.print("Código do prato: ");
        int codigo = sc.nextInt();

        Prato prato = dao.pesquisarPrato(codigo);
        if (prato == null) {
            System.out.println("Prato não encontrado.");
        } else {
            System.out.println(prato);
        }
    }

    private static void atualizarPrato(Scanner sc, PratoDAO dao) {

        System.out.print("Código do prato: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        Prato prato = dao.pesquisarPrato(codigo);
        if (prato == null) {
            System.out.println("Prato não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        prato.setNome(sc.nextLine());

        System.out.print("Nova categoria: ");
        prato.setCategoria(sc.nextLine());

        System.out.print("Novo preço: ");
        prato.setPreco(sc.nextDouble());
        sc.nextLine();

        System.out.print("Disponível (true/false): ");
        prato.setDisp(sc.nextBoolean());
        sc.nextLine();

        System.out.print("Nova descrição: ");
        prato.setDescricao(sc.nextLine());

        dao.atualizarPrato(prato);
        System.out.println("Prato atualizado!");
    }

    private static void excluirPrato(Scanner sc, PratoDAO dao) {
        System.out.print("Código do prato: ");
        int codigo = sc.nextInt();
        dao.deletarPrato(codigo);
        System.out.println("Prato excluído!");
    }

    private static void criarPedido(Scanner sc, PratoDAO pratoDAO, PedidoDao pedidoDAO) {

        Pedido pedido = new Pedido();

        System.out.print("Nome do cliente: ");
        pedido.setCliente(sc.nextLine());

        System.out.print("Código do prato: ");
        int codPrato = sc.nextInt();
        sc.nextLine();

        Prato prato = pratoDAO.pesquisarPrato(codPrato);
        if (prato == null) {
            System.out.println("Prato não encontrado!");
            return;
        }

        pedido.setPrato(prato);

        System.out.print("Quantidade: ");
        pedido.setQuantidade(sc.nextInt());
        sc.nextLine();

        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus("PENDENTE");

        pedidoDAO.create(pedido);
        System.out.println("Pedido criado com sucesso!");
    }

    private static void listarPedidos(PedidoDao dao) {

        List<Pedido> pedidos = dao.read();

        System.out.println("\n--- PEDIDOS ---");
        for (Pedido p : pedidos) {
            System.out.println(
                "ID: " + p.getIdPedido() +
                " | Cliente: " + p.getCliente() +
                " | Prato (código): " + p.getPrato().getCodigo() +
                " | Quantidade: " + p.getQuantidade() +
                " | Data/Hora: " + p.getDataHora() +
                " | Status: " + p.getStatus()
            );
        }
    }

    private static void atualizarPedido(Scanner sc, PratoDAO pratoDAO, PedidoDao dao) {

        Pedido pedido = new Pedido();

        System.out.print("ID do pedido: ");
        pedido.setIdPedido(sc.nextInt());
        sc.nextLine();

        System.out.print("Nome do cliente: ");
        pedido.setCliente(sc.nextLine());

        System.out.print("Quantidade: ");
        pedido.setQuantidade(sc.nextInt());
        sc.nextLine();

        System.out.print("Status: ");
        pedido.setStatus(sc.nextLine());

        System.out.print("Código do prato: ");
        int codPrato = sc.nextInt();
        sc.nextLine();

        Prato prato = pratoDAO.pesquisarPrato(codPrato);
        pedido.setPrato(prato);

        pedido.setDataHora(LocalDateTime.now());

        dao.update(pedido);
        System.out.println("Pedido atualizado!");
    }

    private static void excluirPedido(Scanner sc, PedidoDao dao) {
        System.out.print("ID do pedido: ");
        int id = sc.nextInt();
        dao.delete(id);
        System.out.println("Pedido excluído!");
    }
}
