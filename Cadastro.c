#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>

typedef struct
{
  char codigo[2];
  char usuario[20];
  char senha[20];
  char tipo[2];
}usuario;

main(int argc, char *argv[])
{
    FILE *cadastrados;
    usuario dados;
    int x = 0;

    cadastrados = fopen("usuarios.dad", "rb+");
    if(cadastrados == NULL){

        cadastrados = fopen("usuarios.dad", "wb+");
        x++;

    }
    fseek(cadastrados, 0*sizeof(usuario), SEEK_END);
    if(x > 0)
        strcpy(dados.tipo,"1");
    else
    strcpy(dados.tipo,"4");
    strcpy(dados.codigo,argv[1]);
    strcpy(dados.usuario,argv[2]);
    strcpy(dados.senha,argv[3]);

    fwrite(&dados,sizeof(usuario),1,cadastrados);
    //printf("Cadastrado com sucesso!");

    fclose(cadastrados);
    Sleep(1000);
    exit(1);
}
