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
        printf("Voce nao pode excluir o administrador!");
        getchar();
        exit(1);
    }

    arquivo = fopen("usuarios.dad","rb+");

    rewind(arquivo);
    do
    {
        fread(&dados,sizeof(usuario),1,arquivo);
        if (strcmp(dados.codigo,argv[1]) != 0 && !feof(arquivo))
        {
            fwrite(&dados,sizeof(usuario),1,arquivo);
        }
    } while (!feof(arquivo));

    fclose(arquivo);
    printf("\nRegistro excluido!");

    Sleep(1000);
    exit(1);

}
