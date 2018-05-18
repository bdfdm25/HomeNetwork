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
    FILE *arquivo;
    usuario dados;

    if (strcmp("1",argv[4]) == 0){
        printf("Voce nao pode alterar o administrador!");
        getchar();
        exit(1);
    }

    arquivo = fopen("usuarios.dad","rb+");

    fseek(arquivo, 0*sizeof(usuario), SEEK_SET);
        do
        {
            fread(&dados,sizeof(usuario),1,arquivo);
            if (strcmp(dados.codigo,argv[1]) == 0 && !feof(arquivo))
            {

                strcpy(dados.usuario,argv[2]);
                strcpy(dados.senha,argv[3]);
                strcpy(dados.tipo,argv[4]);

                //printf("%s", dados.usuario);
                //printf("%s", dados.senha);
                //printf("%s", dados.tipo);

                fseek(arquivo,-1*sizeof(usuario),SEEK_CUR);
                fwrite(&dados,sizeof(usuario),1,arquivo);
                break;
            }
        } while (!feof(arquivo));

    fclose(arquivo);
    //printf("\nRegistro alterado!");

    Sleep(1000);
    exit(1);
}


