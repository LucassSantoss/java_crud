package org.example;

import jakarta.persistence.EntityManager;
import org.example.persistence.AlunoDAO;
import org.example.persistence.AlunoDAOHibernate;
import org.example.model.Aluno;
import org.example.service.AlunoService;
import org.example.service.JPAUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;

/* Nomes:
Lucas Pereira dos Santos
Tiago Catoia
 */

public class Main {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        EntityManager em = JPAUtil.getEntityManager();
        AlunoDAO dao = new AlunoDAOHibernate(em);
        AlunoService service = new AlunoService(dao);

        boolean rodando = true;
        while (rodando) {
            Scanner scanner = new Scanner(System.in);
            int opcao;
            String menu = STR.
                    """
                    ** CADASTRO DE ALUNOS **

                    1 - Cadastrar aluno
                    2 - Excluir aluno
                    3 - Alterar aluno
                    4 - Buscar aluno pelo nome
                    5 - Listar alunos (com status aprovação)
                    6 - Fim
                    """;
            System.out.print(menu);
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println(" ");
            switch (opcao) {
                case 1 -> {
                    System.out.println("CADASTRO DE ALUNO:");
                    boolean cadastrado = dao.cadastrar(criarAluno(scanner));
                    if (cadastrado) {
                        System.out.println("\nCadastro efetuado com sucesso!");
                    } else {
                        System.out.println("\nAluno não encontrado!");
                    }
                    System.out.println("\n");
                }
                case 2 -> {
                    System.out.println("EXCLUIR ALUNO:");
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    if (dao.remover(nome)) {
                        System.out.println("\nAluno removido com sucesso!\n");
                    } else {
                        System.out.println("\nAluno não encontrado!\n");
                    }
                }
                case 3 -> {
                    System.out.println("ALTERAR ALUNO:");
                    System.out.print("Digite o nome: ");
                    String nomeAntigo = scanner.nextLine();
                    Optional<Aluno> aluno = dao.buscarPorNome(nomeAntigo);
                    if (aluno.isPresent()) {
                        System.out.println("Dados do aluno:");
                        System.out.println(aluno.get());
                        System.out.println("\nNOVOS DADOS:");
                        boolean editado = dao.editar(aluno.get(), criarAluno(scanner));
                        if (editado) {
                            System.out.println("\nAluno editado com sucesso!\n");
                        } else {
                            System.out.println("\nNão foi possível, pois este nome já está cadastrado!\n");
                        }
                    } else {
                        System.out.println("\nAluno não encontrado!\n");
                    }
                }
                case 4 -> {
                    System.out.println("CONSULTAR ALUNO:");
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    Optional<Aluno> aluno = dao.buscarPorNome(nome);
                    if (aluno.isPresent()) {
                        System.out.println("Dados do aluno:");
                        System.out.println(aluno.get());
                    } else {
                        System.out.println("\nAluno não encontrado!\n");
                    }
                }
                case 5 -> {
                    System.out.println("Exibindo todos os alunos: ");
                    List<Aluno> alunos = dao.buscarTodos();
                    if (!alunos.isEmpty()) {
                        System.out.println(service.getAlunosComStatus(alunos));
                    } else {
                        System.out.println("\nNenhum aluno foi cadastrado ainda!\n");
                    }
                }
                case 6 -> {
                    System.out.println("Encerrando programa...");
                    rodando = false;
                }
                default -> {
                    System.out.println("Opção inválida\n");
                }
            }
        }
    }

    public static Aluno criarAluno(Scanner scanner) {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o RA: ");
        String ra = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a nota 1: ");
        BigDecimal nota1 = scanner.nextBigDecimal();
        System.out.print("Digite a nota 2: ");
        BigDecimal nota2 = scanner.nextBigDecimal();
        System.out.print("Digite a nota 3: ");
        BigDecimal nota3 = scanner.nextBigDecimal();

        return new Aluno(nome, ra, email, nota1, nota2, nota3);
    }
}