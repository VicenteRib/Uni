#ifndef GERENTE_H_
#define GERENTE_H_

typedef struct _gerente * gerente;

/***********************************************
criaGerente - Criacao da instancia da estrutura associada a um gerente.
Parametros:
     uni(string) - universidade associada ao gerente
     nome(string) - nome do gerente
     login(string) - login do gerente
Retorno: apontador para a instancia criada
Pre-condicoes: uni != {'\0'} && nome != {'\0'} && login != {'\0'}
***********************************************/
gerente criaGerente(char * uni, char * nome, char * login);

/***********************************************
destroiGerente - Destruição da instancia da estrutura associada a um gerente.
Parametros:
     m(gerente) - instancia da estrutura associada a um gerente
Retorno:
Pre-condicoes: m != NULL
***********************************************/
void destroiGerente( gerente m);

/***********************************************
daDadosGerente - consulta os dados da instancia da estrutura associada a um gerente.
Parametros:
     b(gerente) - instancia da estrutura associada a um gerente
     nome(string) - nome do gerente
     uni(string) - universidade associada ao gerente
Retorno: Devolve as strings atraves de pointers
Pre-condicoes: b != NULL && uni != {'\0'} && nome != {'\0'}
***********************************************/
void daDadosGerente(gerente b, char * nome, char * uni);

/***********************************************
verificarUniversidadeGerente - Verifica se o gerente é da universidade(string).
Parametros:
     b(gerente) - instancia da estrutura associada a um gerente
     universidade(string) - universidade que será verificada com a do gerente
Retorno:1-se for da mesma universidade, 0- se não for.
Pre-condicoes: b != NULL && universidade != {'\0'}
***********************************************/
int verificarUniversidadeGerente(gerente b, char * universidade);

/***********************************************
quartoAlugadoGerente - Adiciona quarto alugado.
Parametros:
     m(gerente) - instancia da estrutura associada a um gerente
Retorno:
Pre-condicoes: m != NULL
***********************************************/
void quartoAlugadoGerente(gerente b);

int daQuartosAlugadosGerente(gerente b);

char * loginGerente(gerente b);

void* codigoGerenteSort(void* a);

int quartoAlugadoGerenteSort(gerente c);
#endif