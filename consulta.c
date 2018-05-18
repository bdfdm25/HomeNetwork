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
    FILE *arquivo, *arquivoConsulta;
    usuario dados;

    arquivo = fopen("usuarios.dad","rb+");
    arquivoConsulta = fopen("Consulta.txt", "w+");

    fseek(arquivo,0*sizeof(usuario),SEEK_SET);
    do
    {
        if(!feof(arquivo)){

            fread(&dados,sizeof(usuario),1,arquivo);
            if (strcmp(dados.codigo,argv[1]) == 0 && !feof(arquivo))
            {
               // printf("\nCodigo encontrado");
               // printf("\nNome = %s  Senha = %s Tipo = %s \n\n", dados.usuario, dados.senha, dados.tipo);
                fputs(dados.usuario, arquivoConsulta);
                fputs(";", arquivoConsulta);
                fputs(dados.senha, arquivoConsulta);
                fputs(";", arquivoConsulta);
                fputs(dados.tipo, arquivoConsulta);
                fputs(";", arquivoConsulta);
            }
        }

    }while (!feof(arquivo));

    Sleep(1000);
    exit(1);
}
