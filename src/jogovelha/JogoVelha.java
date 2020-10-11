//Marcos Fellipe Andrade da Silva
package jogovelha;

import java.util.Scanner;
import java.security.SecureRandom;

public class JogoVelha {

    public static void main(String[] args) {
        telaI();
    }

    public static void telaI() { //tela inicial
        Scanner in = new Scanner(System.in);
        int resp;
        do {
            char[][] vectM = new char[3][3];
            System.out.println("Jogo da Velha");
            System.out.println("1- Jogar");
            System.out.println("2- Sair");
            System.out.println("Digite sua opção:");
            resp = in.nextInt();
            while (resp != 1 && resp != 2) {
                System.out.println("Resposta inválida.");
                System.out.println("1- Jogar");
                System.out.println("2- Sair");
                System.out.println("Digite sua opção:");
                resp = in.nextInt();
            }
            if (resp == 1) {
                telaP(vectM);
            }
        } while (resp != 2);
    }

    public static void telaP(char[][] vectM) { //tela principal
        Scanner in = new Scanner(System.in);
        int resp;
        System.out.println("Jogo da Velha");
        System.out.println("1- Um jogador");
        System.out.println("2- Dois jogadores");
        System.out.println("Digite sua opção:");
        resp = in.nextInt();
        while (resp != 1 && resp != 2) {
            System.out.println("Resposta inválida.");
            System.out.println("1- Um jogador");
            System.out.println("2- Dois jogadores");
            System.out.println("Digite sua opção:");
            resp = in.nextInt();
        }
        if (resp == 1) {
            umJ(vectM);
        } else {
            doisJ(vectM);
        }
    }

    public static void umJ(char[][] vectM) { //um jogador
        Scanner in = new Scanner(System.in);
        String nome;
        String maquina = "Maquina";
        int resp;
        System.out.println("Insira o nome do jogador humano: ");
        nome = in.nextLine();
        System.out.println("Insira o nível de jogo");
        System.out.println("1- Fácil");
        System.out.println("2- Dificil");
        resp = in.nextInt();
        while (resp != 1 && resp != 2) {
            System.out.println("Resposta inválida.");
            System.out.println("1- Fácil");
            System.out.println("2- Dificil");
            System.out.println("Digite sua opção:");
            resp = in.nextInt();
        }
        sorteioUmJogador(vectM, nome, maquina, resp);
        if (resp == 1) {
            facil(vectM, nome, maquina);
        } else {
            dificil(vectM, nome, maquina);
        }
    }

    public static void facil(char[][] vectM, String jogador, String maquina) { //jogador x computador facil
        Scanner in = new Scanner(System.in);
        int acabou = 0;
        int som = 0;
        int l, c;
        do {
            imprimeMatriz(vectM);
            if (som % 2 == 0) {
                System.out.println(jogador + " é sua vez de jogar");
                l = preencheLinha(in, vectM);
                c = preencheColuna(in, vectM);
                while (isPosicaoPreenchida(l, c, vectM)) {
                    System.out.println("Posição já preenchida");
                    imprimeMatriz(vectM);
                    l = preencheLinha(in, vectM);
                    c = preencheColuna(in, vectM);
                }
            } else {
                System.out.println(maquina + " é sua vez de jogar");
                
                    l = preencheLinha(in, vectM);
                    c = preencheColuna(in, vectM);
            }

            vectM[l][c] = preencheSimbolo(som);
            acabou = isAcabou(vectM, som);
            if (acabou == 1) {
                quemGanhou(jogador, maquina, som);
                break;
            } else if (acabou == -1) {
                System.out.println("O jogo empatou!");
                break;
            }
            som++;
        } while (acabou == 0);
    }

    public static void dificil(char[][] vectM, String nome, String maquina) { //jogador x computador dificil

    }

    public static void doisJ(char[][] vectM) { //dois jogadores
        Scanner in = new Scanner(System.in);
        String nomeA, nomeB;
        System.out.println("Informe o nome do jogador 1: ");
        nomeA = in.nextLine();
        System.out.println("Informe o nome do jogador 2: ");
        nomeB = in.nextLine();
        sorteioDoisJogadores(vectM, nomeA, nomeB);

    }

    public static void sorteioUmJogador(char[][] vectM, String nome, String maquina, int resp) {
        SecureRandom rand = new SecureRandom();
        System.out.println("Sorteando quem vai começar...");
        int comeca = rand.nextInt(2);

        if (comeca == 1) {
            System.out.println("Jogador " + nome + " vai começar!");
            if (resp == 1) {
                facil(vectM, nome, maquina);
            } else {
                dificil(vectM, nome, maquina);
            }
        } else {
            System.out.println(maquina + " vai começar!");
            if (resp == 1) {
                facil(vectM, maquina, nome);
            } else {
                dificil(vectM, maquina, nome);
            }
        }
    }

    public static void sorteioDoisJogadores(char[][] vectM, String nomeA, String nomeB) { //jogador x jogador
        SecureRandom rand = new SecureRandom();
        System.out.println("Sorteando quem vai começar...");
        int comeca = rand.nextInt(2);

        if (comeca == 1) {
            System.out.println("Jogador " + nomeA + " vai começar!");
            jogo(vectM, nomeA, nomeB);
        } else {
            System.out.println("Jogador " + nomeB + " vai começar!");
            jogo(vectM, nomeB, nomeA);
        }
    }

