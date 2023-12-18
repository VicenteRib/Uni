/**
* @author 65385 DIOGO BELFO d.belfo@campus.fct.unl.pt
* @author 66188 VICENTE RIBEIRO vg.ribeiro@campus.fct.unl.pt
*/
#include<stdio.h>
#include<stdlib.h>
#include <string.h>
#include <ctype.h>

#include"gerente.h"
#include"student.h"
#include"quarto.h"
#include"uni.h"
#include"dicionario.h"
#include"sequencia.h"
#include"iterador.h"

#define LINE1 250
#define MAX_CHAR 50
#define MAX_LOGIN 20
#define COMMAND 3
#define DESCRICAO 200

/***********************************************
comandos - interpreta o input dado.
Parametros:
     comando(string) - primeiras duas letras que definem a função
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 1 caso não seja utilizada a função de saída, 0 caso seja utilizada a função de saída.
Pre-condicoes: comando != {'/0'} && a != NULL && line1 != {'/0'}
***********************************************/
int comandos(char * comando, universidade a, char * line1);

/***********************************************
inserirEstudante - inserir estudante.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void inserirEstudante(universidade a, char * line1);

/***********************************************
dadosEstudante - dados do estudante.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void dadosEstudante(universidade a, char * line1);
/***********************************************
inserirGerente - inserir gerente.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void inserirGerente(universidade a, char * line1);

/***********************************************
dadosGerente - dados do gerente.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void dadosGerente(universidade a, char * line1);

/***********************************************
inserirQuarto - inserir quarto.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void inserirQuarto(universidade a, char * line1);

/***********************************************
dadosQuarto - dados do Quarto.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void dadosQuarto(universidade a, char * line1);

/***********************************************
modificarQuarto - modificar quarto.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void modificarQuarto(universidade a , char * line1);

/***********************************************
removerQuarto - remover quarto.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void removerQuarto(universidade a, char * line1);

/***********************************************
inserirCandidatos - inserir candidatura.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void inserirCandidatos(universidade a, char * line1);

/***********************************************
aceitarCandidatura - aceitar candidatura.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void aceitarCandidatura(universidade a, char * line1);

/***********************************************
listaCandidaturas - lista de candidaturas.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void listaCandidaturas(universidade a, char * line1);

/***********************************************
listaQuartos - lista de Quartos.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void listaQuartos(universidade a);

char * cortaEspacoVazio(char * str);

/***********************************************
listaQuartosLocaisLivres - lista de quartos locais livres.
Parametros:
     a(universidade) - universidade
     line1 - primeira linha de input
Retorno: 
Pre-condicoes: a != NULL && line1 != {'/0'}
***********************************************/
void listaQuartosLocaisLivres(universidade a, char * line1);

void top3gerentes(universidade a);

void quickSort(void * v[],int esq,int dir, void* (*daChave)(void*), int (*compChaves)(void*,void*));

void troca(void* *a, void* *b);

int main() {
    universidade a = (universidade) criaUniversidade();
    char comando[COMMAND];
    char line1[LINE1];
    char buffer[LINE1];
    do {
        printf("> ");
        fgets(line1, sizeof(line1), stdin); 
        strcpy(buffer,line1);
        strncpy(comando, line1, 2);
        comando[2]='\0';
    } while (comandos(comando,a,buffer)==1);
}

int comandos(char * comando, universidade a,char * line1){
    char comment;
    sscanf(comando, "%c", &comment);
    if(comment == '#') {
        printf("\n\n"); 
        return 1;
    }
    if (!strcmp(comando, "IE")) {
        inserirEstudante(a,line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "DE")) {
        dadosEstudante(a,line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "IG")) {
        inserirGerente(a,line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "DG")) {
        dadosGerente(a,line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "IQ")) {
        inserirQuarto(a,line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "DQ")) {
        dadosQuarto(a,line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "MQ")) {
        modificarQuarto(a,line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "RQ")) {
        removerQuarto(a,line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "IC")) {
        inserirCandidatos(a,line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "AC")) {
        aceitarCandidatura(a, line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "LC")) {
        listaCandidaturas(a, line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "LQ")) {
        listaQuartos(a);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "LL")) {
        listaQuartosLocaisLivres(a,line1);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "LT")) {
        top3gerentes(a);
        printf("\n");
        return 1;
    } else if (!strcmp(comando, "XS")) {
        printf("Obrigado. Ate a proxima.\n\n");
        return 0;
    } else {
        printf("Comando invalido.\n\n"); 
        return 1;}
}
void troca(void* *a, void* *b) {
  void* t = *a;
  *a = *b;
  *b = t;
}

