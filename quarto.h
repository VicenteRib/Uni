#ifndef QUARTO_H_
#define QUARTO_H_
#include"sequencia.h" 


typedef struct _quarto * quarto;
/***********************************************
criaQuarto - Criacao da instancia da estrutura associada a um Quarto.
Parametros:
     uni(string) - universidade associada ao quarto
     residencia(string) - nome da residencia
     gerente(string) - login do gerente associado ao quarto
     local(string) - local do quarto
     desc(string) - descriçao do quarto
     andar(int) - andar do quarto
     codigo(string) - codigo do quarto
Retorno: apontador para a instancia criada
Pre-condicoes: uni != {'\0'} && residencia != {'\0'} && gerente != {'\0'} && desc != {'\0'} && codigo != {'\0'} && local != {'\0'} && andar>=0
***********************************************/
quarto criaQuarto(char * uni, char * residencia, char * local, char * desc, int andar, char * gerente, char * codigo);

/***********************************************
destroiQuarto - Destruição da instancia da estrutura associada a um quarto.
Parametros:
     q(quarto) - instancia da estrutura associada a um quarto
Retorno: 
Pre-condicoes: q != NULL
***********************************************/
void destroiQuarto(quarto q);

/***********************************************
daDadosQuarto - consulta os dados da instancia da estrutura associada a um quarto.
Parametros:
     uni(string) - universidade associada ao quarto
     res(string) - nome da residencia
     gerente(string) - login do gerente associado ao quarto
     local(string) - local do quarto
     desc(string) - descriçao do quarto
     andar(int) - andar do quarto
     ocupacao(string)- se esta livre ou ocupado
Retorno: Devolve as strings atraves de pointers
Pre-condicoes: b != NULL && uni != {'\0'} && nome != {'\0'}
***********************************************/
void daDadosQuarto(quarto b, char * res,char * uni, char * local, int * andar, char * desc, char * ocupacao);

/***********************************************
SeGerenteDoQuarto - Verifica se gerente(string) é o gerente do quarto.
Parametros:
     b(quarto) - instancia da estrutura associada a um quarto
     gerente(string) - gerente que será verificada com a do quarto
Retorno:0-se for o gerente do quarto, 1- se não for.
Pre-condicoes: b != NULL && gerente != {'\0'}
***********************************************/
int SeGerenteDoQuarto(quarto b, char * gerente);

/***********************************************
ocupacaoQuarto - Verifica se ocupacao(string) é a mesma que o quarto.
Parametros:
     b(quarto) - instancia da estrutura associada a um quarto
     ocupacao(string) - ocupacao a verificar com o quarto
Retorno:0-se for a ocupao do quarto, 1- se não for.
Pre-condicoes: q != NULL && ocupaco != {'\0'}
***********************************************/
void ocupacaoQuarto(quarto q, char * ocupacao);

/***********************************************
seLivreQuarto - Verifica se o quarto esta livre.
Parametros:
     b(quarto) - instancia da estrutura associada a um quarto
Retorno:1-se for estiver livre, 0- se não.
Pre-condicoes: b != NULL 
***********************************************/
int seLivreQuarto(quarto b);

/***********************************************
adicionaCandQuarto - Adiciona o estudante como candidato ao quarto.
Parametros:
     c(quarto) - instancia da estrutura associada a um quarto
     b(estudante) - instancia da estrutura associada a um estudante
Retorno:
Pre-condicoes: b != NULL && c != NULL 
***********************************************/
void adicionaCandQuarto(student b, quarto c);

/***********************************************
seExisteCandidaturasQuarto - Indica se há candidaturas ao quarto.
Parametros:
     c(quarto) - instancia da estrutura associada a um quarto
Retorno: 0-caso esteja vazia 1-caso nao
Pre-condicoes:c != NULL 
***********************************************/
int seExisteCandidaturasQuarto(quarto c);

/***********************************************
removerCandidaturasQuarto - Remove as candidaturas do quarto.
Parametros:
     c(quarto) - instancia da estrutura associada a um quarto
Retorno:
Pre-condicoes: c != NULL 
***********************************************/
void removerCandidaturasQuarto(quarto c);

/***********************************************
iteradorCandidaturas - Cria um iterador com as candidaturas do quarto.
Parametros:
     c(quarto) - instancia da estrutura associada a um quarto
Retorno: iterador com as candidaturas do quarto
Pre-condicoes: c != NULL 
***********************************************/
iterador iteradorCandidaturas(quarto c);

/***********************************************
localQuarto - Retorna a localidade do quarto.
Parametros:
     c(quarto) - instancia da estrutura associada a um quarto
Retorno:string com a localidade do quarto
Pre-condicoes: c != NULL 
***********************************************/
char * localQuarto(quarto c); 

/***********************************************
codigoQuarto - Retorna o codigo do quarto.
Parametros:
     c(quarto) - instancia da estrutura associada a um quarto
Retorno: string com o codigo do quarto
Pre-condicoes: c != NULL 
***********************************************/
char * codigoQuarto(quarto c);

/***********************************************
uniQuarto - Retorna a universidade do quarto.
Parametros:
     c(quarto) - instancia da estrutura associada a um quarto
Retorno: string com a universidade do quarto
Pre-condicoes: c != NULL 
***********************************************/
char * uniQuarto(quarto c);

/***********************************************
nomeResQuarto - Retorna o nome da residencia do quarto.
Parametros:
     c(quarto) - instancia da estrutura associada a um quarto
Retorno: string com o nome da residencia do quarto
Pre-condicoes:c != NULL 
***********************************************/
char * nomeResQuarto(quarto c);

/***********************************************
removeCandidaturaEstudanteAlocado - Remove do quarto a candidatura de um estudante que ja tem quarto.
Parametros:
     c(quarto) - instancia da estrutura associada a um quarto
     loginEst(string) - string com o login do Estudante
Retorno:
Pre-condicoes: c != NULL && loginEst != {'\0'}
***********************************************/
void removeCandidaturaEstudanteAlocado(quarto c,char * loginEst);

/***********************************************
CandidatosQuarto - Retorna a sequencia com os candidatos ao quarto.
Parametros:
     c(quarto) - instancia da estrutura associada a um quarto
Retorno: sequencia com os candidatos ao quarto
Pre-condicoes:c != NULL 
***********************************************/
sequencia CandidatosQuarto(quarto c);

void* codigoQuartoSort(void* a);

void* localQuartoSort(void* a);
#endif