    public static int preencheLinha(Scanner in, char[][] vectM) {

        System.out.println("Insira a linha: ");
        int linha = in.nextInt();
        while (linha < 1 || linha > 3) { //verifica posição valida
            imprimeMatriz(vectM);
            System.out.println("Posição inválida.");
            System.out.println("Insira novamente a linha: ");
            linha = in.nextInt();
        }
        return linha - 1;
    }

    public static int preencheColuna(Scanner in, char[][] vectM) {

        System.out.println("Insira a coluna: ");
        int coluna = in.nextInt();
        while (coluna < 1 || coluna > 3) { //verifica posição valida
            imprimeMatriz(vectM);
            System.out.println("Posição inválida.");
            System.out.println("Insira novamente a coluna: ");
            coluna = in.nextInt();
        }
        return coluna - 1;
    }

    public static boolean isPosicaoPreenchida(int l, int c, char[][] vectM) {
        if (vectM[l][c] == 'X' || vectM[l][c] == 'O') { //verifica posição preenchida
            return true;
        }
        return false;
    }

    public static char preencheSimbolo(int som) {
        if (som % 2 == 0) {
            return 'X';
        }
        return 'O';
    }

    public static boolean isCondicaoVitoriaDiagonalPrincipal(char[][] vectM, int som) { //checa diagonal principal
        char c = preencheSimbolo(som);
        for (int i = 0; i < vectM.length; i++) {
            if (vectM[i][i] != c) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCondicaoVitoriaDiagonalSecundaria(char[][] vectM, int som) {
        char c = preencheSimbolo(som);
        int l = 0;
        for (int i = vectM.length - 1; i >= 0; i--) {//Diagonal secundaria
            if (vectM[l][i] != c) {
                return false;
            }
            l++;
        }
        return true;
    }

    public static boolean isCondicaoVitoriaLinha(char[][] vectM, int som) {
        char c = preencheSimbolo(som);
        for (int i = 0; i < vectM.length; i++) {
            for (int j = 0; j < vectM[i].length; j++) {
                if (vectM[i][j] != c) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCondicaoVitoriaColuna(char[][] vectM, int som) {
        char c = preencheSimbolo(som);
        for (int i = 0; i < vectM.length; i++) {
            for (int j = 0; j < vectM[i].length; j++) {
                if (vectM[j][i] != c) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isEmpate(char[][] vectM) { //verifica se deu empate
        for (int i = 0; i < vectM.length; i++) {
            for (int j = 0; j < vectM[i].length; j++) {
                if (!isPosicaoPreenchida(i, j, vectM)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void imprimeMatriz(char[][] vectM) {
        for (int i = 0; i < vectM.length; i++) {
            for (int j = 0; j < vectM[i].length; j++) {
                if (!isPosicaoPreenchida(i, j, vectM)) {
                    System.out.print("-" + " ");
                } else {
                    System.out.print(vectM[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static int isAcabou(char[][] vectM, int som) { //verifica se a partida acabou
        boolean dp, ds, linha, coluna, empate;
        dp = isCondicaoVitoriaDiagonalPrincipal(vectM, som);
        ds = isCondicaoVitoriaDiagonalSecundaria(vectM, som);
        linha = isCondicaoVitoriaLinha(vectM, som);
        coluna = isCondicaoVitoriaColuna(vectM, som);
        empate = isEmpate(vectM);
        if (dp || ds || linha || coluna) { //houve vencedor
            return 1;
        } else if (empate == true) { //empatou
            return -1;
        }
        return 0; //aconteceu nada
    }

    public static void quemGanhou(String jogUm, String jogDois, int som) {
        if (som % 2 == 0) {
            System.out.println("O jogador " + jogUm + " venceu!");
        } else {
            System.out.println("O jogador " + jogDois + " venceu!");
        }
    }

    public static void jogo(char[][] vectM, String jogUm, String jogDois) {
        Scanner in = new Scanner(System.in);
        int acabou = 0;
        int som = 0;
        int l, c;
        do {
            imprimeMatriz(vectM);
            if (som % 2 == 0) {
                System.out.println(jogUm + " é sua vez de jogar");
            } else {
                System.out.println(jogDois + " é sua vez de jogar");
            }
            l = preencheLinha(in, vectM);
            c = preencheColuna(in, vectM);
            while (isPosicaoPreenchida(l, c, vectM)) {
                System.out.println("Posição já preenchida");
                imprimeMatriz(vectM);
                l = preencheLinha(in, vectM);
                c = preencheColuna(in, vectM);
            }
            vectM[l][c] = preencheSimbolo(som);
            acabou = isAcabou(vectM, som);
            if (acabou == 1) {
                quemGanhou(jogUm, jogDois, som);
                break;
            } else if (acabou == -1) {
                System.out.println("O jogo empatou!");
                break;
            }
            som++;
        } while (acabou == 0);
    }
}