/* ordenação de um vector de elementos, de esq a dir */
void quickSort(void * v[],int esq,int dir,
               void*    (*daChave)(void*),
               int (*compChaves)(void*,void*))
{
    int i = esq;
    int j = dir;
    void * p = daChave(v[(i+j) / 2]); //pivot é o ponto médio
    do {
        while (compChaves(daChave(v[i]),p) < 0) // v[i]<p
            i++;
        while (compChaves(p,daChave(v[j])) < 0) // p<v[j]
            j--;
        if (i <= j){
            troca(&v[i++], &v[j--]);  // troca
        }
    } while (i<=j);
    if (esq<j)
        quickSort(v,esq,j,daChave,compChaves);
    if (i<dir)
        quickSort(v,i,dir,daChave,compChaves);
}

void inserirEstudante(universidade a, char * line1){
    char c[COMMAND];
    char nomeEstudante[MAX_CHAR];
    char local[MAX_CHAR];
    int idade;
    char buffer[DESCRICAO];
    char uni[MAX_CHAR];
    char login[MAX_LOGIN];

    sscanf(line1, "%s %s %[^\n]%*C", c, login, nomeEstudante);
    fgets(buffer, sizeof buffer, stdin);
    sscanf(buffer, "%d %s\n", &idade, local);
    fgets(uni, sizeof uni, stdin);
    cortaEspacoVazio(nomeEstudante);
    cortaEspacoVazio(uni);
    if(loginExiste(a, login)==1){
        printf("Utilizador ja existente.\n");
        return;
    }
    adicionarEstudante(a, login, uni, nomeEstudante, idade, local);
    printf("Registo de estudante executado.\n");
    return;
}

void dadosEstudante(universidade a, char * line1){
    char login[MAX_LOGIN];
    char c[COMMAND];
    char nome[MAX_CHAR];
    char local[MAX_CHAR];
    char uni[MAX_CHAR];
    int idade;

    sscanf(line1,"%s %s\n", c, login);
    if(estudanteExiste(a, login)==1){
        student b = daEstudante(a,login);
        daDadosStudent(b,nome,&idade,local,uni);
        printf("%s, %s, %d anos, %s\n", login, nome, idade, local);
        printf("%s\n", uni);
        return;
        }
        printf("Inexistencia do estudante referido.\n");
}

void inserirGerente(universidade a, char * line1){
    char c[COMMAND];
    char uni[MAX_CHAR];
    char login[MAX_LOGIN];
    char nomeGerente[MAX_CHAR];

    sscanf(line1, "%s %s %[^\n]%*C", c, login, nomeGerente);
    fgets(uni, sizeof uni, stdin);
    cortaEspacoVazio(uni);
    cortaEspacoVazio(nomeGerente);

    if(loginExiste(a, login)==1){
        printf("Utilizador ja existente.\n");
        return;
    }
    adicionarGerente(a, login, uni, nomeGerente);
    printf("Registo de gerente executado.\n");
    return;
}

void dadosGerente(universidade a, char * line1){
    char c[COMMAND];
    char uni[MAX_CHAR];
    char login[MAX_LOGIN];
    char nome[MAX_CHAR];
    sscanf(line1,"%s %s\n", c, login);
    if(gerenteExiste(a, login)==1){
        gerente b = daGerente(a,login);
        daDadosGerente(b,nome,uni);
        printf("%s, %s\n", login, nome);
        printf("%s\n", uni);
        return;
    }
    printf("Inexistencia do gerente referido.\n");
    return;
}

