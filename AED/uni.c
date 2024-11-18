#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include"student.h"
#include"gerente.h"
#include"quarto.h"
#include"dicionario.h"
#include"chaves.h"
#include"uni.h"
#include"iterador.h"

#define MAX_CHAR 50
#define MAX_STD 20000
#define MAX_QRT 10000
#define MAX_MNG 1000

struct _universidade{
dicionario  student;
dicionario  gerente;
dicionario  quarto;
};

typedef struct _universidade * universidade;

universidade criaUniversidade(){
    universidade a;
    a = (universidade) malloc(sizeof(struct _universidade));
    if (a == NULL) return NULL;
    a->student = (dicionario) criaDicionario(MAX_STD,1);
    if (a->student == NULL) return NULL;
    a->gerente = (dicionario) criaDicionario(MAX_MNG,1);
    if (a->gerente == NULL) return NULL;
    a->quarto = (dicionario) criaDicionario(MAX_QRT,1); 
    if (a->quarto == NULL) return NULL;
    return a;
}  

void adicionarEstudante(universidade a, char * login,char * uni, char * nome, int idade, char * local){
    student b = criaStudent(uni, nome, idade, local, login);
    adicionaElemDicionario(a->student, login, b);
}

void adicionarQuarto(universidade a, char * codigo, char * gerente, char * uni, char * residencia, char * desc, int andar, char * local){
    quarto b = criaQuarto(uni, residencia, local, desc, andar, gerente, codigo);
    adicionaElemDicionario(a->quarto, codigo, b);
}

void removeQuarto(universidade a, char * codigo) {
    removeElemDicionario(a->quarto, codigo);
}

void adicionarGerente(universidade a, char * login,char * uni, char * nome){
    gerente b = criaGerente(uni, nome, login);
    adicionaElemDicionario(a->gerente, login, b);
}

int estudanteExiste(universidade a, char * login){
    return existeElemDicionario(a->student,login);
}

int gerenteExiste(universidade a, char * login){
    return existeElemDicionario(a->gerente,login);
}

int quartoExiste(universidade a, char * codigo){
    return existeElemDicionario(a->quarto,codigo);
}

student daEstudante(universidade a, char * login){
    student b = (student) elementoDicionario(a->student,login);
    return b;
}

gerente daGerente(universidade a, char * login){
    gerente b = (gerente) elementoDicionario(a->gerente,login);
    return b;
}

quarto daQuarto(universidade a, char * codigo){
    quarto b = (quarto) elementoDicionario(a->quarto,codigo);
    return b;
}

iterador iteradorQuartos(universidade a){
    iterador b;
    b = (iterador) iteradorDicionario(a->quarto);
    return b;
}

dicionario daDicionarioQuartos(universidade a){
    return a->quarto;
}

int loginExiste(universidade a, char * login){
if (estudanteExiste(a,login) || gerenteExiste(a,login))
    return 1;

return 0;
}

int haGerentes(universidade a) {
    if(vazioDicionario(a->gerente)) return 0;
    return 1;
}

int numGerentes(universidade a) {
    return tamanhoDicionario(a->gerente);
}

int numQuartos(universidade a) {
    return tamanhoDicionario(a->quarto);
}

iterador iteradorGerente(universidade a){
    iterador b;
    b = (iterador) iteradorDicionario(a->gerente);
    return b;
}

int comparaString(void* str1,void* str2){
    return strcmp(str1,str2);
}

dicionario daDicionarioGerentes(universidade a){
    return a->gerente;
}

int comparaNumero(void* n1, void* n2){
    //int a=(int) n1;
    //int b=(int) n2;
    if(n1>n2){
    return -1;
    }
    else if(n1<n2){
    return 1;
    }
    else
    return 0;
}

