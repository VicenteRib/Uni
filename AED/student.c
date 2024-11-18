#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include"sequencia.h"
#include"student.h"

#define MAX_CHAR 50
#define MAX_LOGIN 20
#define CANDIDATURA_MAX 10
//Criar Vetor com pointer para os quartos a qual esta candidatado(maximo 10);
struct _student{
    char login[MAX_LOGIN];
    char uni[MAX_CHAR];
    char nome[MAX_CHAR];
    int idade;
    char local[MAX_CHAR];
    sequencia candidaturas;
};

typedef struct _student * student;

student criaStudent(char * uni, char * nome, int idade, char * local, char * login){
    student a;
    a = (student) malloc(sizeof(struct _student));
    if (a == NULL) return NULL;
    strcpy(a->login,login);
    strcpy(a->uni,uni);
    strcpy(a->nome,nome);
    a->idade=idade;
    strcpy(a->local,local);
    a->candidaturas=(sequencia) criaSequencia(CANDIDATURA_MAX);
    return a;
}

void destroiStudent(student a) {
    free(a);
}

void daDadosStudent(student b,char * nome,int * idade,char * local,char *uni){
    strcpy(nome,b->nome);
    strcpy(local,b->local);
    strcpy(uni,b->uni);
    *idade = b->idade;
}

int seMaxCandStudent(student b){
    if(tamanhoSequencia(b->candidaturas)==10)
        return 1;
    return 0;
}

int seCandExisteStudent(student b, char * c){
    char * codigo;
    codigo = (char *)malloc(sizeof(char)*MAX_LOGIN);
    strcpy(codigo,c);
    for(int i=1; i<=tamanhoSequencia(b->candidaturas); i++){
    char * candidatura = (char *) elementoPosSequencia(b->candidaturas,i);
       if(strcmp(candidatura,codigo)==0)
        return 0;
    }
    return 1;
}

void adicionaCandStudent(student b,char * c){
    char * codigo;
    codigo = (char *)malloc(sizeof(char)*MAX_LOGIN);
    strcpy(codigo,c);
    adicionaPosSequencia(b->candidaturas,codigo,tamanhoSequencia(b->candidaturas)+1);
}

void removeCandidaturaStudent(student b, char * c) {
    for(int i = 1; i <= tamanhoSequencia(b->candidaturas); i++) {
        char * candidatura = (char *) elementoPosSequencia(b->candidaturas,i);
        if(strcmp(c,candidatura)==0){
            removePosSequencia(b->candidaturas,i);
        }
    }
}

char * loginStudent(student b) {
    return b->login;
}

char * nomeStudent(student b) {
    return b->nome;
}

char * uniStudent(student b) {
    return b->uni;
}

char * quartoCandidaturaPosicaoStudent(student b, int i){
    return (char *) elementoPosSequencia(b->candidaturas,i+1);
}

void removeTodasAsCandidaturasStudent(student b) {
    for(int i = 1; i <= tamanhoSequencia(b->candidaturas); i++) {
        removePosSequencia(b->candidaturas,1);
    }
}

int numCandStudent(student b){
    return tamanhoSequencia(b->candidaturas);
}