void inserirQuarto(universidade a, char * line1){
    char loginGer[MAX_LOGIN];
    char codigo[MAX_LOGIN];
    char c[COMMAND];
    char nomeRes[MAX_CHAR];
    char local[MAX_CHAR];
    char uni[MAX_CHAR];
    char desc[DESCRICAO];
    char buffer[DESCRICAO];
    int andar;

    sscanf(line1,"%s %s %s\n", c, codigo, loginGer);
    fgets(nomeRes, sizeof nomeRes, stdin);
    fgets(uni, sizeof uni, stdin);
    fgets(local, sizeof local, stdin);
    fgets(buffer, sizeof buffer, stdin);
    sscanf(buffer,"%d",&andar);
    fgets(buffer, sizeof buffer, stdin);
    sscanf(buffer," %[^\n]%*c",desc);
    cortaEspacoVazio(uni);
    cortaEspacoVazio(local);
    cortaEspacoVazio(nomeRes);

    if(quartoExiste(a,codigo)){
        printf("Quarto existente.\n");
        return;
    }
    if(gerenteExiste(a, loginGer)==1){
        gerente b = daGerente(a,loginGer);
        if(verificarUniversidadeGerente(b, uni)){
            adicionarQuarto(a, codigo, loginGer, uni, nomeRes, desc , andar, local);
            printf("Registo de quarto executado.\n");
            return;
        }
        printf("Operacao nao autorizada.\n");
        return;
    }
    printf("Inexistencia do gerente referido.\n");
}

void dadosQuarto(universidade a, char * line1){
    char codigo[MAX_LOGIN];
    char nomeRes[MAX_CHAR];
    char local[MAX_CHAR];
    char uni[MAX_CHAR];
    char c[MAX_CHAR];
    char desc[DESCRICAO];
    char ocupacao[MAX_LOGIN];
    int andar;

    sscanf(line1,"%s %s\n", c, codigo);

    if(quartoExiste(a,codigo)){
        quarto b = (quarto) daQuarto(a, codigo);
        daDadosQuarto(b, nomeRes,uni,local,&andar,desc,ocupacao);
        printf("%s, %s\n", codigo, nomeRes);
        printf("%s\n", uni);
        printf("%s\n", local);
        printf("%d\n", andar);
        printf("%s\n", desc);
        printf("%s\n", ocupacao);
        return;
    }
    printf("Inexistencia do quarto referido.\n");
}

void modificarQuarto(universidade a , char * line1) {
    char codigo[MAX_LOGIN];
    char loginGer[MAX_LOGIN];
    char ocupacao[MAX_LOGIN];
    char c[COMMAND];
    sscanf(line1, "%s %s %s %s\n", c, codigo, loginGer, ocupacao);
    if(quartoExiste(a, codigo)) {
        quarto b = (quarto) daQuarto(a, codigo);
        if(SeGerenteDoQuarto(b, loginGer)) {
            printf("Operacao nao autorizada.\n");
            return;
        }
        if(seExisteCandidaturasQuarto(b) && strcmp(ocupacao,"ocupado")==0){
            printf("Candidaturas activas.\n");
            return;
        }
        ocupacaoQuarto(b, ocupacao);
        printf("Estado de quarto atualizado.\n");
        return;
    }
    printf("Inexistencia do quarto referido.\n");
}


void removerQuarto(universidade a, char * line1) {
    char codigo[MAX_LOGIN];
    char loginGer[MAX_LOGIN];
    char c[COMMAND];
    if(sscanf(line1, "%s %s %s\n", c, codigo, loginGer)!=3){
        printf("Comando invalido.\n");
        return;
    }
    if(quartoExiste(a, codigo)) {
        quarto b = (quarto) daQuarto(a, codigo);
        if(SeGerenteDoQuarto(b, loginGer)) {
            printf("Operacao nao autorizada.\n");
            return;
        }
        if(seExisteCandidaturasQuarto(b)){
            printf("Candidaturas activas.\n");
            return;
        }
        removeQuarto(a, codigo);
        printf("Remocao de quarto executada.\n");
        return;
    }
    printf("Inexistencia do quarto referido.\n");

}

void inserirCandidatos(universidade a, char * line1){
    char codigo[MAX_LOGIN];
    char login[MAX_LOGIN];
    char c[COMMAND];

    if(sscanf(line1, "%s %s %s\n", c, login, codigo)!=3){
        printf("Comando invalido.\n");
        return;
    }
    if(estudanteExiste(a,login)){
        student b = (student) daEstudante(a, login);
        if(seMaxCandStudent(b)){
            printf("Operacao nao autorizada.\n");
            return;
        }
        if(quartoExiste(a,codigo)){
            quarto c = (quarto) daQuarto(a,codigo);
            if(seLivreQuarto(c)){
                if(seCandExisteStudent(b,codigo)==1){
                    printf("Registo de candidatura executado.\n");
                    adicionaCandStudent(b,codigo);
                    adicionaCandQuarto(b,c);
                    return;
                }
                printf("Candidatura existente.\n");
                return;
            }
            printf("Quarto ocupado.\n");
            return;
        }
        printf("Inexistencia do quarto referido.\n");
        return;
    }   
    printf("Inexistencia do estudante referido.\n");
    return;
}

