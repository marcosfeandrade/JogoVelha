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
    }

    public static boolean checarVitoriaMaquinaOuJogador(char[][] vectM, int som) {
        boolean vitoriaMaquina = true;
        boolean compPreencheLinha = computadorPreencheLinha(vectM, som, vitoriaMaquina);
        if (compPreencheLinha) {
            return true;
        }
        boolean compPreencheColuna = computadorPreencheColuna(vectM, som, vitoriaMaquina);
        if (compPreencheColuna) {
            return true;
        }
        boolean compPreencheDP = computadorPreencheDP(vectM, som, vitoriaMaquina);
        if (compPreencheDP) {
            return true;
        }
        boolean compPreencheDS = computadorPreencheDS(vectM, som, vitoriaMaquina);
        if (compPreencheDS) {
            return true;
        }
        vitoriaMaquina = false;
        boolean compPreencheLinhaJ = computadorPreencheLinha(vectM, som, vitoriaMaquina);
        if (compPreencheLinhaJ) {
            return true;
        }
        boolean compPreencheColunaJ = computadorPreencheColuna(vectM, som, vitoriaMaquina);
        if (compPreencheColunaJ) {
            return true;
        }
        boolean compPreencheDPJ = computadorPreencheDP(vectM, som, vitoriaMaquina);
        if (compPreencheDPJ) {
            return true;
        }
        boolean compPreencheDSJ = computadorPreencheDS(vectM, som, vitoriaMaquina);
        if (compPreencheDSJ) {
            return true;
        }
        return false;
    }

    public static void facil(char[][] vectM, String jogador, String maquina, boolean isJogadorComeca) { //jogador x computador facil
        Scanner in = new Scanner(System.in);
        SecureRandom rand = new SecureRandom();
        int acabou = 0;
        int som = 0;
        int l, c;
        int lMaquina;
        int cMaquina;
        imprimeMatriz(vectM);
        do {
            if (!isJogadorComeca) {
                System.out.println(maquina + " é sua vez de jogar!");

                do {
                    lMaquina = rand.nextInt(3);
                    cMaquina = rand.nextInt(3);
                } while (isPosicaoPreenchida(lMaquina, cMaquina, vectM));
                vectM[lMaquina][cMaquina] = preencheSimbolo(som);
                isJogadorComeca = true;
            } else {
                System.out.println(jogador + " é sua vez de jogar!");
                l = preencheLinha(in, vectM);
                c = preencheColuna(in, vectM);
                while (isPosicaoPreenchida(l, c, vectM)) {
                    System.out.println("Posição já preenchida");
                    imprimeMatriz(vectM);
                    l = preencheLinha(in, vectM);
                    c = preencheColuna(in, vectM);
                }
                vectM[l][c] = preencheSimbolo(som);
                isJogadorComeca = false;
            }
            imprimeMatriz(vectM);
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

    public static void dificil(char[][] vectM, String jogador, String maquina, boolean isJogadorComeca) { //jogador x computador dificil
        Scanner in = new Scanner(System.in);
        SecureRandom rand = new SecureRandom();
        int acabou = 0;
        int som = 0;
        int l, c;
        int lMaquina;
        int cMaquina;
        imprimeMatriz(vectM);
        do {
            if (!isJogadorComeca) { //maquina = 'X' && som % 2 = 0
                System.out.println(maquina + " é sua vez de jogar!");
                if (!checarVitoriaMaquinaOuJogador(vectM, som)) {
                    do {
                        lMaquina = rand.nextInt(3);
                        cMaquina = rand.nextInt(3);
                        System.out.println("sem condição bixo");
                    } while (isPosicaoPreenchida(lMaquina, cMaquina, vectM));
                    vectM[lMaquina][cMaquina] = preencheSimbolo(som);
                }
                isJogadorComeca = true;
            } else {
                System.out.println(jogador + " é sua vez de jogar!");
                l = preencheLinha(in, vectM);
                c = preencheColuna(in, vectM);
                while (isPosicaoPreenchida(l, c, vectM)) {
                    System.out.println("Posição já preenchida");
                    imprimeMatriz(vectM);
                    l = preencheLinha(in, vectM);
                    c = preencheColuna(in, vectM);
                }
                vectM[l][c] = preencheSimbolo(som);
                isJogadorComeca = false;
            }
            imprimeMatriz(vectM);
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

    public static boolean computadorPreencheLinha(char[][] vectM, int som, boolean vitoriaMaquina) {
        char s;
        int vazioI = -1;
        int vazioJ = -1;
        int contSimbolo = 0;

        for (int i = 0; i < vectM.length; i++) {

            if (vitoriaMaquina) {
                s = preencheSimbolo(som);
            } else {
                s = preencheSimbolo(som + 1);
            }
            for (int j = 0; j < vectM[i].length; j++) {

                if (vectM[i][j] == s) {
                    contSimbolo++;
                }
                if (!isPosicaoPreenchida(i, j, vectM)) {
                    vazioI = i;
                    vazioJ = j;
                }
            }
            s = preencheSimbolo(som);
            if (contSimbolo >= 2 && vazioI >= 0 && vazioJ >= 0) {
                System.out.println("Condiçao linha");
                vectM[vazioI][vazioJ] = s;
                return true;
            }
            contSimbolo = 0;
            vazioI = -1;
            vazioJ = -1;
        }
        return false;
    }

    public static boolean computadorPreencheColuna(char[][] vectM, int som, boolean vitoriaMaquina) {
        char s;

        int vazioI = -1;
        int vazioJ = -1;
        int contSimbolo = 0;
        for (int i = 0; i < vectM.length; i++) {
            if (vitoriaMaquina) {
                s = preencheSimbolo(som);
            } else {
                s = preencheSimbolo(som + 1);
            }
            for (int j = 0; j < vectM[i].length; j++) {
                if (vectM[j][i] == s) {
                    contSimbolo++;
                }
                if (!isPosicaoPreenchida(j, i, vectM)) {
                    vazioI = i;
                    vazioJ = j;
                }
            }
            s = preencheSimbolo(som);
            if (contSimbolo >= 2 && vazioI >= 0 && vazioJ >= 0) {
                System.out.println("Condiçao coluna");

                vectM[vazioJ][vazioI] = s;
                return true;
            }
            contSimbolo = 0;
            vazioI = -1;
            vazioJ = -1;
        }
        return false;
    }

    public static boolean computadorPreencheDP(char[][] vectM, int som, boolean vitoriaMaquina) {
        char s;
        if (vitoriaMaquina) {
            s = preencheSimbolo(som);
        } else {
            s = preencheSimbolo(som + 1);
        }
        int vazio = -1;
        int contSimbolo = 0;
        for (int i = 0; i < vectM.length; i++) {
            if (vectM[i][i] == s) {
                contSimbolo++;
            }
            if (!isPosicaoPreenchida(i, i, vectM)) {
                vazio = i;

            }
        }
        s = preencheSimbolo(som);
        if (contSimbolo >= 2 && vazio >= 0) {
            System.out.println("Condiçao dp");
            vectM[vazio][vazio] = s;
            return true;
        }
        return false;
    }

    public static boolean computadorPreencheDS(char[][] vectM, int som, boolean vitoriaMaquina) {
        char s;
        if (vitoriaMaquina) {
            s = preencheSimbolo(som);
        } else {
            s = preencheSimbolo(som + 1);
        }
        int l = 0;
        int contSimbolo = 0;
        int vazioI = -1;
        int vazioL = -1;
        for (int i = vectM.length - 1; i >= 0; i--) {
            if (vectM[l][i] == s) {
                contSimbolo++;
            }
            if (!isPosicaoPreenchida(l, i, vectM)) {
                vazioI = i;
                vazioL = l;
            }
            l++;
        }
        s = preencheSimbolo(som);
        if (contSimbolo >= 2 && vazioI >= 0 && vazioL >= 0) {
            System.out.println("Condiçao ds");

            vectM[vazioL][vazioI] = s;
            return true;
        }
        return false;
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
        boolean isJogadorComeca = rand.nextBoolean();

        if (isJogadorComeca) {
            System.out.println("Jogador " + nome + " vai começar!");
            if (resp == 1) {
                facil(vectM, nome, maquina, isJogadorComeca);
            } else {
                dificil(vectM, nome, maquina, isJogadorComeca);
            }
        } else {
            System.out.println(maquina + " vai começar!");
            if (resp == 1) {
                facil(vectM, nome, maquina, isJogadorComeca);
            } else {
                dificil(vectM, nome, maquina, isJogadorComeca);
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
        int ctd = 0;
        for (int i = 0; i < vectM.length; i++) {
            if (vectM[i][i] == c) {
                ctd++;
            }
        }
        if (ctd == 3) {
            return true;
        }
        return false;
    }

    public static boolean isCondicaoVitoriaDiagonalSecundaria(char[][] vectM, int som) {
        char c = preencheSimbolo(som);
        int l = 0;
        int ctd = 0;
        for (int i = vectM.length - 1; i >= 0; i--) {//Diagonal secundaria
            if (vectM[l][i] == c) {
                ctd++;
            }
            l++;
        }
        if (ctd == 3) {
            return true;
        }
        return false;
    }

    public static boolean isCondicaoVitoriaLinha(char[][] vectM, int som) {
        char c = preencheSimbolo(som);
        int ctd = 0;
        for (int i = 0; i < vectM.length; i++) {
            for (int j = 0; j < vectM[i].length; j++) {
                if (vectM[i][j] == c) {
                    ctd++;
                }
            }
            if (ctd == 3) {
                return true;
            }
            ctd = 0;
        }
        return false;
    }

    public static boolean isCondicaoVitoriaColuna(char[][] vectM, int som) {
        char c = preencheSimbolo(som);
        int ctd = 0;
        for (int i = 0; i < vectM.length; i++) {
            for (int j = 0; j < vectM[i].length; j++) {
                if (vectM[j][i] == c) {
                    ctd++;
                }
            }
            if (ctd == 3) {
                return true;
            }
            ctd = 0;
        }
        return false;
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
        } else if (empate) { //empatou
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

        imprimeMatriz(vectM);
        do {
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

            imprimeMatriz(vectM);
            som++;
        } while (acabou == 0);
    }
}
