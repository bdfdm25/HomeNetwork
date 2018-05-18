#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct
{
  char id[2];
  char status;
  char nome[30];
  char codx[4];
  char cody[4];

}equip;

main(int argc, char *argv[])
{
    FILE *equipamentos;
    char caractere;
    int x = 0;
    equip novo;

    if((equipamentos=fopen("c:\\temp\\texto.txt","rt"))==NULL)
    {
        printf("Não é possível abrir arquivo...");
        exit(1);
    }
while(!feof(equipamentos)){
 caractere = fgetc(equipamentos);
    while(caractere != 10){


        while(caractere != 59){
                novo.id += caractere;
                if(strcmp(novo.id,argv[1])==0){
                    x++;
                }
                caractere = fgetc(equipamentos);
        }
        if(x>0){
        fputs(argv[2],equipamentos);
        }
        caractere = fgetc(equipamentos);
        while(caractere != 59){
                novo.status=caractere;
                caractere = fgetc(equipamentos);
        }
        caractere = fgetc(equipamentos);
        while(caractere != 59){
                novo.nome += caractere;
                caractere = fgetc(equipamentos);
        }
        caractere = fgetc(equipamentos);
        while(caractere != 59){
                novo.codx += caractere;
                caractere = fgetc(equipamentos);
        }
        caractere = fgetc(equipamentos);
        while(caractere != 59){
                novo.cody += caractere;
                caractere = fgetc(equipamentos);
        }
        caractere = fgetc(equipamentos);
    }
}
    getchar();
}