void aceitarCandidatura(universidade a, char * line1) {
    char codigo[MAX_LOGIN];
    char logEstudante[MAX_LOGIN];
    char loginGerente[MAX_LOGIN];
    char c[COMMAND];

    sscanf(line1, "%s %s %s %s\n", c, codigo, loginGerente, logEstudante);
    if(quartoExiste(a,codigo)){
        quarto c = (quarto) daQuarto(a,codigo);
        if(SeGerenteDoQuarto(c, loginGerente)!=0){
            printf("Operacao nao autorizada.\n");
            return;
        }
        gerente g = (gerente) daGerente(a, loginGerente);
        if(estudanteExiste(a,logEstudante)==0){
            printf("Inexistencia da candidatura referida.\n");
            return;
        }
        student b = (student) daEstudante(a, logEstudante);
        if(seCandExisteStudent(b,codigo)==0){
            quartoAlugadoGerente(g);
            ocupacaoQuarto(c, "ocupado");
            for(int i=0; i<numCandStudent(b);i++){
                if(strcmp(quartoCandidaturaPosicaoStudent(b,i),codigo)!=0){
                    removeCandidaturaEstudanteAlocado(daQuarto(a,quartoCandidaturaPosicaoStudent(b,i)),loginStudent(b));
                }
            }
            removerCandidaturasQuarto(c);
            removeTodasAsCandidaturasStudent(b);
            printf("Aceitacao de candidatura executada.\n");
            return;
        }
        printf("Inexistencia da candidatura referida.\n");
        return;
    }
    printf("Inexistencia do quarto referido.\n");
}

void listaCandidaturas(universidade a, char * line1) {
    char codigo[MAX_LOGIN];
    char loginGerente[MAX_LOGIN];
    char c[COMMAND];
    if(sscanf(line1, "%s %s %s\n", c, codigo, loginGerente)!=3){
        printf("Comando invalido.\n");
        return;
    }
        if(quartoExiste(a,codigo)){
            quarto c = (quarto) daQuarto(a,codigo);
        if(SeGerenteDoQuarto(c, loginGerente)!=0){
            printf("Operacao nao autorizada.\n");
            return;
        } 
        if(seExisteCandidaturasQuarto(c)){
            iterador listaCandidaturas = iteradorCandidaturas(c);
            student b;
            while(temSeguinteIterador(listaCandidaturas)){
			    b = (student)seguinteIterador(listaCandidaturas);
			    printf("%s, %s, %s\n", loginStudent(b), nomeStudent(b), uniStudent(b));
		    }
		destroiIterador(listaCandidaturas);
            return;
        }
        printf("Inexistencia de candidaturas.\n");
        return;
    }
    printf("Inexistencia do quarto referido.\n");
}

void listaQuartos(universidade a){
    iterador quartos = iteradorQuartos(a);
    quarto c;
    char organizaLocal[MAX_CHAR];
    int i=0, j=0, index = 0, sort=0, h=0;
        if(!vazioDicionario(daDicionarioQuartos(a))){
            void* quartosPorLocal[numQuartos(a)];
            void* quartoPorLocalPorCodigo[numQuartos(a)];
            while(temSeguinteIterador(quartos)){
                c = (quarto)seguinteIterador(quartos);
                quartosPorLocal[index]=c;
                index++;
            }
            destroiIterador(quartos);
            quickSort(quartosPorLocal, 0, numQuartos(a)-1, localQuartoSort, comparaString);
            for (i = 0; i < numQuartos(a); i++){
                strcpy(organizaLocal,localQuarto(quartosPorLocal[i]));
                int m = j;
                sort = 0;
                while(strcmp(organizaLocal,localQuarto(quartosPorLocal[i]))==0 && i<numQuartos(a)){
                    quartoPorLocalPorCodigo[j]=quartosPorLocal[i];
                    j++;
                    sort++;
                    if(i<numQuartos(a)-1){
                    i++;
                    }else break;
                }
                if(sort>1){
                quickSort(quartoPorLocalPorCodigo, m, j-1, codigoQuartoSort, comparaString);
                }
                if(j==numQuartos(a)){
                    break;
                }
                i--;
            }
            for(h=0; h<j; h++){
                printf("%s %s\n%s\n%s\n\n", localQuarto(quartoPorLocalPorCodigo[h]), codigoQuarto(quartoPorLocalPorCodigo[h]), uniQuarto(quartoPorLocalPorCodigo[h]), nomeResQuarto(quartoPorLocalPorCodigo[h]));
            }
            return;
        }
        printf("Inexistencia de quartos.\n");
    }

    //Remove o espaço vazio ate e depois das palavras.
    char * cortaEspacoVazio(char * str){
    char * end;
    // Corta o espaço ate a palavra
    while(isspace((unsigned char)*str)) str++;

    if(*str == 0) 
    return str;
    // Corta o espaço depois das palavras
    end = str + strlen(str) - 1;
    while(end > str && isspace((unsigned char)*end)) end--;
    end[1] = '\0';
    return str;
}

