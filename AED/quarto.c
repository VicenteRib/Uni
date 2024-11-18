#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include"student.h"
#include"quarto.h"
#include"sequencia.h" 

#define MAX_CHAR 50
#define MAX_LOGIN 20
#define DESCRICAO 200
//Criar Sequencia com as candidaturas dos estudantes;
struct _quarto{
char uni[MAX_CHAR];
char residencia[MAX_CHAR];
char local[MAX_CHAR];
char desc[DESCRICAO];
char gerente[MAX_LOGIN];
int andar;
char codigo[MAX_LOGIN];
char ocupacao[MAX_LOGIN];
sequencia candidatos;
};

typedef struct _quarto * quarto;

quarto criaQuarto(char * uni, char * residencia, char * local, char * desc, int andar, char * gerente, char * codigo){
    quarto a;
    a = (quarto) malloc(sizeof(struct _quarto));
    if (a == NULL) return NULL;
    strcpy(a->codigo,codigo);
    strcpy(a->uni,uni);
    strcpy(a->residencia,residencia);
    strcpy(a->local,local);
    strcpy(a->desc,desc);
    strcpy(a->gerente,gerente);
    a->andar=andar;
    strcpy(a->ocupacao,"livre");
    a->candidatos = (sequencia) criaSequencia(MAX_LOGIN);
    return a;
}

void destroiQuarto(quarto q) {
    free(q);
}

void daDadosQuarto(quarto b, char * residencia,char * uni, char * local, int * andar, char * desc, char * ocupacao){
    strcpy(uni,b->uni);
    strcpy(residencia,b->residencia);
    strcpy(local,b->local);
    strcpy(desc,b->desc);
    *andar=b->andar;
    strcpy(ocupacao,b->ocupacao);
}

int SeGerenteDoQuarto(quarto b, char * gerente) {
    return strcmp(b->gerente, gerente);
}

void ocupacaoQuarto(quarto q, char * ocupacao) {
    strcpy(q->ocupacao, ocupacao);
}

int seLivreQuarto(quarto b){
    if(strcmp(b->ocupacao,"livre")==0)
        return 1;
    return 0;
}

void adicionaCandQuarto(student b, quarto c){
    adicionaPosSequencia(c->candidatos, b, tamanhoSequencia(c->candidatos)+1);
}

int seExisteCandidaturasQuarto(quarto c) {
    return !vaziaSequencia(c->candidatos);
}

void removerCandidaturasQuarto(quarto c) {
    int a = tamanhoSequencia(c->candidatos);
    for(int i = 0; i < a; i++) {
        student b = (student) elementoPosSequencia(c->candidatos, 1);
        removeCandidaturaStudent(b, c->codigo);
        removePosSequencia(c->candidatos, 1);
    }
}

iterador iteradorCandidaturas(quarto c) {
return iteradorSequencia(c->candidatos);
}

char * localQuarto(quarto c){
return c->local;
} 

char * codigoQuarto(quarto c){
return c->codigo;
}

char * uniQuarto(quarto c){
return c->uni;
}

char * nomeResQuarto(quarto c){
return c->residencia;
}

void removeCandidaturaEstudanteAlocado(quarto c,char * loginEst){
    for(int i = tamanhoSequencia(c->candidatos); i > 0; i--) {
        student b = (student) elementoPosSequencia(c->candidatos,i);
        if(strcmp(loginStudent(b),loginEst)==0 ){
            removePosSequencia(c->candidatos, i);
            return;
        }
    }
}

sequencia CandidatosQuarto(quarto c){
    return c->candidatos;
}

void* codigoQuartoSort(void* a){
    quarto c = (quarto) a;
    void* str=malloc(strlen(c->codigo)+1);
    strcpy(str,c->codigo);
    return str;
}

void* localQuartoSort(void* a){
    quarto c = (quarto) a;
    void* str=malloc(strlen(c->local)+1);
    strcpy(str,c->local);
    return str;
}