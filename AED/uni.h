#ifndef UNI_H_
#define UNI_H_
#include"dicionario.h"

typedef struct _universidade * universidade;

universidade criaUniversidade();

/***********************************************
adicionarEstudante - Adiciona o estudante ao seu respetivo dicionario.
Parametros:
    uni(string) - universidade associada ao estudante
    nome(string) - nome do estudante
    login(string) - login do estudante
    local(string) - localalidade do estudante
    idade(int) - idade do estudante
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno:
Pre-condicoes: a != NULL && nome != {'\0'} && login != {'\0'} && local != {'\0'} && uni != {'\0'} && idade>0
***********************************************/
void adicionarEstudante(universidade a, char * login,char * uni, char * nome, int idade, char * local);

/***********************************************
adicionarQuarto - Adiciona o quarto ao seu respetivo dicionario.
Parametros:
    uni(string) - universidade associada ao quarto
    residencia(string) - nome da residencia
    gerente(string) - login do gerente associado ao quarto
    local(string) - local do quarto
    desc(string) - descriçao do quarto
    andar(int) - andar do quarto
    codigo(string) - codigo do quarto
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno:
Pre-condicoes: a != NULL uni != {'\0'} && residencia != {'\0'} && gerente != {'\0'} && desc != {'\0'} && codigo != {'\0'} && local != {'\0'} && andar>=0
***********************************************/
void adicionarQuarto(universidade a, char * codigo, char * gerente, char * uni, char * residencia, char * desc, int andar, char * local);

/***********************************************
removeQuarto - Remove o quarto do dicionario.
Parametros:
    codigo(string) - codigo do quarto
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno:
Pre-condicoes: a != NULL && codigo != {'\0'}
***********************************************/
void removeQuarto(universidade a, char * codigo);

/***********************************************
adicionarGerente - Adiciona o gerente ao seu respetivo dicionario.
Parametros:
    uni(string) - universidade associada ao gerente
    nome(string) - nome do gerente
    login(string) - login do gerente
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno:
Pre-condicoes: a != NULL && uni != {'\0'} && nome != {'\0'} && login != {'\0'}
***********************************************/
void adicionarGerente(universidade a, char * login,char * uni, char * nome);

/***********************************************
estudanteExiste - Verifica se o estudante com login(string) existe.
Parametros:
    login(string) - login do estudante
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: 1- se exitir 0- caso contrario
Pre-condicoes: a != NULL && login != {'\0'}
***********************************************/
int estudanteExiste(universidade a, char * login);

/***********************************************
gerenteExiste - Verifica se o gerente com login(string) existe.
Parametros:
    login(string) - login do gerente
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: 1- se exitir 0- caso contrario
Pre-condicoes: a != NULL && login != {'\0'}
***********************************************/
int gerenteExiste(universidade a, char * login);

/***********************************************
quartoExiste - Verifica se o quarto com codigo(string) existe.
Parametros:
    codigo(string) - codigo do quarto
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: 1- se exitir 0- caso contrario
Pre-condicoes: a != NULL && codigo != {'\0'}
***********************************************/
int quartoExiste(universidade a, char * codigo);

/***********************************************
returnDicStudent - Retorna o Dicionario associado com os estudantes.
Parametros:
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: Dicionario que contem os estudantes da universidade
Pre-condicoes: a != NULL
***********************************************/
dicionario returnDicStudent(universidade a);

/***********************************************
daEstudante - Retorna o estudante associado com o login(string).
Parametros:
    login(string) - login do estudante
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: estudante associado com o login
Pre-condicoes: a != NULL && login != {'\0'}
***********************************************/
student daEstudante(universidade a, char * login);

/***********************************************
daGerente - Retorna o gerente associado com o login(string).
Parametros:
    login(string) - login do gerente
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: gerente associado com o login
Pre-condicoes: a != NULL && login != {'\0'}
***********************************************/
gerente daGerente(universidade a, char * login);
/***********************************************
daQuarto - Retorna o quarto associado com o codigo(string).
Parametros:
    codigo(string) - codigo do quarto
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: quarto associado com o codigo
Pre-condicoes: a != NULL && codigo != {'\0'}
***********************************************/
quarto daQuarto(universidade a, char * codigo);

/***********************************************
iteradorQuartos - Retorna o iterador do dicionario dos quartos da univerisidade.
Parametros:
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: iterador do dicionario dos quartos da univerisidade
Pre-condicoes: a != NULL
***********************************************/
iterador iteradorQuartos(universidade a);

/***********************************************
daDicionarioQuartos - Retorna o Dicionario associado com os quartos.
Parametros:
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: Dicionario que contem os quartos da universidade
Pre-condicoes: a != NULL
***********************************************/
dicionario daDicionarioQuartos(universidade a);

/***********************************************
loginExiste - Verifica se o login(string) existe na universidade.
Parametros:
    login(string) - login do gerente ou estudante
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: 1- caso exite 0- caso nao exista
Pre-condicoes: a != NULL && login != {'\0'}
***********************************************/
int loginExiste(universidade a, char * login);

/***********************************************
haGerentes - Verifica se existem gerentes na universidade.
Parametros:
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: 1- caso exita 0- caso nao exista
Pre-condicoes: a != NULL
***********************************************/
int haGerentes(universidade a);

/***********************************************
numGerentes - Devolve o numero de gerentes na universidade.
Parametros:
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: numero de gerentes
Pre-condicoes: a != NULL
***********************************************/
int numGerentes(universidade a);

/***********************************************
numQuartos - Devolve o numero de quartos na universidade.
Parametros:
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: numero de quartos
Pre-condicoes: a != NULL
***********************************************/
int numQuartos(universidade a);

/***********************************************
iteradorGerente - Cria um iterador do dicionario de gerentes.
Parametros:
    a(universidade) - instancia da estrutura associada a uma universidade
Retorno: iterador do dicionario de gerentes
Pre-condicoes: a != NULL
***********************************************/
iterador iteradorGerente(universidade a);

/***********************************************
comparaString - Compara duas strings.
Parametros:
    str1(string) - string a ser comparada
    str2(string) - string a ser comparada
Retorno: <0 - str1 < str2 | 0 - str1 = str2 | >0 - str1 > str2
Pre-condicoes: str1 != {'\0'} && str2 != {'\0'}
***********************************************/
int comparaString(void* str1,void* str2);

dicionario daDicionarioGerentes(universidade a);

int comparaNumero(void* n1, void* n2);
#endif
