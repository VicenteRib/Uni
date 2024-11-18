#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include"gerente.h"

#define MAX_CHAR 50
#define MAX_LOGIN 20

struct _gerente{
char uni[MAX_CHAR];
char nome[MAX_CHAR];
char login[MAX_LOGIN];
int numQuartosAlugados;
};

typedef struct _gerente * gerente;

gerente criaGerente(char * uni, char * nome, char * login){
    gerente a;
    a = (gerente) malloc(sizeof(struct _gerente));
    if (a == NULL) return NULL;
    strcpy(a->login,login);
    strcpy(a->uni,uni);
    strcpy(a->nome,nome);
    a->numQuartosAlugados = 0;
    return a;
}

void destroiGerente(gerente m) {
    free(m);
}

void daDadosGerente(gerente b, char * nome, char * uni){
    strcpy(nome,b->nome);
    strcpy(uni,b->uni);
    return;
}

int verificarUniversidadeGerente(gerente b, char * universidade){
    char uniGerente[MAX_CHAR];
    strcpy(uniGerente,b->uni);
    if(strcmp(universidade,uniGerente)==0){
        return 1;
    }
    return 0;
}

void quartoAlugadoGerente(gerente b) {
    b->numQuartosAlugados++;
}

int daQuartosAlugadosGerente(gerente b) {
    return b->numQuartosAlugados;
}

char * loginGerente(gerente b) {
    return b->login;
}

void* codigoGerenteSort(void* a){
    gerente c = (gerente) a;
    void* str=malloc(strlen(c->login)+1);
    strcpy(str,c->login);
    return str;
}

int quartoAlugadoGerenteSort(gerente c){
    return c->numQuartosAlugados;
}

