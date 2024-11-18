#ifndef STUDENT_H_
#define STUDENT_H_

typedef struct _student * student;

/***********************************************
criaStudent - Criacao da instancia da estrutura associada a um estudante.
Parametros:
     uni(string) - universidade associada ao estudante
     nome(string) - nome do estudante
     idade(inteiro) - idade do estudante
     local(string) - local de residecência atual do estudante
     login(string) - login do estudante
Retorno: apontador para a instancia criada
Pre-condicoes: uni != {'\0'} && nome != {'\0'} && idade > 0 && local != {'\0'} && login != {'/0'}
***********************************************/
student criaStudent(char * uni, char * nome, int idade, char * local, char * login);

/***********************************************
destroiStudent - Destruição da instancia da estrutura associada a um estudante.
Parametros:
     s(student) - instancia da estrutura associada a um estudante
Retorno: 
Pre-condicoes: s != NULL
***********************************************/
void destroiStudent(student s);

/***********************************************
daDadosStudent - consulta os dados da instancia da estrutura associada a um estudante.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
     nome(string) - nome do estudante
     idade(int) - idade do estudante
     local(string) - local de residência atual do estudante
     uni(string) - universidade do estudnate
Retorno: Devolve as strings atraves de pointers
Pre-condicoes: b != NULL && nome != {'\0'} && idade > 0 && local != {'\0'} && uni != {'\0'}
***********************************************/
void daDadosStudent(student b,char * nome,int * idade,char * local,char *uni);

/***********************************************
seMaxCandStudentStudent - Consulta se o estudante já chegou ao limite máximo de candidaturas.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
Retorno: 1 - chegou ao limite, 0 - não chegou ao limite
Pre-condicoes: b != NULL
***********************************************/
int seMaxCandStudent(student b);

/***********************************************
seCandExisteStudent - Consulta se a candidatura existe para o correspodente estudante.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
     c(string) - código de quarto de candidatura
Retorno: 1 - chegou ao limite, 0 - não chegou ao limite
Pre-condicoes: b != NULL && c != {'/0'}
***********************************************/
int seCandExisteStudent(student b, char * c);

/***********************************************
adicionaCandStudent - Adiciona a candidatura na instancia de estrutura de um estudante.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
     c(string) - código de quarto de candidatura
Retorno: 
Pre-condicoes: b != NULL&& c != {'/0'}
***********************************************/
void adicionaCandStudent(student b, char * c);

/***********************************************
removeCandidaturaStudent - Remove candidatura na instancia de estrutura de um estudante.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
     c(string) - código de quarto de candidatura
Retorno: 
Pre-condicoes: b != NULL && c != {'/0'}
***********************************************/
void removeCandidaturaStudent(student b, char * c);

/***********************************************
loginStudent- Consulta o login do estudante.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
Retorno: login do estudante(string).
Pre-condicoes: b != NULL
***********************************************/
char * loginStudent(student b);

/***********************************************
nomeStudent- Consulta o nome do estudante.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
Retorno: nome do estudante (string).
Pre-condicoes: b != NULL
***********************************************/
char * nomeStudent(student b);

/***********************************************
uniStudent - Consulta a universidade do estudante.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
Retorno: universidade do estudante (string).
Pre-condicoes: b != NULL
***********************************************/
char * uniStudent(student b);

/***********************************************
quartoCandidaturaPosicaoStudent- Consulta candidatura da sequência de candidaturas da estrutura de estudante.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
     i(inteiro) - posição da candidatura na sequência
Retorno: codigo de quarto da candidatura do estudante (string).
Pre-condicoes: b != NULL && i > 0
***********************************************/
char * quartoCandidaturaPosicaoStudent(student b, int i);

/***********************************************
removeTodasAsCandidaturaStudent - Remove todas as candidatura na instancia de estrutura de um estudante.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
Retorno: 
Pre-condicoes: b != NULL
***********************************************/
void removeTodasAsCandidaturasStudent(student b);

/***********************************************
numCandStudent - Consulta o numero de candidaturas feitas pelo estudante.
Parametros:
     b(student) - instancia da estrutura associada a um estudante
Retorno: número de candidaturas(inteiro).
Pre-condicoes: b != NULL
***********************************************/
int numCandStudent(student b);

#endif