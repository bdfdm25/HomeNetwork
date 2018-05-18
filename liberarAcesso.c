#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>

typedef struct
{
  char codigo[2];
  char usuario[20];
  char senha[20];
  char  tipo[2];
}usuario;

main(int argc, char *argv[])
{
    FILE *arquivo, *cadastrados;
    usuario dados;
    char autorizacao[10];

    cadastrados = fopen("usuarios.dad", "rb");
    fseek(cadastrados, 0*sizeof(usuario), SEEK_SET);

    while(!feof(cadastrados))
    {

        if(!feof(cadastrados))
        {

            fread(&dados,sizeof(usuario),1,cadastrados);
            if(strcmp(dados.usuario,argv[1]) == 0 && strcmp(dados.senha,argv[2]) == 0)
            {
                if(strcmp(dados.tipo,"1") == 0){
                    arquivo = fopen("Acesso.txt", "wt");
                    strcpy(autorizacao, "Liberado;");
                    fwrite(&autorizacao,10,1,arquivo);
                   // printf("Liberado!");
                    fclose(arquivo);
                    fclose(cadastrados);
                    Sleep(1000);
                    exit(1);
                }else if(strcmp(dados.tipo,"2") == 0){
                    arquivo = fopen("Acesso.txt", "wt");
                    strcpy(autorizacao, "Liberado2;");
                    fwrite(&autorizacao,10,1,arquivo);
                    //printf("Liberado!");
                    fclose(arquivo);
                    fclose(cadastrados);
                    Sleep(1000);
                    exit(1);
                }else if(strcmp(dados.tipo,"3") == 0){
                    arquivo = fopen("Acesso.txt", "wt");
                    strcpy(autorizacao, "Liberado3;");
                    fwrite(&autorizacao,10,1,arquivo);
                    //printf("Liberado!");
                    fclose(arquivo);
                    fclose(cadastrados);
                    Sleep(1000);
                    exit(1);
                }else if(strcmp(dados.tipo,"4") == 0){
                    arquivo = fopen("Acesso.txt", "wt");
                    strcpy(autorizacao, "Liberado4;");
                    fwrite(&autorizacao,10,1,arquivo);
                    //printf("Liberado!");
                    fclose(arquivo);
                    fclose(cadastrados);
                    Sleep(1000);
                    exit(1);
                }
            }
        }
    }

    arquivo = fopen("Acesso.txt", "wt");
    strcpy(autorizacao, "NLiberado;");
    fwrite(&autorizacao,10,1,arquivo);
    //printf("Nao Liberado!");
    fclose(arquivo);
    fclose(cadastrados);
    Sleep(1000);
    exit(1);
}

