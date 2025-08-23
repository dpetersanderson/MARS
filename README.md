# MARS

Fork do MARS - assembler e simulador de MIPS.

MARS é um ambiente de desenvolvimento integrado (IDE) para programação em assembly MIPS, pensado para uso educacional e desenvolvido originalmente por Pete Sanderson, enquanto professor e Ken Vollmar.

Essa versão, mantida pela estudante Sasha Rodela Steidle da Universidade Federal de São Paulo - UNIFESP, inclui correções de bugs, melhorias à experiência do usuário e uma interface segura para integração ao Sharif Judge. Isso porque a última versão original do MARS foi lançada em agosto de 2014 e, desde então, o projeto ficou abandonado. A descoberta de bugs no programa gerou a necessidade de manutenção.

A documentação do MARS pode ser acessada em https://dpetersanderson.github.io/ ou pelo menu "Help" dentro do programa.

## Downloads

Esse programa precisa do Ambiente de Execução Java para funcionar. Se você não tiver algum instalado ao executar o programa, abrirá uma janela no seu navegador padrão no site oficial de download do Java.

### Windows

Basta fazer o download da versão para [Windows](https://github.com/Sa-RSt/MARS/releases/download/v4.6.2/Mars-Windows.exe).

### Linux

Feito o download para a versão para [Linux](https://github.com/Sa-RSt/MARS/releases/download/v4.6.2/Mars-Linux), talvez o arquivo não fique marcado como executável, impedindo a execução do programa.
Se for esse o caso, clique com o botão direito no arquivo baixado, vá em "Permissões" e permita a execução do programa (instruções podem variar a depender da configuração do sistema) ou execute no terminal, mudando o nome do arquivo de acordo:
```
$ chmod +x ~/Downloads/Mars-Linux
```

### Outros sistemas

Para executar o MARS em outros sistemas, verifique se o Java está corretamente instalado. Se não estiver, [faça o download do Java.](https://www.java.com/download/). Depois disso, baixe a versão do MARS para [Outros sistemas](https://github.com/Sa-RSt/MARS/releases/download/v4.6.2/Mars.jar).

## Como compilar?

Primeiramente, instale o [Docker](https://docs.docker.com/get-started/). Então, construa e execute um container (comando de exemplo):
```
docker build -t mars .; docker run --rm --mount type=bind,src=<caminho da pasta para guardar os artefatos>,dst=/mnt mars
```
Se estiver no Linux e seu usuário não estiver no grupo `docker`, `sudo` ou alguma alternativa pode ser necessário para ambos os comandos.  
Deve ser especificado um diretório onde serão gravados os executáveis. **Ele deve estar fora do repositório**. 