void listaQuartosLocaisLivres(universidade a, char * line1){
iterador quartos = iteradorQuartos(a);
quarto c;
void* quartoPorLocalPorCodigo[numQuartos(a)];
char comando[COMMAND];
char local[MAX_CHAR];
int index=0;
if(sscanf(line1,"%s %[^\n]%*C", comando, local)!=2){
    printf("Comando invalido.\n");
    return;
}

    if(!vazioDicionario(daDicionarioQuartos(a))){
        while(temSeguinteIterador(quartos)){
		    c = (quarto)seguinteIterador(quartos);
            if(strcmp(local,localQuarto(c))==0 && seLivreQuarto(c)){
                quartoPorLocalPorCodigo[index]=c;
                index++;
            }
        }
        destroiIterador(quartos);
        if(index==0){
            printf("Inexistencia de quartos na localidade referida.\n");
            return;
        }
        quickSort(quartoPorLocalPorCodigo, 0, index-1, codigoQuartoSort, comparaString);
        for(int h=0; h<index; h++){
            printf("%s %s\n%s\n%s\n\n", localQuarto(quartoPorLocalPorCodigo[h]), codigoQuarto(quartoPorLocalPorCodigo[h]), uniQuarto(quartoPorLocalPorCodigo[h]), nomeResQuarto(quartoPorLocalPorCodigo[h]));
        }
        return;
    }
    printf("Inexistencia de quartos na localidade referida.\n");
}

void top3gerentes(universidade a) {
gerente g;
int i=0, n=0;
int numQuartosGerente = 0, quantosGerentes=0;
int j=0, index = 0;
    if(haGerentes(a)) {
        int tamanho = tamanhoDicionario(daDicionarioGerentes(a));
        void* gerentesPorQuartos[tamanho];
        void* gerentesPorQuartosPorCodigo[tamanho];
        iterador iteradorGerentes = iteradorGerente(a);
        while(temSeguinteIterador(iteradorGerentes)) {
            g = (gerente)seguinteIterador(iteradorGerentes);
            gerentesPorQuartos[index] = g;
            index++;
        }
        destroiIterador(iteradorGerentes);
        quickSort(gerentesPorQuartos, 0, tamanho -1,(void*) quartoAlugadoGerenteSort, comparaNumero);
        for(i=0; i<tamanho; i++){
            numQuartosGerente=quartoAlugadoGerenteSort(gerentesPorQuartos[i]);
            int m = j;
            while(numQuartosGerente==quartoAlugadoGerenteSort(gerentesPorQuartos[i]) && i<tamanho){
                gerentesPorQuartosPorCodigo[j]=gerentesPorQuartos[i];
                j++;
                quantosGerentes++;
                if(i<tamanho-1){
                    i++;
                }else break;
            }
            quickSort(gerentesPorQuartosPorCodigo, m, j-1, codigoGerenteSort, comparaString);
            if(i<tamanho-1){
                i--;
            }
        }
        if(quantosGerentes>=3){
                n=3;
        }else n=j;
        for(int k = 0; k < n; k++) {
            printf("%s alugou %d quartos\n", loginGerente(gerentesPorQuartosPorCodigo[k]), daQuartosAlugadosGerente(gerentesPorQuartosPorCodigo[k]));
        }
        return;
    }
    printf("Inexistencia de gerentes.\n");
}
